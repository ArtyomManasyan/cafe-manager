package com.cafeManagerAssignment.cafeManager.service.impl;

import com.cafeManagerAssignment.cafeManager.dto.ProductDto;
import com.cafeManagerAssignment.cafeManager.dto.mapper.ProductMapper;
import com.cafeManagerAssignment.cafeManager.repository.ProductRepository;
import com.cafeManagerAssignment.cafeManager.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Optional.ofNullable(productDto)
                .map(ProductDto::getName)
                .flatMap(productRepository::findByName)
                .ifPresent(product -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            "Product with name = " + productDto.getName() + " already exists");
                });

        return Optional.ofNullable(productDto)
                .map(ProductMapper::toProduct)
                .flatMap(product -> Optional.of(productRepository.save(product)))
                .map(ProductMapper::toProductDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Product has not been created"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toProductDto)
                .collect(Collectors.toList());
    }
}

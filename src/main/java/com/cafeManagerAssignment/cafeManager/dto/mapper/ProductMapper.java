package com.cafeManagerAssignment.cafeManager.dto.mapper;

import com.cafeManagerAssignment.cafeManager.dto.ProductDto;
import com.cafeManagerAssignment.cafeManager.model.Product;

import java.util.Optional;

public class ProductMapper {

    public static Product toProduct(ProductDto productDto) {

        Optional<ProductDto> optional = Optional.ofNullable(productDto);
        return new Product()
                .setId(optional.map(ProductDto::getId).orElse(null))
                .setPrice(optional.map(ProductDto::getPrice).orElse(null))
                .setName(optional.map(ProductDto::getName).orElse(null));
    }

    public static ProductDto toProductDto(Product product) {

        Optional<Product> optional = Optional.ofNullable(product);
        return new ProductDto()
                .setId(optional.map(Product::getId).orElse(null))
                .setPrice(optional.map(Product::getPrice).orElse(null))
                .setName(optional.map(Product::getName).orElse(null));
    }
}

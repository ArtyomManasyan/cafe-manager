package com.cafeManagerAssignment.cafeManager.service;

import com.cafeManagerAssignment.cafeManager.dto.ProductDto;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface ProductService {

    /**
     * Create product with given product information.
     *
     * @param productDto a new product which will storing in database.
     * @throws ResponseStatusException if the name of product already exist in database.
     * @return a product which hsa stored in database.
     */
    ProductDto createProduct(ProductDto productDto);

    /**
     * Finding all available products in the database.
     *
     * @return list of products stored in database.
     */
    List<ProductDto> getProducts();
}

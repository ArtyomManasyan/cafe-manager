package com.cafeManagerAssignment.cafeManager.service;

import com.cafeManagerAssignment.cafeManager.dto.ProductDto;

import java.util.List;

public interface ProductService {

    /**
     * Create product with given product information.
     *
     * @param product
     * @return
     */
    ProductDto createProduct(ProductDto product);

    /**
     * Returns all available products in the database.
     *
     * @return
     */
    List<ProductDto> getProducts();
}

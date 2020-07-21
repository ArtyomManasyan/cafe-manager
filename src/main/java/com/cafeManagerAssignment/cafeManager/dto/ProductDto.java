package com.cafeManagerAssignment.cafeManager.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class ProductDto {

    private Long id;

    private String name;

    private Double price;

    private List<ProductInOrderDto> productInOrderList;
}

package com.cafeManagerAssignment.cafeManager.dto;

import com.cafeManagerAssignment.cafeManager.model.enums.ProductInOrderStatus;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ProductInOrderDto {

    private Long id;

    private ProductInOrderStatus status;

    private Integer count;

    private OrderDto order;

    private ProductDto product;
}

package com.cafeManagerAssignment.cafeManager.dto;

import com.cafeManagerAssignment.cafeManager.model.enums.OrderStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class OrderDto {

    private Long id;

    private Integer orderNumber;

    private OrderStatus status;

    private CoffeeTableDto coffeeTable;

    private List<ProductInOrderDto> productInOrderList;
}

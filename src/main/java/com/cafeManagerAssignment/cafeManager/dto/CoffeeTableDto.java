package com.cafeManagerAssignment.cafeManager.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class CoffeeTableDto {

    private Long tableNumber;

    private String name;

    private List<OrderDto> orders;

    private UserDto user;
}

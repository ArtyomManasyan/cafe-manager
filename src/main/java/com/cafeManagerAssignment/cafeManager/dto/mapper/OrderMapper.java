package com.cafeManagerAssignment.cafeManager.dto.mapper;

import com.cafeManagerAssignment.cafeManager.dto.OrderDto;
import com.cafeManagerAssignment.cafeManager.model.Order;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto toOrderDto(Order order) {

        Optional<Order> optional = Optional.ofNullable(order);
        return new OrderDto()
                .setId(optional.map(Order::getId).orElse(null))
                .setStatus(optional.map(Order::getStatus).orElse(null))
                .setCoffeeTable(CoffeeTableMapper.toCoffeeTableDto(optional.map(Order::getCoffeeTable).orElse(null)))
                .setProductInOrderList(optional.map(Order::getProductInOrderList).orElse(Collections.emptyList())
                        .stream()
                        .map(ProductInOrderMapper::toProductInOrderDto)
                        .collect(Collectors.toList()));
    }

    public static Order toOrder(OrderDto orderDto) {

        Optional<OrderDto> optional = Optional.ofNullable(orderDto);
        return new Order()
                .setId(optional.map(OrderDto::getId).orElse(null))
                .setStatus(optional.map(OrderDto::getStatus).orElse(null))
                .setCoffeeTable(CoffeeTableMapper.toCoffeeTable(optional.map(OrderDto::getCoffeeTable).orElse(null)))
                .setProductInOrderList(optional.map(OrderDto::getProductInOrderList).orElse(Collections.emptyList())
                        .stream()
                        .map(ProductInOrderMapper::toProductInOrder)
                        .collect(Collectors.toList()));
    }
}

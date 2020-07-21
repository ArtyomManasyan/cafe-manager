package com.cafeManagerAssignment.cafeManager.dto.mapper;

import com.cafeManagerAssignment.cafeManager.dto.CoffeeTableDto;
import com.cafeManagerAssignment.cafeManager.model.CoffeeTable;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class CoffeeTableMapper {

    public static CoffeeTable toCoffeeTable(CoffeeTableDto coffeeTableDto) {

        Optional<CoffeeTableDto> optional = Optional.ofNullable(coffeeTableDto);
        return new CoffeeTable()
                .setId(optional.map(CoffeeTableDto::getTableNumber).orElse(null))
                .setName(optional.map(CoffeeTableDto::getName).orElse(null))
                .setUser(UserMapper.toUser(optional.map(CoffeeTableDto::getUser).orElse(null)))
                .setOrders(optional.map(CoffeeTableDto::getOrders).orElse(Collections.emptyList())
                        .stream()
                        .map(OrderMapper::toOrder)
                        .collect(Collectors.toList()));
    }

    public static CoffeeTableDto toCoffeeTableDto(CoffeeTable coffeeTable) {

        Optional<CoffeeTable> optional = Optional.ofNullable(coffeeTable);
        return new CoffeeTableDto()
                .setTableNumber(optional.map(CoffeeTable::getId).orElse(null))
                .setName(optional.map(CoffeeTable::getName).orElse(null))
                .setUser(UserMapper.toUserDto(optional.map(CoffeeTable::getUser).orElse(null)))
                .setOrders(optional.map(CoffeeTable::getOrders).orElse(Collections.emptyList())
                        .stream()
                        .map(OrderMapper::toOrderDto)
                        .collect(Collectors.toList()));
    }
}

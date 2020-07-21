package com.cafeManagerAssignment.cafeManager.dto.mapper;

import com.cafeManagerAssignment.cafeManager.dto.ProductInOrderDto;
import com.cafeManagerAssignment.cafeManager.model.ProductInOrder;

import java.util.Optional;

public class ProductInOrderMapper {

    public static ProductInOrder toProductInOrder(ProductInOrderDto productInOrderDto) {

        Optional<ProductInOrderDto> optional = Optional.ofNullable(productInOrderDto);
        return new ProductInOrder()
                .setId(optional.map(ProductInOrderDto::getId).orElse(null))
                .setCount(optional.map(ProductInOrderDto::getCount).orElse(null))
                .setStatus(optional.map(ProductInOrderDto::getStatus).orElse(null))
                .setProduct(ProductMapper.toProduct(optional.map(ProductInOrderDto::getProduct).orElse(null)))
                .setOrder(OrderMapper.toOrder(optional.map(ProductInOrderDto::getOrder).orElse(null)));
    }

    public static ProductInOrderDto toProductInOrderDto(ProductInOrder productInOrder) {

        Optional<ProductInOrder> optional = Optional.ofNullable(productInOrder);
        return new ProductInOrderDto()
                .setId(optional.map(ProductInOrder::getId).orElse(null))
                .setCount(optional.map(ProductInOrder::getCount).orElse(null))
                .setStatus(optional.map(ProductInOrder::getStatus).orElse(null))
                .setProduct(ProductMapper.toProductDto(optional.map(ProductInOrder::getProduct).orElse(null)))
                .setOrder(OrderMapper.toOrderDto(optional.map(ProductInOrder::getOrder).orElse(null)));
    }
}

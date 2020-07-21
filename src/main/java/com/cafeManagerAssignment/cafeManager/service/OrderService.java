package com.cafeManagerAssignment.cafeManager.service;

import com.cafeManagerAssignment.cafeManager.dto.OrderDto;
import com.cafeManagerAssignment.cafeManager.dto.ProductInOrderDto;
import com.cafeManagerAssignment.cafeManager.model.enums.OrderStatus;


public interface OrderService {

    /**
     * Create order with given coffeeTable and order information if coffeeTable do not have order with status open.
     *
     * @param tableId
     * @param orderDto
     * @return
     */
    OrderDto createOrder(OrderDto orderDto);

    /**
     * Update order's productInOrder based on order id.
     *
     * @param orderId
     * @param product
     * @return
     */
    OrderDto updateOrderProduct(Long orderId, ProductInOrderDto product);

    /**
     * Update order's status based on order id.
     *
     * @param orderId
     * @param status
     * @return
     */
    OrderDto updateOrderStatus(Long orderId, OrderStatus status);
}

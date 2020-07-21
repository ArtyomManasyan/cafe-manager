package com.cafeManagerAssignment.cafeManager.service;

import com.cafeManagerAssignment.cafeManager.dto.OrderDto;
import com.cafeManagerAssignment.cafeManager.dto.ProductInOrderDto;
import com.cafeManagerAssignment.cafeManager.model.enums.OrderStatus;
import org.springframework.web.server.ResponseStatusException;


public interface OrderService {

    /**
     * Creating order with given coffeeTable and order information if
     * coffeeTable do not have order with status open.
     *
     * @param orderDto a new order which will storing in database it also
     *                 containing information of coffeeTable which one it will assigning.
     * @throws ResponseStatusException if the coffeeTable assigned
     *                                 to order already assigned another order.
     * @return a order already has stored in database.
     */
    OrderDto createOrder(OrderDto orderDto);

    /**
     * Update productInOrder of order.
     *
     * @param orderId an uniq id of order which productInOrder will updating.
     * @param productInOrderDto a new one, which will storing into order.
     * @throws ResponseStatusException if the order whit given orderId not present in database.
     * @return a order already has updated.
     */
    OrderDto updateOrderProduct(Long orderId, ProductInOrderDto productInOrderDto);

    /**
     * Updating status of order.
     *
     * @param orderId an uniq id of order which status will updating.
     * @param status a new enum value, which is intended to order
     * @throws ResponseStatusException if the order whit given orderId not present in database.
     * @return
     */
    OrderDto updateOrderStatus(Long orderId, OrderStatus status);
}

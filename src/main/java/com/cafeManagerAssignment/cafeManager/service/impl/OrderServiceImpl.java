package com.cafeManagerAssignment.cafeManager.service.impl;

import com.cafeManagerAssignment.cafeManager.dto.OrderDto;
import com.cafeManagerAssignment.cafeManager.dto.ProductInOrderDto;
import com.cafeManagerAssignment.cafeManager.dto.mapper.OrderMapper;
import com.cafeManagerAssignment.cafeManager.model.CoffeeTable;
import com.cafeManagerAssignment.cafeManager.model.Order;
import com.cafeManagerAssignment.cafeManager.model.ProductInOrder;
import com.cafeManagerAssignment.cafeManager.model.enums.OrderStatus;
import com.cafeManagerAssignment.cafeManager.repository.CoffeeTableRepository;
import com.cafeManagerAssignment.cafeManager.repository.OrderRepository;
import com.cafeManagerAssignment.cafeManager.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CoffeeTableRepository coffeeTableRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CoffeeTableRepository coffeeTableRepository) {
        this.orderRepository = orderRepository;
        this.coffeeTableRepository = coffeeTableRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        CoffeeTable coffeeTable = coffeeTableRepository.findById(orderDto.getCoffeeTable().getTableNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such table with id = " + orderDto.getCoffeeTable().getTableNumber()));

        orderRepository.findByCoffeeTableIdAndStatus(orderDto.getCoffeeTable().getTableNumber(), OrderStatus.OPEN)
                .ifPresent(existingOrder -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Already exist order with table id = " + orderDto.getCoffeeTable().getTableNumber() + " and OPEN status");
                });

        return OrderMapper.toOrderDto(orderRepository.save(OrderMapper.toOrder(orderDto)));
    }

    @Override
    public OrderDto updateOrderProduct(Long orderId, ProductInOrderDto productInOrderDto) {
        Order currentOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "So such order with orderId= " + orderId));

        ProductInOrder existingProductInOrder = currentOrder.getProductInOrderList()
                .stream()
                .filter(productInOrder -> productInOrder.getId().equals(productInOrderDto.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such productInOrder with id = " + productInOrderDto.getId()));

        existingProductInOrder.setCount(productInOrderDto.getCount());

        return OrderMapper.toOrderDto(currentOrder);
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, OrderStatus status) {
        Order currentOrder = orderRepository.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "So such order with orderId= " + orderId));

        currentOrder.setStatus(status);

        currentOrder.setCoffeeTable(null);

        return OrderMapper.toOrderDto(currentOrder);

    }
}

package com.cafeManagerAssignment.cafeManager.repository;

import com.cafeManagerAssignment.cafeManager.model.Order;
import com.cafeManagerAssignment.cafeManager.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByCoffeeTableIdAndStatus(Long tableId, OrderStatus status);
}

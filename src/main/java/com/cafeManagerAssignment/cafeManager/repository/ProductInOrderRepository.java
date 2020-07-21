package com.cafeManagerAssignment.cafeManager.repository;

import com.cafeManagerAssignment.cafeManager.model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {
}

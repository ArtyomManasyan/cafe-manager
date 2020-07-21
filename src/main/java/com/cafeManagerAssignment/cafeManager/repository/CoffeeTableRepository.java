package com.cafeManagerAssignment.cafeManager.repository;

import com.cafeManagerAssignment.cafeManager.model.CoffeeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoffeeTableRepository extends JpaRepository<CoffeeTable, Long> {

    /**
     * @param userId
     * @return
     */
    List<CoffeeTable> findAllByUserId(Long userId);

    Optional<CoffeeTable> findByName(String name);
}

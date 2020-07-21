package com.cafeManagerAssignment.cafeManager.service;

import com.cafeManagerAssignment.cafeManager.dto.CoffeeTableDto;

import java.util.List;

public interface CoffeeTableService {

    /**
     * Create coffeeTable.
     *
     * @param coffeeTableDto
     * @return
     */
    CoffeeTableDto createCoffeeTable(CoffeeTableDto coffeeTableDto);

    /**
     * Assign coffeeTable to user
     *
     * @param userId
     * @param tableId
     * @return
     */
    CoffeeTableDto assignCoffeeTable(Long userId, Long tableId);

    /**
     * Returns coffeeTables assigned to given user
     *
     * @param userId
     * @return
     */
    List<CoffeeTableDto> getAssignedTables(Long userId);
}

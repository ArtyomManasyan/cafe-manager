package com.cafeManagerAssignment.cafeManager.service;

import com.cafeManagerAssignment.cafeManager.dto.CoffeeTableDto;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CoffeeTableService {

    /**
     * Create coffeeTable.
     *
     * @param coffeeTableDto is a new table which will creating
     *                       after checking if it is not exist in database.
     * @throws ResponseStatusException if the coffeeTable already exist.
     * @return a coffeeTableDto which hsa stored in database.
     */
    CoffeeTableDto createCoffeeTable(CoffeeTableDto coffeeTableDto);

    /**
     * Assign coffeeTable to user.
     *
     * @param userId an uniq id whit help of
     *               it finding user in database.
     * @param tableId an uniq id of coffeeTable into which setting user.
     * @return a coffeeTable which user has set.
     */
    CoffeeTableDto assignCoffeeTable(Long userId, Long tableId);

    /**
     * Returns coffeeTables assigned to given user
     *
     * @param userId an uniq id of user which is assigned coffeeTables.
     * @return a List containing coffeeTables assigned to given user.
     */
    List<CoffeeTableDto> getAssignedTables(Long userId);
}

package com.cafeManagerAssignment.cafeManager.service.impl;

import com.cafeManagerAssignment.cafeManager.dto.CoffeeTableDto;
import com.cafeManagerAssignment.cafeManager.dto.mapper.CoffeeTableMapper;
import com.cafeManagerAssignment.cafeManager.model.CoffeeTable;
import com.cafeManagerAssignment.cafeManager.model.User;
import com.cafeManagerAssignment.cafeManager.repository.CoffeeTableRepository;
import com.cafeManagerAssignment.cafeManager.repository.UserRepository;
import com.cafeManagerAssignment.cafeManager.service.CoffeeTableService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CoffeeTableServiceImpl implements CoffeeTableService {

    private final CoffeeTableRepository coffeeTableRepository;
    private final UserRepository userRepository;

    public CoffeeTableServiceImpl(CoffeeTableRepository coffeeTableRepository, UserRepository userRepository) {
        this.coffeeTableRepository = coffeeTableRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CoffeeTableDto createCoffeeTable(CoffeeTableDto coffeeTableDto) {
        coffeeTableRepository.findByName(coffeeTableDto.getName())
                .ifPresent(existingCoffeeTable -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Already exist coffeeTable with name = " + coffeeTableDto.getName());
                });

        return CoffeeTableMapper.toCoffeeTableDto(coffeeTableRepository.save(CoffeeTableMapper.toCoffeeTable(coffeeTableDto)));

    }

    @Override
    public CoffeeTableDto assignCoffeeTable(Long userId, Long tableId) {
        User waiter = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user userId = " + userId));

        CoffeeTable coffeeTable = coffeeTableRepository.findById(tableId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Such table"));

        Optional.ofNullable(coffeeTable.getUser())
                .ifPresent(user -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Table already assigned to user " + user.getId());
                });

        coffeeTable.setUser(waiter);

        return CoffeeTableMapper.toCoffeeTableDto(coffeeTable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CoffeeTableDto> getAssignedTables(Long userId) {
        return coffeeTableRepository.findAllByUserId(userId)
                .stream()
                .map(CoffeeTableMapper::toCoffeeTableDto)
                .collect(Collectors.toList());
    }
}

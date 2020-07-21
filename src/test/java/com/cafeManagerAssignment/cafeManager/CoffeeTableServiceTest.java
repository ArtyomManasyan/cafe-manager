package com.cafeManagerAssignment.cafeManager;

import com.cafeManagerAssignment.cafeManager.dto.CoffeeTableDto;
import com.cafeManagerAssignment.cafeManager.model.CoffeeTable;
import com.cafeManagerAssignment.cafeManager.model.User;
import com.cafeManagerAssignment.cafeManager.repository.CoffeeTableRepository;
import com.cafeManagerAssignment.cafeManager.repository.UserRepository;
import com.cafeManagerAssignment.cafeManager.service.CoffeeTableService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class CoffeeTableServiceTest {

    @MockBean
    private CoffeeTableRepository coffeeTableRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CoffeeTableService coffeeTableService;

    @Test
    public void testCreateTable() {
        CoffeeTable coffeeTable = new CoffeeTable();
        coffeeTable.setId(1L);
        coffeeTable.setName("Name");

        given(coffeeTableRepository.save(any())).willReturn(coffeeTable);

        CoffeeTableDto dto = new CoffeeTableDto();
        dto.setName("Name");

        CoffeeTableDto savedDto = coffeeTableService.createCoffeeTable(dto);

        assertThat(savedDto.getTableNumber()).isEqualTo(coffeeTable.getId());
        assertThat(savedDto.getName()).isEqualTo(coffeeTable.getName());
    }

    @Test
    public void testAssignTable() {
        User user = new User();
        user.setUsername("aaa");
        user.setPassword("bbb");
        user.setId(1L);

        CoffeeTable table = new CoffeeTable();
        table.setId(1L);
        table.setName("Name");

        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        given(coffeeTableRepository.findById(table.getId())).willReturn(Optional.of(table));

        CoffeeTableDto savedDto = coffeeTableService.assignCoffeeTable(1L, 1L);

        assertThat(savedDto.getTableNumber()).isEqualTo(table.getId());
        assertThat(savedDto.getName()).isEqualTo(table.getName());
        assertThat(savedDto.getUser()).isNotNull();
        assertThat(savedDto.getUser().getId()).isEqualTo(user.getId());
    }
}

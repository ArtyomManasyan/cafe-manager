package com.cafeManagerAssignment.cafeManager.controller.v1.api;

import com.cafeManagerAssignment.cafeManager.controller.v1.util.AuthUtil;
import com.cafeManagerAssignment.cafeManager.dto.CoffeeTableDto;
import com.cafeManagerAssignment.cafeManager.service.CoffeeTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/coffee-tables")
public class CoffeeTableController {

    private final CoffeeTableService coffeeTableService;

    public CoffeeTableController(CoffeeTableService coffeeTableService) {
        this.coffeeTableService = coffeeTableService;
    }

    @Operation(summary = "Create new coffeeTable", description = "", tags = {"coffee-Table"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CoffeeTable created",
                    content = @Content(schema = @Schema(implementation = CoffeeTableDto.class))),
            @ApiResponse(responseCode = "409", description = "CoffeeTable already exists")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeTableDto createCoffeeTable(@RequestBody CoffeeTableDto coffeeTableDto) {
        log.info("CoffeeTable {} creating", coffeeTableDto);
        return coffeeTableService.createCoffeeTable(coffeeTableDto);
    }

    @Operation(summary = "Assign coffeeTable to waiter", description = "", tags = {"coffee-Table"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CoffeeTables assigned",
                    content = @Content(schema = @Schema(implementation = CoffeeTableDto.class))),
            @ApiResponse(responseCode = "409", description = "CoffeeTable already assigned")})
    @PutMapping("/{tableId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public CoffeeTableDto assignCoffeeTable(@PathVariable("userId") Long userId,
                                            @PathVariable("tableId") Long tableId) {
        log.info("CoffeeTable {} assigning to User {}", tableId, userId);
        return coffeeTableService.assignCoffeeTable(userId, tableId);
    }

    @Operation(summary = "Retrieve coffeeTables assigned to waiter", description = "", tags = {"coffee-Table"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Waiter CoffeeTables",
                    content = @Content(schema = @Schema(implementation = CoffeeTableDto.class))),
            @ApiResponse(responseCode = "404", description = "No coffeeTable assigned")})
    @GetMapping("/waiter")
    @ResponseStatus(HttpStatus.OK)
    public List<CoffeeTableDto> getAssignedTables() {
        log.info("User {} retrieving assigned tables", AuthUtil.getUserId());
        return coffeeTableService.getAssignedTables(AuthUtil.getUserId());
    }

}

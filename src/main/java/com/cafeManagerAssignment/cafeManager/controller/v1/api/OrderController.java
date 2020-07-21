package com.cafeManagerAssignment.cafeManager.controller.v1.api;

import com.cafeManagerAssignment.cafeManager.dto.OrderDto;
import com.cafeManagerAssignment.cafeManager.dto.ProductInOrderDto;
import com.cafeManagerAssignment.cafeManager.model.enums.OrderStatus;
import com.cafeManagerAssignment.cafeManager.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Create new order", description = "", tags = {"order-management"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created",
                    content = @Content(schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "409", description = "Order already exists")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        log.info("Order {} creating", orderDto);
        return orderService.createOrder(orderDto);
    }

    @Operation(summary = "Edit order's product", description = "", tags = {"order-management"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order updated",
                    content = @Content(schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404", description = "Order not found")})
    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateOrderProduct(@PathVariable("orderId") Long orderId,
                                       @RequestBody ProductInOrderDto product) {
        log.info("order {} updating for product {}", orderId, product.getId());
        return orderService.updateOrderProduct(orderId, product);
    }

    @Operation(summary = "Edit order status", description = "", tags = {"order-management"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order updated",
                    content = @Content(schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(responseCode = "404", description = "Order not found")})
    @PatchMapping("{orderId}/status/{orderStatus}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateOrderStatus(@PathVariable("orderId") Long orderId,
                                      @PathVariable("orderStatus") OrderStatus status) {
        log.info("Order {} updating change status to {}", orderId, status);
        return orderService.updateOrderStatus(orderId, status);
    }

}

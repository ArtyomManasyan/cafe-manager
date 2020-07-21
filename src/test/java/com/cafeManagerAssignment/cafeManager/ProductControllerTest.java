package com.cafeManagerAssignment.cafeManager;

import com.cafeManagerAssignment.cafeManager.dto.ProductDto;
import com.cafeManagerAssignment.cafeManager.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @WithMockUser
    @Test
    public void testCreateProductShouldOk() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1l);
        productDto.setName("Burger");
        productDto.setPrice(100.5);

        given(productService.createProduct(any())).willReturn(productDto);

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Burger"));
    }

    @WithMockUser
    @Test
    public void testCreateProductShouldFail() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1l);
        productDto.setName("Burger");
        productDto.setPrice(100.5);

        given(productService.createProduct(any())).willThrow(new ResponseStatusException(HttpStatus.CONFLICT, ""));

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isConflict());
    }

    @Test
    public void testGetProductsShouldFailWithoutAuthentication() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1l);
        productDto.setName("Burger");
        productDto.setPrice(100.5);

        given(productService.getProducts()).willReturn(Collections.singletonList(productDto));

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isUnauthorized());
    }
}

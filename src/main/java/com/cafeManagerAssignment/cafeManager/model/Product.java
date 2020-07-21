package com.cafeManagerAssignment.cafeManager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "product_name", unique = true)
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "price")
    private Double price;
}

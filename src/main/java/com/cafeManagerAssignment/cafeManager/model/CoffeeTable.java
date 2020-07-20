package com.cafeManagerAssignment.cafeManager.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "coffee_table")
public class CoffeeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            mappedBy = "coffeeTable",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

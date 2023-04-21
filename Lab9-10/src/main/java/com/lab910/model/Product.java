package com.lab910.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Products")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;

    @Column(name = "Code")
    private String code;

    @Column(name = "ProductName", nullable = false)
    private String productName;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Column(name = "Illustration")
    private String illustration;

    @Column(name = "Description")
    private String description;
}

package com.lab910.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Orders")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;

    @Column(name = "OrderNumber", unique = true, nullable = false)
    private String orderNumber;

    @Column(name = "TotalSellingPrice", nullable = false)
    private BigDecimal totalSellingPrice;

    @Column(name = "ProductList")
    private String productList;
}

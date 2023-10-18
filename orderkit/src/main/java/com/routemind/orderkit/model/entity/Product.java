package com.routemind.orderkit.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "code")
    private String code;
    @Column(name = "product_code")
    private String productCode;
    @Column(name = "price")
    private Double price;
    @Column(name = "currency")
    private String currency;
    @Column(name = "unit")
    private String unit;
    @Column(name = "count")
    private Double count;
    @OneToOne
    private Company company;
    @ManyToOne
    private Status status;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

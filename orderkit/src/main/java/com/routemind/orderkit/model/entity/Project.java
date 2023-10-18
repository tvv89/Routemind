package com.routemind.orderkit.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Status status;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

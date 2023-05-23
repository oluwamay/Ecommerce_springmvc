package com.estore.ecommercespring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "category_id")
    private Long id;
    private String name;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "category",
            orphanRemoval = true
    )
    private Set<Product> product= new HashSet<>();
}

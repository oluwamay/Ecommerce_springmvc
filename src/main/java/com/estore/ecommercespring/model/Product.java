package com.estore.ecommercespring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "product_gen")
    @SequenceGenerator(name = "product_gen",
            sequenceName = "prod_seq_name",
            allocationSize = 1)
    private Long product_id;
    private String product_name;
    private String description;
    private String price;
    private String imageName;
    @CreationTimestamp
    private LocalDateTime dateAdded;
    @UpdateTimestamp
    private LocalDate dateUpdated;
    @ManyToOne()
    @JoinColumn(
            name= "id",
            referencedColumnName = "category_id"
    )
    private Category category;
}

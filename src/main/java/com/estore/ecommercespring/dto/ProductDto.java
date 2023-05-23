package com.estore.ecommercespring.dto;

import com.estore.ecommercespring.model.Category;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private String price;
    private String imageName;
    private Long id;
    private Long category_id;
    private String description;

}

package com.estore.ecommercespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;

}

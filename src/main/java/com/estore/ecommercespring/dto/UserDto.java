package com.estore.ecommercespring.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String userEmail;
    private String password;
}

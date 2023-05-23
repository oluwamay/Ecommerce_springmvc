package com.estore.ecommercespring.service;

import com.estore.ecommercespring.dto.UserDto;
import com.estore.ecommercespring.model.User;

public interface
UserService {
    User authenticate(UserDto user);
   User saveUser(UserDto userDto);
}

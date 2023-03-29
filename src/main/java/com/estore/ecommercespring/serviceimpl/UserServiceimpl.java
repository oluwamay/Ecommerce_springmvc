package com.estore.ecommercespring.serviceimpl;

import com.estore.ecommercespring.dto.UserDto;
import com.estore.ecommercespring.model.User;
import com.estore.ecommercespring.repository.UserRepository;
import com.estore.ecommercespring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceimpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User saveUser(UserDto userDto) {
        if(userDto == null){
            return null;
        }else {
            User user = User.builder()
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .email(userDto.getUserEmail())
                    .password(userDto.getPassword())
                    .build();
            return userRepository.save(user);
        }
    }

    public User authenticate(String email, String password){
        User user = userRepository.findUserByEmailAndPassword(email, password).get();
        return user;
    }
}

package com.estore.ecommercespring.serviceimpl;

import com.estore.ecommercespring.dto.UserDto;
import com.estore.ecommercespring.model.User;
import com.estore.ecommercespring.repository.UserRepository;
import com.estore.ecommercespring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

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

            User user = new User(
                    userDto.getFirstName(),
                    userDto.getLastName(),
                    userDto.getPassword(),
                    userDto.getUserEmail()
            );

            return userRepository.save(user);
        }
    }

    public User authenticate(UserDto userDTO){
        User user = userRepository.findUserByEmail(userDTO.getUserEmail()).get();
        boolean check = user.checkPassword(userDTO.getPassword(), user.getPassword());
        if(check){
            return user;
        }
        return null;
    }
}

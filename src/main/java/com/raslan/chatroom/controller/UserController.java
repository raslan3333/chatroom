package com.raslan.chatroom.controller;

import com.raslan.chatroom.model.*;
import com.raslan.chatroom.repositories.*;
import com.raslan.chatroom.service.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*",maxAge = 3600, allowCredentials = "false", allowedHeaders = "*")
public class UserController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

/*    @GetMapping(path = "/{id}")
    public UserRest getOneUser(@PathVariable Long id) {
        UserRest userRest = new UserRest();
        userRest.setUsername(userRepository.findById(id).get().getUsername());
        return userRest;
    }*/

    @GetMapping("/all")
    public UserRest getUser(Authentication authentication) {
        UserEntity userEntity = userRepository.findByUsername(authentication.getPrincipal().toString());
        UserRest userRest = new UserRest();
        BeanUtils.copyProperties(userEntity ,userRest);
        System.out.println(userRest.getUsername()+ userRest.getId());
        return userRest;
    }


    @GetMapping("test1")
    public String getTest1() {
        return "getTest1 was called";
    }

    @GetMapping("test2")
    public String getTest2() {
        return "getTest2 was called";
    }

    @PostMapping("/signup")
    public UserRest createUser(@RequestBody UserDetailsRequestModel model){
        System.out.println(model.getUsername());
        if (userRepository.findByUsername(model.getUsername()) != null) {
            throw new RuntimeException("this user exists");
        }

        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(model ,userEntity);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(model.getPassword()));
        UserEntity storedUserDetails = userRepository.save(userEntity);
        BeanUtils.copyProperties(storedUserDetails, userDto);
        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    @PutMapping
    public String updateUser() {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user was called";
    }

}

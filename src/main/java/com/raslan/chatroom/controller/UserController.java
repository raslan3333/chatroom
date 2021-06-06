package com.raslan.chatroom.controller;

import com.raslan.chatroom.model.*;
import com.raslan.chatroom.repositories.*;
import com.raslan.chatroom.service.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*",maxAge = 3600, allowCredentials = "false")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id) {
        UserRest userRest = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDto, userRest);
        return userRest;
    }


    @GetMapping
    public String getUser() {
        return "get user was called";
    }

    @GetMapping("test1")
    public String getTest1() {
        return "getTest1 was called";
    }

    @GetMapping("test2")
    public String getTest2() {
        return "getTest2 was called";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel model){
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(model, userDto);
        UserDto createUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createUser, returnValue);
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

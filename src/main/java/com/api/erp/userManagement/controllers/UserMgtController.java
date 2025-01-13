package com.api.erp.userManagement.controllers;

import com.api.erp.userManagement.entity.UserMgt;
import com.api.erp.userManagement.services.UserService;
import com.api.erp.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserMgtController {

    @Autowired
    private UserService userService;

    @PostMapping("/userMgt")
    public ApiResponse<UserMgt> addUsers(@RequestBody UserMgt user){
//        System.out.println(user.getUsername());
        UserMgt newUser = userService.addUser(user);
        return new ApiResponse<>(true, "User created successfully", newUser);
    }

    @GetMapping("/user/{id}")
    public ApiResponse<UserMgt> getUserById(@PathVariable("id") long id) throws ChangeSetPersister.NotFoundException {
       UserMgt user = userService.getUserById(id);
       return new ApiResponse<>(true, "User found", user);
    }


    @GetMapping("/users")
    public ApiResponse<List<UserMgt>> getUsers(){
        List<UserMgt> users = userService.findAll();
        return new ApiResponse<>(true, "Users found", users);
    }

    @PutMapping("/user/{id}")
    public ApiResponse<UserMgt> updateUser(@PathVariable("id") long id, @RequestBody UserMgt user) {
        UserMgt updateduser = userService.updateUserById(id, user);
        return new ApiResponse<>(true, "User updated successfully", updateduser);
    }
}

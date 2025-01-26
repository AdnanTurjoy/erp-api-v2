package com.api.erp.userManagement.controllers;

import com.api.erp.userManagement.dtos.UserDTO;
import com.api.erp.userManagement.entity.UserMgt;
import com.api.erp.userManagement.services.UserService;
import com.api.erp.utils.ApiResponse;
import com.api.erp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserMgtController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @PostMapping("/auth/user-register")
    public ApiResponse<UserMgt> addUsers(@RequestBody UserMgt user){
//        System.out.println(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserMgt newUser = userService.addUser(user);
        return new ApiResponse<>(true, "User created successfully", newUser);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
        );
        UserMgt user = userService.findByUsername(userDTO.getUsername());
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
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

    @GetMapping("/userByRole/{roleId}")
    public ApiResponse<List<UserMgt>> getUsersByRole(@PathVariable("roleId") long roleId){
        return new ApiResponse<>(true, "Users found", userService.getUsersByRole(roleId));
    }
}

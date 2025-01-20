package com.api.erp.userManagement.services.impl;

import com.api.erp.userManagement.entity.UserMgt;
import com.api.erp.userManagement.enums.Status;
import com.api.erp.userManagement.repository.UserRepository;
import com.api.erp.userManagement.services.UserService;
import com.api.erp.utils.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserMgt addUser(UserMgt us) {
        if (us.getUsername() == null || us.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        UserMgt user = new UserMgt();
        user.setUsername(us.getUsername());
        user.setPassword(user.getPassword());
        user.setEmail(us.getEmail());
        user.setRoleId(us.getRoleId());
        user.setStatus(Status.ACTIVE);
        return userRepository.save(user);
    }

    @Override
    public UserMgt getUserById(long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User Not Found", id)));
    }

    @Override
    public List<UserMgt> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserMgt updateUserById(long id, UserMgt user) {
        return null;
    }

    @Override
    public List<UserMgt> getUsersByRole(long roleId) {
        return userRepository.findByRoleId(roleId);
    }
}

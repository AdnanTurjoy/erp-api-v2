package com.api.erp.userManagement.services;
import com.api.erp.userManagement.entity.UserMgt;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;


public interface  UserService {

    UserMgt addUser(UserMgt user);
    UserMgt getUserById(long id) throws ChangeSetPersister.NotFoundException;

    List<UserMgt> findAll();

    UserMgt updateUserById(long id, UserMgt user);
}

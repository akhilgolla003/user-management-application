package com.dailybuffer.usermngtsystem.service;

import com.dailybuffer.usermngtsystem.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);


    List<User> getAllUsers();

    User getUserById(long id);

    boolean deleteUserById(long id);

    User updateUser(long id, User user);
}

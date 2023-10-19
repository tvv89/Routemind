package com.routemind.orderkit.service;

import com.routemind.orderkit.exception.UserNotFoundException;
import com.routemind.orderkit.model.entity.User;

public interface UserService {
    User createUser(User user);
    User findUserByEmail(String email);
    User updateUser(Long id, User user);
    User findUserById(Long id);
    void deleteUserById(Long id) throws UserNotFoundException;
}

package com.jwt.security.learn.securityjwt.service;


import com.jwt.security.learn.securityjwt.models.Role;
import com.jwt.security.learn.securityjwt.models.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);

    void addRoleToUser(String username,String roleName);

    User getUserByUsername(String username);

    List<User> getAllUsers();

}

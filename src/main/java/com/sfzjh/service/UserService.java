package com.sfzjh.service;

import com.sfzjh.entity.User;

public interface UserService {
    User checkUser(String username, String password);
}

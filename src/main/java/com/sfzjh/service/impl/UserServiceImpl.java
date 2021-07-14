package com.sfzjh.service.impl;

import com.sfzjh.dao.UserRepository;
import com.sfzjh.entity.User;
import com.sfzjh.service.UserService;
import com.sfzjh.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author  孙飞
 * @Date  2021年03月09日  21:31
 * @PackageName  com.sfzjh.service.impl
 * @Name  UserServiceImpl
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, Md5Utils.code(password));
        return user;
    }
}

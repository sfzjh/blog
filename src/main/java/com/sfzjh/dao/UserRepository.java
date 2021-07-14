package com.sfzjh.dao;

import com.sfzjh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户接口
 * @Author  孙飞
 * @Date  2021年03月09日 13:36
 * @PackageName  com.sfzjh.dao
 * @Name  UserRepository
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
public interface UserRepository extends JpaRepository<User ,Long> {
    /**
     * 根据用户名和密码查询用户进行登录
     * @author  孙飞
     * @date  2021年03月09日 13:36
     * @param username 用户名
     * @param password 密码
     * @return  com.sfzjh.entity.User
     */
    User findByUsernameAndPassword(String username, String password);
}

package com.sfzjh.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 用户类
 * @Author  孙飞
 * @Date  2021年03月09日 13:13
 * @PackageName  com.sfzjh.entity
 * @Name  User
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "t_user")
public class User {
    /**
     * ID
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户类型：1-管理员  2-普通用户
     */
    private Integer type;
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatTime;
    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    /**
     * 用户所发布的博客列表
     */
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();
}

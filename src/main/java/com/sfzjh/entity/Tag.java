package com.sfzjh.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * 博客标签类
 * @Author  孙飞
 * @Date  2021年03月09日 13:10
 * @PackageName  com.sfzjh.entity
 * @Name  Tag
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "t_tag")
public class Tag {
    /**
     * 标签ID
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 标签名称
     */
    private String name;
    /**
     * uuid
     */
    private String uuid;
    /**
     * 标签所对应的博客
     */
    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();

}

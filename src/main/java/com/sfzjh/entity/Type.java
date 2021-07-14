package com.sfzjh.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/***
 * @Author  孙飞
 * @Date  2021年03月09日 13:12
 * @PackageName  com.sfzjh.entity
 * @Name  Type
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "t_type")
public class Type {
    /**
     * 类型ID
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 类型名称
     */
    @NotBlank(message = "分类名称不能为空！")
    private String name;
    /**
     * uuid
     */
    private String uuid;
    /**
     * 类型所对应的博客
     */
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();
}

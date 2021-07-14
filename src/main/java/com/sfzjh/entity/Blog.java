package com.sfzjh.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 博客详情类
 * @Author  孙飞
 * @Date  2021年03月09日 12:42
 * @PackageName  com.sfzjh.entity
 * @Name  Blog
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "t_blog")
public class Blog {
    /**
     * 博客ID
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 博客标题
     */
    private String title;
    /**
     * 博客内容
     */
    private String content;
    /**
     * 博客首图
     */
    private String firstPicture;
    /**
     * 博客标记：原创或转载
     */
    private String flag;
    /**
     * 博客浏览次数
     */
    private Integer views;
    /**
     * 博客赞赏：true或false
     */
    private boolean appreciation;
    /**
     * 博客转载：true或false
     */
    private boolean shareStatement;
    /**
     * 博客评论：true或false
     */
    private boolean commentEnabled;
    /**
     * 博客发布：true或false
     */
    private boolean published;
    /**
     * 博客推荐：true或false
     */
    private boolean recommend;
    /**
     * 博客创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /**
     * 博客修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    /**
     * 博客所属类型
     */
    @ManyToOne()
    private Type type;
    /**
     * 博客所属标签
     */
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();
    /**
     * 博客所属用户
     */
    @ManyToOne
    private User user;
    /**
     * 博客评论列表
     */
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
    /**
     * 博客所属标签ID
     */
    @Transient
    private String tagIds;
    /**
     * 博客描述
     */
    private String description;
    /**
     * UUID
     */
    private String uuid;

    /**
     * 初始化标签
     * @author  孙飞
     * @date  2021年03月09日 13:04
     * @return  void
     */
    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }
/**
 * 将博客所属标签列表转成对应标签ID
 * @author  孙飞
 * @date  2021年03月09日 13:02
 * @param tags 博客所属标签列表
 * @return  java.lang.String 标签ID字符串
 */
    private String tagsToIds(List<Tag> tags){
        if (!tags.isEmpty()){
            StringBuilder ids = new StringBuilder();
            boolean flag = false;
            for (Tag tag : tags){
                if (flag){
                    ids.append(",");
                }
                else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }
        else {
            return tagIds;
        }
    }

}

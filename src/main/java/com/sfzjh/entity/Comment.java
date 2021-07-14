package com.sfzjh.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 博客评论类
 * @Author  孙飞
 * @Date  2021年03月09日 12:57
 * @PackageName  com.sfzjh.entity
 * @Name  Comment
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "t_comment")
public class Comment {
    /**
     * 评论ID
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 评论者昵称
     */
    private String nickName;
    /**
     * 评论者邮箱
     */
    private String email;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论者头像
     */
    private String avatar;
    /**
     * 评论时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /**
     * 评论的博客
     */
    @ManyToOne
    private Blog blog;
    /**
     * 评论列表
     */
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();
    /**
     * 评论者父级
     */
    @ManyToOne
    private Comment parentComment;
    /**
     * 是否是博主评论：true或false
     */
    private boolean adminComment;

}

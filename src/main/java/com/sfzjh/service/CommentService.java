package com.sfzjh.service;

import com.sfzjh.entity.Comment;

import java.util.List;

/**
 * @Author 孙飞
 * @Date 2021年05月14日  10:26
 * @PackageName com.sfzjh.service
 * @Name CommentService
 * @Version 1.0
 * @Description TODO
 * Created with IntelliJ IDEA.
 */
public interface CommentService {

    /**
     * 根据博客ID查找相关的评论
     *
     * @param blogId 博客ID
     * @return java.util.List<com.sfzjh.entity.Comment>
     * @author 孙飞
     * @date 2021年05月14日  10:27
     */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 保存评论
     *
     * @param comment 评论对象
     * @author 孙飞
     * @date 2021年05月14日  10:27
     */
    void saveComment(Comment comment);
}

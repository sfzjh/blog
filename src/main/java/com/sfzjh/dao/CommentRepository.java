package com.sfzjh.dao;

import com.sfzjh.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 评论接口
 * @Author  孙飞
 * @Date  2021年03月09日 11:59
 * @PackageName  com.sfzjh.dao
 * @Name  CommentRepository
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
public interface CommentRepository extends JpaRepository<Comment,Long>{

    /**
     * 通过博客ID查询评论
     * @author  孙飞
     * @date  2021年03月09日 13:28
     * @param blogId 博客ID
     * @param sort 评论顺序
     * @return  java.util.List<com.sfzjh.entity.Comment>
     */
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}

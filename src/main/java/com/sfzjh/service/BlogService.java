package com.sfzjh.service;

import com.sfzjh.entity.Blog;
import com.sfzjh.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 博客接口
 *
 * @Author 孙飞
 * @Date 2021年03月09日 14:13
 * @PackageName com.sfzjh.service
 * @Name BlogService
 * @Version 1.0
 * @Description TODO
 * Created with IntelliJ IDEA.
 */
public interface BlogService {
    /**
     * 根据博客ID查找博客
     *
     * @param id 博客id
     * @return com.sfzjh.entity.Blog 返回相应的博客
     * @author 孙飞
     * @date 2021年03月09日 14:14
     */
    Blog getBlog(Long id);

    /**
     * 根据博客ID查找博客用于更新数据
     *
     * @param id 博客ID
     * @return com.sfzjh.entity.Blog
     * @author 孙飞
     * @date 2021年03月09日 14:15
     */
    Blog getAndConvert(Long id);

    /**
     * 通过条件查询分页显示博客
     *
     * @param pageable 分页
     * @param blog     博客查询条件
     * @return org.springframework.data.domain.Page<com.sfzjh.entity.Blog>
     * @author 孙飞
     * @date 2021年03月09日 14:15
     */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    /**
     * 分页显示博客
     *
     * @param pageable 分页
     * @return org.springframework.data.domain.Page<com.sfzjh.entity.Blog>
     * @author 孙飞
     * @date 2021年03月09日 14:19
     */
    Page<Blog> listBlog(Pageable pageable);

    /**
     * 根据标签查询博客分页显示
     *
     * @param tagId    标签ID
     * @param pageable 分页
     * @return org.springframework.data.domain.Page<com.sfzjh.entity.Blog>
     * @author 孙飞
     * @date 2021年03月09日 14:20
     */
    Page<Blog> listBlog(Long tagId, Pageable pageable);

    /**
     * 条件查询博客并分页
     *
     * @param query    查询条件
     * @param pageable 分页
     * @return org.springframework.data.domain.Page<com.sfzjh.entity.Blog>
     * @author 孙飞
     * @date 2021年03月09日 14:20
     */
    Page<Blog> listBlog(String query, Pageable pageable);

    /**
     * 显示推荐的博客
     *
     * @param size 推荐博客数量
     * @return java.util.List<com.sfzjh.entity.Blog>
     * @author 孙飞
     * @date 2021年03月09日 14:21
     */
    List<Blog> listRecommendBlogTop(Integer size);

    /**
     * 博客归档
     *
     * @return java.util.Map<java.lang.String, java.util.List < com.sfzjh.entity.Blog>>
     * @author 孙飞
     * @date 2021年03月09日 14:21
     */
    Map<String, List<Blog>> archiveBlog();

    /**
     * 查询博客总数
     *
     * @return java.lang.Long
     * @author 孙飞
     * @date 2021年03月09日 14:23
     */
    Long countBlog();

    /**
     * 保存博客
     *
     * @param blog 博客
     * @return com.sfzjh.entity.Blog
     * @author 孙飞
     * @date 2021年03月09日 14:23
     */
    Blog saveBlog(Blog blog);

    /**
     * 更新博客
     *
     * @param id   博客ID
     * @param blog 博客
     * @return com.sfzjh.entity.Blog
     * @author 孙飞
     * @date 2021年03月09日 14:24
     */
    Blog updateBlog(Long id, Blog blog);

    /**
     * 删除博客
     *
     * @param id 博客ID
     * @return void
     * @author 孙飞
     * @date 2021年03月09日 14:24
     */
    void deleteBlog(Long id);

    /**
     * 根据UUID查找游戏ID
     *
     * @param uuid 游戏UUID
     * @return int
     * @author 孙飞
     * @date 2021年03月10日 15:09
     */
    Long getIdByUuid(String uuid);

}

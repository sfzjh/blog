package com.sfzjh.dao;

import com.sfzjh.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 博客相关接口
 * @Author  孙飞
 * @Date  2021年03月09日 11:59
 * @PackageName  com.sfzjh.dao
 * @Name  BlogRepository
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    /**
     * 查询推荐的博客
     * @author  孙飞
     * @date  2021年03月09日 12:40
     * @param pageable 分页对象
     * @return  java.util.List<com.sfzjh.entity.Blog>
     */
    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    /**
     * 根据查询条件分页查询
     * @author  孙飞
     * @date  2021年03月09日 13:20
     * @param query 查询条件
     * @param pageable 分页对象
     * @return  org.springframework.data.domain.Page<com.sfzjh.entity.Blog>
     */
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    /**
     * 根据博客ID更新博客阅读次数
     * @author  孙飞
     * @date  2021年03月09日 13:22
     * @param id 更新博客的ID
     * @return  int
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    void updateViews(Long id);

    /**
     * 分组显示博客列表
     * @author  孙飞
     * @date  2021年03月09日 13:23
     * @return  java.util.List<java.lang.String>
     */
    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b" +
            ".updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    /**
     * 根据年查询博客列表
     * @author  孙飞
     * @date  2021年03月09日 13:24
     * @param year 年查询条件
     * @return  java.util.List<com.sfzjh.entity.Blog>
     */
    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

    /**
     * 根据UUID查找博客ID
     * @author  孙飞
     * @date  2021年03月10日 14:52
     * @param uuid 博客UUID
     * @return  java.lang.String
     */
    @Query("select b.id from Blog b where b.uuid = ?1")
    Long getIdByUuid(String uuid);
}

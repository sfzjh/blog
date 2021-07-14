package com.sfzjh.dao;
import com.sfzjh.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 标签接口
 * @Author  孙飞
 * @Date  2021年03月09日 13:33
 * @PackageName  com.sfzjh.dao
 * @Name  TagRepository
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    /**
     * 根据标签名称查询标签
     * @author  孙飞
     * @date  2021年03月09日 13:33
     * @param name 标签名称
     * @return  com.sfzjh.entity.Tag
     */
    Tag findByName(String name);

    /**
     * 分页查询标签
     * @author  孙飞
     * @date  2021年03月09日 13:34
     * @param pageable 分页对象
     * @return  java.util.List<com.sfzjh.entity.Tag>
     */
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
    /**
     * 根据UUID查找博客ID
     * @author  孙飞
     * @date  2021年03月10日 14:52
     * @param uuid 博客UUID
     * @return  java.lang.String
     */
    @Query("select t.id from Tag t where t.uuid = ?1")
    Long getIdByUuid(String uuid);
}

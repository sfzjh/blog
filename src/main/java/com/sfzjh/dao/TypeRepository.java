package com.sfzjh.dao;
import com.sfzjh.entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 类型接口
 * @Author  孙飞
 * @Date  2021年03月09日 13:34
 * @PackageName  com.sfzjh.dao
 * @Name  TypeRepository
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
public interface TypeRepository extends JpaRepository<Type, Long> {

    /**
     * 根据类型名称查询
     * @author  孙飞
     * @date  2021年03月09日 13:35
     * @param name 查询名称
     * @return  com.sfzjh.entity.Type
     */
    Type findByName(String name);

    /**
     * 分页查询类型
     * @author  孙飞
     * @date  2021年03月09日 13:35
     * @param pageable 分页对象
     * @return  java.util.List<com.sfzjh.entity.Type>
     */
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
    /**
     * 根据UUID查找博客ID
     * @author  孙飞
     * @date  2021年03月10日 14:52
     * @param uuid 博客UUID
     * @return  java.lang.String
     */
    @Query("select t.id from Type t where t.uuid = ?1")
    Long getIdByUuid(String uuid);
}

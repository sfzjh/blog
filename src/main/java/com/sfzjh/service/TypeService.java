package com.sfzjh.service;
/*
 * @Author:孙飞
 * @Date:2020年04月28  11:02
 * @Version:1.0
 */

import com.sfzjh.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Type updateType(Long id, Type type);

    void deleteType(Long id);
    /**
     * 根据UUID查找游戏ID
     * @author  孙飞
     * @date  2021年03月10日 15:09
     * @param uuid 游戏UUID
     * @return  int
     */
    Long getIdByUuid(String uuid);
}

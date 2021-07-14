package com.sfzjh.service;

/*
 * @Author:孙飞
 * @Date:2020年04月28  11:04
 * @Version:1.0
 */

import com.sfzjh.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    Tag saveTag(Tag tag);
    Tag getTag(Long id);
    Tag getTagByName(String name);
    Page<Tag> listTag(Pageable pageable);
    List<Tag> listTag();
    List<Tag> listTagTop(Integer size);
    List<Tag> listTag(String ids);
    Tag updateTag(Long id, Tag tag);
    void deleteTag(Long id);
    /**
     * 根据UUID查找游戏ID
     * @author  孙飞
     * @date  2021年03月10日 15:09
     * @param uuid 游戏UUID
     * @return  int
     */
    Long getIdByUuid(String uuid);
}

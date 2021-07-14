package com.sfzjh.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 博客查询
 * @Author  孙飞
 * @Date  2021年03月09日 14:18
 * @PackageName  com.sfzjh.vo
 * @Name  BlogQuery
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@NoArgsConstructor
@Setter
@Getter
public class BlogQuery {
    /**
     * 博客标题
     */
    private String title;
    /**
     * 类型ID
     */
    private Long typeId;
    /**
     * 是否推荐
     */
    private boolean recommend;

}

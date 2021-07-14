package com.sfzjh.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * 游戏类
 * @Author  孙飞
 * @Date  2021年03月09日 13:08
 * @PackageName  com.sfzjh.entity
 * @Name  Game
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "t_game")
public class Game {
    /**
     * 游戏ID
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 游戏名称
     */
    private String name;
    /**
     * 总玩次数
     */
    private Integer playCounts;
    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    /**
     * 游戏UUID
     */
    private String uuid;
}

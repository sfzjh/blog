package com.sfzjh.dao;

import com.sfzjh.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * 游戏接口
 * @Author  孙飞
 * @Date  2021年03月09日 13:31
 * @PackageName  com.sfzjh.dao
 * @Name  GameRepository
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
public interface GameRepository extends JpaRepository<Game, Long> {

    /**
     * 根据游戏ID更新点玩次数
     * @author  孙飞
     * @date  2021年03月09日 13:31
     * @param id 游戏ID
     * @return  int
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update Game g set g.playCounts = g.playCounts+1 where g.id = ?1")
    void updateGameById(Long id);

    /**
     * 根据UUID查找游戏ID
     * @author  孙飞
     * @date  2021年03月10日 14:52
     * @param uuid 游戏UUID
     * @return  java.lang.String
     */
    @Query("select g.id from Game g where g.uuid = ?1")
    Long getIdByUuid(String uuid);

}

package com.sfzjh.service;


import com.sfzjh.entity.Game;

/**
 * @Author 孙飞
 * @Date 2021年05月14日  10:26
 * @PackageName com.sfzjh.service
 * @Name GameService
 * @Version 1.0
 * @Description TODO
 * Created with IntelliJ IDEA.
 */


public interface GameService {
    /**
     * 根据游戏ID查找游戏
     *
     * @param id 游戏ID
     * @return com.sfzjh.entity.Game
     * @author 孙飞
     * @date 2021年03月10日 10:18
     */
    Game getGameById(Long id);

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

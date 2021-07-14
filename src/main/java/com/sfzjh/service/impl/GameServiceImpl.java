package com.sfzjh.service.impl;

import com.sfzjh.dao.GameRepository;
import com.sfzjh.entity.Game;
import com.sfzjh.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : 孙飞
 * @Date : 2020年09月14日  20:39
 * @Version : 1.0
 * @Description :
 */

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;


    @Override
    public Game getGameById(Long id) {
        Game game = gameRepository.findById(id).get();
        gameRepository.updateGameById(id);
        return game;
    }

    @Override
    public Long getIdByUuid(String uuid) {
        return gameRepository.getIdByUuid(uuid);
    }

}

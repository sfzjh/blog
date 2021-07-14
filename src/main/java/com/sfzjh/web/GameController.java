package com.sfzjh.web;

import com.sfzjh.entity.Game;
import com.sfzjh.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author  孙飞
 * @Date  2021年03月09日 14:31
 * @PackageName  com.sfzjh.web
 * @Name  GameController
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@Controller
public class GameController {

    @Autowired

    private GameService gameService;
    @GetMapping("/games")
    public void getGames(Model model){
        String uuid = "adc314e3060a43a29defa644449ba820";
        gamePlayCounts(uuid, model);
    }

    @GetMapping("/games/{uuid}")
    public String gamePlayCounts(@PathVariable String  uuid, Model model){
        Long id = gameService.getIdByUuid(uuid);
        Game game = gameService.getGameById(id);
        model.addAttribute("game",game);
        return "games";
    }
}

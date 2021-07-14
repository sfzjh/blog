package com.sfzjh.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author  孙飞
 * @Date  2021年03月09日 14:30
 * @PackageName  com.sfzjh.web
 * @Name  AboutController
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}

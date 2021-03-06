package com.sfzjh.web;

import com.sfzjh.service.BlogService;
import com.sfzjh.service.TagService;
import com.sfzjh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author  孙飞
 * @Date  2021年03月09日 14:31
 * @PackageName  com.sfzjh.web
 * @Name  IndexController
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "index";
    }
    @PostMapping("/search")
    public String search(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC)
                         Pageable pageable, @RequestParam String query, Model model){
        model.addAttribute("page", blogService.listBlog("%" + query + "%", pageable));
        model.addAttribute("query", query);
        return "search";
    }
    @GetMapping("/blogDetail/{uuid}")
    public String blog(@PathVariable String uuid, Model model){
        Long id = blogService.getIdByUuid(uuid);
        model.addAttribute("blog" ,blogService.getAndConvert(id));
        return "blog";
    }
    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }

}

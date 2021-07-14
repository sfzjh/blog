package com.sfzjh.web;

import com.sfzjh.entity.Tag;
import com.sfzjh.service.BlogService;
import com.sfzjh.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author  孙飞
 * @Date  2021年03月09日 14:31
 * @PackageName  com.sfzjh.web
 * @Name  TagShowController
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;


    @GetMapping("/tags")
    public void getTypes(Pageable pageable, Model model){
        String uuid = "eb424cfcbcfd41269e7358da315563d7";
        tags(uuid, pageable,model);
    }
    @GetMapping("/tags/{uuid}")
    public String tags(@PageableDefault(size = 5, sort = {"updateTime"},direction = Sort.Direction.DESC)
                        @PathVariable String uuid, Pageable pageable, Model model){
        Long id;
        String uuidDefault = "eb424cfcbcfd41269e7358da315563d7";
        List<Tag> tags = tagService.listTagTop(100);
        if (uuid.equals(uuidDefault)){
            id = tags.get(0).getId();
        }else {
            id = tagService.getIdByUuid(uuid);
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(id, pageable));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}

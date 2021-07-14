package com.sfzjh.web;

import com.sfzjh.entity.Type;
import com.sfzjh.service.BlogService;
import com.sfzjh.service.TypeService;
import com.sfzjh.vo.BlogQuery;
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
 * @Name  TypeShowController
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types")
    public void getTypes(Pageable pageable, Model model){
        String uuid = "2c9c163e6d3847418a0c5da40777f9ff";
        types(uuid, pageable,model);
    }

    @GetMapping("/types/{uuid}")
    public String types(@PageableDefault(size = 5, sort = {"updateTime"},direction = Sort.Direction.DESC)
                        @PathVariable String uuid, Pageable pageable, Model model){
        String uuidDefault = "2c9c163e6d3847418a0c5da40777f9ff";
        List<Type> types = typeService.listTypeTop(100);
        Long id;
        if (uuid.equalsIgnoreCase(uuidDefault)){
            id = types.get(0).getId();
        }else {
            id = typeService.getIdByUuid(uuid);
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlog(pageable,blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}

package com.sfzjh.web.admin;

import com.sfzjh.entity.Tag;
import com.sfzjh.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Author  孙飞
 * @Date  2021年03月09日 14:33
 * @PackageName  com.sfzjh.web.admin
 * @Name  TagController
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.DESC)
            Pageable pageable, Model model){
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tagInput = tagService.getTagByName(tag.getName());
        if (tagInput != null){
            result.rejectValue("name", "nameError","该标签已经存在！");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag saveTag = tagService.saveTag(tag);
        if (saveTag == null){
            attributes.addFlashAttribute("message", "添加失败！");
        }
        else {
            attributes.addFlashAttribute("message", "添加成功！");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Tag tagInput = tagService.getTagByName(tag.getName());
        if (tagInput != null) {
            result.rejectValue("name","nameError","该标签已经存在！");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag saveTag = tagService.updateTag(id,tag);
        if (saveTag == null ) {
            attributes.addFlashAttribute("message", "更新失败！");
        } else {
            attributes.addFlashAttribute("message", "更新成功！");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功！");
        return "redirect:/admin/tags";
    }

}

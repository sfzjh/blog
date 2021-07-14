package com.sfzjh.web.admin;



import com.sfzjh.entity.Type;
import com.sfzjh.service.TypeService;
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
 * @Name  TypeController
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;
    @GetMapping("/types")
    public String types(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input( Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String updateTypes(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult bindingResult, RedirectAttributes attributes){
        Type typeName = typeService.getTypeByName(type.getName());
        if (typeName != null){
            bindingResult.rejectValue("name","nameError","该分类名称已经存在！");
        }
        if (bindingResult.hasErrors()){
            return "admin/types-input";
        }
        Type result = typeService.saveType(type);
        if (result == null){
            attributes.addFlashAttribute("message","添加失败！");
        }
        else {
            attributes.addFlashAttribute("message", "添加成功！");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult bindingResult, @PathVariable Long id, RedirectAttributes attributes){
        Type typeName = typeService.getTypeByName(type.getName());
        if (typeName != null){
            bindingResult.rejectValue("name","nameError","该分类名称已经存在！");
        }
        if (bindingResult.hasErrors()){
            return "admin/types-input";
        }
        Type result = typeService.updateType(id, type);
        if (result == null){
            attributes.addFlashAttribute("message","更新失败！");
        }
        else {
            attributes.addFlashAttribute("message", "更新成功！");
        }
        return "redirect:/admin/types";
    }


    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功！");
        return "redirect:/admin/types";
    }

}

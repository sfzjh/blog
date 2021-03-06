package com.sfzjh.web.admin;


import com.sfzjh.entity.Blog;
import com.sfzjh.entity.User;
import com.sfzjh.service.BlogService;
import com.sfzjh.service.TagService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author  孙飞
 * @Date  2021年03月09日 14:31
 * @PackageName  com.sfzjh.web.admin
 * @Name  BlogController
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";


    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog blogSave;
        if (blog.getId() == null){
            blogSave = blogService.saveBlog(blog);
        }
        else {
            blogSave = blogService.updateBlog(blog.getId(), blog);
        }
        if (blogSave == null){
            attributes.addFlashAttribute("message","操作失败！");
        }
        else {
            attributes.addFlashAttribute("message", "操作成功！");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功！");
        return REDIRECT_LIST;
    }

//    @GetMapping("/admin/footer/newblog")
//    public String newblogs(Model model) {
//        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
//        return "admin/_fragments :: newblogList";
//    }
}

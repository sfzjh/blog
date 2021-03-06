package com.sfzjh.service.impl;

import com.sfzjh.NotFoundException;
import com.sfzjh.dao.BlogRepository;
import com.sfzjh.entity.Blog;
import com.sfzjh.entity.Type;
import com.sfzjh.service.BlogService;
import com.sfzjh.util.MarkdownUtils;
import com.sfzjh.util.MyBeanUtils;
import com.sfzjh.util.UUIDUtils;
import com.sfzjh.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * 博客实现类
 * @Author  孙飞
 * @Date  2021年03月09日 14:25
 * @PackageName  com.sfzjh.service.impl
 * @Name  BlogServiceImpl
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findById(id).get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.findById(id).get();
        if (blog == null){
            throw new NotFoundException("该博客不存在");
        }
        Blog blogSave = new Blog();
        BeanUtils.copyProperties(blog, blogSave);
        String content = blogSave.getContent();
        blogSave.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogRepository.updateViews(id);
        return blogSave;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {

        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    predicates.add(criteriaBuilder.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }
                if (blog.getTypeId() != null){
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join join = root.join("tags");
                return criteriaBuilder.equal(join.get("id"), tagId);
            }
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query, pageable);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogRepository.findTop(pageable);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>(0);
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            blog.setUuid(UUIDUtils.getUUID());
        }
        else {
            blog.setUpdateTime(new Date());
        }

        return blogRepository.save(blog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Blog updateBlog(Long id, Blog blog) {
        Blog blogName = blogRepository.findById(id).get();
        if (blogName == null){
            throw  new NotFoundException("该博客不存在！");
        }
        BeanUtils.copyProperties(blog, blogName, MyBeanUtils.getNullPropertyNames(blog));
        blogName.setUpdateTime(new Date());
        return blogRepository.save(blogName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Long getIdByUuid(String uuid) {
        return blogRepository.getIdByUuid(uuid);
    }
}

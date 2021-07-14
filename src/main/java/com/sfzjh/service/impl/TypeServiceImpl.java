package com.sfzjh.service.impl;
/*
 * @Author:孙飞
 * @Date:2020年04月28  11:04
 * @Version:1.0
 */

import com.sfzjh.NotFoundException;
import com.sfzjh.dao.TypeRepository;
import com.sfzjh.entity.Type;
import com.sfzjh.service.TypeService;
import com.sfzjh.util.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    @Transactional
    public Type saveType(Type type) {
        type.setUuid(UUIDUtils.getUUID());
        return typeRepository.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.findById(id).get();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size,sort);
        return typeRepository.findTop(pageable);
    }

    @Override
    @Transactional
    public Type updateType(Long id, Type type) {
        Type typeOne = typeRepository.getOne(id);
        if (typeOne == null){
            throw new NotFoundException("不存在该类型");
        }
        type.setUuid(typeOne.getUuid());
        BeanUtils.copyProperties(type,typeOne);
        return typeRepository.save(typeOne);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public Long getIdByUuid(String uuid) {
        return typeRepository.getIdByUuid(uuid);
    }
}

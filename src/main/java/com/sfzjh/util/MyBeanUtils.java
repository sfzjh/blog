package com.sfzjh.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author  孙飞
 * @Date  2021年03月09日 14:29
 * @PackageName  com.sfzjh.util
 * @Name  MyBeanUtils
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
public class MyBeanUtils {


    /**
     * 获取所有的属性值为空属性名数组
     * @param source 数据源
     * @return 字符串数组
     */
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds =  beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor pd : pds) {
            String propertyName = pd.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null) {
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }

}

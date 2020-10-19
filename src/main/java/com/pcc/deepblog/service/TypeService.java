package com.pcc.deepblog.service;

import com.pcc.deepblog.entity.Type;

import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-13 16:35
 **/
public interface TypeService {
    List<Type> getAllType();

    Type getType(Long id);

    Type getTypeByName(String name);

    int saveType(Type type);

    int updateType(Type type);

    int deleteType(Long id);

    List<Type> getAllTypeAndBlog();
}

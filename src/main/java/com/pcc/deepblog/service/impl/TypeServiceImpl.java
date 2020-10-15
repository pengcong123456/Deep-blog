package com.pcc.deepblog.service.impl;

import com.pcc.deepblog.dao.TypeDao;
import com.pcc.deepblog.entity.Type;
import com.pcc.deepblog.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-13 16:40
 **/
@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    TypeDao typeDao;
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }
}

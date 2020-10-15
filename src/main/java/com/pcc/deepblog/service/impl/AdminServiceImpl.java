package com.pcc.deepblog.service.impl;

import com.pcc.deepblog.MyException.RunException;
import com.pcc.deepblog.dao.UserDao;
import com.pcc.deepblog.entity.User;
import com.pcc.deepblog.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : 彭聪
 * @date : 2020-10-12 14:03
 **/
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    UserDao userDao;

    @Override
    public User loginback(String username, String password) throws RunException {

        User user = userDao.findByUsernameAndPassword(username);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                return user;
            } else {
                throw new RunException("用户密码错误！");
            }
        } else {
            throw new RunException("用户不存在！");
        }

    }
}

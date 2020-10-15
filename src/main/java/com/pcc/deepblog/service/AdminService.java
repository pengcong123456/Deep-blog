package com.pcc.deepblog.service;

import com.pcc.deepblog.MyException.RunException;
import com.pcc.deepblog.entity.User;

public interface AdminService {
    User loginback(String username,String password) throws RunException;
}

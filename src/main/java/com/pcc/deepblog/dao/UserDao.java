package com.pcc.deepblog.dao;

import com.pcc.deepblog.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserDao {
    User findByUsernameAndPassword(@Param("username") String username);
}
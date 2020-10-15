package com.pcc.deepblog.service.impl;

import com.pcc.deepblog.dao.FriendLinkDao;
import com.pcc.deepblog.entity.FriendLink;
import com.pcc.deepblog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-14 21:36
 **/
@Service
public class FriendLinkServiceImpl implements FriendLinkService {
    @Autowired
    FriendLinkDao friendLinkDao;

    @Override
    public List<FriendLink> listFriendLink() {
        return friendLinkDao.listFriendLink();
    }

    //通过地址查找友链
    @Override
    public FriendLink getFriendLinkByBlogaddress(String blogaddress) {
        return friendLinkDao.getFriendLinkByBlogaddress(blogaddress);
    }

    @Override
    public int saveFriendLink(FriendLink friendLink) {
        return friendLinkDao.saveFriendLink(friendLink);
    }

    //通过id获取友链
    @Override
    public FriendLink getFriendLink(Long id) {
        return friendLinkDao.getFriendLink(id);
    }

    @Override
    public int updateFriendLink(FriendLink friendLink) {
        return friendLinkDao.updateFriendLink(friendLink);
    }

    @Override
    public int deleteFriendLink(Long id) {
        return friendLinkDao.deleteFriendLink(id);
    }
}

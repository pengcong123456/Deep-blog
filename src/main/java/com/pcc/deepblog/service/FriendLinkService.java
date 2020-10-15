package com.pcc.deepblog.service;

import com.pcc.deepblog.entity.FriendLink;

import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-14 21:33
 **/
public interface FriendLinkService {
    List<FriendLink> listFriendLink();

    FriendLink getFriendLinkByBlogaddress(String blogaddress);

    int saveFriendLink(FriendLink friendLink);

    FriendLink getFriendLink(Long id);

    int updateFriendLink(FriendLink friendLink);

    int deleteFriendLink(Long id);

}

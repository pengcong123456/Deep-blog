package com.pcc.deepblog.service;

import com.pcc.deepblog.entity.Picture;

import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-15 21:02
 **/
public interface PictureService {

    //查询照片
    List<Picture> listPicture();

    //添加图片
    int savePicture(Picture picture);

    //根据id查询照片
    Picture getPicture(Long id);

    //    编辑修改相册
    int updatePicture(Picture picture);

    //    删除照片
    int deletePicture(Long id);

}

package com.pcc.deepblog.service.impl;

import com.pcc.deepblog.dao.PictureDao;
import com.pcc.deepblog.entity.Picture;
import com.pcc.deepblog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-10-15 21:03
 **/
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDao pictureDao;

    @Override
    public List<Picture> listPicture() {
        return pictureDao.listPicture();
    }

    @Override
    public int savePicture(Picture picture) {
        return pictureDao.savePicture(picture);
    }

    @Override
    public Picture getPicture(Long id) {
        return pictureDao.getPicture(id);
    }

    @Override
    public int updatePicture(Picture picture) {
        return pictureDao.updatePicture(picture);
    }

    @Override
    public int deletePicture(Long id) {
        return pictureDao.deletePicture(id);
    }

}
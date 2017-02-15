package com.imp.lf.service.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.imp.lf.dao.ImageMapper;
import com.imp.lf.entities.Image;
import com.imp.lf.service.ImageService;
import org.springframework.stereotype.Service;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/21
 */
@Service
public class ImageServiceImpl extends SuperServiceImpl<ImageMapper, Image> implements ImageService {


    @Override
    public Long insertImage(Image image) {
        baseMapper.insert(image);
        return image.getId();
    }
}

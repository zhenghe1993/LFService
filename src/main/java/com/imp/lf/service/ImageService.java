package com.imp.lf.service;

import com.baomidou.framework.service.ISuperService;
import com.imp.lf.entities.Image;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/21
 */
public interface ImageService extends ISuperService<Image> {

    public Long insertImage(Image image);
}

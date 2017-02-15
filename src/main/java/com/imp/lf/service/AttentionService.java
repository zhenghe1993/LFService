package com.imp.lf.service;

import com.baomidou.framework.service.ISuperService;
import com.imp.lf.entities.Attention;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/22
 */
public interface AttentionService extends ISuperService<Attention> {

   public void addAttention(Attention attention);
   public Attention findAttention(Long dataId,Long userId,String type);
}

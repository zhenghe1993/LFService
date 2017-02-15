package com.imp.lf.dao;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.imp.lf.entities.Attention;


/**
 * Created by (IMP)郑和明
 * Date is 2017/1/19
 */
public interface AttentionMapper extends AutoMapper<Attention>{

   public Attention findAttention(Long dataId,Long userId,String type);

}

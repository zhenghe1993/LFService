package com.imp.lf.service.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.imp.lf.dao.AttentionMapper;
import com.imp.lf.entities.Attention;
import com.imp.lf.service.AttentionService;
import com.imp.lf.service.DiscoverService;
import com.imp.lf.service.LFMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/22
 */
@Service
public class AttentionServiceImpl extends SuperServiceImpl<AttentionMapper, Attention> implements AttentionService {
    @Autowired
    private LFMessageService lfMessageService;

    @Autowired
    private DiscoverService discoverService;

    @Override
    public void addAttention(Attention attention) {

        baseMapper.insert(attention);
        if (attention.getType().equals("LF"))
            lfMessageService.addLFMessageAttentionCount(attention.getDataId());
        else
            discoverService.addDiscoverCommentsCount(attention.getDataId());


    }

    @Override
    public Attention findAttention(Long dataId, Long userId, String type) {
        return baseMapper.findAttention(dataId, userId, type);
    }
}

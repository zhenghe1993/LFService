package com.imp.lf.service.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.imp.lf.dao.LFMessageMapper;
import com.imp.lf.entities.LFMessage;
import com.imp.lf.service.LFMessageService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/19
 */
@Configuration
@PropertySource("classpath:pageConfig.properties")
@Service
public class LFMessageServiceImpl extends SuperServiceImpl<LFMessageMapper, LFMessage> implements LFMessageService {


    @Value("${page.size:5}")
    private Integer pageSize;

    @Override
    public List<LFMessage> findLFMessageList(Long id, String type, String kind, String location, int currentPage) {

        return baseMapper.findLFMessageList(id, type, kind, location, currentPage * pageSize, pageSize);
    }


    @Override
    public void insertLFMessage(LFMessage lfMessage) {
         baseMapper.insertMessage(lfMessage);
    }

    @Override
    public void deleteLFMessageById(Long id) {
        baseMapper.deleteLFMessageById(id);
    }

    @Override
    public void addLFMessageShareCount(Long id) {
        baseMapper.addLFMessageShareCount(id);
    }

    @Override
    public void addLFMessageCommentsCount(Long id) {
        baseMapper.addLFMessageCommentsCount(id);
    }

    @Override
    public void minusLFMessageCommentsCount(Long id) {
        baseMapper.minusLFMessageCommentsCount(id);
    }

    @Override
    public void addLFMessageAttentionCount(Long id) {
        baseMapper.addLFMessageAttentionCount(id);
    }


}

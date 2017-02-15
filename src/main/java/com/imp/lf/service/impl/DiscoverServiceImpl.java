package com.imp.lf.service.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.imp.lf.dao.DiscoverMapper;
import com.imp.lf.entities.Discover;
import com.imp.lf.service.DiscoverService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/22
 */
@Configuration
@PropertySource("classpath:pageConfig.properties")
@Service
public class DiscoverServiceImpl extends SuperServiceImpl<DiscoverMapper, Discover> implements DiscoverService {

    @Value("${page.size:5}")
    private Integer pageSize;
    @Override
    public Long insertDiscover(Discover discover) {
        return baseMapper.insertDiscover(discover);
    }

    @Override
    public List<Discover> findDiscoverList(Long id, int currentPage) {
        return baseMapper.findDiscoverList(id, currentPage*pageSize, pageSize);
    }

    @Override
    public void deleteDiscoverById(Long id) {
        baseMapper.deleteDiscoverById(id);
    }

    @Override
    public void addDiscoverShareCount(Long id) {
        baseMapper.addDiscoverShareCount(id);
    }

    @Override
    public void addDiscoverCommentsCount(Long id) {
        baseMapper.addDiscoverCommentsCount(id);
    }

    @Override
    public void minusDiscoverCommentsCount(Long id) {
        baseMapper.minusDiscoverCommentsCount(id);
    }

    @Override
    public void addDiscoverAttentionCount(Long id) {
        baseMapper.addDiscoverAttentionCount(id);
    }
}

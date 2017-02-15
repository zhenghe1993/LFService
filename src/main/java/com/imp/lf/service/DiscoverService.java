package com.imp.lf.service;

import com.baomidou.framework.service.ISuperService;
import com.imp.lf.entities.Discover;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/22
 */
public interface DiscoverService extends ISuperService<Discover> {


    Long insertDiscover(Discover discover);

    List<Discover> findDiscoverList(Long id, int currentPage);

    void deleteDiscoverById(Long id);

    void addDiscoverShareCount(Long id);

    void addDiscoverCommentsCount(Long id);

    void minusDiscoverCommentsCount(Long id);


    void addDiscoverAttentionCount(Long id);

}

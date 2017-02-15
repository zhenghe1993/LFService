package com.imp.lf.dao;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.imp.lf.entities.Discover;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/22
 */
public interface DiscoverMapper  extends AutoMapper<Discover> {

    Long insertDiscover(Discover discover);

    List<Discover> findDiscoverList(@Param("id") Long id,@Param("currentPage") int currentPage, @Param("size") int size);

    void deleteDiscoverById(@Param("id") Long id);

    void addDiscoverShareCount(@Param("id") Long id);

    void addDiscoverCommentsCount(@Param("id") Long id);

    void minusDiscoverCommentsCount(@Param("id") Long id);


    void addDiscoverAttentionCount(@Param("id") Long id);

}

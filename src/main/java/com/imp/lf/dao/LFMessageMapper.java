package com.imp.lf.dao;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.imp.lf.entities.LFMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/19
 */
public interface LFMessageMapper extends AutoMapper<LFMessage> {


    void insertMessage(LFMessage lfMessage);

    List<LFMessage> findLFMessageList(@Param("id") Long id, @Param("type") String type, @Param("kind") String kind, @Param("location") String location, @Param("currentPage") int currentPage, @Param("size") int size);

    void deleteLFMessageById(@Param("id") Long id);

    void addLFMessageShareCount(@Param("id") Long id);

    void addLFMessageCommentsCount(@Param("id") Long id);

    void addLFMessageAttentionCount(@Param("id") Long id);


    void minusLFMessageCommentsCount(@Param("id") Long id);
}

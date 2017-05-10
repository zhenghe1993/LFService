package com.imp.lf.service;

import com.baomidou.framework.service.ISuperService;

import com.imp.lf.entities.LFMessage;

import java.util.List;


/**
 * Created by (IMP)郑和明
 * Date is 2017/1/19
 * <p>
 * 获取所有，个人（id）,失物/招领，分类（），地点，分页
 * <p>
 * 软删除
 * <p>
 * 添加
 */
public interface LFMessageService extends ISuperService<LFMessage> {

    public List<LFMessage> findLFMessageList(Long id, String type, String kind, String location, int currentPage);

    public void insertLFMessage(LFMessage lfMessage);

    public void deleteLFMessageById(Long id);

    public void addLFMessageShareCount(Long id);

    public void addLFMessageCommentsCount(Long id);

    public void minusLFMessageCommentsCount(Long id);

    public void addLFMessageAttentionCount(Long id);
}

package com.imp.lf.service;

import com.baomidou.framework.service.ISuperService;
import com.imp.lf.entities.Words;

import java.util.List;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/22
 * <p>
 * 添加
 * 软删除
 */
public interface WordsService extends ISuperService<Words> {

    void deleteWordsById(Long id);

    void insertWords(Words words);

    List<Words> findWordsById(Long id);
}

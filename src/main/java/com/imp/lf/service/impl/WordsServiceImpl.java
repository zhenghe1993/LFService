package com.imp.lf.service.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.imp.lf.dao.WordsMapper;
import com.imp.lf.entities.Words;
import com.imp.lf.service.DiscoverService;
import com.imp.lf.service.LFMessageService;
import com.imp.lf.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/22
 */
@Service
public class WordsServiceImpl extends SuperServiceImpl<WordsMapper, Words> implements WordsService {

    @Autowired
    private LFMessageService lfMessageService;

    @Autowired
    private DiscoverService discoverService;

    @Override
    public void deleteWordsById(Long id) {

        baseMapper.deleteWordsById(id);

        Words words = baseMapper.selectById(id);
        if (words.getType().equals("LF"))
            lfMessageService.minusLFMessageCommentsCount(words.getDataId());
        else
            discoverService.minusDiscoverCommentsCount(words.getDataId());
    }

    @Override
    public void insertWords(Words words) {

        baseMapper.insert(words);

        if (words.getType().equals("LF"))
            lfMessageService.addLFMessageCommentsCount(words.getDataId());
        else
            discoverService.addDiscoverCommentsCount(words.getDataId());
    }

    @Override
    public List<Words> findWordsById(Long id) {
        return baseMapper.findWordsById(id);
    }


}

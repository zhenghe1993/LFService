package com.imp.lf.controller;

import com.alibaba.fastjson.JSON;
import com.imp.lf.entities.Words;
import com.imp.lf.service.UserService;
import com.imp.lf.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by (IMP)郑和明
 * Date is 2017/2/2
 * <p>
 * 评论或留言
 */
@Controller
public class WordsController {

    @Autowired
    private UserService userService;

    @Autowired
    private WordsService wordsService;

    //添加
    @ResponseBody
    @RequestMapping(value = "/words/addWords", method = RequestMethod.POST)
    public String addWords(Words words,
                           String token) {
        if (words == null || token == null) {
            return "Incomplete";
        }

        wordsService.insertWords(words);

        return userService.updateTokenById(words.getFromUserId());
    }


    //删除
    @ResponseBody
    @RequestMapping(value = "/words/deleteWords", method = RequestMethod.POST)
    public String deleteWords(Long id,
                              String token) {
        if (id == null || token == null) {
            return "Incomplete";
        }
        Words words = wordsService.selectById(id);
        if (words == null) {
            return "error";
        }
        wordsService.deleteWordsById(id);

        return userService.updateTokenById(words.getFromUserId());
    }

    //查找留言

    @ResponseBody
    @RequestMapping(value = "/words/findWords", method = RequestMethod.POST)
    public String findWords(Long id,
                            String token) {
        if (id == null || token == null) {
            return "Incomplete";
        }
        List<Words> words = wordsService.findWordsById(id);
        if (words == null) {
            return "error";
        }

        return JSON.toJSONString(words);
    }


}

package com.imp.lf.controller;

import com.imp.lf.dto.ResultTemplate;
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
@RequestMapping(produces = "application/json")
@Controller
public class WordsController {

    @Autowired
    private UserService userService;

    @Autowired
    private WordsService wordsService;

    //添加
    @ResponseBody
    @RequestMapping(value = "/words/addWords", method = RequestMethod.POST)
    public Object addWords(Words words,
                           String token) {
        if (words == null || token == null) {
            return new ResultTemplate("参数缺失",ResultTemplate.PARAMETER_LOST);
        }

        wordsService.insertWords(words);

        return new ResultTemplate(userService.updateTokenById(words.getFromUserId()),ResultTemplate.SUCCESS);
    }


    //删除
    @ResponseBody
    @RequestMapping(value = "/words/deleteWords", method = RequestMethod.POST)
    public Object deleteWords(Long id,
                              String token) {
        if (id == null || token == null) {
            return new ResultTemplate("参数缺失",ResultTemplate.PARAMETER_LOST);
        }
        Words words = wordsService.selectById(id);
        if (words == null) {
            return new ResultTemplate("无此评论",ResultTemplate.WORDS_ERROR);
        }
        wordsService.deleteWordsById(id);


        return new ResultTemplate(userService.updateTokenById(words.getFromUserId()),ResultTemplate.SUCCESS);
    }

    //查找

    @ResponseBody
    @RequestMapping(value = "/words/findWords", method = RequestMethod.POST)
    public Object findWords(Long id,
                            String token) {
        if (id == null || token == null) {
            return new ResultTemplate("参数缺失",ResultTemplate.PARAMETER_LOST);
        }
        List<Words> words = wordsService.findWordsById(id);
        if (words == null) {
            return new ResultTemplate("无此评论",ResultTemplate.WORDS_ERROR);
        }

        return new ResultTemplate(words,ResultTemplate.SUCCESS);
    }


}

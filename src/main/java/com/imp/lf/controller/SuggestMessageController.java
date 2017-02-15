package com.imp.lf.controller;

import com.imp.lf.entities.SuggestMessage;
import com.imp.lf.service.SuggestMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/24
 */
@Controller
public class SuggestMessageController {

    @Autowired
    private SuggestMessageService suggestMessageService;

    @ResponseBody
    @RequestMapping(value = "/addSuggestMessage", method = RequestMethod.POST)
    public String addSuggestMessage(String content, String contact) {

        if (contact == null || content == null) {
            return "Incomplete";
        }
        SuggestMessage suggestMessage = new SuggestMessage();
        suggestMessage.setCreateTime(new Date());
        suggestMessage.setContent(content);
        suggestMessage.setContact(contact);
        boolean flag = suggestMessageService.insert(suggestMessage);
        return ""+flag;
    }
}

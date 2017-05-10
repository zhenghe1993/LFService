package com.imp.lf.controller;

import com.imp.lf.dto.ResultTemplate;
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
@RequestMapping(produces = "application/json")
@Controller
public class SuggestMessageController {

    @Autowired
    private SuggestMessageService suggestMessageService;

    @ResponseBody
    @RequestMapping(value = "/addSuggestMessage", method = RequestMethod.POST)
    public Object addSuggestMessage(String content, String contact) {

        if (contact == null || content == null) {
            return new ResultTemplate("参数缺失",ResultTemplate.PARAMETER_LOST);
        }
        SuggestMessage suggestMessage = new SuggestMessage();
        suggestMessage.setCreateTime(new Date());
        suggestMessage.setContent(content);
        suggestMessage.setContact(contact);
        boolean res = suggestMessageService.insert(suggestMessage);

        if(!res){
            return new ResultTemplate("意见反馈失败",ResultTemplate.SQL_ERROR);
        }

        return new ResultTemplate("意见反馈成功",ResultTemplate.SUCCESS);
    }
}

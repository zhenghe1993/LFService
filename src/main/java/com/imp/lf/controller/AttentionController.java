package com.imp.lf.controller;

import com.imp.lf.dto.ResultTemplate;
import com.imp.lf.entities.Attention;
import com.imp.lf.service.AttentionService;
import com.imp.lf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/24
 * <p>
 * 关注 方法
 * <p>
 * 添加关注
 * <p>
 * 查找关注(是否存在,根据user_id and data_id)
 * <p>
 * 查找关注数量（根据data_id）
 */
@RequestMapping(value = "/attention",produces = "application/json")
@Controller
public class AttentionController {

    @Autowired
    private AttentionService attentionService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/addAttention", method = RequestMethod.POST)
    public Object addAttention(Long dataId, Long userId, String type) {

        if (dataId == 0 || userId == 0) {
            return new ResultTemplate("参数缺失",ResultTemplate.PARAMETER_LOST);
        }
        Attention attention = new Attention();
        attention.setUserId(userId);
        attention.setDataId(dataId);
        attention.setType(type);
        attentionService.addAttention(attention);

        return new ResultTemplate(userService.updateTokenById(userId),ResultTemplate.SUCCESS);
    }

    @ResponseBody
    @RequestMapping(value = "/findAttention", method = RequestMethod.GET)
    public Object findAttention(Long dataId, Long userId, String type) {

        if (dataId == 0 || userId == 0 || type .trim().isEmpty()) {
            return new ResultTemplate("参数缺失",ResultTemplate.PARAMETER_LOST);
        }
        Attention attention = attentionService.findAttention(dataId, userId, type);
        if (attention == null) {
            return new ResultTemplate("无此关注",ResultTemplate.ATTENTION_ERROR);
        }

        return new ResultTemplate(attention,ResultTemplate.SUCCESS);
    }

}

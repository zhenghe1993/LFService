package com.imp.lf.controller;

import com.imp.lf.util.SmsWebApiKit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * Created by (IMP)郑和明
 * Date is 2017/2/10
 */
@Controller
public class SMSController {

    @RequestMapping(value = "/sms/sendSMS", method = RequestMethod.GET)
    public void sendSMS(String phoneNumber) throws Exception {
        SmsWebApiKit smsWebApiKit = new SmsWebApiKit("1b3738590413e");
        smsWebApiKit.sendMsg(phoneNumber, "86");
    }

    @RequestMapping(value = "/sms/checkSMS", method = RequestMethod.GET)
    public void checkSMS(String phoneNumber, String code) throws Exception {
        SmsWebApiKit smsWebApiKit = new SmsWebApiKit("1b3738590413e");
        smsWebApiKit.checkcode(phoneNumber, "86", code);
    }
}

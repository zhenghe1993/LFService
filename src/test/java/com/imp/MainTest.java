package com.imp;

import com.imp.lf.entities.LFMessage;
import com.imp.lf.service.LFMessageService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * Created by (IMP)郑和明
 * Date is 2017/1/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext.xml"})
public class MainTest {

    @Autowired
    private LFMessageService lfMessageService;

    @Test
    public void testEncrypt() {
        List<LFMessage> lfMessages = lfMessageService.findLFMessageList(9L, null, null, null, 0);

        System.out.println(lfMessages);
    }
}

package com.imp;


import org.junit.Test;

/**
 * Created by (IMP)郑和明
 * Date is 2017/2/8
 */
public class CommonTest {

    @Test
    public void testCommon() {

        StringBuilder builder = new StringBuilder("18844188674");
        builder.reverse();
        for (int i = 4, len = builder.length(); i < len; i += 5) {
            builder.insert(i, ' ');
        }
        builder.reverse();
        String res = builder.toString();
        System.out.println(res);
    }
}

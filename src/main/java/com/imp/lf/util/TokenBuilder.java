package com.imp.lf.util;

import java.util.UUID;

/**
 * Created by (IMP)郑和明
 * Date is 2017/1/19
 */
public class TokenBuilder {

    public static String tokenBuilder() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}

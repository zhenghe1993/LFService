package com.imp.lf.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by 郑和明
 * Created on 2017/4/5.
 */
public class ResultTemplate extends ResponseEntity<Object> {

    public ResultTemplate(Object body, int code) {
        this(body, HttpStatus.valueOf(code));
    }


    public ResultTemplate(Object body, HttpStatus status) {
        super(body, status);
    }

    public static final Integer SUCCESS = 200;

    public static final Integer FAILURE = 430;


    //argument lost
    public static final Integer PARAMETER_LOST = 420;


    //login
    public static final Integer PHONE_ERROR = 418;
    public static final Integer PASSWORD_ERROR = 419;

    //register
    public static final Integer PHONE_REPEAT = 422;

    public static final Integer NAME_REPEAT = 423;





    public static final Integer TOKEN_ERROR = 421;


    //words

    public static final Integer WORDS_ERROR = 425;

    //attention
    public static final Integer ATTENTION_ERROR = 426;

    //lfmessage
    public static final Integer USER_ERROR = 427;

    public static final Integer QUERY_ERROR = 428;

    //service error

    public static final Integer SQL_ERROR = 510;


}

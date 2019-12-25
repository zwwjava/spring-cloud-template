package com.ww.diary.controller;


import com.ww.diary.constants.RespCode;
import com.ww.diary.dto.MessageResp;
import lombok.extern.slf4j.Slf4j;

/**
 * 控制层的父类.提供公用方法.
 */
@Slf4j
public class BaseController {

    protected static <T> MessageResp<T> success(T data) {
        return buildResp(data, RespCode.SUCCESS);
    }

    protected static <T> MessageResp<T> buildResp(T data, RespCode respEnum) {
        MessageResp<T> resp = new MessageResp<T>();
        resp.setMessage(respEnum);
        resp.setData(data);
        return resp;
    }

    protected static <T> MessageResp<T> success() {
        return buildResp((T) null, RespCode.SUCCESS);
    }

    protected static <T> MessageResp<T> fail() {
        return buildResp((T) null, RespCode.FAIL);
    }

}

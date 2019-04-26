package com.ww.common.aop;

/**
 * Created by ww
 */
public class RespCode {

    private String code;
    private String content;

    public RespCode(String code, String content) {
        this.code = code;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

}

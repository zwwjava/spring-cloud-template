package com.ww.diary.dto;


import com.ww.diary.constants.RespCode;

/**
 * Created by puyd on 8/28/16.
 */
public class MessageResp<T> {

    protected String code = "100200";
    protected String msg = "";
    protected T data;

    public MessageResp() {
    }

    public MessageResp(T data) {
        this.data = data;
    }

    public MessageResp(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRespCode() {
        return this.code;
    }

    public void setRespCode(String respCode) {
        this.code = respCode;
    }

    public String getRespMsg() {
        return this.msg;
    }

    public void setRespMsg(String respMsg) {
        this.msg = respMsg;
    }

    public void setMessage(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public void setOverrideMessage(RespCode respCode, String msg) {
        this.code = respCode.getCode();
        this.msg = msg;
    }

    public void setMessageWithAppend(RespCode respCode, String appendMsg) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg() + "," + appendMsg;
    }

}

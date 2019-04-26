package com.ww.common.aop;

import com.ww.common.enums.RespMessageEnum;

/**
 * 全局异常基类
 */
public class WwException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private RespCode respMessage;

    private Throwable e;

    private String debugMessage;

    private boolean isAlarm;

    public WwException(RespCode respMessage) {
        this.respMessage = respMessage;
    }

    /**
     * 在异常日志记录时，debugMessage的优先级高于throwable的message
     *
     * @param respMessage
     * @param debugMessage
     */
    public WwException(RespCode respMessage, String debugMessage) {
        this.respMessage = respMessage;
        this.debugMessage = debugMessage;
    }

    /**
     * 设置预警选项
     * @param respMessage
     * @param isAlarm
     */
    public WwException(RespCode respMessage, boolean isAlarm) {
        this.respMessage = respMessage;
        this.isAlarm = isAlarm;
    }

    /**
     * 设置预警调试信息和选项
     * @param respMessage
     * @param isAlarm
     */
    public WwException(RespCode respMessage, String debugMessage, boolean isAlarm) {
        this.respMessage = respMessage;
        this.debugMessage = debugMessage;
        this.isAlarm = isAlarm;
    }

    public Throwable getThrowable() {
        return e;
    }

    public RespCode getRespMessage() {
        return respMessage;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public boolean isAlarm() {
        return isAlarm;
    }

    public void setRespMessage(RespCode respMessage) {
        this.respMessage = respMessage;
    }

    /**
     * 返回异常提示信息：
     */
    @Override
    public String getMessage() {
        return this.getRespMessage().getContent();
    }

    /**
     * 普通运行时异常
     * 将统一返回RespMessageEnum中的SHOW_TO_USER错误码
     *
     * @param message
     * @return
     */
    public static WwException commonRException(String message) {
        return new WwException(new RespCode(RespMessageEnum.SHOW_TO_USER.getCode(), message));
    }


}

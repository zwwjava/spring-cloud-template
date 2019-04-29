package com.ww.common.aop;

import com.ww.common.enums.RespMessageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Description - 异常同一处理类
 * @Author 查旺旺
 * @Date 2019/4/2 13:50
 */
@ControllerAdvice
public class ExceptionTranslator {

    private static Logger logger = LoggerFactory.getLogger(ExceptionTranslator.class);

    /**
     * 处理fide项目自定义的异常
     * @param e
     * @return
     */
    @ExceptionHandler(WwException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageResp processFideRException(WwException e) {
        RespCode respCode = e.getRespMessage();
        if(e.isAlarm()){
            logger.error("遇到错误：[{}]{}", respCode.getCode(), e.getDebugMessage(), e);
        }else{
            logger.warn("遇到错误：[{}]{}", respCode.getCode(), e.getDebugMessage(), e);
        }
        MessageResp resp = new MessageResp();
        resp.setMessage(respCode);
        return resp;
    }

    /**
     * 处理除上述异常外的非预期异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public MessageResp processOtherException(Exception e) {
        logger.error("遇到非预期的错误", e);
         MessageResp resp = new MessageResp();
        resp.setMessage(RespMessageEnum.FAIL);
        return resp;
    }

}

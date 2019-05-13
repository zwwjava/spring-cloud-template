package com.ww.common.bean;

import lombok.Data;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/5/6 15:40
 */
@Data
public class WxLoginInfo {

    private String session_key;

    private String openId;

}

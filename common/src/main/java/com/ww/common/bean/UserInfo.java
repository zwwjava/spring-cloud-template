package com.ww.common.bean;

import lombok.Data;
import org.joda.time.DateTime;

/**
 * 用户信息
 */
@Data
public class UserInfo {

    private String partyUuid;
    private String username;//用户名
    private String openid;//小程序openId
    private String mobil;//手机
    private String role;//角色
    /**
     * 登录时间
     */
    private DateTime loginTime;

    public UserInfo() {
    }

    public UserInfo(String partyUuid) {
        this.partyUuid = partyUuid;
    }

    public UserInfo(String partyUuid, String username, String openid, String mobil, String role) {
        this.partyUuid = partyUuid;
        this.username = username;
        this.openid = openid;
        this.mobil = mobil;
        this.role = role;
        this.loginTime = DateTime.now();
    }

}

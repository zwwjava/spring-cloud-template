package com.ww.common.bean;

import org.joda.time.DateTime;

/**
 * 用户信息
 */
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

    public String getPartyUuid() {
        return partyUuid;
    }

    public void setPartyUuid(String partyUuid) {
        this.partyUuid = partyUuid;
    }

    public DateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(DateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

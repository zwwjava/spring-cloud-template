package com.ww.yifeichief.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/4/25 15:19
 */
@Data
@Entity
public class UserInfo {

    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
    private String mobil;
    private String role;
}

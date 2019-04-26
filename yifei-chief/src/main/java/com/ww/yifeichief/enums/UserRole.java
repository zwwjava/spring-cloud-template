package com.ww.yifeichief.enums;

import lombok.Getter;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/4/25 15:23
 */
@Getter
public enum UserRole {
    PERSON("PERSON", "个人"),
    COMPANY("COMPANY", "公司");
    private String code;
    private String value;

    UserRole(String code, String value) {
        this.code = code;
        this.value = value;
    }
}

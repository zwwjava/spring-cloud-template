package com.ww.product.enums;

import lombok.Getter;

/**
 * @Description -
 * @Author zww
 * @Date 2019/3/13 15:53
 */
@Getter
public enum ProductStatus {
    UP("100", "100"),
    DOWN("200", "200")
    ;
    private String code;
    private String messager;

    ProductStatus(String code, String messager) {
        this.code = code;
        this.messager = messager;
    }

}

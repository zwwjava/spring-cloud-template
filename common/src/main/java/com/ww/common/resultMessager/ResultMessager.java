package com.ww.common.resultMessager;

import lombok.Data;

/**
 * @Description -
 * @Author zww
 * @Date 2019/3/14 9:52
 */
@Data
public class ResultMessager<T> {
    private Integer code;
    private String messager;
    private T data;
    private String port;

    public ResultMessager(Integer code, String messager, T data) {
        this.code = code;
        this.messager = messager;
        this.data = data;
    }

    public ResultMessager(T data) {
        this.code = 200;
        this.messager = "返回成功";
        this.data = data;
    }
}

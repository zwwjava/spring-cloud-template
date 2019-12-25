package com.ww.diary.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @Description - 创建日记文章
 * @Author zww
 * @Date 2019/12/9
 */
@Data
public class ImageCreateReq {

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 文本内容
     */
    @NotBlank(message = "文本内容不能为空")
    private String content;

    /**
     * 排序下标
     */
    @NotNull(message = "排序下标不能为空")
    private Integer sort;

    /**
     * 图片地址
     */
    @NotBlank(message = "图片地址不能为空")
    private String url;

}

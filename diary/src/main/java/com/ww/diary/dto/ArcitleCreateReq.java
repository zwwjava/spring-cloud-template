package com.ww.diary.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description - 创建日记文章
 * @Author zww
 * @Date 2019/12/9
 */
@Data
public class ArcitleCreateReq {

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 微信头像地址
     */
    @NotBlank(message = "微信头像地址不能为空")
    private String avatarUrl;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    /**
     * 图片信息
     */
    @NotNull(message = "日记内容不能为空")
    private List<ImageCreateReq> imagesList;

}

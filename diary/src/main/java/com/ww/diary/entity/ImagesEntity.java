package com.ww.diary.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ww.diary.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 图片信息表
 * </p>
 *
 * @author zww
 * @since 2019-12-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("images")
public class ImagesEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 文章UUID
     */
    private String articleId;

    /**
     * 排序下标
     */
    private Integer sort;

    /**
     * 图片介绍
     */
    private String title;

    /**
     * 文本内容
     */
    private String content;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 状态(引用程序数据字典)
     */
    private String status;


}

package com.ww.diary.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ww.diary.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("article")
public class ArticleEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 微信头像地址
     */
    private String avatarUrl;

    /**
     * 首页图片
     */
    private String url;

    /**
     * 状态(引用程序数据字典)
     */
    private String status;


}

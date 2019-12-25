package com.ww.diary.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ww.diary.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author zww
 * @since 2019-12-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 外部UUID
     */
    private String extUuid;

    /**
     * 系统登录ID
     */
    private String loginName;

    /**
     * 系统登录密码
     */
    private String password;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 用户类型
     */
    private String type;

    /**
     * 推荐人UUID
     */
    private String referrerUuid;

    /**
     * 当事人系统来源(引用程序数据字典)
     */
    private String sysSource;

    /**
     * 贷款客户标记(0-否，1-是)
     */
    private Boolean loanFlag;

    /**
     * 状态(引用程序数据字典)
     */
    private String status;


}

package com.ww.diary.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 * Date： 19-6-19.
 *
 * @author puyd
 */
@Data
@ApiModel("基础类")
public class BaseEntity {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    /**
     * UUID
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "UUID")
    private String uuid;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "最后更新时间")
    private Date lastUpdateTime;

    /**
     * 最新版本
     */
    @TableField(fill = FieldFill.INSERT_UPDATE, update = "%s+1")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Version
    @ApiModelProperty(value = "最新版本")
    private Integer version;

    /**
     * 删除标记(0-否，1-是)
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "删除标记(0-否，1-是)")
    private Boolean delFlag;

}

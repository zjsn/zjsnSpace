package com.zjsn.rolesystem.model.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.zjsn.rolesystem.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yingluo
 * @since 2021-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
@ApiModel(value="User对象", description="用户表")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "部门ID")
    @TableField("dept_id")
    private Long deptId;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "联系电话")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "状态 0锁定 1有效")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "最近访问时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "性别 0男 1女 2保密")
    @TableField("ssex")
    private String ssex;

    @ApiModelProperty(value = "是否开启tab，0关闭 1开启")
    @TableField("is_tab")
    private String isTab;

    @ApiModelProperty(value = "主题")
    @TableField("theme")
    private String theme;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "版本号")
    @TableField("version")
    @Version
    private int version;
}

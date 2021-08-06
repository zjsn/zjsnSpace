package com.zjsn.rolesystem.model.menu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.zjsn.rolesystem.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author yingluo
 * @since 2021-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_menu")
@ApiModel(value="Menu对象", description="菜单表")
public class Menu extends BaseEntity {


    @ApiModelProperty(value = "上级菜单ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "菜单/按钮名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty(value = "对应路由path")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "对应路由组件component")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "权限标识")
    @TableField("perms")
    private String perms;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "类型 0菜单 1按钮")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "排序")
    @TableField("order_num")
    private Double orderNum;



}

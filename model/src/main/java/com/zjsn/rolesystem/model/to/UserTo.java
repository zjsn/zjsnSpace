package com.zjsn.rolesystem.model.to;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjsn.rolesystem.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户数据实体类
 * </p>
 *
 * @author yingluo
 * @since 2021-08-04
 */
@Data
public class UserTo {


    private String username;

    private String password;

    private Long deptId;

    private String email;

    private String mobile;

    private String status;

    private LocalDateTime lastLoginTime;

    private String ssex;

    private String isTab;

    private String theme;

    private String avatar;

    private String description;


}

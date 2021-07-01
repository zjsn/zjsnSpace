package com.zjsn.domain.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: tangjiaren
 * @Date: 2021/5/27 9:54
 */
@Data
@ApiModel("mongoUserEntity")
public class UserMongoEntity implements Serializable {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("电话")
    private Integer phone;
}

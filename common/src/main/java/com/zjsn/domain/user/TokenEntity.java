package com.zjsn.domain.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenEntity implements Serializable {
    /**
     * token是否过期
     */
    private boolean expire;

    /**
     * uid
     */
    private Long userId;

    /**
     * name
     */
    private String userName;

    /**
     * 角色
     */
    private String userRole;
}

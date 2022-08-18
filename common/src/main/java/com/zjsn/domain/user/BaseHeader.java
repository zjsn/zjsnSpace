package com.zjsn.domain.user;

import lombok.Data;

@Data
public class BaseHeader<T> {

    private String token;
    private String requestId;
    /**
     * 调用来源
     */
    private Long source;

    private String appId;

    private String sign;

    private String timeStamp;

    private TokenEntity tokenEntity;

    private T t;
}

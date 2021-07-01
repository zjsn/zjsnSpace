package com.zjsn.util;

import lombok.Data;

/**
 * 返回数据
 */
@Data
public class R<T> {
    /*返回的code*/
    private Integer code;
    /*描述*/
    private String msg;
    /*实体body*/
    private T data;

    public R() {

    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(Integer code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.data = body;
    }

}
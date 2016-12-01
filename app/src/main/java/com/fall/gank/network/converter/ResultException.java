package com.fall.gank.network.converter;

/**
 * Created by 康颢曦 on 2016/8/3.
 */

public class ResultException extends RuntimeException {

    private String errCode = "0";

    public ResultException(String errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }
}

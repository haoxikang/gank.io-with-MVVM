package com.example.qqq34.mvvmdemo.entity;

/**
 * Created by qqq34 on 2016/11/25.
 */
public class BaseEntity
{
    private int resultCode=0;
    private String resultMessage;

    public int getResultCode()
    {
        return this.resultCode;
    }

    public String getResultMessage()
    {
        return this.resultMessage;
    }

    public void setResultCode(int code)
    {
        this.resultCode = code;
    }

    public void setResultMessage(String message)
    {
        this.resultMessage = message;
    }
}

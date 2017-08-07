package com.zhang.newapplication.model.bean;

/**
 * Created by zhang_shuai on 2017/7/7.
 * Del:实体类的基类 登录注册的时候同意调用
 */

public class BaseBean<T> {

    private String code;
    private String message;//自定义异常的消息
    private T userInfo;//用户关心的数据

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getUserInfo() {
        return userInfo;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserInfo(T userInfo) {
        this.userInfo = userInfo;
    }
}

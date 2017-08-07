package com.zhang.newapplication.presenter;

import com.zhang.newapplication.views.interfaces.BaseView;

/**
 * Created by zhang_shuai on 2017/7/6.
 * Del:P层基类
 */

public abstract class BasePresenter<T extends BaseView> {

    //定义接口的引用
    protected T showView;
    //返回接口的实现类对象
    public T getThisView(){
        return showView;
    }
    //注册接口
    public void setThisView(T thisView){
        this.showView = thisView;
    }
}

package com.zhang.newapplication.model.net;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhang_shuai on 2017/7/7.
 * Del:自定义观察者
 */

public abstract class ProgressObserver<T> implements Observer<T>{
    /**
     * 声明AlertDialog对象
     */
    private SweetAlertDialog mAlertDialog;
    private Context mContext;
    private SweetAlertDialog mErrorDialog;

    /**
     * 在构造函数中实例化AlertDialog
     *
     * @param context
     */
    public ProgressObserver(Context context) {
        mContext = context;
        mAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        mAlertDialog.setTitleText("Loading...");

    }

    @Override
    public void onNext(T value) {
        onSuccess(value);
    }

    /**
     * 开始时显示Dialog
     * @param d
     */
    @Override
    public void onSubscribe(Disposable d) {
        showProgressDialog();
    }


    /**
     * 根据服务器来显示不通的错误
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        String error = "";
        if(e instanceof ApiException){
            error = e.getMessage();
        }
        mErrorDialog = new SweetAlertDialog(mContext,SweetAlertDialog.ERROR_TYPE);
        mErrorDialog.setTitleText("出问题了...")
                        .setCancelText(error)
                        .show();
        if(mAlertDialog != null){
            mAlertDialog.cancel();
        }
    }

    /**
     * 完成时取消提示窗
     */
    @Override
    public void onComplete() {
        dismissProgressDialog();
        if (mErrorDialog != null) {
            mErrorDialog.cancel();
        }
    }

    /**
     * 显示Dialog
     */
    public void showProgressDialog() {
        if (mAlertDialog != null) {
            mAlertDialog.show();
        }
    }

    /**
     * 取消提示窗
     */
    protected void dismissProgressDialog() {
        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
        }
    }
    public abstract void onSuccess(T t);
}

package com.zhang.newapplication.views.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import com.zhang.newapplication.Common.AppManager;
import com.zhang.newapplication.Common.StatusBarCompat;
import com.zhang.newapplication.R;
import com.zhang.newapplication.model.net.ActivityLifeCycleEvent;
import com.zhang.newapplication.presenter.BasePresenter;
import com.zhang.newapplication.views.app.MyApplication;
import com.zhang.newapplication.views.interfaces.BaseView;

import butterknife.ButterKnife;
import im.quar.autolayout.AutoLayoutActivity;
import rx.subjects.PublishSubject;

/**
 * Created by zhang_shuai on 2017/7/6.
 * Del:activity的基类
 */

public abstract class BaseActivity <T extends BasePresenter> extends AppCompatActivity{

    //得到生命周期，用于联网时，判断activity的生命状态
    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();
    protected MyApplication myApplication;
    protected SharedPreferences mSp;
    protected T mPresenter;
    protected SharedPreferences.Editor editor;
    protected  Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
        super.onCreate(savedInstanceState);
        mContext = this;
        //添加集合
        AppManager.getInstance().addActivity(this);
        // 无标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
        SetStatusBarColor();
        mSp = getSharedPreferences("config",MODE_PRIVATE);
        editor = mSp.edit();

        ///注册接口
        BaseView baseInterface = initCallBack();
        if (myApplication == null) {
            myApplication = (MyApplication) getApplicationContext();
        }
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.setThisView(baseInterface);
        }
        //添加布局
        setContentView(getLayout());
        //注册butterknife
        ButterKnife.bind(this);
        //实例化P
        initPresenter();
        //初始化数据
        initData();
        //初始化控件
        initView();

    }

    /**
     * 着色状态栏,4.4以上系统有效
     */
    protected  void SetStatusBarColor(){
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }

    protected void SetTranslanteBar(){
        StatusBarCompat.translucentStatusBar(this);
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onResume() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.RESUME);
        super.onResume();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
        ButterKnife.unbind(this);
        AppManager.getInstance().finishActivity(this);
    }

    protected abstract int getLayout();

    protected abstract T initPresenter();

    protected abstract BaseView initCallBack();
    //初始化数据
    protected abstract void initData();
    //初始化试图
    protected abstract void initView();

}

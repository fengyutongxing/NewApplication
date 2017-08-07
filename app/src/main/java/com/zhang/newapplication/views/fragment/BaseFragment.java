package com.zhang.newapplication.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhang.newapplication.presenter.BasePresenter;
import com.zhang.newapplication.views.interfaces.BaseView;
import com.zhang.newapplication.views.widget.LoadingCustom;

import butterknife.ButterKnife;


/**
 * Created by zhang_shuai on 2017/7/11.
 * Del:
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected T mPresenter;
    protected Context mContext;
    protected Context mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //获取上下文
        mContext = getContext();
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutId(),container,false);
        //注册butterknife
        ButterKnife.bind(this, view);
        initView(view);
        mPresenter = initPresenter();
        //注册接口
        if (mPresenter != null) {
            mPresenter.setThisView(initCallBack());
        }
        //初始化数据
        initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注册接口
        /*if (mPresenter != null) {
            mPresenter.setThisView(initCallBack());
        }*/
        //初始化数据
//        initData();
    }


    //获取布局
    protected abstract int  getLayoutId() ;
    //返回所需要的presenter
    protected abstract T initPresenter();
    //返回Fragment所加载的视图
    protected  abstract void initView(View view);
    protected abstract void initData();
    //返回接口的实现类对象 (子类中可直接返回this)
    protected abstract BaseView initCallBack();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

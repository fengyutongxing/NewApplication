package com.zhang.newapplication.views.fragment;

import android.util.Log;
import android.view.View;

import com.zhang.newapplication.R;
import com.zhang.newapplication.model.bean.MainBean;
import com.zhang.newapplication.presenter.VideoPresenter;
import com.zhang.newapplication.views.interfaces.BaseView;
import com.zhang.newapplication.views.interfaces.VideoView;

import retrofit2.Call;

/**
 * Created by zhang_shuai on 2017/7/11.
 * Del:视频
 */

public class VideoFragment extends BaseFragment<VideoPresenter> implements VideoView {
    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected VideoPresenter initPresenter() {
        return new VideoPresenter(getActivity());
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected BaseView initCallBack() {
        return (BaseView) getActivity();
    }

    @Override
    public void OnSuccess(MainBean mainBean) {
        
    }

    @Override
    public void onError(Call<MainBean> t) {

    }
}

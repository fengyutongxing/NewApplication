package com.zhang.newapplication.views.interfaces;

import com.zhang.newapplication.model.bean.MainBean;

import retrofit2.Call;

/**
 * Created by zhang_shuai on 2017/7/11.
 * Del:
 */

public interface VideoView extends BaseView {
    void OnSuccess(MainBean mainBean);
    void onError(Call<MainBean> t);
}

package com.zhang.newapplication.presenter;

import android.content.Context;

import com.zhang.newapplication.model.bean.MainBean;
import com.zhang.newapplication.model.net.NetClient;
import com.zhang.newapplication.views.interfaces.VideoView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by zhang_shuai on 2017/7/11.
 * Del:
 */

public class VideoPresenter extends BasePresenter<VideoView> {

    private Context context;
    public VideoPresenter(Context context) {
        this.context = context;
    }
    /**
     * 获取新闻头条内容
     * @param type
     * @param key
     */
    public void getDatasFromNet(String type, String key) {
        Call<MainBean> androidInfo = NetClient.getApiRetrofitInstance().getTop(type, key);
        androidInfo.enqueue(new Callback<MainBean>() {
            @Override
            public void onResponse(Call<MainBean> call, Response<MainBean> response) {
                showView.OnSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MainBean> call, Throwable t) {
                showView.onError(call);
            }
        });

    }

}

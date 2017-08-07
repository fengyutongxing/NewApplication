package com.zhang.newapplication.model.net;

import com.zhang.newapplication.model.bean.BaseBean;
import com.zhang.newapplication.model.bean.MainBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zhang_shuai on 2017/7/7.
 * Del:拼接网络请求接口
 */

public interface ApiService {

    @GET("toutiao/index")
    Call<MainBean> getTop(@Query("type") String type, @Query("key") String key);
}

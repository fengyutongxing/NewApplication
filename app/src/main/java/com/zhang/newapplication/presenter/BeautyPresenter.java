package com.zhang.newapplication.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.zhang.newapplication.model.bean.BeautyBean;
import com.zhang.newapplication.views.adapter.ListAdapter;
import com.zhang.newapplication.views.interfaces.BeautyView;


import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by zhang_shuai on 2017/7/11.
 * Del:请求美女
 */

public class BeautyPresenter extends BasePresenter<BeautyView> {

    //图片请求的网址和封装的不是同一服务器
    private String url = "http://oatest.yuhong.com.cn/GetImages";
    private ListView listView;
    private Context context;
    private ArrayList<BeautyBean> beanArrayList = new ArrayList<>();
    //请求的数据集合
    private ArrayList<BeautyBean> list;
    public BeautyPresenter(Context context){
        this.context = context;
    }
    private Handler handler = new Handler(){



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    list = (ArrayList<BeautyBean>) msg.obj;
                    listView.setAdapter(new ListAdapter(list,context));
                    break;
            }
        }
    };


    public void getNetClick(final ListView listView) {
        this.listView = listView;
        //得到OKHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        // 获取Request对象
        Request request = new Request.Builder()
                .url(url)
                .build();
        // 获取Call对象
        Call call = okHttpClient.newCall(request);
        //获取Response对象, 通过接口回调方式返回Response
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                //TODO 请求失败逻辑在这里处理
                //TODO 注意:这里边都是子线程,所以要更改UI的时候需要发送到主线程才OK
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //TODO 请求成功时候
                //TODO 注意:这里边都是子程线,所以要更改UI的时候需要发送到主线程才OK
                String result = response.body().string();
                //Json的解析类对象
                JsonParser parser = new JsonParser();
                //将JSON的String 转成一个JsonArray对象
                JsonArray jsonArray = parser.parse(result).getAsJsonArray();
                Gson gson = new Gson();
                //加强for循环遍历JsonArray
                for (JsonElement user : jsonArray) {
                    //使用GSON，直接转成Bean对象
                    BeautyBean beautyBean = gson.fromJson(user, BeautyBean.class);
                    beanArrayList.add(beautyBean);
                }
                //在子线程中将Message对象发出去\s
                Message message = new Message();
                message.obj = beanArrayList;
                message.what = 1;
                handler.sendMessage(message);
            }
        });
    }
    public ArrayList<BeautyBean> getList(){
        return list;
    }
}

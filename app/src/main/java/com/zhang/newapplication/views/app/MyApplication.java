package com.zhang.newapplication.views.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.IBinder;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloader;
import com.zhang.newapplication.utils.SharedPreferencesUtils;

import java.util.ArrayList;

/**
 * Created by zhang_shuai on 2017/7/6.
 * Del:需要的配置文件
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;
    private ArrayList<Activity> mActivityList ;
    public static SharedPreferences mSp;
    public static Context _context;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        _context = getApplicationContext();
        if(mActivityList == null){
            mActivityList = new ArrayList<>();
        }
        mSp = getSharedPreferences("config",MODE_PRIVATE);
        //图片的加载
        Fresco.initialize(this);

        boolean isNightMode = (boolean) SharedPreferencesUtils.get(_context, "night_mode", false);
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    public static Context getAppContext() {
        return myApplication;
    }
    public static Resources getAppResources() {
        return myApplication.getResources();
    }


    // x5 init service
    public class AdvanceLoadX5Service extends Service {
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            initX5();
        }

        private void initX5() {
            //  预加载X5内核
            QbSdk.initX5Environment(getApplicationContext(), cb);
        }

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //初始化完成回调
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
    }
    /**
     * 分包
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    public static MyApplication getInstance() {
        return myApplication;
    }
}

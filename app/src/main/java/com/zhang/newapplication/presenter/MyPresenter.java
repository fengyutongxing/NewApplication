package com.zhang.newapplication.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;

import com.zhang.newapplication.R;
import com.zhang.newapplication.views.interfaces.MyView;
import com.zhang.newapplication.views.widget.GifSizeFilter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

/**
 * Created by zhang_shuai on 2017/7/11.
 * Del:
 */

public class MyPresenter extends BasePresenter<MyView> {
    private static final int REQUEST_CODE_CHOOSE = 23;//定义请求码常量

    public void getMatisse(Context context){
        Matisse.from((Activity) context)
                .choose(MimeType.allOf())//照片视频全部显示
                .countable(true)//有序选择图片
                .capture(true)//是否允许打开相机
                .captureStrategy(
                        new CaptureStrategy(true, "com.zhang.newapplication.fileprovider"))
                .maxSelectable(9)//最大选择数量为9
                .theme(R.style.Matisse)//设置主题
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                        .gridExpectedSize(
//                                getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)//图像选择和预览活动所需的方向。
                .thumbnailScale(0.85f)//缩放比例
                .imageEngine(new GlideEngine())//加载方式
                .forResult(REQUEST_CODE_CHOOSE);//请求码
    }
}

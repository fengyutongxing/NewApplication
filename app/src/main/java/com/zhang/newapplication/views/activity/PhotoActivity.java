package com.zhang.newapplication.views.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import com.zhang.newapplication.R;
import com.zhang.newapplication.model.bean.BeautyBean;
import com.zhang.newapplication.presenter.BeautyPresenter;
import com.zhang.newapplication.views.interfaces.BaseView;
import com.zhang.newapplication.views.widget.MultiTouchViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;
import me.relex.photodraweeview.PhotoDraweeView;

/**
 * Created by zhang_shuai on 2017/7/31.
 * Del:图片详情
 */

public class PhotoActivity extends BaseActivity {

    @Bind(R.id.view_pager)
    MultiTouchViewPager viewPager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    /**
     * 小圆点
     */
    @Bind(R.id.indicator)
    CircleIndicator indicator;
    private static ArrayList<BeautyBean> mList ;
    @Override
    protected int getLayout() {
        return R.layout.activity_photo;

    }

    @Override
    protected BeautyPresenter initPresenter() {
        return null;
    }

    @Override
    protected BaseView initCallBack() {
        return null;
    }

    @Override
    protected void initData() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        viewPager.setAdapter(new DraweePagerAdapter());
        indicator.setViewPager(viewPager);
    }

    @Override
    protected void initView() {

    }

    public static void startActivity(Context context, ArrayList<BeautyBean> list) {
        context.startActivity(new Intent(context, PhotoActivity.class));
        mList = list;
    }

    public class DraweePagerAdapter extends PagerAdapter {


        @Override public int getCount() {
            return mList.size();
        }

        @Override public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override public Object instantiateItem(ViewGroup viewGroup, int position) {
            final PhotoDraweeView photoDraweeView = new PhotoDraweeView(viewGroup.getContext());
            PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
            controller.setUri(Uri.parse(mList.get(position).getUrl()));
            controller.setOldController(photoDraweeView.getController());
            controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
                @Override
                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                    super.onFinalImageSet(id, imageInfo, animatable);
                    if (imageInfo == null) {
                        return;
                    }
                    photoDraweeView.update(imageInfo.getWidth(), imageInfo.getHeight());
                }
            });
            photoDraweeView.setController(controller.build());
            try {
                viewGroup.addView(photoDraweeView, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return photoDraweeView;
        }
    }
}

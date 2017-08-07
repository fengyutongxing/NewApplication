package com.zhang.newapplication.views.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhang.newapplication.R;
import com.zhang.newapplication.presenter.MyPresenter;
import com.zhang.newapplication.utils.SharedPreferencesUtils;
import com.zhang.newapplication.utils.ToastUtil;
import com.zhang.newapplication.views.app.MyApplication;
import com.zhang.newapplication.views.interfaces.BaseView;
import com.zhang.newapplication.views.interfaces.MyView;
import com.zhang.newapplication.views.widget.GifSizeFilter;
import com.zhang.newapplication.views.widget.WaveView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.ui.MatisseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by zhang_shuai on 2017/7/11.
 * Del:
 */

public class MyFragment extends BaseFragment<MyPresenter> implements MyView, View.OnClickListener {
    /**
     * 自定义的波纹
     */
    @Bind(R.id.wave_view)
    WaveView waveView;
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    //侧滑
    @Bind(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.left_drawer)
    ListView mDrawerList;
    @Bind(R.id.my_iv)
    ImageView myIv;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> mList = new ArrayList<>();

    private static final int REQUEST_CODE_CHOOSE = 23;//定义请求码常量

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected MyPresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void initView(View view) {
        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2, -2);
        lp.gravity = Gravity.BOTTOM | Gravity.CENTER;
        waveView.setOnWaveAnimationListener(new WaveView.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
                lp.setMargins(0, 0, 0, (int) y + 2);
                image.setLayoutParams(lp);
            }
        });
        myIv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        //设置toolbar
        AppCompatActivity appCompatActivity = (AppCompatActivity) mActivity;
//        appCompatActivity. getActionBar().setDisplayHomeAsUpEnabled(true);
//        appCompatActivity.getActionBar().setHomeButtonEnabled(true);
        appCompatActivity.setSupportActionBar(toolbar);
        setHasOptionsMenu(true);//让菜单生效
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(appCompatActivity, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();//同步drawerLayout
        toolbar.setTitle("个人信息");
        mList.add("");
        mList.add("想加什么就加什么");
        for (int i = 0; i < 30; i++) {
            mList.add("List Item  0" + i);
        }
        //设置菜单列表
        arrayAdapter = new ArrayAdapter(appCompatActivity, android.R.layout.simple_list_item_1, mList);
        mDrawerList.setAdapter(arrayAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notification:
                ToastUtil.show("跳转到登录界面");
                break;
            case R.id.action_night:
                if ((boolean) SharedPreferencesUtils.get(MyApplication._context, "night_mode", false)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferencesUtils.put(MyApplication._context, "night_mode", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferencesUtils.put(MyApplication._context, "night_mode", true);
                }
//                getActivity().recreate();
                break;
            case R.id.action_setting:
                ToastUtil.show("靓仔还没完成哦！");
//                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected BaseView initCallBack() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_iv:
                mPresenter.getMatisse(getActivity());
                break;
        }
    }

    List<Uri> mSelected;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("tag", "mSelected:========= " + mSelected);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.e("tag", "mSelected:========= " + mSelected);
        }
    }

}

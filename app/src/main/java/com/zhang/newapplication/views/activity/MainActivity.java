package com.zhang.newapplication.views.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.zhang.newapplication.Common.AppManager;
import com.zhang.newapplication.R;
import com.zhang.newapplication.presenter.MainPresenter;
import com.zhang.newapplication.views.adapter.MyViewPagerAdapter;
import com.zhang.newapplication.views.fragment.BeautyFragment;
import com.zhang.newapplication.views.fragment.HeadlinesFragment;
import com.zhang.newapplication.views.fragment.MyFragment;
import com.zhang.newapplication.views.fragment.NewFragment;
import com.zhang.newapplication.views.fragment.VideoFragment;
import com.zhang.newapplication.views.interfaces.BaseView;
import com.zhang.newapplication.views.interfaces.MainView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by zhang_shuai on 2017/7/6.
 * Del:主界面的展示
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.tablayout)
    TabLayout mTablayout;
    //标题栏
    private ArrayList<String> mTitles ;
    //未选择中的
    private ArrayList<Integer> mIcon;
    ///Fragment 数组
    private ArrayList<Fragment> mFragments;
    private MyViewPagerAdapter mAdapter;

    /**
     * 绑定xml布局
     */
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    /**
     * 初始化P
     */
    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter(this);
    }


    @Override
    protected BaseView initCallBack() {
        return this;
    }

    @Override
    protected void initData() {
        mTitles = new ArrayList<>();
        mIcon = new ArrayList<>();
        mFragments = new ArrayList<>();
        mTitles.add("新闻");
        mTitles.add("视频");
        mTitles.add("美女");
        mTitles.add("我的");
        mIcon.add(R.drawable.tab_icon_address_nor);
        mIcon.add(R.drawable.tab_icon_message_nor);
        mIcon.add(R.drawable.tab_icon_me_nor);
        mIcon.add(R.drawable.tab_icon_schedule_nor);
        mFragments.add(new NewFragment());
        mFragments.add(new VideoFragment());
        mFragments.add(new BeautyFragment());
        mFragments.add(new MyFragment());
    }

    @Override
    protected void initView() {
        //设置tab
        mPresenter.setTabs(mTablayout, this.getLayoutInflater(), mTitles, mIcon);
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewpager.setAdapter(mAdapter);
        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablayout));
        // 跟随ViewPager的页面切换
        mTablayout.setupWithViewPager(mViewpager);
        //ViewPager的页面切换
        mPresenter.getSwitch(mTablayout, mViewpager);
//        mTablayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewpager));

    }
    /**
     * 监听返回--是否退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 是否退出应用
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(mContext).setTitle("确认退出吗？")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           // 退出
                            AppManager.getInstance().AppExit(mContext);
                        }
                    })
                    .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
        return super.onKeyDown(keyCode, event);
    }


}

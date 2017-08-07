package com.zhang.newapplication.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.zhang.newapplication.R;
import com.zhang.newapplication.presenter.NewPresenter;
import com.zhang.newapplication.utils.MyUtils;
import com.zhang.newapplication.utils.ToastUtil;
import com.zhang.newapplication.views.adapter.MyViewPagerAdapter;
import com.zhang.newapplication.views.interfaces.BaseView;
import com.zhang.newapplication.views.interfaces.NewView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by zhang_shuai on 2017/7/11.
 * Del:新闻首页
 */

public class NewFragment extends BaseFragment<NewPresenter> implements NewView,View.OnClickListener {

    //标题
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyViewPagerAdapter mAdapter;
    private TabLayout tabLayout;
    public static ViewPager viewPager;
    private ImageView imageView,search;//图片
    private Toolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new;
    }

    @Override
    protected NewPresenter initPresenter() {
        return new NewPresenter(getActivity());
    }

    @Override
    protected void initView(View view) {
        mTitles.add("头条");
        mTitles.add("社会");
        mTitles.add("国内");
        mTitles.add("国际");
        mTitles.add("娱乐");
        mTitles.add("体育");
        mTitles.add("军事");
        mTitles.add("科技");
        mTitles.add("财经");
        mTitles.add("时尚");
        for (int i=0;i<mTitles.size();i++){
            mFragments.add(HeadlinesFragment.newInstance(i));
        }
        tabLayout = (TabLayout) view.findViewById(R.id.tabs__news);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager_news);
        imageView = (ImageView) view.findViewById(R.id.add_channel_iv);
        search = (ImageView) view.findViewById(R.id.new_search);
        toolbar = (Toolbar) view.findViewById(R.id.news_toobar);
        toolbar.setTitle("九州新闻");

        imageView.setOnClickListener(this);
        search.setOnClickListener(this);
        /**
         * Fragment嵌套Fragment
         */
        mAdapter = new MyViewPagerAdapter(getChildFragmentManager(),mFragments,mTitles);
        viewPager.setAdapter(mAdapter);
        // 跟随ViewPager的页面切换
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        //计算tabLayout内容的宽度
        MyUtils.dynamicSetTabLayoutMode(tabLayout);
        //设置下划线高度
        tabLayout.setSelectedTabIndicatorHeight(10);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
//                mPresenter.setIndicator(tabLayout,10,10);
            }
        });
    }

    @Override
    protected void initData() {

    }
    @Override
    protected BaseView initCallBack() {
        return this;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_channel_iv:
                ToastUtil.show("正在努力完善中.....");
                break;
            case R.id.new_search:
                //跳转到搜索页面
                break;
        }
    }
}

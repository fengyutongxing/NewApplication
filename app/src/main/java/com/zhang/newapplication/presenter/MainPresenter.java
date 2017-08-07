package com.zhang.newapplication.presenter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.zhang.newapplication.R;
import com.zhang.newapplication.views.interfaces.BaseView;
import com.zhang.newapplication.views.interfaces.MainView;

import java.util.ArrayList;

/**
 * Created by zhang_shuai on 2017/7/6.
 * Del:mainActivity的P层
 */

public class MainPresenter extends BasePresenter<MainView> implements BaseView {

    private final Context mContext;

    public MainPresenter(Context context) {
        mContext = context;
    }

    /**
     * 设置添加Tab
     */
    public void setTabs(TabLayout tabLayout, LayoutInflater inflater, ArrayList<String> tabTitlees, ArrayList<Integer> tabImgs) {
        for (int i = 0; i < tabImgs.size(); i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.tab_custom, null);
            tab.setCustomView(view);

            TextView tvTitle = (TextView) view.findViewById(R.id.tv_tab);
            tvTitle.setText(tabTitlees.get(i));
            ImageView imgTab = (ImageView) view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs.get(i));
            tabLayout.addTab(tab);

        }

    }

    public void getSwitch(TabLayout mTablayout, final ViewPager mViewpager) {
        //设置背景颜色
        mTablayout.getTabAt(0).setIcon(R.drawable.selector_new);
        mTablayout.getTabAt(1).setIcon(R.drawable.selector_shiping);
        mTablayout.getTabAt(2).setIcon(R.drawable.selector_meiren);
        mTablayout.getTabAt(3).setIcon(R.drawable.selector_my);
        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                // 设置ViewPager的页面切换
                mViewpager.setCurrentItem(position);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}

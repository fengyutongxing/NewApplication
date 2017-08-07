package com.zhang.newapplication.views.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.PopupWindow;

import com.zhang.newapplication.R;
import com.zhang.newapplication.model.bean.HeadlinesBeean;
import com.zhang.newapplication.model.bean.MainBean;
import com.zhang.newapplication.presenter.VideoPresenter;
import com.zhang.newapplication.views.adapter.MyAdapter;
import com.zhang.newapplication.views.interfaces.BaseView;
import com.zhang.newapplication.views.interfaces.VideoView;
import com.zhang.newapplication.views.widget.LoadingCustom;
import com.zhang.newapplication.views.widget.MyRecyclerViewScrollListener;
import com.zhang.newapplication.views.widget.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by zhang_shuai on 2017/7/13.
 * Del:新闻页面
 */

public class HeadlinesFragment extends BaseFragment<VideoPresenter> implements VideoView {

    @Bind(R.id.headlines_rrv)
    RefreshRecyclerView headlinesRrv;
    @Bind(R.id.headlines_srl)
    SwipeRefreshLayout headlinesSrl;
    private MyAdapter myAdapter;
    private ArrayList<HeadlinesBeean> mHeadlinesBeeen = new ArrayList<>() ;
    /**
     * 模拟刷新
     */
    private Handler handler = new Handler();
    private List<MainBean.ResultBean.DataBean> data;
    /**
     * 返回顶部
     */
    private FloatingActionButton mFab;
    //标记
    private int tag;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_toutiao;
    }

    @Override
    protected VideoPresenter initPresenter() {
        return new VideoPresenter(getActivity());
    }

    @Override
    protected void initView(View view) {
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        //默认不显示
        mFab.hide();
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headlinesRrv.smoothScrollToPosition(0);//回到顶部
            }
        });

        //设置颜色
        headlinesSrl.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_blue_light, android.R.color.background_light);

        headlinesRrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        headlinesRrv.setLoadMoreEnable(true);//允许加载更多
        headlinesRrv.setFooterResource(R.layout.item_footer);//设置脚布局
        //滑动监听
        headlinesRrv.addOnScrollListener(new MyRecyclerViewScrollListener(mFab));
        myAdapter = new MyAdapter(mHeadlinesBeeen);
        //点击事件
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {

            private PopupWindow popupWindow;
            private WebView mWebView;
            @Override
            public void onItemClick(View view, int position) {
                View view1 =  LayoutInflater.from(mActivity).inflate(R.layout.pop_layout,null,true);
                view1.setFocusable(true); // 这个很重要
                view1.setFocusableInTouchMode(true);

                mWebView = (WebView) view1.findViewById(R.id.pop_webView);
                mWebView.loadUrl(data.get(position).getUrl());

                popupWindow = new PopupWindow(view1, DrawerLayout.LayoutParams.MATCH_PARENT,DrawerLayout.LayoutParams.MATCH_PARENT, true);
                popupWindow.setContentView(view1);
                popupWindow.setFocusable(true);
                //显示PopupWindow
                View rootview = LayoutInflater.from(mActivity).inflate(R.layout.item_footer, null);
                popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
               // 重写onKeyListener
                view1.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            popupWindow.dismiss();
                            popupWindow = null;
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
        headlinesRrv.setAdapter(myAdapter);
        //上拉加载更多
        headlinesRrv.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener() {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.getDatasFromNet("shehui", "b2c387cf93202955c2d2db69819e0c58");
                        for (MainBean.ResultBean.DataBean d : data) {
                            mHeadlinesBeeen.add(new HeadlinesBeean(d.getTitle(),d.getDate(),d.getAuthor_name(),d.getThumbnail_pic_s()));
                        }
                        headlinesRrv.notifyData();//刷新数据
                    }
                }, 2000);
            }
        });

        //下拉刷新
        headlinesSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        getToast(getActivity(),"已经是最新数据了!");
                        headlinesSrl.setRefreshing(false);
                        headlinesRrv.notifyData();//刷新数据
                    }
                }, 2000);
            }
        });

    }


    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        tag = bundle.getInt("tag");
        if(tag == 0){
            LoadingCustom.showprogress(getActivity(),"正在加载",true);
            mPresenter.getDatasFromNet("top", "b2c387cf93202955c2d2db69819e0c58");
        }else if(tag == 1){
            mPresenter.getDatasFromNet("shehui", "b2c387cf93202955c2d2db69819e0c58");
        }else if(tag == 2){
            mPresenter.getDatasFromNet("guonei", "b2c387cf93202955c2d2db69819e0c58");
        }else if(tag == 3){
            mPresenter.getDatasFromNet("guoji", "b2c387cf93202955c2d2db69819e0c58");
        }else if(tag == 4){
            mPresenter.getDatasFromNet("yule", "b2c387cf93202955c2d2db69819e0c58");
        }else if(tag == 5){
            mPresenter.getDatasFromNet("tiyu", "b2c387cf93202955c2d2db69819e0c58");
        }else if(tag == 6){
            mPresenter.getDatasFromNet("junshi", "b2c387cf93202955c2d2db69819e0c58");
        }else if(tag == 7){
            mPresenter.getDatasFromNet("keji", "b2c387cf93202955c2d2db69819e0c58");
        }else if(tag == 8){
            mPresenter.getDatasFromNet("caijing", "b2c387cf93202955c2d2db69819e0c58");
        }else if(tag == 9){
            mPresenter.getDatasFromNet("shishang", "b2c387cf93202955c2d2db69819e0c58");
        }
    }

    @Override
    protected BaseView initCallBack() {
        return this;
    }

    @Override
    public void OnSuccess(MainBean mainBean) {
        if (mainBean.getResult().getStat().equals("1")) {
            LoadingCustom.dismissprogress();
            data = mainBean.getResult().getData();
            for (MainBean.ResultBean.DataBean d : data) {
                mHeadlinesBeeen.add(new HeadlinesBeean(d.getTitle(),d.getDate(),d.getAuthor_name(),d.getThumbnail_pic_s()));
            }
        }
        headlinesRrv.notifyData();//刷新数据
    }

    @Override
    public void onError(Call<MainBean> t) {

    }

    /**
     * 获取fragment对象
     * @param tag
     * @return
     */
    public static HeadlinesFragment newInstance(int tag) {
        Bundle args = new Bundle();
        args.putInt("tag",tag);
        HeadlinesFragment fragment = new HeadlinesFragment();
        fragment.setArguments(args);
        return fragment;
    }

}

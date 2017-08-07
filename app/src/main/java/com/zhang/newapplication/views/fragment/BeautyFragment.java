package com.zhang.newapplication.views.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhang.newapplication.R;
import com.zhang.newapplication.presenter.BeautyPresenter;
import com.zhang.newapplication.views.activity.PhotoActivity;
import com.zhang.newapplication.views.interfaces.BaseView;
import com.zhang.newapplication.views.interfaces.BeautyView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhang_shuai on 2017/7/11.
 * Del:
 */

public class BeautyFragment extends BaseFragment<BeautyPresenter> implements BeautyView {

    @Bind(R.id.beauty_listView)
    ListView mListView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_beauty;
    }

    @Override
    protected BeautyPresenter initPresenter() {
        return new BeautyPresenter(getActivity());
    }

    @Override
    protected void initView(View view) {
        toolbar.setTitle("美女集锦");
    }

    @Override
    protected void initData() {
        mPresenter.getNetClick(mListView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoActivity.startActivity(getActivity(),mPresenter.getList());
            }
        });
    }
    @Override
    protected BaseView initCallBack() {
        return (BaseView) getActivity();
    }

}

package com.zhang.newapplication.views.activity;


import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zhang.newapplication.R;
import com.zhang.newapplication.presenter.MainPresenter;
import com.zhang.newapplication.utils.ToastUtil;
import com.zhang.newapplication.views.fragment.HeadlinesFragment;
import com.zhang.newapplication.views.fragment.NewFragment;
import com.zhang.newapplication.views.interfaces.BaseView;
import com.zhang.newapplication.views.interfaces.MainView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhang_shuai on 2017/7/19.
 * Del:暂时还没有用
 */

public class NewsDetailActivity extends BaseActivity<MainPresenter> implements MainView {

    @Bind(R.id.etWebsite)
    EditText etWebsite;
    @Bind(R.id.tvEnter)
    TextView tvEnter;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    /**
     * http://v.qq.com/iframe/player.html?vid=s001529w0dr&tiny=0&auto=0 视频网址
     */

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

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

    }

    @Override
    protected void initView() {
        Intent intent = new Intent();
        String url = getIntent().getStringExtra("url");
        setResult(RESULT_OK,intent);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        loadUrl(url);
        tvEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = etWebsite.getText().toString();
                if (TextUtils.isEmpty(url) || !url.startsWith("http")) {
                    ToastUtil.show("网址错误!");
                    return;
                }
                loadUrl(url);
            }
        });
    }



    private void loadUrl(String url) {
        etWebsite.setText(url);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView var1, int var2, String var3, String var4) {
                progressBar.setVisibility(View.GONE);
                ToastUtil.show("网页加载失败");
            }
        });
        //进度条
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    etWebsite.setText(webView.getOriginalUrl());
                    progressBar.setVisibility(View.GONE);
                    tvEnter.setText("刷新");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
            return true;
        }
        finish();
        return super.onKeyDown(keyCode, event);
    }
}

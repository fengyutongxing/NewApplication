package com.zhang.newapplication.views.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.newapplication.R;
import com.zhang.newapplication.presenter.BasePresenter;
import com.zhang.newapplication.views.interfaces.BaseView;

import butterknife.Bind;

/**
 * Created by zhang_shuai on 2017/7/10.
 * Del:启动页
 */

public class SplashActivity extends BaseActivity {
    @Bind(R.id.iv_logo)
    ImageView ivLogo;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected BaseView initCallBack() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        SetTranslanteBar();
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(tvName, alpha, scaleX, scaleY);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(ivLogo, alpha, scaleX, scaleY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(2000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //动画结束的时候跳转
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                //跳转动画
                SplashActivity.this.overridePendingTransition(R.anim.fade_in,
                        R.anim.fade_out);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
    }

    /**
     * 监听返回--是否退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 是否退出应用
//            if (MyApplication.get(NetClient.KEY_DOUBLE_CLICK_EXIT, true)) {
//                return mDoubleClickExitHelper.onKeyDown(keyCode, event);
//            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}

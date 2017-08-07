package com.zhang.newapplication.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.zhang.newapplication.views.app.MyApplication;

/**
 * Created by zhang_shuai on 2017/7/25.
 * Del:
 */

public class ToastUtil {
    public static void show(Object object) {
        Toast toast = Toast.makeText(MyApplication.getInstance(), object + "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}

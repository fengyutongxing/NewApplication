package com.zhang.newapplication.views.widget;

import android.content.Context;
import android.graphics.Point;

import com.zhang.newapplication.R;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.UncapableCause;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhang_shuai on 2017/8/4.
 * Del:图片过滤器
 */

public class GifSizeFilter extends Filter {

    private int mMinWidth;
    private int mMinHeight;
    private int mMaxSize;

    public GifSizeFilter(int minWidth, int minHeight, int maxSizeInBytes) {
        mMinWidth = minWidth;
        mMinHeight = minHeight;
        mMaxSize = maxSizeInBytes;
    }

    @Override
    public Set<MimeType> constraintTypes() {
        return new HashSet<MimeType>() {{
            add(MimeType.GIF);
            add(MimeType.PNG);
            add(MimeType.JPEG);
        }};
    }


    @Override
    public UncapableCause filter(Context context, Item item) {
        if (!needFiltering(context, item))
            return null;

        Point size = PhotoMetadataUtils.getBitmapBound(context.getContentResolver(), item.getContentUri());
        if (size.x < mMinWidth || size.y < mMinHeight || item.size > mMaxSize) {
            return new UncapableCause(UncapableCause.DIALOG, context.getString(mMinWidth,
                    String.valueOf(PhotoMetadataUtils.getSizeInMB(mMaxSize))));
        }
        return null;
    }
}

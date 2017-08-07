package com.zhang.newapplication.views.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhang.newapplication.R;
import com.zhang.newapplication.model.bean.BeautyBean;

import java.util.ArrayList;

/**
 * Created by zhang_shuai on 2017/7/31.
 * Del:
 */

public class ListAdapter extends BaseAdapter {
    private ArrayList<BeautyBean> list;
    private Context context;
    public ListAdapter(ArrayList<BeautyBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_layout, null, false);
            viewHolder = new ViewHolder();
            viewHolder.mImageView = (SimpleDraweeView) convertView.findViewById(R.id.my_imageview);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.my_textview);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mImageView.setImageURI(Uri.parse(list.get(position).getUrl()));
        viewHolder.mTextView.setText(list.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        SimpleDraweeView mImageView;
        TextView mTextView;
    }

}

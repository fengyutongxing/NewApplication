package com.zhang.newapplication.views.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhang.newapplication.R;
import com.zhang.newapplication.model.bean.HeadlinesBeean;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang_shuai on 2017/7/12.
 * Del:头条的适配器
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener{

    private ArrayList<HeadlinesBeean> mHeadlinesBeeen ;
    private OnItemClickListener mOnItemClickListener = null;

    public MyAdapter(ArrayList<HeadlinesBeean> dataList) {
        this.mHeadlinesBeeen = dataList;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        holder.tv.setText(mHeadlinesBeeen.get(position).getTitle());
        holder.data.setText(mHeadlinesBeeen.get(position).getData());
        holder.author.setText(mHeadlinesBeeen.get(position).getAuthor_name());
        //网络请求的图片
        holder.sdv.setImageURI(Uri.parse(mHeadlinesBeeen.get(position).getThumbnail_pic_s()));
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv,data,author;
        private SimpleDraweeView sdv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_title);
            author = (TextView) itemView.findViewById(R.id.tou_author);
            data = (TextView) itemView.findViewById(R.id.tou_time);
            sdv = (SimpleDraweeView) itemView.findViewById(R.id.tou_iv);
        }
    }

    @Override
    public int getItemCount() {
        return (mHeadlinesBeeen == null || mHeadlinesBeeen.size() == 0)?0:mHeadlinesBeeen.size();
    }

    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}

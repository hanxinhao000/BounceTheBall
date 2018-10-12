package com.example.administrator.gameapplication.game;

import android.content.Context;

import java.util.List;

public abstract class BaseLevel {

    public List<ZKBean> mList;
    public Context mContext;
    //屏幕的宽
    public int width;
    //屏幕的高
    public int height;

    public BaseLevel(Context mContext) {
        this.mContext = mContext;
        width = mContext.getResources().getDisplayMetrics().widthPixels;
        height = mContext.getResources().getDisplayMetrics().heightPixels;
        initRec();
    }

    public abstract List<ZKBean> getList();

    public abstract void initRec();


}

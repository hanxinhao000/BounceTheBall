package com.example.administrator.gameapplication.game;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 等级1,这块制作一个等级,后期可以直接加
 */
public class LevelOne extends BaseLevel {


    public LevelOne(Context mContext) {
        super(mContext);
    }

    @Override
    public List<ZKBean> getList() {
        return mList;

    }

    //初始化方块坐标
    @Override
    public void initRec() {
        mList = new ArrayList<>();
        //方块坐标调小一点
        //一个屏幕调整为
        int midW = width / 40;

        for (int i1 = 0; i1 < 30; i1++) {
            //这是一排
            for (int i = 0; i < 38; i++) {

                if(i % 2  == 0) {
                    ZKBean zkBean = new ZKBean();
                    zkBean.setStartX(i * midW + 3);
                    zkBean.setEndX((i + 1) * midW - 3);
                    zkBean.setStartY(i1 * midW + 3);
                    zkBean.setEndY((i1 * midW) + midW - 3);
                    mList.add(zkBean);
                }
            }
        }



    }


}

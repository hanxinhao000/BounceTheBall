package com.example.administrator.gameapplication.game;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * 主类,控制类
 */
public class BounceTheBallGame {

    private GameBean mGameBean;
    private Context mContext;
    //球的初始
    private Ball ball;


    private  LevelOne mlevelOne;
    //屏幕高度
    private int mHeight;
    //屏幕宽度
    private int mWidth;

    /**
     * 地板的画笔
     */
    private Paint mLinePaint;
    /**
     * 方块的画笔
     */
    private Paint mRecPaint;
    private ArrayList<Ball> arr;


    /**
     * 开始初始化各种信息
     */

    public void initData(Context mContext) {

        mHeight = mContext.getResources().getDisplayMetrics().heightPixels;
        mWidth = mContext.getResources().getDisplayMetrics().widthPixels;


        //初始化坐标
        mGameBean = new GameBean();

        mGameBean.setStartX(0);
        //高度设置屏幕的9/10
        mGameBean.setStartY((mHeight / 10) * 8);
        //宽度设置为屏幕的1/6
        mGameBean.setEndX(mWidth / 5);

        mGameBean.setEndY((mHeight / 10) * 8);

        //设置地板默认为屏幕中间

        mGameBean.setStartX((mWidth / 2) - (mGameBean.getEndX() / 2));
        mGameBean.setEndX((mWidth / 2) + (mGameBean.getEndX()) / 2);


        /**
         * 初始化画笔
         */
        mLinePaint = new Paint();
        //设置质量为高
        mLinePaint.setAntiAlias(true);
        //设置画笔的颜色
        mLinePaint.setColor(Color.parseColor("#000000"));
        //设置画笔的粗细
        mLinePaint.setStrokeWidth(20);

        /**
         *
         *  初始化方块画笔
         */

        mRecPaint = new Paint();
        mRecPaint.setAntiAlias(true);
        mRecPaint.setColor(Color.parseColor("#000000"));
        mRecPaint.setStrokeWidth(1);

        /**
         *
         *  初始化球的初始参数
         *
         */

        ball = new Ball();

        arr = new ArrayList<>();
        arr.add(ball);
        ball.setX( mGameBean.getStartX() + ((mGameBean.getEndX() - mGameBean.getStartX()) / 2));
        ball.setY(mGameBean.getEndY() - (mGameBean.getEndY()  - mGameBean.getStartY()) - 20);
        ball.setSize(10);

        mlevelOne = new LevelOne(mContext);
        Trajectory.init(arr,mlevelOne,mContext,mGameBean);

    }


    public List<Ball> getBall() {
        return arr;
    }

    /**
     * 返回计算好的坐标
     *
     * @return
     */

    public BaseLevel getBaseLevel(Context mContext) {

        return mlevelOne;
    }


    public GameBean getGameBean() {
        return mGameBean;
    }

    public Paint getLinePaint() {
        return mLinePaint;
    }

    public Paint getRecPaint() {
        return mRecPaint;
    }

    /**
     * 0 表示到达左边  1表示到达右边 2表示滑动中
     *
     * @return
     */
    public int isMove() {

        if (mGameBean.getStartX() < 0) {
            return 0;
        } else if (mGameBean.getEndX() > mWidth) {
            return 1;
        }
        return 2;
    }

}

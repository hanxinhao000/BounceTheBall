package com.example.administrator.gameapplication.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.gameapplication.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class BounceTheBallView extends View {
    private Context mContext;
    private BounceTheBallGame mBounceTheBallGame;

    public BounceTheBallView(Context context) {
        super(context);
        initView(context);
    }

    public BounceTheBallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BounceTheBallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化操作类
     */
    private void initView(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 开始入口
     */

    public void startGeam() {
        invalidate();
        mBounceTheBallGame = new BounceTheBallGame();
        mBounceTheBallGame.initData(mContext);
    }

    /**
     * 开始画各种东西
     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
        drawRec(canvas);
        drawDall(canvas);

        if(mStatListener!= null){

            int size = mBounceTheBallGame.getBaseLevel(mContext).getList().size();

            int OSize = 0;
            int SSize = 0;
            for (int i = 0; i < mBounceTheBallGame.getBaseLevel(mContext).getList().size(); i++) {

                if (mBounceTheBallGame.getBaseLevel(mContext).getList().get(i).isDestroy()) {
                    OSize++;
                }else{
                    SSize++;
                }


            }

            mStatListener.stat(size,OSize,SSize);


        }
    }


    /**
     *
     * 画球
     *
     */

    private void drawDall(Canvas canvas){
        for (int i = 0; i < mBounceTheBallGame.getBall().size(); i++) {
            canvas.drawCircle(mBounceTheBallGame.getBall().get(i).getX(),mBounceTheBallGame.getBall().get(i).getY(),mBounceTheBallGame.getBall().get(i).getSize(),mBounceTheBallGame.getLinePaint());
        }



        Trajectory.setBullListener(new Trajectory.BullListener() {
            @Override
            public void move(ArrayList<Ball> bal) {

                MainActivity.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        invalidate();
                    }
                });




            }
        });
    }

    /**
     * 画控制板
     */

    private void drawLine(Canvas canvas) {

        canvas.drawLine(mBounceTheBallGame.getGameBean().getStartX(), mBounceTheBallGame.getGameBean().getStartY(), mBounceTheBallGame.getGameBean().getEndX(), mBounceTheBallGame.getGameBean().getEndY(), mBounceTheBallGame.getLinePaint());

    }

    /**
     * 画
     */
    private void drawRec(Canvas canvas) {

        List<ZKBean> list = mBounceTheBallGame.getBaseLevel(mContext).getList();
        Paint recPaint = mBounceTheBallGame.getRecPaint();

        for (int i = 0; i < list.size(); i++) {

            if(!list.get(i).isDestroy()) {
                canvas.drawRect(list.get(i).getStartX(), list.get(i).getStartY(), list.get(i).getEndX(), list.get(i).getEndY(), recPaint);
            }


        }


    }

    private int startX;

    /**
     * 手势滑动
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                startX = (int) event.getX();
                if(!Trajectory.isLeft ) {

                    Trajectory.start();
                }
                break;
            case MotionEvent.ACTION_MOVE:


                int move = mBounceTheBallGame.isMove();

                int endX = (int) event.getX();

                int midX = startX - endX;
                //表示到达左边
                if (move == 0) {
                    if (midX > 0) {
                        return true;
                    }
                }
                //表示到达右边
                if (move == 1) {
                    if (midX < 0) {
                        return true;
                    }

                }


                mBounceTheBallGame.getGameBean().setStartX(mBounceTheBallGame.getGameBean().getStartX() - midX);
                mBounceTheBallGame.getGameBean().setEndX(mBounceTheBallGame.getGameBean().getEndX() - midX);


                startX = endX;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }


    private StatListener mStatListener;
    public void setStatListener(StatListener statListener){
        this.mStatListener = statListener;

    }


    public static interface StatListener{
        void stat(int totleSize,int OSize,int SSize);
    }
}

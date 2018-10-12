package com.example.administrator.gameapplication.game;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * 小球的运动轨迹
 */
public class Trajectory {

    public static boolean isLeft = false;

    private static BullListener mBullListener;

    private static ArrayList<Ball> mBall;

    private static LevelOne mLevelOne;
    /**
     * 球的速度
     *
     * @param bullListener
     */


    private static Random mRandom;
    private static Context mContext;
    private static GameBean mGameBean;
    private static int dX = 2, dY = 2;
    private static int i;

    public static void setBullListener(BullListener bullListener) {
        mBullListener = bullListener;
    }

    public static void init(ArrayList<Ball> ball, LevelOne levelOne, Context context, GameBean gameBean) {
        mLevelOne = levelOne;
        mBall = ball;
        mContext = context;
        mGameBean = gameBean;
        mRandom = new Random();
        i = mRandom.nextInt(10) + 1;
    }

    public static void start() {


        if (isLeft) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                //是否存活
                while (isLeft) {
                    //开始初始化球的运行轨迹

                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int i1 = 0; i1 < mBall.size(); i1++) {


                        mBall.get(i1).setY(mBall.get(i1).getY() - dY);
                        mBall.get(i1).setX(mBall.get(i1).getX() - dX);
                        mBullListener.move(mBall);

                    }


                    //判断球有没有在挡板上


                    //判断球有没有与方块装上
                    for (int i = 0; i < mLevelOne.getList().size(); i++) {

                        int endX = mLevelOne.getList().get(i).getEndX();
                        int endY = mLevelOne.getList().get(i).getEndY();
                        int startX = mLevelOne.getList().get(i).getStartX();
                        int startY = mLevelOne.getList().get(i).getStartY();


                        for (int i1 = 0; i1 < mBall.size(); i1++) {

                            int x = mBall.get(i1).getX();
                            int y = mBall.get(i1).getY();

                            //表示撞到球了
                            if (x < endX && x > startX && y < endY && y > startY) {
                                ///-------------------------------上下碰撞
                               /* if (y < endY && y > startY) {
                                    if (!mLevelOne.getList().get(i).isDestroy()) {
                                        //被摧毁了
                                        mLevelOne.getList().get(i).setDestroy(true);
                                        dY *= -1;
                                        break;
                                    }

                                }*/

                                //撞在球的右边

                                if (y < endY && y > startY && x > endX - 5 && x < endX + 5) {
                                    if (!mLevelOne.getList().get(i).isDestroy()) {
                                        //被摧毁了
                                        mLevelOne.getList().get(i).setDestroy(true);
                                        dX *= -1;
                                        Log.e("撞击", "run: " + "撞在右边了");

                                    }
                                }

                                //撞在求得下边

                                if (x < endX && x > startX && y < (endY + 5) && y > (endY - 5)) {
                                    if (!mLevelOne.getList().get(i).isDestroy()) {
                                        //被摧毁了
                                        mLevelOne.getList().get(i).setDestroy(true);
                                        dY *= -1;
                                        Log.e("撞击", "run: " + "撞在下边了");
                                    }
                                }

                                //撞在球的左边
                                if (y < endY && y > startY && x > (startX - 5) && x < (startX + 5)) {
                                    if (!mLevelOne.getList().get(i).isDestroy()) {
                                        //被摧毁了
                                        mLevelOne.getList().get(i).setDestroy(true);
                                        dX *= -1;
                                        Log.e("撞击", "run: " + "撞在左边了");
                                    }
                                }

                                //撞在球的上边
                                if (x < endX && x > startX && y > (startY - 5) && y < (startY + 5)) {
                                    if (!mLevelOne.getList().get(i).isDestroy()) {
                                        //被摧毁了
                                        mLevelOne.getList().get(i).setDestroy(true);
                                        dY *= -1;
                                        Log.e("撞击", "run: " + "撞在上边了");
                                    }
                                }
                            }


                        }


                    }


                    //判断边框[不能超出屏幕]
                    for (int i1 = 0; i1 < mBall.size(); i1++) {

                        int x = mBall.get(i1).getX();
                        int y = mBall.get(i1).getY();
                        if (y < 0 || y > mContext.getResources().getDisplayMetrics().heightPixels) {

                            dY *= -1;

                        }

                        if (x < 0 || x > mContext.getResources().getDisplayMetrics().widthPixels) {
                            dX *= -1;
                        }

                        //判断有没有在挡板上
                        if (x > mGameBean.getStartX() && x < mGameBean.getEndX() && y > mGameBean.getStartY() && y < mGameBean.getEndY() + 20) {

                            dY *= -1;
                            // dX *= -1;


                        }


                    }


                }
            }
        }).start();
        Trajectory.isLeft = true;
    }


    public static interface BullListener {

        void move(ArrayList<Ball> arrayList);

    }

}

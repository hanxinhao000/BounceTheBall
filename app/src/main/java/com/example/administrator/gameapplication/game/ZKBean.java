package com.example.administrator.gameapplication.game;

/**
 *
 *
 *
 */
public class ZKBean {


    private int startX;
    private int startY;
    private int endX;
    private int endY;
    //是否显示该方块,表示盖方块是否被摧毁了
    private boolean isDestroy = false;

    //是否表示该方块是石头不可摧毁
    private boolean isNoDestroy = false;

    public boolean isNoDestroy() {
        return isNoDestroy;
    }

    public void setNoDestroy(boolean noDestroy) {
        isNoDestroy = noDestroy;
    }

    public boolean isDestroy() {
        return isDestroy;
    }

    public void setDestroy(boolean destroy) {
        isDestroy = destroy;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }
}

package com.example.administrator.gameapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.gameapplication.game.BounceTheBallView;

public class MainActivity extends AppCompatActivity {

    private BounceTheBallView mBounceTheBallView;

    private TextView stat;
    public static Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBounceTheBallView = findViewById(R.id.pa);
        stat = findViewById(R.id.stat);
        mBounceTheBallView.startGeam();
        mHandler = new Handler();
        mBounceTheBallView.setStatListener(new BounceTheBallView.StatListener() {
            @Override
            public void stat(int totleSize, int OSize, int SSize) {
                stat.setText("总共砖块:"+totleSize+",已打掉砖块:"+OSize+",剩余砖块:" + SSize);
            }
        });
    }
}

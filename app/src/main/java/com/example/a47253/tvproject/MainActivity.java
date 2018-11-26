package com.example.a47253.tvproject;

import android.app.UiModeManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    @BindView(R.id.floder)
    TextView floder;
    @BindView(R.id.stroe)
    TextView stroe;
    @BindView(R.id.manager)
    TextView manager;
    @BindView(R.id.setting)
    TextView setting;
    @BindView(R.id.other)
    TextView other;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ButterKnife.bind(this);
        // 检查是否运行在TV上
        UiModeManager uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
        if (uiModeManager.getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION) {
            Log.d(TAG, "Running on a TV Device");
        } else {
            Log.d(TAG, "Running on a non-TV Device");
        }
        floder.setOnFocusChangeListener(new ViewOnFocusChanageListener());
        stroe.setOnFocusChangeListener(new ViewOnFocusChanageListener());
        setting.setOnFocusChangeListener(new ViewOnFocusChanageListener());
        manager.setOnFocusChangeListener(new ViewOnFocusChanageListener());
        other.setOnFocusChangeListener(new ViewOnFocusChanageListener());
        floder.requestFocus();
        floder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toVideoMainActivityIntent = new Intent();
                toVideoMainActivityIntent.setClass(MainActivity.this, VideoMainActivity.class);
                startActivity(toVideoMainActivityIntent);
            }
        });
    }

    private class ViewOnFocusChanageListener implements OnFocusChangeListener{
        @Override
        public void onFocusChange(View view,boolean hasFocus){
            if (hasFocus) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.setElevation(2f);
                }
                ViewCompat.animate(view)
                        .scaleX(1.17f)
                        .scaleY(1.17f)
                        .translationZ(1)
                        .start();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.setElevation(1f);
                }
                ViewCompat.animate(view)
                        .scaleX(1)
                        .scaleY(1)
                        .translationZ(1)
                        .start();
            }
        }
    }
}

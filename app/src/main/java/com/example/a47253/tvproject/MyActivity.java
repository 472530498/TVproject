package com.example.a47253.tvproject;

import android.app.UiModeManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyActivity extends FragmentActivity {
    public static final String TAG = "DeviceTypeRuntimeCheck";
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
        setContentView(R.layout.my_layout);
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
    }

    private class ViewOnFocusChanageListener implements OnFocusChangeListener{
        @Override
        public void onFocusChange(View view,boolean hasFocus){
            if (hasFocus) {
                view.setElevation(2f);
                ViewCompat.animate(view)
                        .scaleX(1.17f)
                        .scaleY(1.17f)
                        .translationZ(1)
                        .start();
            } else {
                view.setElevation(1f);
                ViewCompat.animate(view)
                        .scaleX(1)
                        .scaleY(1)
                        .translationZ(1)
                        .start();
            }
        }
    }
}

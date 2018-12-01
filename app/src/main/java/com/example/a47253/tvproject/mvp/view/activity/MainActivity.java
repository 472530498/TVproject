package com.example.a47253.tvproject.mvp.view.activity;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

import com.example.a47253.tvproject.R;
import com.example.a47253.tvproject.mvp.presenter.MainPresenter;
import com.example.a47253.tvproject.mvp.view.activity.base.BaseActivity;
import com.example.a47253.tvproject.mvp.view.iview.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
    public static final String TAG = "MainActivity";
    @BindView(R.id.daily_update)
    TextView daily_update;
    @BindView(R.id.tv_play)
    TextView tv_play;
    @BindView(R.id.variety_show)
    TextView variety_show;
    @BindView(R.id.film)
    TextView film;
    @BindView(R.id.more)
    TextView more;

    private MainPresenter mainPresenter;

    @Override
    protected int getContentLayout() {
        return R.layout.main_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        // 检查是否运行在TV上
        UiModeManager uiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);
        if (uiModeManager.getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION) {
            Log.d(TAG, "Running on a TV Device");
        } else {
            Log.d(TAG, "Running on a non-TV Device");
        }
        ButterKnife.bind(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mainPresenter = new MainPresenter(this);
    }

    @Override
    protected void initEvent() {
        daily_update.setOnFocusChangeListener(new ViewOnFocusChanageListener());
        tv_play.setOnFocusChangeListener(new ViewOnFocusChanageListener());
        film.setOnFocusChangeListener(new ViewOnFocusChanageListener());
        variety_show.setOnFocusChangeListener(new ViewOnFocusChanageListener());
        more.setOnFocusChangeListener(new ViewOnFocusChanageListener());
        daily_update.requestFocus();
        daily_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.this == null) {
                    Log.w(TAG,"nullObject MainActivity.this");
                    return;
                }
                else if (VideoMainActivity.class == null) {
                    Log.w(TAG,"nullObject VideoMainActivity.class");
                    return;
                }
                else {
                    Log.w(TAG,"Main,View-Activity 都不为空");
                }
                 mainPresenter.jumpActivity(MainActivity.this,VideoMainActivity.class);
            }
        });
    }

    @Override
    public void jump(Context context, Class<?> tass) {
        Intent intent = new Intent();
        intent.setClass(context, tass);
        startActivity(intent);
    }

    @Override
    public void force(View view, boolean hasFocus) {
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

    private class ViewOnFocusChanageListener implements OnFocusChangeListener{
        @Override
        public void onFocusChange(View view,boolean hasFocus){
            mainPresenter.requestForce(view, hasFocus);
        }
    }
}

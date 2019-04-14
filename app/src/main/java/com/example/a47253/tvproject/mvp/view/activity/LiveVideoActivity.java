package com.example.a47253.tvproject.mvp.view.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

import com.example.a47253.tvproject.R;
import com.example.a47253.tvproject.listener.OnTransitionListener;
import com.example.a47253.tvproject.mvp.presenter.MainPresenter;
import com.example.a47253.tvproject.mvp.view.activity.base.BaseActivity;
import com.example.a47253.tvproject.mvp.view.iview.MainView;
import com.example.a47253.tvproject.video.EmptyControlVideo;
import com.example.a47253.tvproject.video.MyVideo;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LiveVideoActivity extends BaseActivity<MainPresenter> implements MainView {

    public final static String IMG_TRANSITION = "IMG_TRANSITION";
    public final static String TRANSITION = "TRANSITION";

    @BindView(R.id.video_player)
    EmptyControlVideo videoPlayer;

    OrientationUtils orientationUtils;

    private boolean isTransition;

    private Transition transition;

    @Override
    protected int getContentLayout() {
        return R.layout.video_layout_live;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        isTransition = getIntent().getBooleanExtra(TRANSITION, false);
        init();
    }

    @Override
    protected void initEvent() {

    }

    private void init() {
        String url = "http://dlhls.cdn.zhanqi.tv/zqlive/53346_ESoth.m3u8";

        videoPlayer.setUp(url, true, "");

        //过渡动画
        initTransition();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayer.release();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        GSYVideoManager.releaseAllVideos();
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onBackPressed();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
            }, 500);
        }
    }


    private void initTransition() {
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
            ViewCompat.setTransitionName(videoPlayer, IMG_TRANSITION);
            addTransitionListener();
            startPostponedEnterTransition();
        } else {
            videoPlayer.startPlayLogic();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean addTransitionListener() {
        transition = getWindow().getSharedElementEnterTransition();
        if (transition != null) {
            transition.addListener(new OnTransitionListener(){
                @Override
                public void onTransitionEnd(Transition transition) {
                    super.onTransitionEnd(transition);
                    videoPlayer.startPlayLogic();
                    transition.removeListener(this);
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public void jump(Context context, Class<?> tclass, Map map) {

    }

    @Override
    public void force(View view, boolean hasFocus) {

    }

    @Override
    public void showLoginStatus(boolean bool) {

    }
}

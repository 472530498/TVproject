package com.example.a47253.tvproject.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.a47253.tvproject.R;
import com.example.a47253.tvproject.bean.PosterBean;
import com.example.a47253.tvproject.bean.VideoBean;
import com.example.a47253.tvproject.mvp.presenter.MainPresenter;
import com.example.a47253.tvproject.mvp.view.activity.base.BaseActivity;
import com.example.a47253.tvproject.mvp.view.iview.MainView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class GSYVideoPlayer extends BaseActivity<MainPresenter> implements MainView {
    private static final String TAG = "GSYVideoPlayer";

    VideoBean videoBean;

    StandardGSYVideoPlayer videoPlayer;

    OrientationUtils orientationUtils;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_simple_play;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        init();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent i = getIntent();
        Bundle videoData = i.getExtras();
        Log.i(TAG, videoData.getString("posterName"));
        Log.i(TAG, videoData.getString("posterUrl"));
        String posterName = videoData.getString("posterName");
        String posterUrl = videoData.getString("posterUrl");
        PosterBean posterBean = new PosterBean(posterName, posterUrl);
        videoBean = new VideoBean(posterBean, "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4", "测试视频", 0);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void jump(Context context, Class<?> tclass) {

    }

    @Override
    public void force(View view, boolean hasFocus) {

    }

    @Override
    public void showLoginStatus(boolean bool) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }

    private void init() {
        videoPlayer =  (StandardGSYVideoPlayer)findViewById(R.id.video_player);



//        String source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        videoPlayer.setUp(videoBean.getChannelUrl(), true, videoBean.getChannelTitle());

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.xxx1);
        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        videoPlayer.startPlayLogic();
    }

}

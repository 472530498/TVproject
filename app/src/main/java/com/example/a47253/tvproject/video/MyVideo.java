package com.example.a47253.tvproject.video;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import com.example.a47253.tvproject.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;

public class MyVideo extends StandardGSYVideoPlayer {

    private final static String TAG = "--->SampleCoverVideo";

    private Button mButton;

    public MyVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public MyVideo(Context context) {
        super(context);
    }

    public MyVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        Log.i(TAG,"init");
        super.init(context);
        mButton = findViewById(R.id.my_button_2);
    }

    @Override
    public int getLayoutId() {
        Log.i(TAG,"getLayoutId");
        return R.layout.video_layout_cover;
    }

    @Override
    protected void cloneParams(GSYBaseVideoPlayer from, GSYBaseVideoPlayer to) {
        Log.i(TAG,"cloneParams");
        super.cloneParams(from, to);
    }

    @Override
    protected void changeUiToNormal() {
        Log.i(TAG,"changeUiToNormal");
        super.changeUiToNormal();
    }

    @Override
    protected void changeUiToPreparingShow() {
        Log.i(TAG,"changeUiToPreparingShow");
        super.changeUiToPreparingShow();
    }

    @Override
    protected void changeUiToPlayingShow() {
        Log.i(TAG,"changeUiToPlayingShow");
        super.changeUiToPlayingShow();
        mButton.setVisibility(GONE);
    }

    @Override
    protected void changeUiToPauseShow() {
        Log.i(TAG,"changeUiToPauseShow");
        super.changeUiToPauseShow();
        mButton.setVisibility(VISIBLE);
    }

    @Override
    protected void changeUiToPlayingBufferingShow() {
        Log.i(TAG,"changeUiToPlayingBufferingShow");
        super.changeUiToPlayingBufferingShow();
    }

    @Override
    protected void changeUiToCompleteShow() {
        Log.i(TAG,"changeUiToCompleteShow");
        super.changeUiToCompleteShow();
    }

    @Override
    protected void changeUiToError() {
        Log.i(TAG,"changeUiToError");
        super.changeUiToError();
    }
}

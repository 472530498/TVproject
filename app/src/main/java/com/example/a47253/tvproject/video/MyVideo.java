package com.example.a47253.tvproject.video;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.a47253.tvproject.R;
import com.example.a47253.tvproject.mvp.view.activity.MainActivity;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class MyVideo extends StandardGSYVideoPlayer {

    private final static String TAG = "--->SampleCoverVideo";

    private Button mButton;

//    private ListView listView;

    private Context context;

    public MyVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
        this.context = context;
    }

    public MyVideo(Context context) {
        super(context);
        this.context = context;
    }

    public MyVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void init(Context context) {
        Log.i(TAG,"-->init");
        super.init(context);
//        mButton = findViewById(R.id.my_button_2);
//        listView = findViewById(R.id.myList);
//        ArrayList<String> arrayList = new ArrayList<String>();
//        arrayList.add("节目一");
//        arrayList.add("节目二");
//        arrayList.add("中央八台");
//        arrayList.add("中央八台");
//        arrayList.add("中央八台");
//        arrayList.add("中央八台");
//        arrayList.add("中央八台");
//        arrayList.add("中央八台");
//        arrayList.add("中央八台");
//        arrayList.add("中央不知道什么台");
//        int size=arrayList.size();
//        String[] array=new String[size];
//        for(int i=0;i<size;i++){
//            array[i]=(String)arrayList.get(i);
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                context,android.R.layout.simple_list_item_1, array);
//        listView.setAdapter(adapter);
    }

    @Override
    public int getLayoutId() {
        Log.i(TAG,"-->getLayoutId");
        return R.layout.video_layout_cover;
    }

    @Override
    protected void cloneParams(GSYBaseVideoPlayer from, GSYBaseVideoPlayer to) {
        Log.i(TAG,"-->cloneParams");
        super.cloneParams(from, to);
    }

    @Override
    protected void changeUiToNormal() {
        Log.i(TAG,"-->changeUiToNormal");
        super.changeUiToNormal();
    }

    @Override
    protected void changeUiToPreparingShow() {
        Log.i(TAG,"-->changeUiToPreparingShow");
        super.changeUiToPreparingShow();
    }

    @Override
    protected void changeUiToPlayingShow() {
        Log.i(TAG,"-->changeUiToPlayingShow");
        super.changeUiToPlayingShow();
//        setViewShowState(mButton, GONE);
    }

    @Override
    protected void changeUiToPauseShow() {
        Log.i(TAG,"-->changeUiToPauseShow");
        super.changeUiToPauseShow();
        if (mCurrentState == CURRENT_STATE_PAUSE) {
//            mButton.setVisibility(VISIBLE);
//            listView.setVisibility(VISIBLE);
        }   else {
//            mButton.setVisibility(GONE);
        }

    }

    @Override
    protected void changeUiToPlayingBufferingShow() {
        Log.i(TAG,"-->changeUiToPlayingBufferingShow");
        super.changeUiToPlayingBufferingShow();
    }

    @Override
    protected void changeUiToCompleteShow() {
        Log.i(TAG,"-->changeUiToCompleteShow");
        super.changeUiToCompleteShow();
    }

    @Override
    protected void changeUiToError() {
        Log.i(TAG,"-->changeUiToError");
        super.changeUiToError();
    }
    @Override
    protected void onClickUiToggle() {
        Log.i(TAG,"-->onClickUiToggle");
        super.onClickUiToggle();
//        setViewShowState(mButton, VISIBLE);
//        setViewShowState(listView, VISIBLE);
    }

    @Override protected void changeUiToPrepareingClear() {
        Log.i(TAG,"-->changeUiToPrepareingClear");
        super.changeUiToPrepareingClear();
    }

    @Override protected void hideAllWidget () {
        super.hideAllWidget();
//        setViewShowState(mButton, INVISIBLE);
    }
}

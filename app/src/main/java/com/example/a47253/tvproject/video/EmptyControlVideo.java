package com.example.a47253.tvproject.video;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a47253.tvproject.R;
import com.shuyu.gsyvideoplayer.model.GSYVideoModel;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * 无任何控制ui的播放
 * Created by guoshuyu on 2017/8/6.
 */

public class EmptyControlVideo extends StandardGSYVideoPlayer {

    private ListView listView;

    private Context context;

    public EmptyControlVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
        this.context = context;
    }

    public EmptyControlVideo(Context context) {
        super(context);
        this.context = context;
    }

    public EmptyControlVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.empty_control_video;
    }

    @Override
    protected void touchSurfaceMoveFullLogic(float absDeltaX, float absDeltaY) {
        super.touchSurfaceMoveFullLogic(absDeltaX, absDeltaY);
        //不给触摸快进，如果需要，屏蔽下方代码即可
        mChangePosition = false;

        //不给触摸音量，如果需要，屏蔽下方代码即可
        mChangeVolume = false;

        //不给触摸亮度，如果需要，屏蔽下方代码即可
        mBrightness = false;
    }

    @Override
    protected void touchDoubleUp() {
        //super.touchDoubleUp();
        //不需要双击暂停
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        listView = findViewById(R.id.myList);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("节目一");
        arrayList.add("节目二");
        arrayList.add("中央八台");
        arrayList.add("中央八台");
        arrayList.add("中央八台");
        arrayList.add("中央八台");
        arrayList.add("中央八台");
        arrayList.add("中央八台");
        arrayList.add("中央八台");
        arrayList.add("中央不知道什么台");
        int size=arrayList.size();
        String[] array=new String[size];
        for(int i=0;i<size;i++){
            array[i]=(String)arrayList.get(i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (playNext()) {
                    return;
                }
            }
        });
    }

    @Override
    protected void changeUiToPauseShow() {
        super.changeUiToPauseShow();
        if (mCurrentState == CURRENT_STATE_PAUSE) {
            listView.setVisibility(VISIBLE);
        }   else {
        }

    }

    @Override
    protected void onClickUiToggle() {
        super.onClickUiToggle();
        setViewShowState(listView, VISIBLE);
    }

    /**
     * 播放下一集
     *
     * @return true表示还有下一集
     */
    public boolean playNext() {
            setUp("http://123.108.164.110/etv1sb/pld10765/playlist.m3u8",false , null, "title", false);
            startPlayLogic();
            return true;
    }
}

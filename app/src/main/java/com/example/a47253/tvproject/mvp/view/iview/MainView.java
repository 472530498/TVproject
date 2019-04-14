package com.example.a47253.tvproject.mvp.view.iview;

import android.content.Context;
import android.view.View;

import com.example.a47253.tvproject.mvp.view.iview.base.IBaseView;

import java.util.Map;

public interface MainView extends IBaseView {
    void jump(Context context, Class<?> tclass, Map map);
    void force(View view, boolean hasFocus);
    void showLoginStatus(boolean bool);
}

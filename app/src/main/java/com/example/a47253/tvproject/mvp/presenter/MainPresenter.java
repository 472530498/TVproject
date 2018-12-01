package com.example.a47253.tvproject.mvp.presenter;

import android.content.Context;
import android.view.View;

import com.example.a47253.tvproject.mvp.presenter.base.BasePresenter;
import com.example.a47253.tvproject.mvp.view.iview.MainView;

public class MainPresenter extends BasePresenter {
    MainView mainView;

    public MainPresenter ( MainView mainView ) {
        this.mainView = mainView;
    }

    public void checkLogin () {
    }

    public void jumpActivity ( Context context, Class<?> tClass ) {
        mainView.jump(context, tClass);
    }

    public void requestForce (View view, boolean hasFocus) {
        mainView.force(view, hasFocus);
    }
}

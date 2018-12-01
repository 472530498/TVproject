package com.example.a47253.tvproject.mvp.model;

import com.example.a47253.tvproject.mvp.model.imodel.IMainModel;

public class MainModel{
    public void checkLoginStatus (final IMainModel callBack) {
        // 耗时操作 复杂操作 判断操作等等
        boolean flag = false;
        callBack.checkLogin(flag);
    }
}

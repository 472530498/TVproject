package com.example.a47253.tvproject.mvp.model;

import com.example.a47253.tvproject.mvp.model.imodel.IMainModel;

public class MainModel implements IMainModel{
    public boolean checkLogin (final IMainModel callBack) {
        return callBack.checkLogin(false);
    }

    @Override
    public boolean checkLogin(boolean res) {
        return res;
    }
}

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
import android.widget.Toast;

import com.example.a47253.tvproject.R;
import com.example.a47253.tvproject.bean.PosterBean;
import com.example.a47253.tvproject.bean.ResponseBean;
import com.example.a47253.tvproject.bean.VideoBean;
import com.example.a47253.tvproject.mvp.presenter.MainPresenter;
import com.example.a47253.tvproject.mvp.view.activity.base.BaseActivity;
import com.example.a47253.tvproject.mvp.view.iview.MainView;
import com.example.a47253.tvproject.retrofit.VideoRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.mob.MobSDK;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.register)
    TextView register;

    Gson gson = new Gson();

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
        MobSDK.init(this);
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
                    Log.w(TAG, "nullObject MainActivity.this");
                    return;
                } else if (VideoMainActivity.class == null) {
                    Log.w(TAG, "nullObject VideoMainActivity.class");
                    return;
                } else {
                    Log.w(TAG, "Main,View-Activity 都不为空");
                }
                Log.i("", v.toString());
                Map map = new HashMap();
                map.put("video_zone_tags_name", "每日更新");
                mainPresenter.jumpActivity(MainActivity.this, VideoMainActivity.class, map);
            }
        });
        film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = new HashMap();
                map.put("video_zone_tags_name", "电影");
                mainPresenter.jumpActivity(MainActivity.this, VideoMainActivity.class, map);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.sendCode(MainActivity.this, login.getId());
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.sendCode(MainActivity.this, register.getId());
            }
        });
        mainPresenter.checkLogin();
    }

    @Override
    public void jump(Context context, Class<?> tass, Map map) {
        Intent intent = new Intent();
        intent.setClass(context, tass);
        Bundle bundle = new Bundle();
        bundle.putString("video_zone_tags_name", (String) map.get("video_zone_tags_name"));
        intent.putExtras(bundle);
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

    @Override
    public void showLoginStatus(boolean bool) {
        if (!bool) {
            Log.i(TAG, "bool: " + bool);
            Toast.makeText(MainActivity.this, "没有登陆", Toast.LENGTH_LONG).show();
        }
    }

    private class ViewOnFocusChanageListener implements OnFocusChangeListener {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            mainPresenter.requestForce(view, hasFocus);
        }
    }

    public void sendCode(Context context, int ViewId) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                Log.i(TAG, data + "");
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country"); // 国家代码，如“86”
                    String phone = (String) phoneMap.get("phone"); // 手机号码，如“13800138000”
                    // TODO 利用国家代码和手机号码进行后续的操作
                    Log.i(TAG, country);
                    Log.i(TAG, phone);
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .build();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.1.8:7001/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okHttpClient)
                            .build();
                    VideoRequest service = retrofit.create(VideoRequest.class);
                    JSONObject insertData = new JSONObject();
                    JSONObject json = new JSONObject();
                    try {
                        insertData.put("insertData", json);
                        json.put("user_rid", "13660590625");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (ViewId == login.getId()) {
                        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
                        service.login(body).enqueue(new Callback<ResponseBean>() {
                            @Override
                            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {
                                Log.i(TAG, response.body().getResultMsg());
                                Log.i(TAG, response.body().getResultCode());
                                Log.i(TAG, gson.toJson(response.body().getData()));
                                Log.i(TAG, response.body().getData().getClass().getName());
                                if ((response.body().getResultCode().toString().equals("1"))) {
                                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBean> call, Throwable t) {
                                System.out.print("onFailure response.body():");
                            }
                        });
                    } else if (ViewId == register.getId()) {
                        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), insertData.toString());
                        service.register(body).enqueue(new Callback<ResponseBean>() {
                            @Override
                            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {
                                Log.i(TAG, response.body().getResultMsg());
                                Log.i(TAG, response.body().getResultCode());
                                Log.i(TAG, gson.toJson(response.body().getData()));
                                Log.i(TAG, response.body().getData().getClass().getName());
                                if ((response.body().getResultCode().toString().equals("1"))) {
                                    Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBean> call, Throwable t) {
                                System.out.print("onFailure response.body():");
                            }
                        });
                    }
                } else {
                    // TODO 处理错误的结果
                    Log.i(TAG, result + "");
                }
            }
        });
        page.show(context);
    }
}

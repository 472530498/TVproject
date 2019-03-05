package com.example.a47253.tvproject.utils;

public class NetWorkManager {
//    private static NetWorkManager mInstance;
//    private static Retrofit retrofit;
//    private static volatile VideoRequest request = null;
//
//    public static NetWorkManager getInstance() {
//        if (mInstance == null) {
//            synchronized (NetWorkManager.class) {
//                if (mInstance == null) {
//                    mInstance = new NetWorkManager();
//                }
//            }
//        }
//        return mInstance;
//    }
//
//    /**
//     * 初始化必要对象和参数
//     */
//    public void init() {
//        // 初始化okhttp
//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//
//        // 初始化Retrofit
//        retrofit = new Retrofit.Builder()
//                .client(client)
//                .baseUrl(VideoRequest.HOST)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }
//
//    public static VideoRequest getRequest() {
//        if (request == null) {
//            synchronized (VideoRequest.class) {
//                request = retrofit.create(VideoRequest.class);
//            }
//        }
//        return request;
//    }
}

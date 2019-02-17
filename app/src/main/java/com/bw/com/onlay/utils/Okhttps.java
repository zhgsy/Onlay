package com.bw.com.onlay.utils;

import android.net.Network;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.bw.com.onlay.api.MyApi;
import com.bw.com.onlay.api.UserApi;
import com.bw.com.onlay.bean.ShopBean;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Okhttps {

    private Interceptor interceptor;
    private static volatile Okhttps instance;
    private final OkHttpClient client;
    private Handler handler=new Handler();
    private FormBody.Builder body;

    private Interceptor getAppInterceptor(){
        interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.e("+++++++++++++++++", "intercept: 拦截前" );
                Response response = chain.proceed(request);
                Log.e("+++++++++++++++++", "intercept: 拦截后" );
                return response;
            }
        };
        return interceptor;
    }
    public  static  Okhttps getinstance(){
                if (instance==null){
                    synchronized (Okhttps.class){
                        instance=new Okhttps();
                    }
       }
                return instance;
    }

    public Okhttps(){
        File file=new File(Environment.getExternalStorageDirectory(),"xiuxiuha");
        client = new OkHttpClient.Builder()
                .readTimeout(5000,TimeUnit.SECONDS)
                .writeTimeout(5000,TimeUnit.SECONDS)
                .addInterceptor(getAppInterceptor())
                .cache(new Cache(file,1024*2))
                .build();
    }
    public void doGet(String url, final NetWork netWork){
        Request request=new Request.Builder()
                .url(url)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netWork.Success(result);
                    }
                });

            }
        });

    }

    public void doPost(String url, Map<String,String> mpars, final NetWork netwk){
        body = new FormBody.Builder();
        for (String key:mpars.keySet()){
            body.add(key,mpars.get(key));
        }
        Request request=new Request.Builder()
                .url(url)
                .post(body.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netwk.Success(result);
                    }
                });
            }
        });
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserApi userApi = retrofit.create(UserApi.class);

        /*retrofit2.Call<ShopBean> shop = userApi.shop(serchname,page,count);*/
    }

    public interface  NetWork{
        public void Success(String dataView);
        public void Faild();
    }

}

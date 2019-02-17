package com.bw.com.onlay.frag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.com.onlay.R;
import com.bw.com.onlay.api.MyApi;
import com.bw.com.onlay.api.UserApi;
import com.bw.com.onlay.bean.QuanziBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BlankFragment2 extends Fragment {

    int page=1;
    int count=8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserApi userApi = retrofit.create(UserApi.class);
        Call<QuanziBean> quan = userApi.quan(page, count);
        quan.enqueue(new Callback<QuanziBean>() {
            @Override
            public void onResponse(Call<QuanziBean> call, Response<QuanziBean> response) {
                QuanziBean bean = response.body();
                List<QuanziBean.ResultBean> list = bean.getResult();

            }

            @Override
            public void onFailure(Call<QuanziBean> call, Throwable t) {

            }
        });

        return  view;
    }

}

package com.bw.com.onlay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bw.com.onlay.api.MyApi;
import com.bw.com.onlay.api.UserApi;
import com.bw.com.onlay.bean.XiangBean;
import com.bw.com.onlay.xiang.presenter.HomePresenterInter;
import com.bw.com.onlay.xiang.view.IHomeView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class XiangActivity extends AppCompatActivity implements IHomeView {

    private String cid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        EventBus.getDefault().register(this);
        Toast.makeText(XiangActivity.this,cid,Toast.LENGTH_SHORT).show();

    }

    /**
     * 粘性事件
     * @param id
     */
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void getId(String id){
        cid=id;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getDataView(String dataview) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserApi userApi = retrofit.create(UserApi.class);
        Call<com.bw.com.onlay.adapter.XiangBean> xiang = userApi.Xiang(Integer.valueOf(cid));
        xiang.enqueue(new Callback<com.bw.com.onlay.adapter.XiangBean>() {
            @Override
            public void onResponse(Call<com.bw.com.onlay.adapter.XiangBean> call, Response<com.bw.com.onlay.adapter.XiangBean> response) {
                com.bw.com.onlay.adapter.XiangBean bean = response.body();
                
            }

            @Override
            public void onFailure(Call<com.bw.com.onlay.adapter.XiangBean> call, Throwable t) {

            }
        });


    }
}

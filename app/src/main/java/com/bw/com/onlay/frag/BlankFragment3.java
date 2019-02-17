package com.bw.com.onlay.frag;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.com.onlay.MainActivity;
import com.bw.com.onlay.R;
import com.bw.com.onlay.ShowActivity;
import com.bw.com.onlay.XiangActivity;
import com.bw.com.onlay.adapter.Shopadapter;
import com.bw.com.onlay.adapter.XiangBean;
import com.bw.com.onlay.api.MyApi;
import com.bw.com.onlay.api.UserApi;
import com.bw.com.onlay.bean.ShopBean;
import com.bw.com.onlay.xiang.view.IHomeView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BlankFragment3 extends Fragment  {


    private RecyclerView recy;
    private EditText edname;
    int page=1;
    int count=5;
     String serchname;
     String name="鞋";
    private Button butn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blank_fragment3, container, false);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserApi userApi = retrofit.create(UserApi.class);

        Call<ShopBean> shop = userApi.shop(name,page,count);
        shop.enqueue(new Callback<ShopBean>() {
            @Override
            public void onResponse(Call<ShopBean> call, Response<ShopBean> response) {
                ShopBean bean = response.body();
                List<ShopBean.ResultBean> list = bean.getResult();
                if (bean.getStatus().equals("0000")) {
                    //吐司内容
                    Toast.makeText(getActivity(), bean.getMessage(), Toast.LENGTH_LONG).show();
                    Shopadapter shopadapter = new Shopadapter(getActivity(), list);
                    recy.setAdapter(shopadapter);
                    shopadapter.notifyDataSetChanged();
                    shopadapter.setItemClick(new Shopadapter.SetOnItemClickListener() {
                        @Override
                        public void downClick(View view, String id) {
                              EventBus.getDefault().postSticky(id);
                            Toast.makeText(getActivity(),"点我",Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(getActivity(),XiangActivity.class));
                        }
                    });
                }else{
                    Toast.makeText(getActivity(), bean.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ShopBean> call, Throwable t) {
            }
        });
        createinit(view);

        butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serchname = edname.getText().toString();
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(MyApi.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                UserApi userApi = retrofit.create(UserApi.class);

                Call<ShopBean> shop = userApi.shop(serchname,page,count);
                shop.enqueue(new Callback<ShopBean>() {
                    @Override
                    public void onResponse(Call<ShopBean> call, Response<ShopBean> response) {
                        ShopBean bean = response.body();
                        List<ShopBean.ResultBean> list = bean.getResult();
                        if (bean.getStatus().equals("0000")) {
                            //吐司内容
                            Toast.makeText(getActivity(), bean.getMessage(), Toast.LENGTH_LONG).show();
                            Shopadapter shopadapter = new Shopadapter(getActivity(), list);
                            recy.setAdapter(shopadapter);
                            shopadapter.notifyDataSetChanged();

                        }else{
                            Toast.makeText(getActivity(), bean.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ShopBean> call, Throwable t) {
                    }
                });
            }
        });
        return  view;
    }

    public void createinit(View view){
        recy = view.findViewById(R.id.shop_recy);
        edname = view.findViewById(R.id.shop_ed);
        butn = view.findViewById(R.id.shop_butn);
        recy.setLayoutManager(new GridLayoutManager(getActivity(),2));

    }



}

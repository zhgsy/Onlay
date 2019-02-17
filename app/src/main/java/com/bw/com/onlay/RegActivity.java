package com.bw.com.onlay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.com.onlay.api.MyApi;
import com.bw.com.onlay.api.UserApi;
import com.bw.com.onlay.bean.RegBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegActivity extends AppCompatActivity {

    @BindView(R.id.login_user_et)
    EditText reg_phone;
    @BindView(R.id.login_pwd_et)
    EditText reg_pwd;
    private String re_phone;
    private String re_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
    }

    //按钮
    public void reg_butn(View view) {
        re_phone = reg_phone.getText().toString();
        re_pass = reg_pwd.getText().toString();
        //第一步创建retrofit管理器
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //第二步创建事件接口
        UserApi userApi = retrofit.create(UserApi.class);
        final Call<RegBean> reg = userApi.reg(re_phone, re_pass);
        reg.enqueue(new Callback<RegBean>() {
            @Override
            public void onResponse(Call<RegBean> call, Response<RegBean> response) {
                RegBean bean = response.body();
                if (bean.getStatus().equals("0000")){
                    Toast.makeText(RegActivity.this,bean.getMessage(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(RegActivity.this,bean.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegBean> call, Throwable t) {

            }
        });

    }

    //返回页面
    public void reg_fan(View view) {
        startActivity(new Intent(RegActivity.this,MainActivity.class));
    }
}

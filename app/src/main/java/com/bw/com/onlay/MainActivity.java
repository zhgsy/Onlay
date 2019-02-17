package com.bw.com.onlay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.com.onlay.api.MyApi;
import com.bw.com.onlay.api.UserApi;
import com.bw.com.onlay.bean.LoginBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.login_user_et)
    EditText name;
    @BindView(R.id.login_pwd_et)
    EditText pass;
    private UserApi userApi;
    private String pho;
    private String pas;
    private Call<LoginBean> login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    //登录
    public void login_butn(View view) {
        //拿取输入框里面的值
        pho = name.getText().toString();
        pas = pass.getText().toString();
        //第一步创建retrofit管理器
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//数据解析式
                .build();
        //创建事件接口
        userApi = retrofit.create(UserApi.class);
        //第三步
        login = userApi.login(pho, pas);
        login.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                LoginBean bean = response.body();
                if (bean.getStatus().equals("0000")){
                    Toast.makeText(MainActivity.this,bean.getMessage(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,ShowActivity.class));
                }else{
                    Toast.makeText(MainActivity.this,bean.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
            
            }
        });

    }

    //注册
    public void reg_butn(View view) {
        Toast.makeText(MainActivity.this,"跳转到注册页面",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,RegActivity.class));
    }
}

package com.bw.com.onlay.api;

import com.bw.com.onlay.adapter.XiangBean;
import com.bw.com.onlay.bean.LoginBean;
import com.bw.com.onlay.bean.QuanziBean;
import com.bw.com.onlay.bean.RegBean;
import com.bw.com.onlay.bean.ShopBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    @POST("small/user/v1/login")
    @FormUrlEncoded
    Call<LoginBean> login(@Field("phone") String moble,@Field("pwd") String pass);
    @POST("small/user/v1/register")
    @FormUrlEncoded
    Call<RegBean> reg(@Field("phone") String reg_phone,@Field("pwd") String reg_pass);
    @GET("small/circle/v1/findCircleList")
    Call<QuanziBean> quan(@Query("page") int page,@Query("count") int count);
    @GET("small/commodity/v1/findCommodityByKeyword")
    Call<ShopBean> shop(@Query("keyword") String name,@Query("page") int page,@Query("count") int count);
    @GET("small/commodity/v1/findCommodityDetailsById")
    Call<XiangBean> Xiang(@Query("commodityId") int count);

}

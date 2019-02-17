package com.bw.com.onlay.xiang.model;

import com.bw.com.onlay.utils.Okhttps;

public class HomeModelInter implements IHomeModelInter{

    @Override
    public void getData(String url, final ModelInter modelInter) {
        Okhttps.getinstance().doGet(url, new Okhttps.NetWork() {
            @Override
            public void Success(String dataView) {

                modelInter.Success(dataView);
            }

            @Override
            public void Faild() {

            }
        });
    }
}

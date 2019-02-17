package com.bw.com.onlay.xiang.presenter;

import com.bw.com.onlay.XiangActivity;
import com.bw.com.onlay.api.MyApi;
import com.bw.com.onlay.api.UserApi;
import com.bw.com.onlay.frag.BlankFragment3;
import com.bw.com.onlay.xiang.model.HomeModelInter;
import com.bw.com.onlay.xiang.model.IHomeModelInter;

public class HomePresenterInter implements IHomePresenterInter{
    XiangActivity mview;
    HomeModelInter homeModelInter=new HomeModelInter();
    public HomePresenterInter(XiangActivity mview, HomeModelInter homeModelInter) {
        this.mview = mview;
        this.homeModelInter = homeModelInter;
    }

    @Override
    public void getPresenter(String cid) {
            homeModelInter.getData(MyApi.XIANG, new IHomeModelInter.ModelInter() {
                @Override
                public void Success(String data) {
                    mview.getDataView(data);
                }

                @Override
                public void Faild() {

                }
            });
    }
}

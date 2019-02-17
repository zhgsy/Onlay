package com.bw.com.onlay.xiang.model;

import java.util.Map;

public interface IHomeModelInter {
    public void  getData(String url,ModelInter modelInter);
        public interface ModelInter{
        public void Success(String data);
        public void Faild();
       }
}

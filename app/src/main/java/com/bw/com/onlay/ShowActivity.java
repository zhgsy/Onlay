package com.bw.com.onlay;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bw.com.onlay.frag.BlankFragment;
import com.bw.com.onlay.frag.BlankFragment2;
import com.bw.com.onlay.frag.BlankFragment3;
import com.bw.com.onlay.frag.BlankFragment4;
import com.bw.com.onlay.frag.BlankFragment5;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.fragment)
    BottomTabBar fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        fragment.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(8)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED,Color.DKGRAY)
                .addTabItem("商品",R.drawable.ic_home,BlankFragment.class)
                .addTabItem("圈子",R.drawable.ic_home,BlankFragment2.class)
                .addTabItem("购物",R.drawable.ic_home,BlankFragment3.class)
                .addTabItem("社交",R.drawable.ic_home,BlankFragment4.class)
                .addTabItem("我的",R.drawable.ic_home,BlankFragment5.class)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });


    }
}

package com.bw.com.onlay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.com.onlay.R;
import com.bw.com.onlay.bean.ShopBean;

import java.util.List;

public class Shopadapter extends RecyclerView.Adapter<Shopadapter.ViewHolder> {

    Context context;
    List<ShopBean.ResultBean> list;
    public Shopadapter(Context context, List<ShopBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  view=View.inflate(context, R.layout.shop_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(list.get(i).getMasterPic()).into(viewHolder.image);
            viewHolder.text.setText(list.get(i).getCommodityName());
            viewHolder.price.setText(list.get(i).getMasterPic());
            viewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setOnItemClickListener.downClick(v,list.get(i).getCommodityId());
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView text;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.shop_img);
            text = itemView.findViewById(R.id.shop_text);
            price = itemView.findViewById(R.id.shop_price);
        }
    }

    //创建接口     实现条目点击事件
    public interface SetOnItemClickListener{
        void downClick(View view,String id);
    }
    SetOnItemClickListener setOnItemClickListener;
    public void setItemClick(SetOnItemClickListener setOnItemClickListener){
        this.setOnItemClickListener = setOnItemClickListener;
    }
}

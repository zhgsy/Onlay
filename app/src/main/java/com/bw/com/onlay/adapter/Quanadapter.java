package com.bw.com.onlay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.com.onlay.R;
import com.bw.com.onlay.bean.QuanziBean;

import java.util.List;

public class Quanadapter extends RecyclerView.Adapter<Quanadapter.ViewHolder> {
    List<QuanziBean.ResultBean> list;
    Context context;
    public Quanadapter(List<QuanziBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       /* View view=View.inflate(context,R.layout.)*/
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

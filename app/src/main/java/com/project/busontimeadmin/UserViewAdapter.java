package com.project.busontimeadmin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class UserViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    List<DriverO> driverOS = new ArrayList<>();
    UserViewAdapter(Context context){
        this.context = context;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        MyHolder(View view){
            super(view);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_driver_view, viewGroup, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return driverOS.size();
    }

    public void update(List<DriverO> driverOS){
        this.driverOS = driverOS;
        notifyDataSetChanged();
    }
}

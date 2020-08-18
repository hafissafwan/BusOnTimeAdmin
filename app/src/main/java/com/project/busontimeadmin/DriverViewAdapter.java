package com.project.busontimeadmin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DriverViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    List<DriverO> driverOS = new ArrayList<>();
    DriverViewAdapter(Context context){
        this.context = context;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name,license,routeD;
        Button button;
        EditText route;
        MyHolder(View view){
            super(view);
            name = view.findViewById(R.id.name);
            route = view.findViewById(R.id.routeed);
            license = view.findViewById(R.id.license);
            button = view.findViewById(R.id.approveB);
            routeD = view.findViewById(R.id.route);
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
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        final MyHolder myHolder = (MyHolder) viewHolder;
        if (driverOS.get(i).getUserName()!=null&&!driverOS.get(i).getUserName().equals("")){
            myHolder.name.setText(driverOS.get(i).getUserName());
        }
        if (driverOS.get(i).getLicenseNumber()!=null&&!driverOS.get(i).getLicenseNumber().equals("")){
            myHolder.license.setText(driverOS.get(i).getLicenseNumber());
        }
        if (driverOS.get(i).getRouteName()!=null&&!driverOS.get(i).getRouteName().equals("")){
            myHolder.routeD.setText(driverOS.get(i).getRouteName());
        }
        if (!driverOS.get(i).isApproved()){
            myHolder.button.setVisibility(View.VISIBLE);
            myHolder.route.setVisibility(View.VISIBLE);
            myHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!myHolder.route.getText().toString().equals("")) {
                        driverOS.get(viewHolder.getAdapterPosition()).setApproved(true);
                        driverOS.get(viewHolder.getAdapterPosition()).setRouteName(myHolder.route.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Db/drivers").child(driverOS.get(viewHolder.getAdapterPosition()).getUserGuid()).setValue(driverOS.get(viewHolder.getAdapterPosition()));
                        myHolder.routeD.setText(myHolder.route.getText().toString());
                        myHolder.route.setVisibility(View.GONE);
                        myHolder.button.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(context,"Please enter route for this driver",0).show();
                    }
                }
            });
        }else {
            myHolder.route.setVisibility(View.GONE);
            myHolder.button.setVisibility(View.GONE);
        }
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

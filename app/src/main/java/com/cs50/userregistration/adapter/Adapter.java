package com.cs50.userregistration.adapter;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cs50.userregistration.model.DataHolder;
import com.cs50.userregistration.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Adapter extends FirebaseRecyclerAdapter<DataHolder,Adapter.myHolder> {


    public Adapter(@NonNull FirebaseRecyclerOptions<DataHolder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myHolder holder, int position, @NonNull DataHolder model) {

        holder.header.setText(model.getProductName());
        holder.price.setText(Integer.toString(model.getPrice()));
        Glide.with(holder.profile.getContext()).load(model.getImage()).into(holder.profile);

    }



    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater= LayoutInflater.from(parent.getContext());
        View view=layoutinflater.inflate(R.layout.singlerow,parent,false);

        //for scroable horizontal grid layout
        view.getLayoutParams().width = (int) (getScreenWidth(layoutinflater.getContext()) / 2);
        //eha sammaw ani look getScreenWidth wala function

        return new myHolder(view);
    }

    public class myHolder extends RecyclerView.ViewHolder{
        ImageView profile;
        TextView header,price,description;
        Button order;
        public myHolder(@NonNull View itemView) {
            super(itemView);

            profile=itemView.findViewById(R.id.itemprofile);
            header=itemView.findViewById(R.id.itemheader);
            price=itemView.findViewById(R.id.itemprice);


        }
    }
    public int getScreenWidth(Context context) {

        WindowManager wm = (WindowManager)  context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;
    }
}

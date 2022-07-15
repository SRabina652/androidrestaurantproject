package com.cs50.userregistration.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cs50.userregistration.model.DataHolder;
import com.cs50.userregistration.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ParentAdapter extends FirebaseRecyclerAdapter<DataHolder,ParentAdapter.myHolder> {


    public ParentAdapter(@NonNull FirebaseRecyclerOptions<DataHolder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myHolder holder, int position, @NonNull DataHolder model) {
//        holder.Titleheader.setText();

    }



    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater= LayoutInflater.from(parent.getContext());
        View view=layoutinflater.inflate(R.layout.single_layout,parent,false);


        return new myHolder(view);
    }

    public class myHolder extends RecyclerView.ViewHolder{
        TextView Titleheader;
        RecyclerView rv_child;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            Titleheader=itemView.findViewById(R.id.parent_title);
            rv_child=itemView.findViewById(R.id.parent_rcv);
        }
    }

}

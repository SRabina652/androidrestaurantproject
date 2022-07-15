package com.cs50.userregistration.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.cs50.userregistration.pages.InsertDetails;
import com.cs50.userregistration.R;
import com.cs50.userregistration.adapter.Adapter;
import com.cs50.userregistration.model.DataHolder;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    RecyclerView recview;
    Adapter adapter;
    DataHolder holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //recycler view
        recview= findViewById(R.id.recyfood);

//        GridLayoutManager gmanager= new GridLayoutManager(this,3);
        GridLayoutManager gmanager=new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        recview.setLayoutManager(gmanager);

        FirebaseRecyclerOptions<DataHolder> options =
                new FirebaseRecyclerOptions.Builder<DataHolder>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Food"), DataHolder.class)
                        .build();
        adapter=new Adapter(options);
        recview.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        ImageSlider slider=findViewById(R.id.imageslider);

        List<SlideModel> images=new ArrayList<>();
        images.add(new SlideModel("https://images.pexels.com/photos/704569/pexels-photo-704569.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"));
        images.add(new SlideModel("https://www.sbs.com.au/food/sites/sbs.com.au.food/files/styles/full/public/gettyimages-sharing-food.jpg?itok=o_y4Rbqy&mtime=1644882121"));
        images.add(new SlideModel("https://img.traveltriangle.com/blog/wp-content/uploads/2018/06/belgian-waffles-cover-image.jpg"));
        images.add(new SlideModel("https://img.traveltriangle.com/blog/wp-content/uploads/2018/06/belgian-fries-400x267.jpg"));
        images.add(new SlideModel(R.drawable.one));

        slider.setImageList(images,true);
        Button addtext=findViewById(R.id.AddFood);

        addtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adddata= new Intent(Dashboard.this, InsertDetails.class);
                startActivity(adddata);
            }
        });
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}
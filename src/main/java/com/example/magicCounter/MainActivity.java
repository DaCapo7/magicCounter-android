package com.example.magicCounter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private RecyclerView courseRV;
    private FloatingActionButton add_card;
    // Arraylist for storing data
    private ArrayList<CourseModel> courseModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);

        //hide status and navigation bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);



        Log.d("hi","hello");



            setContentView(R.layout.activity_main);
            courseRV = findViewById(R.id.player_recyler);

            add_card = findViewById(R.id.add_card);


            // here we have created new array list and added data to it.
            courseModelArrayList = new ArrayList<>();
            courseModelArrayList.add(new CourseModel("20", "Player 0", "0", "Life", 0));
            courseModelArrayList.add(new CourseModel("20", "Player 1", "0", "Life", 1));


            // we are initializing our adapter class and passing our arraylist to it.
            CourseAdapter courseAdapter = new CourseAdapter(this, courseModelArrayList);

            // below line is for setting a layout manager for our recycler view.
            // here we are creating vertical list so we will provide orientation as vertical
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

            // in below two lines we are setting layoutmanager and adapter to our recycler view.
            courseRV.setLayoutManager(linearLayoutManager);
            courseRV.setAdapter(courseAdapter);

            add_card.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    courseModelArrayList.add(0,new CourseModel("20", "Player "+Integer.toString(courseModelArrayList.size()), "0", "Life", (courseModelArrayList.size())));
                    courseAdapter.notifyDataSetChanged();
                }
            });







    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list", courseModelArrayList);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        //hide status and navigation bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        Log.d("hi","hello");



        setContentView(R.layout.activity_main);
        courseRV = findViewById(R.id.player_recyler);

        add_card = findViewById(R.id.add_card);


        // here we have created new array list and added data to it.
        courseModelArrayList = savedInstanceState.getParcelableArrayList("list");
        //.add(new CourseModel("20", "Player 0", "0", "Life"));
        //courseModelArrayList.add(new CourseModel("20", "Player 1", "0", "Life"));


        // we are initializing our adapter class and passing our arraylist to it.
        CourseAdapter courseAdapter = new CourseAdapter(this, courseModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(courseAdapter);

        add_card.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                courseModelArrayList.add(0,new CourseModel("20", "Player "+Integer.toString(courseModelArrayList.size()), "0", "Life",(courseModelArrayList.size())));
                courseAdapter.notifyDataSetChanged();
            }
        });

    }
}
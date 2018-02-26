package com.mlorenzo.vitrasapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyCardAdapter ca;
    List<StopInformation> stops = new ArrayList();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addButton = findViewById(R.id.fab);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stops.add(new StopInformation());
                ca.notifyDataSetChanged();
            }
        });

        RecyclerView recList = findViewById(R.id.cardList);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        createList(3);
        ca = new MyCardAdapter(stops);
        recList.setAdapter(ca);
    }

    private void createList(int size) {
        for (int i=1; i <= size; i++) {
            stops.add(new StopInformation());
        }
    }
}

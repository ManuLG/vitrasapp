package com.mlorenzo.vitrasapp;

import android.content.Intent;
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
    List<StopInformation> stops = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addButton = findViewById(R.id.fab);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Intent intent = new Intent(view.getContext(), QrCodeScanner.class);
                    startActivityForResult(intent, 0);

                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("result");
            }
        }
    } // Close onActivityResult

    private void createList(int size) {
        for (int i=1; i <= size; i++) {
            stops.add(new StopInformation());
        }
    }
}

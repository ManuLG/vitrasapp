package com.mlorenzo.vitrasapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyCardAdapter ca;
    List<StopInformation> stops = new ArrayList<>();

    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addButton = findViewById(R.id.fab);

        sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE);

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

        //createList(3);

        Log.d("LISTA", sharedPref.getString("stops_fav", "error"));

        ca = new MyCardAdapter(stops);
        recList.setAdapter(ca);

        updateData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                updateData();
            }
        }
    } // Close onActivityResult

    private void updateData() {
        stops.clear();

        try {
            JSONArray stops_array = new JSONArray(sharedPref.getString("stops_fav", "error"));
            Gson gson = new Gson();

            for (int i = 0; i < stops_array.length(); i++) {
                Log.d("item", stops_array.get(i).toString());
                stops.add(gson.fromJson(stops_array.get(i).toString(), StopInformation.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ca.notifyDataSetChanged();
    }


}

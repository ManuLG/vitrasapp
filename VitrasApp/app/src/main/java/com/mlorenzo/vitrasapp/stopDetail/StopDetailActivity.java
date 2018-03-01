package com.mlorenzo.vitrasapp.stopDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mlorenzo.vitrasapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by mlorenzo on 26/02/2018.
 */

public class StopDetailActivity extends AppCompatActivity {

    MyEstimatesAdapter ca;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_detail);

        Intent intent= getIntent();
        String response = intent.getStringExtra("response");

        try {
            JSONObject json = new JSONObject(response);

            RecyclerView recList = findViewById(R.id.cardList);
            recList.setHasFixedSize(true);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recList.setLayoutManager(llm);

            ca = new MyEstimatesAdapter(parseResponse(json));
            recList.setAdapter(ca);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private List<EstimateInformation> parseResponse(JSONObject response) {
        List<EstimateInformation> list = new ArrayList<>();

        try {
            JSONArray array = response.getJSONArray("buses");

            for (int i = 0; i < array.length(); i++) {

                JSONObject estimate = (JSONObject) array.get(i);
                list.add(new EstimateInformation(estimate.getString("line"), estimate.getString("route"), estimate.getString("minutes")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}

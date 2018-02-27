package com.mlorenzo.vitrasapp.stopDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.mlorenzo.vitrasapp.MyCardAdapter;
import com.mlorenzo.vitrasapp.R;
import com.mlorenzo.vitrasapp.StopInformation;

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
    List<EstimateInformation> infor = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_detail);

        Intent intent= getIntent();

        TextView text = findViewById(R.id.text);

        String debug = intent.getStringExtra("response");
        Log.d("TEST", debug);

        try {
            JSONObject json = new JSONObject(debug);

            RecyclerView recList = findViewById(R.id.cardList);
            recList.setHasFixedSize(true);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recList.setLayoutManager(llm);

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            recList.setLayoutManager(mLayoutManager);
//            recList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            recList.setItemAnimator(new DefaultItemAnimator());

            ca = new MyEstimatesAdapter(parseResponse(json));
            recList.setAdapter(ca);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private List parseResponse(JSONObject response) {
        List<EstimateInformation> list = new ArrayList<EstimateInformation>();

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

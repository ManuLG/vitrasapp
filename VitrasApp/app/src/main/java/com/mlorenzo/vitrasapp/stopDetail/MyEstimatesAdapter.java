package com.mlorenzo.vitrasapp.stopDetail;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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

public class MyEstimatesAdapter extends RecyclerView.Adapter<com.mlorenzo.vitrasapp.stopDetail.MyEstimatesAdapter.EstimatesViewHolder> {

    private List<EstimateInformation> estimateList;

    public MyEstimatesAdapter(List<EstimateInformation> estimateList) {
        this.estimateList = estimateList;
    }

    @Override
    public int getItemCount() {
        return estimateList.size();
    }

    @Override
    public void onBindViewHolder(com.mlorenzo.vitrasapp.stopDetail.MyEstimatesAdapter.EstimatesViewHolder estimateViewHolder, int i) {
        EstimateInformation ci = estimateList.get(i);
        estimateViewHolder.line.setText(ci.line);
        estimateViewHolder.remaining_time.setText(ci.remaining_time);
    }

    @Override
    public EstimatesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.estimation_card, viewGroup, false);


        return new EstimatesViewHolder(itemView);
    }

    public static class EstimatesViewHolder extends RecyclerView.ViewHolder {
        protected TextView line;
        protected TextView remaining_time;

        public EstimatesViewHolder(View v) {
            super(v);
            line =  (TextView) v.findViewById(R.id.line_number);
            remaining_time =  (TextView) v.findViewById(R.id.remaining_time);
            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //testRequest(v.getContext());
                }
            });
        }
    }
}


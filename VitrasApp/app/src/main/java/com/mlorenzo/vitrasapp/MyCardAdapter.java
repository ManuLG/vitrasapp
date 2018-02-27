package com.mlorenzo.vitrasapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

/**
 *  Created by mlorenzo on 26/02/2018.
 */

public class MyCardAdapter extends RecyclerView.Adapter<MyCardAdapter.ContactViewHolder> {

    private List<StopInformation> contactList;

    public MyCardAdapter(List<StopInformation> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        StopInformation ci = contactList.get(i);
        contactViewHolder.vName.setText(ci.name);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;

        public ContactViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);
            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Log.d("DDD", vName.getText().toString());

                    testRequest(v.getContext());
                }
            });
        }

        public void testRequest(final Context context) {

            String url = "http://manulg.ddns.net:5000/stops/6660/estimates";

            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Request", response.toString());

                            Intent goToNextActivity = new Intent(context, StopDetailActivity.class);
                            goToNextActivity.putExtra("name", response.toString());
                            context.startActivity(goToNextActivity);
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO Auto-generated method stub

                        }
                    });
            Volley.newRequestQueue(context).add(jsObjRequest);
        }
    }
}

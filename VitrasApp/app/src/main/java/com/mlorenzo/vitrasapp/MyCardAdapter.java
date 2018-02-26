package com.mlorenzo.vitrasapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
                    Intent goToNextActivity = new Intent(v.getContext(), StopDetailActivity.class);
                    goToNextActivity.putExtra("name", vName.getText().toString());
                    v.getContext().startActivity(goToNextActivity);
                }
            });
        }
    }
}

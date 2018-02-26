package com.mlorenzo.vitrasapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 *  Created by mlorenzo on 26/02/2018.
 */

public class StopDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_detail);

        Intent intent= getIntent();

        TextView text = findViewById(R.id.text);
        text.setText(intent.getStringExtra("name"));
    }
}

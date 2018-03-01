package com.mlorenzo.vitrasapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.zxing.Result;
import com.mlorenzo.vitrasapp.stopDetail.StopDetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 *  Created by mlorenzo on 01/03/2018.
 */

public class QrCodeScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    Matcher matcher;
    String regex = "";

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // Programmatically initialize the scanner view
        mScannerView = new ZXingScannerView(this);
        // Set the scanner view as the content view
        setContentView(mScannerView);

        Matcher matcher;

        // BA
        String regex = "^([0-9]{8})/([^0-9]+)$";


    }

    @Override
    public void onResume() {
        super.onResume();
        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);
        // Start camera on resume
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        // Prints scan results
        Log.d("result", rawResult.getText());

        String scan_result = rawResult.getText();

        matcher = Pattern.compile(regex).matcher(scan_result);

        if (matcher.find()) {

            // Coincide con la expresion regular

        }

        testRequest(this.getBaseContext(), rawResult.getText());

    }

    public void testRequest(final Context context, final String rawResult) {

        String url = "http://manulg.ddns.net:5000/stops/6660";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("RequestQR", response.toString());

                        SharedPreferences sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        Gson gson = new Gson();
                        JSONArray array = null;
                        try {
                            Log.e("Antes", sharedPref.getString("stops_fav", ""));

                            if (sharedPref.getString("stops_fav", "").equals("")) {
                                array = new JSONArray();
                            } else {
                                array = new JSONArray(sharedPref.getString("stops_fav", ""));
                            }

                            array.put(gson.toJson(new StopInformation(response.getString("name"))));
                            editor.putString("stops_fav", array.toString());
                            editor.apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent();
                        intent.putExtra("result", rawResult);
                        setResult(RESULT_OK, intent);
                        finish();

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


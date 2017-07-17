package com.example.nicco.inspectionReviewManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class LocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // ADDRESS
        final AutoCompleteTextView address = (AutoCompleteTextView) findViewById(R.id.autocompleteAddress);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        address.setAdapter(adapter);
        address.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus) {
                    Log.d("LOCATION", "ON FOCUS CHANGE TEST");
                    String text = address.getText().toString();
                    if(text.equals("")) {
                        Log.d("LOCATION", "\"\" VALUE STRING");
                    }
                }
            }
        });

        // LOCATION
        final AutoCompleteTextView location = (AutoCompleteTextView) findViewById(R.id.autoCompleteCityProv);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        location.setAdapter(adapter);
        location.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus) {
                    Log.d("LOCATION", "ON FOCUS CHANGE TEST");
                    String text = location.getText().toString();
                    if(text.equals("")) {
                        Log.d("LOCATION", "\"\" VALUE STRING");
                    }
                }
            }
        });

        // PROJECT NUMBER
        final AutoCompleteTextView projectNumber = (AutoCompleteTextView) findViewById(R.id.autoCompleteCityProv);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        projectNumber.setAdapter(adapter);

        projectNumber.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus) {
                    Log.d("LOCATION", "ON FOCUS CHANGE TEST");
                    String text = projectNumber.getText().toString();
                    if(text.equals("")) {
                        Log.d("LOCATION", "\"\" VALUE STRING");
                    }
                }
            }
        });
    }
}

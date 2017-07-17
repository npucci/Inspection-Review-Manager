package com.example.nicco.inspectionReviewManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.*;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.Map;

public class LocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        final Model model = (Model) getApplicationContext();

        // ADDRESS
        final AutoCompleteTextView address = (AutoCompleteTextView) findViewById(R.id.autocompleteAddress);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item, getResources().getStringArray(R.array.cityProv));
        address.setAdapter(adapter);
        address.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus) {
                    model.updateAddress(address.getText().toString());
                }
            }
        });
        address.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                Log.v("TEST", parent.getItemAtPosition(position).toString());
                model.updateAddress(parent.getItemAtPosition(position).toString());
            }
        });
        if(model.getAddress() != null && !model.getAddress().equals("")) address.setText(model.getAddress());

        // CITY/PROV
        final AutoCompleteTextView cityProv = (AutoCompleteTextView) findViewById(R.id.autoCompleteCityProv);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        cityProv.setAdapter(adapter);
        cityProv.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus) {
                    model.updateCityProv(cityProv.getText().toString());
                }
            }
        });
        if(model.getCityProv() != null && !model.getCityProv().equals("")) cityProv.setText(model.getCityProv());

        // PROJECT NUMBER
        final AutoCompleteTextView projectNumber = (AutoCompleteTextView) findViewById(R.id.autoCompleteCityProv);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        projectNumber.setAdapter(adapter);

        projectNumber.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus) {
                    model.updateProjectNumber(projectNumber.getText().toString());
                }
            }
        });
        if(model.getProjectNumber() != null && !model.getProjectNumber().equals("")) projectNumber.setText(model.getProjectNumber());
    }
}

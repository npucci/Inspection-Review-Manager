package com.example.nicco.inspectionReviewManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;

public class ProjectActivity extends AppCompatActivity {
    private Model model;
    private AutoCompleteTextView address;
    private AutoCompleteTextView cityProv;
    private AutoCompleteTextView projectNumber;
    private AutoCompleteTextView developer;
    private AutoCompleteTextView contractor;
    private CheckBox footings;
    private CheckBox foundationWalls;
    private CheckBox sheathing;
    private CheckBox framing;
    private CheckBox other;
    private AutoCompleteTextView otherInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        init();

        // ADDRESS
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item, getResources().getStringArray(R.array.cityProv));
        address.setAdapter(adapter);
        if(model.getAddress() != null && !model.getAddress().equals("")) address.setText(model.getAddress());

        // CITY/PROV
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        cityProv.setAdapter(adapter);
        if(model.getCityProv() != null && !model.getCityProv().equals("")) cityProv.setText(model.getCityProv());

        // PROJECT NUMBER
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        projectNumber.setAdapter(adapter);
        if(model.getProjectNumber() != null && !model.getProjectNumber().equals("")) projectNumber.setText(model.getProjectNumber());

        // DEVELOPER
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        developer.setAdapter(adapter);
        if(model.getDeveloper() != null && !model.getDeveloper().equals("")) developer.setText(model.getDeveloper());

        // CONTRACTOR
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        contractor.setAdapter(adapter);
        if(model.getContractor() != null && !model.getContractor().equals("")) contractor.setText(model.getContractor());

        // FOOTINGS
        footings.setChecked(model.getFootings());

        // FOUNDATION WALLS
        foundationWalls.setChecked(model.getFoundationWalls());

        // SHEATHING
        sheathing.setChecked(model.getSheathing());

        // FRAMING
        framing.setChecked(model.getFraming());

        // OTHER
        other.setChecked(model.getOther());
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(other.isChecked()) otherInput.setEnabled(true);
                else otherInput.setEnabled(false);
            }
        });

        // OTHER INPUT
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        otherInput.setAdapter(adapter);
        if(other.isChecked()) {
            if(model.getOtherInput() != null && !model.getOtherInput().equals("")) otherInput.setText(model.getOtherInput());
        }
        else otherInput.setEnabled(false);
    }

    // initialize all referenced fields
    private void init() {
        model = (Model) getApplicationContext();
        address = (AutoCompleteTextView) findViewById(R.id.autocompleteAddress);
        cityProv = (AutoCompleteTextView) findViewById(R.id.autoCompleteCityProv);
        projectNumber = (AutoCompleteTextView) findViewById(R.id.autoCompleteProjectNumber);
        developer = (AutoCompleteTextView) findViewById(R.id.autoCompleteDeveloper);
        contractor = (AutoCompleteTextView) findViewById(R.id.autoCompleteContractor);
        footings = (CheckBox) findViewById(R.id.footings);
        foundationWalls = (CheckBox) findViewById(R.id.foundation_walls);
        sheathing = (CheckBox) findViewById(R.id.sheathing);
        framing = (CheckBox) findViewById(R.id.framing);
        other = (CheckBox) findViewById(R.id.other);
        otherInput = (AutoCompleteTextView) findViewById(R.id.autoCompleteOther);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ADDRESS
        model.updateAddress(address.getText().toString());

        // CITY/PROV
        model.updateCityProv(cityProv.getText().toString());

        // PROJECT NUMBER
        model.updateProjectNumber(projectNumber.getText().toString());

        // DEVELOPER
        model.updateDeveloper(developer.getText().toString());

        // CONTRACTOR
        model.updateContractor(contractor.getText().toString());

        // FOOTINGS
        if(footings.isChecked()) model.updateFootings(Model.SpecialValues.YES);
        else model.updateFootings(Model.SpecialValues.NO);
        Log.v("PUCCI", "footings = " + footings.isChecked());

        // FOUNDATION WALLS
        if(foundationWalls.isChecked()) model.updateFoundationWalls(Model.SpecialValues.YES);
        else model.updateFoundationWalls(Model.SpecialValues.NO);

        // SHEATHING
        if(sheathing.isChecked()) model.updateSheathing(Model.SpecialValues.YES);
        else model.updateSheathing(Model.SpecialValues.NO);

        // FRAMING
        if(framing.isChecked()) model.updateFraming(Model.SpecialValues.YES);
        else model.updateFraming(Model.SpecialValues.NO);

        // OTHER
        if(other.isChecked()) model.updateOther(Model.SpecialValues.YES);
        else model.updateOther(Model.SpecialValues.NO);

        // OTHER
        model.updateOtherInput(otherInput.getText().toString());
    }
}

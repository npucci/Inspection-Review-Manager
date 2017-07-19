package com.example.nicco.inspectionReviewManager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.TextView;

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
    private TextView descriptionTextView;
    private AutoCompleteTextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        init();

        // ADDRESS
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item, getResources().getStringArray(R.array.cityProv));
        address.setAdapter(adapter);
        if(model.getAddress() != null) address.setText(model.getAddress());

        // CITY/PROV
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        cityProv.setAdapter(adapter);
        if(model.getCityProv() != null) cityProv.setText(model.getCityProv());

        // PROJECT NUMBER
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        projectNumber.setAdapter(adapter);
        if(model.getProjectNumber() != null) projectNumber.setText(model.getProjectNumber());

        // DEVELOPER
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        developer.setAdapter(adapter);
        if(model.getDeveloper() != null) developer.setText(model.getDeveloper());

        // CONTRACTOR
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        contractor.setAdapter(adapter);
        if(model.getContractor() != null) contractor.setText(model.getContractor());

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
                if(other.isChecked()) {
                    descriptionTextView.setVisibility(View.VISIBLE);
                    description.setVisibility(View.VISIBLE);
                    description.setEnabled(true);
                    other.setTextColor(Color.RED);
                    descriptionTextView.setTextColor(Color.RED);
                } else {
                    descriptionTextView.setVisibility(View.INVISIBLE);
                    description.setVisibility(View.INVISIBLE);
                    description.setEnabled(false);
                    other.setTextColor(Color.BLACK);
                    descriptionTextView.setTextColor(Color.BLACK);
                }
            }
        });

        // DESCRIPTION
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        description.setAdapter(adapter);
        if(other.isChecked()) {
            descriptionTextView.setVisibility(View.VISIBLE);
            other.setTextColor(Color.RED);
            descriptionTextView.setTextColor(Color.RED);
            description.setVisibility(View.VISIBLE);
            if(model.getDescription() != null && !model.getDescription().equals("")) description.setText(model.getDescription());
        }
        else {
            descriptionTextView.setVisibility(View.INVISIBLE);
            description.setVisibility(View.INVISIBLE);
            other.setTextColor(Color.BLACK);
            descriptionTextView.setTextColor(Color.BLACK);
            description.setEnabled(false);
        }
    }

    // initialize all referenced fields
    private void init() {
        model = (Model) getApplicationContext();
        address = (AutoCompleteTextView) findViewById(R.id.autocompleteAddress);
        cityProv = (AutoCompleteTextView) findViewById(R.id.autoCompleteCityProv);
        projectNumber = (AutoCompleteTextView) findViewById(R.id.autoCompleteProjectNumber);
        developer = (AutoCompleteTextView) findViewById(R.id.autoCompleteDeveloper);
        contractor = (AutoCompleteTextView) findViewById(R.id.autoCompleteContractor);
        footings = (CheckBox) findViewById(R.id.checkBoxFootings);
        foundationWalls = (CheckBox) findViewById(R.id.checkBoxFoundationWalls);
        sheathing = (CheckBox) findViewById(R.id.checkBoxSheathing);
        framing = (CheckBox) findViewById(R.id.checkBoxFraming);
        other = (CheckBox) findViewById(R.id.checkBoxOther);
        descriptionTextView = (TextView) findViewById(R.id.textViewDescription);
        descriptionTextView.setTextColor(Color.BLACK);
        description = (AutoCompleteTextView) findViewById(R.id.autoCompleteDescription);
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
        if(footings.isChecked()) model.updateFootings(Model.SpecialValue.YES);
        else model.updateFootings(Model.SpecialValue.NO);

        // FOUNDATION WALLS
        if(foundationWalls.isChecked()) model.updateFoundationWalls(Model.SpecialValue.YES);
        else model.updateFoundationWalls(Model.SpecialValue.NO);

        // SHEATHING
        if(sheathing.isChecked()) model.updateSheathing(Model.SpecialValue.YES);
        else model.updateSheathing(Model.SpecialValue.NO);

        // FRAMING
        if(framing.isChecked()) model.updateFraming(Model.SpecialValue.YES);
        else model.updateFraming(Model.SpecialValue.NO);

        // OTHER
        if(other.isChecked()) model.updateOther(Model.SpecialValue.YES);
        else model.updateOther(Model.SpecialValue.NO);

        // DESCRIPTION
        model.updateDescription(description.getText().toString());
    }
}

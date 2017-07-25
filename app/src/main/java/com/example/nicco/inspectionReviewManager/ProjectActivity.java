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

        String value = model.getValue(Model.Keys.ADDRESS);
        if(value != null) address.setText(value);

        // CITY/PROV
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        cityProv.setAdapter(adapter);

        value = model.getValue(Model.Keys.CITY_PROV);
        if(value != null) cityProv.setText(value);

        // PROJECT NUMBER
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        projectNumber.setAdapter(adapter);

        value = model.getValue(Model.Keys.PROJECT_NUMBER);
        if(value != null) projectNumber.setText(value);

        // DEVELOPER
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        developer.setAdapter(adapter);

        value = model.getValue(Model.Keys.DEVELOPER);
        if(value != null) developer.setText(value);

        // CONTRACTOR
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        contractor.setAdapter(adapter);

        value = model.getValue(Model.Keys.CONTRACTOR);
        if(value != null) contractor.setText(value);

        // FOOTINGS
        footings.setChecked(model.isChecked(Model.Keys.FOOTINGS));

        // FOUNDATION WALLS
        foundationWalls.setChecked(model.isChecked(Model.Keys.FOUNDATION_WALLS));

        // SHEATHING
        sheathing.setChecked(model.isChecked(Model.Keys.SHEATHING));

        // FRAMING
        framing.setChecked(model.isChecked(Model.Keys.FRAMING));

        // OTHER
        other.setChecked(model.isChecked(Model.Keys.OTHER));
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

            value = model.getValue(Model.Keys.DESCRIPTION);
            if(model.validValue(value)) description.setText(value);
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
    public void onPause() {
        super.onPause();

        // ADDRESS
        model.updateValue(Model.Keys.ADDRESS, address.getText().toString());

        // CITY/PROV
        model.updateValue(Model.Keys.CITY_PROV, cityProv.getText().toString());

        // PROJECT NUMBER
        model.updateValue(Model.Keys.PROJECT_NUMBER, projectNumber.getText().toString());

        // DEVELOPER
        model.updateValue(Model.Keys.DEVELOPER, developer.getText().toString());

        // CONTRACTOR
        model.updateValue(Model.Keys.CONTRACTOR, contractor.getText().toString());

        // FOOTINGS
        if(footings.isChecked()) model.updateValue(Model.Keys.FOOTINGS, Model.SpecialValue.YES.toString());
        else model.updateValue(Model.Keys.FOOTINGS, Model.SpecialValue.NO.toString());

        // FOUNDATION WALLS
        if(foundationWalls.isChecked()) model.updateValue(Model.Keys.FOUNDATION_WALLS, Model.SpecialValue.YES.toString());
        else model.updateValue(Model.Keys.FOUNDATION_WALLS, Model.SpecialValue.NO.toString());

        // SHEATHING
        if(sheathing.isChecked()) model.updateValue(Model.Keys.SHEATHING, Model.SpecialValue.YES.toString());
        else model.updateValue(Model.Keys.SHEATHING, Model.SpecialValue.NO.toString());

        // FRAMING
        if(framing.isChecked()) model.updateValue(Model.Keys.FRAMING, Model.SpecialValue.YES.toString());
        else model.updateValue(Model.Keys.FRAMING, Model.SpecialValue.NO.toString());

        // OTHER
        if(other.isChecked()) model.updateValue(Model.Keys.OTHER, Model.SpecialValue.YES.toString());
        else model.updateValue(Model.Keys.OTHER, Model.SpecialValue.NO.toString());

        // DESCRIPTION
        model.updateValue(Model.Keys.DESCRIPTION, description.getText().toString());
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}

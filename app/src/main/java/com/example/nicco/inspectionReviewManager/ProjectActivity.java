package com.example.nicco.inspectionReviewManager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.TextView;

public class ProjectActivity extends AppCompatActivity {
    private Model model;
    private AutoCompleteTextView address;
    private AutoCompleteTextView city;
    private AutoCompleteTextView province;
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
                android.R.layout.simple_selectable_list_item,
                model.queryDatabase(DatabaseWriter.DatabaseColumn.ADDRESS, null, null));
        address.setAdapter(adapter);

        String value = model.getValue(DatabaseWriter.DatabaseColumn.ADDRESS);
        if(value != null) address.setText(value);

        // CITY
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.combineArrays(
                    model.queryDatabase(DatabaseWriter.DatabaseColumn.CITY, null, null),
                    getResources().getStringArray(R.array.cities)));

        city.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.DatabaseColumn.CITY);
        if(value != null) city.setText(value);

        // PROVINCE
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.combineArrays(
                        model.queryDatabase(DatabaseWriter.DatabaseColumn.PROVINCE, null, null),
                        getResources().getStringArray(R.array.provinces)));
        province.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.DatabaseColumn.PROVINCE);
        if(value != null) province.setText(value);

        // PROJECT NUMBER
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.DatabaseColumn.PROJECT_NUMBER, null, null));
        projectNumber.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.DatabaseColumn.PROJECT_NUMBER);
        if(value != null) projectNumber.setText(value);

        // DEVELOPER
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.DatabaseColumn.DEVELOPER, null, null));
        developer.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.DatabaseColumn.DEVELOPER);
        if(value != null) developer.setText(value);

        // CONTRACTOR
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.DatabaseColumn.CONTRACTOR, null, null));
        contractor.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.DatabaseColumn.CONTRACTOR);
        if(value != null) contractor.setText(value);

        // FOOTINGS
        footings.setChecked(model.isChecked(DatabaseWriter.DatabaseColumn.FOOTINGS_REVIEW));

        // FOUNDATION WALLS
        foundationWalls.setChecked(model.isChecked(DatabaseWriter.DatabaseColumn.FOUNDATION_WALLS_REVIEW));

        // SHEATHING
        sheathing.setChecked(model.isChecked(DatabaseWriter.DatabaseColumn.SHEATHING_REVIEW));

        // FRAMING
        framing.setChecked(model.isChecked(DatabaseWriter.DatabaseColumn.FRAMING_REVIEW));

        // OTHER
        other.setChecked(model.isChecked(DatabaseWriter.DatabaseColumn.OTHER_REVIEW));
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
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.DatabaseColumn.OTHER_REVIEW_DESCRIPTION, null, null));
        description.setAdapter(adapter);
        if(other.isChecked()) {
            descriptionTextView.setVisibility(View.VISIBLE);
            other.setTextColor(Color.RED);
            descriptionTextView.setTextColor(Color.RED);
            description.setVisibility(View.VISIBLE);

            value = model.getValue(DatabaseWriter.DatabaseColumn.OTHER_REVIEW_DESCRIPTION);
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
        city = (AutoCompleteTextView) findViewById(R.id.autoCompleteCity);
        province = (AutoCompleteTextView) findViewById(R.id.autoCompleteProvince);
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
        model.updateValue(DatabaseWriter.DatabaseColumn.ADDRESS, address.getText().toString());

        // CITY
        model.updateValue(DatabaseWriter.DatabaseColumn.CITY, city.getText().toString());

        // PROVINCE
        model.updateValue(DatabaseWriter.DatabaseColumn.PROVINCE, province.getText().toString());

        // PROJECT NUMBER
        model.updateValue(DatabaseWriter.DatabaseColumn.PROJECT_NUMBER, projectNumber.getText().toString());

        // DEVELOPER
        model.updateValue(DatabaseWriter.DatabaseColumn.DEVELOPER, developer.getText().toString());

        // CONTRACTOR
        model.updateValue(DatabaseWriter.DatabaseColumn.CONTRACTOR, contractor.getText().toString());

        // FOOTINGS
        if(footings.isChecked()) model.updateValue(DatabaseWriter.DatabaseColumn.FOOTINGS_REVIEW, Model.SpecialValue.YES.toString());
        else model.updateValue(DatabaseWriter.DatabaseColumn.FOOTINGS_REVIEW, Model.SpecialValue.NO.toString());

        // FOUNDATION WALLS
        if(foundationWalls.isChecked()) model.updateValue(DatabaseWriter.DatabaseColumn.FOUNDATION_WALLS_REVIEW, Model.SpecialValue.YES.toString());
        else model.updateValue(DatabaseWriter.DatabaseColumn.FOUNDATION_WALLS_REVIEW, Model.SpecialValue.NO.toString());

        // SHEATHING
        if(sheathing.isChecked()) model.updateValue(DatabaseWriter.DatabaseColumn.SHEATHING_REVIEW, Model.SpecialValue.YES.toString());
        else model.updateValue(DatabaseWriter.DatabaseColumn.SHEATHING_REVIEW, Model.SpecialValue.NO.toString());

        // FRAMING
        if(framing.isChecked()) model.updateValue(DatabaseWriter.DatabaseColumn.FRAMING_REVIEW, Model.SpecialValue.YES.toString());
        else model.updateValue(DatabaseWriter.DatabaseColumn.FRAMING_REVIEW, Model.SpecialValue.NO.toString());

        // OTHER
        if(other.isChecked()) model.updateValue(DatabaseWriter.DatabaseColumn.OTHER_REVIEW, Model.SpecialValue.YES.toString());
        else model.updateValue(DatabaseWriter.DatabaseColumn.OTHER_REVIEW, Model.SpecialValue.NO.toString());

        // DESCRIPTION
        model.updateValue(DatabaseWriter.DatabaseColumn.OTHER_REVIEW_DESCRIPTION, description.getText().toString());
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}

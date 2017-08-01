package com.example.nicco.inspectionReviewManager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
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
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.ADDRESS, null, null));
        address.setAdapter(adapter);

        String value = model.getValue(DatabaseWriter.UIComponentInputValue.ADDRESS);
        if(value != null) address.setText(value);
        address.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                autoFill(address);
                return false;
            }
        });
        address.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autoFill(address);
            }
        });

        // CITY
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.combineArrays(
                    model.queryDatabase(DatabaseWriter.UIComponentInputValue.CITY, null, null),
                    getResources().getStringArray(R.array.cities)));

        city.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.UIComponentInputValue.CITY);
        if(value != null) city.setText(value);
        city.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                autoFill(city);
                return false;
            }
        });
        city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autoFill(city);
            }
        });

        // PROVINCE
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.combineArrays(
                        model.queryDatabase(DatabaseWriter.UIComponentInputValue.PROVINCE, null, null),
                        getResources().getStringArray(R.array.provinces)));
        province.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.UIComponentInputValue.PROVINCE);
        if(value != null) province.setText(value);
        province.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                autoFill(province);
                return false;            }
        });
        province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autoFill(province);
            }
        });

        // PROJECT NUMBER
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER, null, null));
        projectNumber.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER);
        if(value != null) projectNumber.setText(value);
        projectNumber.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                autoFill(projectNumber);
                return false;
            }
        });
        projectNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autoFill(projectNumber);
            }
        });

        // DEVELOPER
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.DEVELOPER, null, null));
        developer.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.UIComponentInputValue.DEVELOPER);
        if(value != null) developer.setText(value);
        developer.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                autoFill(developer);
                return false;
            }
        });
        developer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autoFill(developer);
            }
        });

        // CONTRACTOR
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.CONTRACTOR, null, null));
        contractor.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.UIComponentInputValue.CONTRACTOR);
        if(value != null) contractor.setText(value);
        contractor.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                autoFill(contractor);
                return false;
            }
        });
        contractor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autoFill(contractor);
            }
        });

        // FOOTINGS
        footings.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.FOOTINGS_REVIEW));

        // FOUNDATION WALLS
        foundationWalls.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.FOUNDATION_WALLS_REVIEW));

        // SHEATHING
        sheathing.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.SHEATHING_REVIEW));

        // FRAMING
        framing.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.FRAMING_REVIEW));

        // OTHER
        other.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.OTHER_REVIEW));
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
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.OTHER_REVIEW_DESCRIPTION, null, null));
        description.setAdapter(adapter);
        if(other.isChecked()) {
            descriptionTextView.setVisibility(View.VISIBLE);
            other.setTextColor(Color.RED);
            descriptionTextView.setTextColor(Color.RED);
            description.setVisibility(View.VISIBLE);

            value = model.getValue(DatabaseWriter.UIComponentInputValue.OTHER_REVIEW_DESCRIPTION);
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
        model.updateValue(DatabaseWriter.UIComponentInputValue.ADDRESS, address.getText().toString());

        // CITY
        model.updateValue(DatabaseWriter.UIComponentInputValue.CITY, city.getText().toString());

        // PROVINCE
        model.updateValue(DatabaseWriter.UIComponentInputValue.PROVINCE, province.getText().toString());

        // PROJECT NUMBER
        model.updateValue(DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER, projectNumber.getText().toString());

        // DEVELOPER
        model.updateValue(DatabaseWriter.UIComponentInputValue.DEVELOPER, developer.getText().toString());

        // CONTRACTOR
        model.updateValue(DatabaseWriter.UIComponentInputValue.CONTRACTOR, contractor.getText().toString());

        // FOOTINGS
        if(footings.isChecked()) model.updateValue(DatabaseWriter.UIComponentInputValue.FOOTINGS_REVIEW, Model.SpecialValue.YES.toString());
        else model.updateValue(DatabaseWriter.UIComponentInputValue.FOOTINGS_REVIEW, Model.SpecialValue.NO.toString());

        // FOUNDATION WALLS
        if(foundationWalls.isChecked()) model.updateValue(DatabaseWriter.UIComponentInputValue.FOUNDATION_WALLS_REVIEW, Model.SpecialValue.YES.toString());
        else model.updateValue(DatabaseWriter.UIComponentInputValue.FOUNDATION_WALLS_REVIEW, Model.SpecialValue.NO.toString());

        // SHEATHING
        if(sheathing.isChecked()) model.updateValue(DatabaseWriter.UIComponentInputValue.SHEATHING_REVIEW, Model.SpecialValue.YES.toString());
        else model.updateValue(DatabaseWriter.UIComponentInputValue.SHEATHING_REVIEW, Model.SpecialValue.NO.toString());

        // FRAMING
        if(framing.isChecked()) model.updateValue(DatabaseWriter.UIComponentInputValue.FRAMING_REVIEW, Model.SpecialValue.YES.toString());
        else model.updateValue(DatabaseWriter.UIComponentInputValue.FRAMING_REVIEW, Model.SpecialValue.NO.toString());

        // OTHER
        if(other.isChecked()) model.updateValue(DatabaseWriter.UIComponentInputValue.OTHER_REVIEW, Model.SpecialValue.YES.toString());
        else model.updateValue(DatabaseWriter.UIComponentInputValue.OTHER_REVIEW, Model.SpecialValue.NO.toString());

        // DESCRIPTION
        model.updateValue(DatabaseWriter.UIComponentInputValue.OTHER_REVIEW_DESCRIPTION, description.getText().toString());
    }

    private void autoFill(Object uiComponent) {
        String whereClause = "";
        String[] whereArgs = null;
        String[] queryResult = null;

        if(uiComponent != city && uiComponent != province && uiComponent != projectNumber &&
                uiComponent != developer && uiComponent != contractor) {
            whereClause = DatabaseWriter.UIComponentInputValue.ADDRESS.getValue() + " = ?";
            whereArgs = new String[]{address.getText().toString()};
            queryResult = model.queryDatabase(DatabaseWriter.UIComponentInputValue.CITY, whereClause, whereArgs);
            if (queryResult == null || queryResult.length == 0) return;
            city.setText(queryResult[0]);
        }

        if(uiComponent != province && uiComponent != projectNumber &&
                uiComponent != developer && uiComponent != contractor) {
            whereClause = DatabaseWriter.UIComponentInputValue.ADDRESS.getValue() + " = ? AND " +
                    DatabaseWriter.UIComponentInputValue.CITY.getValue() + " = ?";
            whereArgs = new String[]{address.getText().toString(), city.getText().toString()};
            queryResult = model.queryDatabase(DatabaseWriter.UIComponentInputValue.PROVINCE, whereClause, whereArgs);
            if (queryResult == null || queryResult.length == 0) return;
            province.setText(queryResult[0]);
        }

        if(uiComponent != projectNumber &&
                uiComponent != developer && uiComponent != contractor) {
            whereClause = DatabaseWriter.UIComponentInputValue.ADDRESS.getValue() + " = ? AND " +
                    DatabaseWriter.UIComponentInputValue.CITY.getValue() + " = ? AND " +
                    DatabaseWriter.UIComponentInputValue.PROVINCE.getValue() + " = ?";
            whereArgs = new String[]{address.getText().toString(), city.getText().toString(), province.getText().toString()};
            queryResult = model.queryDatabase(DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER, whereClause, whereArgs);
            if (queryResult == null || queryResult.length == 0) return;
            projectNumber.setText(queryResult[0]);
        }

        if(uiComponent != developer && uiComponent != contractor) {
            whereClause = DatabaseWriter.UIComponentInputValue.ADDRESS.getValue() + " = ? AND " +
                    DatabaseWriter.UIComponentInputValue.CITY.getValue() + " = ? AND " +
                    DatabaseWriter.UIComponentInputValue.PROVINCE.getValue() + " = ? AND " +
                    DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER.getValue() + " = ?";
            whereArgs = new String[]{address.getText().toString(), city.getText().toString(),
                    province.getText().toString(), projectNumber.getText().toString()};
            queryResult = model.queryDatabase(DatabaseWriter.UIComponentInputValue.DEVELOPER, whereClause, whereArgs);
            if (queryResult == null || queryResult.length == 0) return;
            developer.setText(queryResult[0]);
        }
        if(uiComponent != contractor) {
            whereClause = DatabaseWriter.UIComponentInputValue.ADDRESS.getValue() + " = ? AND " +
                    DatabaseWriter.UIComponentInputValue.CITY.getValue() + " = ? AND " +
                    DatabaseWriter.UIComponentInputValue.PROVINCE.getValue() + " = ? AND " +
                    DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER.getValue() + " = ? AND " +
                    DatabaseWriter.UIComponentInputValue.DEVELOPER.getValue() + " = ?";
            whereArgs = new String[]{address.getText().toString(), city.getText().toString(),
                    province.getText().toString(), projectNumber.getText().toString(), developer.getText().toString()};
            queryResult = model.queryDatabase(DatabaseWriter.UIComponentInputValue.CONTRACTOR, whereClause, whereArgs);
            if (queryResult == null || queryResult.length == 0) return;
            contractor.setText(queryResult[0]);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}

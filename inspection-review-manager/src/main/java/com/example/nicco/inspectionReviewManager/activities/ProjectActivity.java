package com.example.nicco.inspectionReviewManager.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.nicco.inspectionReviewManager.customDatatypes.AutoFillActivity;
import com.example.nicco.inspectionReviewManager.customDatatypes.DatabaseWriter;
import com.example.nicco.inspectionReviewManager.customDatatypes.Model;
import com.example.nicco.inspectionReviewManager.customDatatypes.QueryingAutoCompleteTextView;
import com.example.nicco.inspectionReviewManager.R;

public class ProjectActivity extends AppCompatActivity implements AutoFillActivity {
    private Model model;
    private QueryingAutoCompleteTextView address;
    private QueryingAutoCompleteTextView city;
    private QueryingAutoCompleteTextView province;
    private QueryingAutoCompleteTextView projectNumber;
    private QueryingAutoCompleteTextView developer;
    private QueryingAutoCompleteTextView contractor;
    private CheckBox footings;
    private CheckBox foundationWalls;
    private CheckBox sheathing;
    private CheckBox framing;
    private CheckBox other;
    private TextView descriptionTextView;
    private QueryingAutoCompleteTextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        initViews();
        initValues();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
    }

    // initialize all referenced fields
    private void initViews() {
        model = (Model) getApplicationContext();
        address = (QueryingAutoCompleteTextView) findViewById(R.id.autocompleteAddress);
        address.set(this, model, this, DatabaseWriter.UIComponentInputValue.ADDRESS, null);

        city = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteCity);
        city.set(this, model, this, DatabaseWriter.UIComponentInputValue.CITY, getResources().getStringArray(R.array.cities));

        province = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteProvince);
        province.set(this, model, this, DatabaseWriter.UIComponentInputValue.PROVINCE, new String[]{getResources().getString(R.string.province_default)});

        projectNumber = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteProjectNumber);
        projectNumber.set(this, model, this, DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER, new String[]{getResources().getString(R.string.project_number_default)});

        developer = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteDeveloper);
        developer.set(this, model, this, DatabaseWriter.UIComponentInputValue.DEVELOPER, null);

        contractor = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteContractor);
        contractor.set(this, model, this, DatabaseWriter.UIComponentInputValue.CONTRACTOR, null);

        footings = (CheckBox) findViewById(R.id.checkBoxFootings);
        foundationWalls = (CheckBox) findViewById(R.id.checkBoxFoundationWalls);
        sheathing = (CheckBox) findViewById(R.id.checkBoxSheathing);
        framing = (CheckBox) findViewById(R.id.checkBoxFraming);
        other = (CheckBox) findViewById(R.id.checkBoxOther);
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
        descriptionTextView = (TextView) findViewById(R.id.textViewDescription);
        descriptionTextView.setTextColor(Color.BLACK);

        description = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteDescription);
        description.set(this, model, this, DatabaseWriter.UIComponentInputValue.OTHER_REVIEW_DESCRIPTION, null);
    }

    private void initValues() {
        // ADDRESS
        String value = model.getValue(DatabaseWriter.UIComponentInputValue.ADDRESS);
        if(value != null) address.setText(value);

        // CITY
        value = model.getValue(DatabaseWriter.UIComponentInputValue.CITY);
        if(value != null) city.setText(value);

        // PROVINCE
        value = model.getValue(DatabaseWriter.UIComponentInputValue.PROVINCE);
        if(value != null) province.setText(getResources().getString(R.string.province_default));

        // PROJECT NUMBER
        value = model.getValue(DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER);
        if(value != null) projectNumber.setText(getResources().getString(R.string.project_number_default));

        // DEVELOPER
        value = model.getValue(DatabaseWriter.UIComponentInputValue.DEVELOPER);
        if(value != null) developer.setText(value);

        // CONTRACTOR
        value = model.getValue(DatabaseWriter.UIComponentInputValue.CONTRACTOR);
        if(value != null) contractor.setText(value);

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

        boolean framingActivityAutoFill = footings.isChecked() || foundationWalls.isChecked();
        Log.v("PUCCI", "framingActivityAutoFill = " + framingActivityAutoFill);
        boolean concreteActivityAutoFill = sheathing.isChecked() || framing.isChecked();
        Log.v("PUCCI", "concreteActivityAutoFill = " + concreteActivityAutoFill);
        if(framingActivityAutoFill && !concreteActivityAutoFill) model.AutoFillFramingActivity();
        else if(!framingActivityAutoFill && concreteActivityAutoFill) model.AutoFillConcreteActivity();

    }

    @Override
    public void autofill(Object uiComponent) {
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
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void setTextSize(float textSize) {
        TextView addressLabel = (TextView) findViewById(R.id.textViewAddress);
        addressLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        address.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView cityLabel = (TextView) findViewById(R.id.textViewCity);
        cityLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        city.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView provinceLabel = (TextView) findViewById(R.id.textViewProvince);
        provinceLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        province.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView projectNumberLabel = (TextView) findViewById(R.id.textViewProjectNumber);
        projectNumberLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        projectNumber.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView developerLabel = (TextView) findViewById(R.id.textViewDeveloper);
        developerLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        developer.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView contractorLabel = (TextView) findViewById(R.id.textViewContractor);
        contractorLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        contractor.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView typeOfReviewLabel = (TextView) findViewById(R.id.textViewTypeOfReview);
        typeOfReviewLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        footings.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        foundationWalls.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        sheathing.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        framing.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        other.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        descriptionTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        description.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }
}

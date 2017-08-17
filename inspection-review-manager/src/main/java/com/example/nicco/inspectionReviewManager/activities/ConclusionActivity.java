package com.example.nicco.inspectionReviewManager.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.nicco.inspectionReviewManager.customDatatypes.AutoFillActivity;
import com.example.nicco.inspectionReviewManager.customDatatypes.DatabaseWriter;
import com.example.nicco.inspectionReviewManager.customDatatypes.Model;
import com.example.nicco.inspectionReviewManager.customDatatypes.QueryingAutoCompleteTextView;
import com.example.nicco.inspectionReviewManager.R;

/**
 * Created by Jennifer on 2017-07-17.
 */

public class ConclusionActivity extends AppCompatActivity implements AutoFillActivity {
    private Model model;

    // REBAR POSITION
    private RadioButton approved;
    private RadioButton notApproved;
    private RadioButton reinspectionRequired;
    private EditText observations;
    private EditText comments;
    private QueryingAutoCompleteTextView reviewedBy;
    private CheckBox stamped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusion);

        initViews();
        initValues();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
    }

    private void initViews() {
        model = (Model) getApplicationContext();

        approved = (RadioButton) findViewById(R.id.radioButtonApproved);
        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stamped.setEnabled(true);
            }
        });

        notApproved = (RadioButton) findViewById(R.id.radioButtonNotApproved);
        notApproved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stamped.setEnabled(false);
                stamped.setChecked(false);
            }
        });

        reinspectionRequired = (RadioButton) findViewById(R.id.radioButtonReinspectionRequired);
        reinspectionRequired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stamped.setEnabled(false);
                stamped.setChecked(false);
            }
        });

        observations = (EditText) findViewById(R.id.editTextObservations);
        comments = (EditText) findViewById(R.id.editTextComments);
        reviewedBy = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteReviewedBy);
        reviewedBy.set(this, model, this, DatabaseWriter.UIComponentInputValue.REVIEWED_BY, null);
        stamped = (CheckBox) findViewById(R.id.checkBoxStamped);
        stamped.setVisibility(View.INVISIBLE);
    }

    private void initValues() {
        // OBSERVATIONS
        observations.setText(model.getValue(DatabaseWriter.UIComponentInputValue.OBSERVATIONS));

        // COMMENTS
        comments.setText(model.getValue(DatabaseWriter.UIComponentInputValue.COMMENTS));

        // REVIEW STATUS
        approved.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_APPROVED));
        notApproved.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_NOT_APPROVED));
        reinspectionRequired.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_REINSPECTION_REQUIRED));
        if(approved.isChecked()) stamped.setEnabled(true);
        else {
            stamped.setEnabled(false);
            stamped.setChecked(false);
        }

        // REVIEWED BY
        if(model.validValue(model.getValue(DatabaseWriter.UIComponentInputValue.REVIEWED_BY))) {
            reviewedBy.setText(model.getValue(DatabaseWriter.UIComponentInputValue.REVIEWED_BY));
        }
        stamped.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.STAMPED));
    }


    @Override
    protected void onPause() {
        super.onPause();

        // OBSERVATIONS
        String tempText = observations.getText().toString();
        if(tempText.equals("")) tempText = Model.SpecialValue.NONE.toString();
        model.updateValue(DatabaseWriter.UIComponentInputValue.OBSERVATIONS, tempText);

        // COMMENTS
        tempText = comments.getText().toString();
        if(tempText.equals("")) tempText = Model.SpecialValue.NONE.toString();
        model.updateValue(DatabaseWriter.UIComponentInputValue.COMMENTS, tempText);

        // REVIEW STATUS
        if(approved.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS, Model.ReviewStatusValue.APPROVED.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_APPROVED, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_NOT_APPROVED, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_REINSPECTION_REQUIRED, Model.SpecialValue.NO.toString());
        } else if(notApproved.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS, Model.ReviewStatusValue.NOT_APPROVED.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_APPROVED, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_NOT_APPROVED, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_REINSPECTION_REQUIRED, Model.SpecialValue.NO.toString());
        } else if(reinspectionRequired.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS, Model.ReviewStatusValue.REINSPECTION_REQUIRED.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_APPROVED, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_NOT_APPROVED, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_REINSPECTION_REQUIRED, Model.SpecialValue.YES.toString());
        }

        // REVIEWED BY
        model.updateValue(DatabaseWriter.UIComponentInputValue.REVIEWED_BY, reviewedBy.getText().toString());
        if(stamped.isChecked()) model.updateValue(DatabaseWriter.UIComponentInputValue.STAMPED, Model.SpecialValue.YES.toString());
        else model.updateValue(DatabaseWriter.UIComponentInputValue.STAMPED, Model.SpecialValue.NO.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        initValues();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void setTextSize(float textSize) {
        TextView observationLabel = (TextView) findViewById(R.id.textViewObservations);
        observationLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        observations.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView commentsLabel = (TextView) findViewById(R.id.textViewComments);
        commentsLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        comments.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView reviewStatusLabel = (TextView) findViewById(R.id.textViewReviewStatus);
        reviewStatusLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        ((RadioButton) findViewById(R.id.radioButtonApproved)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        ((RadioButton) findViewById(R.id.radioButtonNotApproved)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        ((RadioButton) findViewById(R.id.radioButtonReinspectionRequired)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView reviewByLabel = (TextView) findViewById(R.id.textViewReviewedBy);
        reviewByLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        reviewedBy.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    @Override
    public void autofill(Object uiComponent) {}
}

package com.example.nicco.inspectionReviewManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by Jennifer on 2017-07-17.
 */

public class ConclusionActivity extends AppCompatActivity {
    private Model model;

    // REBAR POSITION
    private RadioButton approved;
    private RadioButton notApproved;
    private RadioButton reinspectionRequired;
    private EditText observations;
    private EditText comments;
    private AutoCompleteTextView reviewedBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusion);

        init();

        // OBSERVATIONS
        observations.setText(model.getValue(DatabaseWriter.DatabaseColumn.OBSERVATIONS));

        // COMMENTS
        comments.setText(model.getValue(DatabaseWriter.DatabaseColumn.COMMENTS));

        // REVIEW STATUS
        String reviewStatus = model.getValue(DatabaseWriter.DatabaseColumn.REVIEW_STATUS);
        if(reviewStatus.equals(Model.ReviewStatusValue.APPROVED.toString())) {
            approved.setChecked(true);
        } else if(reviewStatus.equals(Model.ReviewStatusValue.NOT_APPROVED.toString())) {
            notApproved.setChecked(true);
        } else if(reviewStatus.equals(Model.ReviewStatusValue.REINSPECTION_REQUIRED.toString())) {
            reinspectionRequired.setChecked(true);
        }

        // REVIEWED BY
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.DatabaseColumn.REVIEWED_BY, null, null));
        reviewedBy.setAdapter(adapter);
        if(model.validValue(model.getValue(DatabaseWriter.DatabaseColumn.REVIEWED_BY))) {
            reviewedBy.setText(model.getValue(DatabaseWriter.DatabaseColumn.REVIEWED_BY));
        }
    }

    private void init() {
        model = (Model) getApplicationContext();
        approved = (RadioButton) findViewById(R.id.radioButtonApproved);
        notApproved = (RadioButton) findViewById(R.id.radioButtonNotApproved);
        reinspectionRequired = (RadioButton) findViewById(R.id.radioButtonReinspectionRequired);
        observations = (EditText) findViewById(R.id.editTextObservations);
        comments = (EditText) findViewById(R.id.editTextComments);
        reviewedBy = (AutoCompleteTextView) findViewById(R.id.autoCompleteReviewedBy);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // OBSERVATIONS
        model.updateValue(DatabaseWriter.DatabaseColumn.OBSERVATIONS, observations.getText().toString());

        // COMMENTS
        model.updateValue(DatabaseWriter.DatabaseColumn.COMMENTS, comments.getText().toString());

        // REVIEW STATUS
        if(approved.isChecked()) {
            model.updateValue(DatabaseWriter.DatabaseColumn.REVIEW_STATUS, Model.ReviewStatusValue.APPROVED.toString());
        } else if(notApproved.isChecked()) {
            model.updateValue(DatabaseWriter.DatabaseColumn.REVIEW_STATUS, Model.ReviewStatusValue.NOT_APPROVED.toString());
        } else if(reinspectionRequired.isChecked()) {
            model.updateValue(DatabaseWriter.DatabaseColumn.REVIEW_STATUS, Model.ReviewStatusValue.REINSPECTION_REQUIRED.toString());
        }

        // REVIEWED BY
        model.updateValue(DatabaseWriter.DatabaseColumn.REVIEWED_BY, reviewedBy.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
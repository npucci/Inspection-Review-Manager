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
        observations.setText(model.getObservations());

        // COMMENTS
        comments.setText(model.getComments());

        // REVIEW STATUS
        String reviewStatus = model.getReviewedStatus();
        if(reviewStatus.equals(Model.ReviewStatusValue.APPROVED.toString())) {
            approved.setChecked(true);
        } else if(reviewStatus.equals(Model.ReviewStatusValue.NOT_APPROVED.toString())) {
            notApproved.setChecked(true);
        } else if(reviewStatus.equals(Model.ReviewStatusValue.REINSPECTION_REQUIRED.toString())) {
            reinspectionRequired.setChecked(true);
        }

        // REVIEWED BY
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        reviewedBy.setAdapter(adapter);
        if(model.getReviewedBy() != null) {
            reviewedBy.setText(model.getReviewedBy());
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
    protected void onStop() {
        super.onStop();

        // OBSERVATIONS
        model.updateObservations(observations.getText().toString());

        // COMMENTS
        model.updateComments(comments.getText().toString());

        // REVIEW STATUS
        if(approved.isChecked()) {
            model.updateReviewStatus(Model.ReviewStatusValue.APPROVED);
        } else if(notApproved.isChecked()) {
            model.updateReviewStatus(Model.ReviewStatusValue.NOT_APPROVED);
        } else if(reinspectionRequired.isChecked()) {
            model.updateReviewStatus(Model.ReviewStatusValue.REINSPECTION_REQUIRED);
        }

        // REVIEWED BY
        model.updateReviewedBy(reviewedBy.getText().toString());
    }
}

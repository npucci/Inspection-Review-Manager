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
        observations.setText(model.getValue(DatabaseWriter.UIComponentInputValue.OBSERVATIONS));

        // COMMENTS
        comments.setText(model.getValue(DatabaseWriter.UIComponentInputValue.COMMENTS));

        // REVIEW STATUS
        approved.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_APPROVED));
        notApproved.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_NOT_APPROVED));
        reinspectionRequired.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_REINSPECTION_REQUIRED));

        // REVIEWED BY
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.REVIEWED_BY, null, null));
        reviewedBy.setAdapter(adapter);
        if(model.validValue(model.getValue(DatabaseWriter.UIComponentInputValue.REVIEWED_BY))) {
            reviewedBy.setText(model.getValue(DatabaseWriter.UIComponentInputValue.REVIEWED_BY));
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
        model.updateValue(DatabaseWriter.UIComponentInputValue.OBSERVATIONS, observations.getText().toString());

        // COMMENTS
        model.updateValue(DatabaseWriter.UIComponentInputValue.COMMENTS, comments.getText().toString());

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
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

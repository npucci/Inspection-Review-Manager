package com.example.nicco.inspectionReviewManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.RadioButton;

/**
 * Created by Nicco on 2017-07-14.
 */

public class ConcreteActivity extends AppCompatActivity {
    private Model model;

    // REBAR POSITION
    private RadioButton rebarPositionReviewed;
    private RadioButton rebarPositionNA;
    private AutoCompleteTextView rebarPositionInstruction;

    // REBAR SIZE
    private RadioButton rebarSizeReviewed;
    private RadioButton rebarSizeNA;
    private AutoCompleteTextView rebarSizeInstruction;

    // FORMWORK
    private RadioButton formworkReviewed;
    private RadioButton formworkNA;
    private AutoCompleteTextView formworkInstruction;

    // ANCHORAGE
    private RadioButton anchorageReviewed;
    private RadioButton anchorageNA;
    private AutoCompleteTextView anchorageInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concrete);

        init();

        // REBAR POSITION
        rebarPositionReviewed.setChecked(model.getRebarPositionReviewed());
        rebarPositionReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rebarPositionInstruction.setEnabled(true);
            }
        });

        rebarPositionNA.setChecked(model.getRebarPositionNA());
        rebarPositionNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rebarPositionInstruction.setEnabled(false);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        rebarPositionInstruction.setAdapter(adapter);
        if(rebarPositionReviewed.isChecked()) {
            if(model.getRebarPositionInstruction() != null) {
                rebarPositionInstruction.setText(model.getRebarPositionInstruction());
            }
        }
        else rebarPositionInstruction.setEnabled(false);

        // REBAR SIZE
        rebarSizeReviewed.setChecked(model.getRebarSizeReviewed());
        rebarSizeReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rebarSizeInstruction.setEnabled(true);
            }
        });

        rebarSizeNA.setChecked(model.getRebarSizeNA());
        rebarSizeNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rebarSizeInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        rebarSizeInstruction.setAdapter(adapter);
        if(rebarSizeReviewed.isChecked()) {
            if(model.getRebarSizeInstruction() != null) {
                rebarSizeInstruction.setText(model.getRebarSizeInstruction());
            }
        }
        else rebarSizeInstruction.setEnabled(false);

        // FORMWORK
        formworkReviewed.setChecked(model.getFormworkReviewed());
        formworkReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formworkInstruction.setEnabled(true);
            }
        });

        formworkNA.setChecked(model.getFormworkNA());
        formworkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formworkInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        formworkInstruction.setAdapter(adapter);
        if(formworkReviewed.isChecked()) {
            if(model.getFormworkInstruction() != null) {
                formworkInstruction.setText(model.getFormworkInstruction());
            }
        }
        else formworkInstruction.setEnabled(false);

        // ANCHORAGE
        anchorageReviewed.setChecked(model.getAnchorageReviewed());
        anchorageReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anchorageInstruction.setEnabled(true);
            }
        });

        anchorageNA.setChecked(model.getAnchorageNA());
        anchorageNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anchorageInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        anchorageInstruction.setAdapter(adapter);
        if(anchorageReviewed.isChecked()) {
            if(model.getAnchorageInstruction() != null) {
                anchorageInstruction.setText(model.getAnchorageInstruction());
            }
        }
        else anchorageInstruction.setEnabled(false);
    }

    private void init() {
        model = (Model) getApplicationContext();

        // REBAR POSITION
        rebarPositionReviewed = (RadioButton) findViewById(R.id.radioButtonRebarPositionReviewed);
        rebarPositionNA = (RadioButton) findViewById(R.id.radioButtonRebarPositionNA);
        rebarPositionInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteRebarPositionInstruction);

        // REBAR SIZE
        rebarSizeReviewed = (RadioButton) findViewById(R.id.radioButtonRebarSizeReviewed);
        rebarSizeNA = (RadioButton) findViewById(R.id.radioButtonRebarSizeNA);
        rebarSizeInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteRebarSizeInstruction);

        // FORMWORK
        formworkReviewed = (RadioButton) findViewById(R.id.radioButtonFormworkReviewed);
        formworkNA = (RadioButton) findViewById(R.id.radioButtonFormworkNA);
        formworkInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteFormworkInstruction);

        // ANCHORAGE
        anchorageReviewed = (RadioButton) findViewById(R.id.radioButtonAnchorageReviewed);
        anchorageNA = (RadioButton) findViewById(R.id.radioButtonAnchorageNA);
        anchorageInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteAnchorageInstruction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // REBAR POSITION
        if(rebarPositionReviewed.isChecked()) {
            model.updateRebarPositionReviewed(Model.SpecialValues.YES);
            model.updateRebarPositionNA(Model.SpecialValues.NO);
            if(rebarPositionInstruction.getText().toString().equals(""))
                model.updateRebarPositionInstruction(Model.SpecialValues.NONE.toString());
            else
                model.updateRebarPositionInstruction(rebarPositionInstruction.getText().toString());
        } else if(rebarPositionNA.isChecked()) {
            model.updateRebarPositionReviewed(Model.SpecialValues.NO);
            model.updateRebarPositionNA(Model.SpecialValues.YES);
            model.updateRebarPositionInstruction(Model.SpecialValues.NA.toString());
        }

        // REBAR SIZE
        if(rebarSizeReviewed.isChecked()) {
            model.updateRebarSizeReviewed(Model.SpecialValues.YES);
            model.updateRebarSizeNA(Model.SpecialValues.NO);
            if(rebarSizeInstruction.getText().toString().equals(""))
                model.updateRebarSizeInstruction(Model.SpecialValues.NONE.toString());
            else
                model.updateRebarSizeInstruction(rebarSizeInstruction.getText().toString());
        } else if (rebarSizeNA.isChecked()) {
            model.updateRebarSizeReviewed(Model.SpecialValues.NO);
            model.updateRebarSizeNA(Model.SpecialValues.YES);
            model.updateRebarSizeInstruction(Model.SpecialValues.NA.toString());
        }

        // FORMWORK
        if(formworkReviewed.isChecked()) {
            model.updateFormworkReviewed(Model.SpecialValues.YES);
            model.updateFormworkNA(Model.SpecialValues.NO);
            if(formworkInstruction.getText().toString().equals(""))
                model.updateFormworkInstruction(Model.SpecialValues.NONE.toString());
            else
                model.updateFormworkInstruction(formworkInstruction.getText().toString());
        } else if (formworkNA.isChecked()) {
            model.updateFormworkReviewed(Model.SpecialValues.NO);
            model.updateFormworkNA(Model.SpecialValues.YES);
            model.updateFormworkInstruction(Model.SpecialValues.NA.toString());
        }

        // ANCHORAGE
        if(anchorageReviewed.isChecked()) {
            model.updateAnchorageReviewed(Model.SpecialValues.YES);
            model.updateAnchorageNA(Model.SpecialValues.NO);
            if(anchorageInstruction.getText().toString().equals(""))
                model.updateAnchorageInstruction(Model.SpecialValues.NONE.toString());
            else
                model.updateAnchorageInstruction(anchorageInstruction.getText().toString());
        } else if (anchorageNA.isChecked()) {
            model.updateAnchorageReviewed(Model.SpecialValues.NO);
            model.updateAnchorageNA(Model.SpecialValues.YES);
            model.updateAnchorageInstruction(Model.SpecialValues.NA.toString());
        }
    }

}


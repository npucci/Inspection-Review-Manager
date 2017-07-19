package com.example.nicco.inspectionReviewManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
    protected void onPause() {
        super.onPause();

        // REBAR POSITION
        if(rebarPositionReviewed.isChecked()) {
            model.updateRebarPositionReviewed(Model.SpecialValue.YES);
            model.updateRebarPositionNA(Model.SpecialValue.NO);
            if(rebarPositionInstruction.getText().toString().equals(""))
                model.updateRebarPositionInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateRebarPositionInstruction(rebarPositionInstruction.getText().toString());
        } else if(rebarPositionNA.isChecked()) {
            model.updateRebarPositionReviewed(Model.SpecialValue.NO);
            model.updateRebarPositionNA(Model.SpecialValue.YES);
            model.updateRebarPositionInstruction(Model.SpecialValue.NA.toString());
        }

        // REBAR SIZE
        if(rebarSizeReviewed.isChecked()) {
            model.updateRebarSizeReviewed(Model.SpecialValue.YES);
            model.updateRebarSizeNA(Model.SpecialValue.NO);
            if(rebarSizeInstruction.getText().toString().equals(""))
                model.updateRebarSizeInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateRebarSizeInstruction(rebarSizeInstruction.getText().toString());
        } else if (rebarSizeNA.isChecked()) {
            model.updateRebarSizeReviewed(Model.SpecialValue.NO);
            model.updateRebarSizeNA(Model.SpecialValue.YES);
            model.updateRebarSizeInstruction(Model.SpecialValue.NA.toString());
        }

        // FORMWORK
        if(formworkReviewed.isChecked()) {
            model.updateFormworkReviewed(Model.SpecialValue.YES);
            model.updateFormworkNA(Model.SpecialValue.NO);
            if(formworkInstruction.getText().toString().equals(""))
                model.updateFormworkInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateFormworkInstruction(formworkInstruction.getText().toString());
        } else if (formworkNA.isChecked()) {
            model.updateFormworkReviewed(Model.SpecialValue.NO);
            model.updateFormworkNA(Model.SpecialValue.YES);
            model.updateFormworkInstruction(Model.SpecialValue.NA.toString());
        }

        // ANCHORAGE
        if(anchorageReviewed.isChecked()) {
            model.updateAnchorageReviewed(Model.SpecialValue.YES);
            model.updateAnchorageNA(Model.SpecialValue.NO);
            if(anchorageInstruction.getText().toString().equals(""))
                model.updateAnchorageInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateAnchorageInstruction(anchorageInstruction.getText().toString());
        } else if (anchorageNA.isChecked()) {
            model.updateAnchorageReviewed(Model.SpecialValue.NO);
            model.updateAnchorageNA(Model.SpecialValue.YES);
            model.updateAnchorageInstruction(Model.SpecialValue.NA.toString());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}


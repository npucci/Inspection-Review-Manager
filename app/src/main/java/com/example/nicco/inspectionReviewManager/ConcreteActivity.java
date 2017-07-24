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
        rebarPositionReviewed.setChecked(model.isChecked(Model.Keys.REBAR_POSITION_REVIEWED));
        rebarPositionReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rebarPositionInstruction.setEnabled(true);
            }
        });

        rebarPositionNA.setChecked(model.isChecked(Model.Keys.REBAR_POSITION_NA));
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
            rebarPositionInstruction.setText(model.getValue(Model.Keys.REBAR_POSITION_INSTRUCTION));
        }
        else rebarPositionInstruction.setEnabled(false);

        // REBAR SIZE
        rebarSizeReviewed.setChecked(model.isChecked(Model.Keys.REBAR_SIZE_REVIEWED));
        rebarSizeReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rebarSizeInstruction.setEnabled(true);
            }
        });

        rebarSizeNA.setChecked(model.isChecked(Model.Keys.REBAR_SIZE_NA));
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
            rebarSizeInstruction.setText(model.getValue(Model.Keys.REBAR_SIZE_INSTRUCTION));
        }
        else rebarSizeInstruction.setEnabled(false);

        // FORMWORK
        formworkReviewed.setChecked(model.isChecked(Model.Keys.FORMWORK_REVIEWED));
        formworkReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formworkInstruction.setEnabled(true);
            }
        });

        formworkNA.setChecked(model.isChecked(Model.Keys.FORMWORK_NA));
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
            formworkInstruction.setText(model.getValue(Model.Keys.FORMWORK_INSTRUCTION));
        }
        else formworkInstruction.setEnabled(false);

        // ANCHORAGE
        anchorageReviewed.setChecked(model.isChecked(Model.Keys.ANCHORAGE_REVIEWED));
        anchorageReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anchorageInstruction.setEnabled(true);
            }
        });

        anchorageNA.setChecked(model.isChecked(Model.Keys.ANCHORAGE_NA));
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
            anchorageInstruction.setText(model.getValue(Model.Keys.ANCHORAGE_INSTRUCTION));
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
            model.updateValue(Model.Keys.REBAR_POSITION_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.REBAR_POSITION_NA, Model.SpecialValue.NO.toString());
            if(rebarPositionInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.REBAR_POSITION_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.REBAR_POSITION_INSTRUCTION, rebarPositionInstruction.getText().toString());
        } else if(rebarPositionNA.isChecked()) {
            model.updateValue(Model.Keys.REBAR_POSITION_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.REBAR_POSITION_NA, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.REBAR_POSITION_INSTRUCTION, Model.SpecialValue.NA.toString());
        }

        // REBAR SIZE
        if(rebarSizeReviewed.isChecked()) {
            model.updateValue(Model.Keys.REBAR_SIZE_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.REBAR_SIZE_NA, Model.SpecialValue.NO.toString());
            if(rebarSizeInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.REBAR_SIZE_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.REBAR_SIZE_INSTRUCTION, rebarSizeInstruction.getText().toString());
        } else if (rebarSizeNA.isChecked()) {
            model.updateValue(Model.Keys.REBAR_SIZE_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.REBAR_SIZE_NA, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.REBAR_SIZE_INSTRUCTION, Model.SpecialValue.NA.toString());
        }

        // FORMWORK
        if(formworkReviewed.isChecked()) {
            model.updateValue(Model.Keys.FORMWORK_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.FORMWORK_NA, Model.SpecialValue.NO.toString());
            if(formworkInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.FORMWORK_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.FORMWORK_INSTRUCTION, formworkInstruction.getText().toString());
        } else if (formworkNA.isChecked()) {
            model.updateValue(Model.Keys.FORMWORK_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.FORMWORK_NA, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.FORMWORK_INSTRUCTION, Model.SpecialValue.NA.toString());
        }

        // ANCHORAGE
        if(anchorageReviewed.isChecked()) {
            model.updateValue(Model.Keys.ANCHORAGE_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.ANCHORAGE_NA, Model.SpecialValue.NO.toString());
            if(anchorageInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.ANCHORAGE_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.ANCHORAGE_INSTRUCTION, anchorageInstruction.getText().toString());
        } else if (anchorageNA.isChecked()) {
            model.updateValue(Model.Keys.ANCHORAGE_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.ANCHORAGE_NA, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.ANCHORAGE_INSTRUCTION, Model.SpecialValue.NA.toString());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}


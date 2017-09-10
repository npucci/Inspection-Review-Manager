package com.example.nicco.inspectionReviewManager.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.nicco.inspectionReviewManager.interfaces.AutoFillActivity;
import com.example.nicco.inspectionReviewManager.customDatatypes.DatabaseWriter;
import com.example.nicco.inspectionReviewManager.customDatatypes.Model;
import com.example.nicco.inspectionReviewManager.customDatatypes.QueryingAutoCompleteTextView;
import com.example.nicco.inspectionReviewManager.R;

/**
 * Created by Nicco on 2017-07-14.
 */

public class ConcreteActivity extends AppCompatActivity implements AutoFillActivity {
    private Model model;

    // REBAR POSITION
    private RadioButton rebarPositionReviewed;
    private RadioButton rebarPositionNA;
    private QueryingAutoCompleteTextView rebarPositionInstruction;

    // REBAR SIZE
    private RadioButton rebarSizeReviewed;
    private RadioButton rebarSizeNA;
    private QueryingAutoCompleteTextView rebarSizeInstruction;

    // FORMWORK
    private RadioButton formworkReviewed;
    private RadioButton formworkNA;
    private QueryingAutoCompleteTextView formworkInstruction;

    // ANCHORAGE
    private RadioButton anchorageReviewed;
    private RadioButton anchorageNA;
    private QueryingAutoCompleteTextView anchorageInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concrete);
        initViews();
        initValues();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
    }

    private void initViews() {
        model = (Model) getApplicationContext();

        // REBAR POSITION
        rebarPositionReviewed = (RadioButton) findViewById(R.id.radioButtonRebarPositionReviewed);
        rebarPositionNA = (RadioButton) findViewById(R.id.radioButtonRebarPositionNA);
        rebarPositionInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteRebarPositionInstruction);
        rebarPositionInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.REBAR_POSITION_INSTRUCTION.getValue(), null);

        // REBAR SIZE
        rebarSizeReviewed = (RadioButton) findViewById(R.id.radioButtonRebarSizeReviewed);
        rebarSizeNA = (RadioButton) findViewById(R.id.radioButtonRebarSizeNA);
        rebarSizeInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteRebarSizeInstruction);
        rebarSizeInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.REBAR_SIZE_INSTRUCTION.getValue(), null);

        // FORMWORK
        formworkReviewed = (RadioButton) findViewById(R.id.radioButtonFormworkReviewed);
        formworkNA = (RadioButton) findViewById(R.id.radioButtonFormworkNA);
        formworkInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteFormworkInstruction);
        formworkInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.FORMWORK_INSTRUCTION.getValue(), null);

        // ANCHORAGE
        anchorageReviewed = (RadioButton) findViewById(R.id.radioButtonAnchorageReviewed);
        anchorageNA = (RadioButton) findViewById(R.id.radioButtonAnchorageNA);
        anchorageInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteAnchorageInstruction);
        anchorageInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.ANCHORAGE_INSTRUCTION.getValue(), null);
    }

    private void initValues() {
        // REBAR POSITION
        rebarPositionReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.REBAR_POSITION));
        rebarPositionReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rebarPositionInstruction.setEnabled(true);
            }
        });

        if(model.getValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION).equals(Model.SpecialValue.NO.toString())) {
            rebarPositionNA.setChecked(true);
        } else rebarPositionNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.REBAR_POSITION_NA));
        rebarPositionNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rebarPositionInstruction.setEnabled(false);
            }
        });

        if(rebarPositionReviewed.isChecked()) {
            rebarPositionInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION_INSTRUCTION));
        }
        else rebarPositionInstruction.setEnabled(false);

        // REBAR SIZE
        rebarSizeReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.REBAR_SIZE));
        rebarSizeReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rebarSizeInstruction.setEnabled(true);
            }
        });

        if(model.getValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE).equals(Model.SpecialValue.NO.toString())) {
            rebarSizeNA.setChecked(true);
        } else rebarSizeNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.REBAR_SIZE_NA));
        rebarSizeNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rebarSizeInstruction.setEnabled(false);
            }
        });

        if(rebarSizeReviewed.isChecked()) {
            rebarSizeInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE_INSTRUCTION));
        }
        else rebarSizeInstruction.setEnabled(false);

        // FORMWORK
        formworkReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.FORMWORK));
        formworkReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formworkInstruction.setEnabled(true);
            }
        });

        if(model.getValue(DatabaseWriter.UIComponentInputValue.FORMWORK).equals(Model.SpecialValue.NO.toString())) {
            formworkNA.setChecked(true);
        } else formworkNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.FORMWORK_NA));
        formworkNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formworkInstruction.setEnabled(false);
            }
        });
        if(formworkReviewed.isChecked()) {
            formworkInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.FORMWORK_INSTRUCTION));
        }
        else formworkInstruction.setEnabled(false);

        // ANCHORAGE
        anchorageReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.ANCHORAGE));
        anchorageReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anchorageInstruction.setEnabled(true);
            }
        });
        if(model.getValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE).equals(Model.SpecialValue.NO.toString())) {
            anchorageNA.setChecked(true);
        } else anchorageNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.ANCHORAGE_NA));
        anchorageNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anchorageInstruction.setEnabled(false);
            }
        });
        if(anchorageReviewed.isChecked()) {
            anchorageInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE_INSTRUCTION));
        }
        else anchorageInstruction.setEnabled(false);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // REBAR POSITION
        if(rebarPositionReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION_NA, Model.SpecialValue.NO.toString());
            if(rebarPositionInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION_INSTRUCTION, rebarPositionInstruction.getText().toString());
        } else if(rebarPositionNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION_INSTRUCTION, Model.SpecialValue.NA.toString());
        } else model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION, Model.SpecialValue.EMPTY.toString());

        // REBAR SIZE
        if(rebarSizeReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE_NA, Model.SpecialValue.NO.toString());
            if(rebarSizeInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE_INSTRUCTION, rebarSizeInstruction.getText().toString());
        } else if (rebarSizeNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE_INSTRUCTION, Model.SpecialValue.NA.toString());
        } else model.updateValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE, Model.SpecialValue.EMPTY.toString());

        // FORMWORK
        if(formworkReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.FORMWORK, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.FORMWORK_NA, Model.SpecialValue.NO.toString());
            if(formworkInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.FORMWORK_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.FORMWORK_INSTRUCTION, formworkInstruction.getText().toString());
        } else if (formworkNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.FORMWORK, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.FORMWORK_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.FORMWORK_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.FORMWORK, Model.SpecialValue.EMPTY.toString());

        // ANCHORAGE
        if(anchorageReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE_NA, Model.SpecialValue.NO.toString());
            if(anchorageInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE_INSTRUCTION, anchorageInstruction.getText().toString());
        } else if (anchorageNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE, Model.SpecialValue.EMPTY.toString());
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
        TextView rebarPositionLabel = (TextView) findViewById(R.id.textViewRebarPosition);
        rebarPositionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        rebarPositionReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        rebarPositionNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        rebarPositionInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView rebarPositionInstructionLabel = (TextView) findViewById(R.id.textViewRebarPositionInstruction);
        rebarPositionInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView rebarSizeLabel = (TextView) findViewById(R.id.textViewRebarSize);
        rebarSizeLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        rebarSizeReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        rebarSizeNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        rebarSizeInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView rebarSizeInstructionLabel = (TextView) findViewById(R.id.textViewRebarSizeInstruction);
        rebarSizeInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView formworkLabel = (TextView) findViewById(R.id.textViewFormwork);
        formworkLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        formworkReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        formworkNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        formworkInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView formworkInstructionLabel = (TextView) findViewById(R.id.textViewFormworkInstruction);
        formworkInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView anchorageLabel = (TextView) findViewById(R.id.textViewAnchorage);
        anchorageLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        anchorageReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        anchorageNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        anchorageInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView anchorageInstructionLabel = (TextView) findViewById(R.id.textViewAnchorageInstruction);
        anchorageInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    @Override
    public void autofill(Object uiComponent) {}
}


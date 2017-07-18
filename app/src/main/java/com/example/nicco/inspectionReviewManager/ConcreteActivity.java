package com.example.nicco.inspectionReviewManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;

/**
 * Created by Nicco on 2017-07-14.
 */

public class ConcreteActivity extends AppCompatActivity {
    private Model model;
    private AutoCompleteTextView address;
    private AutoCompleteTextView cityProv;
    private AutoCompleteTextView projectNumber;
    private AutoCompleteTextView developer;
    private AutoCompleteTextView contractor;
    private CheckBox footings;
    private CheckBox foundationWalls;
    private CheckBox sheathing;
    private CheckBox framing;
    private CheckBox other;
    private AutoCompleteTextView otherInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concrete);
    }
}


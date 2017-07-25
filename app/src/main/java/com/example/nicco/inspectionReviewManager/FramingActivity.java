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

public class FramingActivity extends AppCompatActivity {
    private Model model;

    // TRUSS SPEC
    private RadioButton trussSpecReviewed;
    private RadioButton trussSpecNA;
    private AutoCompleteTextView trussSpecInstruction;

    // IJOIST
    private RadioButton iJoistReviewed;
    private RadioButton iJoistNA;
    private AutoCompleteTextView iJoistInstruction;

    // BEARING
    private RadioButton bearingReviewed;
    private RadioButton bearingNA;
    private AutoCompleteTextView bearingInstruction;

    // TOP PLATES
    private RadioButton topPlatesReviewed;
    private RadioButton topPlatesNA;
    private AutoCompleteTextView topPlatesInstruction;


    // LINTELS
    private RadioButton lintelsReviewed;
    private RadioButton lintelsNA;
    private AutoCompleteTextView lintelsInstruction;


    // SHEARWALLS
    private RadioButton shearwallsReviewed;
    private RadioButton shearwallsNA;
    private AutoCompleteTextView shearwallsInstruction;

    // TALL WALLS
    private RadioButton tallWallsReviewed;
    private RadioButton tallWallsNA;
    private AutoCompleteTextView tallWallsInstruction;

    // BLOCKING
    private RadioButton blockingReviewed;
    private RadioButton blockingNA;
    private AutoCompleteTextView blockingInstruction;

    // WALL SHEATHING
    private RadioButton wallSheathingReviewed;
    private RadioButton wallSheathingNA;
    private AutoCompleteTextView wallSheathingInstruction;

    // WIND GIRTS
    private RadioButton windGirtsReviewed;
    private RadioButton windGirtsNA;
    private AutoCompleteTextView windGirtsInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framing);

        init();

        // TRUSS SPEC
        trussSpecReviewed.setChecked(model.isChecked(Model.Keys.TRUSS_SPEC_REVIEWED));
        trussSpecReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trussSpecInstruction.setEnabled(true);
            }
        });

        trussSpecNA.setChecked(model.isChecked(Model.Keys.TRUSS_SPEC_NA));
        trussSpecNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trussSpecInstruction.setEnabled(false);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        trussSpecInstruction.setAdapter(adapter);
        if(trussSpecReviewed.isChecked()) {
            trussSpecInstruction.setText(model.getValue(Model.Keys.TRUSS_SPEC_INSTRUCTION));
        }
        else trussSpecInstruction.setEnabled(false);

        // IJOIST SPEC
        iJoistReviewed.setChecked(model.isChecked(Model.Keys.IJOIST_REVIEWED));
        iJoistReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iJoistInstruction.setEnabled(true);
            }
        });

        iJoistNA.setChecked(model.isChecked(Model.Keys.IJOIST_NA));
        iJoistNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iJoistInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        iJoistInstruction.setAdapter(adapter);
        if(iJoistReviewed.isChecked()) {
            iJoistInstruction.setText(model.getValue(Model.Keys.IJOIST_INSTRUCTION));
        }
        else iJoistInstruction.setEnabled(false);

        // BEARING
        bearingReviewed.setChecked(model.isChecked(Model.Keys.BEARING_REVIEWED));
        bearingReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bearingInstruction.setEnabled(true);
            }
        });

        bearingNA.setChecked(model.isChecked(Model.Keys.BEARING_NA));
        bearingNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bearingInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        bearingInstruction.setAdapter(adapter);
        if( bearingReviewed.isChecked()) {
            bearingInstruction.setText(model.getValue(Model.Keys.BEARING_INSTRUCTION));
        }
        else bearingInstruction.setEnabled(false);

        // TOP PLATES
        topPlatesReviewed.setChecked(model.isChecked(Model.Keys.TOP_PLATES_REVIEWED));
        topPlatesReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topPlatesInstruction.setEnabled(true);
            }
        });

        topPlatesNA.setChecked(model.isChecked(Model.Keys.TOP_PLATES_NA));
        topPlatesNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topPlatesInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        topPlatesInstruction.setAdapter(adapter);
        if(topPlatesReviewed.isChecked()) {
                topPlatesInstruction.setText(model.getValue(Model.Keys.TOP_PLATES_INSTRUCTION));
        }
        else topPlatesInstruction.setEnabled(false);

        // LINTELS
        lintelsReviewed.setChecked(model.isChecked(Model.Keys.LINTELS_REVIEWED));
        lintelsReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lintelsInstruction.setEnabled(true);
            }
        });

        lintelsNA.setChecked(model.isChecked(Model.Keys.LINTELS_NA));
        lintelsNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lintelsInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        lintelsInstruction.setAdapter(adapter);
        if(lintelsReviewed.isChecked()) {
            lintelsInstruction.setText(model.getValue(Model.Keys.LINTELS_INSTRUCTION));
        }
        else lintelsInstruction.setEnabled(false);

        // SHEARWALLS
        shearwallsReviewed.setChecked(model.isChecked(Model.Keys.SHEARWALLS_REVIEWED));
        shearwallsReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shearwallsInstruction.setEnabled(true);
            }
        });

        shearwallsNA.setChecked(model.isChecked(Model.Keys.SHEARWALLS_NA));
        shearwallsNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shearwallsInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        shearwallsInstruction.setAdapter(adapter);
        if(shearwallsReviewed.isChecked()) {
            shearwallsInstruction.setText(model.getValue(Model.Keys.SHEARWALLS_INSTRUCTION));
        }
        else shearwallsInstruction.setEnabled(false);

        // TALL WALLS
        tallWallsReviewed.setChecked(model.isChecked(Model.Keys.TALLWALLS_REVIEWED));
        tallWallsReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tallWallsInstruction.setEnabled(true);
            }
        });

        tallWallsNA.setChecked(model.isChecked(Model.Keys.TALLWALLS_NA));
        tallWallsNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tallWallsInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        tallWallsInstruction.setAdapter(adapter);
        if(tallWallsReviewed.isChecked()) {
            tallWallsInstruction.setText(model.getValue(Model.Keys.SHEARWALLS_INSTRUCTION));
        }
        else tallWallsInstruction.setEnabled(false);

        // BLOCKING
        blockingReviewed.setChecked(model.isChecked(Model.Keys.BLOCKING_REVIEWED));
        blockingReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blockingInstruction.setEnabled(true);
            }
        });

        blockingNA.setChecked(model.isChecked(Model.Keys.BLOCKING_NA));
        blockingNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blockingInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        blockingInstruction.setAdapter(adapter);
        if(blockingReviewed.isChecked()) {
                blockingInstruction.setText(model.getValue(Model.Keys.BLOCKING_INSTRUCTION));
        }
        else blockingInstruction.setEnabled(false);

        // WALL SHEATHING
        wallSheathingReviewed.setChecked(model.isChecked(Model.Keys.WALLSHEATHING_REVIEWED));
        wallSheathingReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wallSheathingInstruction.setEnabled(true);
            }
        });

        wallSheathingNA.setChecked(model.isChecked(Model.Keys.WALLSHEATHING_NA));
        wallSheathingNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wallSheathingInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        wallSheathingInstruction.setAdapter(adapter);
        if(wallSheathingReviewed.isChecked()) {
            wallSheathingInstruction.setText(model.getValue(Model.Keys.WALLSHEATHING_INSTRUCTION));
        }
        else wallSheathingInstruction.setEnabled(false);

        // WALL GIRTS
        windGirtsReviewed.setChecked(model.isChecked(Model.Keys.WINDGIRTS_REVIEWED));
        windGirtsReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windGirtsInstruction.setEnabled(true);
            }
        });

        windGirtsNA.setChecked(model.isChecked(Model.Keys.WINDGIRTS_NA));
        windGirtsNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windGirtsInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cityProv));
        windGirtsInstruction.setAdapter(adapter);
        if(windGirtsReviewed.isChecked()) {
            windGirtsInstruction.setText(model.getValue(Model.Keys.WINDGIRTS_INSTRUCTION));
        }
        else windGirtsInstruction.setEnabled(false);
    }

    private void init() {
        model = (Model) getApplicationContext();

        // TRUSS SPEC
        trussSpecReviewed = (RadioButton) findViewById(R.id.radioButtonTrussSpecReviewed);
        trussSpecNA = (RadioButton) findViewById(R.id.radioButtonTrussSpecNA);
        trussSpecInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteTrussSpecInstruction);

        // IJOIST
        iJoistReviewed = (RadioButton) findViewById(R.id.radioButtonIJoistReviewed);
        iJoistNA = (RadioButton) findViewById(R.id.radioButtonIJoistNA);
        iJoistInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteIJoistInstruction);

        // BEARING
        bearingReviewed = (RadioButton) findViewById(R.id.radioButtonBearingReviewed);
        bearingNA = (RadioButton) findViewById(R.id.radioButtonBearingNA);
        bearingInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteBearingInstruction);

        // TOP PLATES
        topPlatesReviewed = (RadioButton) findViewById(R.id.radioButtonTopPlatesReviewed);
        topPlatesNA = (RadioButton) findViewById(R.id.radioButtonTopPlatesNA);
        topPlatesInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteTopPlatesInstruction);

        // LINTELS
        lintelsReviewed = (RadioButton) findViewById(R.id.radioButtonLintelsReviewed);
        lintelsNA = (RadioButton) findViewById(R.id.radioButtonLintelsNA);
        lintelsInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteLintelsInstruction);

        // SHEARWALLS
        shearwallsReviewed = (RadioButton) findViewById(R.id.radioButtonShearwallsReviewed);
        shearwallsNA = (RadioButton) findViewById(R.id.radioButtonShearwallsNA);
        shearwallsInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteShearwallsInstruction);

        // TALL WALLS
        tallWallsReviewed = (RadioButton) findViewById(R.id.radioButtonTallWallsReviewed);
        tallWallsNA = (RadioButton) findViewById(R.id.radioButtonTallWallsNA);
        tallWallsInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteTallWallsInstruction);

        // BLOCKING
        blockingReviewed = (RadioButton) findViewById(R.id.radioButtonBlockingReviewed);
        blockingNA = (RadioButton) findViewById(R.id.radioButtonBlockingNA);
        blockingInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteBlockingInstruction);

        // WALL SHEATHING
        wallSheathingReviewed = (RadioButton) findViewById(R.id.radioButtonWallSheathingReviewed);
        wallSheathingNA = (RadioButton) findViewById(R.id.radioButtonWallSheathingNA);
        wallSheathingInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteWallSheathingInstruction);

        // WIND GIRTS
        windGirtsReviewed = (RadioButton) findViewById(R.id.radioButtonWindGirtsReviewed);
        windGirtsNA = (RadioButton) findViewById(R.id.radioButtonWindGirtsNA);
        windGirtsInstruction = (AutoCompleteTextView) findViewById(R.id.autoCompleteWindGirtsInstruction);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // TRUSS SPEC
        if(trussSpecReviewed.isChecked()) {
            model.updateValue(Model.Keys.TRUSS_SPEC_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.TRUSS_SPEC_NA, Model.SpecialValue.NO.toString());
            if(trussSpecInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.TRUSS_SPEC_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.TRUSS_SPEC_INSTRUCTION, trussSpecInstruction.getText().toString());
        } else if(trussSpecNA.isChecked()) {
            model.updateValue(Model.Keys.TRUSS_SPEC_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.TRUSS_SPEC_NA,Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.TRUSS_SPEC_INSTRUCTION, Model.SpecialValue.NA.toString());
        }

        // IJOIST
        if(iJoistReviewed.isChecked()) {
            model.updateValue(Model.Keys.IJOIST_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.IJOIST_NA, Model.SpecialValue.NO.toString());
            if(iJoistInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.IJOIST_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.IJOIST_INSTRUCTION, iJoistInstruction.getText().toString());
        } else if(iJoistNA.isChecked()) {
            model.updateValue(Model.Keys.IJOIST_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.IJOIST_NA, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.IJOIST_INSTRUCTION, Model.SpecialValue.NA.toString());
        }

        // BEARING
        if(bearingReviewed.isChecked()) {
            model.updateValue(Model.Keys.BEARING_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.BEARING_NA, Model.SpecialValue.NO.toString());
            if(bearingInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.BEARING_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.BEARING_INSTRUCTION, bearingInstruction.getText().toString());
        } else if(bearingNA.isChecked()) {
            model.updateValue(Model.Keys.BEARING_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.BEARING_NA, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.BEARING_INSTRUCTION, Model.SpecialValue.NA.toString());
        }

        // TOP PLATES
        if(topPlatesReviewed.isChecked()) {
            model.updateValue(Model.Keys.TOP_PLATES_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.TOP_PLATES_NA, Model.SpecialValue.NO.toString());
            if(topPlatesInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.TOP_PLATES_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.TOP_PLATES_INSTRUCTION, topPlatesInstruction.getText().toString());
        } else if(topPlatesNA.isChecked()) {
            model.updateValue(Model.Keys.TOP_PLATES_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.TOP_PLATES_NA, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.TOP_PLATES_INSTRUCTION, Model.SpecialValue.NA.toString());
        }

        // LINTELS
        if(lintelsReviewed.isChecked()) {
            model.updateValue(Model.Keys.LINTELS_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.LINTELS_NA, Model.SpecialValue.NO.toString());
            if(lintelsInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.LINTELS_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.LINTELS_INSTRUCTION, lintelsInstruction.getText().toString());
        } else if(lintelsNA.isChecked()) {
            model.updateValue(Model.Keys.LINTELS_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.LINTELS_NA, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.LINTELS_INSTRUCTION, Model.SpecialValue.NA.toString());
        }

        // SHEARWALLS
        if(shearwallsReviewed.isChecked()) {
            model.updateValue(Model.Keys.SHEARWALLS_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.SHEARWALLS_NA, Model.SpecialValue.NO.toString());
            if(shearwallsInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.SHEARWALLS_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.SHEARWALLS_INSTRUCTION, shearwallsInstruction.getText().toString());
        } else if(shearwallsNA.isChecked()) {
            model.updateValue(Model.Keys.SHEARWALLS_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.SHEARWALLS_NA, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.SHEARWALLS_INSTRUCTION, Model.SpecialValue.NA.toString());
        }

        // TALL WALLS
        if(tallWallsReviewed.isChecked()) {
            model.updateValue(Model.Keys.TALLWALLS_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.TALLWALLS_NA, Model.SpecialValue.NO.toString());
            if(tallWallsInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.TALLWALLS_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.TALLWALLS_INSTRUCTION, tallWallsInstruction.getText().toString());
        } else if(tallWallsNA.isChecked()) {
            model.updateValue(Model.Keys.TALLWALLS_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.TALLWALLS_NA, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.TALLWALLS_INSTRUCTION, Model.SpecialValue.NA.toString());
        }

        // BLOCKING
        if(blockingReviewed.isChecked()) {
            model.updateValue(Model.Keys.BLOCKING_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.BLOCKING_NA, Model.SpecialValue.NO.toString());
            if(blockingInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.BLOCKING_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.BLOCKING_INSTRUCTION, blockingInstruction.getText().toString());
        } else if(blockingNA.isChecked()) {
            model.updateValue(Model.Keys.BLOCKING_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.BLOCKING_NA, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.BLOCKING_INSTRUCTION, Model.SpecialValue.NA.toString());
        }

        // WALL SHEATHING
        if(wallSheathingReviewed.isChecked()) {
            model.updateValue(Model.Keys.WALLSHEATHING_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.WALLSHEATHING_NA, Model.SpecialValue.NO.toString());
            if(wallSheathingInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.WALLSHEATHING_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.WALLSHEATHING_INSTRUCTION, wallSheathingInstruction.getText().toString());
        } else if(wallSheathingNA.isChecked()) {
            model.updateValue(Model.Keys.WALLSHEATHING_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.WALLSHEATHING_NA, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.WALLSHEATHING_INSTRUCTION, Model.SpecialValue.NA.toString());
        }

        // WIND GIRTS
        if(windGirtsReviewed.isChecked()) {
            model.updateValue(Model.Keys.WINDGIRTS_REVIEWED, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.WINDGIRTS_NA, Model.SpecialValue.NO.toString());
            if(windGirtsInstruction.getText().toString().equals(""))
                model.updateValue(Model.Keys.WINDGIRTS_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(Model.Keys.WINDGIRTS_INSTRUCTION, windGirtsInstruction.getText().toString());
        } else if(windGirtsNA.isChecked()) {
            model.updateValue(Model.Keys.WINDGIRTS_REVIEWED, Model.SpecialValue.NO.toString());
            model.updateValue(Model.Keys.WINDGIRTS_NA, Model.SpecialValue.YES.toString());
            model.updateValue(Model.Keys.WINDGIRTS_INSTRUCTION, Model.SpecialValue.NA.toString());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

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
        trussSpecReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC));
        trussSpecReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trussSpecInstruction.setEnabled(true);
            }
        });

        trussSpecNA.setChecked(!trussSpecReviewed.isChecked());
        trussSpecNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trussSpecInstruction.setEnabled(false);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_INSTRUCTION, null, null));
        trussSpecInstruction.setAdapter(adapter);
        if(trussSpecReviewed.isChecked()) {
            trussSpecInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_INSTRUCTION));
        }
        else trussSpecInstruction.setEnabled(false);

        // IJOIST SPEC
        iJoistReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.IJOIST));
        iJoistReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iJoistInstruction.setEnabled(true);
            }
        });

        iJoistNA.setChecked(!iJoistReviewed.isChecked());
        iJoistNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iJoistInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.IJOIST_INSTRUCTION, null, null));
        iJoistInstruction.setAdapter(adapter);
        if(iJoistReviewed.isChecked()) {
            iJoistInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.IJOIST_INSTRUCTION));
        }
        else iJoistInstruction.setEnabled(false);

        // BEARING
        bearingReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.BEARING));
        bearingReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bearingInstruction.setEnabled(true);
            }
        });

        bearingNA.setChecked(!bearingReviewed.isChecked());
        bearingNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bearingInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.BEARING_INSTRUCTION, null, null));
        bearingInstruction.setAdapter(adapter);
        if( bearingReviewed.isChecked()) {
            bearingInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.BEARING_INSTRUCTION));
        }
        else bearingInstruction.setEnabled(false);

        // TOP PLATES
        topPlatesReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.TOP_PLATES));
        topPlatesReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topPlatesInstruction.setEnabled(true);
            }
        });

        topPlatesNA.setChecked(!topPlatesReviewed.isChecked());
        topPlatesNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topPlatesInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.TOP_PLATES_INSTRUCTION, null, null));
        topPlatesInstruction.setAdapter(adapter);
        if(topPlatesReviewed.isChecked()) {
                topPlatesInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES_INSTRUCTION));
        }
        else topPlatesInstruction.setEnabled(false);

        // LINTELS
        lintelsReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.LINTELS));
        lintelsReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lintelsInstruction.setEnabled(true);
            }
        });

        lintelsNA.setChecked(!lintelsReviewed.isChecked());
        lintelsNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lintelsInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.LINTELS_INSTRUCTION, null, null));
        lintelsInstruction.setAdapter(adapter);
        if(lintelsReviewed.isChecked()) {
            lintelsInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.LINTELS_INSTRUCTION));
        }
        else lintelsInstruction.setEnabled(false);

        // SHEARWALLS
        shearwallsReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.SHEARWALLS));
        shearwallsReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shearwallsInstruction.setEnabled(true);
            }
        });

        shearwallsNA.setChecked(!shearwallsReviewed.isChecked());
        shearwallsNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shearwallsInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.SHEARWALLS_INSTRUCTION, null, null));
        shearwallsInstruction.setAdapter(adapter);
        if(shearwallsReviewed.isChecked()) {
            shearwallsInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS_INSTRUCTION));
        }
        else shearwallsInstruction.setEnabled(false);

        // TALL WALLS
        tallWallsReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.TALL_WALLS));
        tallWallsReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tallWallsInstruction.setEnabled(true);
            }
        });

        tallWallsNA.setChecked(!tallWallsReviewed.isChecked());
        tallWallsNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tallWallsInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.TALL_WALLS_INSTRUCTION, null, null));
        tallWallsInstruction.setAdapter(adapter);
        if(tallWallsReviewed.isChecked()) {
            tallWallsInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS_INSTRUCTION));
        }
        else tallWallsInstruction.setEnabled(false);

        // BLOCKING
        blockingReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.BLOCKING));
        blockingReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blockingInstruction.setEnabled(true);
            }
        });

        blockingNA.setChecked(!blockingReviewed.isChecked());
        blockingNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blockingInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.BLOCKING_INSTRUCTION, null, null));
        blockingInstruction.setAdapter(adapter);
        if(blockingReviewed.isChecked()) {
                blockingInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.BLOCKING_INSTRUCTION));
        }
        else blockingInstruction.setEnabled(false);

        // WALL SHEATHING
        wallSheathingReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING));
        wallSheathingReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wallSheathingInstruction.setEnabled(true);
            }
        });

        wallSheathingNA.setChecked(!wallSheathingReviewed.isChecked());
        wallSheathingNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wallSheathingInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_INSTRUCTION, null, null));
        wallSheathingInstruction.setAdapter(adapter);
        if(wallSheathingReviewed.isChecked()) {
            wallSheathingInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_INSTRUCTION));
        }
        else wallSheathingInstruction.setEnabled(false);

        // WALL GIRTS
        windGirtsReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.WIND_GIRTS));
        windGirtsReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windGirtsInstruction.setEnabled(true);
            }
        });

        windGirtsNA.setChecked(!windGirtsReviewed.isChecked());
        windGirtsNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windGirtsInstruction.setEnabled(false);
            }
        });

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_INSTRUCTION, null, null));
        windGirtsInstruction.setAdapter(adapter);
        if(windGirtsReviewed.isChecked()) {
            windGirtsInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_INSTRUCTION));
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
            model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC, Model.SpecialValue.YES.toString());
            if(trussSpecInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_INSTRUCTION, trussSpecInstruction.getText().toString());
        } else if(trussSpecNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC, Model.SpecialValue.EMPTY.toString());

        // IJOIST
        if(iJoistReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST, Model.SpecialValue.YES.toString());
            if(iJoistInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST_INSTRUCTION, iJoistInstruction.getText().toString());
        } else if(iJoistNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST, Model.SpecialValue.EMPTY.toString());

        // BEARING
        if(bearingReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING, Model.SpecialValue.YES.toString());
            if(bearingInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING_INSTRUCTION, bearingInstruction.getText().toString());
        } else if(bearingNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING, Model.SpecialValue.EMPTY.toString());

        // TOP PLATES
        if(topPlatesReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES, Model.SpecialValue.YES.toString());
            if(topPlatesInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES_INSTRUCTION, topPlatesInstruction.getText().toString());
        } else if(topPlatesNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES, Model.SpecialValue.EMPTY.toString());

        // LINTELS
        if(lintelsReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS, Model.SpecialValue.YES.toString());
            if(lintelsInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS_INSTRUCTION, lintelsInstruction.getText().toString());
        } else if(lintelsNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS, Model.SpecialValue.EMPTY.toString());

        // SHEARWALLS
        if(shearwallsReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS, Model.SpecialValue.YES.toString());
            if(shearwallsInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS_INSTRUCTION, shearwallsInstruction.getText().toString());
        } else if(shearwallsNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS, Model.SpecialValue.EMPTY.toString());

        // TALL WALLS
        if(tallWallsReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS, Model.SpecialValue.YES.toString());
            if(tallWallsInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS_INSTRUCTION, tallWallsInstruction.getText().toString());
        } else if(tallWallsNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS, Model.SpecialValue.EMPTY.toString());

        // BLOCKING
        if(blockingReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING, Model.SpecialValue.YES.toString());
            if(blockingInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING_INSTRUCTION, blockingInstruction.getText().toString());
        } else if(blockingNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING, Model.SpecialValue.EMPTY.toString());

        // WALL SHEATHING
        if(wallSheathingReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING, Model.SpecialValue.YES.toString());
            if(wallSheathingInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_INSTRUCTION, wallSheathingInstruction.getText().toString());
        } else if(wallSheathingNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING, Model.SpecialValue.EMPTY.toString());

        // WIND GIRTS
        if(windGirtsReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS, Model.SpecialValue.YES.toString());
            if(windGirtsInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_INSTRUCTION, windGirtsInstruction.getText().toString());
        } else if(windGirtsNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS, Model.SpecialValue.EMPTY.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

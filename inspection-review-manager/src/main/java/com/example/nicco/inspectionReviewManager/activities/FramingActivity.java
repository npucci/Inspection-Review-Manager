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

public class FramingActivity extends AppCompatActivity implements AutoFillActivity {
    private Model model;

    // TRUSS SPEC
    private RadioButton trussSpecReviewed;
    private RadioButton trussSpecNA;
    private QueryingAutoCompleteTextView trussSpecInstruction;

    // IJOIST
    private RadioButton iJoistReviewed;
    private RadioButton iJoistNA;
    private QueryingAutoCompleteTextView iJoistInstruction;

    // BEARING
    private RadioButton bearingReviewed;
    private RadioButton bearingNA;
    private QueryingAutoCompleteTextView bearingInstruction;

    // TOP PLATES
    private RadioButton topPlatesReviewed;
    private RadioButton topPlatesNA;
    private QueryingAutoCompleteTextView topPlatesInstruction;


    // LINTELS
    private RadioButton lintelsReviewed;
    private RadioButton lintelsNA;
    private QueryingAutoCompleteTextView lintelsInstruction;


    // SHEARWALLS
    private RadioButton shearwallsReviewed;
    private RadioButton shearwallsNA;
    private QueryingAutoCompleteTextView shearwallsInstruction;

    // TALL WALLS
    private RadioButton tallWallsReviewed;
    private RadioButton tallWallsNA;
    private QueryingAutoCompleteTextView tallWallsInstruction;

    // BLOCKING
    private RadioButton blockingReviewed;
    private RadioButton blockingNA;
    private QueryingAutoCompleteTextView blockingInstruction;

    // WALL SHEATHING
    private RadioButton wallSheathingReviewed;
    private RadioButton wallSheathingNA;
    private QueryingAutoCompleteTextView wallSheathingInstruction;

    // WIND GIRTS
    private RadioButton windGirtsReviewed;
    private RadioButton windGirtsNA;
    private QueryingAutoCompleteTextView windGirtsInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framing);

        initViews();
        initValues();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
    }

    private void initViews() {
        model = (Model) getApplicationContext();

        // TRUSS SPEC
        trussSpecReviewed = (RadioButton) findViewById(R.id.radioButtonTrussSpecReviewed);
        trussSpecNA = (RadioButton) findViewById(R.id.radioButtonTrussSpecNA);
        trussSpecInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteTrussSpecInstruction);
        trussSpecInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_INSTRUCTION.getValue(), null);

        // IJOIST
        iJoistReviewed = (RadioButton) findViewById(R.id.radioButtonIJoistReviewed);
        iJoistNA = (RadioButton) findViewById(R.id.radioButtonIJoistNA);
        iJoistInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteIJoistInstruction);
        iJoistInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.IJOIST_INSTRUCTION.getValue(), null);

        // BEARING
        bearingReviewed = (RadioButton) findViewById(R.id.radioButtonBearingReviewed);
        bearingNA = (RadioButton) findViewById(R.id.radioButtonBearingNA);
        bearingInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteBearingInstruction);
        bearingInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.BEARING_INSTRUCTION.getValue(), null);

        // TOP PLATES
        topPlatesReviewed = (RadioButton) findViewById(R.id.radioButtonTopPlatesReviewed);
        topPlatesNA = (RadioButton) findViewById(R.id.radioButtonTopPlatesNA);
        topPlatesInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteTopPlatesInstruction);
        topPlatesInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.TOP_PLATES_INSTRUCTION.getValue(), null);

        // LINTELS
        lintelsReviewed = (RadioButton) findViewById(R.id.radioButtonLintelsReviewed);
        lintelsNA = (RadioButton) findViewById(R.id.radioButtonLintelsNA);
        lintelsInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteLintelsInstruction);
        lintelsInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.LINTELS_INSTRUCTION.getValue(), null);

        // SHEARWALLS
        shearwallsReviewed = (RadioButton) findViewById(R.id.radioButtonShearwallsReviewed);
        shearwallsNA = (RadioButton) findViewById(R.id.radioButtonShearwallsNA);
        shearwallsInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteShearwallsInstruction);
        shearwallsInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.SHEARWALLS_INSTRUCTION.getValue(), null);

        // TALL WALLS
        tallWallsReviewed = (RadioButton) findViewById(R.id.radioButtonTallWallsReviewed);
        tallWallsNA = (RadioButton) findViewById(R.id.radioButtonTallWallsNA);
        tallWallsInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteTallWallsInstruction);
        tallWallsInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.TALL_WALLS_INSTRUCTION.getValue(), null);

        // BLOCKING
        blockingReviewed = (RadioButton) findViewById(R.id.radioButtonBlockingReviewed);
        blockingNA = (RadioButton) findViewById(R.id.radioButtonBlockingNA);
        blockingInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteBlockingInstruction);
        blockingInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.BLOCKING_INSTRUCTION.getValue(), null);

        // WALL SHEATHING
        wallSheathingReviewed = (RadioButton) findViewById(R.id.radioButtonWallSheathingReviewed);
        wallSheathingNA = (RadioButton) findViewById(R.id.radioButtonWallSheathingNA);
        wallSheathingInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteWallSheathingInstruction);
        wallSheathingInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_INSTRUCTION.getValue(), null);

        // WIND GIRTS
        windGirtsReviewed = (RadioButton) findViewById(R.id.radioButtonWindGirtsReviewed);
        windGirtsNA = (RadioButton) findViewById(R.id.radioButtonWindGirtsNA);
        windGirtsInstruction = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteWindGirtsInstruction);
        windGirtsInstruction.set(this, model, this, DatabaseWriter.REVIEW_TABLE_NAME, DatabaseWriter.UIComponentInputValue.WIND_GIRTS_INSTRUCTION.getValue(), null);
    }

    private void initValues() {
        // TRUSS SPEC
        trussSpecReviewed.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC));
        trussSpecReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trussSpecInstruction.setEnabled(true);
            }
        });
        if(model.getValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC).equals(Model.SpecialValue.NO.toString())) {
            trussSpecNA.setChecked(true);
        } else trussSpecNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_NA));
        trussSpecNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trussSpecInstruction.setEnabled(false);
            }
        });
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

        if(model.getValue(DatabaseWriter.UIComponentInputValue.IJOIST).equals(Model.SpecialValue.NO.toString())) {
            iJoistNA.setChecked(true);
        } else iJoistNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.IJOIST_NA));
        iJoistNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iJoistInstruction.setEnabled(false);
            }
        });
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

        if(model.getValue(DatabaseWriter.UIComponentInputValue.BEARING).equals(Model.SpecialValue.NO.toString())) {
            bearingNA.setChecked(true);
        } else bearingNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.BEARING_NA));
        bearingNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bearingInstruction.setEnabled(false);
            }
        });
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

        if(model.getValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES).equals(Model.SpecialValue.NO.toString())) {
            topPlatesNA.setChecked(true);
        } else topPlatesNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.TOP_PLATES_NA));
        topPlatesNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topPlatesInstruction.setEnabled(false);
            }
        });
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

        if(model.getValue(DatabaseWriter.UIComponentInputValue.LINTELS).equals(Model.SpecialValue.NO.toString())) {
            lintelsNA.setChecked(true);
        } else lintelsNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.LINTELS_NA));
        lintelsNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lintelsInstruction.setEnabled(false);
            }
        });
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

        if(model.getValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS).equals(Model.SpecialValue.NO.toString())) {
            shearwallsNA.setChecked(true);
        } else shearwallsNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.SHEARWALLS_NA));
        shearwallsNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shearwallsInstruction.setEnabled(false);
            }
        });
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

        if(model.getValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS).equals(Model.SpecialValue.NO.toString())) {
            tallWallsNA.setChecked(true);
        } else tallWallsNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.TALL_WALLS_NA));
        tallWallsNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tallWallsInstruction.setEnabled(false);
            }
        });
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

        if(model.getValue(DatabaseWriter.UIComponentInputValue.BLOCKING).equals(Model.SpecialValue.NO.toString())) {
            blockingNA.setChecked(true);
        } else
            blockingNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.BLOCKING_NA));
        blockingNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blockingInstruction.setEnabled(false);
            }
        });
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

        if(model.getValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING).equals(Model.SpecialValue.NO.toString())) {
            wallSheathingNA.setChecked(true);
        } else wallSheathingNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_NA));
        wallSheathingNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wallSheathingInstruction.setEnabled(false);
            }
        });
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

        if(model.getValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS).equals(Model.SpecialValue.NO.toString())) {
            windGirtsNA.setChecked(true);
        } else windGirtsNA.setChecked(model.isChecked(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_NA));
        windGirtsNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windGirtsInstruction.setEnabled(false);
            }
        });
        if(windGirtsReviewed.isChecked()) {
            windGirtsInstruction.setText(model.getValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_INSTRUCTION));
        }
        else windGirtsInstruction.setEnabled(false);

    }

    @Override
    protected void onPause() {
        super.onPause();

        // TRUSS SPEC
        if(trussSpecReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_NA, Model.SpecialValue.NO.toString());
            if(trussSpecInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_INSTRUCTION, trussSpecInstruction.getText().toString());
        } else if(trussSpecNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC, Model.SpecialValue.EMPTY.toString());

        // IJOIST
        if(iJoistReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST_NA, Model.SpecialValue.NO.toString());
            if(iJoistInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST_INSTRUCTION, iJoistInstruction.getText().toString());
        } else if(iJoistNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.IJOIST, Model.SpecialValue.EMPTY.toString());

        // BEARING
        if(bearingReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING_NA, Model.SpecialValue.NO.toString());
            if(bearingInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING_INSTRUCTION, bearingInstruction.getText().toString());
        } else if(bearingNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.BEARING, Model.SpecialValue.EMPTY.toString());

        // TOP PLATES
        if(topPlatesReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES_NA, Model.SpecialValue.NO.toString());
            if(topPlatesInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES_INSTRUCTION, topPlatesInstruction.getText().toString());
        } else if(topPlatesNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES, Model.SpecialValue.EMPTY.toString());

        // LINTELS
        if(lintelsReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS_NA, Model.SpecialValue.NO.toString());
            if(lintelsInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS_INSTRUCTION, lintelsInstruction.getText().toString());
        } else if(lintelsNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.LINTELS, Model.SpecialValue.EMPTY.toString());

        // SHEARWALLS
        if(shearwallsReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS_NA, Model.SpecialValue.NO.toString());
            if(shearwallsInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS_INSTRUCTION, shearwallsInstruction.getText().toString());
        } else if(shearwallsNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS, Model.SpecialValue.EMPTY.toString());

        // TALL WALLS
        if(tallWallsReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS_NA, Model.SpecialValue.NO.toString());
            if(tallWallsInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS_INSTRUCTION, tallWallsInstruction.getText().toString());
        } else if(tallWallsNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS, Model.SpecialValue.EMPTY.toString());

        // BLOCKING
        if(blockingReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING_NA, Model.SpecialValue.NO.toString());
            if(blockingInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING_INSTRUCTION, blockingInstruction.getText().toString());
        } else if(blockingNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING, Model.SpecialValue.EMPTY.toString());

        // WALL SHEATHING
        if(wallSheathingReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_NA, Model.SpecialValue.NO.toString());
            if(wallSheathingInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_INSTRUCTION, wallSheathingInstruction.getText().toString());
        } else if(wallSheathingNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING, Model.SpecialValue.EMPTY.toString());

        // WIND GIRTS
        if(windGirtsReviewed.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_NA, Model.SpecialValue.NO.toString());
            if(windGirtsInstruction.getText().toString().equals(""))
                model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_INSTRUCTION, Model.SpecialValue.NONE.toString());
            else
                model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_INSTRUCTION, windGirtsInstruction.getText().toString());
        } else if(windGirtsNA.isChecked()) {
            model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS, Model.SpecialValue.NO.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_NA, Model.SpecialValue.YES.toString());
            model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_INSTRUCTION, Model.SpecialValue.NA.toString());
        }  else model.updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS, Model.SpecialValue.EMPTY.toString());

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initValues();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
    }

    private void setTextSize(float textSize) {
        TextView trussSpecLabel = (TextView) findViewById(R.id.textViewTrussSpec);
        trussSpecLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        trussSpecReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        trussSpecNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        trussSpecInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView trussSpecInstructionLabel = (TextView) findViewById(R.id.textViewTrussSpecInstruction);
        trussSpecInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView iJoistLabel = (TextView) findViewById(R.id.textViewIJoist);
        iJoistLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        iJoistReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        iJoistNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        iJoistInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView iJoistInstructionLabel = (TextView) findViewById(R.id.textViewIJoistInstruction);
        iJoistInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView bearingLabel = (TextView) findViewById(R.id.textViewBearing);
        bearingLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        bearingReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        bearingNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        bearingInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView bearingInstructionLabel = (TextView) findViewById(R.id.textViewBearingInstruction);
        bearingInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView topPlatesLabel = (TextView) findViewById(R.id.textViewTopPlates);
        topPlatesLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        topPlatesReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        topPlatesNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        topPlatesInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView topPlatesInstructionLabel = (TextView) findViewById(R.id.textViewTopPlatesInstruction);
        topPlatesInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView lintelsLabel = (TextView) findViewById(R.id.textViewLintels);
        lintelsLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        lintelsReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        lintelsNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        lintelsInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView lintelsInstructionLabel = (TextView) findViewById(R.id.textViewLintelsInstruction);
        lintelsInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView shearwallsLabel = (TextView) findViewById(R.id.textViewShearwalls);
        shearwallsLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        shearwallsReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        shearwallsNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        shearwallsInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView shearwallsInstructionLabel = (TextView) findViewById(R.id.textViewShearwallsInstruction);
        shearwallsInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView tallWallsLabel = (TextView) findViewById(R.id.textViewTallWalls);
        tallWallsLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        tallWallsReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        tallWallsNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        tallWallsInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView tallWallsInstructionLabel = (TextView) findViewById(R.id.textViewTallWallsInstruction);
        tallWallsInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView blockingLabel = (TextView) findViewById(R.id.textViewBlocking);
        blockingLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        blockingReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        blockingNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        blockingInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView blockingInstructionLabel = (TextView) findViewById(R.id.textViewBlockingInstruction);
        blockingInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView wallSheathingLabel = (TextView) findViewById(R.id.textViewWallSheathing);
        wallSheathingLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        wallSheathingReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        wallSheathingNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        wallSheathingInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView wallSheathingInstructionLabel = (TextView) findViewById(R.id.textViewWallSheathingInstruction);
        wallSheathingInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView windGirtsLabel = (TextView) findViewById(R.id.textViewWindGirts);
        windGirtsLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        windGirtsReviewed.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        windGirtsNA.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        windGirtsInstruction.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView windGirtsInstructionLabel = (TextView) findViewById(R.id.textViewWindGirtsInstruction);
        windGirtsInstructionLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    @Override
    public void autofill(Object uiComponent) {}
}

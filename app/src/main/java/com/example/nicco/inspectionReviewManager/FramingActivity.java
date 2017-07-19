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
        trussSpecReviewed.setChecked(model.getTrussSpecReviewed());
        trussSpecReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trussSpecInstruction.setEnabled(true);
            }
        });

        trussSpecNA.setChecked(model.getTrussSpecNA());
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
            if(model.getTrussSpecInstruction() != null) {
                trussSpecInstruction.setText(model.getTrussSpecInstruction());
            }
        }
        else trussSpecInstruction.setEnabled(false);

        // IJOIST SPEC
        iJoistReviewed.setChecked(model.getIJoistReviewed());
        iJoistReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iJoistInstruction.setEnabled(true);
            }
        });

        iJoistNA.setChecked(model.getIJoistNA());
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
            if(model.getIJoistInstruction() != null) {
                iJoistInstruction.setText(model.getIJoistInstruction());
            }
        }
        else iJoistInstruction.setEnabled(false);

        // BEARING
        bearingReviewed.setChecked(model.getBearingReviewed());
        bearingReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bearingInstruction.setEnabled(true);
            }
        });

        bearingNA.setChecked(model.getBearingNA());
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
            if(model.getBearingInstruction() != null) {
                bearingInstruction.setText(model.getBearingInstruction());
            }
        }
        else bearingInstruction.setEnabled(false);

        // TOP PLATES
        topPlatesReviewed.setChecked(model.getTopPlatesReviewed());
        topPlatesReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topPlatesInstruction.setEnabled(true);
            }
        });

        topPlatesNA.setChecked(model.getTopPlatesNA());
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
            if(model.getTopPlatesInstruction() != null) {
                topPlatesInstruction.setText(model.getTopPlatesInstruction());
            }
        }
        else topPlatesInstruction.setEnabled(false);

        // LINTELS
        lintelsReviewed.setChecked(model.getLintelsReviewed());
        lintelsReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lintelsInstruction.setEnabled(true);
            }
        });

        lintelsNA.setChecked(model.getLintelsNA());
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
            if(model.getLintelsInstruction() != null) {
                lintelsInstruction.setText(model.getLintelsInstruction());
            }
        }
        else lintelsInstruction.setEnabled(false);

        // SHEARWALLS
        shearwallsReviewed.setChecked(model.getShearwallsReviewed());
        shearwallsReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shearwallsInstruction.setEnabled(true);
            }
        });

        shearwallsNA.setChecked(model.getShearwallsNA());
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
            if(model.getShearwallsInstruction() != null) {
                shearwallsInstruction.setText(model.getShearwallsInstruction());
            }
        }
        else shearwallsInstruction.setEnabled(false);

        // TALL WALLS
        tallWallsReviewed.setChecked(model.getTallWallsReviewed());
        tallWallsReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tallWallsInstruction.setEnabled(true);
            }
        });

        tallWallsNA.setChecked(model.getTallWallsNA());
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
            if(model.getTallWallsInstruction() != null) {
                tallWallsInstruction.setText(model.getTallWallsInstruction());
            }
        }
        else tallWallsInstruction.setEnabled(false);

        // BLOCKING
        blockingReviewed.setChecked(model.getBlockingReviewed());
        blockingReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blockingInstruction.setEnabled(true);
            }
        });

        blockingNA.setChecked(model.getBlockingNA());
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
            if(model.getBlockingInstruction() != null) {
                blockingInstruction.setText(model.getBlockingInstruction());
            }
        }
        else blockingInstruction.setEnabled(false);

        // WALL SHEATHING
        wallSheathingReviewed.setChecked(model.getWallSheathingReviewed());
        wallSheathingReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wallSheathingInstruction.setEnabled(true);
            }
        });

        wallSheathingNA.setChecked(model.getWallSheathingNA());
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
            if(model.getWallSheathingInstruction() != null) {
                wallSheathingInstruction.setText(model.getWallSheathingInstruction());
            }
        }
        else wallSheathingInstruction.setEnabled(false);

        // WALL GIRTS
        windGirtsReviewed.setChecked(model.getWindGirtsReviewed());
        windGirtsReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windGirtsInstruction.setEnabled(true);
            }
        });

        windGirtsNA.setChecked(model.getWindGirtsNA());
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
            if(model.getWindGirtsInstruction() != null) {
                windGirtsInstruction.setText(model.getWindGirtsInstruction());
            }
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
    protected void onStop() {
        super.onStop();

        // TRUSS SPEC
        if(trussSpecReviewed.isChecked()) {
            model.updateTrussSpecReviewed(Model.SpecialValue.YES);
            model.updateTrussSpecNA(Model.SpecialValue.NO);
            if(trussSpecInstruction.getText().toString().equals(""))
                model.updateTrussSpecInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateTrussSpecInstruction(trussSpecInstruction.getText().toString());
        } else if(trussSpecNA.isChecked()) {
            model.updateTrussSpecReviewed(Model.SpecialValue.NO);
            model.updateTrussSpecNA(Model.SpecialValue.YES);
            model.updateTrussSpecInstruction(Model.SpecialValue.NA.toString());
        }

        // IJOIST
        if(iJoistReviewed.isChecked()) {
            model.updateIJoistReviewed(Model.SpecialValue.YES);
            model.updateIJoistNA(Model.SpecialValue.NO);
            if(iJoistInstruction.getText().toString().equals(""))
                model.updateIJoistInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateIJoistInstruction(iJoistInstruction.getText().toString());
        } else if(iJoistNA.isChecked()) {
            model.updateIJoistReviewed(Model.SpecialValue.NO);
            model.updateIJoistNA(Model.SpecialValue.YES);
            model.updateIJoistInstruction(Model.SpecialValue.NA.toString());
        }

        // BEARING
        if(bearingReviewed.isChecked()) {
            model.updateBearingReviewed(Model.SpecialValue.YES);
            model.updateBearingNA(Model.SpecialValue.NO);
            if(bearingInstruction.getText().toString().equals(""))
                model.updateBearingInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateBearingInstruction(bearingInstruction.getText().toString());
        } else if(bearingNA.isChecked()) {
            model.updateBearingReviewed(Model.SpecialValue.NO);
            model.updateBearingNA(Model.SpecialValue.YES);
            model.updateBearingInstruction(Model.SpecialValue.NA.toString());
        }

        // TOP PLATES
        if(topPlatesReviewed.isChecked()) {
            model.updateTopPlatesReviewed(Model.SpecialValue.YES);
            model.updateTopPlatesNA(Model.SpecialValue.NO);
            if(topPlatesInstruction.getText().toString().equals(""))
                model.updateTopPlatesInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateTopPlatesInstruction(topPlatesInstruction.getText().toString());
        } else if(topPlatesNA.isChecked()) {
            model.updateTopPlatesReviewed(Model.SpecialValue.NO);
            model.updateTopPlatesNA(Model.SpecialValue.YES);
            model.updateTopPlatesInstruction(Model.SpecialValue.NA.toString());
        }

        // LINTELS
        if(lintelsReviewed.isChecked()) {
            model.updateLintelsReviewed(Model.SpecialValue.YES);
            model.updateLintelsNA(Model.SpecialValue.NO);
            if(lintelsInstruction.getText().toString().equals(""))
                model.updateLintelsInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateLintelsInstruction(lintelsInstruction.getText().toString());
        } else if(lintelsNA.isChecked()) {
            model.updateLintelsReviewed(Model.SpecialValue.NO);
            model.updateLintelsNA(Model.SpecialValue.YES);
            model.updateLintelsInstruction(Model.SpecialValue.NA.toString());
        }

        // SHEARWALLS
        if(shearwallsReviewed.isChecked()) {
            model.updateShearwallsReviewed(Model.SpecialValue.YES);
            model.updateShearwallsNA(Model.SpecialValue.NO);
            if(shearwallsInstruction.getText().toString().equals(""))
                model.updateShearwallsInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateShearwallsInstruction(shearwallsInstruction.getText().toString());
        } else if(shearwallsNA.isChecked()) {
            model.updateShearwallsReviewed(Model.SpecialValue.NO);
            model.updateShearwallsNA(Model.SpecialValue.YES);
            model.updateShearwallsInstruction(Model.SpecialValue.NA.toString());
        }

        // TALL WALLS
        if(tallWallsReviewed.isChecked()) {
            model.updateTallWallsReviewed(Model.SpecialValue.YES);
            model.updateTallWallsNA(Model.SpecialValue.NO);
            if(tallWallsInstruction.getText().toString().equals(""))
                model.updateTallWallsInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateTallWallsInstruction(tallWallsInstruction.getText().toString());
        } else if(tallWallsNA.isChecked()) {
            model.updateTallWallsReviewed(Model.SpecialValue.NO);
            model.updateTallWallsNA(Model.SpecialValue.YES);
            model.updateTallWallsInstruction(Model.SpecialValue.NA.toString());
        }

        // BLOCKING
        if(blockingReviewed.isChecked()) {
            model.updateBlockingReviewed(Model.SpecialValue.YES);
            model.updateBlockingNA(Model.SpecialValue.NO);
            if(blockingInstruction.getText().toString().equals(""))
                model.updateBlockingInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateBlockingInstruction(blockingInstruction.getText().toString());
        } else if(blockingNA.isChecked()) {
            model.updateBlockingReviewed(Model.SpecialValue.NO);
            model.updateBlockingNA(Model.SpecialValue.YES);
            model.updateBlockingInstruction(Model.SpecialValue.NA.toString());
        }

        // WALL SHEATHING
        if(wallSheathingReviewed.isChecked()) {
            model.updateWallSheathingReviewed(Model.SpecialValue.YES);
            model.updateWallSheathingNA(Model.SpecialValue.NO);
            if(wallSheathingInstruction.getText().toString().equals(""))
                model.updateWallSheathingInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateWallSheathingInstruction(wallSheathingInstruction.getText().toString());
        } else if(wallSheathingNA.isChecked()) {
            model.updateWallSheathingReviewed(Model.SpecialValue.NO);
            model.updateWallSheathingNA(Model.SpecialValue.YES);
            model.updateWallSheathingInstruction(Model.SpecialValue.NA.toString());
        }

        // WIND GIRTS
        if(windGirtsReviewed.isChecked()) {
            model.updateWindGirtsReviewed(Model.SpecialValue.YES);
            model.updateWindGirtsNA(Model.SpecialValue.NO);
            if(windGirtsInstruction.getText().toString().equals(""))
                model.updateWindGirtsInstruction(Model.SpecialValue.NONE.toString());
            else
                model.updateWindGirtsInstruction(windGirtsInstruction.getText().toString());
        } else if(windGirtsNA.isChecked()) {
            model.updateWindGirtsReviewed(Model.SpecialValue.NO);
            model.updateWindGirtsNA(Model.SpecialValue.YES);
            model.updateWindGirtsInstruction(Model.SpecialValue.NA.toString());
        }
    }
}

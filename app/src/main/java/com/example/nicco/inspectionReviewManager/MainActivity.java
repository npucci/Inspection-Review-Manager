package com.example.nicco.inspectionReviewManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
    public static final String EXTRA_MESSAGE = "com.example.nicco.myfirstapp.MESSAGE";
    private Model model;
    private AlertDialog.Builder builder;
    private AlertDialog dialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Model model = (Model) getApplicationContext();
        final Button finishedButton = (Button) findViewById(R.id.buttonFinished);

        builder = new AlertDialog.Builder((Activity) MainActivity.this);
        builder.setMessage(R.string.dialogue_message)
                .setTitle(R.string.dialogue_title);
        // set buttons
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CharSequence message = "Review Updated";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(getApplicationContext(), message, duration);
                model.updateReviewToDatabase();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CharSequence message = "Review Not Updated";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(getApplicationContext(), message, duration);
                dialogInterface.dismiss();
            }
        });
        dialogue = builder.create();

        boolean ready = true;
        if(!model.checkDateActivityStatus()) ready = false;
        if(!model.checkProjectActivityStatus()) ready = false;
        if(!model.checkConcreteActivityStatus()) ready = false;
        if(!model.checkFramingActivityStatus()) ready = false;
        if(!model.checkConclusionActivityStatus()) ready = false;

        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(model.reviewExistsInDatabase()) {
                    dialogue.show();
                    Log.v("PUCCI", "Dialog creation CALLED");
                } else {
                    model.insertReviewToDatabase();
                }
                //model.exportReviewToDoc();
            }
        });

        finishedButton.setEnabled(ready);
    }
}

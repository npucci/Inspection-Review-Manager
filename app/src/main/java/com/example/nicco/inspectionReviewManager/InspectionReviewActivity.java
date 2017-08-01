package com.example.nicco.inspectionReviewManager;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InspectionReviewActivity extends FragmentActivity {
    private Model model;
    private AlertDialog.Builder builder;
    private AlertDialog dialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_review);

        model = (Model) getApplicationContext();

        builder = new AlertDialog.Builder((Activity) this);
        builder.setMessage(R.string.dialogue_message)
                .setTitle(R.string.dialogue_title);
        // set buttons
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CharSequence message = "Review Updated";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(getApplicationContext(), message, duration);
                toast.show();
                // update existing review recorded in database
                model.updateReviewToDatabase();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CharSequence message = "Review Not Updated";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(getApplicationContext(), message, duration);
                toast.show();
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

        final Button backButton = (Button) findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InspectionReviewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final Button finishedButton = (Button) findViewById(R.id.buttonFinished);
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(model.reviewExistsInDatabase()) {
                    dialogue.show();
                } else {
                    // insert review into database
                    model.insertReviewToDatabase();
                    model.reset();
                    Intent intent = new Intent(InspectionReviewActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        finishedButton.setEnabled(ready);

        final Button exportDocButton = (Button) findViewById(R.id.buttonExportDoc);
        exportDocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // export and open review as a doc file
                ActivityCompat.requestPermissions(InspectionReviewActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                int permission = ActivityCompat.checkSelfPermission(InspectionReviewActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(permission == PackageManager.PERMISSION_GRANTED) {
                    Log.v("PUCCI", "PERMISSION TO WRITE GRANTED");
                    model.exportReviewToDoc();
                }
                else Log.v("PUCCI", "ERROR: NO PERMISSION GRANTED TO WRITE TO EXTERNAL");
            }
        });
        exportDocButton.setEnabled(ready);
    }
}

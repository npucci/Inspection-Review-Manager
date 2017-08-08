package com.example.nicco.inspectionReviewManager;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InspectionReviewActivity extends FragmentActivity {
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_review);

        model = (Model) getApplicationContext();

        final Button backButton = (Button) findViewById(R.id.buttonBack);
        backButton.setTextSize(16);
        backButton.setPaintFlags(backButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButton.setTextColor(Color.BLACK);
                Intent intent = new Intent(InspectionReviewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final Button exportDocButton = (Button) findViewById(R.id.buttonExportDoc);
        exportDocButton.setTextSize(16);
        exportDocButton.setPaintFlags(exportDocButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        exportDocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportDocButton.setTextColor(Color.BLACK);
                // export and open review as a doc file
                ActivityCompat.requestPermissions(InspectionReviewActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                int permission = ActivityCompat.checkSelfPermission(InspectionReviewActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(permission == PackageManager.PERMISSION_GRANTED) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.exporting_doc_message, Toast.LENGTH_LONG);
                    toast.show();
                    model.exportReviewToDoc();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.write_permissions_denied, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        final Button finishedButton = (Button) findViewById(R.id.buttonFinished);
        finishedButton.setTextSize(16);
        finishedButton.setPaintFlags(finishedButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishedButton.setTextColor(Color.BLACK);
                if(model.reviewExistsInDatabase()) {
                    showUpdateReviewAlertDialogue();
                } else {
                    // insert review into database
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.saved_message, Toast.LENGTH_LONG);
                    toast.show();

                    model.insertReviewToDatabase();
                    model.reset();

                    Intent intent = new Intent(InspectionReviewActivity.this, MainActivity.class);
                    recreate();
                    startActivity(intent);
                }
            }
        });

        final Button closeButton = (Button) findViewById(R.id.buttonClose);
        closeButton.setPaintFlags(backButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButton.setTextColor(Color.BLACK);
                showCloseAlertDialogue();
            }
        });

        boolean ready = allInputFilled();
        finishedButton.setEnabled(ready);
        exportDocButton.setEnabled(ready);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final Button exportDocButton = (Button) findViewById(R.id.buttonExportDoc);
        final Button finishedButton = (Button) findViewById(R.id.buttonFinished);
        boolean ready = allInputFilled();
        finishedButton.setEnabled(ready);
        exportDocButton.setEnabled(ready);
    }

    private boolean allInputFilled() {
        boolean ready = true;
        if(!model.checkDateActivityStatus()) ready = false;
        if(!model.checkProjectActivityStatus()) ready = false;
        if(!model.checkConcreteActivityStatus()) ready = false;
        if(!model.checkFramingActivityStatus()) ready = false;
        if(!model.checkConclusionActivityStatus()) ready = false;
        return ready;
    }

    private void showUpdateReviewAlertDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialogue_update_review_message)
                .setTitle(R.string.dialogue_update_review_title);
        // set buttons
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.updated_message, Toast.LENGTH_LONG);
                toast.show();
                // update existing review recorded in database
                model.updateReviewToDatabase();
                model.reset();

                Intent intent = new Intent(InspectionReviewActivity.this, MainActivity.class);
                recreate();
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.not_updated_message, Toast.LENGTH_LONG);
                toast.show();
                dialogInterface.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void showCloseAlertDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialogue_close_review_message)
                .setTitle(R.string.dialogue_close_review_title);
        // set buttons
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                model.reset();
                Intent intent = new Intent(InspectionReviewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}

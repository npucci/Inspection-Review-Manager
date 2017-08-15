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
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InspectionReviewActivity extends FragmentActivity {
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_review);

        model = (Model) getApplicationContext();

        setTitle();

        final Button backButton = (Button) findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButton.setTextColor(Color.BLACK);
                Intent intent = new Intent(InspectionReviewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final Button exportDocButton = (Button) findViewById(R.id.buttonExportDoc);
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
                    model.exportReviewToDoc(getBaseContext());
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.write_permissions_denied, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        final Button finishedButton = (Button) findViewById(R.id.buttonFinished);
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

        setTextSize(getResources().getDimension(R.dimen.defaultTextSize));
        setTextUnderline();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle();

        final Button exportDocButton = (Button) findViewById(R.id.buttonExportDoc);
        final Button finishedButton = (Button) findViewById(R.id.buttonFinished);
        boolean ready = allInputFilled();
        finishedButton.setEnabled(ready);
        exportDocButton.setEnabled(ready);

        setTextSize(getResources().getDimension(R.dimen.defaultTextSize));
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

    private void setTitle() {
        final TextView titleTextView = (TextView) findViewById(R.id.textViewInspectionReviewTitle);
        String address = model.getValue(DatabaseWriter.UIComponentInputValue.ADDRESS);
        String city = model.getValue(DatabaseWriter.UIComponentInputValue.CITY);
        String province = model.getValue(DatabaseWriter.UIComponentInputValue.PROVINCE);

        if(!address.isEmpty() && !city.isEmpty() && !province.isEmpty())
            titleTextView.setText(address + ", " + city + ", " + province);
    }

    private void setTextSize(float textSize) {
        TextView titleTextView = (TextView) findViewById(R.id.textViewInspectionReviewTitle);
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        InspectionReviewListFragment listFragment = (InspectionReviewListFragment) getFragmentManager().findFragmentById(R.id.fragmentInspectionReview);
        listFragment.setTextSize(textSize);

        Button backButton = (Button) findViewById(R.id.buttonBack);
        backButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        Button exportDocButton = (Button) findViewById(R.id.buttonExportDoc);
        exportDocButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        Button finishedButton = (Button) findViewById(R.id.buttonFinished);
        finishedButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        Button closeButton = (Button) findViewById(R.id.buttonClose);
        closeButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    private void setTextUnderline() {
        Button backButton = (Button) findViewById(R.id.buttonBack);
        backButton.setPaintFlags(backButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Button exportDocButton = (Button) findViewById(R.id.buttonExportDoc);
        exportDocButton.setPaintFlags(exportDocButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Button finishedButton = (Button) findViewById(R.id.buttonFinished);
        finishedButton.setPaintFlags(finishedButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Button closeButton = (Button) findViewById(R.id.buttonClose);
        closeButton.setPaintFlags(closeButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}

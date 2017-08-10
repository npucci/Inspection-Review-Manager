package com.example.nicco.inspectionReviewManager;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends FragmentActivity implements RecyclerViewClickListener, ModelLoadListener {
    private HashMap<String, String> selectedArchiveReview = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Model model = (Model) getApplicationContext();

        final RecyclerView archive = (RecyclerView) findViewById(R.id.recyclerViewArchive);
        archive.setAdapter(new RecyclerAdapter(getApplicationContext(), this, model.getDatabaseCursor()));
        archive.setHasFixedSize(true);
        archive.setLayoutManager(new LinearLayoutManager(this));
        archive.setItemAnimator(new DefaultItemAnimator());
        archive.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.background));

        final Button selectButton = (Button) findViewById(R.id.buttonSelectReview);
        selectButton.setPaintFlags(selectButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        selectButton.setTextSize(16);
        selectButton.setEnabled(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition() > -1);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition() > -1) {
                    showSelectDialog();
                }
            }
        });

        final Button newReviewButton = (Button) findViewById(R.id.buttonInspectionReview);
        newReviewButton.setPaintFlags(newReviewButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        newReviewButton.setTextSize(16);
        if(model.reviewStarted()) newReviewButton.setText("Review In Progress:\nContinue >>");
        else  newReviewButton.setText("New Review");
        newReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newReviewButton.setTextColor(Color.BLACK);
                Intent intent = new Intent(MainActivity.this, InspectionReviewActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void recyclerViewListClicked(View view, int position) {
        final RecyclerView archive = (RecyclerView) findViewById(R.id.recyclerViewArchive);
        final Button selectButton = (Button) findViewById(R.id.buttonSelectReview);

        // double tapping the currently selected item deselects it
        if(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition() == position) {
            archive.getAdapter().notifyItemChanged(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition());
            ((RecyclerAdapter) archive.getAdapter()).setSelectedPosition(-1);
            archive.getAdapter().notifyItemChanged(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition());
            selectedArchiveReview.clear();
            selectButton.setEnabled(false);
            return;
        }

        // unselect old selection if it exists
        if(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition() > -1) {
            archive.getAdapter().notifyItemChanged(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition());
            ((RecyclerAdapter) archive.getAdapter()).setSelectedPosition(-1);
            archive.getAdapter().notifyItemChanged(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition());
        }

        // make new selection
        archive.getAdapter().notifyItemChanged(position);
        ((RecyclerAdapter) archive.getAdapter()).setSelectedPosition(position);
        archive.getAdapter().notifyItemChanged(position);

        String temp = "";
        View selectedArchiveItem = archive.getLayoutManager().findViewByPosition(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition());
        if(view == null) return;

        // DATE
        temp = ((TextView) selectedArchiveItem.findViewById(R.id.textViewDate)).getText().toString();
        if(temp.endsWith(", ")) temp = temp.substring(0, temp.length() - 2);
        selectedArchiveReview.put(DatabaseWriter.UIComponentInputValue.DATE.getValue(), temp);
        // TIME
        temp = ((TextView) selectedArchiveItem.findViewById(R.id.textViewTime)).getText().toString();
        if(temp.endsWith(", ")) temp = temp.substring(0, temp.length() - 2);
        selectedArchiveReview.put(DatabaseWriter.UIComponentInputValue.TIME.getValue(), temp);
        // ADDRESS
        temp = ((TextView) selectedArchiveItem.findViewById(R.id.textViewAddress)).getText().toString();
        if(temp.endsWith(", ")) temp = temp.substring(0, temp.length() - 2);
        selectedArchiveReview.put(DatabaseWriter.UIComponentInputValue.ADDRESS.getValue(), temp);
        // CITY
        temp = ((TextView) selectedArchiveItem.findViewById(R.id.textViewCity)).getText().toString();
        if(temp.endsWith(", ")) temp = temp.substring(0, temp.length() - 2);
        selectedArchiveReview.put(DatabaseWriter.UIComponentInputValue.CITY.getValue(), temp);
        // PROVINCE
        temp = ((TextView) selectedArchiveItem.findViewById(R.id.textViewProvince)).getText().toString();
        if(temp.endsWith(", ")) temp = temp.substring(0, temp.length() - 2);
        selectedArchiveReview.put(DatabaseWriter.UIComponentInputValue.PROVINCE.getValue(), temp);
        // PROJECT NUMBER
        temp = ((TextView) selectedArchiveItem.findViewById(R.id.textViewProjectNumber)).getText().toString();
        if(temp.endsWith(", ")) temp = temp.substring(0, temp.length() - 2);
        selectedArchiveReview.put(DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER.getValue(), temp);
        selectButton.setEnabled(true);
    }

    @Override
    public void edit() {
        final Model model = (Model) getApplicationContext();
        if(model.reviewStarted()) {
            showLoadAlertDialogue();
        } else {
            Toast toast = Toast.makeText(this, R.string.button_edit, Toast.LENGTH_LONG);
            toast.show();
            model.loadReviewFromDatabase(selectedArchiveReview);
            Intent intent = new Intent(MainActivity.this, InspectionReviewActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean export() {
        Toast toast = Toast.makeText(this, R.string.exporting_doc_message, Toast.LENGTH_LONG);
        toast.show();
        final Model model = (Model) getApplicationContext();
        model.loadReviewFromDatabase(selectedArchiveReview);
        boolean exported = model.exportReviewToDoc(getBaseContext());
        model.reset();
        return exported;
    }

    @Override
    public void delete() {
        showDeleteAlertDialogue();
    }

    private void showSelectDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        SelectDialog selectDialog = new SelectDialog();
        selectDialog.addModelLoadListener(this);
        selectDialog.show(fragmentManager, "dialog");
    }

    private void showDeleteAlertDialogue() {
        final Model model = (Model) getApplicationContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_message)
                .setTitle(R.string.delete_dialog_title);
        // set buttons
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast toast = Toast.makeText(MainActivity.this, R.string.delete_message, Toast.LENGTH_LONG);
                toast.show();

                model.deleteReviewFromDatabase(selectedArchiveReview);
                selectedArchiveReview = new HashMap<>();
                final Button selectButton = (Button) findViewById(R.id.buttonSelectReview);
                selectButton.setEnabled(false);
                final RecyclerView archive = (RecyclerView) findViewById(R.id.recyclerViewArchive);
                archive.setAdapter(new RecyclerAdapter(MainActivity.this.getApplicationContext(),
                        MainActivity.this, model.getDatabaseCursor()));
                archive.getAdapter().notifyDataSetChanged();
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

    private void showLoadAlertDialogue() {
        final Model model = (Model) getApplicationContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialogue_review_in_progress_message)
                .setTitle(R.string.dialogue_review_in_progress_title);
        // set buttons
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast toast = Toast.makeText(MainActivity.this, R.string.button_edit, Toast.LENGTH_LONG);
                toast.show();
                model.loadReviewFromDatabase(selectedArchiveReview);
                Intent intent = new Intent(MainActivity.this, InspectionReviewActivity.class);
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

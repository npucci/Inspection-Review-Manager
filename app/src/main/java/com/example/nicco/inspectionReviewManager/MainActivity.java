package com.example.nicco.inspectionReviewManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
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

import java.util.HashMap;

public class MainActivity extends FragmentActivity implements RecyclerViewClickListener {
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
//        DividerItemDecoration  dividerItemDecoration = new DividerItemDecoration (
//                archive.getContext(), getResources().getConfiguration().orientation
//
//        );
//        archive.addItemDecoration(dividerItemDecoration);

        final Button editReviewButton = (Button) findViewById(R.id.buttonEditReview);
        editReviewButton.setPaintFlags(editReviewButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        editReviewButton.setTextSize(16);
        editReviewButton.setEnabled(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition() > -1);
        //editReviewButton.setEnabled(selectedArchiveItem != null);
        // ((RecyclerAdapter) archive.getAdapter()).getSelectedPosition()
        editReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("PUCCI", "archive.getAdapter()).getSelectedPosition() = " + ((RecyclerAdapter) archive.getAdapter()).getSelectedPosition());
                if(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition() > -1) {
                    model.loadReviewFromDatabase(selectedArchiveReview);
                    Intent intent = new Intent(MainActivity.this, InspectionReviewActivity.class);
                    startActivity(intent);
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
    public void recyclerViewListClicked(View view, int position) {
        final RecyclerView archive = (RecyclerView) findViewById(R.id.recyclerViewArchive);
        final Button editReviewButton = (Button) findViewById(R.id.buttonEditReview);

        // double tapping the currently selected item deselects it
        if(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition() == position) {
            archive.getAdapter().notifyItemChanged(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition());
            ((RecyclerAdapter) archive.getAdapter()).setSelectedPosition(-1);
            archive.getAdapter().notifyItemChanged(((RecyclerAdapter) archive.getAdapter()).getSelectedPosition());
            selectedArchiveReview.clear();
            editReviewButton.setEnabled(false);
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
        editReviewButton.setEnabled(true);
    }

    private class DividerItemDecoration extends ItemDecoration {
        private Context context;
        private int orientation;

        public DividerItemDecoration(Context context, int orientation) {
            this.context = context;
            this.orientation = orientation;
        }
    }
}

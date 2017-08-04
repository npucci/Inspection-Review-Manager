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
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements RecyclerViewClickListener {
    private View selectedArchiveItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Model model = (Model) getApplicationContext();

        final RecyclerView archive = (RecyclerView) findViewById(R.id.recyclerViewArchive);
        archive.setAdapter(new RecyclerAdapter(getApplicationContext(), this, model.getDatabaseCursor()));
        archive.setHasFixedSize(false);
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
        editReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // need to implement editing feature still
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
        LinearLayout linearLayout;
        TextView textView;

        // clear old selection, if there was a previous selection
        if(selectedArchiveItem != null) {
            linearLayout = (LinearLayout) selectedArchiveItem.findViewById(R.id.linearLayoutItem);
            linearLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.incompleteBackground));

            textView = (TextView) selectedArchiveItem.findViewById(R.id.textViewItemString);
            textView.setPaintFlags(0);
            textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.incompleteText));
            textView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.incompleteBackground));
        }

        // make new selection
        selectedArchiveItem = view;

        linearLayout = (LinearLayout) selectedArchiveItem.findViewById(R.id.linearLayoutItem);
        linearLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedBackground));

        textView = (TextView) selectedArchiveItem.findViewById(R.id.textViewItemString);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedText));
        textView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedBackground));
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

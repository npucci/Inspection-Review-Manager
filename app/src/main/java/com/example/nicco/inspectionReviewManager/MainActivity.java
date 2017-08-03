package com.example.nicco.inspectionReviewManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Model model = (Model) getApplicationContext();

        RecyclerView archive = (RecyclerView) findViewById(R.id.recyclerViewArchive);
        archive.setAdapter(new RecyclerAdapter(this, model.getDatabaseCursor()));
        archive.setHasFixedSize(false);
        archive.setLayoutManager(new LinearLayoutManager(this));
        archive.setItemAnimator(new DefaultItemAnimator());
        archive.setBackgroundColor(Color.DKGRAY);
//        DividerItemDecoration  dividerItemDecoration = new DividerItemDecoration (
//                archive.getContext(), getResources().getConfiguration().orientation
//
//        );
//        archive.addItemDecoration(dividerItemDecoration);

        final Button editReviewReviewButton = (Button) findViewById(R.id.buttonEditReview);
        editReviewReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // need to implement editing feature still
            }
        });

        final Button newReviewButton = (Button) findViewById(R.id.buttonInspectionReview);
        if(model.reviewStarted()) {
            newReviewButton.setText("Review In Progress:\nContinue >>");
            newReviewButton.setPaintFlags(newReviewButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
        else  newReviewButton.setText("New Review");
        newReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InspectionReviewActivity.class);
                startActivity(intent);
            }
        });
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

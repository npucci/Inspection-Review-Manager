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
        TextView date;
        TextView time;
        TextView address;
        TextView city;
        TextView province;
        TextView projectNumber;
        TextView reviewType;
        TextView reviewStatus;
        TextView reviewBy;

        // clear old selection, if there was a previous selection
        if(selectedArchiveItem != null) {
            linearLayout = (LinearLayout) selectedArchiveItem.findViewById(R.id.linearLayoutRecycleViewItem);
            linearLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

            // DATE
            date = (TextView) selectedArchiveItem.findViewById(R.id.textViewDate);
            date.setPaintFlags(0);
            date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            date.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            // TIME
            time = (TextView) selectedArchiveItem.findViewById(R.id.textViewTime);
            time.setPaintFlags(0);
            time.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            time.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            // ADDRESS
            address = (TextView) selectedArchiveItem.findViewById(R.id.textViewAddress);
            address.setPaintFlags(0);
            address.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            address.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            // CITY
            city = (TextView) selectedArchiveItem.findViewById(R.id.textViewCity);
            city.setPaintFlags(0);
            city.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            city.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            // PROVINCE
            province = (TextView) selectedArchiveItem.findViewById(R.id.textViewProvince);
            province.setPaintFlags(0);
            province.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            province.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            // PROJECT NUMBER
            projectNumber = (TextView) selectedArchiveItem.findViewById(R.id.textViewProjectNumber);
            projectNumber.setPaintFlags(0);
            projectNumber.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            projectNumber.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            // REVIEW TYPE
            reviewType = (TextView) selectedArchiveItem.findViewById(R.id.textViewReviewType);
            reviewType.setPaintFlags(0);
            reviewType.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            reviewType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            // REVIEW STATUS
            reviewStatus = (TextView) selectedArchiveItem.findViewById(R.id.textViewReviewStatus);
            reviewStatus.setPaintFlags(0);
            reviewStatus.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            reviewStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            // REVIEW BY
            reviewBy = (TextView) selectedArchiveItem.findViewById(R.id.textViewReviewBy);
            reviewBy.setPaintFlags(0);
            reviewBy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            reviewBy.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        }

        // make new selection
        selectedArchiveItem = view;

        linearLayout = (LinearLayout) selectedArchiveItem.findViewById(R.id.linearLayoutRecycleViewItem);
        linearLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedBackground));

        // DATE
        date = (TextView) selectedArchiveItem.findViewById(R.id.textViewDate);
        date.setPaintFlags(date.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedText));
        date.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedBackground));
        // TIME
        time = (TextView) selectedArchiveItem.findViewById(R.id.textViewTime);
        time.setPaintFlags(time.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        time.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedText));
        time.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedBackground));
        // ADDRESS
        address = (TextView) selectedArchiveItem.findViewById(R.id.textViewAddress);
        address.setPaintFlags(address.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        address.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedText));
        address.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedBackground));
        // CITY
        city = (TextView) selectedArchiveItem.findViewById(R.id.textViewCity);
        city.setPaintFlags(city.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        city.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedText));
        city.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedBackground));
        // PROVINCE
        province = (TextView) selectedArchiveItem.findViewById(R.id.textViewProvince);
        province.setPaintFlags(province.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        province.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedText));
        province.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedBackground));
        // PROJECT NUMBER
        projectNumber = (TextView) selectedArchiveItem.findViewById(R.id.textViewProjectNumber);
        projectNumber.setPaintFlags(projectNumber.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        projectNumber.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedText));
        projectNumber.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedBackground));
        // REVIEW TYPE
        reviewType = (TextView) selectedArchiveItem.findViewById(R.id.textViewReviewType);
        reviewType.setPaintFlags(reviewType.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        reviewType.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedText));
        reviewType.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedBackground));
        // REVIEW STATUS
        reviewStatus = (TextView) selectedArchiveItem.findViewById(R.id.textViewReviewStatus);
        reviewStatus.setPaintFlags(reviewStatus.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        reviewStatus.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedText));
        reviewStatus.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedBackground));
        // REVIEW BY
        reviewBy = (TextView) selectedArchiveItem.findViewById(R.id.textViewReviewBy);
        reviewBy.setPaintFlags(reviewBy.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        reviewBy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedText));
        reviewBy.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selectedBackground));

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

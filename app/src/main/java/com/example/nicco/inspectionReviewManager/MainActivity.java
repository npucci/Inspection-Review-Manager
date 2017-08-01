package com.example.nicco.inspectionReviewManager;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Model model = (Model) getApplicationContext();

        final Button editReviewReviewButton = (Button) findViewById(R.id.buttonEditReview);
        editReviewReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // need to implement editing feature still
            }
        });

        final Button newReviewButton = (Button) findViewById(R.id.buttonInspectionReview);
        if(model.reviewStarted()) newReviewButton.setText("Resume Review");
        else  newReviewButton.setText("New Review");
        newReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InspectionReviewActivity.class);
                startActivity(intent);
            }
        });


    }
}

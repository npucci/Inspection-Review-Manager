package com.example.nicco.inspectionReviewManager.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.nicco.inspectionReviewManager.R;
import com.example.nicco.inspectionReviewManager.customDatatypes.Model;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by Nicco on 2017-08-30.
 */

public class ReviewFinishActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_finish);

        final Model model = (Model) getApplicationContext();

        final LinearLayout webViewLayout = (LinearLayout) findViewById(R.id.linearLayoutWebView);
        webViewLayout.setBackgroundColor(Color.WHITE);

        final LinearLayout webViewButtonsLayout = (LinearLayout) findViewById(R.id.linearLayoutWebViewButtons);
        webViewButtonsLayout.setBackgroundColor(Color.WHITE);

        final WebView webView = (WebView) findViewById(R.id.WebViewPrint);
        final File exportedHTML = model.getExportHTML(getBaseContext());

        try {
            webView.loadUrl(exportedHTML.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            Log.v("NICCO", e.getMessage());
        }
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) return true;
                //Log.v("NICCO", motionEvent.toString());
                return false;
            }
        });
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        Button backButton = (Button) findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewFinishActivity.this, InspectionReviewActivity.class);
                startActivity(intent);
            }
        });

        Button printButton = (Button) findViewById(R.id.buttonPrint);
        printButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
                android.print.PrintDocumentAdapter printDocumentAdapter = webView.createPrintDocumentAdapter();
                String jobName = getString(R.string.app_name) + " Document";
                PrintAttributes printAttributes = new PrintAttributes.Builder().build();
                PrintJob printJob = printManager.print(jobName, printDocumentAdapter, new PrintAttributes.Builder().build());
            }
        });

        final Button finishedButton = (Button) findViewById(R.id.buttonFinished);
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.reset();
                Intent intent = new Intent(ReviewFinishActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
        setTextUnderline();
    }

    private void setTextSize(Float textSize) {
        Button backButton = (Button) findViewById(R.id.buttonBack);
        backButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        Button printButton = (Button) findViewById(R.id.buttonPrint);
        printButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        Button finishedButton = (Button) findViewById(R.id.buttonFinished);
        finishedButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    private void setTextUnderline() {
        Button backButton = (Button) findViewById(R.id.buttonBack);
        backButton.setPaintFlags(backButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Button printButton = (Button) findViewById(R.id.buttonPrint);
        printButton.setPaintFlags(printButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Button finishedButton = (Button) findViewById(R.id.buttonFinished);
        finishedButton.setPaintFlags(finishedButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}

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

public class PrintActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        Model model = (Model) getApplicationContext();

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
                return false;
            }
        });
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        Button backButton = (Button) findViewById(R.id.buttonPrintBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("about:blank") ;
                Intent intent = new Intent(PrintActivity.this, MainActivity.class);
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

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
        setTextUnderline();
    }

    @Override
    public void onResume() {
        super.onResume();
        Model model = (Model) getApplicationContext();

        final WebView webView = (WebView) findViewById(R.id.WebViewPrint);
        final File exportedHTML = model.getExportHTML(getBaseContext());;

        try {
            webView.loadUrl(exportedHTML.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            Log.v("NICCO", e.getMessage());
        }

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
        setTextUnderline();
    }

    private void setTextSize(Float textSize) {
        Button backButton = (Button) findViewById(R.id.buttonPrintBack);
        backButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        Button printButton = (Button) findViewById(R.id.buttonPrint);
        printButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    private void setTextUnderline() {
        Button backButton = (Button) findViewById(R.id.buttonPrintBack);
        backButton.setPaintFlags(backButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Button printButton = (Button) findViewById(R.id.buttonPrint);
        printButton.setPaintFlags(printButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        Model model = (Model) getApplicationContext();
        model.reset();
    }

    @Override
    public void onStop() {
        final WebView webView = (WebView) findViewById(R.id.WebViewPrint);
        webView.loadUrl("about:blank") ;;
        super.onStop();
    }
}

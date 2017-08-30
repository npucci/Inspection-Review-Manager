package com.example.nicco.inspectionReviewManager.activities;

import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

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

        final WebView webView = (WebView) findViewById(R.id.WebViewPrint);
        final File exportedHTML = model.getExportHTML(getBaseContext());
        model.reset();

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

        Button printButton = (Button) findViewById(R.id.buttonPrint);
        printButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
                PrintDocumentAdapter printDocumentAdapter = webView.createPrintDocumentAdapter();
                String jobName = getString(R.string.app_name) + " Document";
                PrintJob printJob = printManager.print(jobName, printDocumentAdapter, new PrintAttributes.Builder().build());
            }
        });

        //initViews();
        //initValues();

        //SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        //setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
    }
}

package com.example.nicco.inspectionReviewManager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
    public static final String EXTRA_MESSAGE = "com.example.nicco.myfirstapp.MESSAGE";
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Model model = (Model) getApplicationContext();
        final Button finishedButton = (Button) findViewById(R.id.buttonFinished);

        boolean ready = true;
        if(!model.checkDateActivityStatus()) ready = false;
        if(!model.checkProjectActivityStatus()) ready = false;
        if(!model.checkConcreteActivityStatus()) ready = false;
        if(!model.checkFramingActivityStatus()) ready = false;
        if(!model.checkConclusionActivityStatus()) ready = false;

        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.insertReviewToDatabase();
                model.exportReviewToDoc();
            }
        });

        finishedButton.setEnabled(ready);
    }
}

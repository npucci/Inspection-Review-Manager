package com.example.nicco.inspectionReviewManager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
    public static final String EXTRA_MESSAGE = "com.example.nicco.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dbWriter = new DatabaseWriter(MainActivity.this.getApplication());
        final Model model = (Model) getApplicationContext();
        final Button finishedButton = (Button) findViewById(R.id.buttonFinished);
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.updateValue(Model.Keys.YEAR, "2010");
                model.updateValue(Model.Keys.MONTH, "July");
                model.updateValue(Model.Keys.DAY, "2");
                model.updateValue(Model.Keys.HOUR, "5");
                model.updateValue(Model.Keys.MINUTE, "20");
                model.updateValue(Model.Keys.TIME_PERIOD, "PM");
                model.insertDatabase();
                Log.v("PUCCI", "TEST CLICK");
            }
        });
    }
}

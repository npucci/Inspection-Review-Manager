package com.example.nicco.inspectionReviewManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TimePicker.*;

import java.text.DateFormatSymbols;
import java.util.Calendar;

/**
 * Created by Nicco on 2017-07-14.
 */

public class DateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        final Model model = (Model) getApplicationContext();

        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    model.updateValue(Model.Keys.DAY, "" + dayOfMonth);
                    model.updateValue(Model.Keys.MONTH, "" + new DateFormatSymbols().getMonths()[month]);
                    model.updateValue(Model.Keys.YEAR, "" + year);
                }
            }
        );

        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                int hour = hourOfDay;
                String period = "";
                if(hourOfDay < 12) {
                    period = "AM";
                } else {
                    period = "PM";
                    if(hourOfDay > 12) hour -= 12;
                }

                model.updateValue(Model.Keys.HOUR, "" + hour);
                model.updateValue(Model.Keys.MINUTE, "" + minute);
                model.updateValue(Model.Keys.TIME_PERIOD, "" + period);
            }
        });
    }
}

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
    private Model model;
    private DatePicker datePicker;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        init();

        String year = model.getValue(Model.Keys.YEAR);
        String month = model.getValue(Model.Keys.MONTH);
        String day = model.getValue(Model.Keys.DAY);
        if(model.validValue(year) && model.validValue(month) && model.validValue(day))
            datePicker.updateDate(Integer.parseInt(year), model.monthToInt(month), Integer.parseInt(day));

        String hour = model.getValue(Model.Keys.HOUR);
        String minute = model.getValue(Model.Keys.MINUTE);
        String timePeriod = model.getValue(Model.Keys.TIME_PERIOD);
        if(model.validValue(hour) && model.validValue(minute) && (model.validValue(timePeriod))) {
            int convertTo24Hr = 0;
            if(timePeriod.equals(Model.SpecialValue.PM.toString())) convertTo24Hr = 12;
            timePicker.setCurrentHour(Integer.parseInt(hour) + convertTo24Hr);
            timePicker.setCurrentMinute(Integer.parseInt(minute));
        }
    }

    private void init() {
        model = (Model) getApplicationContext();
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();
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

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                int hour = hourOfDay;
                String period = "";
                if(hourOfDay < 12) {
                    period = Model.SpecialValue.AM.toString();
                } else {
                    period = Model.SpecialValue.PM.toString();
                    if(hourOfDay > 12) hour -= 12;
                }

                model.updateValue(Model.Keys.HOUR, "" + hour);
                model.updateValue(Model.Keys.MINUTE, "" + minute);
                model.updateValue(Model.Keys.TIME_PERIOD, "" + period);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

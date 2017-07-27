package com.example.nicco.inspectionReviewManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
    private AutoCompleteTextView weather;
    private AutoCompleteTextView temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        init();

        String value = model.getValue(DatabaseWriter.DatabaseColumn.DATE);
        if(value != null) {
            String[] split = value.split("-"); // DATE format "YYYY-MM-DD"
            if (split.length == 3) {
                String year = split[0];
                String month = split[1];
                String day = split[2];
                if (model.validValue(year) && model.validValue(month) && model.validValue(day))
                    datePicker.updateDate(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
            }
        }

        value = model.getValue(DatabaseWriter.DatabaseColumn.DATE);
        if(value != null) {
            String[] split = value.split(":"); // TIME format "HH:MM"
            if (split.length == 2) {
                String hour = split[0];
                String minute = split[1];
                if (model.validValue(hour) && model.validValue(minute)) {
                    timePicker.setCurrentHour(Integer.parseInt(hour));
                    timePicker.setCurrentMinute(Integer.parseInt(minute));
                }
            }
        }

        // WEATHER
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.combineArrays(
                        model.queryDatabase(DatabaseWriter.DatabaseColumn.WEATHER, null, null),
                        getResources().getStringArray(R.array.weather)));

        weather.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.DatabaseColumn.WEATHER);
        if(value != null) weather.setText(value);

        // TEMPERATURE
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.DatabaseColumn.TEMPERATURE_CELSIUS, null, null));

        temperature.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.DatabaseColumn.TEMPERATURE_CELSIUS);
        if(value != null) temperature.setText(value);
    }

    private void init() {
        model = (Model) getApplicationContext();
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
//        datePicker.init(calendar.get(Calendar.YEAR),
//                calendar.get(Calendar.MONTH),
//                calendar.get(Calendar.DAY_OF_MONTH),
//                new DatePicker.OnDateChangedListener() {
//                    @Override
//                    public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
//                        model.updateValue(Model.Keys.DAY, "" + dayOfMonth);
//                        model.updateValue(Model.Keys.MONTH, "" + new DateFormatSymbols().getMonths()[month]);
//                        model.updateValue(Model.Keys.YEAR, "" + year);
//                    }
//                }
//        );

        timePicker = (TimePicker) findViewById(R.id.timePicker);
//        timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
//            @Override
//            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//                int hour = hourOfDay;
//                String timePeriod = "";
//                if(hourOfDay < 12) {
//                    timePeriod = Model.SpecialValue.AM.toString();
//                } else {
//                    timePeriod = Model.SpecialValue.PM.toString();
//                    if(hourOfDay > 12) hour -= 12;
//                }
//
//                model.updateValue(Model.Keys.HOUR, "" + hour);
//                model.updateValue(Model.Keys.MINUTE, "" + minute);
//                model.updateValue(Model.Keys.TIME_PERIOD, "" + timePeriod);
//            }
//        });

        weather = (AutoCompleteTextView) findViewById(R.id.autoCompleteWeather);
        temperature = (AutoCompleteTextView) findViewById(R.id.editTextTemperature);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // DATE
        String date = datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getDayOfMonth();
        model.updateValue(DatabaseWriter.DatabaseColumn.DATE, date);

        // TIME
        String time = timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
        model.updateValue(DatabaseWriter.DatabaseColumn.TIME, time);

        // WEATHER
        model.updateValue(DatabaseWriter.DatabaseColumn.WEATHER, weather.getText().toString());

        // TEMPERATURE
        model.updateValue(DatabaseWriter.DatabaseColumn.TEMPERATURE_CELSIUS, temperature.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

package com.example.nicco.inspectionReviewManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TimePicker;

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

        // DATE PICKER
        String value = model.getValue(DatabaseWriter.UIComponentInputValue.DATE);
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

        // TIME PICKER
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
                        model.queryDatabase(DatabaseWriter.UIComponentInputValue.WEATHER, null, null),
                        getResources().getStringArray(R.array.weather)));

        weather.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.UIComponentInputValue.WEATHER);
        if(value != null) weather.setText(value);

        // TEMPERATURE
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                model.queryDatabase(DatabaseWriter.UIComponentInputValue.TEMPERATURE_CELSIUS, null, null));

        temperature.setAdapter(adapter);

        value = model.getValue(DatabaseWriter.UIComponentInputValue.TEMPERATURE_CELSIUS);
        if(value != null) temperature.setText(value);
    }

    private void init() {
        model = (Model) getApplicationContext();
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        weather = (AutoCompleteTextView) findViewById(R.id.autoCompleteWeather);
        temperature = (AutoCompleteTextView) findViewById(R.id.editTextTemperature);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // DATE
        String date = datePicker.getYear() + "-" + formatDigitStr("" + (datePicker.getMonth() + 1)) +
                "-" + formatDigitStr("" + datePicker.getDayOfMonth());
        model.updateValue(DatabaseWriter.UIComponentInputValue.DATE, date);

        // TIME
        String time = timePicker.getCurrentHour() + ":" + formatDigitStr("" + timePicker.getCurrentMinute());
        model.updateValue(DatabaseWriter.UIComponentInputValue.TIME, time);

        // WEATHER
        String tempText = weather.getText().toString();
        if(tempText.equals("")) tempText = Model.SpecialValue.NA.toString();
        model.updateValue(DatabaseWriter.UIComponentInputValue.WEATHER, tempText);

        // TEMPERATURE
        tempText = temperature.getText().toString();
        if(tempText.equals("")) tempText = Model.SpecialValue.NA.toString();
        model.updateValue(DatabaseWriter.UIComponentInputValue.TEMPERATURE_CELSIUS, tempText);
    }

    public String formatDigitStr(String num) {
        if(num.length() < 2) return "0" + num;
        return num;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

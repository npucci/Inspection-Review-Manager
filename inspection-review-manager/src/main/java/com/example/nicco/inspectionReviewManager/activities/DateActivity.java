package com.example.nicco.inspectionReviewManager.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.nicco.inspectionReviewManager.customDatatypes.AutoFillActivity;
import com.example.nicco.inspectionReviewManager.customDatatypes.DatabaseWriter;
import com.example.nicco.inspectionReviewManager.customDatatypes.Model;
import com.example.nicco.inspectionReviewManager.customDatatypes.QueryingAutoCompleteTextView;
import com.example.nicco.inspectionReviewManager.R;

/**
 * Created by Nicco on 2017-07-14.
 */

public class DateActivity extends AppCompatActivity implements AutoFillActivity {
    private Model model;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private QueryingAutoCompleteTextView weather;
    private QueryingAutoCompleteTextView temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        initViews();
        initValues();
    }

    private void initViews() {
        model = (Model) getApplicationContext();
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        weather = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteWeather);
        weather.set(this, model, this, DatabaseWriter.UIComponentInputValue.WEATHER, getResources().getStringArray(R.array.weather));
        temperature = (QueryingAutoCompleteTextView) findViewById(R.id.editTextTemperature);
        temperature.set(this, model, this, DatabaseWriter.UIComponentInputValue.TEMPERATURE_CELSIUS, null);
    }

    private void initValues() {
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
        value = model.getValue(DatabaseWriter.UIComponentInputValue.TIME);
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
        value = model.getValue(DatabaseWriter.UIComponentInputValue.WEATHER);
        if(value != null) weather.setText(value);

        // TEMPERATURE
        value = model.getValue(DatabaseWriter.UIComponentInputValue.TEMPERATURE_CELSIUS);
        if(value != null) temperature.setText(value);
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
    protected void onResume() {
        super.onResume();
        initValues();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void setTextSize(float textSize) {
        TextView dateLabel = (TextView) findViewById(R.id.textViewDate);
        dateLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView timeLabel = (TextView) findViewById(R.id.textViewTime);
        timeLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView weatherLabel = (TextView) findViewById(R.id.textViewWeather);
        weatherLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        weather.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView temperatureLabel = (TextView) findViewById(R.id.textViewTemperature);
        temperatureLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        temperature.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    @Override
    public void autofill(Object uiComponent) {}
}

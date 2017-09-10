package com.example.nicco.inspectionReviewManager.customDatatypes;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.nicco.inspectionReviewManager.interfaces.AutoFillActivity;

/**
 * Created by Nicco on 2017-08-16.
 */

public class QueryingTextWatcher implements TextWatcher {
    private String databaseTable;
    private DatabaseWriter.UIComponentInputValue databaseColumn;
    private AutoCompleteTextView autoCompleteTextView;
    private AutoFillActivity activity;
    private String[] defaultValues;
    private Model model;

    public QueryingTextWatcher(AutoFillActivity activity, AutoCompleteTextView autoCompleteTextView, String databaseTble,
                               DatabaseWriter.UIComponentInputValue databaseColumn, String[] defaultValues) {
        this.activity = activity;
        this.autoCompleteTextView = autoCompleteTextView;
        this.databaseTable = databaseTable;
        this.databaseColumn = databaseColumn;
        this.defaultValues = defaultValues;
        model = (Model) ((AppCompatActivity) activity).getApplicationContext();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(autoCompleteTextView.getText().toString() == null && autoCompleteTextView.getText().toString().isEmpty()) return;
        ArrayAdapter<String> adapter = new ArrayAdapter<>((AppCompatActivity) activity,
                android.R.layout.simple_selectable_list_item,
                model.combineArrays(
                        model.queryMatchSearchDatabase(
                                databaseTable, databaseColumn.getValue(), autoCompleteTextView.getText().toString()),
                        defaultValues));
        autoCompleteTextView.setAdapter(adapter);
        ((ArrayAdapter) autoCompleteTextView.getAdapter()).notifyDataSetChanged();
        activity.autofill(autoCompleteTextView);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}

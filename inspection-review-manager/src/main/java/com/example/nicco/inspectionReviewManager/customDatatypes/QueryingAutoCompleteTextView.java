package com.example.nicco.inspectionReviewManager.customDatatypes;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.nicco.inspectionReviewManager.interfaces.AutoFillActivity;

import java.util.ArrayList;

/**
 * Created by Nicco on 2017-08-16.
 */

public class QueryingAutoCompleteTextView extends android.support.v7.widget.AppCompatAutoCompleteTextView {
    ArrayList<String> values = new ArrayList<String>();

    public QueryingAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void set(final Activity activity, final Model model, final AutoFillActivity autofillActivity,
                    final String databaseTable,
                    final String databaseColumn,
                    final String[] defaultValues) {
        setAdapter(new ArrayAdapter<>(activity.getBaseContext(),
                android.R.layout.simple_selectable_list_item,
                values));
        setThreshold(1);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(QueryingAutoCompleteTextView.this.getText().toString() == null && QueryingAutoCompleteTextView.this.getText().toString().isEmpty()) return;
                values.clear();
                values.addAll(model.combineArraysLinkedHashSet(
                        model.queryMatchSearchDatabase(databaseTable, databaseColumn, QueryingAutoCompleteTextView.this.getText().toString()),
                        defaultValues));
                ((ArrayAdapter) QueryingAutoCompleteTextView.this.getAdapter()).notifyDataSetChanged();
                autofillActivity.autofill(QueryingAutoCompleteTextView.this);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {}
        });
        setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autofillActivity.autofill(this);
            }
        });
    }
}

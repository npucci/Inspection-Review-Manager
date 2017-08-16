package com.example.nicco.inspectionReviewManager;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

/**
 * Created by Nicco on 2017-08-16.
 */

public class QueryingAutoCompleteTextView extends android.support.v7.widget.AppCompatAutoCompleteTextView {
    public QueryingAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void set(final Activity activity, final Model model, final AutoFillActivity autofillActivity,
                    final DatabaseWriter.UIComponentInputValue databaseColumn,
                    final String[] defaultValues, boolean useFirstDefault) {
        if(useFirstDefault && defaultValues != null && defaultValues.length != 0) setText(defaultValues[0]);
        setAdapter(new ArrayAdapter<>(activity.getBaseContext(),
                android.R.layout.simple_selectable_list_item,
                new String[0]));
        setThreshold(1);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(QueryingAutoCompleteTextView.this.getText().toString() == null && QueryingAutoCompleteTextView.this.getText().toString().isEmpty()) return;
                ArrayAdapter<String> adapter = new ArrayAdapter<>(activity,
                        android.R.layout.simple_selectable_list_item,
                        model.combineArrays(
                                model.queryMatchSearchDatabase(
                                        databaseColumn, QueryingAutoCompleteTextView.this.getText().toString()),
                                defaultValues));
                QueryingAutoCompleteTextView.this.setAdapter(adapter);
                ((ArrayAdapter) QueryingAutoCompleteTextView.this.getAdapter()).notifyDataSetChanged();
                autofillActivity.autofill(QueryingAutoCompleteTextView.this);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autofillActivity.autofill(this);
            }
        });
    }

}

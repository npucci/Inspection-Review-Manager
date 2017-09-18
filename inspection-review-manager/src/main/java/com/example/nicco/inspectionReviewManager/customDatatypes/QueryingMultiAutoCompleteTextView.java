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
 * Created by Nicco on 2017-09-17.
 */

public class QueryingMultiAutoCompleteTextView extends android.support.v7.widget.AppCompatMultiAutoCompleteTextView {
    private ArrayList<String> values = new ArrayList<String>();
    private int textLength = 0;

    public QueryingMultiAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void set(final Activity activity, final Model model, final AutoFillActivity autofillActivity,
                    final String databaseTable,
                    final String databaseColumn,
                    final String[] defaultValues) {
        setAdapter(new ArrayAdapter<>(activity.getBaseContext(),
                android.R.layout.simple_selectable_list_item, values));
        setThreshold(1);
        setTokenizer( new QueryingMultiAutoCompleteTextView.CommaTokenizer() );

        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if( charSequence.length() > textLength && charSequence.length() > 1 &&
                        charSequence.charAt( charSequence.length() - 1 ) == ',' ) {
                    QueryingMultiAutoCompleteTextView.this.getText().append("\u0020");
                    textLength = QueryingMultiAutoCompleteTextView.this.getText()
                            .toString().length();
                    return;
                }

                String item = charSequence.toString();
                String[] temp = item.split(", ");
                item = temp[ temp.length - 1 ];
                if(item.toString() == null && item.toString().isEmpty()) return;
                values = new ArrayList<String>();
                values.addAll(model.combineArraysLinkedHashSet(
                        model.queryMatchSearchDatabase(databaseTable, databaseColumn, item.toString()),
                        defaultValues));
                QueryingMultiAutoCompleteTextView.this.setAdapter(new ArrayAdapter<>(activity.getBaseContext(),
                        android.R.layout.simple_selectable_list_item, values));
                textLength = charSequence.toString().length();
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                autofillActivity.autofill(QueryingMultiAutoCompleteTextView.this);
            }
        });
        setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autofillActivity.autofill(QueryingMultiAutoCompleteTextView.this);
            }
        });
    }
}
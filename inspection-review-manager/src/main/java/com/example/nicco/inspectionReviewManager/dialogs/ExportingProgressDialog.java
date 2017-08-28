package com.example.nicco.inspectionReviewManager.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.nicco.inspectionReviewManager.R;
import com.example.nicco.inspectionReviewManager.interfaces.ModelLoadListener;

/**
 * Created by Nicco on 2017-08-08.
 */

public class ExportingProgressDialog extends DialogFragment {
    private ModelLoadListener modelLoadListener;
    private float textSize = 0f;
    private TextView exportingTitle;
    private TextView exportingProgressPercentage;
    private TextView exportingResult;

    public ExportingProgressDialog() { super(); }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.exporting_loading_dialog, container, false);

        exportingTitle = (TextView) view.findViewById(R.id.textViewExportingTitle);
        exportingProgressPercentage = (TextView) view.findViewById(R.id.textViewProgressPercentage);
        exportingResult = (TextView) view.findViewById(R.id.textViewExportResult);

        updateTextSize(textSize);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    private void updateTextSize(float textSize) {
        if(textSize != getResources().getDimension(R.dimen.defaultTextSize) &&
                textSize != getResources().getDimension(R.dimen.largeTextSize))
            this.textSize = getResources().getDimension(R.dimen.defaultTextSize);
        else this.textSize = textSize;
        exportingTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 1.2f * textSize);
        exportingProgressPercentage.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 1.2f * textSize);
        exportingResult.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 1.2f * textSize);
    }

    public void setTitle(String title) {
        exportingTitle.setText(title);
    }

    public void setProgressPercentage(int percentage) {
        exportingProgressPercentage.setText(percentage + " %");
    }

    public void finished(boolean result) {
        if(result) exportingResult.setText("Export Finished.");
        else exportingResult.setText("Export Failed. Make sure the desired file is not already opened in another app.");
    }
}

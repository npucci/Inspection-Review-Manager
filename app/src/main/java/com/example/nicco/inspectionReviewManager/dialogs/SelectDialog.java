package com.example.nicco.inspectionReviewManager.dialogs;

import android.Manifest;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicco.inspectionReviewManager.R;
import com.example.nicco.inspectionReviewManager.customDatatypes.ModelLoadListener;

/**
 * Created by Nicco on 2017-08-08.
 */

public class SelectDialog extends DialogFragment {
    private ModelLoadListener modelLoadListener;
    private float textSize;

    public SelectDialog() { super(); }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.select_dialog, container, false);
        Button exportButton = (Button) view.findViewById(R.id.buttonSelectExport);
        Button editButton = (Button) view.findViewById(R.id.buttonSelectEdit);
        Button deleteButton = (Button) view.findViewById(R.id.buttonDelete);
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(view.getContext(), R.string.exporting_doc_message, Toast.LENGTH_LONG);
                toast.show();
                // export and open review as a doc file
                ActivityCompat.requestPermissions(SelectDialog.this.getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                int permission = ActivityCompat.checkSelfPermission(SelectDialog.this.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(permission == PackageManager.PERMISSION_GRANTED) {
                    modelLoadListener.export();
                    getDialog().dismiss();
                } else {
                    toast = Toast.makeText(view.getContext(), R.string.write_permissions_denied, Toast.LENGTH_LONG);
                    toast.show();
                    getDialog().dismiss();
                }
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelLoadListener.edit();
                getDialog().dismiss();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelLoadListener.delete();
                getDialog().dismiss();
            }
        });

        updateTextSize(view);
        setTextUnderline(view);
        return view;
    }

    public void addModelLoadListener(ModelLoadListener modelLoadListener) {
        this.modelLoadListener = modelLoadListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    private void setTextUnderline(View view) {
        Button exportButton = (Button) view.findViewById(R.id.buttonSelectExport);
        exportButton.setPaintFlags(exportButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Button editButton = (Button) view.findViewById(R.id.buttonSelectEdit);
        editButton.setPaintFlags(editButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Button deleteButton = (Button) view.findViewById(R.id.buttonDelete);
        deleteButton.setPaintFlags(deleteButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void updateTextSize(View view) {
        Button exportButton = (Button) view.findViewById(R.id.buttonSelectExport);
        exportButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        Button editButton = (Button) view.findViewById(R.id.buttonSelectEdit);
        editButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        Button deleteButton = (Button) view.findViewById(R.id.buttonDelete);
        deleteButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView avilableActionsLabel = (TextView) view.findViewById(R.id.textViewAvailableActions);
        avilableActionsLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 1.2f * textSize);

        TextView choiceLabel = (TextView) view.findViewById(R.id.textViewChoice);
        choiceLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }
}

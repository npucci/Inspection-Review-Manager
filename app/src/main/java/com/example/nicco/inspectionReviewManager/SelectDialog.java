package com.example.nicco.inspectionReviewManager;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Nicco on 2017-08-08.
 */

public class SelectDialog extends DialogFragment {
    private ModelLoadListener modelLoadListener;

    public SelectDialog() { super(); }

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
                // export and open review as a doc file
                ActivityCompat.requestPermissions(SelectDialog.this.getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                int permission = ActivityCompat.checkSelfPermission(SelectDialog.this.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(permission == PackageManager.PERMISSION_GRANTED) {
                    if(modelLoadListener.export()) {
                        Toast toast = Toast.makeText(view.getContext(), R.string.exported_doc_message, Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(view.getContext(), R.string.unable_to_export_doc_message, Toast.LENGTH_LONG);
                        toast.show();
                    }
                    getDialog().dismiss();
                } else {
                    Toast toast = Toast.makeText(view.getContext(), R.string.write_permissions_denied, Toast.LENGTH_LONG);
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
}

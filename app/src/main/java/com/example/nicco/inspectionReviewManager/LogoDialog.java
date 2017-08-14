package com.example.nicco.inspectionReviewManager;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

/**
 * Created by Nicco on 2017-08-11.
 */

public class LogoDialog extends DialogFragment {
    public LogoDialog() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.logo_dialog, container, false);
        final TabHost tabHost = (TabHost) view.findViewById(R.id.tabHost);
        tabHost.setup();

        // SETTINGS TAB
        TabHost.TabSpec settingsTab = tabHost.newTabSpec("Settings");
        settingsTab.setIndicator("Settings");
        settingsTab.setContent(R.id.Settings);
        tabHost.addTab(settingsTab);

        // ABOUT TAB
        TabHost.TabSpec aboutTab = tabHost.newTabSpec("About");
        aboutTab.setIndicator("About");
        aboutTab.setContent(R.id.About);
        tabHost.addTab(aboutTab);

        // LICENSES TAB
        TabHost.TabSpec licensesTab = tabHost.newTabSpec("Licenses");
        licensesTab.setIndicator("Licenses");
        licensesTab.setContent(R.id.Licenses);
        tabHost.addTab(licensesTab);

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}

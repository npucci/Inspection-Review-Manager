package com.example.nicco.inspectionReviewManager;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nicco on 2017-08-11.
 */

public class LogoDialog extends DialogFragment {
    private float textSize;

    public LogoDialog() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.logo_dialog, container, false);

        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("AppPref", 0);
        textSize = sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize));

        final TabHost tabHost = (TabHost) view.findViewById(R.id.tabHost);
        tabHost.setup();

        // SETTINGS TAB
        TabHost.TabSpec settingsTab = tabHost.newTabSpec("Settings");
        settingsTab.setIndicator("Settings");
        settingsTab.setContent(R.id.Settings);
        tabHost.addTab(settingsTab);

        final Switch textSizeControlSwitch = (Switch) view.findViewById(R.id.switchTextSizeControl);
        textSizeControlSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    textSize = getResources().getDimension(R.dimen.largeTextSize);
                    textSizeControlSwitch.setText("Turn Off Large Text");
                } else {
                    textSize = getResources().getDimension(R.dimen.defaultTextSize);
                    textSizeControlSwitch.setText("Turn On Large Text");
                }
                updateTextSize(LogoDialog.this.getView());
                ((MainActivity) getActivity()).setTextSize(textSize);
            }
        });
        if(textSize == getResources().getDimension(R.dimen.largeTextSize)) {
            textSizeControlSwitch.setChecked(true);
            textSizeControlSwitch.setText("Turn Off Large Text");
        } else {
            textSizeControlSwitch.setText("Turn On Large Text");
        }
        textSizeControlSwitch.setSwitchMinWidth((int)textSize * 5);

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

        TextView settingsTabTextView = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        settingsTabTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView aboutTabTextView = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        aboutTabTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView licensesTabTextView = (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        licensesTabTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        updateTextSize(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void setTextSize(float textSize) {
        this.textSize = textSize;
        updateTextSize(getView());
    }

    private void updateTextSize(View view) {
        if(view == null) return;

        TabHost tabHost = (TabHost) view.findViewById(R.id.tabHost);
        TextView settingsTab = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        settingsTab.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView aboutTab = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        aboutTab.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView licensesTab = (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        licensesTab.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        Switch textSizeControlSwitch = (Switch) view.findViewById(R.id.switchTextSizeControl);
        textSizeControlSwitch.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        textSizeControlSwitch.setSwitchMinWidth((int)textSize * 5);
        Button backUpDatabaseButton = (Button) view.findViewById(R.id.buttonBackUpDatabase);
        backUpDatabaseButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView noteTextView = (TextView) view.findViewById(R.id.textViewNote);
        noteTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView appNameTextView = (TextView) view.findViewById(R.id.textViewAppName);
        appNameTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView appVersionTextView = (TextView) view.findViewById(R.id.textViewAppVersion);
        appVersionTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView companyTextView = (TextView) view.findViewById(R.id.textViewCompany);
        companyTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView purposeTextView = (TextView) view.findViewById(R.id.textViewPurpose);
        purposeTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView usageTextView = (TextView) view.findViewById(R.id.textViewUsage);
        usageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        TextView librariesUsedTextView = (TextView) view.findViewById(R.id.textViewLibrariesUsed);
        librariesUsedTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView apachePoiTextView = (TextView) view.findViewById(R.id.textViewApachePoi);
        apachePoiTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView scratchpadTextView = (TextView) view.findViewById(R.id.textViewScratchpad);
        scratchpadTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView commonsCollectionsTextView = (TextView) view.findViewById(R.id.textViewCommonsCollections);
        commonsCollectionsTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        TextView propertyTextView = (TextView) view.findViewById(R.id.textViewProperty);
        propertyTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }
}

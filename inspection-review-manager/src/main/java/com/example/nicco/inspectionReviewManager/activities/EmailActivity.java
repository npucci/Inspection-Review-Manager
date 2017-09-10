package com.example.nicco.inspectionReviewManager.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nicco.inspectionReviewManager.R;
import com.example.nicco.inspectionReviewManager.customDatatypes.DatabaseWriter;
import com.example.nicco.inspectionReviewManager.customDatatypes.Model;
import com.example.nicco.inspectionReviewManager.customDatatypes.QueryingAutoCompleteTextView;
import com.example.nicco.inspectionReviewManager.interfaces.AutoFillActivity;

import java.util.ArrayList;

/**
 * Created by Nicco on 2017-09-09.
 */

public class EmailActivity extends FragmentActivity implements AutoFillActivity {
    private TextView emailToLabel;
    private QueryingAutoCompleteTextView emailTo;
    private TextView emailCCLabel;
    private QueryingAutoCompleteTextView emailCC;
    private TextView subjectLabel;
    private EditText subject;
    private TextView messageLabel;
    private EditText message;
    private TextView attachmentLabel;
    private TextView attachment;
    private Button createEmailButton;

    private boolean ready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        final Model model = (Model) getApplicationContext();
        String officeEmailAddress = getResources().getString(R.string.office_email_address);

        emailToLabel = (TextView) findViewById(R.id.textViewEmailTo);
        emailTo = (QueryingAutoCompleteTextView) findViewById(R.id.autocompleteEmailTo);
        emailTo.setText(R.string.office_email_address);
        emailTo.set(this, model, this, DatabaseWriter.EMAIL_TABLE_NAME, DatabaseWriter.EMAIL_ADDRESS_COLUMN, new String[]{officeEmailAddress});

        emailCCLabel = (TextView) findViewById(R.id.textViewEmailCC);
        emailCC = (QueryingAutoCompleteTextView) findViewById(R.id.autoCompleteEmailCC);
        emailCC.set(this, model, this, DatabaseWriter.EMAIL_TABLE_NAME, DatabaseWriter.EMAIL_ADDRESS_COLUMN, new String[]{officeEmailAddress});

        subjectLabel = (TextView) findViewById(R.id.textViewSubject);
        subject = (EditText) findViewById(R.id.editTextSubject);
        subject.setText(model.createEmailSubject());

        messageLabel = (TextView) findViewById(R.id.textViewMessage);
        message = (EditText) findViewById(R.id.editTextMessage);

        attachmentLabel = (TextView) findViewById(R.id.textViewAttachmentLabel);
        attachment = (TextView) findViewById(R.id.textViewAttachment);
        attachment.setText(model.makeReviewTitle() + ".doc");

        createEmailButton = (Button) findViewById(R.id.buttonCreateEmail);
        createEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkReady();
                if(ready) {
                    createEmail();
                    model.addEmailsToDatabase(getEmails());
                }
            }
        });

        emailTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        emailCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkReady();
            }
        });

        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkReady();
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkReady();
            }
        });

        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkReady();
            }
        });

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
        setTextUnderline();
    }

    private void checkReady() {
        boolean isFilled = true;

        if(emailTo.getText().toString().isEmpty()) {
            emailToLabel.setTextColor(Color.RED);
            isFilled = false;
        }
        else emailToLabel.setTextColor(Color.BLACK);

        if(subject.getText().toString().isEmpty()) {
            subjectLabel.setTextColor(Color.RED);
            isFilled = false;
        }
        else subjectLabel.setTextColor(Color.BLACK);

        ready = isFilled;
        Log.v("NICCO", "ready = " + ready);
    }

    private void setTextSize(Float textSize) {
        emailToLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        emailCCLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        subjectLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        messageLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        attachmentLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        createEmailButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    private void setTextUnderline() {
        emailToLabel.setPaintFlags(emailToLabel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        emailCCLabel.setPaintFlags(emailCCLabel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        subjectLabel.setPaintFlags(subjectLabel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        messageLabel.setPaintFlags(messageLabel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        attachmentLabel.setPaintFlags(attachmentLabel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        createEmailButton.setPaintFlags(createEmailButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void createEmail() {
        String to = emailTo.getText().toString();
        String cc = emailCC.getText().toString();
        String emailSubject = subject.getText().toString();
        String emailMessage = message.getText().toString();;
        Model model = (Model) getApplicationContext();
        model.emailExportDoc(getBaseContext(), getFragmentManager(), to, cc, emailSubject, emailMessage);
    }

    private ArrayList<String> getEmails() {
        ArrayList<String> emails = new ArrayList<String>();
        String to = emailTo.getText().toString();
        String[] split = to.split("( )|(, )|( ,)|( , )|(,)|(\n)");
        for(String email : split) if(email != null && !email.isEmpty()) emails.add(email);

        String cc = emailCC.getText().toString();
         split = cc.split("( )|(, )|( ,)|( , )|(,)|(\n)");
        for(String email : split) if(email != null && !email.isEmpty()) emails.add(email);

        return emails;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        Model model = (Model) getApplicationContext();
        model.reset();
        super.onStop();
    }

    @Override
    public void autofill(Object uiComponent) {}
}
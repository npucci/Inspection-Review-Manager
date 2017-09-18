package com.example.nicco.inspectionReviewManager.activities;

import android.content.Intent;
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
import com.example.nicco.inspectionReviewManager.customDatatypes.QueryingMultiAutoCompleteTextView;
import com.example.nicco.inspectionReviewManager.interfaces.AutoFillActivity;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Nicco on 2017-09-09.
 */

public class EmailActivity extends FragmentActivity implements AutoFillActivity {
    private TextView emailToLabel;
    private QueryingMultiAutoCompleteTextView emailTo;
    private TextView emailCCLabel;
    private QueryingMultiAutoCompleteTextView emailCC;
    private TextView subjectLabel;
    private EditText subject;
    private TextView messageLabel;
    private EditText message;
    private TextView attachmentLabel;
    private TextView attachment;
    private Button createEmailButton;
    private Button backButton;

    private boolean ready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        emailToLabel = (TextView) findViewById(R.id.textViewEmailTo);
        emailTo = (QueryingMultiAutoCompleteTextView) findViewById(R.id.autocompleteEmailTo);

        emailCCLabel = (TextView) findViewById(R.id.textViewEmailCC);
        emailCC = (QueryingMultiAutoCompleteTextView) findViewById(R.id.autoCompleteEmailCC);

        subjectLabel = (TextView) findViewById(R.id.textViewSubject);
        subject = (EditText) findViewById(R.id.editTextSubject);

        messageLabel = (TextView) findViewById(R.id.textViewMessage);
        message = (EditText) findViewById(R.id.editTextMessage);

        attachmentLabel = (TextView) findViewById(R.id.textViewAttachmentLabel);
        attachment = (TextView) findViewById(R.id.textViewAttachment);

        createEmailButton = (Button) findViewById(R.id.buttonCreateEmail);
        backButton = (Button) findViewById(R.id.buttonEmailBack);

        final Model model = (Model) getApplicationContext();

        createEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkReady();
                if (ready) {
                    createEmail();
                    model.addEmailsToDatabase(getEmails());
                }
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = (Model) getApplicationContext();
                model.reset();
                Intent intent = new Intent(EmailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        if( model.getViewedFilePath() == null ) {
            String officeEmailAddress = getResources().getString(R.string.office_email_address);

            emailTo.set(this, model, this, DatabaseWriter.EMAIL_TABLE_NAME, DatabaseWriter.EMAIL_ADDRESS_COLUMN, new String[]{officeEmailAddress});
            emailTo.setText(officeEmailAddress + ", ");

            emailCC.set(this, model, this, DatabaseWriter.EMAIL_TABLE_NAME, DatabaseWriter.EMAIL_ADDRESS_COLUMN, new String[]{officeEmailAddress});
            emailCC.setText("");

            subject.setText(model.createEmailSubject());
            message.setText("");

            attachment.setText(model.makeReviewTitle() + ".doc");

            emailTo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkReady();
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
        }

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
        setTextUnderline();
    }

    @Override
    public void onStart() {
        super.onStart();

        Model model = (Model) getApplicationContext();
        if( model.getViewedFilePath() == null ) {
            model.exportReviewToDoc(this, getFragmentManager(), false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Model model = (Model) getApplicationContext();

        Log.v("PUCCI", "onResume(), viewedFile = " + model.getViewedFilePath());

        if( model.getViewedFilePath() == null ) {
            String officeEmailAddress = getResources().getString(R.string.office_email_address);

            emailTo.set(this, model, this, DatabaseWriter.EMAIL_TABLE_NAME, DatabaseWriter.EMAIL_ADDRESS_COLUMN, new String[]{officeEmailAddress});
            emailTo.setText(officeEmailAddress + ", ");

            emailCC.set(this, model, this, DatabaseWriter.EMAIL_TABLE_NAME, DatabaseWriter.EMAIL_ADDRESS_COLUMN, new String[]{officeEmailAddress});
            emailCC.setText("");

            subject.setText(model.createEmailSubject());
            message.setText("");
            attachment.setText(model.makeReviewTitle() + ".doc");
        }
        else {
            attachment.setText(new File(model.getViewedFilePath()).getName());
        }

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AppPref", 0);
        setTextSize(sharedPreferences.getFloat("TextSize", getResources().getDimension(R.dimen.defaultTextSize)));
        setTextUnderline();
    }

    private void checkReady() {
        boolean isFilled = true;
        int invalid = Color.RED;
        int valid = Color.GRAY;

        String toStr = emailTo.getText().toString().replace("\n", "");
        if (toStr.isEmpty()) {
            emailToLabel.setTextColor(invalid);
            isFilled = false;
        } else emailToLabel.setTextColor(valid);

        String subjectStr = subject.getText().toString().replace("\n", "");
        if (subjectStr.isEmpty()) {
            subjectLabel.setTextColor(invalid);
            isFilled = false;
        } else subjectLabel.setTextColor(valid);

        ready = isFilled;
    }

    private void setTextSize(Float textSize) {
        emailToLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        emailTo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        emailCCLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        emailCC.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        subjectLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        subject.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        messageLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        message.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        attachmentLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        attachment.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        createEmailButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        backButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    private void setTextUnderline() {
        createEmailButton.setPaintFlags(createEmailButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        backButton.setPaintFlags(backButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void createEmail() {
        ArrayList<String> toList = new ArrayList<>();

        String toTotal = emailTo.getText().toString();
        String[] temp = toTotal.split( "( )|(,)|(, )|(\n)" );
        for ( int i = 0; i < temp.length; i++ ) {
            if( !temp[i].isEmpty() ) {
                toList.add( temp[i] );
            }
        }

        ArrayList<String> ccList = new ArrayList<>();
        String ccTotal = emailCC.getText().toString();
        temp = ccTotal.split( "( )|(,)|(, )|(\n)" );
        for ( int i = 0; i < temp.length; i++ ) {
            if( !temp[i].isEmpty() ) {
                ccList.add( temp[i] );
            }
        }

        String emailSubject = subject.getText().toString();
        String emailMessage = message.getText().toString();
        Model model = ( Model ) getApplicationContext();
        model.emailExportDoc(
                getBaseContext(),
                getFragmentManager(),
                toList.toArray( new String[]{} ),
                ccList.toArray( new String[]{} ),
                emailSubject,
                emailMessage );
    }

    private ArrayList<String> getEmails() {
        ArrayList<String> emails = new ArrayList<String>();

        String toTotal = emailTo.getText().toString();
        String[] temp = toTotal.split( "( )|(,)|(, )|(\n)" );
        for ( int i = 0; i < temp.length; i++ ) {
            if( !temp[i].isEmpty() ) {
                emails.add( temp[i] );
            }
        }

        String ccTotal = emailCC.getText().toString();
        temp = ccTotal.split( "( )|(,)|(, )|(\n)" );
        for ( int i = 0; i < temp.length; i++ ) {
            if( !temp[i].isEmpty() ) {
                emails.add( temp[i] );
            }
        }

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
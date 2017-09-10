package com.example.nicco.inspectionReviewManager.customDatatypes;

import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.nicco.inspectionReviewManager.R;
import com.example.nicco.inspectionReviewManager.dialogs.ExportingProgressDialog;
import com.example.nicco.inspectionReviewManager.utilities.FileIO;

import java.io.File;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Created by Nicco on 2017-07-17.
 */

public class Model extends Application {
    private HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap = new HashMap<>();
    private DatabaseWriter dbWriter;
    private EmailExportDocAsyncTask emailExportDocTask;
    private ExportHTMLAsyncTask exportHTMLTask;
    private ExportDocAsyncTask exportDocTask;
    private ExportDatabaseAsyncTask exportDatabaseTask;
    private File exportHTML;

    public enum SpecialValue {
        YES ("Yes"),
        NO ("No"),
        NA ("N/A"),
        NONE("None"),
        EMPTY (""),
        PM("PM"),
        AM("AM");

        private String value;
        SpecialValue(String value) { this.value = value; }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum ReviewStatusValue {
        APPROVED ("Approved"),
        NOT_APPROVED ("Not Approved"),
        REINSPECTION_REQUIRED ("Reinspection Required");

        private String value;
        ReviewStatusValue(String value) { this.value = value; }

        @Override
        public String toString() {
            return value;
        }
    }

    // COLORS
    private int completeBKGColor;

    // ACTIVITY COMPLETE FLAGS
    private boolean dateActivityComplete = false;
    private boolean projectActivityComplete = false;
    private boolean concreteActivityComplete = false;
    private boolean framingActivityComplete = false;
    private boolean conclusionActivityComplete = false;

    @Override
    public void onCreate() {
        super.onCreate();
        completeBKGColor = ContextCompat.getColor(getApplicationContext(), R.color.completeBackground);
        dbWriter = new DatabaseWriter(getBaseContext());
    }

    public void updateValue(DatabaseWriter.UIComponentInputValue key, String value) { hashMap.put(key, value); }

    public String getValue(DatabaseWriter.UIComponentInputValue key) {
        String value = hashMap.get(key);
        if(value == null) value = "";
        return value;
    }

    public int getBackgroundColor(String listItem) {
        int incompleteBKGColor = ContextCompat.getColor(getApplicationContext(), R.color.incompleteBackground);
        return  getColor(listItem, completeBKGColor, incompleteBKGColor);
    }

    public int getTextColor(String listItem) {
        int completeTextColor = ContextCompat.getColor(getApplicationContext(), R.color.completeText);
        int incompleteTextColor = ContextCompat.getColor(getApplicationContext(), R.color.incompleteText);
        return  getColor(listItem, completeTextColor, incompleteTextColor);
    }

    private int getColor(String listItem, int completeColor, int incompleteColor) {
        if(listItem.equals("Date") && dateActivityComplete) {
            return completeColor;
        } else if(listItem.equals("Project") && projectActivityComplete) {
            return completeColor;
        } else if(listItem.equals("Concrete") && concreteActivityComplete) {
            return completeColor;
        } else if(listItem.equals("Framing") && framingActivityComplete) {
            return completeColor;
        } else if(listItem.equals("Conclusion") && conclusionActivityComplete) {
            return completeColor;
        }
        return incompleteColor;
    }

    public boolean checkDateActivityStatus() {
        dateActivityComplete = false;

        // DATE ACTIVITY
        if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.DATE))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.TIME))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.WEATHER)))return false;
        else if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.TEMPERATURE_CELSIUS)))return false;

        dateActivityComplete = true;
        return true;
    }

    public boolean checkProjectActivityStatus() {
        projectActivityComplete = false;

        if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.ADDRESS))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.CITY))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.PROVINCE))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.DEVELOPER))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.CONTRACTOR)))return false;
        else if (isChecked(DatabaseWriter.UIComponentInputValue.FOOTINGS_REVIEW)) projectActivityComplete = true;
        else if (isChecked(DatabaseWriter.UIComponentInputValue.FOUNDATION_WALLS_REVIEW)) projectActivityComplete = true;
        else if (isChecked(DatabaseWriter.UIComponentInputValue.SHEATHING_REVIEW)) projectActivityComplete = true;
        else if (isChecked(DatabaseWriter.UIComponentInputValue.FRAMING_REVIEW)) projectActivityComplete = true;
        else if(isChecked(DatabaseWriter.UIComponentInputValue.OTHER_REVIEW))
            projectActivityComplete = validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.OTHER_REVIEW_DESCRIPTION));
        return true;
    }

    public boolean checkConcreteActivityStatus() {
        concreteActivityComplete = false;

        if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION)) return false;
        else if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE)) return false;
        else if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE)) return false;
        else if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.FORMWORK)) return false;

        concreteActivityComplete = true;
        return true;
    }

    public boolean checkFramingActivityStatus() {
        framingActivityComplete = false;

        if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC)) return false;
        else if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.IJOIST)) return false;
        else if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.BEARING)) return false;
        else if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES)) return false;
        else if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.LINTELS)) return false;
        else if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS)) return false;
        else if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS)) return false;
        else if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.BLOCKING)) return false;
        else if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING)) return false;
        else if(!isValidCheckValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS)) return false;

        framingActivityComplete = true;
        return true;
    }

    public boolean checkConclusionActivityStatus() {
        conclusionActivityComplete = false;

        if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.OBSERVATIONS))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.COMMENTS))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.REVIEWED_BY))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.STAMPED))) return false;

        conclusionActivityComplete = true;
        return true;
    }

    public boolean validValue(String value) { return value != null && !value.isEmpty(); }

    public boolean isChecked(DatabaseWriter.UIComponentInputValue key) {
        return isChecked(getValue(key));
    }

    public boolean isChecked(String key) {
        return key != null && key.equals(SpecialValue.YES.toString());
    }

    public boolean isValidCheckValue(DatabaseWriter.UIComponentInputValue key) {
        String value = getValue(key);
        return value != null && (value.equals(SpecialValue.YES.toString()) || value.equals(SpecialValue.NO.toString()));
    }

    public String[] queryDatabase(String table, DatabaseWriter.UIComponentInputValue column, String whereClause, String[] whereArgs) {
        return dbWriter.query(table, column, whereClause, whereArgs);
    }

    public String[] queryMatchSearchDatabase(String table, String column, String input) {
        String whereClause = column + " LIKE '%" + input + "%'" + " ORDER BY " + column
                + " DESC" + " LIMIT 0,5";
        return dbWriter.query(table, column, whereClause, null);
    }

    public String monthIntToString(int month) {
        String[] months = new DateFormatSymbols().getMonths();
        if(month < months.length) return months[month];
        return "";
    }

    public int monthStringToInt(String month) {
        String[] months = new DateFormatSymbols().getMonths();
        for(int i = 0; i < months.length; i++) {
            if(month.equals(months[i])) return i;
        }
        return -1;
    }

    public boolean reviewStarted() {
        return !hashMap.isEmpty();
    }

    public String[] combineArrays(String[] arr1, String[] arr2) {
        LinkedHashSet<String> combined = new LinkedHashSet<>();
        if(arr1 != null) combined.addAll(Arrays.asList(arr1));
        if(arr2 != null) combined.addAll(Arrays.asList(arr2));
        return combined.toArray(new String[combined.size()]);
    }

    public LinkedHashSet<String> combineArraysLinkedHashSet(String[] arr1, String[] arr2) {
        LinkedHashSet<String> combined = new LinkedHashSet<>();
        if(arr1 != null) combined.addAll(Arrays.asList(arr1));
        if(arr2 != null) combined.addAll(Arrays.asList(arr2));
        return combined;
    }

     public boolean insertReviewToDatabase() {
         DatabaseWriter.UIComponentInputValue[] keySet = hashMap.keySet().toArray(
                 new DatabaseWriter.UIComponentInputValue[hashMap.keySet().size()]);
         ArrayList<DatabaseWriter.UIComponentInputValue> columns = new ArrayList<>();
         for(DatabaseWriter.UIComponentInputValue value : keySet) if(value.isDatabaseColum()) columns.add(value);
         return dbWriter.insertValues(hashMap, columns.toArray(new DatabaseWriter.UIComponentInputValue[columns.size()]));
     }

    public boolean updateReviewToDatabase() { return dbWriter.updateReviewValues(hashMap); }

    public boolean addEmailsToDatabase(ArrayList<String> emailAddresses) { return dbWriter.addEmailAddresses(emailAddresses); }

    public boolean reviewExistsInDatabase() { return dbWriter.existsInDatabase(hashMap); }

    public String makeReviewTitle() {
        String fileName = "";
        String temp = hashMap.get(DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER);
        if(temp == null) return fileName;

        if(temp.length() > 20) temp = temp.substring(0, 21);
        fileName += temp + " (";

        temp = hashMap.get(DatabaseWriter.UIComponentInputValue.ADDRESS);
        if(temp == null) return fileName;

        if(temp.length() > 20) temp = temp.substring(0, 21);
        fileName += temp + ", ";

        temp = hashMap.get(DatabaseWriter.UIComponentInputValue.CITY);
        if(temp == null) return fileName;

        if(temp.length() > 20) temp = temp.substring(0, 21);
        fileName += temp + ", ";

        temp = hashMap.get(DatabaseWriter.UIComponentInputValue.PROVINCE);
        if(temp == null) return fileName;

        if(temp.length() > 20) temp = temp.substring(0, 21);
        fileName += temp + ") ";

        ArrayList<String> reviewTypes = getReviewTypes();
        for(int i = 0; i < reviewTypes.size(); i++) {
            if(i == reviewTypes.size() - 1) fileName += " and " + reviewTypes.get(i) + " Review ";
            else if(i > 0) fileName += ", " + reviewTypes.get(i);
            else fileName += reviewTypes.get(i);
        }

        String dateStr = hashMap.get(DatabaseWriter.UIComponentInputValue.DATE); // YYYY-MM-DD
        if(dateStr == null) return fileName;

        String[] date = dateStr.split("-");
        if(date.length == 3) fileName += "(" + date[1] + date[2] + date[0] + ")"; // MMDDYYYY
        else fileName += "(" + hashMap.get(DatabaseWriter.UIComponentInputValue.DATE) + ")"; // YYYY-MM-DD
        return fileName; // ie. "C15 (4295 Quarry Road, Coquitlam, BC) Sheathing and Framing Inspection Report (07242017).doc"
    }

    private ArrayList<String> getReviewTypes() {
        ArrayList<String> reviewTypes = new ArrayList<>();
        if(hashMap.get(DatabaseWriter.UIComponentInputValue.FOOTINGS_REVIEW).equals(SpecialValue.YES.toString()))
            reviewTypes.add(DatabaseWriter.UIComponentInputValue.FOOTINGS_REVIEW.getFormattedValue());
        if(hashMap.get(DatabaseWriter.UIComponentInputValue.FOUNDATION_WALLS_REVIEW).equals(SpecialValue.YES.toString()))
            reviewTypes.add(DatabaseWriter.UIComponentInputValue.FOUNDATION_WALLS_REVIEW.getFormattedValue());
        if(hashMap.get(DatabaseWriter.UIComponentInputValue.SHEATHING_REVIEW).equals(SpecialValue.YES.toString()))
            reviewTypes.add(DatabaseWriter.UIComponentInputValue.SHEATHING_REVIEW.getFormattedValue());
        if(hashMap.get(DatabaseWriter.UIComponentInputValue.FRAMING_REVIEW).equals(SpecialValue.YES.toString()))
            reviewTypes.add(DatabaseWriter.UIComponentInputValue.FRAMING_REVIEW.getFormattedValue());
        if(hashMap.get(DatabaseWriter.UIComponentInputValue.OTHER_REVIEW).equals(SpecialValue.YES.toString()))
            reviewTypes.add(DatabaseWriter.UIComponentInputValue.OTHER_REVIEW.getFormattedValue());
        return reviewTypes;
    }

    public File getExportHTML() {
        return exportHTML;
    }

    public boolean emailExportDoc(Context context, FragmentManager fragmentManager, String to, String cc, String subject, String message) {
        if(emailExportDocTask != null && (emailExportDocTask.getStatus() == AsyncTask.Status.PENDING ||
                emailExportDocTask.getStatus() == AsyncTask.Status.RUNNING)) {
            Log.v("NICCO", "export doc already pending/running");
            emailExportDocTask.showProgressDialog();
            return false;
        }
        Log.v("NICCO", "export doc executing");
        String fileName = makeReviewTitle();
        emailExportDocTask = new EmailExportDocAsyncTask(context, hashMap, fileName, fragmentManager, this);
        emailExportDocTask.addEmailTo(to);
        emailExportDocTask.addEmailcc(cc);
        emailExportDocTask.addEmailSubject(subject);
        emailExportDocTask.addEmailMessage(message);
        emailExportDocTask.execute();
        return true;
    }

    public boolean exportReviewToHTML(Context context, FragmentManager fragmentManager) {
        if(exportHTMLTask != null && (exportHTMLTask.getStatus() == AsyncTask.Status.PENDING ||
                exportHTMLTask.getStatus() == AsyncTask.Status.RUNNING)) {
            Log.v("NICCO", "export HTML already pending/running");
            exportHTMLTask.showProgressDialog();
            return false;
        }
        Log.v("NICCO", "export HTML executing");
        String fileName = makeReviewTitle();
        exportHTMLTask = new ExportHTMLAsyncTask(context, hashMap, fileName, fragmentManager, this);
        exportHTMLTask.execute();
        return true;
    }

    public boolean exportReviewToDoc(Context context, FragmentManager fragmentManager) {
        if(exportDocTask != null && (exportDocTask.getStatus() == AsyncTask.Status.PENDING ||
                exportDocTask.getStatus() == AsyncTask.Status.RUNNING)) {
            Log.v("NICCO", "export doc already pending/running");
            exportDocTask.showProgressDialog();
            return false;
        }
        Log.v("NICCO", "export doc executing");
        String fileName = makeReviewTitle();
        exportDocTask = new ExportDocAsyncTask(context, hashMap, fileName, fragmentManager, this);
        exportDocTask.execute();
        return true;
    }

    public Cursor getDatabaseCursor() {
        return dbWriter.getCursor();
    }

    public boolean backupDatabase(Context context, FragmentManager fragmentManager) {
        if(exportDatabaseTask != null && (exportDatabaseTask.getStatus() == AsyncTask.Status.PENDING ||
                exportDatabaseTask.getStatus() == AsyncTask.Status.RUNNING)) {
            Log.v("NICCO", "export database already pending/running");
            exportDatabaseTask.showProgressDialog();
            return false;
        }
        Log.v("NICCO", "export database executing");
        String filePath = dbWriter.getDatabasePath();
        exportDatabaseTask = new ExportDatabaseAsyncTask(context, filePath, fragmentManager, this);
        exportDatabaseTask.execute();
        return true;
    }

    public void AutoFillConcreteActivity() {
        Log.v("PUCCI", "AutoFillConcreteActivity CALLED");
        updateValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.REBAR_POSITION_INSTRUCTION, Model.SpecialValue.NA.toString());

        updateValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.REBAR_SIZE_INSTRUCTION, Model.SpecialValue.NA.toString());

        updateValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.ANCHORAGE_INSTRUCTION, Model.SpecialValue.NA.toString());

        updateValue(DatabaseWriter.UIComponentInputValue.FORMWORK, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.FORMWORK_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.FORMWORK_INSTRUCTION, Model.SpecialValue.NA.toString());
    }

    public void AutoFillFramingActivity() {
        Log.v("PUCCI", "AutoFillFramingActivity CALLED");

        updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.TRUSS_SPEC_INSTRUCTION, Model.SpecialValue.NA.toString());

        updateValue(DatabaseWriter.UIComponentInputValue.IJOIST, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.IJOIST_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.IJOIST_INSTRUCTION, Model.SpecialValue.NA.toString());

        updateValue(DatabaseWriter.UIComponentInputValue.BEARING, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.BEARING_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.BEARING_INSTRUCTION, Model.SpecialValue.NA.toString());

        updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.TOP_PLATES_INSTRUCTION, Model.SpecialValue.NA.toString());

        updateValue(DatabaseWriter.UIComponentInputValue.LINTELS, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.LINTELS_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.LINTELS_INSTRUCTION, Model.SpecialValue.NA.toString());

        updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.SHEARWALLS_INSTRUCTION, Model.SpecialValue.NA.toString());

        updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.TALL_WALLS_INSTRUCTION, Model.SpecialValue.NA.toString());

        updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.BLOCKING_INSTRUCTION, Model.SpecialValue.NA.toString());

        updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.WALL_SHEATHING_INSTRUCTION, Model.SpecialValue.NA.toString());

        updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS, SpecialValue.NO.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_NA, SpecialValue.YES.toString());
        updateValue(DatabaseWriter.UIComponentInputValue.WIND_GIRTS_INSTRUCTION, Model.SpecialValue.NA.toString());
    }

    public void reset() {
        hashMap = new HashMap<>();
        dateActivityComplete = false;
        projectActivityComplete = false;
        concreteActivityComplete = false;
        framingActivityComplete = false;
        conclusionActivityComplete = false;
    }

    public void loadReviewFromDatabase(HashMap<String, String> primaryKeys) {
        String[] columns = primaryKeys.keySet().toArray(new String[primaryKeys.keySet().size()]);
        String[] whereArgs = new String[columns.length];
        String whereClause = "";
        for(int i = 0; i < columns.length; i++) {
            whereArgs[i] = primaryKeys.get(columns[i]);
            whereClause += columns[i] + " = " + "?";
            if(i < columns.length - 1) whereClause += " AND ";
            else whereClause += " ";
        }
        hashMap = dbWriter.loadReview(DatabaseWriter.getDatabaseColumns(), whereClause, whereArgs);

        // REVIEW STATUS
        if(hashMap.get(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS).equals(ReviewStatusValue.APPROVED.toString())) {
            hashMap.put(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_APPROVED, SpecialValue.YES.toString());
        } else if(hashMap.get(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS).equals(ReviewStatusValue.NOT_APPROVED.toString())) {
            hashMap.put(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_NOT_APPROVED, SpecialValue.YES.toString());
        } else if(hashMap.get(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS).equals(ReviewStatusValue.REINSPECTION_REQUIRED.toString())) {
            hashMap.put(DatabaseWriter.UIComponentInputValue.REVIEW_STATUS_REINSPECTION_REQUIRED, SpecialValue.YES.toString());
        }
    }

    public void deleteReviewFromDatabase(HashMap<String, String> primaryKeys) {
        String[] columns = primaryKeys.keySet().toArray(new String[primaryKeys.keySet().size()]);
        String[] whereArgs = new String[columns.length];
        String whereClause = "";
        for(int i = 0; i < columns.length; i++) {
            whereArgs[i] = primaryKeys.get(columns[i]);
            whereClause += columns[i] + " = " + "?";
            if(i < columns.length - 1) whereClause += " AND ";
            else whereClause += " ";
        }
        dbWriter.deleteReview(DatabaseWriter.getDatabaseColumns(), whereClause, whereArgs);
    }

    public File getExportHTML(Context context) {
        String fileName = makeReviewTitle();
        String dateStr = hashMap.get(DatabaseWriter.UIComponentInputValue.DATE); // YYYY-MM-DD
        if(dateStr == null) dateStr = "";

        String[] date = dateStr.split("-");
        String year = "";
        String month = "";
        String day = "";
        if(date.length == 3) {
            year = date[0];
            month = date[1] + " - " + monthIntToString(Integer.parseInt(date[1]) - 1); // monthIntToString uses months 0 to 11
            day = date[2];
        }

        String project = "";
        String address = hashMap.get(DatabaseWriter.UIComponentInputValue.ADDRESS);
        if(address != null) project = address;
        return FileIO.exportInspectionReviewToHTML(context, hashMap, fileName, year, month, day, project, null);
    }

    public String createEmailSubject() {
        String dateStr = hashMap.get(DatabaseWriter.UIComponentInputValue.DATE); // YYYY-MM-DD
        if(dateStr == null) dateStr = "";
        String[] date = dateStr.split("-");
        String year = "";
        String month = "";
        String day = "";
        if(date.length == 3) {
            year = date[0];
            month = monthIntToString(Integer.parseInt(date[1]) - 1); // monthIntToString uses months 0 to 11
            day = date[2];
        }

        return "Inspection Review - " +
                getValue(DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER) + " " +
                getValue(DatabaseWriter.UIComponentInputValue.ADDRESS) + ", " +
                getValue(DatabaseWriter.UIComponentInputValue.CITY) + ", " +
                getValue(DatabaseWriter.UIComponentInputValue.PROVINCE) + " - " +
                month + " " + day + ", " + year;
    }

    public class EmailExportDocAsyncTask extends AsyncTask<String, Integer, Boolean> {
        private Context context;
        private String fileName;
        private HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap;
        private FragmentManager fragmentManager;
        private ExportingProgressDialog exportingProgressDialog;
        private File emailAttachment;
        private String to;
        private String cc;
        private String subject;
        private String message;

        public EmailExportDocAsyncTask(Context context,
                                       HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap,
                                       String fileName, FragmentManager fragmentManager, Model model) {
            super();
            this.context = context;
            this.fileName = fileName;
            this.hashMap = hashMap;
            this.fragmentManager = fragmentManager;
            exportingProgressDialog = new ExportingProgressDialog();
            SharedPreferences sharedPreferences = model.getSharedPreferences("AppPref", 0);
            exportingProgressDialog.setTextSize(sharedPreferences.getFloat("TextSize", model.getResources().getDimension(R.dimen.defaultTextSize)));

        }

        public void addEmailTo(String to) {
            this.to = to;
        }

        public void addEmailcc(String cc) {
            this.cc = cc;
        }

        public void addEmailSubject(String subject) {
            this.subject = subject;
        }

        public void addEmailMessage(String message) {
            this.message = message;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exportingProgressDialog.show(fragmentManager, "dialog");
        }

        @Override
        protected void onProgressUpdate(Integer... integers) {
            exportingProgressDialog.setProgressPercentage(integers[0]);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String dateStr = hashMap.get(DatabaseWriter.UIComponentInputValue.DATE); // YYYY-MM-DD
            if(dateStr == null) dateStr = "";

            String[] date = dateStr.split("-");
            String year = "";
            String month = "";
            String day = "";
            if(date.length == 3) {
                year = date[0];
                month = date[1] + " - " + monthIntToString(Integer.parseInt(date[1]) - 1); // monthIntToString uses months 0 to 11
                day = date[2];
            }

            String project = "";
            String address = hashMap.get(DatabaseWriter.UIComponentInputValue.ADDRESS);
            if(address != null) project = address;
            emailAttachment = FileIO.exportInspectionReviewToDOC(context, hashMap, fileName, year, month, day, project, this);
            return emailAttachment != null;
        }

        public void doProgress(int value){
            publishProgress(value);
        }

        private void showProgressDialog() {
            if(exportingProgressDialog != null)
                exportingProgressDialog.show(fragmentManager, "dialog");
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            exportingProgressDialog.finished(result);
            FileIO.createEmail(context, to, cc, subject, message, new File[] {emailAttachment});
            exportingProgressDialog.dismiss();
        }
    }

    public class ExportDocAsyncTask extends AsyncTask<String, Integer, Boolean> {
        private Context context;
        private String fileName;
        private HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap;
        private FragmentManager fragmentManager;
        private ExportingProgressDialog exportingProgressDialog;

        public ExportDocAsyncTask(Context context,
                                  HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap,
                                  String fileName, FragmentManager fragmentManager, Model model) {
            super();
            this.context = context;
            this.fileName = fileName;
            this.hashMap = hashMap;
            this.fragmentManager = fragmentManager;
            exportingProgressDialog = new ExportingProgressDialog();
            SharedPreferences sharedPreferences = model.getSharedPreferences("AppPref", 0);
            exportingProgressDialog.setTextSize(sharedPreferences.getFloat("TextSize", model.getResources().getDimension(R.dimen.defaultTextSize)));

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exportingProgressDialog.show(fragmentManager, "dialog");
        }

        @Override
        protected void onProgressUpdate(Integer... integers) {
            exportingProgressDialog.setProgressPercentage(integers[0]);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String dateStr = hashMap.get(DatabaseWriter.UIComponentInputValue.DATE); // YYYY-MM-DD
            if(dateStr == null) dateStr = "";

            String[] date = dateStr.split("-");
            String year = "";
            String month = "";
            String day = "";
            if(date.length == 3) {
                year = date[0];
                month = date[1] + " - " + monthIntToString(Integer.parseInt(date[1]) - 1); // monthIntToString uses months 0 to 11
                day = date[2];
            }

            String project = "";
            String address = hashMap.get(DatabaseWriter.UIComponentInputValue.ADDRESS);
            if(address != null) project = address;
            File exportedFile = FileIO.exportInspectionReviewToDOC(context, hashMap, fileName, year, month, day, project, this);
            FileIO.openDocFile(context, exportedFile);
            return exportedFile != null;
        }

        public void doProgress(int value){
            publishProgress(value);
        }

        private void showProgressDialog() {
            if(exportingProgressDialog != null)
                exportingProgressDialog.show(fragmentManager, "dialog");
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            exportingProgressDialog.finished(result);
            exportingProgressDialog.dismiss();
        }
    }

    public class ExportHTMLAsyncTask extends AsyncTask<String, Integer, Boolean> {
        private Context context;
        private String fileName;
        private HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap;
        private FragmentManager fragmentManager;
        private ExportingProgressDialog exportingProgressDialog;
        private File exportFile;

        public ExportHTMLAsyncTask(Context context,
                                   HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap,
                                   String fileName, FragmentManager fragmentManager, Model model) {
            super();
            this.context = context;
            this.fileName = fileName;
            this.hashMap = hashMap;
            this.fragmentManager = fragmentManager;
            exportingProgressDialog = new ExportingProgressDialog();
            SharedPreferences sharedPreferences = model.getSharedPreferences("AppPref", 0);
            exportingProgressDialog.setTextSize(sharedPreferences.getFloat("TextSize", model.getResources().getDimension(R.dimen.defaultTextSize)));
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exportingProgressDialog.show(fragmentManager, "dialog");
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String dateStr = hashMap.get(DatabaseWriter.UIComponentInputValue.DATE); // YYYY-MM-DD
            if(dateStr == null) dateStr = "";

            String[] date = dateStr.split("-");
            String year = "";
            String month = "";
            String day = "";
            if(date.length == 3) {
                year = date[0];
                month = date[1] + " - " + monthIntToString(Integer.parseInt(date[1]) - 1); // monthIntToString uses months 0 to 11
                day = date[2];
            }

            String project = "";
            String address = hashMap.get(DatabaseWriter.UIComponentInputValue.ADDRESS);
            if(address != null) project = address;

            exportFile = FileIO.exportInspectionReviewToHTML(context, hashMap, fileName, year, month, day, project, this);
            FileIO.openHTMLFile(context, exportFile);
            return exportFile != null;
        }

        @Override
        protected void onProgressUpdate(Integer... integers) {
            exportingProgressDialog.setProgressPercentage(integers[0]);
        }

        public void doProgress(int value){
            publishProgress(value);
        }

        private void showProgressDialog() {
            if(exportingProgressDialog != null)
                exportingProgressDialog.show(fragmentManager, "dialog");
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            exportHTML = exportFile;
            exportingProgressDialog.finished(result);
            exportingProgressDialog.dismiss();
        }
    }

    public class ExportDatabaseAsyncTask extends AsyncTask<String, Integer, Boolean> {
        private Context context;
        private FragmentManager fragmentManager;
        private ExportingProgressDialog exportingProgressDialog;
        private String filePath;

        public ExportDatabaseAsyncTask(Context context, String filePath, FragmentManager fragmentManager, Model model) {
            super();
            this.context = context;
            this.fragmentManager = fragmentManager;
            this.filePath = filePath;
            exportingProgressDialog = new ExportingProgressDialog();
            SharedPreferences sharedPreferences = model.getSharedPreferences("AppPref", 0);
            exportingProgressDialog.setTextSize(sharedPreferences.getFloat("TextSize", model.getResources().getDimension(R.dimen.defaultTextSize)));
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exportingProgressDialog.show(fragmentManager, "dialog");
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return FileIO.exportDatabase(context, new File(filePath), this);
        }

        @Override
        protected void onProgressUpdate(Integer... integers) {
            exportingProgressDialog.setProgressPercentage(integers[0]);
        }

        public void doProgress(int value){
            publishProgress(value);
        }

        private void showProgressDialog() {
            if(exportingProgressDialog != null)
                exportingProgressDialog.show(fragmentManager, "dialog");
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            exportingProgressDialog.finished(result);
            exportingProgressDialog.dismiss();
        }
    }
}

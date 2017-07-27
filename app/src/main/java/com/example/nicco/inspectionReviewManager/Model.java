package com.example.nicco.inspectionReviewManager;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;

import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static com.example.nicco.inspectionReviewManager.R.string.maxSmallInputLength;

/**
 * Created by Nicco on 2017-07-17.
 */

public class Model extends Application {
    private HashMap<DatabaseWriter.DatabaseColumn, String> hashMap = new HashMap<DatabaseWriter.DatabaseColumn, String>();
    private DatabaseWriter dbWriter;
    private Context context;

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
    private int completeBKGColor = Color.rgb(108, 249, 93);
    private int incompleteBKGColor = Color.BLACK; //Color.rgb(249, 93, 93);
    private int completeTextColor = Color.BLACK;
    private int incompleteTextColor = Color.WHITE;

    // ACTIVITY COMPLETE FLAGS
    private boolean dateActivityComplete = false;
    private boolean projectActivityComplete = false;
    private boolean concreteActivityComplete = false;
    private boolean framingActivityComplete = false;
    private boolean conclusionActivityComplete = false;

    @Override
    public void onCreate() {
        super.onCreate();
        dbWriter = new DatabaseWriter(this.getBaseContext());
    }

    public void updateValue(DatabaseWriter.DatabaseColumn key, String value) { hashMap.put(key, value); }

    public String getValue(DatabaseWriter.DatabaseColumn key) {
        String value = hashMap.get(key);
        if(value == null) value = "";
        return value;
    }

    public int getBackgroundColor(String listItem) {
        return  getColor(listItem, completeBKGColor, incompleteBKGColor);
    }

    public int getTextColor(String listItem) {
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
        if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.DATE))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.TIME))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.WEATHER)))return false;
        else if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.TEMPERATURE_CELSIUS)))return false;

        dateActivityComplete = true;
        return true;
    }

    public boolean checkProjectActivityStatus() {
        projectActivityComplete = false;

        if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.ADDRESS))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.CITY))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.PROVINCE))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.PROJECT_NUMBER))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.DEVELOPER))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.CONTRACTOR)))return false;
        else if (isChecked(DatabaseWriter.DatabaseColumn.FOOTINGS_REVIEW)) projectActivityComplete = true;
        else if (isChecked(DatabaseWriter.DatabaseColumn.FOUNDATION_WALLS_REVIEW)) projectActivityComplete = true;
        else if (isChecked(DatabaseWriter.DatabaseColumn.SHEATHING_REVIEW)) projectActivityComplete = true;
        else if (isChecked(DatabaseWriter.DatabaseColumn.FRAMING_REVIEW)) projectActivityComplete = true;
        else if(isChecked(DatabaseWriter.DatabaseColumn.OTHER_REVIEW)) projectActivityComplete = validValue(hashMap.get(DatabaseWriter.DatabaseColumn.OTHER_REVIEW_DESCRIPTION));
        return true;
    }

    public boolean checkConcreteActivityStatus() {
        concreteActivityComplete = false;

        if(!isChecked(DatabaseWriter.DatabaseColumn.REBAR_POSITION)) return false;
        else if(!isChecked(DatabaseWriter.DatabaseColumn.REBAR_SIZE)) return false;
        else if(!isChecked(DatabaseWriter.DatabaseColumn.ANCHORAGE)) return false;
        else if(!isChecked(DatabaseWriter.DatabaseColumn.FORMWORK)) return false;

        concreteActivityComplete = true;
        return true;
    }

    public boolean checkFramingActivityStatus() {
        framingActivityComplete = false;

        if(!isChecked(DatabaseWriter.DatabaseColumn.TRUSS_SPEC)) return false;
        else if(!isChecked(DatabaseWriter.DatabaseColumn.IJOIST)) return false;
        else if(!isChecked(DatabaseWriter.DatabaseColumn.BEARING)) return false;
        else if(!isChecked(DatabaseWriter.DatabaseColumn.TOP_PLATES)) return false;
        else if(!isChecked(DatabaseWriter.DatabaseColumn.LINTELS)) return false;
        else if(!isChecked(DatabaseWriter.DatabaseColumn.SHEARWALLS)) return false;
        else if(!isChecked(DatabaseWriter.DatabaseColumn.TALL_WALLS)) return false;
        else if(!isChecked(DatabaseWriter.DatabaseColumn.BLOCKING)) return false;
        else if(!isChecked(DatabaseWriter.DatabaseColumn.WALL_SHEATHING)) return false;
        else if(!isChecked(DatabaseWriter.DatabaseColumn.WIND_GIRTS)) return false;

        framingActivityComplete = true;
        return true;
    }

    public boolean checkConclusionActivityStatus() {
        conclusionActivityComplete = false;

        if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.OBSERVATIONS))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.COMMENTS))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.REVIEW_STATUS))) return false;
        else if(!validValue(hashMap.get(DatabaseWriter.DatabaseColumn.REVIEWED_BY))) return false;

        conclusionActivityComplete = true;
        return true;
    }

    public boolean validValue(String value) { return value != null && !value.isEmpty(); }

    public boolean isChecked(DatabaseWriter.DatabaseColumn key) {
        String value = getValue(key);
        return value != null && value.equals(SpecialValue.YES.toString());
    }

    public String[] queryDatabase(DatabaseWriter.DatabaseColumn column, String whereClause, String[] whereArgs) {
        return dbWriter.query(column, whereClause, whereArgs);
    }

    public int monthToInt(String month) {
        String[] months = new DateFormatSymbols().getMonths();
        for(int i = 0; i < months.length; i++) {
            if(month.equals(months[i])) return i;
        }
        return -1;
    }

    public String formatDigitStr(String num) {
        if(num.length() < 2) return "0" + num;
        return num;
    }

    public String[] combineArrays(String[] arr1, String[] arr2) {
        LinkedHashSet<String> combined = new LinkedHashSet<String>();
        for(String s : arr1) combined.add(s);
        for(String s : arr2) combined.add(s);
        return combined.toArray(new String[combined.size()]);
    }

     public boolean insertReviewToDatabase() {
         HashMap<DatabaseWriter.DatabaseColumn, String> inputDataMap = new HashMap<DatabaseWriter.DatabaseColumn, String>();

         dbWriter.insertValues(hashMap);

        return true;
    }

    public boolean exportReviewToDoc() {

        return false;
    }
}

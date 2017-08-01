package com.example.nicco.inspectionReviewManager;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Created by Nicco on 2017-07-17.
 */

public class Model extends Application {
    private HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap = new HashMap<DatabaseWriter.UIComponentInputValue, String>();
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

    public void updateValue(DatabaseWriter.UIComponentInputValue key, String value) { hashMap.put(key, value); }

    public String getValue(DatabaseWriter.UIComponentInputValue key) {
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
        else if(isChecked(DatabaseWriter.UIComponentInputValue.OTHER_REVIEW)) projectActivityComplete = validValue(hashMap.get(DatabaseWriter.UIComponentInputValue.OTHER_REVIEW_DESCRIPTION));
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

        conclusionActivityComplete = true;
        return true;
    }

    public boolean validValue(String value) { return value != null && !value.isEmpty(); }

    public boolean isChecked(DatabaseWriter.UIComponentInputValue key) {
        String value = getValue(key);
        return value != null && value.equals(SpecialValue.YES.toString());
    }

    public boolean isValidCheckValue(DatabaseWriter.UIComponentInputValue key) {
        String value = getValue(key);
        return value != null && (value.equals(SpecialValue.YES.toString()) || value.equals(SpecialValue.NO.toString()));
    }

    public String[] queryDatabase(DatabaseWriter.UIComponentInputValue column, String whereClause, String[] whereArgs) {
        return dbWriter.query(column, whereClause, whereArgs);
    }

    public String[] queryDatabase(String column, String whereClause, String[] whereArgs) {
        return dbWriter.query(column, whereClause, whereArgs);
    }

    public int monthToInt(String month) {
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
        LinkedHashSet<String> combined = new LinkedHashSet<String>();
        for(String s : arr1) combined.add(s);
        for(String s : arr2) combined.add(s);
        return combined.toArray(new String[combined.size()]);
    }

     public boolean insertReviewToDatabase() {
         DatabaseWriter.UIComponentInputValue[] keySet = hashMap.keySet().toArray(
                 new DatabaseWriter.UIComponentInputValue[hashMap.keySet().size()]);
         ArrayList<DatabaseWriter.UIComponentInputValue> columns = new ArrayList<DatabaseWriter.UIComponentInputValue>();
         for(DatabaseWriter.UIComponentInputValue value : keySet) if(value.isDatabaseColum()) columns.add(value);
         return dbWriter.insertValues(hashMap, columns.toArray(new DatabaseWriter.UIComponentInputValue[columns.size()]));
     }

    public boolean updateReviewToDatabase() { return dbWriter.updateValues(hashMap); }

    public boolean reviewExistsInDatabase() { return dbWriter.existsInDatabase(hashMap); }

    public boolean exportReviewToDoc() {
        String fileName = hashMap.get(DatabaseWriter.UIComponentInputValue.ADDRESS) + ", " +
                hashMap.get(DatabaseWriter.UIComponentInputValue.CITY) + ", " +
                hashMap.get(DatabaseWriter.UIComponentInputValue.PROVINCE) + " - " +
                hashMap.get(DatabaseWriter.UIComponentInputValue.DATE) + ".doc";
        FileIO.exportInpsectionReviewToDOC(getApplicationContext(), hashMap, fileName);
        return false;
    }

    public void reset() {
        hashMap.clear(); // = new HashMap<DatabaseWriter.UIComponentInputValue, String>();
        dateActivityComplete = false;
        projectActivityComplete = false;
        concreteActivityComplete = false;
        framingActivityComplete = false;
        conclusionActivityComplete = false;
    }
}

package com.example.nicco.inspectionReviewManager;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Created by Nicco on 2017-07-17.
 */

public class Model extends Application {
    private HashMap<DatabaseWriter.UIComponentInputValue, String> hashMap = new HashMap<DatabaseWriter.UIComponentInputValue, String>();
    private DatabaseWriter dbWriter;

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
        return isChecked(getValue(key));
    }

    public boolean isChecked(String key) {
        return key != null && key.equals(SpecialValue.YES.toString());
    }

    public boolean isValidCheckValue(DatabaseWriter.UIComponentInputValue key) {
        String value = getValue(key);
        return value != null && (value.equals(SpecialValue.YES.toString()) || value.equals(SpecialValue.NO.toString()));
    }

    public String[] queryDatabase(DatabaseWriter.UIComponentInputValue column, String whereClause, String[] whereArgs) {
        return dbWriter.query(column, whereClause, whereArgs);
    }

//    public int monthToInt(String month) {
//        String[] months = new DateFormatSymbols().getMonths();
//        for(int i = 0; i < months.length; i++) {
//            if(month.equals(months[i])) return i;
//        }
//        return -1;
//    }

    public boolean reviewStarted() {
        return !hashMap.isEmpty();
    }

    public String[] combineArrays(String[] arr1, String[] arr2) {
        LinkedHashSet<String> combined = new LinkedHashSet<>();
        combined.addAll(Arrays.asList(arr1));
        combined.addAll(Arrays.asList(arr2));
        return combined.toArray(new String[combined.size()]);
    }

     public boolean insertReviewToDatabase() {
         DatabaseWriter.UIComponentInputValue[] keySet = hashMap.keySet().toArray(
                 new DatabaseWriter.UIComponentInputValue[hashMap.keySet().size()]);
         ArrayList<DatabaseWriter.UIComponentInputValue> columns = new ArrayList<>();
         for(DatabaseWriter.UIComponentInputValue value : keySet) if(value.isDatabaseColum()) columns.add(value);
         return dbWriter.insertValues(hashMap, columns.toArray(new DatabaseWriter.UIComponentInputValue[columns.size()]));
     }

    public boolean updateReviewToDatabase() { return dbWriter.updateValues(hashMap); }

    public boolean reviewExistsInDatabase() { return dbWriter.existsInDatabase(hashMap); }

    public boolean exportReviewToDoc(Context context) {
        String fileName =
                hashMap.get(DatabaseWriter.UIComponentInputValue.PROJECT_NUMBER) + " " +
                "(" + hashMap.get(DatabaseWriter.UIComponentInputValue.ADDRESS) + ", " +
                hashMap.get(DatabaseWriter.UIComponentInputValue.CITY) + ", " +
                hashMap.get(DatabaseWriter.UIComponentInputValue.PROVINCE) + ") ";

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

        // C15 (4295 Quarry Road, Coquitlam, BC) Sheathing and Framing Inspection Report (07242017)
        for(int i = 0; i < reviewTypes.size(); i++) {
            if(i == reviewTypes.size() - 1) fileName += " and " + reviewTypes.get(i) + " Review";
            else if(i > 0) fileName += ", " + reviewTypes.get(i);
            else fileName += reviewTypes.get(i);
        }

        String[] date = hashMap.get(DatabaseWriter.UIComponentInputValue.DATE).split("-"); // YYYY-MM-DD
        if(date.length == 3) fileName += "(" + date[1] + date[2] + date[0] + ").doc"; // MMDDYYYY
        else fileName += "(" + hashMap.get(DatabaseWriter.UIComponentInputValue.DATE) + ").doc"; // YYYY-MM-DD

        FileIO.exportInpsectionReviewToDOC(context, hashMap, fileName);
        return false;
    }

    public Cursor getDatabaseCursor() {
        return dbWriter.getCursor();
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
}

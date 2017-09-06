package com.example.nicco.inspectionReviewManager.customDatatypes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Nicco on 2017-07-25.
 */

public class DatabaseWriter extends SQLiteOpenHelper {
    private String tableCreate;
    private SQLiteDatabase database;
    private final String TABLE_NAME = "Review";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "InspectionReviews.db";
    private static final ArrayList<UIComponentInputValue> PRIMARY_KEYS = new ArrayList<UIComponentInputValue>(
        Arrays.asList(new UIComponentInputValue[]{
                UIComponentInputValue.ADDRESS,
                UIComponentInputValue.CITY,
                UIComponentInputValue.PROVINCE,
                UIComponentInputValue.PROJECT_NUMBER,
                UIComponentInputValue.DATE,
                UIComponentInputValue.TIME}));
    private static final String TABLE_PRIMARY_KEY = createPrimaryKeyStatement();
    private static final int maxXXLargeInputLength = 500;
    private static final int maxXLargeInputLength = 300;
    private static final int maxLargeInputLength = 100;
    private static final int maxMediumInputLength = 50;
    private static final int maxSmallInputLength = 10;

    // used as keys that map directoy to their corresponding:
    // 1. ui component values (in a HashTable within Model)
    // 2. determines if it has a corresponding database columns
    // (*iff isDatabaseColumn() returns true)
    // 3. maps out Reviewed vs N/A radiobutton values
    // 4. acts as value replacement tokens in the exported doc template
    public enum UIComponentInputValue {
        /* UI COMPONENT VALUES */
        REBAR_POSITION_NA("rebar_position_na", "none", null),
        REBAR_SIZE_NA("rebar_size_na", "none", null),
        FORMWORK_NA("formwork_na", "none", null),
        ANCHORAGE_NA("anchorage_na", "none", null),
        TRUSS_SPEC_NA("truss_spec_na", "none", null),
        IJOIST_NA("ijoist_na", "none", null),
        BEARING_NA("bearing_na", "none", null),
        TOP_PLATES_NA("top_plates_na", "none", null),
        LINTELS_NA("lintels_na", "none", null),
        SHEARWALLS_NA("shearwalls_na", "none", null),
        TALL_WALLS_NA("tall_walls_na", "none", null),
        BLOCKING_NA("blocking_na", "none", null),
        WALL_SHEATHING_NA("wall_sheathing_na", "none", null),
        WIND_GIRTS_NA("wind_girts_na", "none", null),
        REVIEW_STATUS_APPROVED("review_status_approved", "none", null),
        REVIEW_STATUS_NOT_APPROVED("review_status_not_approved", "none", null),
        REVIEW_STATUS_REINSPECTION_REQUIRED("review_status_reinspection_required", "none", null),

        /* DATABASE COLUMNS */
        // DATE ACTIVITY
        DATE("date", "DATE", null),
        TIME("time", "TIME", null),
        WEATHER("weather", "VARCHAR(" + maxSmallInputLength + ")", null),
        TEMPERATURE_CELSIUS("temperature_celsius", "VARCHAR(" + maxSmallInputLength + ")", null),
        // PROJECT ACTIVITY
        ADDRESS("address", "VARCHAR(" + maxLargeInputLength + ")", null),
        CITY("city", "VARCHAR(" + maxLargeInputLength + ")", null),
        PROVINCE("province", "VARCHAR(" + maxLargeInputLength + ")", null),
        PROJECT_NUMBER("project_number", "VARCHAR(" + maxMediumInputLength + ")", null),
        DEVELOPER("developer", "VARCHAR(" + maxLargeInputLength + ")", null),
        CONTRACTOR("contractor", "VARCHAR(" + maxLargeInputLength + ")", null),
        FOOTINGS_REVIEW("footings_review", "VARCHAR(" + maxSmallInputLength + ")", null),
        FOUNDATION_WALLS_REVIEW("foundation_walls_review", "VARCHAR(" + maxSmallInputLength + ")", null),
        SHEATHING_REVIEW("sheathing_review", "VARCHAR(" + maxSmallInputLength + ")", null),
        FRAMING_REVIEW("framing_review", "VARCHAR(" + maxSmallInputLength + ")", null),
        OTHER_REVIEW("other_review", "VARCHAR(" + maxSmallInputLength + ")", null),
        OTHER_REVIEW_DESCRIPTION("other_review_description", "VARCHAR(" + maxLargeInputLength + ")", null),
        // CONCRETE ACTIVITY
        REBAR_POSITION("rebar_position", "VARCHAR(" + maxSmallInputLength + ")", REBAR_POSITION_NA),
        REBAR_POSITION_INSTRUCTION("rebar_position_instruction", "VARCHAR(" + maxXLargeInputLength + ")", null),
        REBAR_SIZE("rebar_size", "VARCHAR(" + maxMediumInputLength + ")", REBAR_SIZE_NA),
        REBAR_SIZE_INSTRUCTION("rebar_size_instruction", "VARCHAR(" + maxXLargeInputLength + ")", null),
        FORMWORK("formwork", "VARCHAR(" + maxSmallInputLength + ")", FORMWORK_NA),
        FORMWORK_INSTRUCTION("formwork_instruction", "VARCHAR(" + maxXLargeInputLength + ")", null),
        ANCHORAGE("anchorage", "VARCHAR(" + maxSmallInputLength + ")", ANCHORAGE_NA),
        ANCHORAGE_INSTRUCTION("anchorage_instruction", "VARCHAR(" + maxXLargeInputLength + ")", null),
        // FRAMING ACTIVITY
        TRUSS_SPEC("truss_spec", "VARCHAR(" + maxSmallInputLength + ")", TRUSS_SPEC_NA),
        TRUSS_SPEC_INSTRUCTION("truss_spec_instruction", "VARCHAR(" + maxXLargeInputLength + ")", null),
        IJOIST("ijoist", "VARCHAR(" + maxSmallInputLength + ")", IJOIST_NA),
        IJOIST_INSTRUCTION("ijoist_instruction", "VARCHAR(" + maxXLargeInputLength + ")", null),
        BEARING("bearing", "VARCHAR(" + maxSmallInputLength + ")", BEARING_NA),
        BEARING_INSTRUCTION("bearing_instruction", "VARCHAR(" + maxSmallInputLength + ")", null),
        TOP_PLATES("top_plates", "VARCHAR(" + maxSmallInputLength + ")", TOP_PLATES_NA),
        TOP_PLATES_INSTRUCTION("top_plates_instruction", "VARCHAR(" + maxXLargeInputLength + ")", null),
        LINTELS("lintels", "VARCHAR(" + maxSmallInputLength + ")", LINTELS_NA),
        LINTELS_INSTRUCTION("lintels_instruction", "VARCHAR(" + maxXLargeInputLength + ")", null),
        SHEARWALLS("shearwalls", "VARCHAR(" + maxLargeInputLength + ")", SHEARWALLS_NA),
        SHEARWALLS_INSTRUCTION("shearwalls_instruction", "VARCHAR(" + maxXLargeInputLength + ")", null),
        TALL_WALLS("tall_walls", "VARCHAR(" + maxSmallInputLength + ")", TALL_WALLS_NA),
        TALL_WALLS_INSTRUCTION("tall_walls_instruction", "VARCHAR(" + maxXLargeInputLength + ")", null),
        BLOCKING("blocking", "VARCHAR(" + maxSmallInputLength + ")", BLOCKING_NA),
        BLOCKING_INSTRUCTION("blocking_instruction", "VARCHAR(" + maxXLargeInputLength + ")", null),
        WALL_SHEATHING("wall_sheathing", "VARCHAR(" + maxSmallInputLength + ")", WALL_SHEATHING_NA),
        WALL_SHEATHING_INSTRUCTION("wall_sheathing_instruction", "VARCHAR(" + maxXLargeInputLength + ")", null),
        WIND_GIRTS("wind_girts", "VARCHAR(" + maxSmallInputLength + ")", WIND_GIRTS_NA),
        WIND_GIRTS_INSTRUCTION("wind_girts_instruction", "VARCHAR(" + maxXLargeInputLength + ")", null),
        // CONCLUSION ACTIVITY
        OBSERVATIONS("observations", "VARCHAR(" + maxXXLargeInputLength + ")", null),
        COMMENTS("comments", "VARCHAR(" + maxXXLargeInputLength + ")", null),
        REVIEW_STATUS("review_status", "VARCHAR(" + maxMediumInputLength + ")", null),
        REVIEWED_BY("reviewed_by", "VARCHAR(" + maxMediumInputLength + ")", null),
        STAMPED("stamped", "VARCHAR(" + maxSmallInputLength + ")", null);

        private String value;
        private String dataType;
        private UIComponentInputValue correspondingRow;
        private boolean isADatabaseColumn;

        UIComponentInputValue(String value, String dataType, UIComponentInputValue correspondingRow) {
            this.value = value;
            this.dataType = dataType;
            this.correspondingRow = correspondingRow;
        }

        public String getValue() { return value; }
        public String getDataType() { return dataType; }
        public UIComponentInputValue getCorrespondingRow() { return correspondingRow; }
        public boolean hasCorrespondingRow() { return correspondingRow != null; }
        public boolean isDatabaseColum(){ return !dataType.equals("none"); }
        public String getFormattedValue() {
            String frmtValue = "";
            //shearwalls_instruction
            String[] split = value.split("_");
            for(String str : split) {
                String firstLetter = "" + str.charAt(0);
                firstLetter = firstLetter.toUpperCase();
                str = firstLetter + str.substring(1, str.length());

                if(frmtValue.isEmpty()) frmtValue = str;
                else frmtValue += " " + str;
            }
            if(isDatabaseColum()) frmtValue = frmtValue.replace(" Review", "");
            return frmtValue;
        }
        @Override
        public String toString() { return value + " " + dataType; }
    }

    public DatabaseWriter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        try {
            database = getWritableDatabase();
        } catch(Exception e) {
            Log.v("PUCCI", "EXCEPTION: " + e.getMessage());
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database = db;
        tableCreate = generateTableCreateSQL();
        Log.v("PUCCI", "tableCreate = " + tableCreate);
        database.execSQL(tableCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private String generateTableCreateSQL() {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (";
        String[] columns = getDatabaseColumns();
        for(int i = 0; i < columns.length; i++) {
            sql += columns[i];
            if (i < columns.length - 1) sql += ", ";
            else sql += ", ";
        }
        sql += TABLE_PRIMARY_KEY + ")";
        return sql;
    }

    public boolean existsInDatabase(HashMap<UIComponentInputValue, String> hashMap) {
        UIComponentInputValue[] keys = PRIMARY_KEYS.toArray(new UIComponentInputValue[PRIMARY_KEYS.size()]);
        if(keys.length == 0) return false;

        String sql = "";
        String sqlSelect = "SELECT COUNT (" + keys[0].getValue() + ") FROM " + TABLE_NAME;
        String sqlWhere = "WHERE ";
        for(int i = 0; i < keys.length; i++) {
            sqlWhere += keys[i].getValue() + " = " + "\"" + hashMap.get(keys[i]) + "\"";
            if (i < keys.length - 1) sqlWhere += " AND ";
        }
        sql = sqlSelect + " " + sqlWhere;
        int numResults = 0;
        try {
            Cursor cursor = database.rawQuery(sql, null);
            if(cursor != null) {
                cursor.moveToFirst();
                numResults = cursor.getInt(0);
            }
            cursor.close();
        } catch(Exception e) {
            Log.v("PUCCI", "QUERY Exception: " + e.getMessage());
        }
        Log.v("PUCCI", "numResults = " + numResults);
        return numResults > 0;
    }

    public boolean insertValues(HashMap<UIComponentInputValue, String> hashMap,  UIComponentInputValue[] columnSet) {
        String sql = "INSERT INTO " + TABLE_NAME;
        String sqlColumns = "(";
        String sqlValues = "VALUES(";
        for(int i = 0; i < columnSet.length; i++) {
            sqlColumns = sqlColumns + columnSet[i].getValue();
            String data = hashMap.get(columnSet[i]).replace("'", "\'");
            data = data.replace("\"", "\'");
            sqlValues = sqlValues + "\"" + data + "\"";
            if(i < columnSet.length - 1) {
                sqlColumns += ", ";
                sqlValues += ",";
            } else {
                sqlColumns += ")";
                sqlValues += ")";
            }
        }

        sql = sql + sqlColumns + " " + sqlValues;

        Log.v("PUCCI", "INSERT sql = " + sql);
        try {
            database.execSQL(sql);
        } catch(Exception e) {
            Log.v("PUCCI", "EXCEPTION: " + e.getMessage());
            updateValues(hashMap);
            return false;
        }
        return true;
    }

    public boolean updateValues(HashMap<UIComponentInputValue, String> hashMap) {
        String sql = "UPDATE " + TABLE_NAME;
        String sqlSet = "SET ";
        String sqlWhere = "WHERE ";
        UIComponentInputValue[] columnSet = hashMap.keySet().toArray(new UIComponentInputValue[hashMap.keySet().size()]);
        int countPrimaryKeys = 0;
        for(int i = 0; i < columnSet.length; i++) {
            if(!columnSet[i].isDatabaseColum()) {
                // do nothing
            } else if (PRIMARY_KEYS.contains(columnSet[i])) {
                countPrimaryKeys++;
                sqlWhere += columnSet[i].getValue() + " = " + "\"" + hashMap.get(columnSet[i]) + "\"";
                if(countPrimaryKeys != PRIMARY_KEYS.size()) sqlWhere += " AND ";
            } else {
                sqlSet += columnSet[i].getValue() + " = " + "\"" + hashMap.get(columnSet[i]) + "\"";
                sqlSet += ", ";
            }
        }
        sqlSet = sqlSet.substring(0, sqlSet.length() - 2) + " ";
        sql += " " + sqlSet + " " + sqlWhere;
        Log.v("PUCCI", "UPDATE sql = " + sql);
        try {
            database.execSQL(sql);
        } catch(Exception e) {
            Log.v("PUCCI", "EXCEPTION: " + e.getMessage());
            return false;
        }
        return true;
    }

    public Cursor getCursor() {
        Cursor cursor = null;
        try {
            String query = "SELECT " + UIComponentInputValue.WEATHER.getValue() + " AS _id, " +
                    UIComponentInputValue.DATE.getValue() + ", " +
                    UIComponentInputValue.TIME.getValue() + ", " +
                    UIComponentInputValue.ADDRESS.getValue() + ", " +
                    UIComponentInputValue.CITY.getValue() + ", " +
                    UIComponentInputValue.PROVINCE.getValue() + ", " +
                    UIComponentInputValue.PROJECT_NUMBER.getValue() + ", " +
                    UIComponentInputValue.FOOTINGS_REVIEW.getValue() + ", " +
                    UIComponentInputValue.FOUNDATION_WALLS_REVIEW.getValue() + ", " +
                    UIComponentInputValue.SHEATHING_REVIEW.getValue() + ", " +
                    UIComponentInputValue.FRAMING_REVIEW.getValue() + ", " +
                    UIComponentInputValue.OTHER_REVIEW.getValue() + ", " +
                    UIComponentInputValue.REVIEW_STATUS.getValue() + ", " +
                    UIComponentInputValue.REVIEWED_BY.getValue() + ", " +
                    UIComponentInputValue.STAMPED.getValue() +
                    " FROM " + TABLE_NAME +
                    " ORDER BY " +
                    "DATE(" + UIComponentInputValue.DATE.getValue() + ")" + " DESC, " +
                    "TIME(" + UIComponentInputValue.TIME.getValue() + ")" + " DESC, " +
                    UIComponentInputValue.CITY.getValue() + " ASC";
            cursor = database.rawQuery(query, null);
        } catch(Exception e) {
            Log.v("PUCCI", "QUERY Exception: " + e.getMessage());
        }
        return cursor;
    }

    public String[] query(UIComponentInputValue column, String whereClause, String[] whereArgs) {
       return query(column.getValue(), whereClause, whereArgs);
    }

    private String[] query(String column, String whereClause, String[] whereArgs) {
        ArrayList<String> results = new ArrayList<>();
        String[] projection = new String[]{"DISTINCT(" + column + ")"};

        try {
            Cursor cursor = database.query(TABLE_NAME, projection, whereClause, whereArgs, null, null, null);

            if(cursor != null) {
                while (cursor.moveToNext()) {
                    String data = cursor.getString(0);
                    Log.v("PUCCI", "QUERY data =  " + data);
                    if(data != null) results.add(data);
                }
            }
            cursor.close();
        } catch(Exception e) {
            Log.v("PUCCI", "QUERY Exception: " + e.getMessage());
        }
        return results.toArray(new String[results.size()]);
    }

    public HashMap<UIComponentInputValue, String> loadReview(String[] columns, String whereClause, String[] whereArgs) {
        HashMap<UIComponentInputValue, String> results = new HashMap();

        try {
            Cursor cursor = database.query(TABLE_NAME, columns, whereClause, whereArgs, null, null, null);
            if(cursor != null) {
                cursor.moveToFirst();
                for (UIComponentInputValue column : UIComponentInputValue.values()) {
                    if(column.isDatabaseColum()) {
                        int index = cursor.getColumnIndex(column.getValue());
                        if(index > -1) {
                            String data = cursor.getString(index);
                            if (column != null && data != null) results.put(column, data);
                            if(column != null && data != null && column.hasCorrespondingRow()) {
                                if(data.equals(Model.SpecialValue.YES.toString())) results.put(column.getCorrespondingRow(), Model.SpecialValue.NO.toString());
                                else if(data.equals(Model.SpecialValue.NO.toString())) results.put(column.getCorrespondingRow(), Model.SpecialValue.YES.toString());
                            } else if(column.equals(UIComponentInputValue.REVIEW_STATUS)) {
                                if(data.equals(Model.ReviewStatusValue.APPROVED.toString())) {
                                    results.put(UIComponentInputValue.REVIEW_STATUS_APPROVED, Model.SpecialValue.YES.toString());
                                    results.put(UIComponentInputValue.REVIEW_STATUS_NOT_APPROVED, Model.SpecialValue.NO.toString());
                                    results.put(UIComponentInputValue.REVIEW_STATUS_REINSPECTION_REQUIRED, Model.SpecialValue.NO.toString());
                                } else if(data.equals(Model.ReviewStatusValue.NOT_APPROVED.toString())) {
                                    results.put(UIComponentInputValue.REVIEW_STATUS_APPROVED, Model.SpecialValue.NO.toString());
                                    results.put(UIComponentInputValue.REVIEW_STATUS_NOT_APPROVED, Model.SpecialValue.YES.toString());
                                    results.put(UIComponentInputValue.REVIEW_STATUS_REINSPECTION_REQUIRED, Model.SpecialValue.NO.toString());
                                } else if(data.equals(Model.ReviewStatusValue.REINSPECTION_REQUIRED.toString())) {
                                    results.put(UIComponentInputValue.REVIEW_STATUS_APPROVED, Model.SpecialValue.NO.toString());
                                    results.put(UIComponentInputValue.REVIEW_STATUS_NOT_APPROVED, Model.SpecialValue.NO.toString());
                                    results.put(UIComponentInputValue.REVIEW_STATUS_REINSPECTION_REQUIRED, Model.SpecialValue.YES.toString());
                                }
                            }
                        }
                    }
                }
            }
        } catch(Exception e) {
            Log.v("PUCCI", "QUERY Exception: " + e.getMessage());
        }
        return results;
    }

    public void deleteReview(String[] columns, String whereClause, String[] whereArgs) {
        database.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public String getDatabasePath() {
        return database.getPath();
    }

    private static String createPrimaryKeyStatement() {
        String primaryKey = "PRIMARY KEY (";
        for(int i = 0; i < PRIMARY_KEYS.size(); i++) {
            primaryKey += PRIMARY_KEYS.get(i).getValue();
            if (i < PRIMARY_KEYS.size() - 1) primaryKey += ", ";
            else primaryKey += ")";
        }
        return primaryKey;
    }

    public static String[] getDatabaseColumns() {
        ArrayList<String> columnList = new ArrayList<String>();
        for(UIComponentInputValue column : UIComponentInputValue.values()) {
            if(column.isDatabaseColum()) columnList.add(column.getValue());
        }
        return columnList.toArray(new String[columnList.size()]);
    }

    public static UIComponentInputValue getUIComponentInputValue(String s) {
        for(UIComponentInputValue uiComponentInputValue : UIComponentInputValue.values()) {
            if (uiComponentInputValue.getValue().equals(s)) return uiComponentInputValue;
        }
        return null;
    }
}

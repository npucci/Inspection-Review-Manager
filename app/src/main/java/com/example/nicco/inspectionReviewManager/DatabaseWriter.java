package com.example.nicco.inspectionReviewManager;

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
    private static String tableCreate;
    private SQLiteDatabase database;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "InspectionReviews.db";
    private static final String TABLE_NAME = "Review";
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
    // 2. database columns *iff isDatabaseColumn() returns true
    // and 3. tokens in the exported doc template
    public enum UIComponentInputValue {
        /* UI COMPONENT VALUES */
        REBAR_POSITION_NA("rebar_position_na", "none", false),
        REBAR_SIZE_NA("rebar_size_na", "none", false),
        FORMWORK_NA("formwork_na", "none", false),
        ANCHORAGE_NA("anchorage_na", "none", false),
        TRUSS_SPEC_NA("truss_spec_na", "none", false),
        IJOIST_NA("ijoist_na", "none", false),
        BEARING_NA("bearing_na", "none", false),
        TOP_PLATES_NA("top_plates_na", "Vnone", false),
        LINTELS_NA("lintels_na", "none", false),
        SHEARWALLS_NA("shearwalls_na", "none", false),
        TALL_WALLS_NA("tall_walls_na", "none", false),
        BLOCKING_NA("blocking_na", "none", false),
        WALL_SHEATHING_NA("wall_sheathing_na", "none", false),
        WIND_GIRTS_NA("wind_girts_na", "none", false),
        REVIEW_STATUS_APPROVED("review_status_approved", "none", false),
        REVIEW_STATUS_NOT_APPROVED("review_status_not_approved", "none", false),
        REVIEW_STATUS_REINSPECTION_REQUIRED("review_status_reinspection_required", "none", false),

        /* DATABASE COLUMNS */
        // DATE ACTIVITY
        DATE("date", "DATE", true),
        TIME("time", "TIME", true),
        WEATHER("weather", "VARCHAR(" + maxSmallInputLength + ")", true),
        TEMPERATURE_CELSIUS("temperature_celsius", "VARCHAR(" + maxSmallInputLength + ")", true),
        // PROJECT ACTIVITY
        ADDRESS("address", "VARCHAR(" + maxLargeInputLength + ")", true),
        CITY("city", "VARCHAR(" + maxLargeInputLength + ")", true),
        PROVINCE("province", "VARCHAR(" + maxLargeInputLength + ")", true),
        PROJECT_NUMBER("project_number", "VARCHAR(" + maxMediumInputLength + ")", true),
        DEVELOPER("developer", "VARCHAR(" + maxLargeInputLength + ")", true),
        CONTRACTOR("contractor", "VARCHAR(" + maxLargeInputLength + ")", true),
        FOOTINGS_REVIEW("footings_review", "VARCHAR(" + maxSmallInputLength + ")", true),
        FOUNDATION_WALLS_REVIEW("foundation_walls_review", "VARCHAR(" + maxSmallInputLength + ")", true),
        SHEATHING_REVIEW("sheathing_review", "VARCHAR(" + maxSmallInputLength + ")", true),
        FRAMING_REVIEW("framing_review", "VARCHAR(" + maxSmallInputLength + ")", true),
        OTHER_REVIEW("other_review", "VARCHAR(" + maxSmallInputLength + ")", true),
        OTHER_REVIEW_DESCRIPTION("other_review_description", "VARCHAR(" + maxLargeInputLength + ")", true),
        // CONCRETE ACTIVITY
        REBAR_POSITION("rebar_position", "VARCHAR(" + maxSmallInputLength + ")", true),
        REBAR_POSITION_INSTRUCTION("rebar_position_instruction", "VARCHAR(" + maxXLargeInputLength + ")", true),
        REBAR_SIZE("rebar_size", "VARCHAR(" + maxMediumInputLength + ")", true),
        REBAR_SIZE_INSTRUCTION("rebar_size_instruction", "VARCHAR(" + maxXLargeInputLength + ")", true),
        FORMWORK("formwork", "VARCHAR(" + maxSmallInputLength + ")", true),
        FORMWORK_INSTRUCTION("formwork_instruction", "VARCHAR(" + maxXLargeInputLength + ")", true),
        ANCHORAGE("anchorage", "VARCHAR(" + maxSmallInputLength + ")", true),
        ANCHORAGE_INSTRUCTION("anchorage_instruction", "VARCHAR(" + maxXLargeInputLength + ")", true),
        // FRAMING ACTIVITY
        TRUSS_SPEC("truss_spec", "VARCHAR(" + maxSmallInputLength + ")", true),
        TRUSS_SPEC_INSTRUCTION("truss_spec_instruction", "VARCHAR(" + maxXLargeInputLength + ")", true),
        IJOIST("ijoist", "VARCHAR(" + maxSmallInputLength + ")", true),
        IJOIST_INSTRUCTION("ijoist_instruction", "VARCHAR(" + maxXLargeInputLength + ")", true),
        BEARING("bearing", "VARCHAR(" + maxSmallInputLength + ")", true),
        BEARING_INSTRUCTION("bearing_instruction", "VARCHAR(" + maxSmallInputLength + ")", true),
        TOP_PLATES("top_plates", "VARCHAR(" + maxSmallInputLength + ")", true),
        TOP_PLATES_INSTRUCTION("top_plates_instruction", "VARCHAR(" + maxXLargeInputLength + ")", true),
        LINTELS("lintels", "VARCHAR(" + maxSmallInputLength + ")", true),
        LINTELS_INSTRUCTION("lintels_instruction", "VARCHAR(" + maxXLargeInputLength + ")", true),
        SHEARWALLS("shearwalls", "VARCHAR(" + maxLargeInputLength + ")", true),
        SHEARWALLS_INSTRUCTION("shearwalls_instruction", "VARCHAR(" + maxXLargeInputLength + ")", true),
        TALL_WALLS("tall_walls", "VARCHAR(" + maxSmallInputLength + ")", true),
        TALL_WALLS_INSTRUCTION("tall_walls_instruction", "VARCHAR(" + maxXLargeInputLength + ")", true),
        BLOCKING("blocking", "VARCHAR(" + maxSmallInputLength + ")", true),
        BLOCKING_INSTRUCTION("blocking_instruction", "VARCHAR(" + maxXLargeInputLength + ")", true),
        WALL_SHEATHING("wall_sheathing", "VARCHAR(" + maxSmallInputLength + ")", true),
        WALL_SHEATHING_INSTRUCTION("wall_sheathing_instruction", "VARCHAR(" + maxXLargeInputLength + ")", true),
        WIND_GIRTS("wind_girts", "VARCHAR(" + maxSmallInputLength + ")", true),
        WIND_GIRTS_INSTRUCTION("wind_girts_instruction", "VARCHAR(" + maxXLargeInputLength + ")", true),
        // CONCLUSION ACTIVITY
        OBSERVATIONS("observations", "VARCHAR(" + maxXXLargeInputLength + ")", true),
        COMMENTS("comments", "VARCHAR(" + maxXXLargeInputLength + ")", true),
        REVIEW_STATUS("review_status", "VARCHAR(" + maxMediumInputLength + ")", true),
        REVIEWED_BY("reviewed_by", "VARCHAR(" + maxMediumInputLength + ")", true);

        private String value;
        private String dataType;
        private boolean isADatabaseColumn;

        UIComponentInputValue(String value, String dataType, boolean isADatabaseColumn) {
            this.value = value;
            this.dataType = dataType;
            this.isADatabaseColumn = isADatabaseColumn;
        }

        public String getValue() { return value; }
        public String getDataType() { return dataType; }
        public boolean isDatabaseColum(){ return isADatabaseColumn; }

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
        Log.v("PUCCI", "Constructor CALLED");
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

    private static String generateTableCreateSQL() {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (";
        UIComponentInputValue[] columns = UIComponentInputValue.values();
        for(int i = 0; i < columns.length; i++) {
            sql += columns[i].toString();
            if(i < columns.length - 1) sql += ", ";
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
            sqlValues = sqlValues + "\"" + hashMap.get(columnSet[i]) + "\"";
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
            if (PRIMARY_KEYS.contains(columnSet[i])) {
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

    public String[] query(UIComponentInputValue column, String whereClause, String[] whereArgs) {
        ArrayList<String> results = new ArrayList<>();
        String[] projection = new String[]{"DISTINCT(" + column.getValue() + ")"};

        try {
            Cursor cursor = database.query(TABLE_NAME, projection, whereClause, whereArgs, null, null, null);

            if(cursor != null) {
                while (cursor.moveToNext()) {
                    String data = cursor.getString(0);
                    Log.v("PUCCI", "QUERY data =  " + data);
                    results.add(data);
                }
            }
        } catch(Exception e) {
            Log.v("PUCCI", "QUERY Exception: " + e.getMessage());
        }
        return results.toArray(new String[results.size()]);
    }

    public String[] query(String column, String whereClause, String[] whereArgs) {
        ArrayList<String> results = new ArrayList<>();
        String[] projection = new String[]{"DISTINCT(" + column + ")"};

        try {
            Cursor cursor = database.query(TABLE_NAME, projection, whereClause, whereArgs, null, null, null);

            if(cursor != null) {
                while (cursor.moveToNext()) {
                    String data = cursor.getString(0);
                    Log.v("PUCCI", "QUERY data =  " + data);
                    results.add(data);
                }
            }
        } catch(Exception e) {
            Log.v("PUCCI", "QUERY Exception: " + e.getMessage());
        }
        return results.toArray(new String[results.size()]);
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

}

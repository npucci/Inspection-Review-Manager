package com.example.nicco.inspectionReviewManager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Nicco on 2017-07-25.
 */

public class DatabaseWriter extends SQLiteOpenHelper {
    private static String tableCreate;
    private SQLiteDatabase database;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "InspectionReviews.db";
    private static final String TABLE_NAME = "Review";
    private static final String TABLE_PRIMARY_KEY = "PRIMARY KEY (" +
            DatabaseColumn.ADDRESS.getValue() + ", " +
            DatabaseColumn.CITY.getValue() + ", " +
            DatabaseColumn.PROVINCE.getValue() + ", " +
            DatabaseColumn.PROJECT_NUMBER.getValue() + ", " +
            DatabaseColumn.DATE.getValue() + ", " +
            DatabaseColumn.TIME.getValue() + ")";
    private static final int maxXXLargeInputLength = 500;
    private static final int maxXLargeInputLength = 300;
    private static final int maxLargeInputLength = 100;
    private static final int maxMediumInputLength = 50;
    private static final int maxSmallInputLength = 10;

    public enum DatabaseColumn {
        // DATE ACTIVITY
        DATE("date", "DATE"),
        TIME("time", "TIME"),
        WEATHER("weather", "VARCHAR(" + maxSmallInputLength + ")"),
        TEMPERATURE_CELSIUS("temperature_celsius", "REAL"),
        // PROJECT ACTIVITY
        ADDRESS("address", "VARCHAR(" + maxLargeInputLength + ")"),
        CITY("city", "VARCHAR(" + maxLargeInputLength + ")"),
        PROVINCE("province", "VARCHAR(" + maxLargeInputLength + ")"),
        PROJECT_NUMBER("project_number", "VARCHAR(" + maxMediumInputLength + ")"),
        DEVELOPER("developer", "VARCHAR(" + maxLargeInputLength + ")"),
        CONTRACTOR("contractor", "VARCHAR(" + maxLargeInputLength + ")"),
        FOOTINGS_REVIEW("footings_review", "VARCHAR(" + maxSmallInputLength + ")"),
        FOUNDATION_WALLS_REVIEW("foundation_walls_review", "VARCHAR(" + maxSmallInputLength + ")"),
        SHEATHING_REVIEW("sheathing_review", "VARCHAR(" + maxSmallInputLength + ")"),
        FRAMING_REVIEW("framing_review", "VARCHAR(" + maxSmallInputLength + ")"),
        OTHER_REVIEW("other_review", "VARCHAR(" + maxSmallInputLength + ")"),
        OTHER_REVIEW_DESCRIPTION("other_review_description", "VARCHAR(" + maxLargeInputLength + ")"),
        // CONCRETE ACTIVITY
        REBAR_POSITION("rebar_position", "VARCHAR(" + maxSmallInputLength + ")"),
        REBAR_POSITION_INSTRUCTION("rebar_instruction", "VARCHAR(" + maxXLargeInputLength + ")"),
        REBAR_SIZE("rebar_size", "VARCHAR(" + maxMediumInputLength + ")"),
        REBAR_SIZE_INSTRUCTION("rebar_size_instruction", "VARCHAR(" + maxXLargeInputLength + ")"),
        FORMWORK("formwork", "VARCHAR(" + maxSmallInputLength + ")"),
        FORMWORK_INSTRUCTION("formwork_instruction", "VARCHAR(" + maxXLargeInputLength + ")"),
        ANCHORAGE("anchorage", "VARCHAR(" + maxSmallInputLength + ")"),
        ANCHORAGE_INSTRUCTION("anchorage_instruction", "VARCHAR(" + maxXLargeInputLength + ")"),
        // FRAMING ACTIVITY
        TRUSS_SPEC("truss_spec", "VARCHAR(" + maxSmallInputLength + ")"),
        TRUSS_SPEC_INSTRUCTION("truss_spec_instruction", "VARCHAR(" + maxXLargeInputLength + ")"),
        IJOIST("ijoist", "VARCHAR(" + maxSmallInputLength + ")"),
        IJOIST_INSTRUCTION("ijoist_instruction", "VARCHAR(" + maxXLargeInputLength + ")"),
        BEARING("bearing", "VARCHAR(" + maxSmallInputLength + ")"),
        BEARING_INSTRUCTION("bearing_instruction", "VARCHAR(" + maxSmallInputLength + ")"),
        TOP_PLATES("top_plates", "VARCHAR(" + maxSmallInputLength + ")"),
        TOP_PLATES_INSTRUCTION("top_plates_instruction", "VARCHAR(" + maxXLargeInputLength + ")"),
        LINTELS("lintels", "VARCHAR(" + maxSmallInputLength + ")"),
        LINTELS_INSTRUCTION("lintels_instruction", "VARCHAR(" + maxXLargeInputLength + ")"),
        SHEARWALLS("shearwalls", "VARCHAR(" + maxLargeInputLength + ")"),
        SHEARWALLS_INSTRUCTION("shearwalls_instruction", "VARCHAR(" + maxXLargeInputLength + ")"),
        TALL_WALLS("tall_walls", "VARCHAR(" + maxSmallInputLength + ")"),
        TALL_WALLS_INSTRUCTION("tall_walls_instruction", "VARCHAR(" + maxXLargeInputLength + ")"),
        BLOCKING("blocking", "VARCHAR(" + maxSmallInputLength + ")"),
        BLOCKING_INSTRUCTION("blocking_instruction", "VARCHAR(" + maxXLargeInputLength + ")"),
        WALL_SHEATHING("wall_sheathing", "VARCHAR(" + maxSmallInputLength + ")"),
        WALL_SHEATHING_INSTRUCTION("wall_sheathing_instruction", "VARCHAR(" + maxXLargeInputLength + ")"),
        WIND_GIRTS("wind_girts", "VARCHAR(" + maxSmallInputLength + ")"),
        WIND_GIRTS_INSTRUCTION("wind_girts_instruction", "VARCHAR(" + maxXLargeInputLength + ")"),
        // CONCLUSION ACTIVITY
        OBSERVATIONS("observation", "VARCHAR(" + maxXXLargeInputLength + ")"),
        COMMENTS("comments", "VARCHAR(" + maxXXLargeInputLength + ")"),
        REVIEW_STATUS("review_status", "VARCHAR(" + maxMediumInputLength + ")"),
        REVIEWED_BY("reviewed_by", "VARCHAR(" + maxMediumInputLength + ")");

        private String value;
        private String dataType;

        DatabaseColumn(String value, String dataType) {
            this.value = value;
            this.dataType = dataType;
        }

        public String getValue() { return value; }
        public String getDataType() { return dataType; }

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
        DatabaseColumn[] columns = DatabaseColumn.values();
        for(int i = 0; i < columns.length; i++) {
            sql += columns[i].toString();
            if(i < columns.length - 1) sql += ", ";
            else sql += ", ";
        }
        sql += TABLE_PRIMARY_KEY + ")";
        return sql;
    }

    public void insertValues(HashMap<DatabaseColumn, String> hashMap) {
        String sql = "INSERT INTO " + TABLE_NAME;
        String sqlColumns = "(";
        String sqlValues = "VALUES(";
        DatabaseColumn[] columnSet = hashMap.keySet().toArray(new DatabaseColumn[hashMap.keySet().size()]);
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
        }
    }

    public String[] query(DatabaseColumn column, String whereClause, String[] whereArgs) {
        ArrayList<String> results = new ArrayList<>();
        String[] projection = new String[]{column.getValue()};

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

}

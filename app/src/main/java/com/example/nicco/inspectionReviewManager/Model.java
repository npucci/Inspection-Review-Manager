package com.example.nicco.inspectionReviewManager;

import android.app.Application;
import android.graphics.Color;

import java.util.HashMap;

/**
 * Created by Nicco on 2017-07-17.
 */

public class Model extends Application {
    private HashMap<Keys, String> hashMap = new HashMap<Keys, String>();
    public enum Keys {
        // DATE
        YEAR,
        MONTH,
        DAY,
        HOUR,
        MINUTE,
        TIME_PERIOD,
        WEATHER,
        TEMPERATURE,

        // PROJECT
        ADDRESS,
        PROJECT_NUMBER,
        CITY_PROV,
        DEVELOPER,
        CONTRACTOR,
        FOOTINGS,
        FOUNDATION_WALLS,
        SHEATHING,
        FRAMING,
        OTHER,
        DESCRIPTION,

        // CONCRETE
        REBAR_POSITION_REVIEWED,
        REBAR_POSITION_NA,
        REBAR_POSITION_INSTRUCTION,
        REBAR_SIZE_REVIEWED,
        REBAR_SIZE_NA,
        REBAR_SIZE_INSTRUCTION,
        ANCHORAGE_REVIEWED,
        ANCHORAGE_NA,
        ANCHORAGE_INSTRUCTION,
        FORMWORK_REVIEWED,
        FORMWORK_NA,
        FORMWORK_INSTRUCTION,

        // FRAMING
        TRUSS_SPEC_REVIEWED,
        TRUSS_SPEC_NA,
        TRUSS_SPEC_INSTRUCTION,
        IJOIST_REVIEWED,
        IJOIST_NA,
        IJOIST_INSTRUCTION,
        BEARING_REVIEWED,
        BEARING_NA,
        BEARING_INSTRUCTION,
        TOP_PLATES_REVIEWED,
        TOP_PLATES_NA,
        TOP_PLATES_INSTRUCTION,
        LINTELS_REVIEWED,
        LINTELS_NA,
        LINTELS_INSTRUCTION,
        SHEARWALLS_REVIEWED,
        SHEARWALLS_NA,
        SHEARWALLS_INSTRUCTION,
        TALLWALLS_REVIEWED,
        TALLWALLS_NA,
        TALLWALLS_INSTRUCTION,
        BLOCKING_REVIEWED,
        BLOCKING_NA,
        BLOCKING_INSTRUCTION,
        WALLSHEATHING_REVIEWED,
        WALLSHEATHING_NA,
        WALLSHEATHING_INSTRUCTION,
        WINDGIRTS_REVIEWED,
        WINDGIRTS_NA,
        WINDGIRTS_INSTRUCTION,

        // REVIEW CONCLUSION
        OBSERVATIONS,
        COMMENTS,
        REVIEW_STATUS,
        REVIEWED_BY;
    }

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

    public void updateValue(Keys key, String value) { hashMap.put(key, value); }

    public String getValue(Keys key) {
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

    public void checkDateActivityStatus() {}

    public void checkProjectActivityStatus() {
        projectActivityComplete = false;

        if(!validValue(hashMap.get(Keys.ADDRESS))) return;
        else if(!validValue(hashMap.get(Keys.CITY_PROV))) return;
        else if(!validValue(hashMap.get(Keys.PROJECT_NUMBER))) return;
        else if(!validValue(hashMap.get(Keys.DEVELOPER))) return;
        else if(!validValue(hashMap.get(Keys.CONTRACTOR)))return;
        else if (isChecked(Keys.FOOTINGS)) projectActivityComplete = true;
        else if (isChecked(Keys.FOUNDATION_WALLS)) projectActivityComplete = true;
        else if (isChecked(Keys.SHEATHING)) projectActivityComplete = true;
        else if (isChecked(Keys.FRAMING)) projectActivityComplete = true;
        else if(isChecked(Keys.OTHER)) projectActivityComplete = validValue(hashMap.get(Keys.DESCRIPTION));
    }

    public void checkConcreteActivityStatus() {
        concreteActivityComplete = false;

        if(!isChecked(Keys.REBAR_POSITION_REVIEWED) &&
                !isChecked(Keys.REBAR_POSITION_NA)) return;
        else if(!isChecked(Keys.REBAR_SIZE_REVIEWED) &&
                !isChecked(Keys.REBAR_SIZE_NA)) return;
        else if(!isChecked(Keys.ANCHORAGE_REVIEWED) &&
                !isChecked(Keys.ANCHORAGE_NA)) return;
        else if(!isChecked(Keys.FORMWORK_REVIEWED) &&
                !isChecked(Keys.FORMWORK_NA)) return;

        concreteActivityComplete = true;
    }

    public void checkFramingActivityStatus() {
        framingActivityComplete = false;

        if(!isChecked(Keys.TRUSS_SPEC_REVIEWED) &&
                !isChecked(Keys.TRUSS_SPEC_NA)) return;
        else if(!isChecked(Keys.IJOIST_REVIEWED) &&
                !isChecked(Keys.IJOIST_NA)) return;
        else if(!isChecked(Keys.BEARING_REVIEWED) &&
                !isChecked(Keys.BEARING_NA)) return;
        else if(!isChecked(Keys.TOP_PLATES_REVIEWED) &&
                !isChecked(Keys.TOP_PLATES_NA)) return;
        else if(!isChecked(Keys.LINTELS_REVIEWED) &&
                !isChecked(Keys.LINTELS_NA)) return;
        else if(!isChecked(Keys.SHEARWALLS_REVIEWED) &&
                !isChecked(Keys.SHEARWALLS_NA)) return;
        else if(!isChecked(Keys.TALLWALLS_REVIEWED) &&
                !isChecked(Keys.TALLWALLS_NA)) return;
        else if(!isChecked(Keys.BLOCKING_REVIEWED) &&
                !isChecked(Keys.BLOCKING_NA)) return;
        else if(!isChecked(Keys.WALLSHEATHING_REVIEWED) &&
                !isChecked(Keys.WALLSHEATHING_NA)) return;
        else if(!isChecked(Keys.WINDGIRTS_REVIEWED) &&
                !isChecked(Keys.WINDGIRTS_NA)) return;

        framingActivityComplete = true;
    }

    public boolean validValue(String value) { return value != null && !value.isEmpty(); }

    public boolean isChecked(Keys key) {
        String value = getValue(key);
        return value != null && value.equals(SpecialValue.YES.toString());
    }

    public void checkConclusionActivityStatus() {}

}

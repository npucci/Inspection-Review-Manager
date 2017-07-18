package com.example.nicco.inspectionReviewManager;

import android.app.Application;
import android.util.Log;

/**
 * Created by Nicco on 2017-07-17.
 */

public class Model extends Application {
    // inputs
    private String address = "";
    private String projectNumber = "";
    private String cityProv = "";
    private String year = "";
    private String month = "";
    private String day = "";
    private String hour = "";
    private String minute = "";
    private String timePeriod = "";
    private String developer = "";
    private String contractor = "";
    private String weather = "";
    private String temperature = "";
    // TYPE OF REVIEW:
    private String footings = "";
    private String foundationWalls = "";
    private String sheathing = "";
    private String framing = "";
    private String other = "";
    private String otherInput = "";
    // CONCRETE:
    private String rebarPositionReviewed = "";
    private String rebarPositionNA = "";
    private String rebarPositionInstruction = "";
    private String rebarSizeReviewed = "";
    private String rebarSizeNA = "";
    private String rebarSizeInstruction = "";
    private String anchorageReviewed = "";
    private String anchorageNA = "";
    private String anchorageInstruction = "";
    private String formworkReviewed = "";
    private String formworkNA = "";
    private String formworkInstruction = "";
    // FRAMING:
    private String trussSpec = "";
    private String trussSpecInstruction = "";
    private String ljoist = "";
    private String ljoistInstruction = "";
    private String bearing = "";
    private String bearingInstruction = "";
    private String topPlates = "";
    private String topPlatesInstruction = "";
    private String openings = "";
    private String openingsInstruction = "";
    private String fastening = "";
    private String fasteningInstruction = "";
    private String tallWalls = "";
    private String tallWallsInstruction = "";
    private String blocking = "";
    private String blockingInstruction = "";
    private String wallSheathing = "";
    private String wallSheathingInstruction = "";
    private String windGirts = "";
    private String windGirtsInstruction = "";
    // REVIEW CONCLUSION:
    private String observations = "";
    private String comments = "";
    private String reportGrading = "";
    private String inspectorName = "";

    public Model() {}

    public void reset() {
        address = "";
        projectNumber = "";
        cityProv = "";
        year = "";
        month = "";
        day = "";
        hour = "";
        minute = "";
        timePeriod = "";
        developer = "";
        contractor = "";
        weather = "";
        temperature = "";
        footings = "";
        foundationWalls = "";
        sheathing = "";
        framing = "";
        other = "";
        otherInput = "";
        // CONCRETE:
        rebarPositionReviewed = "";
        rebarPositionNA = "";
        rebarPositionInstruction = "";
        rebarSizeReviewed = "";
        rebarSizeNA = "";
        rebarSizeInstruction = "";
        anchorageReviewed = "";
        anchorageNA = "";
        anchorageInstruction = "";
        formworkReviewed = "";
        formworkNA = "";
        formworkInstruction = "";
        // FRAMING:
        trussSpec = "";
        trussSpecInstruction = "";
        ljoist = "";
        ljoistInstruction = "";
        bearing = "";
        bearingInstruction = "";
        topPlates = "";
        topPlatesInstruction = "";
        openings = "";
        openingsInstruction = "";
        fastening = "";
        fasteningInstruction = "";
        tallWalls = "";
        tallWallsInstruction = "";
        blocking = "";
        blockingInstruction = "";
        wallSheathing = "";
        wallSheathingInstruction = "";
        windGirts = "";
        windGirtsInstruction = "";
        observations = "";
        comments = "";
        reportGrading = "";
    }

    // PROJECT ACTIVITY - ADDRESS
    public void updateAddress(String address) {
        Log.v("PUCCI", "address = " + address);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    // PROJECT ACTIVITY - PROJECT NUMBER
    public void updateProjectNumber(String projectNumber) {
        Log.v("PUCCI", "projectNumber = " + projectNumber);
        this.projectNumber = projectNumber;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    // PROJECT ACTIVITY - CITY/PROV
    public void updateCityProv(String cityProv) {
        Log.v("PUCCI", "cityProv = " + cityProv);
        this.cityProv = cityProv;
    }

    public String getCityProv() {
        return cityProv;
    }

    // PROJECT ACTIVITY - DEVELOPER
    public void updateDeveloper(String developer) {
        Log.v("PUCCI", "developer = " + developer);
        this.developer = developer;
    }

    public String getDeveloper() { return developer; }

    // PROJECT ACTIVITY - CONTRACTOR
    public void updateContractor(String contractor) {
        Log.v("PUCCI", "contractor = " + contractor);
        this.contractor = contractor;
    }

    public String getContractor() { return contractor; }

    // PROJECT ACTIVITY - FOOTINGS
    public void updateFootings(SpecialValues footings) {
        Log.v("PUCCI", "footings = " + footings.toString());
        this.footings = footings.toString();
    }

    public boolean getFootings() {
        if(footings.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    // PROJECT ACTIVITY - FOUNDATION WALLS
    public void updateFoundationWalls(SpecialValues foundationWalls) {
        Log.v("PUCCI", "foundationWalls = " + foundationWalls.toString());
        this.foundationWalls = foundationWalls.toString();
    }

    public boolean getFoundationWalls() {
        if(foundationWalls.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    // PROJECT ACTIVITY - SHEATHING
    public void updateSheathing(SpecialValues sheathing) {
        Log.v("PUCCI", "sheathing = " + sheathing.toString());
        this.sheathing = sheathing.toString();
    }

    public boolean getSheathing() {
        if(sheathing.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    // PROJECT ACTIVITY - FRAMING
    public void updateFraming(SpecialValues framing) {
        Log.v("PUCCI", "framing = " + framing.toString());
        this.framing = framing.toString();
    }

    public boolean getFraming() {
        if(framing.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    // PROJECT ACTIVITY - OTHER
    public void updateOther(SpecialValues other) {
        Log.v("PUCCI", "other = " + other.toString());
        this.other = other.toString();
    }

    public boolean getOther() {
        if(other.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    // PROJECT ACTIVITY - OTHER INPUT
    public void updateOtherInput(String otherInput) {
        Log.v("PUCCI", "otherInput input = " + otherInput);
        this.otherInput = otherInput;
    }

    public String getOtherInput() { return otherInput; }

    // DATE ACTIVITY - YEAR
    public void updateYear(String year) {
        Log.v("PUCCI", "year = " + year);
        this.year = year;
    }

    // DATE ACTIVITY - MONTH
    public void updateMonth(String month) {
        Log.v("PUCCI", "month = " + month);
        this.month = month;
    }

    // DATE ACTIVITY - DAY
    public void updateDay(String day) {
        Log.v("PUCCI", "day = " + day);
        this.day = day;
    }

    // DATE ACTIVITY - HOUR
    public void updateHour(String hour) {
        Log.v("PUCCI", "hour = " + hour);
        this.hour = hour;
    }

    // DATE ACTIVITY - MINUTE
    public void updateMinute(String minute) {
        Log.v("PUCCI", "minute = " + minute);
        this.minute = minute;
    }

    // DATE ACTIVITY - TIME PERIOD
    public void updateTimePeriod(String timePeriod) {
        Log.v("PUCCI", "timePeriod = " + timePeriod);
        this.timePeriod = timePeriod;
    }

    // DATE ACTIVITY - WEATHER
    public void updateWeather(String weather) {
        Log.v("PUCCI", "weather = " + weather);
        this.weather = weather;
    }

    // DATE ACTIVITY - TEMPERATURE
    public void updateTemperature(String temperature) {
        Log.v("DEBUG", "temperature = " + temperature);
        this.temperature = temperature;
    }

    // CONCRETE ACTIVITY - REBAR POSITION
    public void updateRebarPositionReviewed(SpecialValues rebarPositionReviewed) {
        Log.v("PUCCI", "rebarPositionReviewed = " + rebarPositionReviewed.toString());
        this.rebarPositionReviewed = rebarPositionReviewed.toString();
    }

    public boolean getRebarPositionReviewed() {
        if(rebarPositionReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateRebarPositionNA(SpecialValues rebarPositionNA) {
        Log.v("PUCCI", "rebarPositionNA = " + rebarPositionNA.toString());
        this.rebarPositionNA = rebarPositionNA.toString();
    }

    public boolean getRebarPositionNA() {
        if(rebarPositionNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateRebarPositionInstruction(String rebarPositionInstruction) {
        Log.v("PUCCI", "rebarPositionInstruction = " + rebarPositionInstruction);
        this.rebarPositionInstruction = rebarPositionInstruction;
    }

    public String getRebarPositionInstruction(){ return rebarPositionInstruction; }

    // CONCRETE ACTIVITY - REBAR SIZE REVIEWED
    public void updateRebarSizeReviewed(SpecialValues rebarSizeReviewed) {
        Log.v("PUCCI", "rebarSizeReviewed = " + rebarSizeReviewed.toString());
        this.rebarSizeReviewed = rebarSizeReviewed.toString();
    }

    public boolean getRebarSizeReviewed() {
        if(rebarSizeReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    // CONCRETE ACTIVITY - REBAR SIZE NA
    public void updateRebarSizeNA(SpecialValues rebarSizeNA) {
        Log.v("PUCCI", "rebarSizeNA = " + rebarSizeNA.toString());
        this.rebarSizeNA = rebarSizeNA.toString();
    }

    public boolean getRebarSizeNA() {
        if(rebarSizeNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    // CONCRETE ACTIVITY - REBAR SIZE INSTRUCTION
    public void updateRebarSizeInstruction(String rebarSizeInstruction) {
        Log.v("PUCCI", "rebarSizeInstruction = " + rebarSizeInstruction);
        this.rebarSizeInstruction = rebarSizeInstruction;
    }

    public String getRebarSizeInstruction(){ return rebarSizeInstruction; }

    // CONCRETE ACTIVITY - FORMWORK REVIEWED
    public void updateFormworkReviewed(SpecialValues formworkReviewed) {
        Log.v("PUCCI", "formworkReviewed = " + formworkReviewed.toString());
        this.formworkReviewed = formworkReviewed.toString();
    }

    public boolean getFormworkReviewed() {
        if(formworkReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    // CONCRETE ACTIVITY - FORMWORK NA
    public void updateFormworkNA(SpecialValues formworkNA) {
        Log.v("PUCCI", "formworkNA = " + formworkNA.toString());
        this.formworkNA = formworkNA.toString();
    }

    public boolean getFormworkNA() {
        if(formworkNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    // CONCRETE ACTIVITY - FORMWORK INSTRUCTION
    public void updateFormworkInstruction(String formworkInstruction) {
        Log.v("PUCCI", "formworkInstruction = " + formworkInstruction);
        this.formworkInstruction = formworkInstruction;
    }

    public String getFormworkInstruction(){ return formworkInstruction; }

    // CONCRETE ACTIVITY - ANCHORAGE REVIEWED
    public void updateAnchorageReviewed(SpecialValues anchorageReviewed) {
        Log.v("PUCCI", "anchorageReviewed = " + anchorageReviewed.toString());
        this.anchorageReviewed = anchorageReviewed.toString();
    }

    public boolean getAnchorageReviewed() {
        if(anchorageReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    // CONCRETE ACTIVITY - ANCHORAGE NA
    public void updateAnchorageNA(SpecialValues anchorageNA) {
        Log.v("PUCCI", "anchorageNA = " + anchorageNA.toString());
        this.anchorageNA = anchorageNA.toString();
    }

    public boolean getAnchorageNA() {
        if(anchorageNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    // CONCRETE ACTIVITY - ANCHORAGE INSTRUCTION
    public void updateAnchorageInstruction(String anchorageInstruction) {
        Log.v("PUCCI", "anchorageInstruction = " + anchorageInstruction);
        this.anchorageInstruction = anchorageInstruction;
    }

    public String getAnchorageInstruction(){ return anchorageInstruction; }

    public enum SpecialValues {
        YES ("Yes"),
        NO ("No"),
        NA ("N/A"),
        NONE("None"),
        EMPTY ("");
        private String value;
        SpecialValues(String value) { this.value = value; }

        @Override
        public String toString() {
            return value;
        }
    }
}

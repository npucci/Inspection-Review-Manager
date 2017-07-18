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
    private String rebarPosition = "";
    private String rebarPositionInstruction = "";
    private String rebarSizeSpacing = "";
    private String rebarSizeSpacingInstruction = "";
    private String holdDownAnchorage = "";
    private String holdDownAnchorageInstruction = "";
    private String formwork = "";
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

    public Model() {
    }

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
//            footingsReview = "";
//            foundationWallsReview = "";
//            sheathingReview = "";
//            framingReview = "";
//            otherReview = "";
//            otherInput = "";
        // CONCRETE:
        rebarPosition = "";
        rebarPositionInstruction = "";
        rebarSizeSpacing = "";
        rebarSizeSpacingInstruction = "";
        holdDownAnchorage = "";
        holdDownAnchorageInstruction = "";
        formwork = "";
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
        Log.v("PUCCI", "footings = " + footings.value);
        this.footings = footings.value;
    }

    public boolean getFootings() {
        if(footings.equals(SpecialValues.YES.value)) return true;
        return false;
    }

    // PROJECT ACTIVITY - FOUNDATION WALLS
    public void updateFoundationWalls(SpecialValues foundationWalls) {
        Log.v("PUCCI", "foundationWalls = " + foundationWalls.value);
        this.foundationWalls = foundationWalls.value;
    }

    public boolean getFoundationWalls() {
        if(foundationWalls.equals(SpecialValues.YES.value)) return true;
        return false;
    }

    // PROJECT ACTIVITY - SHEATHING
    public void updateSheathing(SpecialValues sheathing) {
        Log.v("PUCCI", "sheathing = " + sheathing.value);
        this.sheathing = sheathing.value;
    }

    public boolean getSheathing() {
        if(sheathing.equals(SpecialValues.YES.value)) return true;
        return false;
    }

    // PROJECT ACTIVITY - FRAMING
    public void updateFraming(SpecialValues framing) {
        Log.v("PUCCI", "framing = " + framing.value);
        this.framing = framing.value;
    }

    public boolean getFraming() {
        if(framing.equals(SpecialValues.YES.value)) return true;
        return false;
    }

    // PROJECT ACTIVITY - OTHER
    public void updateOther(SpecialValues other) {
        Log.v("PUCCI", "other = " + other.value);
        this.other = other.value;
    }

    public boolean getOther() {
        if(other.equals(SpecialValues.YES.value)) return true;
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

    public enum SpecialValues {
        YES ("Yes"),
        NO ("No"),
        NA ("N/A"),
        EMPTY ("");
        private String value;
        SpecialValues(String value) {
            this.value = value;
        }
    }
}

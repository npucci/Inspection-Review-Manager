package com.example.nicco.inspectionReviewManager;

import android.app.Application;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by Nicco on 2017-07-17.
 */

public class Model extends Application {
    // DATE
    private String year = "";
    private String month = "";
    private String day = "";
    private String hour = "";
    private String minute = "";
    private String timePeriod = "";
    private String weather = "";
    private String temperature = "";
    // PROJECT
    private String address = "";
    private String projectNumber = "";
    private String cityProv = "";
    private String developer = "";
    private String contractor = "";
    private String footings = "";
    private String foundationWalls = "";
    private String sheathing = "";
    private String framing = "";
    private String other = "";
    private String description = "";
    // CONCRETE
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
    // FRAMING
    private String trussSpecReviewed = "";
    private String trussSpecNA = "";
    private String trussSpecInstruction = "";
    private String iJoistReviewed = "";
    private String iJoistNA = "";
    private String iJoistInstruction = "";
    private String bearingReviewed = "";
    private String bearingNA = "";
    private String bearingInstruction = "";
    private String topPlatesReviewed = "";
    private String topPlatesNA = "";
    private String topPlatesInstruction = "";
    private String lintelsReviewed = "";
    private String lintelsNA = "";
    private String lintelsInstruction = "";
    private String shearwallsReviewed = "";
    private String shearwallsNA = "";
    private String shearwallsInstruction = "";
    private String tallWallsReviewed = "";
    private String tallWallsNA = "";
    private String tallWallsInstruction = "";
    private String blockingReviewed = "";
    private String blockingNA = "";
    private String blockingInstruction = "";
    private String wallSheathingReviewed = "";
    private String wallSheathingNA = "";
    private String wallSheathingInstruction = "";
    private String windGirtsReviewed = "";
    private String windGirtsNA = "";
    private String windGirtsInstruction = "";
    // REVIEW CONCLUSION
    private String observations = "";
    private String comments = "";
    private String reviewStatus = "";
    private String reviewedBy = "";

    // COLORS
    private int completeColor = Color.rgb(108, 249, 93);
    private int incompleteColor = Color.WHITE; //Color.rgb(249, 93, 93);

    // ACTIVITY COMPLETE FLAGS
    private boolean dateActivityComplete = false;
    private boolean projectActivityComplete = false;
    private boolean concreteActivityComplete = false;
    private boolean framingActivityComplete = false;
    private boolean conclusionActivityComplete = false;

    public Model() {}

    public void reset() {
        // DATE
        year = "";
        month = "";
        day = "";
        hour = "";
        minute = "";
        timePeriod = "";
        weather = "";
        temperature = "";
        // PROJECT
        address = "";
        projectNumber = "";
        cityProv = "";
        developer = "";
        contractor = "";
        footings = "";
        foundationWalls = "";
        sheathing = "";
        framing = "";
        other = "";
        description = "";
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
        trussSpecReviewed = "";
        trussSpecNA = "";
        trussSpecInstruction = "";
        iJoistReviewed = "";
        iJoistNA = "";
        iJoistInstruction = "";
        bearingReviewed = "";
        bearingNA = "";
        bearingInstruction = "";
        topPlatesReviewed = "";
        topPlatesNA = "";
        topPlatesInstruction = "";
        lintelsReviewed = "";
        lintelsNA = "";
        lintelsInstruction = "";
        shearwallsReviewed = "";
        shearwallsNA = "";
        shearwallsInstruction = "";
        tallWallsReviewed = "";
        tallWallsNA = "";
        tallWallsInstruction = "";
        blockingReviewed = "";
        blockingNA = "";
        blockingInstruction = "";
        wallSheathingReviewed = "";
        wallSheathingNA = "";
        wallSheathingInstruction = "";
        windGirtsReviewed = "";
        windGirtsNA = "";
        windGirtsInstruction = "";
        // CONCLUSION
        observations = "";
        comments = "";
        reviewStatus = "";
        reviewedBy = "";
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
    public void updateFootings(SpecialValue footings) {
        Log.v("PUCCI", "footings = " + footings.toString());
        this.footings = footings.toString();
    }

    public boolean getFootings() {
        if(footings.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    // PROJECT ACTIVITY - FOUNDATION WALLS
    public void updateFoundationWalls(SpecialValue foundationWalls) {
        Log.v("PUCCI", "foundationWalls = " + foundationWalls.toString());
        this.foundationWalls = foundationWalls.toString();
    }

    public boolean getFoundationWalls() {
        if(foundationWalls.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    // PROJECT ACTIVITY - SHEATHING
    public void updateSheathing(SpecialValue sheathing) {
        Log.v("PUCCI", "sheathing = " + sheathing.toString());
        this.sheathing = sheathing.toString();
    }

    public boolean getSheathing() {
        if(sheathing.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    // PROJECT ACTIVITY - FRAMING
    public void updateFraming(SpecialValue framing) {
        Log.v("PUCCI", "framing = " + framing.toString());
        this.framing = framing.toString();
    }

    public boolean getFraming() {
        if(framing.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    // PROJECT ACTIVITY - OTHER
    public void updateOther(SpecialValue other) {
        Log.v("PUCCI", "other = " + other.toString());
        this.other = other.toString();
    }

    public boolean getOther() {
        if(other.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    // PROJECT ACTIVITY - OTHER INPUT
    public void updateDescription(String description) {
        Log.v("PUCCI", "description = " + description);
        this.description = description;
    }

    public String getDescription() { return description; }

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
    public void updateRebarPositionReviewed(SpecialValue rebarPositionReviewed) {
        Log.v("PUCCI", "rebarPositionReviewed = " + rebarPositionReviewed.toString());
        this.rebarPositionReviewed = rebarPositionReviewed.toString();
    }

    public boolean getRebarPositionReviewed() {
        if(rebarPositionReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateRebarPositionNA(SpecialValue rebarPositionNA) {
        Log.v("PUCCI", "rebarPositionNA = " + rebarPositionNA.toString());
        this.rebarPositionNA = rebarPositionNA.toString();
    }

    public boolean getRebarPositionNA() {
        if(rebarPositionNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateRebarPositionInstruction(String rebarPositionInstruction) {
        Log.v("PUCCI", "rebarPositionInstruction = " + rebarPositionInstruction);
        this.rebarPositionInstruction = rebarPositionInstruction;
    }

    public String getRebarPositionInstruction(){ return rebarPositionInstruction; }

    // CONCRETE ACTIVITY - REBAR SIZE
    public void updateRebarSizeReviewed(SpecialValue rebarSizeReviewed) {
        Log.v("PUCCI", "rebarSizeReviewed = " + rebarSizeReviewed.toString());
        this.rebarSizeReviewed = rebarSizeReviewed.toString();
    }

    public boolean getRebarSizeReviewed() {
        if(rebarSizeReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateRebarSizeNA(SpecialValue rebarSizeNA) {
        Log.v("PUCCI", "rebarSizeNA = " + rebarSizeNA.toString());
        this.rebarSizeNA = rebarSizeNA.toString();
    }

    public boolean getRebarSizeNA() {
        if(rebarSizeNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateRebarSizeInstruction(String rebarSizeInstruction) {
        Log.v("PUCCI", "rebarSizeInstruction = " + rebarSizeInstruction);
        this.rebarSizeInstruction = rebarSizeInstruction;
    }

    public String getRebarSizeInstruction(){ return rebarSizeInstruction; }

    // CONCRETE ACTIVITY - FORMWORK
    public void updateFormworkReviewed(SpecialValue formworkReviewed) {
        Log.v("PUCCI", "formworkReviewed = " + formworkReviewed.toString());
        this.formworkReviewed = formworkReviewed.toString();
    }

    public boolean getFormworkReviewed() {
        if(formworkReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateFormworkNA(SpecialValue formworkNA) {
        Log.v("PUCCI", "formworkNA = " + formworkNA.toString());
        this.formworkNA = formworkNA.toString();
    }

    public boolean getFormworkNA() {
        if(formworkNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateFormworkInstruction(String formworkInstruction) {
        Log.v("PUCCI", "formworkInstruction = " + formworkInstruction);
        this.formworkInstruction = formworkInstruction;
    }

    public String getFormworkInstruction(){ return formworkInstruction; }

    // CONCRETE ACTIVITY - ANCHORAGE
    public void updateAnchorageReviewed(SpecialValue anchorageReviewed) {
        Log.v("PUCCI", "anchorageReviewed = " + anchorageReviewed.toString());
        this.anchorageReviewed = anchorageReviewed.toString();
    }

    public boolean getAnchorageReviewed() {
        if(anchorageReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateAnchorageNA(SpecialValue anchorageNA) {
        Log.v("PUCCI", "anchorageNA = " + anchorageNA.toString());
        this.anchorageNA = anchorageNA.toString();
    }

    public boolean getAnchorageNA() {
        if(anchorageNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateAnchorageInstruction(String anchorageInstruction) {
        Log.v("PUCCI", "anchorageInstruction = " + anchorageInstruction);
        this.anchorageInstruction = anchorageInstruction;
    }

    public String getAnchorageInstruction(){ return anchorageInstruction; }

    // FRAMING ACTIVITY - TRUSS SPEC
    public void updateTrussSpecReviewed(SpecialValue trussSpecReviewed) {
        Log.v("PUCCI", "trussSpecReviewed = " + trussSpecReviewed.toString());
        this.trussSpecReviewed = trussSpecReviewed.toString();
    }

    public boolean getTrussSpecReviewed() {
        if(trussSpecReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateTrussSpecNA(SpecialValue trussSpecNA) {
        Log.v("PUCCI", "trussSpecNA = " + trussSpecNA.toString());
        this.trussSpecNA = trussSpecNA.toString();
    }

    public boolean getTrussSpecNA() {
        if(trussSpecNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateTrussSpecInstruction(String trussSpecInstruction) {
        Log.v("PUCCI", "trussSpecInstruction = " + trussSpecInstruction);
        this.trussSpecInstruction = trussSpecInstruction;
    }

    public String getTrussSpecInstruction(){ return trussSpecInstruction; }

    // FRAMING ACTIVITY - IJOIST
    public void updateIJoistReviewed(SpecialValue iJoistReviewed) {
        Log.v("PUCCI", "iJoistReviewed = " + iJoistReviewed.toString());
        this.iJoistReviewed = iJoistReviewed.toString();
    }

    public boolean getIJoistReviewed() {
        if(iJoistReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateIJoistNA(SpecialValue iJoistNA) {
        Log.v("PUCCI", "iJoistNA = " + iJoistNA.toString());
        this.iJoistNA = iJoistNA.toString();
    }

    public boolean getIJoistNA() {
        if(iJoistNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateIJoistInstruction(String iJoistInstruction) {
        Log.v("PUCCI", "iJoistInstruction = " + iJoistInstruction);
        this.iJoistInstruction = iJoistInstruction;
    }

    public String getIJoistInstruction(){ return iJoistInstruction; }

    // FRAMING ACTIVITY - BEARING
    public void updateBearingReviewed(SpecialValue bearingReviewed) {
        Log.v("PUCCI", "bearingReviewed = " + bearingReviewed.toString());
        this.bearingReviewed = bearingReviewed.toString();
    }

    public boolean getBearingReviewed() {
        if(bearingReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateBearingNA(SpecialValue bearingNA) {
        Log.v("PUCCI", "bearingNA = " + bearingNA.toString());
        this.bearingNA = bearingNA.toString();
    }

    public boolean getBearingNA() {
        if(bearingNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateBearingInstruction(String bearingInstruction) {
        Log.v("PUCCI", "bearingInstruction = " + bearingInstruction);
        this.bearingInstruction = bearingInstruction;
    }

    public String getBearingInstruction(){ return bearingInstruction; }

    // FRAMING ACTIVITY - TOP PLATES
    public void updateTopPlatesReviewed(SpecialValue topPlatesReviewed) {
        Log.v("PUCCI", "topPlatesReviewed = " + topPlatesReviewed.toString());
        this.topPlatesReviewed = topPlatesReviewed.toString();
    }

    public boolean getTopPlatesReviewed() {
        if(topPlatesReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateTopPlatesNA(SpecialValue topPlatesNA) {
        Log.v("PUCCI", "topPlatesNA = " + topPlatesNA.toString());
        this.topPlatesNA = topPlatesNA.toString();
    }

    public boolean getTopPlatesNA() {
        if(topPlatesNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateTopPlatesInstruction(String topPlatesInstruction) {
        Log.v("PUCCI", "topPlatesInstruction = " + topPlatesInstruction);
        this.topPlatesInstruction = topPlatesInstruction;
    }

    public String getTopPlatesInstruction(){ return topPlatesInstruction; }

    // FRAMING ACTIVITY - LINTELS
    public void updateLintelsReviewed(SpecialValue lintelsReviewed) {
        Log.v("PUCCI", "lintelsReviewed = " + lintelsReviewed.toString());
        this.lintelsReviewed = lintelsReviewed.toString();
    }

    public boolean getLintelsReviewed() {
        if(lintelsReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateLintelsNA(SpecialValue lintelsNA) {
        Log.v("PUCCI", "lintelsNA = " + lintelsNA.toString());
        this.lintelsNA = lintelsNA.toString();
    }

    public boolean getLintelsNA() {
        if(lintelsNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateLintelsInstruction(String lintelsInstruction) {
        Log.v("PUCCI", "lintelsInstruction = " + lintelsInstruction);
        this.lintelsInstruction = lintelsInstruction;
    }

    public String getLintelsInstruction(){ return lintelsInstruction; }

    // FRAMING ACTIVITY - TALL WALLS
    public void updateTallWallsReviewed(SpecialValue tallWallsReviewed) {
        Log.v("PUCCI", "tallWallsReviewed = " + tallWallsReviewed.toString());
        this.tallWallsReviewed = tallWallsReviewed.toString();
    }

    public boolean getTallWallsReviewed() {
        if(tallWallsReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateTallWallsNA(SpecialValue tallWallsNA) {
        Log.v("PUCCI", "tallWallsNA = " + tallWallsNA.toString());
        this.tallWallsNA = tallWallsNA.toString();
    }

    public boolean getTallWallsNA() {
        if(tallWallsNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateTallWallsInstruction(String tallWallsInstruction) {
        Log.v("PUCCI", "tallWallsInstruction = " + tallWallsInstruction);
        this.tallWallsInstruction = tallWallsInstruction;
    }

    public String getTallWallsInstruction(){ return tallWallsInstruction; }

    // FRAMING ACTIVITY - SHEARWALLS
    public void updateShearwallsReviewed(SpecialValue shearwallsReviewed) {
        Log.v("PUCCI", "shearwallsReviewed = " + shearwallsReviewed.toString());
        this.shearwallsReviewed = shearwallsReviewed.toString();
    }

    public boolean getShearwallsReviewed() {
        if(shearwallsReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateShearwallsNA(SpecialValue shearwallsNA) {
        Log.v("PUCCI", "shearwallsNA = " + shearwallsNA.toString());
        this.shearwallsNA = shearwallsNA.toString();
    }

    public boolean getShearwallsNA() {
        if(shearwallsNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateShearwallsInstruction(String shearwallsInstruction) {
        Log.v("PUCCI", "shearwallsInstruction = " + shearwallsInstruction);
        this.shearwallsInstruction = shearwallsInstruction;
    }

    public String getShearwallsInstruction(){ return shearwallsInstruction; }

    // FRAMING ACTIVITY - BLOCKING
    public void updateBlockingReviewed(SpecialValue blockingReviewed) {
        Log.v("PUCCI", "blockingReviewed = " + blockingReviewed.toString());
        this.blockingReviewed = blockingReviewed.toString();
    }

    public boolean getBlockingReviewed() {
        if(blockingReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateBlockingNA(SpecialValue blockingNA) {
        Log.v("PUCCI", "blockingNA = " + blockingNA.toString());
        this.blockingNA = blockingNA.toString();
    }

    public boolean getBlockingNA() {
        if(blockingNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateBlockingInstruction(String blockingInstruction) {
        Log.v("PUCCI", "blockingInstruction = " + blockingInstruction);
        this.blockingInstruction = blockingInstruction;
    }

    public String getBlockingInstruction(){ return blockingInstruction; }

    // FRAMING ACTIVITY - WALL SHEATHING
    public void updateWallSheathingReviewed(SpecialValue wallSheathingReviewed) {
        Log.v("PUCCI", "wallSheathingReviewed = " + wallSheathingReviewed.toString());
        this.wallSheathingReviewed = wallSheathingReviewed.toString();
    }

    public boolean getWallSheathingReviewed() {
        if(wallSheathingReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateWallSheathingNA(SpecialValue wallSheathingNA) {
        Log.v("PUCCI", "wallSheathingNA = " + wallSheathingNA.toString());
        this.wallSheathingNA = wallSheathingNA.toString();
    }

    public boolean getWallSheathingNA() {
        if(wallSheathingNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateWallSheathingInstruction(String wallSheathingInstruction) {
        Log.v("PUCCI", "wallSheathingInstruction = " + wallSheathingInstruction);
        this.wallSheathingInstruction = wallSheathingInstruction;
    }

    public String getWallSheathingInstruction(){ return wallSheathingInstruction; }

    // FRAMING ACTIVITY - WIND GIRTS
    public void updateWindGirtsReviewed(SpecialValue windGirtsReviewed) {
        Log.v("PUCCI", "windGirtsReviewed = " + windGirtsReviewed.toString());
        this.windGirtsReviewed = windGirtsReviewed.toString();
    }

    public boolean getWindGirtsReviewed() {
        if(windGirtsReviewed.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateWindGirtsNA(SpecialValue windGirtsNA) {
        Log.v("PUCCI", "windGirtsNA = " + windGirtsNA.toString());
        this.windGirtsNA = windGirtsNA.toString();
    }

    public boolean getWindGirtsNA() {
        if(windGirtsNA.equals(SpecialValue.YES.toString())) return true;
        return false;
    }

    public void updateWindGirtsInstruction(String windGirtsInstruction) {
        Log.v("PUCCI", "windGirtsInstruction = " + windGirtsInstruction);
        this.windGirtsInstruction = windGirtsInstruction;
    }

    public String getWindGirtsInstruction(){ return windGirtsInstruction; }

    // CONCLUSION ACTIVITY - OBSERVATIONS
    public void updateObservations(String observations) {
        Log.v("PUCCI", "observations = " + observations);
        this.observations = observations;
    }

    public String getObservations() { return observations; }

    // CONCLUSION ACTIVITY - COMMENTS
    public void updateComments(String comments) {
        Log.v("PUCCI", "comments = " + comments);
        this.comments = comments;
    }

    public String getComments() { return comments; }

    // CONCLUSION ACTIVITY - REVIEW STATUS
    public void updateReviewStatus(ReviewStatusValue reviewStatus) {
        Log.v("PUCCI", "reviewStatus = " + reviewStatus.toString());
        this.reviewStatus = reviewStatus.toString();
    }

    public String getReviewedStatus() { return reviewStatus; }

    // CONCLUSION ACTIVITY - REVEWED BY
    public void updateReviewedBy(String reviewedBy) {
        Log.v("PUCCI", "reviewedBy = " + reviewedBy);
        this.reviewedBy = reviewedBy;
    }

    public String getReviewedBy() { return reviewedBy; }

    public enum SpecialValue {
        YES ("Yes"),
        NO ("No"),
        NA ("N/A"),
        NONE("None"),
        EMPTY ("");
        private String value;
        SpecialValue(String value) { this.value = value; }

        @Override
        public String toString() {
            return value;
        }
    }

    public int getBackgroundColor(String listItem) {
        return  getColor(listItem, completeColor, incompleteColor);
    }

    public int getTextColor(String listItem) {
       return  getColor(listItem, completeColor, Color.BLACK);
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

    public void checkProjectActivityStatus() {}

    public void checkConcreteActivityStatus() {
        boolean incomplete = false;
        if(rebarPositionReviewed == null || rebarPositionReviewed.equals("")) incomplete = true;
        if(!incomplete && rebarPositionNA == null || rebarPositionNA.equals("")) incomplete = true;
        if(!incomplete && rebarPositionInstruction == null || rebarPositionInstruction.equals("")) incomplete =  true;
        if(!incomplete && rebarSizeReviewed == null || rebarSizeReviewed.equals("")) incomplete = true;
        if(!incomplete && rebarSizeNA == null || rebarSizeNA.equals("")) incomplete = true;
        if(!incomplete && rebarSizeInstruction == null || rebarSizeInstruction.equals("")) incomplete = true;
        if(!incomplete && anchorageReviewed == null || anchorageReviewed.equals("")) incomplete = true;
        if(!incomplete && anchorageNA == null || anchorageNA.equals("")) incomplete = true;
        if(!incomplete && anchorageInstruction == null || anchorageInstruction.equals("")) incomplete = true;
        if(!incomplete && formworkReviewed == null || formworkReviewed.equals("")) incomplete =  true;
        if(!incomplete && formworkNA == null || formworkNA.equals("")) incomplete =  true;
        if(!incomplete && formworkInstruction == null || formworkInstruction.equals("")) incomplete =  true;

        if(!incomplete) concreteActivityComplete = true;
        else concreteActivityComplete = false;
    }

    public void checkFramingActivityStatus() {
        boolean incomplete = false;
        if(trussSpecReviewed == null || trussSpecReviewed.equals("")) incomplete = true;
        if(!incomplete && trussSpecNA == null || trussSpecNA.equals("")) incomplete = true;
        if(!incomplete && trussSpecInstruction == null || trussSpecInstruction.equals("")) incomplete =  true;
        if(!incomplete && iJoistReviewed == null || iJoistReviewed.equals("")) incomplete = true;
        if(!incomplete && iJoistNA == null || iJoistNA.equals("")) incomplete = true;
        if(!incomplete && iJoistInstruction == null || iJoistInstruction.equals("")) incomplete = true;
        if(!incomplete && bearingReviewed == null || bearingReviewed.equals("")) incomplete = true;
        if(!incomplete && bearingNA == null || bearingNA.equals("")) incomplete = true;
        if(!incomplete && bearingInstruction == null || bearingInstruction.equals("")) incomplete = true;
        if(!incomplete && topPlatesReviewed == null || topPlatesReviewed.equals("")) incomplete =  true;
        if(!incomplete && topPlatesNA == null || topPlatesNA.equals("")) incomplete =  true;
        if(!incomplete && topPlatesInstruction == null || topPlatesInstruction.equals("")) incomplete =  true;
        if(!incomplete && lintelsReviewed == null || lintelsReviewed.equals("")) incomplete =  true;
        if(!incomplete && lintelsNA == null || lintelsNA.equals("")) incomplete =  true;
        if(!incomplete && lintelsInstruction == null || lintelsInstruction.equals("")) incomplete =  true;
        if(!incomplete && shearwallsReviewed == null || shearwallsReviewed.equals("")) incomplete =  true;
        if(!incomplete && shearwallsNA == null || shearwallsNA.equals("")) incomplete =  true;
        if(!incomplete && shearwallsInstruction == null || shearwallsInstruction.equals("")) incomplete =  true;
        if(!incomplete && tallWallsReviewed == null || tallWallsReviewed.equals("")) incomplete =  true;
        if(!incomplete && tallWallsInstruction == null || tallWallsInstruction.equals("")) incomplete =  true;
        if(!incomplete && blockingReviewed == null || blockingReviewed.equals("")) incomplete =  true;
        if(!incomplete && blockingNA == null || blockingNA.equals("")) incomplete =  true;
        if(!incomplete && blockingInstruction == null || blockingInstruction.equals("")) incomplete =  true;
        if(!incomplete && wallSheathingReviewed == null || wallSheathingReviewed.equals("")) incomplete =  true;
        if(!incomplete && wallSheathingNA == null || wallSheathingNA.equals("")) incomplete =  true;
        if(!incomplete && wallSheathingInstruction == null || wallSheathingInstruction.equals("")) incomplete =  true;
        if(!incomplete && windGirtsReviewed == null || windGirtsReviewed.equals("")) incomplete =  true;
        if(!incomplete && windGirtsNA == null || windGirtsNA.equals("")) incomplete =  true;
        if(!incomplete && windGirtsInstruction == null || windGirtsInstruction.equals("")) incomplete =  true;

        if(!incomplete) framingActivityComplete = true;
        else framingActivityComplete = false;
    }

    public void checkConclusionActivityStatus() {}

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
}

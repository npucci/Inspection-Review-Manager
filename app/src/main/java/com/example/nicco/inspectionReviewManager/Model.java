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

    // CONCRETE ACTIVITY - REBAR SIZE
    public void updateRebarSizeReviewed(SpecialValues rebarSizeReviewed) {
        Log.v("PUCCI", "rebarSizeReviewed = " + rebarSizeReviewed.toString());
        this.rebarSizeReviewed = rebarSizeReviewed.toString();
    }

    public boolean getRebarSizeReviewed() {
        if(rebarSizeReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateRebarSizeNA(SpecialValues rebarSizeNA) {
        Log.v("PUCCI", "rebarSizeNA = " + rebarSizeNA.toString());
        this.rebarSizeNA = rebarSizeNA.toString();
    }

    public boolean getRebarSizeNA() {
        if(rebarSizeNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateRebarSizeInstruction(String rebarSizeInstruction) {
        Log.v("PUCCI", "rebarSizeInstruction = " + rebarSizeInstruction);
        this.rebarSizeInstruction = rebarSizeInstruction;
    }

    public String getRebarSizeInstruction(){ return rebarSizeInstruction; }

    // CONCRETE ACTIVITY - FORMWORK
    public void updateFormworkReviewed(SpecialValues formworkReviewed) {
        Log.v("PUCCI", "formworkReviewed = " + formworkReviewed.toString());
        this.formworkReviewed = formworkReviewed.toString();
    }

    public boolean getFormworkReviewed() {
        if(formworkReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateFormworkNA(SpecialValues formworkNA) {
        Log.v("PUCCI", "formworkNA = " + formworkNA.toString());
        this.formworkNA = formworkNA.toString();
    }

    public boolean getFormworkNA() {
        if(formworkNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateFormworkInstruction(String formworkInstruction) {
        Log.v("PUCCI", "formworkInstruction = " + formworkInstruction);
        this.formworkInstruction = formworkInstruction;
    }

    public String getFormworkInstruction(){ return formworkInstruction; }

    // CONCRETE ACTIVITY - ANCHORAGE
    public void updateAnchorageReviewed(SpecialValues anchorageReviewed) {
        Log.v("PUCCI", "anchorageReviewed = " + anchorageReviewed.toString());
        this.anchorageReviewed = anchorageReviewed.toString();
    }

    public boolean getAnchorageReviewed() {
        if(anchorageReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateAnchorageNA(SpecialValues anchorageNA) {
        Log.v("PUCCI", "anchorageNA = " + anchorageNA.toString());
        this.anchorageNA = anchorageNA.toString();
    }

    public boolean getAnchorageNA() {
        if(anchorageNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateAnchorageInstruction(String anchorageInstruction) {
        Log.v("PUCCI", "anchorageInstruction = " + anchorageInstruction);
        this.anchorageInstruction = anchorageInstruction;
    }

    public String getAnchorageInstruction(){ return anchorageInstruction; }

    // FRAMING ACTIVITY - TRUSS SPEC
    public void updateTrussSpecReviewed(SpecialValues trussSpecReviewed) {
        Log.v("PUCCI", "trussSpecReviewed = " + trussSpecReviewed.toString());
        this.trussSpecReviewed = trussSpecReviewed.toString();
    }

    public boolean getTrussSpecReviewed() {
        if(trussSpecReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateTrussSpecNA(SpecialValues trussSpecNA) {
        Log.v("PUCCI", "trussSpecNA = " + trussSpecNA.toString());
        this.trussSpecNA = trussSpecNA.toString();
    }

    public boolean getTrussSpecNA() {
        if(trussSpecNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateTrussSpecInstruction(String trussSpecInstruction) {
        Log.v("PUCCI", "trussSpecInstruction = " + trussSpecInstruction);
        this.trussSpecInstruction = trussSpecInstruction;
    }

    public String getTrussSpecInstruction(){ return trussSpecInstruction; }

    // FRAMING ACTIVITY - IJOIST
    public void updateIJoistReviewed(SpecialValues iJoistReviewed) {
        Log.v("PUCCI", "iJoistReviewed = " + iJoistReviewed.toString());
        this.iJoistReviewed = iJoistReviewed.toString();
    }

    public boolean getIJoistReviewed() {
        if(iJoistReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateIJoistNA(SpecialValues iJoistNA) {
        Log.v("PUCCI", "iJoistNA = " + iJoistNA.toString());
        this.iJoistNA = iJoistNA.toString();
    }

    public boolean getIJoistNA() {
        if(iJoistNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateIJoistInstruction(String iJoistInstruction) {
        Log.v("PUCCI", "iJoistInstruction = " + iJoistInstruction);
        this.iJoistInstruction = iJoistInstruction;
    }

    public String getIJoistInstruction(){ return iJoistInstruction; }

    // FRAMING ACTIVITY - BEARING
    public void updateBearingReviewed(SpecialValues bearingReviewed) {
        Log.v("PUCCI", "bearingReviewed = " + bearingReviewed.toString());
        this.bearingReviewed = bearingReviewed.toString();
    }

    public boolean getBearingReviewed() {
        if(bearingReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateBearingNA(SpecialValues bearingNA) {
        Log.v("PUCCI", "bearingNA = " + bearingNA.toString());
        this.bearingNA = bearingNA.toString();
    }

    public boolean getBearingNA() {
        if(bearingNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateBearingInstruction(String bearingInstruction) {
        Log.v("PUCCI", "bearingInstruction = " + bearingInstruction);
        this.bearingInstruction = bearingInstruction;
    }

    public String getBearingInstruction(){ return bearingInstruction; }

    // FRAMING ACTIVITY - TOP PLATES
    public void updateTopPlatesReviewed(SpecialValues topPlatesReviewed) {
        Log.v("PUCCI", "topPlatesReviewed = " + topPlatesReviewed.toString());
        this.topPlatesReviewed = topPlatesReviewed.toString();
    }

    public boolean getTopPlatesReviewed() {
        if(topPlatesReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateTopPlatesNA(SpecialValues topPlatesNA) {
        Log.v("PUCCI", "topPlatesNA = " + topPlatesNA.toString());
        this.topPlatesNA = topPlatesNA.toString();
    }

    public boolean getTopPlatesNA() {
        if(topPlatesNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateTopPlatesInstruction(String topPlatesInstruction) {
        Log.v("PUCCI", "topPlatesInstruction = " + topPlatesInstruction);
        this.topPlatesInstruction = topPlatesInstruction;
    }

    public String getTopPlatesInstruction(){ return topPlatesInstruction; }

    // FRAMING ACTIVITY - LINTELS
    public void updateLintelsReviewed(SpecialValues lintelsReviewed) {
        Log.v("PUCCI", "lintelsReviewed = " + lintelsReviewed.toString());
        this.lintelsReviewed = lintelsReviewed.toString();
    }

    public boolean getLintelsReviewed() {
        if(lintelsReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateLintelsNA(SpecialValues lintelsNA) {
        Log.v("PUCCI", "lintelsNA = " + lintelsNA.toString());
        this.lintelsNA = lintelsNA.toString();
    }

    public boolean getLintelsNA() {
        if(lintelsNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateLintelsInstruction(String lintelsInstruction) {
        Log.v("PUCCI", "lintelsInstruction = " + lintelsInstruction);
        this.lintelsInstruction = lintelsInstruction;
    }

    public String getLintelsInstruction(){ return lintelsInstruction; }

    // FRAMING ACTIVITY - TALL WALLS
    public void updateTallWallsReviewed(SpecialValues tallWallsReviewed) {
        Log.v("PUCCI", "tallWallsReviewed = " + tallWallsReviewed.toString());
        this.tallWallsReviewed = tallWallsReviewed.toString();
    }

    public boolean getTallWallsReviewed() {
        if(tallWallsReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateTallWallsNA(SpecialValues tallWallsNA) {
        Log.v("PUCCI", "tallWallsNA = " + tallWallsNA.toString());
        this.tallWallsNA = tallWallsNA.toString();
    }

    public boolean getTallWallsNA() {
        if(tallWallsNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateTallWallsInstruction(String tallWallsInstruction) {
        Log.v("PUCCI", "tallWallsInstruction = " + tallWallsInstruction);
        this.tallWallsInstruction = tallWallsInstruction;
    }

    public String getTallWallsInstruction(){ return tallWallsInstruction; }

    // FRAMING ACTIVITY - SHEARWALLS
    public void updateShearwallsReviewed(SpecialValues shearwallsReviewed) {
        Log.v("PUCCI", "shearwallsReviewed = " + shearwallsReviewed.toString());
        this.shearwallsReviewed = shearwallsReviewed.toString();
    }

    public boolean getShearwallsReviewed() {
        if(shearwallsReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateShearwallsNA(SpecialValues shearwallsNA) {
        Log.v("PUCCI", "shearwallsNA = " + shearwallsNA.toString());
        this.shearwallsNA = shearwallsNA.toString();
    }

    public boolean getShearwallsNA() {
        if(shearwallsNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateShearwallsInstruction(String shearwallsInstruction) {
        Log.v("PUCCI", "shearwallsInstruction = " + shearwallsInstruction);
        this.shearwallsInstruction = shearwallsInstruction;
    }

    public String getShearwallsInstruction(){ return shearwallsInstruction; }

    // FRAMING ACTIVITY - BLOCKING
    public void updateBlockingReviewed(SpecialValues blockingReviewed) {
        Log.v("PUCCI", "blockingReviewed = " + blockingReviewed.toString());
        this.blockingReviewed = blockingReviewed.toString();
    }

    public boolean getBlockingReviewed() {
        if(blockingReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateBlockingNA(SpecialValues blockingNA) {
        Log.v("PUCCI", "blockingNA = " + blockingNA.toString());
        this.blockingNA = blockingNA.toString();
    }

    public boolean getBlockingNA() {
        if(blockingNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateBlockingInstruction(String blockingInstruction) {
        Log.v("PUCCI", "blockingInstruction = " + blockingInstruction);
        this.blockingInstruction = blockingInstruction;
    }

    public String getBlockingInstruction(){ return blockingInstruction; }

    // FRAMING ACTIVITY - WALL SHEATHING
    public void updateWallSheathingReviewed(SpecialValues wallSheathingReviewed) {
        Log.v("PUCCI", "wallSheathingReviewed = " + wallSheathingReviewed.toString());
        this.wallSheathingReviewed = wallSheathingReviewed.toString();
    }

    public boolean getWallSheathingReviewed() {
        if(wallSheathingReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateWallSheathingNA(SpecialValues wallSheathingNA) {
        Log.v("PUCCI", "wallSheathingNA = " + wallSheathingNA.toString());
        this.wallSheathingNA = wallSheathingNA.toString();
    }

    public boolean getWallSheathingNA() {
        if(wallSheathingNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateWallSheathingInstruction(String wallSheathingInstruction) {
        Log.v("PUCCI", "wallSheathingInstruction = " + wallSheathingInstruction);
        this.wallSheathingInstruction = wallSheathingInstruction;
    }

    public String getWallSheathingInstruction(){ return wallSheathingInstruction; }

    // FRAMING ACTIVITY - WIND GIRTS
    public void updateWindGirtsReviewed(SpecialValues windGirtsReviewed) {
        Log.v("PUCCI", "windGirtsReviewed = " + windGirtsReviewed.toString());
        this.windGirtsReviewed = windGirtsReviewed.toString();
    }

    public boolean getWindGirtsReviewed() {
        if(windGirtsReviewed.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateWindGirtsNA(SpecialValues windGirtsNA) {
        Log.v("PUCCI", "windGirtsNA = " + windGirtsNA.toString());
        this.windGirtsNA = windGirtsNA.toString();
    }

    public boolean getWindGirtsNA() {
        if(windGirtsNA.equals(SpecialValues.YES.toString())) return true;
        return false;
    }

    public void updateWindGirtsInstruction(String windGirtsInstruction) {
        Log.v("PUCCI", "windGirtsInstruction = " + windGirtsInstruction);
        this.windGirtsInstruction = windGirtsInstruction;
    }

    public String getWindGirtsInstruction(){ return windGirtsInstruction; }

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

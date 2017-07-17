package com.example.nicco.inspectionReviewManager;

import android.app.Application;

/**
 * Created by Nicco on 2017-07-17.
 */

public class Model extends Application {
    // inputs
    private String project = "";
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
//        private String footingsReview = DatabaseUtilities.NO_VALUE;
//        private String foundationWallsReview = DatabaseUtilities.NO_VALUE;
//        private String sheathingReview = DatabaseUtilities.NO_VALUE;
//        private String framingReview = DatabaseUtilities.NO_VALUE;
//        private String otherReview = DatabaseUtilities.NO_VALUE;
//        private String otherInput = DatabaseUtilities.NO_VALUE;
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
        project = "";
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

    public void updateProject(String project) {
        System.out.println("project = " + project);
        this.project = project;
    }

    public void updateProjectNumber(String projectNumber) {
        System.out.println("projectNumber = " + projectNumber);
        this.projectNumber = projectNumber;
    }

    public void updateCityProv(String cityProv) {
        System.out.println("cityProv = " + cityProv);
        this.cityProv = cityProv;
    }

    public void updateYear(String year) {
        System.out.println("year = " + year);
        this.year = year;
    }

    public void updateMonth(String month) {
        System.out.println("month = " + month);
        this.month = month;
    }

    public void updateDay(String day) {
        System.out.println("day = " + day);
        this.day = day;
    }

    public void updateHour(String hour) {
        System.out.println("hour = " + hour);
        this.hour = hour;
    }

    public void updateMinute(String minute) {
        System.out.println("minute = " + minute);
        this.minute = minute;
    }

    public void updateTimePeriod(String timePeriod) {
        System.out.println("timePeriod = " + timePeriod);
        this.timePeriod = timePeriod;
    }

    public void updateDeveloper(String developer) {
        System.out.println("developer = " + developer);
        this.developer = developer;
    }

    public void updateContractor(String contractor) {
        System.out.println("contractor = " + contractor);
        this.contractor = contractor;
    }

    public void updateWeather(String weather) {
        System.out.println("weather = " + weather);
        this.weather = weather;
    }

    public void updateTemperature(String temperature) {
        System.out.println("temperature = " + temperature);
        this.temperature = temperature;
    }
}

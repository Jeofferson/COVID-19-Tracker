package com.example.covid19tracker.PackageObjectModels;


public class Date {


    private int id;

    private String dateString;

    private int cases;
    private int deaths;
    private int recovered;


    public Date(int id, String dateString, int cases, int deaths, int recovered) {
        this.id = id;
        this.dateString = dateString;
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }


}

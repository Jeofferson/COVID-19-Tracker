package com.example.covid19tracker.PackageObjectModels;

public class Facility {


    String facilityName;
    int cases;


    public Facility(String facilityName, int cases) {
        this.facilityName = facilityName;
        this.cases = cases;
    }


    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }


}

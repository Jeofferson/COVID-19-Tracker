package com.example.covid19tracker.PackageObjectModels;

public class City {


    String cityAddress;
    int cases;


    public City(String cityAddress, int cases) {
        this.cityAddress = cityAddress;
        this.cases = cases;
    }


    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }


}

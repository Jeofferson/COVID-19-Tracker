package com.example.covid19tracker.PackageObjectModels;

import org.json.JSONArray;
import org.json.JSONObject;

public class CaseForeignNational {


    private int id;

    private String caseId;
    private int age;
    private String sex;
    private String nationality;
    private JSONObject travelDate;
    private JSONArray travelHistory;
    private String dateConfirmed;
    private String whereNow;
    private JSONObject coordinates;
    private String status;
    private JSONObject metadata;


    public CaseForeignNational(int id, String caseId, int age, String sex, String nationality, JSONObject travelDate, JSONArray travelHistory, String dateConfirmed, String whereNow, JSONObject coordinates, String status, JSONObject metadata) {
        this.id = id;
        this.caseId = caseId;
        this.age = age;
        this.sex = sex;
        this.nationality = nationality;
        this.travelDate = travelDate;
        this.travelHistory = travelHistory;
        this.dateConfirmed = dateConfirmed;
        this.whereNow = whereNow;
        this.coordinates = coordinates;
        this.status = status;
        this.metadata = metadata;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public JSONObject getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(JSONObject travelDate) {
        this.travelDate = travelDate;
    }

    public JSONArray getTravelHistory() {
        return travelHistory;
    }

    public void setTravelHistory(JSONArray travelHistory) {
        this.travelHistory = travelHistory;
    }

    public String getDateConfirmed() {
        return dateConfirmed;
    }

    public void setDateConfirmed(String dateConfirmed) {
        this.dateConfirmed = dateConfirmed;
    }

    public String getWhereNow() {
        return whereNow;
    }

    public void setWhereNow(String whereNow) {
        this.whereNow = whereNow;
    }

    public JSONObject getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(JSONObject coordinates) {
        this.coordinates = coordinates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JSONObject getMetadata() {
        return metadata;
    }

    public void setMetadata(JSONObject metadata) {
        this.metadata = metadata;
    }


}

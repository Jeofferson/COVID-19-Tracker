package com.example.covid19tracker.PackageObjectModels;

import org.json.JSONArray;
import org.json.JSONObject;

public class Case {


    private int id;

    private String caseId;
    private int age;
    private String sex;
    private String dateConfirmed;
    private JSONObject coordinates;
    private String status;
    private String nationality;
    private JSONObject residence;
    private JSONArray travelHistory;
    private JSONArray symptoms;
    private String facility;
    private String dateDeceased;
    private String dateRecovered;
    private JSONObject metadata;


    public Case(int id, String caseId, int age, String sex, String dateConfirmed, JSONObject coordinates, String status, String nationality, JSONObject residence, JSONArray travelHistory, JSONArray symptoms, String facility, String dateDeceased, String dateRecovered, JSONObject metadata) {
        this.id = id;
        this.caseId = caseId;
        this.age = age;
        this.sex = sex;
        this.dateConfirmed = dateConfirmed;
        this.coordinates = coordinates;
        this.status = status;
        this.nationality = nationality;
        this.residence = residence;
        this.travelHistory = travelHistory;
        this.symptoms = symptoms;
        this.facility = facility;
        this.dateDeceased = dateDeceased;
        this.dateRecovered = dateRecovered;
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

    public String getDateConfirmed() {
        return dateConfirmed;
    }

    public void setDateConfirmed(String dateConfirmed) {
        this.dateConfirmed = dateConfirmed;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public JSONObject getResidence() {
        return residence;
    }

    public void setResidence(JSONObject residence) {
        this.residence = residence;
    }

    public JSONArray getTravelHistory() {
        return travelHistory;
    }

    public void setTravelHistory(JSONArray travelHistory) {
        this.travelHistory = travelHistory;
    }

    public JSONArray getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(JSONArray symptoms) {
        this.symptoms = symptoms;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getDateDeceased() {
        return dateDeceased;
    }

    public void setDateDeceased(String dateDeceased) {
        this.dateDeceased = dateDeceased;
    }

    public String getDateRecovered() {
        return dateRecovered;
    }

    public void setDateRecovered(String dateRecovered) {
        this.dateRecovered = dateRecovered;
    }

    public JSONObject getMetadata() {
        return metadata;
    }

    public void setMetadata(JSONObject metadata) {
        this.metadata = metadata;
    }


}

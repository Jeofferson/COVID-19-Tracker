package com.example.covid19tracker.PackageObjectModels;

import org.json.JSONObject;

public class CaseOfw {


    private int id;

    private String caseId;
    private int age;
    private String sex;
    private String country;
    private JSONObject coordinates;
    private String dateReported;
    private String dateConfirmed;
    private String status;
    private String remarks;
    private JSONObject metadata;


    public CaseOfw(int id, String caseId, int age, String sex, String country, JSONObject coordinates, String dateReported, String dateConfirmed, String status, String remarks, JSONObject metadata) {
        this.id = id;
        this.caseId = caseId;
        this.age = age;
        this.sex = sex;
        this.country = country;
        this.coordinates = coordinates;
        this.dateReported = dateReported;
        this.dateConfirmed = dateConfirmed;
        this.status = status;
        this.remarks = remarks;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public JSONObject getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(JSONObject coordinates) {
        this.coordinates = coordinates;
    }

    public String getDateReported() {
        return dateReported;
    }

    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    public String getDateConfirmed() {
        return dateConfirmed;
    }

    public void setDateConfirmed(String dateConfirmed) {
        this.dateConfirmed = dateConfirmed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public JSONObject getMetadata() {
        return metadata;
    }

    public void setMetadata(JSONObject metadata) {
        this.metadata = metadata;
    }


}

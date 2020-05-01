package com.example.covid19tracker.PackageObjectModels;

public class MoreInformationItem {


    String title;
    String message;

    boolean isExpanded;


    public MoreInformationItem(String title, String message, boolean isExpanded) {
        this.title = title;
        this.message = message;
        this.isExpanded = isExpanded;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }


}

package com.example.communatio;

public class Events1 {
    String eventname="",eventtype="",fee="",collegename="",UriImage="",uniqueid="";
    public Events1(String eventtype, String eventname, String fee, String collegename, String uriImage,String uniqueid) {
        this.eventtype = eventtype;
        this.eventname = eventname;
        this.fee = fee;
        this.collegename = collegename;
        UriImage = uriImage;
        this.uniqueid=uniqueid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getUriImage() {
        return UriImage;
    }

    public void setUriImage(String uriImage) {
        UriImage = uriImage;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }
}

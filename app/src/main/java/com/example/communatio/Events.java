package com.example.communatio;

public class Events {
    String eventtype,eventname,fee,lastdate,address,dateofevent,uid,collegename,information,UriImage,uniqueid;

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

    public Events(String eventtype, String eventname, String fee, String collegename, String uriImage,String uniqueid) {
        this.eventtype = eventtype;
        this.eventname = eventname;
        this.fee = fee;
        this.collegename = collegename;
        UriImage = uriImage;
        this.uniqueid=uniqueid;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public Events(String eventtype, String eventname, String fee, String lastdate, String address, String dateofevent, String uid, String collegename, String information, String UriImage) {
        this.eventtype = eventtype;
        this.eventname = eventname;
        this.fee = fee;
        this.lastdate = lastdate;
        this.address = address;
        this.dateofevent = dateofevent;
        this.collegename=collegename;
        this.information=information;
        this.uid = uid;
        this.UriImage=UriImage;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateofevent() {
        return dateofevent;
    }

    public void setDateofevent(String dateofevent) {
        this.dateofevent = dateofevent;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

package com.beans;


import java.util.Date;

public class WorkingHour {


    private String identity;
    private Date enterance;
    private Date exit;
    private String user;

    public WorkingHour() {
    }

    public WorkingHour(String id, Date enterance, Date exit, String user) {
        this.identity = id;
        this.enterance = enterance;
        this.exit = exit;
        this.user = user;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setEnterance(Date enterance) {
        this.enterance = enterance;
    }

    public void setExit(Date exit) {
        this.exit = exit;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIdentity() {
        return identity;
    }

    public Date getEnterance() {
        return enterance;
    }

    public Date getExit() {
        return exit;
    }

    public String getUser() {
        return user;
    }

}

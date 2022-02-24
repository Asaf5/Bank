package com.beans;


import java.sql.Timestamp;
import java.util.Date;

public class WorkingHour {


    private int identity;
    private Timestamp enterance;
    private Timestamp exit;
    private String user;
    private String user22;
    private String user23332;
    private String asaf;




    public WorkingHour() {
    }

    public WorkingHour(int id, Timestamp enterance, Timestamp exit, String user) {
        this.identity = id;
        this.enterance = enterance;
        this.exit = exit;
        this.user = user;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public void setEnterance(Timestamp enterance) {
        this.enterance = enterance;
    }

    public void setExit(Timestamp exit) {
        this.exit = exit;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getIdentity() {
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

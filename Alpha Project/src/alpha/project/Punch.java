/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.project;

import java.util.GregorianCalendar;

/**
 *
 * @author Adam 
 * Last updated: 10/4/18
 */
public class Punch {
    private int id = 0;
    private int terminalid;
    private String badgeid;
    private GregorianCalendar originaltime = new GregorianCalendar();
    private GregorianCalendar adjustedtime = null;
    private int punchtypeid;
    public Punch(Badge badge, int terminalid, int punchtypeid) {
        badgeid = badge.getId();
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
    }
    public String printOriginalTimestamp() {
        String msg;
        switch (getPunchtypeid()) {
            case 1:
                msg = "#" + badgeid + "CLOCKED IN: " + originaltime.getTime().toString();
                break;
            case 0:
                msg = "#" + badgeid + "CLOCKED OUT: " + originaltime.getTime().toString();
                break;
            default:
                msg = "#" + badgeid + "TIMED OUT: " + originaltime.getTime().toString();
                break;
        }
        
        return msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(int terminalid) {
        this.terminalid = terminalid;
    }

    public String getBadgeid() {
        return badgeid;
    }

    public void setBadgeid(String badgeid) {
        this.badgeid = badgeid;
    }

    public GregorianCalendar getOriginaltime() {
        return originaltime;
    }

    public void setOriginaltime(GregorianCalendar originaltime) {
        this.originaltime = originaltime;
    }

    public GregorianCalendar getAdjustedtime() {
        return adjustedtime;
    }

    public void setAdjustedtime(GregorianCalendar adjustedtime) {
        this.adjustedtime = adjustedtime;
    }

    public int getPunchtypeid() {
        return punchtypeid;
    }

    public void setPunchtypeid(int punchtypeid) {
        this.punchtypeid = punchtypeid;
    }

}
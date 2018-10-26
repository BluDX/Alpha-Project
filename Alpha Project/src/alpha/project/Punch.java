/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.project;
import java.util.GregorianCalendar;
import java.time.format.DateTimeFormatter;

/**
 * take each punch (date and time) and create copies of it. copies of timestamps initialized to timeline. 
 * 10 timestamp objects. 
 * 
 * take punch and create multiple copies that represent every point on the timeline; shiftstart, shiftstop, graceperiod, etc.
 * wherever the punch falls, you can snap it to one of those other positions. helpful for accrued hours.
 * 
 * @author pinoran, Adam, Amber
 * Last updated: 10/22
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
    
    // Feature 3 stuff
    
    /*public void adjust (Shift s) {
        
        // bounds for the timeline; get time in millis
        
        
        GregorianCalendar beforeShift = new GregorianCalendar();
        beforeShift.setTimeInMillis(s.getShiftStart().getTime());
        // beforeShift.add(Calendar.MINUTE, -(s.getInterval()));
        
        // not working because int can't be dereferenced; not sure how to get it other than changing to time
        GregorianCalendar beforeStartGrace = new GregorianCalendar();
        beforeStartGrace.setTimeInMillis(s.getGracePeriod().getTime());
        
        
        GregorianCalendar afterStartDock = new GregorianCalendar();
        
        GregorianCalendar beforeStopDock = new GregorianCalendar();
  
        GregorianCalendar beforeStopGrace = new GregorianCalendar();
        
        GregorianCalendar afterShift = new GregorianCalendar();
        afterShift.setTimeInMillis(s.getShiftStop().getTime());
    }
    
   */
    
    public String printOriginalTimestamp() {
        String msg;
        switch (getPunchtypeid()) {
            case 1:
                msg = "#" + badgeid + " CLOCKED IN: " + originaltime.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
              
                break;
            case 0:
                msg = "#" + badgeid + " CLOCKED OUT: " + originaltime.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
                break;
            default:
                msg = "#" + badgeid + " TIMED OUT: " + originaltime.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
                break;
        }
        
        return msg.toUpperCase();
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

    public long getOriginaltimestamp() {
        return originaltime.getTimeInMillis();
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
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
 * ranged checking with edge cases. loo kat the position of a punch on a timeline and determine based on the range in which it falls how to adjust it.
 * start of shit - interval is shift start range. if you clock in within that range, your punch gets moved forward of back
 * clock start moved back, lunch stop moved forward
 * 7 time zones
 * rounding only done when leaving early? clock out
 * 
 * 
 * take original timestamp of the punch; includes date and time components, collapse it to long integer and then use that to seed different timestamp objects corresponding to each point
 * 10 timestamp objects
 * set time fields within them according to whatever shift rules say they should be
 * 1) shift start - interval
 * !!!2) start of the shift (ex. 7AM)
 * 3) shift start + grace period
 * 4) shift start + dock penalty
 * 5) lunch start (ex: 12:00)
 * 6) lunch stop (ex: 12:30)
 * 7) shift stop - dock penalty
 * 8) shift stop - grace
 * !!! 9) shift stop
 * 10) shift stop + interval
 * 
 * !!! relative to one another
 * copy timestamp, go into hms fields, setting them
 * don't wanna hardcode
 * punch.setAdjustedTimestamp(lunchStart.getTimeInMillis();
 * create timestamps, do comparison and check edge cases
 * 
 * 
 * GregorianCalendar shiftstart = new gregorianCalendar();
 * shiftstart.setTimeInMillis(punch.getOriginalTimestamp();
 * shiftstart.set(
 * 
 * shiftstart, shiftstop, lunchstart, lunchstop
 * 
 * @author pinoran, Adam, Amber
 * Last updated: 10/22
 */
public class Punch {
    private int id = 0;
    private int terminalid;
    private String badgeid;
    private long originaltime;
    private long adjustedtime;
    private int punchtypeid;
    public Punch(Badge badge, int terminalid, int punchtypeid) {
        badgeid = badge.getId();
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
        this.originaltime = (new GregorianCalendar()).getTimeInMillis();
        this.adjustedtime = 0;
    }
    
    // Feature 3 stuff
    
    /*public void adjust (Shift s) {
        
        
        GregorianCalendar shiftStart = new GregorianCalendar();
        shiftStart.setTimeInMillis(punch.getOriginalTimestamp());
        shiftStart.set(Calendar.HOUR, shift.getShiftStartHour());
        shiftStart.set(Calendar.MINUTE, shift.getShiftStartMinute());
        shiftStart.set(Calendar.SECOND, shift.getShiftStartSecond());
    
        GregorianCalendar shiftStart = new GregorianCalendar();
        shiftStop.setTimeInMillis(punch.getOriginalTimestamp());
        shiftStop.set(Calendar.HOUR, shift.getShiftStopHour());
        shiftStop.set(Calendar.MINUTE, shift.getShiftStopMinute());
        shiftStop.set(Calendar.SECOND, shift.getShiftStopSecond());
    
        GregorianCalendar LunchStart = new GregorianCalendar();
        LunchStart.setTimeInMillis(punch.getOriginalTimestamp());
        LunchStart.set(Calendar.HOUR, shift.getLunchStartHour());
        LunchStart.set(Calendar.MINUTE, shift.getLunchStartMinute());
        LunchStart.set(Calendar.SECOND, shift.getLunchStartSecond());
   
        GregorianCalendar LunchStart = new GregorianCalendar();
        lunchStop.setTimeInMillis(punch.getOriginalTimestamp());
        lunchStop.set(Calendar.HOUR, shift.getLunchStopHour());
        lunchStop.set(Calendar.MINUTE, shift.getLunchStopMinute());
        lunchStop.set(Calendar.SECOND, shift.getLunchStopSecond());
    
    
    
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
        GregorianCalendar ots = new GregorianCalendar();
        ots.setTimeInMillis(originaltime);
        String msg;
        switch (getPunchtypeid()) {
            case 1:
                msg = "#" + badgeid + " CLOCKED IN: " + ots.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
              
                break;
            case 0:
                msg = "#" + badgeid + " CLOCKED OUT: " + ots.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
                break;
            default:
                msg = "#" + badgeid + " TIMED OUT: " + ots.toZonedDateTime().format(DateTimeFormatter.ofPattern( "E MM/dd/uuuu HH:mm:ss" ));
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
        return originaltime;
    }

    public void setOriginaltime(long originaltime) {
        this.originaltime = originaltime;
    }



    public int getPunchtypeid() {
        return punchtypeid;
    }

    public void setPunchtypeid(int punchtypeid) {
        this.punchtypeid = punchtypeid;
    }
    
    
    
    

}
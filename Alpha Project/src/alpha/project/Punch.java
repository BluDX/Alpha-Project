/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.project;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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
    private boolean lunchFlag = false;
    private String ruleInvoked = "None";
    public Punch(Badge badge, int terminalid, int punchtypeid) {
        badgeid = badge.getId();
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
        this.originaltime = (new GregorianCalendar()).getTimeInMillis();
        this.adjustedtime = 0;
    }
    
    // Feature 3 stuff
    
    public void adjust (Shift shift) {
        
        GregorianCalendar shiftStart = new GregorianCalendar();
        shiftStart.setTimeInMillis(this.originaltime);
        shiftStart.set(Calendar.HOUR_OF_DAY, shift.getShiftStartHour());
        shiftStart.set(Calendar.MINUTE, shift.getShiftStartMinute());
        shiftStart.set(Calendar.SECOND, 0);
    
        GregorianCalendar shiftStop = new GregorianCalendar();
        shiftStop.setTimeInMillis(this.originaltime);
        shiftStop.set(Calendar.HOUR_OF_DAY, shift.getShiftStopHour());
        shiftStop.set(Calendar.MINUTE, shift.getShiftStopMinute());
        shiftStop.set(Calendar.SECOND, 0);
    
        GregorianCalendar LunchStart = new GregorianCalendar();
        LunchStart.setTimeInMillis(this.originaltime);
        LunchStart.set(Calendar.HOUR_OF_DAY, shift.getLunchStartHour());
        LunchStart.set(Calendar.MINUTE, shift.getLunchStartMinute());
        LunchStart.set(Calendar.SECOND, 0);
   
        GregorianCalendar lunchStop = new GregorianCalendar();
        lunchStop.setTimeInMillis(this.originaltime);
        lunchStop.set(Calendar.HOUR_OF_DAY, shift.getLunchStopHour());
        lunchStop.set(Calendar.MINUTE, shift.getLunchStopMinute());
        lunchStop.set(Calendar.SECOND, 0);
    
        GregorianCalendar startInterval = new GregorianCalendar();
        startInterval.setTimeInMillis(shiftStart.getTimeInMillis());
        startInterval.add(Calendar.MINUTE, -shift.getInterval());
        
        GregorianCalendar startGrace = new GregorianCalendar();
        startGrace.setTimeInMillis(shiftStart.getTimeInMillis());
        startGrace.add(Calendar.MINUTE, shift.getGracePeriod());
        
        GregorianCalendar startDock = new GregorianCalendar();
        startDock.setTimeInMillis(shiftStart.getTimeInMillis());
        startDock.add(Calendar.MINUTE, shift.getDock());
        
        GregorianCalendar stopDock = new GregorianCalendar();
        stopDock.setTimeInMillis(shiftStop.getTimeInMillis());
        stopDock.add(Calendar.MINUTE, -shift.getDock());
        
        GregorianCalendar stopGrace = new GregorianCalendar();
        stopGrace.setTimeInMillis(shiftStop.getTimeInMillis());
        stopGrace.add(Calendar.MINUTE, -shift.getGracePeriod());
        
        GregorianCalendar stopInterval = new GregorianCalendar();
        stopInterval.setTimeInMillis(shiftStop.getTimeInMillis());
        stopInterval.add(Calendar.MINUTE, shift.getInterval());
        
        SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy HH:mm:ss");
        
        System.out.println("shiftStart: " + shiftStart.getTimeInMillis() + " " + sdf.format(shiftStart.getTime())); 
        System.out.println("shiftStop: " + shiftStop.getTimeInMillis() + " " + sdf.format(shiftStop.getTime())); 
        System.out.println("LunchStart: " + LunchStart.getTimeInMillis() + " " + sdf.format(LunchStart.getTime())); 
        System.out.println("lunchStop: " + lunchStop.getTimeInMillis() + " " + sdf.format(lunchStop.getTime()));
        System.out.println("startInterval: " + startInterval.getTimeInMillis() + " " + sdf.format(startInterval.getTime())); 
        System.out.println("startGrace: " + startGrace.getTimeInMillis() + " " + sdf.format(startGrace.getTime())); 
        System.out.println("startDock: " + startDock.getTimeInMillis() + " " + sdf.format(startDock.getTime())); 
        System.out.println("stopDock: " + stopDock.getTimeInMillis() + " " + sdf.format(stopDock.getTime())); 
        System.out.println("stopGrace: " + stopGrace.getTimeInMillis() + " " + sdf.format(stopGrace.getTime())); 
        System.out.println("stopInterval: " + stopInterval.getTimeInMillis() + " " + sdf.format(stopInterval.getTime())); 
        
        
        GregorianCalendar g = new GregorianCalendar();
        g.setTimeInMillis(this.originaltime);
        g.set(Calendar.SECOND, 0);
        long punchTime = g.getTimeInMillis();
        System.out.println("punchTime: " + punchTime + " " + sdf.format(g.getTime())); 
        System.out.println();
        // I think I should be using a setter to change the punch's adjusted timestamp but that setter doesn't exist. I think Snellen told me to delete it. Maybe another way??
        // for now just changing punchTime will do. Easy enough fix later.
        if ( g.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || g.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ) {
            //Move to the nearest 15 minute interval.
            int minuteValue = g.get(Calendar.MINUTE);
            if ( minuteValue % shift.getInterval() == 0 ){
                this.ruleInvoked = "None"; 
            }  
            else if ( minuteValue % shift.getInterval() >= shift.getInterval()/2 ) {
                g.set(Calendar.MINUTE, minuteValue + ( shift.getInterval() - minuteValue % shift.getInterval() ));  
                this.ruleInvoked = "Interval Round";
            }
            else {
                g.set(Calendar.MINUTE, minuteValue - minuteValue % shift.getInterval());
                this.ruleInvoked = "Interval Round";
            }
            
            
            punchTime = g.getTimeInMillis();
        }
        else {

            if ( (punchTime <= shiftStart.getTimeInMillis()) && (punchTime >= startInterval.getTimeInMillis()) ) { // punch is greater than the interval and less than start time: it is snapped to shift start.
                punchTime = shiftStart.getTimeInMillis();
                this.ruleInvoked = "Shift Start";
            }
            else if ( (punchTime >= shiftStart.getTimeInMillis()) && (punchTime <= startGrace.getTimeInMillis()) ) { // punch is greater than start time but less than the grace period. Snap to start time.
                punchTime = shiftStart.getTimeInMillis();
                this.ruleInvoked = "Shift Start";
            }
            else if ( (punchTime >= startGrace.getTimeInMillis()) && (punchTime <= startDock.getTimeInMillis()) ) { // punch is after the grace period and before the dock. Punch is shifted to the dock time.
                punchTime = startDock.getTimeInMillis();
                this.ruleInvoked = "Shift Dock";
            }
            else if ( (punchTime >= LunchStart.getTimeInMillis()) && (punchTime <= lunchStop.getTimeInMillis()) && this.punchtypeid == 0 ) {  
                punchTime = LunchStart.getTimeInMillis();
                lunchFlag = true;
                this.ruleInvoked = "Lunch Start";
            }
            else if ( (punchTime <= lunchStop.getTimeInMillis()) && (punchTime >= LunchStart.getTimeInMillis()) && this.punchtypeid == 1 ) { 
                punchTime = lunchStop.getTimeInMillis();
                lunchFlag = true;
                this.ruleInvoked = "Lunch Stop";
            }
            else if ( (punchTime <= stopGrace.getTimeInMillis()) && (punchTime >= stopDock.getTimeInMillis()) ) { // punch is less than the grace period for clocking out and is also bigger than the stop dock. Punch is adjusted to the dock 
                punchTime = stopDock.getTimeInMillis();
                this.ruleInvoked = "Shift Dock";
            }
            else if ( (punchTime >= stopGrace.getTimeInMillis()) && (punchTime <= shiftStop.getTimeInMillis()) ) { // punch is greater than stop grace and less than shift stop. Adjust to the end of the shift.
                punchTime = shiftStop.getTimeInMillis();
                this.ruleInvoked = "Shift Stop";
            }
            else if ( (punchTime >= shiftStop.getTimeInMillis()) && (punchTime <= stopInterval.getTimeInMillis()) ) { // punch is after the end of shift but before the stop interval. Move it to the end of the shift.
                punchTime = shiftStop.getTimeInMillis();
                this.ruleInvoked = "Shift Stop";
            }
            else {

                //Move to the nearest 15 minute interval.
                int minuteValue = g.get(Calendar.MINUTE);
                if ( minuteValue % shift.getInterval() == 0 ){
                    this.ruleInvoked = "None";
                }  
                else if ( minuteValue % shift.getInterval() >= shift.getInterval()/2 ) { //this rounds up when it is 7, but shouldn't it round down??
                    g.set(Calendar.MINUTE, minuteValue + ( shift.getInterval() - minuteValue % shift.getInterval() ));  
                    this.ruleInvoked = "Interval Round";
                }
                else {
                    g.set(Calendar.MINUTE, minuteValue - minuteValue % shift.getInterval());
                    this.ruleInvoked = "Interval Round";
                }
                

                punchTime = g.getTimeInMillis();
            }
        }
        this.adjustedtime = punchTime;
    }
    
    
    // interval rounding stuff
    /*
    int punchTime = original.get(Calendar.MINUTE);
    int diffmins = punchTime;
    
    
    unroundedTime = getOriginalTimeStamp().get(Calendar.MINUTE);
    modTime = unroundedTime % shift.getInterval();
    
    //wat
    
    
    */
    
    
    public String printOriginalTimestamp() {
        GregorianCalendar ots = new GregorianCalendar();
        ots.setTimeInMillis(originaltime);
        String msg;
        SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy HH:mm:ss");
        switch (getPunchtypeid()) {
            case 1:
                msg = "#" + badgeid + " CLOCKED IN: " + sdf.format(ots.getTime());
              
                break;
            case 0:
                msg = "#" + badgeid + " CLOCKED OUT: " + sdf.format(ots.getTime());
                break;
            default:
                msg = "#" + badgeid + " TIMED OUT: " + sdf.format(ots.getTime());
                break;
        }
        
        msg = msg.toUpperCase();
        return msg;
    }
    
    public String printAdjustedTimestamp() {
        GregorianCalendar ats = new GregorianCalendar();
        ats.setTimeInMillis(adjustedtime);
        String msg;
        SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy HH:mm:ss");
        switch (getPunchtypeid()) {
            case 1:
                msg = "#" + badgeid + " CLOCKED IN: " + sdf.format(ats.getTime());
              
                break;
            case 0:
                msg = "#" + badgeid + " CLOCKED OUT: " + sdf.format(ats.getTime());
                break;
            default:
                msg = "#" + badgeid + " TIMED OUT: " + sdf.format(ats.getTime());
                break;
        }
        
        msg = msg.toUpperCase();
        msg += " (" + this.ruleInvoked + ")";
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
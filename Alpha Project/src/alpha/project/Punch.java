
package alpha.project;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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

        
        GregorianCalendar g = new GregorianCalendar();
        g.setTimeInMillis(this.originaltime);
        g.set(Calendar.SECOND, 0);
        long punchTime = g.getTimeInMillis();
        
        if ( (punchTime <= shiftStart.getTimeInMillis()) && (punchTime >= startInterval.getTimeInMillis()) && !(g.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || g.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) { // punch is greater than the interval and less than start time: it is snapped to shift start.
            punchTime = shiftStart.getTimeInMillis();
            this.ruleInvoked = "Shift Start";
        }
        else if ( (punchTime >= shiftStart.getTimeInMillis()) && (punchTime <= startGrace.getTimeInMillis()) && !(g.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || g.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) { // punch is greater than start time but less than the grace period. Snap to start time.
            punchTime = shiftStart.getTimeInMillis();
            this.ruleInvoked = "Shift Start";
        }
        else if ( (punchTime >= startGrace.getTimeInMillis()) && (punchTime <= startDock.getTimeInMillis()) && !(g.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || g.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) { // punch is after the grace period and before the dock. Punch is shifted to the dock time.
            punchTime = startDock.getTimeInMillis();
            this.ruleInvoked = "Shift Dock";
        }
        else if ( (punchTime >= LunchStart.getTimeInMillis()) && (punchTime <= lunchStop.getTimeInMillis()) && this.punchtypeid == 0 && !(g.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || g.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {  
            punchTime = LunchStart.getTimeInMillis();
            lunchFlag = true;
            this.ruleInvoked = "Lunch Start";
        }
        else if ( (punchTime <= lunchStop.getTimeInMillis()) && (punchTime >= LunchStart.getTimeInMillis()) && this.punchtypeid == 1 && !(g.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || g.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) { 
            punchTime = lunchStop.getTimeInMillis();
            lunchFlag = true;
            this.ruleInvoked = "Lunch Stop";
        }
        else if ( (punchTime <= stopGrace.getTimeInMillis()) && (punchTime >= stopDock.getTimeInMillis()) && !(g.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || g.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) { // punch is less than the grace period for clocking out and is also bigger than the stop dock. Punch is adjusted to the dock 
            punchTime = stopDock.getTimeInMillis();
            this.ruleInvoked = "Shift Dock";
        }
        else if ( (punchTime >= stopGrace.getTimeInMillis()) && (punchTime <= shiftStop.getTimeInMillis()) && !(g.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || g.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) { // punch is greater than stop grace and less than shift stop. Adjust to the end of the shift.
            punchTime = shiftStop.getTimeInMillis();
            this.ruleInvoked = "Shift Stop";
        }
        else if ( (punchTime >= shiftStop.getTimeInMillis()) && (punchTime <= stopInterval.getTimeInMillis()) && !(g.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || g.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) { // punch is after the end of shift but before the stop interval. Move it to the end of the shift.
            punchTime = shiftStop.getTimeInMillis();
            this.ruleInvoked = "Shift Stop";
        }
        else {

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

        this.adjustedtime = punchTime;
    }
    
   
    
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
    
    public long getAdjustedtimestamp() {
        return adjustedtime;
    }
    public void setOriginaltime(long originaltime) {
        this.originaltime = originaltime;
    }
    public String getPunchData() {
        return this.ruleInvoked;
    }


    public int getPunchtypeid() {
        return punchtypeid;
    }

    public void setPunchtypeid(int punchtypeid) {
        this.punchtypeid = punchtypeid;
    }
    
    
    
    

}
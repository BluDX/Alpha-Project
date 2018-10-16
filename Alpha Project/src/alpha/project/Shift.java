package alpha.project;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
* @author Amber
* timestamp fields used for punch date and time, time fields for shift/lunch start/stop
* UNIX_TIMESTAMP
* value returned by MySQL should be multiplied by 1000
* TODO: toString, gregorian calendar conversion
* need to create calendar object for all longs?
* description, shiftStart, shiftStop, interval, graceperiod, dock, lunchstart, lunchstop, lunchdeduct
* added: lunchLength, shiftLength, shiftID
* lunch/shiftlength replaced with elapsed time
* 
* Snellen badge example:
* String id = resultset.getString();
* String desc = resultset.getstring(2);
* Badge b = new Badge (id, desc)
*/  

public class Shift {
    private int id, interval, gracePeriod, dock, lunchDeduct;
    private String description;
    private Timestamp shiftStart, shiftStop, lunchStart, lunchStop;
    
    
    
    public Shift(int id, int interval, int gracePeriod, int dock, int lunchDeduct, String description, Timestamp start, Timestamp stop, Timestamp lunchStart, Timestamp lunchStop){
        this.id = id;
        this.interval = interval;
        this.gracePeriod = gracePeriod;
        this.dock = dock;
        this.lunchDeduct = lunchDeduct;
        this.description = description;
        this.shiftStart = start;
        this.shiftStop = stop;
        this.lunchStart = lunchStart;
        this.lunchStop = lunchStop;
    }
    
    // getters and setters

    public int getId() {
        return id;
    }

    public int getInterval() {
        return interval;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public int getDock() {
        return dock;
    }

    public int getLunchDeduct() {
        return lunchDeduct;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getShiftStart() {
        return shiftStart;
    }

    public Timestamp getShiftStop() {
        return shiftStop;
    }

    public Timestamp getLunchStart() {
        return lunchStart;
    }

    public Timestamp getLunchStop() {
        return lunchStop;
    }
    
    // long cannot be converted to greg calendar; should be string
    // 60 * 1000
    
    // get times
    private long getElapsedTime(Timestamp s, Timestamp e){
        Calendar startCal = GregorianCalendar.getInstance();
        Calendar endCal = GregorianCalendar.getInstance();
        startCal.setTimeInMillis(s.getTime());
        endCal.setTimeInMillis(e.getTime());
        
        // calculate times for lunch and shift... / 60 * 1000 to convert
        long start, end;
        start = startCal.getTimeInMillis();
        end = endCal.getTimeInMillis();
        return (end - start) / (60 * 1000);
    }

    //formatting for output
    
    @Override
    public String toString() {
        
        // Shift 1: 07:00 - 15:30 (510 minutes); Lunch: 12:00 - 12:30 (30 minutes)
        // Pretty cluttered - might use different method
        String data = "";
        String startTime = (new SimpleDateFormat("HH:mm")).format(shiftStart.getTime());
        String stopTime = (new SimpleDateFormat("HH:mm")).format(shiftStop.getTime());
        String lunchStartTime = (new SimpleDateFormat("HH:mm")).format(lunchStart.getTime());
        String lunchStopTime = (new SimpleDateFormat("HH:mm")).format(lunchStop.getTime());
        
        data += description + ": ";
        data += startTime + " - ";
        data += stopTime + " (";
        data += getElapsedTime(shiftStart, shiftStop) + " minutes);";
        data += " Lunch: " + lunchStartTime + " - ";
        data += lunchStopTime + " (";
        data += getElapsedTime(lunchStart, lunchStop) + " minutes)";
        return data;
    }
}
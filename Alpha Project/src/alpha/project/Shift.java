package alpha.project;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;

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
* 
* gc.setTimeInMillis(ts);
* SimpleDateFormat
* Date d = gc.getTime(); > would then be an input for sdf;
* sdf.format(d);
* 
* change timestamps to localTime; isBefore and isAfter (compare local times to see which occurs before or after). ß
*/  

public class Shift {
    private int id, interval, gracePeriod, dock, lunchDeduct;
    private String description;
    private Time shiftStart, shiftStop, lunchStart, lunchStop;
    
    
    
    public Shift(int id, int interval, int gracePeriod, int dock, int lunchDeduct, String description, Time start, Time stop, Time lunchStart, Time lunchStop){
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

    public Time getShiftStart() {
        return shiftStart;
    }

    public Time getShiftStop() {
        return shiftStop;
    }

    public Time getLunchStart() {
        return lunchStart;
    }

    public Time getLunchStop() {
        return lunchStop;
    }
    
    public int getShiftStartHour() {
        GregorianCalendar g = new GregorianCalendar();
        g.setTimeInMillis(shiftStart.getTime());
        return g.get(Calendar.HOUR_OF_DAY);
    }
    
    public int getShiftStartMinute() {
        GregorianCalendar g = new GregorianCalendar();
        g.setTimeInMillis(shiftStart.getTime());
        return g.get(Calendar.MINUTE);
    }
    
    public int getShiftStopHour() {
        GregorianCalendar g = new GregorianCalendar();
        g.setTimeInMillis(shiftStop.getTime());
        return g.get(Calendar.HOUR_OF_DAY);
    }
    
    public int getShiftStopMinute() {
        GregorianCalendar g = new GregorianCalendar();
        g.setTimeInMillis(shiftStop.getTime());
        return g.get(Calendar.MINUTE);
    }
    
    public int getLunchStartHour() {
        GregorianCalendar g = new GregorianCalendar();
        g.setTimeInMillis(lunchStart.getTime());
        return g.get(Calendar.HOUR_OF_DAY);
    }
    
    public int getLunchStartMinute() {
        GregorianCalendar g = new GregorianCalendar();
        g.setTimeInMillis(lunchStart.getTime());
        return g.get(Calendar.MINUTE);
    }
    
    public int getLunchStopHour() {
        GregorianCalendar g = new GregorianCalendar();
        g.setTimeInMillis(lunchStop.getTime());
        return g.get(Calendar.HOUR_OF_DAY);
    }
    
    public int getLunchStopMinute() {
        GregorianCalendar g = new GregorianCalendar();
        g.setTimeInMillis(lunchStop.getTime());
        return g.get(Calendar.MINUTE);
    }
    // long cannot be converted to greg calendar; should be string
    // 60 * 1000
    
    // get times
    private long getElapsedTime(Time s, Time e){
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

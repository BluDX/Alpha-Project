package alpha.project;

/**
 *
* @author Amber
* timestamp fields used for punch date and time, time fields for shift/lunch start/stop
* UNIX_TIMESTAMP
* value returned by MySQL should be multiplied by 1000
* TODO: toString, gregorian calendar conversion
* need to create calendar object for all longs?
* description, shiftstart, shiftstop, interval, graceperiod, dock, lunchstart, lunchstop, lunchdeduct
* added: lunchLength, shiftLength, shiftID
* lunch/shiftlength replaced with elapsed time
*/  



import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Shift {
    private int id, interval, gracePeriod, dock, lunchDeduct;
    private String description;
    private Timestamp start, stop, lunchStart, lunchStop;
    
    public Shift(int id, int interval, int gracePeriod, int dock, int lunchDeduct, int maxTime, int overtimeThreshold,
                 String description, Timestamp start, Timestamp stop, Timestamp lunchStart, Timestamp lunchStop){
        this.id = id;
        this.interval = interval;
        this.gracePeriod = gracePeriod;
        this.dock = dock;
        this.lunchDeduct = lunchDeduct;
        this.description = description;
        this.start = start;
        this.stop = stop;
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

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getStop() {
        return stop;
    }

    public Timestamp getLunchStart() {
        return lunchStart;
    }

    public Timestamp getLunchStop() {
        return lunchStop;
    }
    
    // long cannot be converted to greg calendar; should be string
    // 60 * 1000
    
    private long getElapsedTime(Timestamp s, Timestamp e){
        Calendar startCal = GregorianCalendar.getInstance();
        Calendar endCal = GregorianCalendar.getInstance();
        startCal.setTimeInMillis(s.getTime());
        endCal.setTimeInMillis(e.getTime());
        long initial, ending;
        initial = startCal.getTimeInMillis();
        ending = endCal.getTimeInMillis();
        return (ending - initial)/60000;
    }

    @Override
    public String toString() {
        String data = "";
        String startTime = (new SimpleDateFormat("HH:mm")).format(start.getTime());
        String stopTime = (new SimpleDateFormat("HH:mm")).format(stop.getTime());
        String lunchStartTime = (new SimpleDateFormat("HH:mm")).format(lunchStart.getTime());
        String lunchStopTime = (new SimpleDateFormat("HH:mm")).format(lunchStop.getTime());
        data += description + ": ";
        data += startTime + " - ";
        data += stopTime + " (";
        data += getElapsedTime(start, stop) + " minutes);";
        data += " Lunch: " + lunchStartTime + " - ";
        data += lunchStopTime + " (";
        data += getElapsedTime(lunchStart, lunchStop) + " minutes)";
        return data;
    }
}
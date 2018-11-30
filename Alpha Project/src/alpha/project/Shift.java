package alpha.project;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;


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

    private long getElapsedTime(Time s, Time e){
        Calendar startCal = GregorianCalendar.getInstance();
        Calendar endCal = GregorianCalendar.getInstance();
        startCal.setTimeInMillis(s.getTime());
        endCal.setTimeInMillis(e.getTime());
        
        long start, end;
        start = startCal.getTimeInMillis();
        end = endCal.getTimeInMillis();
        return (end - start) / (60 * 1000);
    }

    @Override
    public String toString() {
        

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

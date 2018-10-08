/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.project;

/**
 *
 * @author Amber
 *  toString
 * description, shiftstart, shiftstop, interval, graceperiod, dock, lunchstart, lunchstop, lunchdeduct > convert gregorian calendar. "constructor that accepts all the thingies" - Adam
 */  

public class Shift {
    
    String shiftDesc;
    long shiftStart;
    long shiftStop;
    int interval;
    int gracePeriod;
    int dock;
    long lunchStart;
    long lunchStop;
    int lunchDeduct;
    
    // Getters and setters
    public String getShiftDesc() {
        return shiftDesc;
    }

    public void setShiftDesc(String shiftDesc) {
        this.shiftDesc = shiftDesc;
    }

    // shiftStart
    public long getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(long shiftStart) {
        this.shiftStart = shiftStart;
    }

    // shiftStop
    public long getShiftStop() {
        return shiftStop;
    }

    public void setShiftStop(long shiftStop) {
        this.shiftStop = shiftStop;
    }

    // interval
    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    //gracePeriod
    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    // dock
    public int getDock() {
        return dock;
    }

    public void setDock(int dock) {
        this.dock = dock;
    }

    //lunchStart
    public long getLunchStart() {
        return lunchStart;
    }

    public void setLunchStart(long lunchStart) {
        this.lunchStart = lunchStart;
    }

    // lunchStop
    public long getLunchStop() {
        return lunchStop;
    }

    public void setLunchStop(long lunchStop) {
        this.lunchStop = lunchStop;
    }

    // lunchDeduct
    public int getLunchDeduct() {
        return lunchDeduct;
    }

    public void setLunchDeduct(int lunchDeduct) {
        this.lunchDeduct = lunchDeduct;
    }

    public Shift(String shiftDesc, long shiftStart, long shiftStop, int interval, int gracePeriod, int dock, long lunchStart, long lunchStop, int lunchDeduct) {
        this.shiftDesc = shiftDesc;
        this.shiftStart = shiftStart;
        this.shiftStop = shiftStop;
        this.interval = interval;
        this.gracePeriod = gracePeriod;
        this.dock = dock;
        this.lunchStart = lunchStart;
        this.lunchStop = lunchStop;
        this.lunchDeduct = lunchDeduct;
    }

    
    
}


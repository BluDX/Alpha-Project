/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.project;

/**
 *
 * @author Amber
 * description, shiftstart, shiftstop, interval, graceperiod, dock, lunchstart, lunchstop, lunchdeduct
 */
public class Shift {
    
    public String getShiftDesc() {
        return desc;
    }
    
    public long getShiftStart() {
        return shift_start;
    }
    
    
    public long getShiftStop() {
        return shift_stop;
    }
    
    public int getInterval() {
        return interval;
    }
    
    public int getGracePeriod() {
        return grace_period;
    }
    
    public int getDock() {
        return dock;
    }
    
    public long getLunchStart () {
        return lunch_start;
    }
    
    public long getLunchStop () {
        return lunch_stop;
    }
    
    public int getLunchDeduct () {
        return lunch_deduct;
    }
    
    
    }


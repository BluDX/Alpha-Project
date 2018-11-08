/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.project;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


/**
 *
 * @author Adam
 * 
 */
public class TASLogic {
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift) {
       
         //Declare variables
        
        int totalMinutes;
        long shiftStart;
        long shiftEnd;
        long lunchStart;
        long lunchEnd;
        
        /*Get times from dailypunchlist and check to see if they took a lunch*/
	
	//This is if they clocked out for a lunch
        
        if(dailypunchlist.size() == 4){ 
            
            shiftStart = dailypunchlist.get(0).getOriginaltimestamp();
            shiftEnd = dailypunchlist.get(3).getOriginaltimestamp();
            lunchStart = dailypunchlist.get(1).getOriginaltimestamp();
            lunchEnd = dailypunchlist.get(2).getOriginaltimestamp();
            
            totalMinutes = (shiftEnd - shiftStart - 1800000) / 60000;
        }
        
        //This is if they did not clock out for a lunch
        
        else if(dailypunchlist.size() == 2){ 
            
            shiftStart = dailypunchlist.get(0).getOriginaltimestamp();
            shiftEnd = dailypunchlist.get(1).getOriginaltimestamp();
            
            totalMinutes = (shiftEnd - shiftStart) / 60000;
            
            //This checks to see if they worked the correct amount of time to get a lunch
            
            if(totalMinutes >= 360) 
               
                totalMinutes = totalMinutes - 1800000;
        }
        
        
        
        
        
        
        return totalMinutes;
    }
}

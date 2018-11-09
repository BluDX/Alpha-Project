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
        
         
        long totalMinutes = 0;
        long shiftStart;
        long shiftEnd;
        long lunchStart;
        long lunchEnd;
        String debug = "No rule used";
        /*Get times from dailypunchlist and check to see if they took a lunch*/
	//System.out.println("Size of dailypunchlist: " + dailypunchlist.size());
        //System.out.println("Punch type of first punch: " + dailypunchlist.get(0).getPunchtypeid());
	//This is if they clocked out for a lunch
        for (Punch punch : dailypunchlist) {
            //System.out.println(punch.printAdjustedTimestamp());
        }
        
        
        if(dailypunchlist.size() == 4){ 
            
            shiftStart = dailypunchlist.get(0).getAdjustedtimestamp();
            shiftEnd = dailypunchlist.get(3).getAdjustedtimestamp();
            lunchStart = dailypunchlist.get(1).getAdjustedtimestamp();
            lunchEnd = dailypunchlist.get(2).getAdjustedtimestamp();
            
            totalMinutes = ((shiftEnd - shiftStart) / 60000) - ((lunchEnd - lunchStart) /60000 );
            //totalMinutes = ((shiftEnd - shiftStart) / 60000) - 30;
            //System.out.println((shiftEnd - shiftStart)); 
            //System.out.println((lunchEnd - lunchStart)); 
            debug = "4";
            
        }
        
        //This is if they did not clock out for a lunch
        
        else if(dailypunchlist.size() == 2){ 
            
            shiftStart = dailypunchlist.get(0).getAdjustedtimestamp();
            shiftEnd = dailypunchlist.get(1).getAdjustedtimestamp();
            
            totalMinutes = (shiftEnd - shiftStart) / 60000;
            //This checks to see if they worked the correct amount of time to get a lunch
            debug = "2"; 
            if(totalMinutes >= 540) {
                totalMinutes = totalMinutes - 30;
                debug = "2 and lunch deducted";
            }
        }
        
        //System.out.println(totalMinutes);
        
        int minutes = (int)totalMinutes;
        
        //System.out.println(minutes);
        
        //System.out.println(debug + "\n");
        return minutes;
    }
}

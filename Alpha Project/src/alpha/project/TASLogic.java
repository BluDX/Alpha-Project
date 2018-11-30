
package alpha.project;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.*;

/**
 *
 * @author Adam
 * 
 */
public class TASLogic {
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift) {
    

        
         
        long totalMinutes = 0;
        long shiftStart;
        long shiftEnd;
        long lunchStart;
        long lunchEnd;
        String debug = "No rule used";

        for (Punch punch : dailypunchlist) {

        }
        
        
        if(dailypunchlist.size() == 4){ 
            
            shiftStart = dailypunchlist.get(0).getAdjustedtimestamp();
            shiftEnd = dailypunchlist.get(3).getAdjustedtimestamp();
            lunchStart = dailypunchlist.get(1).getAdjustedtimestamp();
            lunchEnd = dailypunchlist.get(2).getAdjustedtimestamp();
            
            totalMinutes = ((shiftEnd - shiftStart) / 60000) - ((lunchEnd - lunchStart) /60000 );
            debug = "4";
            
        }
        

        
        else if(dailypunchlist.size() == 2){ 
            
            shiftStart = dailypunchlist.get(0).getAdjustedtimestamp();
            shiftEnd = dailypunchlist.get(1).getAdjustedtimestamp();
            
            totalMinutes = (shiftEnd - shiftStart) / 60000;

            debug = "2"; 
            if(totalMinutes >= 540) {
                totalMinutes = totalMinutes - 30;
                debug = "2 and lunch deducted";
            }
        }

        
        int minutes = (int)totalMinutes;
        

        return minutes;
    }
    
    public static String getPunchListAsJSON(ArrayList<Punch> dailypunchlist) {
        

        ArrayList<HashMap<String, String>> jsonData = new ArrayList<HashMap<String, String>>();

        
        for (Punch punch : dailypunchlist) {
            

            HashMap<String, String>  punchData = new HashMap<>();

            punchData.put("id", String.valueOf(punch.getId()));
            
            punchData.put("badgeid", String.valueOf(punch.getBadgeid()));
            punchData.put("terminalid", String.valueOf(punch.getTerminalid()));
            punchData.put("punchtypeid", String.valueOf(punch.getPunchtypeid()));
            punchData.put("punchdata", String.valueOf(punch.getPunchData()));
            punchData.put("originaltimestamp", String.valueOf(punch.getOriginaltimestamp()));
            punchData.put("adjustedtimestamp", String.valueOf(punch.getAdjustedtimestamp()));

            jsonData.add(punchData);
        }

        String json = JSONValue.toJSONString(jsonData);
        
        return json;
    }
}

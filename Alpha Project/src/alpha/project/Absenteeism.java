/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.project;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author Adam
 */
public class Absenteeism {
    private String badgeid;
    GregorianCalendar g = new GregorianCalendar();
    private double percentage;
    public Absenteeism() {
        g.set(Calendar.MINUTE, 0);
        g.set(Calendar.SECOND, 0);
        g.set(Calendar.HOUR_OF_DAY, 0);
        g.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    }
    public String toString() {
        String msg = "";
        //todo
        return msg;
    }
}

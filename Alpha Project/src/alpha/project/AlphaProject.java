package alpha.project;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class AlphaProject {

    public static void main(String[] args) {
        
        TASDatabase db = new TASDatabase();

        Shift s1 = db.getShift(1);
        Shift s2 = db.getShift(2);
        Shift s3 = db.getShift(3);
        
        System.out.println(s1.toString());
        System.out.println(s2.toString());
        System.out.println(s3.toString());
        
        Badge b1 = db.getBadge("12565C60");
        Badge b2 = db.getBadge("08D01475");
        Badge b3 = db.getBadge("D2CC71D4");
        
        System.out.println(b1.toString());
        System.out.println(b2.toString());
        System.out.println(b3.toString());
        
        Punch p1 = db.getPunch(3433);
        Punch p2 = db.getPunch(3325);
        Punch p3 = db.getPunch(1963);
        Punch p4 = db.getPunch(5702);
        
        System.out.println(p1.printOriginalTimestamp());
        System.out.println(p2.printOriginalTimestamp());
        System.out.println(p3.printOriginalTimestamp());
        System.out.println(p4.printOriginalTimestamp());
        
        Punch idiotmoronface = new Punch(db.getBadge("021890C0"), 101, 1);
        GregorianCalendar ots = new GregorianCalendar();
        ots.setTimeInMillis(idiotmoronface.getOriginaltimestamp());
        
        String originaltimestamp = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ots.getTime());
        
        System.out.println(ots.getTime().toString());
        System.out.println(originaltimestamp);
    }
    
}

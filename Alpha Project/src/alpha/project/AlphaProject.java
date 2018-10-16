package alpha.project;


public class AlphaProject {

    public static void main(String[] args) {
        //SELECT id, terminalid, badgeid, punchtypeid, UNIX_TIMESTAMP(originaltimestamp) * 1000 AS originaltimestamp FROM punch p;
        //Above is the query Snellen recommended for getting the timestamp in terms of milliseconds passed since 1970, which can then be converted into a gregorian calendar object. 
        //Testing
        //Amber Testing
        //Adam Testing
        //Dinorah Testing
        //Hunter Testing
        
        // shift test. pls work
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
        
        //System.out.println(b1.toString());
        //System.out.println(b2.toString());
        //System.out.println(b3.toString());
        //Badge badgeTest = new Badge("ABCDEFG", "Schmoe, Joe");
        //Punch punchTest = new Punch(badgeTest, 103, 1);
        
        //System.out.println(punchTest.printOriginalTimestamp());
        //System.out.println(badgeTest.toString());
    }
    
}

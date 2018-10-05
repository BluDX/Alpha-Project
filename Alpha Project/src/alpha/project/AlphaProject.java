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
        Badge badgeTest = new Badge(102, "Schmoe, Joe");
        Punch punchTest = new Punch(badgeTest, 103, 1);
        
        System.out.println(punchTest.printOriginalTimestamp());
        System.out.println(badgeTest.toString());
    }
    
}

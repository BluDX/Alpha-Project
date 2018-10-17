package alpha.project;
import java.sql.*;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Adam
 */
public class TASDatabase {
    
    private Connection conn;
    private Statement stmt;
    private ResultSet result;
    
    //CONSTRUCTOR
    public TASDatabase() {
        
        try{
        
        //Should establish a connection to the database
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        //Identify the source
        String url = "jdbc:mysql://localhost/tas";
        
        //Allocate a connection
        conn = DriverManager.getConnection(url, "tasuser", "ScrumBitches");
        
        //Allocate a Statement object:
        stmt = conn.createStatement();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public void close() {
        //Should close the connection to the database
        try{
        result.close();
        stmt.close();
        conn.close();
        }catch(Exception ex){
            System.out.println(ex);
        }

    }
    public Punch getPunch(int id) {
        
        Punch p = null;
        try{
      
        String query = "SELECT *, UNIX_TIMESTAMP(originaltimestamp) * 1000 AS ts FROM punch WHERE id = " + Integer.toString(id);
        
            try (Statement st = conn.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                
                while (rs.next())
                {
                    int Id = rs.getInt("id");
                    int terminalId = rs.getInt("terminalid");
                    String badgeId = rs.getString("badgeid");
                    int punchTypeId = rs.getInt("punchtypeid");
                    Badge b = getBadge(badgeId);
                    long time = rs.getLong("ts");
                    //Need to create a punch and to create a punch I need a badge
                    p = new Punch(b, terminalId, punchTypeId);
                    //set original time to whatever it was with setter
                    GregorianCalendar origTime = new GregorianCalendar();
                    origTime.setTimeInMillis(time);
                    p.setOriginaltime(origTime);
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
     return p;   
    }
    public Badge getBadge(String id) {
        
        Badge b = null;
        try(Statement st = conn.createStatement()){
        //Querys for the corresponding badge, creates the badge object, then returns it
         String query = "SELECT * FROM badge WHERE id = " + "\"" + id + "\"";
         //PreparedStatement pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         //pstUpdate.setString(1, id);
         ResultSet rs = st.executeQuery(query);

         while(rs.next()){
                    String Id = rs.getString("id");
                    String d = rs.getString("description");
                    

                    b = new Badge(Id,d);
                }
        
      
       
        }catch(SQLException ex){
            Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return b;
    }
    public Shift getShift(int id) {
        Shift s = null;
       try(Statement st = conn.createStatement()){
         String query = "SELECT * FROM shift WHERE id = " + Integer.toString(id);
         //PreparedStatement pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         //pstUpdate.setString(1, Integer.toString(id));
         ResultSet rs = st.executeQuery(query);
         
         while(rs.next()){
                    int Id = rs.getInt("id");
                    int interval = rs.getInt("interval");
                    int graceperiod = rs.getInt("graceperiod");
                    int dock = rs.getInt("dock");
                    int lunchdeduct = rs.getInt("lunchdeduct");
                    long start = rs.getLong("start");
                    long stop = rs.getLong("stop");
                    long lunchstart = rs.getLong("lunchstart");
                    long lunchstop = rs.getLong("lunchstop");
                    String d = rs.getString("description");
                    Timestamp star = new Timestamp(start);
                    Timestamp sto = new Timestamp(stop);
                    Timestamp lunchstar = new Timestamp(lunchstart);
                    Timestamp lunchsto = new Timestamp(lunchstop);
                    
                   
                    s = new Shift(Id,interval,graceperiod,dock,lunchdeduct,d,star,sto,lunchstar,lunchsto);
                }
        
      
       
        }catch(SQLException ex){
            Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Querys for the corresponding shift, creates the object, returns it
    return s;
    }
    public Shift getShift(Badge badge) {
        Shift s = null;
        int shiftid = 0; //maybe shouldnt initialize to 0 - ask later
       try(Statement st = conn.createStatement()){
         String query = "SELECT * FROM employee WHERE badgeid = " + badge.getId();
         //PreparedStatement pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         //pstUpdate.setString(1, badge.getId());
         ResultSet rs = st.executeQuery(query);
         
         while(rs.next()){
             shiftid = rs.getInt("shiftid");
         }
         
         query = "SELECT * FROM shift WHERE id = " + Integer.toString(shiftid);
         //pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         //pstUpdate.setString(1, Integer.toString(shiftid));
         rs = st.executeQuery(query);
         while(rs.next()){
                    int Id = rs.getInt("id");
                    int interval = rs.getInt("interval");
                    int graceperiod = rs.getInt("graceperiod");
                    int dock = rs.getInt("dock");
                    int lunchdeduct = rs.getInt("lunchdeduct");
                    long start = rs.getLong("start");
                    long stop = rs.getLong("stop");
                    long lunchstart = rs.getLong("lunchstart");
                    long lunchstop = rs.getLong("lunchstop");
                    String d = rs.getString("description");
                    Timestamp star = new Timestamp(start);
                    Timestamp sto = new Timestamp(stop);
                    Timestamp lunchstar = new Timestamp(lunchstart);
                    Timestamp lunchsto = new Timestamp(lunchstop);
                    
                   
                    s = new Shift(Id,interval,graceperiod,dock,lunchdeduct,d,star,sto,lunchstar,lunchsto);
                }
        
      
       
        }catch(SQLException ex){
            Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Querys for the corresponding shift, creates the object, returns it
    return s;
    }
}

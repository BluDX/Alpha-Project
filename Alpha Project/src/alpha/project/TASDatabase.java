package alpha.project;
import java.sql.*;
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
      
        String query = "SELECT * FROM punch WHERE id = " + id;
        
            try (Statement st = conn.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                
                while (rs.next())
                {
                    int Id = rs.getInt("id");
                    int terminalId = rs.getInt("terminalid");
                    String badgeId = rs.getString("badgeid");
                    int punchTypeId = rs.getInt("punchtypeid");
                    Badge b = getBadge(badgeId);
                    //Need to create a punch and to create a punch I need a badge
                    p = new Punch(b, terminalId, punchTypeId);
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
         String query = "SELECT * FROM badge WHERE id = ?";
         PreparedStatement pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         pstUpdate.setString(1, id);
         ResultSet rs = st.executeQuery(query);
         
         while(rs.next()){
                    String Id = rs.getString("id");
                    String d = rs.getNString("description");
                    
                   
                    //Need to create a punch and to create a punch I need a badge
                    b = new Badge(Id,d);
                }
        
      
       
        }catch(SQLException ex){
            Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return b;
    }
    public Shift getShift(int id) {
        try{
        //Querys for the corresponding badge, creates the badge object, then returns it
        result = stmt.executeQuery("SELECT * FROM shift WHERE id="+id);
        
        if ( result != null ){
            result.next();
            Shift s = (Shift)result;
            return s;
        }
        else
            return null;
       
        }catch(Exception ex){System.out.println(ex);
        return null;}
        //Querys for the corresponding shift, creates the object, returns it
        
    }
    public Shift getShift(Badge badge) {
        try{
        //Querys for the corresponding badge, creates the badge object, then returns it
        result = stmt.executeQuery("SELECT * FROM shift WHERE badge="+badge);
        
        if ( result != null ){
            result.next();
            Shift s = (Shift)result;
            return s;
        }
        else
            return null;
       
        }catch(Exception ex){System.out.println(ex);
        return null;}
        //Querys for the corresponding shift by its badge, creates the object, then returns it
        
    }
}

package alpha.project;
import java.sql.*;


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
        try{
        //Accepts the ID of the punch, Querys the database for the corresponding Punch, use this information to populate a new punch, then return that punch
        result = stmt.executeQuery("SELECT * FROM punch WHERE id='id'");
        
        if ( result != null ){
            result.next();
            Punch p = (Punch)result;
            return p;
        }
        else
            return null;
       
        }catch(Exception ex){System.out.println(ex);}
        return null;
    }
    public Badge getBadge(String id) {
        try{
        //Querys for the corresponding badge, creates the badge object, then returns it
        result = stmt.executeQuery("SELECT * FROM badge WHERE id='id'");
        
        if ( result != null ){
            result.next();
            Badge b = (Badge)result;
            return b;
        }
        else
            return null;
       
        }catch(Exception ex){System.out.println(ex);}
        return null;
    
    }
    public Shift getShift(int id) {
        try{
        //Querys for the corresponding badge, creates the badge object, then returns it
        result = stmt.executeQuery("SELECT * FROM shift WHERE id='id'");
        
        if ( result != null ){
            result.next();
            Shift s = (Shift)result;
            return s;
        }
        else
            return null;
       
        }catch(Exception ex){System.out.println(ex);}
        //Querys for the corresponding shift, creates the object, returns it
        return null;
    }
    public Shift getShift(Badge badge) {
        try{
        //Querys for the corresponding badge, creates the badge object, then returns it
        result = stmt.executeQuery("SELECT * FROM shift WHERE badge='badge'");
        
        if ( result != null ){
            result.next();
            Shift s = (Shift)result;
            return s;
        }
        else
            return null;
       
        }catch(Exception ex){System.out.println(ex);}
        //Querys for the corresponding shift by its badge, creates the object, then returns it
        return null;
    }
}

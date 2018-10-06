package alpha.project;
import java.sql.*;


/**
 *
 * @author Adam
 */
public class TASDatabase {
    //CONSTRUCTOR
    public TASDatabase() {
        //Should establish a connection to the database
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        //Identify the source
        String url = "jdbc:mysql://localhost/tas";
        
        //Allocate a connection
        Connection conn = DriverManager.getConnection(url, "tasuser", "ScrumBitches");
        
        //Allocate a Statement object:
        Statement stmt = conn.createStatement();


    }
    public void close() {
        //Should close the connection to the database
        result.close();
        stmt.close();
        conn.close();

    }
    public Punch getPunch(int id) {
        //Accepts the ID of the punch, Querys the database for the corresponding Punch, use this information to populate a new punch, then return that punch
        ResultSet result = stmt.executeQuery("SELECT * FROM punch WHERE id='id'");
        
        if ( result != null ){
            result.next();
            Badge b = result;
        }
        else
            return null;
    }
    public Badge getBadge(int id) {
        //Querys for the corresponding badge, creates the badge object, then returns it
        return null;
    }
    public Shift getShift(int id) {
        //Querys for the corresponding shift, creates the object, returns it
        return null;
    }
    public Shift getShift(Badge badge) {
        //Querys for the corresponding shift by its badge, creates the object, then returns it
        return null;
    }
}

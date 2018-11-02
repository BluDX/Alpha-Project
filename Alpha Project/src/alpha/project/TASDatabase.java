package alpha.project;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
                    p.setOriginaltime(time);
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
                    Time start = rs.getTime("start");
                    Time stop = rs.getTime("stop");
                    Time lunchstart = rs.getTime("lunchstart");
                    Time lunchstop = rs.getTime("lunchstop");
                    String d = rs.getString("description");
                    
                    
                   
                    s = new Shift(Id,interval,graceperiod,dock,lunchdeduct,d,start,stop,lunchstart,lunchstop);
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
         String query = "SELECT * FROM employee WHERE badgeid = " + "\"" + badge.getId() + "\"";
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
                    Time start = rs.getTime("start");
                    Time stop = rs.getTime("stop");
                    Time lunchstart = rs.getTime("lunchstart");
                    Time lunchstop = rs.getTime("lunchstop");
                    String d = rs.getString("description");
                    
                   
                    s = new Shift(Id,interval,graceperiod,dock,lunchdeduct,d,start,stop,lunchstart,lunchstop);
                }
        
      
       
        }catch(SQLException ex){
            Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Querys for the corresponding shift, creates the object, returns it
    return s;
    }
    
    
    public int insertPunch(Punch p){
	
	//Extract data from Punch
	
	int terminalID = p.getTerminalid();
	int punchTypeID = p.getPunchtypeid();
	int ID = p.getId();
        String badgeID = p.getBadgeid();
        //Timestamp s = new Timestamp(p.getOriginaltimestamp());
        //Timestamp s = new Timestamp((new GregorianCalendar()).getTimeInMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar s = new GregorianCalendar();
        s.setTimeInMillis(p.getOriginaltimestamp());
        
	try{
		//Initialize values that will be used to return the punchID and check if the query was exexcuted
		
		int punchID;
            	int Results;
		ResultSet rst;		

		// the mysql insert statement
      	
		String query = " insert into punch (terminalid, badgeid, originaltimestamp, punchtypeid)" + " values (?, ?, ?, ?)";

		// create the mysql insert preparedstatement
      	
		PreparedStatement preparedStmt = conn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);

		//replaces the question marks in the query with values		

      		preparedStmt.setInt(1, terminalID);
      		preparedStmt.setString(2, badgeID);
      		preparedStmt.setString(3, sdf.format(s.getTime()));
      		preparedStmt.setInt(4, punchTypeID);

		//executes the query and closes it

		Results = preparedStmt.executeUpdate();
		

		//Check to see if punch was properly inserted		

		if(Results == 1){
			rst = preparedStmt.getGeneratedKeys();
			if(rst.next()){
                    		punchID = rst.getInt(1);
                    		p.setId(punchID);
                	}
		}
	}
	catch(SQLException ex){
	
	//I do not know what this error is for but it was on the hints in NetBeans
            
		Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
       
		return p.getId();
}
    public ArrayList getDailyPunchList(Badge b, long ts) {
        ArrayList<Punch> punchList = new ArrayList<>();
        GregorianCalendar dayInQuestion = new GregorianCalendar();
        dayInQuestion.setTimeInMillis(ts);
        GregorianCalendar queryTime = new GregorianCalendar();
        try {
            ResultSet rst;
           
            String query = "SELECT *, UNIX_TIMESTAMP(originaltimestamp) * 1000 AS time FROM punch WHERE badgeid = ? ORDER BY originaltimestamp";
            
            PreparedStatement preparedStmt = conn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, b.getId());
            
            rst = preparedStmt.executeQuery();
            int counter = 0;
            while(rst.next()) {
                queryTime.setTimeInMillis(rst.getLong("time"));
                if(queryTime.get(Calendar.DAY_OF_YEAR) == dayInQuestion.get(Calendar.DAY_OF_YEAR) && queryTime.get(Calendar.YEAR) == dayInQuestion.get(Calendar.YEAR)) {
                    Punch p = getPunch(rst.getInt("id"));
                    punchList.add(p); //This MIGHT work for getting all the punches from a given badge on a given day. Still need to find a way to add the first punch from the following day. 
                }
            }
            rst = preparedStmt.executeQuery();
            
            dayInQuestion.add(Calendar.DATE,1);
            if(rst.next()) {
                queryTime.setTimeInMillis(rst.getLong("time"));
                if(queryTime.get(Calendar.DAY_OF_YEAR) == dayInQuestion.get(Calendar.DAY_OF_YEAR) && queryTime.get(Calendar.YEAR) == dayInQuestion.get(Calendar.YEAR)) {
                    Punch p = getPunch(rst.getInt("id"));
                    punchList.add(p); //Hopefully this accurately adds the first punch of the following day. 
                }
            }
            
        }
        catch(SQLException ex) {
            
        }
        
        
        
        return punchList;
    }    
}

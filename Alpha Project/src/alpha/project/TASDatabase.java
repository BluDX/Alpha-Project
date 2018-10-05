/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.project;

/**
 *
 * @author Adam
 */
public class TASDatabase {
    //CONSTRUCTOR
    public TASDatabase() {
        //Should establish a connection to the database
    }
    public void close() {
        //Should close the connection to the database
    }
    public Punch getPunch(int id) {
        //Accepts the ID of the punch, Querys the database for the corresponding Punch, use this information to populate a new punch, then return that punch
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

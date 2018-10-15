/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha.project;

/**
 *
 * @author Adam
 * Last updated: 10/3/18
 */
public class Badge {
    private String description;
    private String id;
    public Badge(String id, String d) {
        description = d;
        this.id = id;
    }
    
    @Override
    public String toString(){
        return "#" + id + " " + "(" + description + ")";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }
}

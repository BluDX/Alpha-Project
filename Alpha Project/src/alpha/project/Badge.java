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
    private int id;
    public Badge(int id, String d) {
        description = d;
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}

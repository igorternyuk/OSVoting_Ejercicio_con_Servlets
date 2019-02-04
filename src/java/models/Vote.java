/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.security.Timestamp;

/**
 * 
 * @author Igor Ternyuk <xmonad100 at gmail.com>
 */
public class Vote {
    private String id;
    private String idOS;
    private Timestamp date;

    public Vote() {
    }

    public Vote(String id, String idOS, Timestamp date) {
        this.id = id;
        this.idOS = idOS;
        this.date = date;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOS() {
        return idOS;
    }

    public void setIdOS(String idOS) {
        this.idOS = idOS;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    
}

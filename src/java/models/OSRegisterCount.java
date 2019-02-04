/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 * 
 * @author Igor Ternyuk <xmonad100 at gmail.com>
 */
public class OSRegisterCount {
    private String osName;
    private int voteCount;
    
    public OSRegisterCount(){
        
    }

    public OSRegisterCount(String osName, int voteCount) {
        this.osName = osName;
        this.voteCount = voteCount;
    }
 
    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "OSRegisterCount{" + "osName=" + osName + ", voteCount=" + voteCount + '}';
    }   
}

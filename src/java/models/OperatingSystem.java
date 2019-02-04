/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.Objects;

/**
 * 
 * @author Igor Ternyuk <xmonad100 at gmail.com>
 */
public class OperatingSystem {
    public static final OperatingSystem NULL_OS = new OperatingSystem();
    
    String id;
    String name;

    public OperatingSystem() {
    }

    public OperatingSystem(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final OperatingSystem other = (OperatingSystem) obj;
        
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "OperatingSystem{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}

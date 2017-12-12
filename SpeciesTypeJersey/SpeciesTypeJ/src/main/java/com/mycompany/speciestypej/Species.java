/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.speciestypej;

import java.util.Objects;

/**
 *
 * @author Dániel
 */
public class Species {
    private String azon;
    private String name;
    private String description;
    
    public Species(String name, String description, String azon){
        this.name = name;
        this.description = description;
        this.azon = azon;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
        @Override
        public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Species other = (Species) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Species{" + "name=" + name + '}';
    }
    
        @Override
        public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * @return the azon
     */
    public String getAzon() {
        return azon;
    }

    /**
     * @param azon the azon to set
     */
    public void setAzon(String azon) {
        this.azon = azon;
    }
    
}

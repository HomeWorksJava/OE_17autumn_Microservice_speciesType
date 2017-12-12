/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.speciestypej;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author hallgato
 */
public class Hero {
    private String name;
    private String description;
    private boolean available;
    private ArrayList<Species> fajok = new ArrayList<Species>();
    private String azon;
    
    
    public Hero() {
    }
    
    public Hero(String azon, String name, String description, boolean available) {
        this.name = name;
        this.description = description;
        this.available = available;
        this.azon = azon;
        this.fajok = new ArrayList<Species>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hero other = (Hero) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hero{" + "name=" + name + '}';
    }

    /**
     * @return the fajok
     */
    public ArrayList<Species> getFajok() {
        return fajok;
    }

    /**
     * @param fajok the fajok to set
     */
    public void setFajok(ArrayList<Species> fajok) {
        this.fajok = fajok;
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
    
    public void ujSpecies(Species s)
    {
        fajok.add(s);
    }
}

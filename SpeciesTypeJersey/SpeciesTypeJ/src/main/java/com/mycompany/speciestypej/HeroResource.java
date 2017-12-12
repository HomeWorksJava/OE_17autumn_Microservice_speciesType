/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.speciestypej;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author hallgato
 */
@Path("hero")
@Produces({ MediaType.APPLICATION_JSON+";charset=UTF-8"})
public class HeroResource {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.96.129:3306/Mikroszerv";
    static final String USER = "monty";
    static final String PASS = "passw0rd";

    public HeroResource()
    {
        
    }
    
    @GET
    @Path("get/{offset}/{limit}")   //OK
    public List<Hero> query(@PathParam("offset") int offset, @PathParam("limit") int limit ){
        
        //ContainerH res = new ContainerH(limit, offset, 5);
        List<Hero> tempL = new ArrayList();
        
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        
        //SELECT * FROM Heroes;
       
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Heroes WHERE azon >= "+offset+" and azon < "+offset+" + "+limit+";";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                int azono = rs.getInt("azon");
                String nev = rs.getString("name");
                String leiras = rs.getString("description");
                String elerheto = rs.getString("available");
                
                Hero h = new Hero();
                
                h.setAzon(Integer.toString(azono));
                h.setName(nev);
                h.setDescription(leiras);
                if (elerheto.equals("1")) h.setAvailable(true); else h.setAvailable(false);
                tempL.add(h);
            }
            //rs.close();
            //stmt.close();

                stmt2 = conn.createStatement();
                sql = "select Heroes.name as HeroName,Species.name as SpeciesName, Species.azon as SpeciesAzon, Species.description as SpeciesLeiras from HeroesSpecies INNER JOIN Heroes on HeroesSpecies.Heroazon = Heroes.azon INNER JOIN Species on HeroesSpecies.Speciesazon = Species.azon;"; //lehetne optimalizálni
                ResultSet rs2 = stmt2.executeQuery(sql);
                while (rs2.next())
                {
                    String Hosname = rs2.getString("HeroName");
                    String Speciesname = rs2.getString("SpeciesName");
                    String Speciesleiras = rs2.getString("SpeciesLeiras");
                    String Speciesazon = rs2.getString("SpeciesAzon");
                    
                    for(Hero h:tempL)
                    {
                        if (h.getName().equals(Hosname))
                        {
                            Species s = new Species(Speciesname, Speciesleiras, Speciesazon);
                            h.ujSpecies(s);
                        }
                    }
                }
                rs.close();
                stmt.close();
                rs2.close();
                stmt2.close();
                conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        //res.setItems(tempL);
        return tempL;
    }
    
    @GET
    @Path("getall") //OK
    public List<Hero> getAll(){
        List<Hero> tempL = new ArrayList();
       
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        
        //SELECT * FROM Heroes;
       
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Heroes;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                int azono = rs.getInt("azon");
                String nev = rs.getString("name");
                String leiras = rs.getString("description");
                String elerheto = rs.getString("available");
                
                Hero h = new Hero();
                
                h.setAzon(Integer.toString(azono));
                h.setName(nev);
                h.setDescription(leiras);
                if (elerheto.equals("1")) h.setAvailable(true); else h.setAvailable(false);
                tempL.add(h);
            }
//                rs.close();
//                stmt.close();

                stmt2 = conn.createStatement();                
                String sql2 = "select Heroes.name as HeroName,Species.name as SpeciesName, Species.azon as SpeciesAzon, Species.description as SpeciesLeiras from HeroesSpecies INNER JOIN Heroes on HeroesSpecies.Heroazon = Heroes.azon INNER JOIN Species on HeroesSpecies.Speciesazon = Species.azon;"; //lehetne optimalizálni
                ResultSet rs2 = stmt2.executeQuery(sql2);
                while (rs2.next())
                {
                    String Hosname = rs2.getString("HeroName");
                    String Speciesname = rs2.getString("SpeciesName");
                    String Speciesleiras = rs2.getString("SpeciesLeiras");
                    String Speciesazon = rs2.getString("SpeciesAzon");
                    
                    for(Hero h:tempL)
                    {
                        if (h.getName().equals(Hosname))
                        {
                            Species s = new Species(Speciesname, Speciesleiras, Speciesazon);
                            h.ujSpecies(s);
                        }
                    }
                }
                rs.close();
                stmt.close();
                rs2.close();
                stmt2.close();
                conn.close();
        }
        catch (Exception e)
        {
            
        }

       
        return tempL;
    }
    
    @GET
    @Path("get/{offset}") //OK
    public Hero query(@PathParam("offset") String offset){
        
        Hero s = new Hero();
        
        Connection conn = null;
        Statement stmt = null;
        
        Statement stmt2 = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Heroes where azon = " + offset + ";";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                int azono = rs.getInt("azon");
                String nev = rs.getString("name");
                String leiras = rs.getString("description");
                String elerheto = rs.getString("available");
                
                s = new Hero();
                
                s.setAzon(Integer.toString(azono));
                s.setName(nev);
                s.setDescription(leiras);
                if (elerheto.equals("1")) s.setAvailable(true); else s.setAvailable(false);
                
            }

                stmt2 = conn.createStatement();
                String sql2 = "select Heroes.name as HeroName,Species.name as SpeciesName, Species.azon as SpeciesAzon, Species.description as SpeciesLeiras from HeroesSpecies INNER JOIN Heroes on HeroesSpecies.Heroazon = Heroes.azon INNER JOIN Species on HeroesSpecies.Speciesazon = Species.azon;"; //lehetne optimalizálni
                ResultSet rs2 = stmt2.executeQuery(sql2);
                while (rs2.next())
                {
                    String Hosname = rs2.getString("HeroName");
                    String Speciesname = rs2.getString("SpeciesName");
                    String Speciesleiras = rs2.getString("SpeciesLeiras");
                    String Speciesazon = rs2.getString("SpeciesAzon");
                        if (s.getName().equals(Hosname))
                        {
                            Species h = new Species(Speciesname, Speciesleiras, Speciesazon);
                            s.ujSpecies(h);
                        }
                }
                rs.close();
                stmt.close();
                stmt2.close();
                rs2.close();
                conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
        return s;
    }
    
    @POST
    @Path("delete")    //OK
    public Response delete(@FormParam("id") String id ){
        
        Connection conn = null;
        Statement stmt = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM Heroes where azon = " + id + ";";
            stmt.execute(sql);
            
            stmt.close();
            conn.close();
        }
            catch (Exception e)
            {
                    System.out.println(e.toString()); 
            }
            

        return Response.status(Response.Status.OK).build();
    }
    
    @POST
    @Path("add")    //OK
    public Response add(
            @FormParam("name") String name,
            @FormParam("description") String description
    ){   
        //INSERT INTO Heroes (name, description, available) values ('name', 'description', 1);
        
        Connection conn = null;
        Statement stmt = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO Heroes (name, description, available) values ('"+ name + "', '"+ description +"', 1);";
            stmt.execute(sql);
            stmt.close();
            conn.close();
        }
            catch (Exception e)
            {
                System.out.println(e.toString());    
            }
        return Response.status(Response.Status.OK).build();
    }
    
    
    
    @POST
    @Path("newSpecies2Hero") //OK
    public Response ujFajHoshoz(@FormParam("Heroid") String Heroid, @FormParam("Speciesid") String Speciesid)
            {
                //INSERT INTO HeroesSpecies
                Connection conn = null;
                Statement stmt = null;

                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    stmt = conn.createStatement();
                    String sql;
                    sql = "INSERT INTO HeroesSpecies (Heroazon, Speciesazon) values ('"+ Heroid +"', '"+ Speciesid +"');";
                    stmt.execute(sql);
                    stmt.close();
                    conn.close();
                }
                    catch (Exception e)
                    {
                           System.out.println(e.toString());
                           return Response.ok("Sikertelen").build();
                    }

                        return Response.ok("Sikeres").build();
                    }
    
    @POST
    @Path("modifyDescription")    //OK
    public Response modDesc(
            @FormParam("id") String id,
            @FormParam("description") String description
    ){   
        Connection conn = null;
        Statement stmt = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "UPDATE Heroes SET description = '"+description+"' where azon = '"+id+"'";
            stmt.execute(sql);
            
            stmt.close();
            conn.close();
        }
            catch (Exception e)
            {
               System.out.println(e.toString());
            }
        return Response.ok("Sikeres").build();
    }
    
    @POST
    @Path("modifyName")    //OK
    public Response modName(
            @FormParam("id") String id,
            @FormParam("name") String name
    ){   
        Connection conn = null;
        Statement stmt = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "UPDATE Heroes SET name = '"+name+"' where azon = '"+id+"'";
            stmt.execute(sql);
            
            stmt.close();
            conn.close();
        }
            catch (Exception e)
            {
               System.out.println(e.toString());
            }
        return Response.ok("Sikeres").build();
    }
    
    @POST
    @Path("modifyAvailable")    //OK
    public Response modAvail(
            @FormParam("id") String id,
            @FormParam("available") String available
    ){   
        Connection conn = null;
        Statement stmt = null;
        
        try
        {
            String avail = "0";
            if (available.equals("true") || available.equals("1"))
            {
                avail = "1";
            }
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "UPDATE Heroes SET available = '"+avail+"' where azon = '"+id+"'";
            stmt.execute(sql);
            
            stmt.close();
            conn.close();
        }
            catch (Exception e)
            {
               System.out.println(e.toString());
            }
        return Response.ok("Sikeres").build();
    }
}

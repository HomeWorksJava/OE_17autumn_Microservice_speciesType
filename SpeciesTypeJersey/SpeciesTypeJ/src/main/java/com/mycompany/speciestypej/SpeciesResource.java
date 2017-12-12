/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.speciestypej;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import java.sql.*;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;

/**
 *
 * @author Dániel
 */
@Path("species")
@Produces({ MediaType.APPLICATION_JSON+";charset=UTF-8"})
public class SpeciesResource {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.96.129:3306/Mikroszerv";
    static final String USER = "monty";
    static final String PASS = "passw0rd";

    
    public SpeciesResource()
    {
        
    }
    
    

    
    @GET
    @Path("get/{offset}/{limit}")   //OK
    public List<Species> query(@PathParam("offset") int offset, @PathParam("limit") int limit ){
        List<Species> tempL = new ArrayList();
        Connection conn = null;
        Statement stmt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Species WHERE azon >= "+offset+" and azon < "+offset+" + "+limit+"";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                String azon = rs.getString("azon");
                String name = rs.getString("name");
                String leiras = rs.getString("description");        
                Species s = new Species(name, leiras, azon);
                tempL.add(s);
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return tempL;
    }
    
    @GET
    @Path("getall") //OK
    public List<Species> getAll(){ //List<Species>
        List<Species> tempL = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        try{

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
         
            stmt = conn.createStatement();
                    
            String sql;
                   ;
            sql = "SELECT * FROM Species";
     
            ResultSet rs = stmt.executeQuery(sql);
      
            while (rs.next())
            {
        
                String azon = rs.getString("azon");
                String name = rs.getString("name");
                String leiras = rs.getString("description");        
                Species s = new Species(name, leiras, azon);
                tempL.add(s);
            }
            rs.close();
            stmt.close();
            conn.close();
        ;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        //return tempL;
        return tempL;
    }
    
    @GET
    @Path("get/{offset}") //OK
    public Species query(@PathParam("offset") String offset){
        Species s = null;
        Connection conn = null;
        Statement stmt = null;
        

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Species WHERE azon = " + offset + "";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                String azon = rs.getString("azon");
                String name = rs.getString("name");
                String leiras = rs.getString("description");        
                s = new Species(name, leiras, azon);
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            
        }
        return s;
    }
    
    @POST
    @Path("delete") //OK
    public Response delete(@FormParam("id") String id ){
        Connection conn = null;
        Statement stmt = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM Species where azon = " + id + "";
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
        Connection conn = null;
        Statement stmt = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO Species (name, description) values ('"+name+"', '"+description+"')";
            stmt.execute(sql);
            
            stmt.close();
            conn.close();
        }
            catch (Exception e)
            {
                    
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
            sql = "UPDATE Species SET name = '"+name+"' where azon = '"+id+"'";
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
            sql = "UPDATE Species SET description = '"+description+"' where azon = '"+id+"'";
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

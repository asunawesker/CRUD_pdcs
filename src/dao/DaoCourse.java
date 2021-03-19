/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojo.PojoCourse;
import pojo.PojoCourse;
import singleton.ConnectDB;

/**
 *
 * @author asunawesker
 */
public class DaoCourse implements IDaoGeneral<PojoCourse>{
    
    private Connection connection;
    private PreparedStatement ps;
    private List<PojoCourse> ls;     
    
    public DaoCourse() throws SQLException{
        connection = ConnectDB.getInstance().getConnection();
        ls = new ArrayList();
        
    }
    
    private final String[] QUERIES = {
        "INSERT INTO courses (crs_id, crs_name, crs_credits) VALUES (?, ?, ?)",
        "DELETE FROM courses WHERE crs_id = ?",
        "SELECT * FROM courses WHERE crs_id = ?",
        "SELECT * FROM courses",
        "UPDATE courses SET crs_name WHERE (crs_id = ?)"
    };

    @Override
    public int create(PojoCourse course) throws SQLException {
        
        ps = connection.prepareStatement(QUERIES[0]); 
        
        ps.setInt(1, course.getId()); 
        ps.setString(2, course.getName()); 
        ps.setInt(3, course.getCredits()); 
        
        int rows = ps.executeUpdate(); 
        
        // Devuelve valor de filas afectadas
        return rows;
    }

    @Override
    public void delete(int id) throws SQLException {
        ps = connection.prepareStatement(QUERIES[1]); 
        
        ps.setInt(1, id); 
        ps.executeUpdate(); 
    }

    @Override
    public PojoCourse readSingle(int id) throws SQLException {
        ps = connection.prepareStatement(QUERIES[2]); 
  
        ps.setInt(1, id);        
        ResultSet rs = ps.executeQuery(); 
        PojoCourse course = new PojoCourse(); 
  
        while (rs.next()) { 
            course.setId(rs.getInt("crs_id"));             
            course.setName(rs.getString("std_name")); 
            course.setCredits(rs.getInt("std_lastname"));
        } 
  
        return course;
    }

    @Override
    public List<PojoCourse> readAll() throws SQLException {
        
        ps = connection.prepareStatement(QUERIES[3]); 
        ResultSet rs = ps.executeQuery();
        PojoCourse course; 
              
        while (rs.next()){
            course = new PojoCourse();
            course.setId(rs.getInt("crs_id"));             
            course.setName(rs.getString("std_name")); 
            course.setCredits(rs.getInt("std_lastname"));
            
            ls.add(course);
        } 
        
        return ls; 
    }

    @Override
    public int update(PojoCourse course, int id) throws SQLException {        
        ps = connection.prepareStatement(QUERIES[4]); 
        
        ps.setString(1, course.getName()); 
        ps.setInt(2, id);
        
        int rows = ps.executeUpdate();
        
        return rows;
    }
    
}

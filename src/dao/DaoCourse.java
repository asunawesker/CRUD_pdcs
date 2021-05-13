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
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.PojoCourse;
import singleton.ConnectDB;
import singleton.TransactionDB;

/**
 *
 * @author asunawesker
 */
public class DaoCourse implements IDaoGeneral<PojoCourse, Integer>{
    
    private PreparedStatement  preparedStatement;
    private ConnectDB connection ;
    private List <PojoCourse> ls;
    private PojoCourse course;

    public DaoCourse() throws SQLException {
        connection = ConnectDB.getInstance();        
    }        
    
    private final String[] QUERIES = {
        "INSERT INTO courses (crs_id, crs_name, crs_credits) VALUES (?, ?, ?)",
        "DELETE FROM courses WHERE crs_id = ?",
        "SELECT * FROM courses WHERE crs_id = ?",
        "SELECT * FROM courses",
        "UPDATE courses SET crs_name = ? WHERE (crs_id = ?)"
    };

    @Override
    public boolean create(PojoCourse course) throws SQLException {
        boolean response;
        TransactionDB t;
        
        t = new TransactionDB<PojoCourse>(course) {
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                
                try {
                    preparedStatement = connection.prepareStatement(QUERIES[0]); 
        
                    preparedStatement.setInt(1, course.getId()); 
                    preparedStatement.setString(2, course.getName()); 
                    preparedStatement.setInt(3, course.getCredits()); 
        
                    preparedStatement.executeUpdate(); 
                    
                    System.out.println("PojoCourse guardada");
                     
                     response = true;                     
                 } catch (SQLException ex) {
                     Logger.getLogger(DaoCourse.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 return response;
             }
         };         
         response = connection.execute(t);
         return response;              
    }
    
    @Override
    public boolean delete(Integer id) throws SQLException {
        boolean response;
        TransactionDB t;        
               
         t = new TransactionDB<PojoCourse>(){
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                
                try {
                     preparedStatement = connection.prepareStatement(QUERIES[1]); 
                    preparedStatement.setInt(1, id); 
                    preparedStatement.executeUpdate(); 
                    
                    System.out.println("Persona eliminada");
                    
                    response = true;
                } catch (SQLException ex) {
                    Logger.getLogger(DaoCourse.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return response;
            }            
        };        
        response = connection.execute(t);        
        return response;
    }

    @Override
    public PojoCourse readSingle(Integer id) throws SQLException {
        boolean response;
        TransactionDB t;    
        
        t = new TransactionDB<PojoCourse>(){
           @Override
           public boolean execute(Connection connection) {
               boolean response = false;
               
               try {
                   preparedStatement = connection.prepareStatement(QUERIES[2]); 
  
                    preparedStatement.setInt(1, id);        
                    ResultSet rs = preparedStatement.executeQuery(); 
                    course = new PojoCourse(); 
  
                    while (rs.next()) { 
                        course.setId(rs.getInt("crs_id"));             
                        course.setName(rs.getString("crs_name")); 
                        course.setCredits(rs.getInt("crs_credits"));
                    } 
                    
                    response = true;
               } catch (SQLException ex) {
                   Logger.getLogger(DaoCourse.class.getName()).log(Level.SEVERE, null, ex);
               }
               
               return response;
           }  
        };
        connection.execute(t);
        return course;
    }

    @Override
    public List<PojoCourse> readAll() throws SQLException {
        TransactionDB t;
        ls = new ArrayList<>();
                
        t = new TransactionDB<PojoCourse>() {
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                try {
                    PojoCourse course;
                    
                    preparedStatement = connection.prepareStatement(QUERIES[3]); 
                    ResultSet rs = preparedStatement.executeQuery();
                    
                    while (rs.next()){
                        course = new PojoCourse();
                        course.setId(rs.getInt("crs_id"));             
                        course.setName(rs.getString("crs_name")); 
                        course.setCredits(rs.getInt("crs_credits"));

                        ls.add(course);
                    } 
                    response = true;
                    return response;
                } catch (SQLException ex) {
                    Logger.getLogger(DaoCourse.class.getName()).log(Level.SEVERE, null, ex);
                }
                return response;
            }            
        };
         connection.execute(t);        
         return ls;
    }
    
    @Override
    public boolean update(PojoCourse course, Integer id) throws SQLException {      
        TransactionDB t;
        boolean response;
        
        t = new TransactionDB<PojoCourse>(course){
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                try {
                    preparedStatement = connection.prepareStatement(QUERIES[4]); 
        
                    preparedStatement.setString(1, course.getName()); 
                    preparedStatement.setInt(2, id);

                    preparedStatement.executeUpdate();
                    response = true;                    
                } catch (SQLException ex) {
                    Logger.getLogger(DaoCourse.class.getName()).log(Level.SEVERE, null, ex);
                }
                return response;
            }            
        };
        response = connection.execute(t);
        return response;
    }
    
}

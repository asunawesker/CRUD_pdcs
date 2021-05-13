/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tstudentlate file, choose Tools | Tstudentlates
 * and open the tstudentlate in the editor.
 */
package dao;

import singleton.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojo.PojoStudent;
import dao.IDaoGeneral;
import java.util.logging.Level;
import java.util.logging.Logger;
import singleton.TransactionDB;

/**
 *
 * @author asunawesker
 */
public class DaoStudent implements IDaoGeneral<PojoStudent, String>{
    
    private PreparedStatement  preparedStatement;
    private ConnectDB connection ;
    private List <PojoStudent> ls;
    private PojoStudent student;

    public DaoStudent() throws SQLException {
        connection = ConnectDB.getInstance();        
    }        
    
    private final String[] QUERIES = {
        "INSERT INTO students (std_enrollment, std_name, std_lastname, std_career, crs_id) VALUES (?, ?, ?, ?, ?)",
        "DELETE FROM students WHERE std_enrollment =  ?",
        "SELECT * FROM students WHERE std_enrollment = ?",
        "SELECT * FROM students",
        "UPDATE students SET std_career=? WHERE (std_enrollment = ?)"
    };

    @Override
    public boolean create(PojoStudent student) throws SQLException {
        boolean response;
        TransactionDB t;
        
        t = new TransactionDB<PojoStudent>(student) {
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                
                try {
                    preparedStatement = connection.prepareStatement(QUERIES[0]); 
        
                    preparedStatement.setString(1, student.getEnrollment()); 
                    preparedStatement.setString(2, student.getName()); 
                    preparedStatement.setString(3, student.getLastName()); 
                    preparedStatement.setString(4, student.getCareer()); 
                    preparedStatement.setInt(5, student.getCourse_id()); 

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
    public boolean delete(String student) throws SQLException {
        boolean response;
        TransactionDB t;        
               
         t = new TransactionDB<PojoStudent>(){
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                
                try {
                    preparedStatement = connection.prepareStatement(QUERIES[1]); 
        
                    preparedStatement.setString(1, student); 
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

    public PojoStudent readSingle(String enrollment) throws SQLException {
        boolean response;
        TransactionDB t;    
        
        t = new TransactionDB<PojoStudent>(){
           @Override
           public boolean execute(Connection connection) {
               boolean response = false;
               
               try {
                    preparedStatement = connection.prepareStatement(QUERIES[2]); 
  
                    preparedStatement.setString(1, enrollment);        
                    ResultSet rs = preparedStatement.executeQuery(); 
                    student = new PojoStudent(); 

                    while (rs.next()) { 
                        student.setEnrollment(rs.getString("std_enrollment"));             
                        student.setName(rs.getString("std_name")); 
                        student.setLastName(rs.getString("std_lastname")); 
                        student.setCareer(rs.getString("std_career")); 
                        student.setCourse_id(rs.getInt("crs_id"));
                    } 
                    
                    response = true;
               } catch (SQLException ex) {
                   Logger.getLogger(DaoCourse.class.getName()).log(Level.SEVERE, null, ex);
               }
               
               return response;
           }  
        };
        connection.execute(t);
        return student;
    }

    @Override
    public List<PojoStudent> readAll() throws SQLException {
        TransactionDB t;
        ls = new ArrayList<>();
                
        t = new TransactionDB<PojoStudent>() {
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                try {
                    PojoStudent student;
                    
                    preparedStatement = connection.prepareStatement(QUERIES[3]); 
                    ResultSet rs = preparedStatement.executeQuery();
                   
                    while (rs.next()){
                        student = new PojoStudent();
                        student.setEnrollment(rs.getString("std_enrollment"));             
                        student.setName(rs.getString("std_name")); 
                        student.setLastName(rs.getString("std_lastname")); 
                        student.setCareer(rs.getString("std_career")); 
                        student.setCourse_id(rs.getInt("crs_id"));

                        ls.add(student);
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
    public boolean update(PojoStudent student, String enrollment) throws SQLException {        
         TransactionDB t;
        boolean response;
        
        t = new TransactionDB<PojoStudent>(student){
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                try {
                    preparedStatement = connection.prepareStatement(QUERIES[4]); 
        
                    System.out.println(student.getCareer());

                    preparedStatement.setString(1, student.getCareer()); 
                    preparedStatement.setString(2, enrollment);

                    preparedStatement.executeUpdate();

                    System.out.println("Actualizado");
                    
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

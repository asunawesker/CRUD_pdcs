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

/**
 *
 * @author asunawesker
 */
public class DaoStudent implements IDaoGeneral<PojoStudent, String>{
    
    private Connection connection;
    private PreparedStatement ps;
    private List<PojoStudent> ls;     
    
    public DaoStudent() throws SQLException{
        connection = ConnectDB.getInstance().getConnection();
        ls = new ArrayList();
        
    }
    
    private final String[] QUERIES = {
        "INSERT INTO students (std_enrollment, std_name, std_lastname, std_career, crs_id) VALUES (?, ?, ?, ?, ?)",
        "DELETE FROM students WHERE std_enrollment =  ?",
        "SELECT * FROM students WHERE std_enrollment = ?",
        "SELECT * FROM students",
        "UPDATE students SET std_career=? WHERE (std_enrollment = ?)"
    };

    @Override
    public int create(PojoStudent student) throws SQLException {
        
        ps = connection.prepareStatement(QUERIES[0]); 
        
        ps.setString(1, student.getEnrollment()); 
        ps.setString(2, student.getName()); 
        ps.setString(3, student.getLastName()); 
        ps.setString(4, student.getCareer()); 
        ps.setInt(5, student.getCourse_id()); 
        
        int rows = ps.executeUpdate(); 
        
        System.out.println("Creado");
        
        // Devuelve valor de filas afectadas
        return rows;
    }

    @Override
    public void delete(String student) throws SQLException {
        ps = connection.prepareStatement(QUERIES[1]); 
        
        ps.setString(1, student); 
        ps.executeUpdate(); 
        
        System.out.println("Eliminado");
    }

    public PojoStudent readSingle(String student) throws SQLException {
        ps = connection.prepareStatement(QUERIES[2]); 
  
        ps.setString(1, student);        
        ResultSet rs = ps.executeQuery(); 
        PojoStudent s = new PojoStudent(); 
  
        while (rs.next()) { 
            s.setEnrollment(rs.getString("std_enrollment"));             
            s.setName(rs.getString("std_name")); 
            s.setLastName(rs.getString("std_lastname")); 
            s.setCareer(rs.getString("std_career")); 
            s.setCourse_id(rs.getInt("crs_id"));
        } 
  
        return s;
    }

    @Override
    public List<PojoStudent> readAll() throws SQLException {
        
        ps = connection.prepareStatement(QUERIES[3]); 
        ResultSet rs = ps.executeQuery();
        PojoStudent student; 
              
        while (rs.next()){
            student = new PojoStudent();
            student.setEnrollment(rs.getString("std_enrollment"));             
            student.setName(rs.getString("std_name")); 
            student.setLastName(rs.getString("std_lastname")); 
            student.setCareer(rs.getString("std_career")); 
            student.setCourse_id(rs.getInt("crs_id"));
            
            ls.add(student);
        } 
        
        return ls; 
    }

    @Override
    public int update(PojoStudent student, String id) throws SQLException {        
        ps = connection.prepareStatement(QUERIES[4]); 
        
        System.out.println(student.getCareer());
        
        ps.setString(1, student.getCareer()); 
        ps.setString(2, id);
        
        int rows = ps.executeUpdate();
        
        System.out.println("Actualizado");
        
        return rows;
    }
    
}

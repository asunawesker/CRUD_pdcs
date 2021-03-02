/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tstudentlate file, choose Tools | Tstudentlates
 * and open the tstudentlate in the editor.
 */
package dao;

import dao.StudentDao;
import util.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Student;

/**
 *
 * @author asunawesker
 */
public class StudentDaoImplementation implements StudentDao{
    
    private Connection connection;
    private PreparedStatement ps;
    private List<Student> ls;     
    
    public StudentDaoImplementation() throws SQLException{
        connection = ConnectDB.getInstance().getConnection();
        ls = new ArrayList();
        
    }
    
    private final String[] QUERIES = {
        "INSERT INTO estudiantes (st_matricula, st_nombre, st_carrera) VALUES (?, ?, ?)",
        "DELETE FROM estudiantes WHERE st_id = ?",
        "SELECT * FROM estudiantes WHERE st_id = ?",
        "SELECT * FROM estudiantes",
        "UPDATE estudiantes SET st_nombre=?, st_matricula=?, st_carrera=? WHERE (st_id = ?)"
    };

    @Override
    public int create(Student student) throws SQLException {
        
        ps = connection.prepareStatement(QUERIES[0]); 
        
        ps.setString(1, student.getMatricula()); 
        ps.setString(2, student.getNombre()); 
        ps.setString(3, student.getCarrera()); 
        
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
    public Student readSingle(int id) throws SQLException {
        ps = connection.prepareStatement(QUERIES[2]); 
  
        ps.setInt(1, id);        
        ResultSet rs = ps.executeQuery(); 
        Student student = new Student(); 
  
        while (rs.next()) { 
            student.setId(rs.getInt("st_id")); 
            student.setMatricula(rs.getString("st_matricula")); 
            student.setNombre(rs.getString("st_nombre")); 
            student.setCarrera(rs.getString("st_carrera")); 
        } 
  
        return student;
    }

    @Override
    public List<Student> readAll() throws SQLException {
        
        ps = connection.prepareStatement(QUERIES[3]); 
        ResultSet rs = ps.executeQuery();
        Student student; 
              
        while (rs.next()){
//            student = new Student();
//            student.setId(rs.getInt("st_id"));
//            student.setMatricula(rs.getString("st_matricula"));
//            student.setNombre(rs.getString("st_nombre"));
//            student.setCarrera(rs.getString("st_carrera"));
//            
//            ls.add(student);
            ls.add(new Student(rs.getInt("st_id"), rs.getString("st_matricula"), rs.getString("st_nombre"), rs.getString("st_carrera"))); 
        } 
        
        return ls; 
    }

    @Override
    public int update(Student student, int id) throws SQLException {        
        ps = connection.prepareStatement(QUERIES[4]); 
        
        ps.setString(1, student.getNombre()); 
        ps.setString(2, student.getMatricula()); 
        ps.setString(3, student.getCarrera()); 
        ps.setInt(4, id);
        
        int rows = ps.executeUpdate();
        
        return rows;
    }
    
}

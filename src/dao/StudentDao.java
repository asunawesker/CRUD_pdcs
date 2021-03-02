/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Student;

/**
 *
 * @author asunawesker
 */
public interface StudentDao {
    
    public int create(Student student)
        throws SQLException;
    
    public void delete(int id) 
        throws SQLException; 
    
    public Student readSingle(int id) 
        throws SQLException; 
    
    public List<Student> readAll() 
        throws SQLException; 
    
    public int update(Student student, int id) 
        throws SQLException;
    
}

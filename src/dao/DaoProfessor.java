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
import pojo.PojoProfessor;
import pojo.PojoProfessor;
import singleton.ConnectDB;

/**
 *
 * @author asunawesker
 */
public class DaoProfessor implements IDaoGeneral<PojoProfessor, String> {

    private Connection connection;
    private PreparedStatement ps;
    private List<PojoProfessor> ls;     
    
    public DaoProfessor() throws SQLException{
        connection = ConnectDB.getInstance().getConnection();
        ls = new ArrayList();
        
    }
    
    private final String[] QUERIES = {
        "INSERT INTO professors (pfs_idcard, pfs_name, pfs_lastname, pfs_career, crs_id) VALUES (?, ?, ?, ?, ?)",
        "DELETE FROM professors WHERE pfs_idcard = ?",
        "SELECT * FROM professors WHERE pfs_idcard = ?",
        "SELECT * FROM professors",
        "UPDATE professors SET pfs_name=?, pfs_lastname=? WHERE (pfs_idcard = ?)"
    };

    @Override
    public int create(PojoProfessor professor) throws SQLException {
        
        ps = connection.prepareStatement(QUERIES[0]); 
        
        ps.setString(1, professor.getIdCard()); 
        ps.setString(2, professor.getName()); 
        ps.setString(3, professor.getLastName()); 
        ps.setString(4, professor.getCareer()); 
        ps.setInt(5, professor.getCourse_id()); 
        
        int rows = ps.executeUpdate(); 
        
        System.out.println("Creado");
        
        // Devuelve valor de filas afectadas
        return rows;
    }

    @Override
    public void delete(String id) throws SQLException {
        ps = connection.prepareStatement(QUERIES[1]); 
        
        ps.setString(1, id); 
        ps.executeUpdate(); 
    }

    @Override
    public PojoProfessor readSingle(String id) throws SQLException {
        ps = connection.prepareStatement(QUERIES[2]); 
  
        ps.setString(1, id);        
        ResultSet rs = ps.executeQuery(); 
        PojoProfessor professor = new PojoProfessor(); 
  
        while (rs.next()) { 
            professor.setIdCard(rs.getString("pfs_idcard"));             
            professor.setName(rs.getString("std_name")); 
            professor.setLastName(rs.getString("std_lastname")); 
            professor.setCareer(rs.getString("std_career")); 
            professor.setCourse_id(rs.getInt("crs_id"));
        } 
  
        return professor;
    }

    @Override
    public List<PojoProfessor> readAll() throws SQLException {
        
        ps = connection.prepareStatement(QUERIES[3]); 
        ResultSet rs = ps.executeQuery();
        PojoProfessor professor; 
              
        while (rs.next()){
            professor = new PojoProfessor();
            professor.setIdCard(rs.getString("pfs_idcard"));             
            professor.setName(rs.getString("std_name")); 
            professor.setLastName(rs.getString("std_lastname")); 
            professor.setCareer(rs.getString("std_career")); 
            professor.setCourse_id(rs.getInt("crs_id"));
            
            ls.add(professor);
        } 
        
        return ls; 
    }

    @Override
    public int update(PojoProfessor professor, String id) throws SQLException {        
        ps = connection.prepareStatement(QUERIES[4]); 
        
        ps.setString(1, professor.getName()); 
        ps.setString(2, professor.getLastName()); 
        ps.setString(3, id);
        
        int rows = ps.executeUpdate();
        
        return rows;
    }
    
}

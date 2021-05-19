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
import pojo.PojoProfessor;
import singleton.ConnectDB;
import singleton.TransactionDB;

/**
 *
 * @author asunawesker
 */
public class DaoProfessor implements IDaoGeneral<PojoProfessor, String> {
    
    private PreparedStatement  preparedStatement;
    private ConnectDB connection ;
    private List <PojoProfessor> ls;
    private PojoProfessor professor;

    public DaoProfessor() throws SQLException {
        connection = ConnectDB.getInstance();        
    } 
    
   private final String[] QUERIES = {
        "INSERT INTO professors (pfs_idcard, pfs_name, pfs_lastname, pfs_career, crs_id) VALUES (?, ?, ?, ?, ?)",
        "DELETE FROM professors WHERE pfs_idcard = ?",
        "SELECT * FROM professors WHERE pfs_idcard = ?",
        "SELECT * FROM professors",
        "UPDATE professors SET pfs_career = ? WHERE (pfs_idcard = ?)"
    };

    @Override
    public boolean create(PojoProfessor professor) throws SQLException {
        boolean response;
        TransactionDB t;
        
        t = new TransactionDB<PojoProfessor>(professor) {
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                
                try {
                    preparedStatement = connection.prepareStatement(QUERIES[0]); 
        
                    preparedStatement.setString(1, professor.getIdCard()); 
                    preparedStatement.setString(2, professor.getName()); 
                    preparedStatement.setString(3, professor.getLastName()); 
                    preparedStatement.setString(4, professor.getCareer()); 
                    preparedStatement.setInt(5, professor.getCourse_id()); 

                    preparedStatement.executeUpdate(); 
                    
                    System.out.println("PojoCourse guardada");
                     
                     response = true;                     
                 } catch (SQLException ex) {
                     Logger.getLogger(DaoProfessor.class.getName()).log(Level.SEVERE, null, ex);
                 }                 
                 return response;
             }
         };         
         response = connection.execute(t);
         return response;               
    }

    @Override
    public boolean delete(String id) throws SQLException {
         boolean response;
        TransactionDB t;        
               
         t = new TransactionDB<PojoProfessor>(){
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                
                try {
                    preparedStatement = connection.prepareStatement(QUERIES[1]); 
        
                    preparedStatement.setString(1, id); 
                    preparedStatement.executeUpdate(); 
                    
                    System.out.println("Persona eliminada");
                    
                    response = true;
                } catch (SQLException ex) {
                    Logger.getLogger(DaoProfessor.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return response;
            }            
        };        
        response = connection.execute(t);        
        return response;
    }

    @Override
    public PojoProfessor readSingle(String id) throws SQLException {
         boolean response;
        TransactionDB t;    
        
        t = new TransactionDB<PojoProfessor>(){
           @Override
           public boolean execute(Connection connection) {
               boolean response = false;
               
               try {
                    preparedStatement = connection.prepareStatement(QUERIES[2]); 
  
                    preparedStatement.setString(1, id);        
                    ResultSet rs = preparedStatement.executeQuery(); 
                    professor = new PojoProfessor(); 

                    while (rs.next()) { 
                        professor.setIdCard(rs.getString("pfs_idcard"));             
                        professor.setName(rs.getString("pfs_name")); 
                        professor.setLastName(rs.getString("pfs_lastname")); 
                        professor.setCareer(rs.getString("pfs_career")); 
                        professor.setCourse_id(rs.getInt("crs_id"));
                    } 
                    
                    response = true;
               } catch (SQLException ex) {
                   Logger.getLogger(DaoCourse.class.getName()).log(Level.SEVERE, null, ex);
               }
               
               return response;
           }  
        };
        connection.execute(t);  
        return professor;
    }

    @Override
    public List<PojoProfessor> readAll() throws SQLException {
        TransactionDB t;
        ls = new ArrayList<>();
                
        t = new TransactionDB<PojoProfessor>() {
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                try {
                    PojoProfessor course;
                    
                    preparedStatement = connection.prepareStatement(QUERIES[3]); 
                    ResultSet rs = preparedStatement.executeQuery();
                    PojoProfessor professor; 

                    while (rs.next()){
                        professor = new PojoProfessor();
                        professor.setIdCard(rs.getString("pfs_idcard"));             
                        professor.setName(rs.getString("pfs_name")); 
                        professor.setLastName(rs.getString("pfs_lastname")); 
                        professor.setCareer(rs.getString("pfs_career")); 
                        professor.setCourse_id(rs.getInt("crs_id"));

                        ls.add(professor);
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
    public boolean update(PojoProfessor professor, String id) throws SQLException {      
         TransactionDB t;
        boolean response;
        
        t = new TransactionDB<PojoProfessor>(professor){
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                try {
                    preparedStatement = connection.prepareStatement(QUERIES[4]); 
        
                    preparedStatement.setString(1, professor.getCareer()); 
                    preparedStatement.setString(2, id);

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

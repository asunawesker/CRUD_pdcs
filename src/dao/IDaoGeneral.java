/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author asunawesker
 * @param <T>
 */
public interface IDaoGeneral <T, V> {
    
    public int create(T pojo)
        throws SQLException;
    
    public void delete(V id) 
        throws SQLException; 
    
    public T readSingle(V id) 
        throws SQLException; 
    
    public List<T> readAll() 
        throws SQLException; 
    
    public int update(T pojo, V id) 
        throws SQLException;
    
}

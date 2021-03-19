/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;

/**
 *
 * @author asunawesker
 */
public class FactoryDao {
    
    public enum TypeDAO {STUDENT, PROFESSOR, COURSE};
    
    public static IDaoGeneral create(TypeDAO type) throws SQLException{
        
        IDaoGeneral dao = null;
        
        switch(type){
            case COURSE:
                dao = new DaoCourse();
                break;
            case STUDENT:
                dao = new DaoStudent();
                break;
            case PROFESSOR:
                dao = new DaoProfessor();
                break;
        }
        return dao;
    } 
    
}

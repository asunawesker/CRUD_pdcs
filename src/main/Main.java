/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tstudentlate file, choose Tools | Tstudentlates
 * and open the tstudentlate in the editor.
 */
package main;

import dao.FactoryDao;
import dao.IDaoGeneral;
import java.sql.SQLException;
import pojo.PojoCourse;
import pojo.PojoProfessor;
import pojo.PojoStudent;
import view.StudentView;

/**
 *
 * @author asunawesker
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        //StudentView view = new StudentView();
        //view.setVisible(true);   
        
//        PojoCourse course = new PojoCourse();
//        
//        course.setId(1);
//        course.setName("Principios de construcción de software");
//        course.setCredits(10);
//        
//        IDaoGeneral<PojoCourse> dao = FactoryDao.create(FactoryDao.TypeDAO.COURSE);
//        dao.create(course);

//        PojoProfessor professor = new PojoProfessor();
//        
//        professor.setIdCard("123456789");
//        professor.setName("Gabriel");
//        professor.setLastName("Rodriguez");
//        professor.setCareer("Ingeniero informático");
//        professor.setCourse_id(1);
//        
//        IDaoGeneral<PojoProfessor> dao = FactoryDao.create(FactoryDao.TypeDAO.PROFESSOR);
//        dao.create(professor);
//        
        PojoStudent student = new PojoStudent();
        
        student.setEnrollment("S19004913");
        student.setName("Iraís");
        student.setLastName("Aguirre");
        student.setCareer("ISW");
        student.setCourse_id(1);
        
        IDaoGeneral<PojoStudent> dao = FactoryDao.create(FactoryDao.TypeDAO.STUDENT);
        dao.create(student);
        
    }
    
}

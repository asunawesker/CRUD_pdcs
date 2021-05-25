/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.FactoryDao;
import dao.IDaoGeneral;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.PojoCourse;

/**
 *
 * @author asunawesker
 */
public class CourseSearch extends javax.swing.JInternalFrame {

    /**
     * Creates new form CourseSearch
     */
    
    PojoCourse course;
    IDaoGeneral<PojoCourse, Integer> dao;
    List<PojoCourse> ls;
    
    public CourseSearch() throws SQLException {
        this.course = new PojoCourse();
        this.dao = FactoryDao.create(FactoryDao.TypeDAO.COURSE);
        this.ls = dao.readAll();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnVerUno = new javax.swing.JButton();
        btnVerTodos = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setTitle("Buscar curso");
        setPreferredSize(new java.awt.Dimension(700, 500));
        setRequestFocusEnabled(false);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("ID");

        btnVerUno.setText("Ver uno");
        btnVerUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerUnoActionPerformed(evt);
            }
        });

        btnVerTodos.setText("Ver todos");
        btnVerTodos.setDefaultCapable(false);
        btnVerTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTodosActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Créditos"
            }
        ));
        table.setFillsViewportHeight(true);
        jScrollPane2.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(btnVerUno, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(btnVerTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jLabel8)
                        .addGap(24, 24, 24)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerUno)
                    .addComponent(btnVerTodos))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerUnoActionPerformed
        // TODO add your handling code here:
        ls.clear();
        
        int id =Integer.parseInt(txtId.getText());

        try {
            PojoCourse course = dao.readSingle(id);
            ls.add(course);
//            System.out.println(
//                "\nId: " + course.getId()+
//                "\nNombre: "  + course.getName()+
//                "\nCréditos: "  + course.getCredits()+
//                "\n");
            showTable(ls);
        } catch (SQLException ex) {
            Logger.getLogger(StudentCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVerUnoActionPerformed

    private void btnVerTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodosActionPerformed
        // TODO add your handling code here:
        ls.clear();

        try {
            ls = dao.readAll();//
//            ls.forEach((course) -> {
//                System.out.println(
//                    "\nId: " + course.getId()+
//                    "\nNombre: "  + course.getName()+
//                    "\nCréditos: "  + course.getCredits()+
//                    "\n");
//            });            
            showTable(ls);            
        } catch (SQLException ex) {
            Logger.getLogger(StudentCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVerTodosActionPerformed
        
    private void showTable(List<PojoCourse> ls){
        Object matrix [][] = new Object[ls.size()][3];
        
        for (int i = 0; i < ls.size(); i++) {
            matrix [i][0] = ls.get(i).getId();
            matrix [i][1] = ls.get(i).getName();
            matrix [i][2] = ls.get(i).getCredits();
        }
        
         table.setModel(new javax.swing.table.DefaultTableModel(
            matrix,
            new String [] {
                "ID", "Nombre", "Créditos"
            }
        ));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerTodos;
    private javax.swing.JButton btnVerUno;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}

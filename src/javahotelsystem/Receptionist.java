/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahotelsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Esraa sayed
 */
public class Receptionist{
    
    My_Connection my_connection = new My_Connection();
    
    
    
    public boolean addReceptionist(String username, String password){
        PreparedStatement st;
        String addQuery = "INSERT INTO `users`(`username`, `password`) VALUES (?,?)";
        
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setString(1,username);
            st.setString(2,password);

                return (st.executeUpdate()>0);
            
             
        } catch (SQLException ex) {
            Logger.getLogger(Receptionist.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
//function to edit the selected client
    public boolean EditReceptionist(int id , String username, String password){
        
        PreparedStatement st;
        String editQuery = "UPDATE `users` SET `username`=?,`password`=? WHERE `id`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(editQuery);
            
            st.setString(1,username);
            st.setString(2,password);
            
            return (st.executeUpdate()>0);
            
        } catch (SQLException ex) {
            Logger.getLogger(Receptionist.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //function to remove the selected clients
    public boolean removeReceptionist(int id){
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `users` WHERE `id`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(deleteQuery);
            
            
            st.setInt(1,id);
            
            return (st.executeUpdate()>0);
            
        } catch (SQLException ex) {
            Logger.getLogger(Receptionist.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //function to fill the table with the clients in the DATABASE
    public void fillReceptionistJTable(JTable table) {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `users`";
        try{
        ps = my_connection.createConnection().prepareStatement(selectQuery);
        rs = ps.executeQuery();
        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
        
        Object[] row;
        
        while(rs.next()){
            row = new Object[3];
            row[0] = rs.getString(1);
            row[1] = rs.getString(2);
            row[2] = rs.getString(3);
            tableModel.addRow(row);
          
        }
        } catch (SQLException ex){
            Logger.getLogger(Receptionist.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    
}

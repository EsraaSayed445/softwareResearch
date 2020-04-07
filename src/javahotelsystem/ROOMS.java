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
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aalaa-pc
 */
public class ROOMS {
    
    My_Connection my_connection = new My_Connection();
    
    public void fillRooms_Type_JTable(JTable table) {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `type`";
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
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    //filling a acombobox with the room type
    public void fillRooms_Type_JCombobox(JComboBox combobox) {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `type`";
        try{
        ps = my_connection.createConnection().prepareStatement(selectQuery);
        rs = ps.executeQuery();
        
        
        while(rs.next()){
            combobox.addItem(rs.getString(1));
          
        }
        } catch (SQLException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    //adding a new room
    
    public boolean addRoom(int number, int type, String phone){
        
        
        
        PreparedStatement st;
        ResultSet rs;
        String addQuery = "INSERT INTO `room`(`r_number`, `type`, `phone`, `reserved`) VALUES ([?,?,?,?)";
        
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setInt(1,number);
            st.setInt(2,type);
            st.setString(3,phone);
            //when adding a new room the reserved column'll be set to noo
            st.setString(4,"NO");
            
            if(st.executeUpdate()>0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
}

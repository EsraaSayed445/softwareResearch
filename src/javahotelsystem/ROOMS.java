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
     public void fillRoomsJTable(JTable table) {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `room`";
        try{
        ps = my_connection.createConnection().prepareStatement(selectQuery);
        rs = ps.executeQuery();
        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
        
        Object[] row;
        
        while(rs.next()){
            row = new Object[4];
            row[0] = rs.getInt(1);
            row[1] = rs.getInt(2);
            row[2] = rs.getString(3);
            row[3] = rs.getString(4);
              
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
        String addQuery = "INSERT INTO `room` (`r_number`, `type`, `phone`, `reserved`) VALUES (?,?,?,?)";
        
        
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setInt(1,number);
            st.setInt(2,type);
            st.setString(3,phone);
            //when adding a new room 
            //the reserved column'll be set to noo
            //the reserved colum mean if this room is free or not 
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
     public boolean editRoom(int number , int type, String phone, String isReserved){
        
        
        
        PreparedStatement st;
        String addQuery = "UPDATE `room` SET `type`=?,`phone`=?,`reserved`=? WHERE `r_number`=?";
                try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setInt(1,type);
            st.setString(2,phone);
            st.setString(3,isReserved);
            st.setInt(4,number);            
                return (st.executeUpdate()>0);
            
             
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
     
 //function to remove the selected Room
    public boolean removeRoom(int roomNumber){
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `clients` WHERE `r_number`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(deleteQuery);
            
            
            st.setInt(1,roomNumber);
            
            return (st.executeUpdate()>0);
            
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    //fun to get set a room reserved or not
    public boolean setRoomToReserved(int number,String isReserved){
        
        PreparedStatement st;
        String addQuery = "UPDATE `room` SET `reserved`=? WHERE `r_number`=?";
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setString(3,isReserved);
            st.setInt(4,number);            
                return (st.executeUpdate()>0);
            
             
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
     //function to check if the room is already reserved
    public String isRoomReserved(int number){
        
        PreparedStatement st;
        ResultSet rs;
        String addQuery = "SELECT `reserved` FROM `room` WHERE `r_number`=?";
        try {
            
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setInt(1,number);
            
            rs = st.executeQuery();
            
            if(rs.next()){
                return rs.getString(1);
            }else{
                return "";
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        
        
    }
     
    
    
}

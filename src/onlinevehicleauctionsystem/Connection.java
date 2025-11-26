/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlinevehicleauctionsystem;
import java.sql.*;
import java.sql.DriverManager;
/**
 *
 * @author insha
 */
public class Connection {
 java.sql.Connection conn;
public static java.sql.Connection ConnecrDb(){
  try{
     
     java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/isp", "root", "root");
     return conn;
  } catch(Exception e){
      
  }
        return null;
}
}

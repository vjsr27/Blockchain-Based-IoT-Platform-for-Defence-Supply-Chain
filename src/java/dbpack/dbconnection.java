/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbpack;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author user
 */
public class dbconnection {
    
    public dbconnection()
    {
        
    }
    
    public Connection getconnection()
    {   
        Connection con=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/defencesupplychain", "root", "root");
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        return con;
    }
    
}

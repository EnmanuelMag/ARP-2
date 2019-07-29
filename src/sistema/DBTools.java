/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import constantes.Constantes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Josue
 */
public class DBTools {
    
    public static Connection getConnection() throws SQLException{
        Connection con=DriverManager.getConnection(Constantes.DataBaseURL, Constantes.DataBaseUser, Constantes.DataBasePass);
        return con;
    }
    
}

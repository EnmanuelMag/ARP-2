/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Josue
 */
public class FacturaDB {
    
    public LinkedList<Factura> getAllFacturas( ) throws SQLException{
        try (ResultSet rst = DBTools.getConnection().createStatement().executeQuery("select * from factura;")) {
            while (rst.next()) {
                System.out.println(rst.getString("film_id"));
            }
        }catch(SQLException ex){
           throw ex;
        }
        return null;
    }
    
    
}

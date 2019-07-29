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
public class ClienteDB {
    
    public static LinkedList<Cliente> getAllCliente() throws SQLException{
        LinkedList<Cliente> clientes=new LinkedList<>();
        String query="select * from cliente";
        try (ResultSet rst = DBTools.getConnection().createStatement().executeQuery(query)) {
            while (rst.next()) {
                String nombres=rst.getString("nombres");
                String apellidos=rst.getString("apellidos");
                int cliente_id=rst.getInt("cliente_id");
                clientes.add(new Cliente(cliente_id,nombres,apellidos));
            }
            return clientes;
        }catch(SQLException ex){
           throw ex;
        }
    }
    
}

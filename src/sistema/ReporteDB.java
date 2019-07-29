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
public class ReporteDB {
    
    public static LinkedList<VentaPorCliente> getVentasClientes(String desde, String hasta) throws SQLException{
        LinkedList<VentaPorCliente> lista=new LinkedList<>();
        String query="select * from cliente as c join "
                + "(select *,sum(total)as totalCliente from"
                + "(select * from, (subtotal*iva)+subtotal as total from factura as f where f.fecha>="+desde+" and f.fecha<="+hasta+" )"
        + " as sq1 group by sq1.cliente_id) as sq2 on c.cliente_id = sq2.cliente_id";
        try (ResultSet rst = DBTools.getConnection().createStatement().executeQuery(query)) {
            while (rst.next()) {
               String nombres=rst.getString("nombres");
                String apellidos=rst.getString("apellidos");
                Double total=Double.parseDouble(rst.getString("total"));
                lista.add(new VentaPorCliente(nombres,apellidos,total));
            }
            return lista;
        }catch(SQLException ex){
           throw ex;
        }
    }
    
    public static LinkedList<VentaPorCliente> getVentaPorCliente(Cliente c,String desde,String hasta) throws SQLException{
        LinkedList<VentaPorCliente> lista=new LinkedList<>();
        String query="select * from cliente as c join "
                + "(select *,sum(total)as totalCliente from"
                + "(select * from, (subtotal*iva)+subtotal as total from factura as f where f.fecha>="+desde+" and f.fecha<="+hasta+" )"
        + " as sq1 group by sq1.cliente_id) as sq2 on c.cliente_id = sq2.cliente_id where c.cliente_id="+String.valueOf(c.getCliente_id());
        try (ResultSet rst = DBTools.getConnection().createStatement().executeQuery(query)) {
            while (rst.next()) {
               String nombres=rst.getString("nombres");
                String apellidos=rst.getString("apellidos");
                Double total=Double.parseDouble(rst.getString("total"));
                lista.add(new VentaPorCliente(nombres,apellidos,total));
            }
            return lista;
        }catch(SQLException ex){
           throw ex;
        }
    }
    
}

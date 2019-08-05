/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.time.LocalDate;

/**
 *
 * @author Josue
 */
public class Pedido {
    
    private Cliente cliente;
    private LocalDate fecha;

    public Pedido() {
    }
    
    

    public Pedido(Cliente cliente, LocalDate fecha) {
        this.cliente = cliente;
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    
}

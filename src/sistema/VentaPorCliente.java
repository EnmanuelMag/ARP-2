/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

/**
 *
 * @author Josue
 */
public class VentaPorCliente {
    
    private String nombresCliente;
    private String apellidosCliente;
    private double monto;

    public VentaPorCliente() {
    }

    public VentaPorCliente(String nombresCliente, String apellidosCliente, double monto) {
        this.nombresCliente = nombresCliente;
        this.apellidosCliente = apellidosCliente;
        this.monto = monto;
    }

    public String getNombresCliente() {
        return nombresCliente;
    }

    public void setNombresCliente(String nombresCliente) {
        this.nombresCliente = nombresCliente;
    }

    public String getApellidosCliente() {
        return apellidosCliente;
    }

    public void setApellidosCliente(String apellidosCliente) {
        this.apellidosCliente = apellidosCliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    
    
}

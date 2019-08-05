/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.util.LinkedList;

/**
 *
 * @author Josue
 */
public class Cliente {
    
    private int cliente_id;
    private String tipo;
    private String nombre;
    private String apellidos;
    private String cedula;
    private String direccion;
    private String telefono;

    public Cliente() {
    }

    public Cliente(int cliente_id, String nombre, String apellios, String cedula, String direccion, String telefono) {
        this.cliente_id = cliente_id;
        this.nombre = nombre;
        this.apellidos = apellios;
        this.cedula = cedula;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Cliente(int cliente_id, String tipo, String nombre, String apellios, String cedula, String direccion, String telefono) {
        this.cliente_id = cliente_id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellidos = apellios;
        this.cedula = cedula;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Cliente(int cliente_id, String nombre, String apellidos) {
        this.cliente_id = cliente_id;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    
    
    

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellios() {
        return apellidos;
    }

    public void setApellios(String apellios) {
        this.apellidos = apellios;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", apellios=" + apellidos + '}';
    }
    
    public String getNombreCompleto(){
        return this.nombre+" "+this.apellidos;
    }
    
    
    
}

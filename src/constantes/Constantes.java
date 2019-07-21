/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constantes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;

/**
 *
 * @author emman
 */
public class Constantes {
    
    
    public static final Insets ESPACIADO = new Insets(20,20,20,20);
    public static final Insets BOTON = new Insets(15,15,15,15);
    public static final Insets BOTONA = new Insets(5,5,5,5);
    public static final ObservableList<String> TABLAS = FXCollections.observableArrayList("Cliente", 
            "Factura", "Producto", "Categoria", "Proveedor", "Materia Prima");
    
}

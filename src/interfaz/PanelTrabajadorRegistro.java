/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import static constantes.Constantes.ESPACIADO;
import constantes.Metodos;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author emman
 */
public class PanelTrabajadorRegistro extends PanelGenerico{
    
    public PanelTrabajadorRegistro(Stage s){
        super(s);
        super.border.setCenter(crearFormulario());
    }
    
    
    public HBox crearFormulario(){
       
        
        JFXTextField nombre = new JFXTextField();
        nombre.setPromptText("Nombre");
        nombre.setLabelFloat(true);
        
        JFXTextField apellido = new JFXTextField();
        apellido.setPromptText("Apellido");
        apellido.setLabelFloat(true);
                
        JFXTextField cedula = new JFXTextField();
        cedula.setPromptText("Cédula");
        cedula.setLabelFloat(true);
        
        JFXTextField descuento = new JFXTextField();
        descuento.setPromptText("Descuento");
        descuento.setLabelFloat(true);
        
        VBox cont1 = new VBox(nombre, apellido, cedula, descuento);
        cont1.setSpacing(65);
        cont1.setMaxWidth(400);
        cont1.setAlignment(Pos.CENTER);
        
        JFXComboBox tipoBox = new JFXComboBox();
        //categBox.setItems(); aqui deberiamos hacer un Query y traer todoso lo nombres de las categorias
        HBox contBox = Metodos.crearPanel(new Label("Tipo"), tipoBox);
        
        JFXComboBox estadoBox = new JFXComboBox();
        //categBox.setItems(); aqui deberiamos hacer un Query y traer todoso lo nombres de las categorias
        HBox contBox2 = Metodos.crearPanel(new Label("Estado"), estadoBox);
        
        JFXTextField saldo = new JFXTextField();
        saldo.setPromptText("Saldo");
        saldo.setLabelFloat(true);
        
        VBox cont2 = new VBox(contBox, contBox2, saldo);
        cont2.setSpacing(65);
        cont2.setMaxWidth(400);
        cont2.setAlignment(Pos.CENTER);
        
        HBox contMain = Metodos.crearPanel(cont1, cont2);
        contMain.setSpacing(40);
        contMain.setAlignment(Pos.CENTER);
        
        JFXButton registrar = new JFXButton("Registrar");
        VBox c = new VBox(registrar);
        c.setAlignment(Pos.CENTER);
        border.setBottom(c);
        
        return contMain;
    }
    
    public class ManejadorVolver implements EventHandler{
        
        
        @Override
        public void handle(Event event) {
            
            PanelRegistro pR = new PanelRegistro(stage);
            stage.setScene(pR.getScene());
        }
    }
    
    public Scene getScene(){
        
        Scene escena=new Scene(getRoot(),1280,720);
        escena.getStylesheets().add(getRutaCssFile());
        return escena;
    }
    
    public StackPane getRoot(){
        return root;
    }
    
    public String getRutaCssFile(){
        return PanelRegistro.class.getResource("/recursos/estiloFrancis.css").toExternalForm();
    }
}

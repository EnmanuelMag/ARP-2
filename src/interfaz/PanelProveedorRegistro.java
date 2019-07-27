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
import static constantes.Constantes.TEXTS;
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
public class PanelProveedorRegistro extends PanelGenerico{
    
    public PanelProveedorRegistro(Stage s,StackPane lastRoot){
        super(s,lastRoot);
        super.border.setCenter(crearFormulario());
    }
    
    
    public HBox crearFormulario(){
       
        
        JFXTextField nombre = new JFXTextField();
        nombre.setPromptText("Nombre");
        nombre.setMinWidth(TEXTS);
        nombre.setLabelFloat(true);
        
        JFXTextField ruc = new JFXTextField();
        ruc.setPromptText("RUC");
        ruc.setLabelFloat(true);
        
        VBox cont1 = new VBox(nombre, ruc);
        cont1.setSpacing(90);
        //cont1.setMaxWidth(400);
        cont1.setAlignment(Pos.TOP_LEFT);
        
        JFXTextField direccion = new JFXTextField();
        direccion.setPromptText("Dirección");
        direccion.getStyleClass().add("jfx-texto-largo");
        direccion.setMinWidth(TEXTS);
        direccion.setPrefWidth(TEXTS);
        direccion.setLabelFloat(true);
        
        JFXTextField telefono = new JFXTextField();
        telefono.setPromptText("Teléfono");
        telefono.setMaxWidth(150);
        telefono.setLabelFloat(true);
        
        VBox cont2 = new VBox(direccion, telefono);
        cont2.setSpacing(90);
        //cont2.setMaxWidth(400);
        cont2.setAlignment(Pos.TOP_LEFT);
        
        HBox contMain = Metodos.crearPanel(cont1, cont2);
        contMain.setSpacing(40);
        contMain.setAlignment(Pos.CENTER);
        
        JFXButton registrar = new JFXButton("Registrar");
        VBox c = new VBox(registrar);
        c.setAlignment(Pos.CENTER);
        border.setBottom(c);
        
        HBox contRoot = new HBox(contMain, cont2);
        contRoot.setMaxHeight(200);
        contRoot.setAlignment(Pos.CENTER);
        contRoot.setSpacing(55);
        //contRoot.setMaxWidth(500);
        return contRoot;
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
        return Class.class.getResource("/recursos/estiloFrancis.css").toExternalForm();
    }
}

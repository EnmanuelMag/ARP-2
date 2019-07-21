/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import constantes.Metodos;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author emman
 */
public class PanelProductoRegistro  extends PanelGenerico{
    
    
   
    
    private HBox contMain;
    
    public PanelProductoRegistro(Stage s){
        super(s);
        super.border.setCenter(crearFormulario());
        
    }
    
    
    public HBox crearFormulario(){
       
        
        JFXTextField nombre = new JFXTextField();
        nombre.setPromptText("Nombre");
        nombre.setLabelFloat(true);
        
        JFXTextField marca = new JFXTextField();
        marca.setPromptText("Marca");
        marca.setLabelFloat(true);
        
        JFXTextField sabor = new JFXTextField();
        sabor.setPromptText("Sabor");
        sabor.setLabelFloat(true);
        
        JFXTextField costo = new JFXTextField();
        costo.setPromptText("Costo");
        costo.setLabelFloat(true);
        
       
        VBox cont1 = new VBox(nombre, marca, sabor, costo);
        cont1.setSpacing(65);
        cont1.setMaxWidth(400);
        cont1.setAlignment(Pos.CENTER);
                
        JFXButton examinar = new JFXButton("Examinar");
        HBox contFoto = Metodos.crearPanel(new Label("Foto"), examinar);
        contFoto.setSpacing(15);
        
        JFXComboBox tipoBox = new JFXComboBox();
        //categBox.setItems(); aqui deberiamos hacer un Query y traer todoso lo nombres de las categorias
        HBox contBox = Metodos.crearPanel(new Label("Estado producto"), tipoBox);
        
        JFXTextField desc = new JFXTextField();
        desc.setPromptText("Deescuento");
        desc.setLabelFloat(true);
        
        
        VBox cont2 = new VBox(contFoto, contBox, desc);
        cont2.setSpacing(65);
        cont2.setMaxWidth(400);
        cont2.setAlignment(Pos.CENTER);
        
        contMain = Metodos.crearPanel(cont1, cont2);
        contMain.setSpacing(40);
        contMain.setAlignment(Pos.CENTER);
        contMain.setPadding(new Insets(50,0,0,0));
        
        //ScrollPane sP = new ScrollPane(contMain);
        //sP.setFitToWidth(true);
        //sP.setPrefHeight(300);
        //sP.setPrefWidth(500);
   
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


/*
        FileChooser foto = new FileChooser();
        foto.setTitle("Open Resource File");
        foto.showOpenDialog(super.stage);
*/
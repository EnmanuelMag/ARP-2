/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import static constantes.Constantes.BOTON;
import static constantes.Constantes.ESPACIADO;
import constantes.Metodos;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
public class PanelProductoRegistro {
    
    private BorderPane rootBorder;
    private StackPane root;
    private Stage pStage;
    private HBox contMain;
    
    public PanelProductoRegistro(Stage p){
        this.pStage = p;
        this.rootBorder = new BorderPane();
        this.rootBorder.setPadding(ESPACIADO);
        this.rootBorder.setCenter(crearFormulario());
        this.root = new StackPane(rootBorder);
    }
    
    
    public ScrollPane crearFormulario(){
       
        
        JFXTextField nombre = new JFXTextField();
        nombre.setPromptText("Nombre");
        nombre.setLabelFloat(true);
        
        JFXTextField costo = new JFXTextField();
        costo.setPromptText("Costo");
        costo.setLabelFloat(true);
               
        JFXComboBox categBox = new JFXComboBox();
        //categBox.setItems(); aqui deberiamos hacer un Query y traer todoso lo nombres de las categorias
        HBox contBox = Metodos.crearPanel(new Label("Categoría"), categBox);
        
        VBox cont1 = new VBox(nombre, costo, contBox);
        cont1.setSpacing(65);
        cont1.setMaxWidth(400);
        cont1.setAlignment(Pos.TOP_LEFT);
                
        JFXTextField proveedor = new JFXTextField();
        proveedor.setPromptText("Proveedor");
        proveedor.setLabelFloat(true);
        
        JFXTextField descripcion = new JFXTextField();
        descripcion.setPromptText("Descripción");
        descripcion.setLabelFloat(true);
        
        VBox cont2 = new VBox(proveedor, descripcion);
        cont2.setSpacing(65);
        cont2.setMaxWidth(400);
        cont2.setAlignment(Pos.TOP_LEFT);
        
        contMain = Metodos.crearPanel(cont1, cont2);
        contMain.setSpacing(40);
        contMain.setAlignment(Pos.CENTER);
        contMain.setPadding(new Insets(50,0,0,0));
        
        ScrollPane sP = new ScrollPane(contMain);
        sP.setFitToWidth(true);
        sP.setPrefHeight(300);
        //sP.setPrefWidth(500);
        
        JFXButton registrar = new JFXButton("Registrar");
        VBox c = new VBox(registrar);
        c.setAlignment(Pos.CENTER);
        c.setPadding(BOTON);
        this.rootBorder.setBottom(c);
        
        JFXButton volver = new JFXButton("Volver");
        VBox c2 = new VBox(volver);
        c2.setPadding(BOTON);
        volver.setOnAction(new ManejadorVolver());
        this.rootBorder.setTop(c2);
        
        return sP;
    }
    
    public class ManejadorVolver implements EventHandler{
        
        
        @Override
        public void handle(Event event) {
            
            PanelRegistro pR = new PanelRegistro(pStage);
            pStage.setScene(pR.getScene());
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

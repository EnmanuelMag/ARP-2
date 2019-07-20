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
public class PanelProveedorRegistro {
    
    private BorderPane rootBorder;
    private StackPane root;
    private Stage pStage;
    
    public PanelProveedorRegistro(Stage p){
        this.pStage = p;
        this.rootBorder = new BorderPane();
        this.rootBorder.setPadding(ESPACIADO);
        this.rootBorder.setCenter(crearFormulario());
        this.root = new StackPane(rootBorder);
    }
    
    
    public VBox crearFormulario(){
       
        
        JFXTextField nombre = new JFXTextField();
        nombre.setPromptText("Nombre");
        nombre.setLabelFloat(true);
        
        JFXTextField apellido = new JFXTextField();
        apellido.setPromptText("Apellido");
        apellido.setLabelFloat(true);
        
        VBox cont1 = new VBox(nombre, apellido);
        cont1.setSpacing(65);
        cont1.setMaxWidth(400);
        cont1.setAlignment(Pos.CENTER);
                
        JFXTextField direccion = new JFXTextField();
        direccion.setPromptText("Dirección");
        direccion.setLabelFloat(true);
        
        JFXTextField cedula = new JFXTextField();
        cedula.setPromptText("RUC");
        cedula.setLabelFloat(true);
        
        JFXTextField telefono = new JFXTextField();
        telefono.setPromptText("Teléfono");
        telefono.setLabelFloat(true);
        
        VBox cont2 = new VBox(cedula, telefono);
        cont2.setSpacing(65);
        cont2.setMaxWidth(400);
        cont2.setAlignment(Pos.CENTER);
        
        HBox contMain = Metodos.crearPanel(cont1, cont2);
        contMain.setSpacing(40);
        contMain.setAlignment(Pos.CENTER);
        
        JFXButton registrar = new JFXButton("Registrar");
        VBox c = new VBox(registrar);
        c.setAlignment(Pos.CENTER);
        this.rootBorder.setBottom(c);
        
        JFXButton volver = new JFXButton("Volver");
        VBox c2 = new VBox(volver);
        volver.setOnAction(new ManejadorVolver());
        this.rootBorder.setTop(c2);
        
        VBox contRoot = new VBox(contMain, direccion);
        contRoot.setAlignment(Pos.CENTER);
        contRoot.setSpacing(65);
        contRoot.setMaxWidth(500);
        return contRoot;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static constantes.Constantes.ESPACIADO;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author emman
 */
public class PanelMateriaRegistro {
    
    private BorderPane rootBorder;
    private StackPane root;
    private Stage pStage;
    
    public PanelMateriaRegistro(Stage p){
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
        
        JFXTextField sotck = new JFXTextField();
        sotck.setPromptText("Stock");
        sotck.setLabelFloat(true);
        
        VBox cont1 = new VBox(nombre, sotck);
        cont1.setSpacing(65);
        cont1.setMaxWidth(400);
        cont1.setAlignment(Pos.CENTER);
        JFXButton registrar = new JFXButton("Registrar");
        VBox c = new VBox(registrar);
        c.setAlignment(Pos.CENTER);
        this.rootBorder.setBottom(c);
        
        JFXButton volver = new JFXButton("Volver");
        VBox c2 = new VBox(volver);
        volver.setOnAction(new ManejadorVolver());
        this.rootBorder.setTop(c2);
        
        return cont1;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
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
public class PanelInicio {
    
    private BorderPane rootBorder;
    private StackPane root;
    private Stage pStage;
    
    public PanelInicio(Stage p){
        this.pStage = p;
        this.rootBorder = new BorderPane();
        rootBorder.setPadding(ESPACIADO);
        rootBorder.setCenter(crearBotones());
        this.root = new StackPane(rootBorder); 
    }
    
    
    public VBox crearBotones(){
        
        JFXButton ingreso = new JFXButton("Ingreso");
        ingreso.getStyleClass().add("jfx-button-inicio");
        ingreso.setOnAction(new ManejadorIngreso());
        
        JFXButton consulta = new JFXButton("Consulta");
        consulta.getStyleClass().add("jfx-button-inicio");
        
        JFXButton reportes = new JFXButton("Reportes");
        reportes.getStyleClass().add("jfx-button-inicio");
        
        VBox contenedor = new VBox(ingreso, consulta, reportes);
        contenedor.setSpacing(60);
        contenedor.setAlignment(Pos.CENTER);
        
        return contenedor;
    }
    
    public class ManejadorIngreso implements EventHandler{
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
        return PanelInicio.class.getResource("/recursos/estiloFrancis.css").toExternalForm();
    }
    
}

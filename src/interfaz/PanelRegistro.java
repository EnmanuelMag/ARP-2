/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import static constantes.Constantes.ESPACIADO;
import static constantes.Constantes.TABLAS;
import constantes.Metodos;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tablas.Objeto;

/**
 *
 * @author emman
 */
public class PanelRegistro {
    
    private BorderPane rootBorder;
    private StackPane root;
    private Stage pStage;
    
    
    public PanelRegistro(Stage p){
        this.pStage = p;
        this.rootBorder = new BorderPane();
        this.rootBorder.setPadding(ESPACIADO);
        this.rootBorder.setCenter(crearContenido());
        this.root = new StackPane(rootBorder);
    }
    
    public HBox crearContenido(){
        
        JFXButton volver = new JFXButton("Volver");
        VBox c2 = new VBox(volver);
        volver.setOnAction(new ManejadorVolver());
        this.rootBorder.setTop(c2);
        
        JFXComboBox<String> combo = new JFXComboBox();
        combo.getStyleClass().add("scroll-barT");
        combo.setItems(TABLAS);
        combo.setOnAction(new ManejadorIngreso(combo));
        HBox contenedor = Metodos.crearPanel(new Label("Seleccione un Objeto a ingresar: "), combo);
        contenedor.setSpacing(15);
        contenedor.setAlignment(Pos.CENTER);
        return contenedor;
        
    }
    
    public class ManejadorIngreso implements EventHandler{
        
        JFXComboBox<String> combo;
        
        public ManejadorIngreso(JFXComboBox<String> combo){
            this.combo = combo;
        }
        
        @Override
        public void handle(Event event) {
            String opcion = this.combo.getValue();
            switch(opcion){
                case "Cliente":
                    PanelClienteRegistro pRC = new PanelClienteRegistro(pStage);
                    pStage.setScene(pRC.getScene());
                    break;
                case "Proveedor":
                    PanelProveedorRegistro pRP = new PanelProveedorRegistro(pStage);
                    pStage.setScene(pRP.getScene());
                    break;
                case "Producto":
                    PanelProductoRegistro pPR = new PanelProductoRegistro(pStage);
                    pStage.setScene(pPR.getScene());
                    break;
                case "Categoria":
                    PanelCategoriaRegistro pCR = new PanelCategoriaRegistro(pStage);
                    pStage.setScene(pCR.getScene());
                    break;
                case "Materia Prima":
                    PanelMateriaRegistro pMR = new PanelMateriaRegistro(pStage);
                    pStage.setScene(pMR.getScene());
                    break;
                case "Factura":
                    PanelFacturaRegistro pFR = new PanelFacturaRegistro(pStage);
                    pStage.setScene(pFR.getScene());
                    break;
                
            }
        }
    }
    
    public class ManejadorVolver implements EventHandler{
        
        @Override
        public void handle(Event event) {
            
            PanelInicio pI = new PanelInicio(pStage);
            pStage.setScene(pI.getScene());
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

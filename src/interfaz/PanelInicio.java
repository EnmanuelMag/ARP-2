/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import static constantes.Constantes.ESPACIADO;
import constantes.Metodos;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    
    
    public HBox crearBotones(){
        
        JFXButton ingreso = new JFXButton("Facturar");
        ingreso.getStyleClass().add("jfx-button-inicio");
        //ingreso.setOnAction(new ManejadorIngreso());
        
        JFXButton producto = new JFXButton("Producto");
        producto.getStyleClass().add("jfx-button-inicio");
        producto.setOnAction(new ManejadorProducto());
        
        JFXButton trabajadores = new JFXButton("Trabajadores");
        trabajadores.getStyleClass().add("jfx-button-inicio");
        trabajadores.setOnAction(new ManejadorTrabajadores());
        
        JFXButton proveedores = new JFXButton("Proveedores");
        proveedores.getStyleClass().add("jfx-button-inicio");
        proveedores.setOnAction(new ManejadorProveedores());
        
        VBox contIzq = new VBox(ingreso, producto, trabajadores, proveedores);
        contIzq.setSpacing(60);
        contIzq.setAlignment(Pos.CENTER);
        
        JFXButton inventario = new JFXButton("Inventario");
        inventario.getStyleClass().add("jfx-button-inicio");
        //ingreso.setOnAction(new ManejadorIngreso());
        
        JFXButton pedidos = new JFXButton("Pedidos");
        pedidos.getStyleClass().add("jfx-button-inicio");
        
        JFXButton estadisticas = new JFXButton("Estadisticas");
        estadisticas.getStyleClass().add("jfx-button-inicio");
        
        JFXButton reporte = new JFXButton("Reportes");
        reporte.getStyleClass().add("jfx-button-inicio");
        
        VBox contDer = new VBox(inventario, pedidos, estadisticas, reporte);
        contDer.setSpacing(60);
        contDer.setAlignment(Pos.CENTER);
        
        HBox cont = Metodos.crearPanel(contIzq, contDer);
        cont.setAlignment(Pos.CENTER);
        cont.setSpacing(150);
        cont.setPadding(ESPACIADO);
        return cont;
    }
    
    public class ManejadorProveedores implements EventHandler{
        @Override
        public void handle(Event event) {
            PanelProveedorRegistro pR = new PanelProveedorRegistro(pStage);
            pStage.setScene(pR.getScene());
        }
    }
    
    public class ManejadorTrabajadores implements EventHandler{
        @Override
        public void handle(Event event) {
            PanelTrabajadorRegistro pR = new PanelTrabajadorRegistro(pStage);
            pStage.setScene(pR.getScene());
        }
    }
    
    public class ManejadorProducto implements EventHandler{
        @Override
        public void handle(Event event) {
            PanelProductoRegistro pR = new PanelProductoRegistro(pStage);
            pStage.setScene(pR.getScene());
        }
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

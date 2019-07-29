/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
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
    private JFXDialog dialogo;
    
    public PanelInicio(Stage p){
        this.pStage = p;
        this.rootBorder = new BorderPane();
        rootBorder.setPadding(ESPACIADO);
        rootBorder.setCenter(crearBotones());
        this.root = new StackPane(rootBorder); 
    }
    
    
    public HBox crearBotones(){
        
        JFXButton facturas = new JFXButton("Facturar");
        facturas.getStyleClass().add("jfx-button-inicio");
        facturas.setOnAction(e -> {
           
            JFXButton registrar = new JFXButton("Registrar");
            registrar.getStyleClass().add("jfx-button-subIncio"); 
            
            JFXButton consultar = new JFXButton("Consultar");
            consultar.getStyleClass().add("jfx-button-subIncio");
            
            HBox cont = new HBox(registrar, consultar);
            cont.setAlignment(Pos.CENTER);
            cont.setSpacing(20);
            dialogo = Metodos.dialogoMaterial(root, "Selecione una acción a realizar (Factura)", cont);
            
        });
        
        JFXButton producto = new JFXButton("Producto");
        producto.getStyleClass().add("jfx-button-inicio");
        producto.setOnAction(e -> {
            
            JFXButton consultar = new JFXButton("Consultar");
            consultar.getStyleClass().add("jfx-button-subIncio");
            
            JFXButton registrar = new JFXButton("Registrar");
            registrar.getStyleClass().add("jfx-button-subIncio");
            registrar.setOnAction(new ManejadorRegistroProducto());
            
            JFXButton modificar = new JFXButton("Modificar");
            modificar.getStyleClass().add("jfx-button-subIncio");
            
            HBox cont = new HBox(consultar, registrar, modificar);
            cont.setAlignment(Pos.CENTER);
            cont.setSpacing(20);
            dialogo = Metodos.dialogoMaterial(root, "Selecione una acción a realizar (Producto)", cont);
            
        });
          
        JFXButton clientes = new JFXButton("Clientes");
        clientes.getStyleClass().add("jfx-button-inicio");
        clientes.setOnAction(e -> {
           
            JFXButton registrar = new JFXButton("Registrar");
            registrar.getStyleClass().add("jfx-button-subIncio");
            registrar.setOnAction(new ManejadorRegistroClientes());
            
            JFXButton modificar = new JFXButton("Modificar");
            modificar.getStyleClass().add("jfx-button-subIncio");
            
            HBox cont = new HBox(registrar, modificar);
            cont.setAlignment(Pos.CENTER);
            cont.setSpacing(20);
            dialogo = Metodos.dialogoMaterial(root, "Selecione una acción a realizar (Cliente)", cont);
            
        });
        
        JFXButton proveedores = new JFXButton("Proveedores");
        proveedores.getStyleClass().add("jfx-button-inicio");
        proveedores.setOnAction(e -> {

            JFXButton registrar = new JFXButton("Registrar");
            registrar.getStyleClass().add("jfx-button-subIncio");
            registrar.setOnAction(new ManejadorRegistroProveedores());
            
            JFXButton modificar = new JFXButton("Modificar");
            modificar.getStyleClass().add("jfx-button-subIncio");
            
            HBox cont = new HBox(registrar, modificar);
            cont.setAlignment(Pos.CENTER);
            cont.setSpacing(20);
            dialogo = Metodos.dialogoMaterial(root, "Selecione una acción a realizar (Proveedores)", cont);
            
        });
        
        VBox contIzq = new VBox(facturas, producto, clientes, proveedores);
        contIzq.setSpacing(60);
        contIzq.setAlignment(Pos.CENTER);
        
        JFXButton inventario = new JFXButton("Inventario");
        inventario.getStyleClass().add("jfx-button-inicio");
        //ingreso.setOnAction(new ManejadorIngreso());
        
        JFXButton pedidos = new JFXButton("Pedidos");
        pedidos.getStyleClass().add("jfx-button-inicio");
        
        JFXButton reporte = new JFXButton("Reportes");
        reporte.getStyleClass().add("jfx-button-inicio");
        reporte.setOnAction(new ManejadorReportes());
        
        JFXButton ordenes = new JFXButton("Ordenes");
        ordenes.getStyleClass().add("jfx-button-inicio");
        //ordenes.setOnAction(new ManejadorReportes());
        
        VBox contDer = new VBox(inventario, pedidos, reporte, ordenes);
        contDer.setSpacing(60);
        contDer.setAlignment(Pos.CENTER);
        
        HBox cont = Metodos.crearPanel(contIzq, contDer);
        cont.setAlignment(Pos.CENTER);
        cont.setSpacing(150);
        cont.setPadding(ESPACIADO);
        return cont;
    }
    
    public class ManejadorRegistroProveedores implements EventHandler{
        @Override
        public void handle(Event event) {
            dialogo.close();
            PanelProveedorRegistro pR = new PanelProveedorRegistro(pStage,root);
            pStage.getScene().setRoot(pR.getRoot());
           
        }
    }
    
    public class ManejadorRegistroClientes implements EventHandler{
        @Override
        public void handle(Event event) {
            dialogo.close();
            PanelClienteRegistro pR = new PanelClienteRegistro(pStage,root);
            pStage.getScene().setRoot(pR.getRoot());
        }
    }
    
    public class ManejadorRegistroProducto implements EventHandler{
        
        
        @Override
        public void handle(Event event) {
            
            dialogo.close();
            PanelProductoRegistro pR = new PanelProductoRegistro(pStage,root);
            pStage.getScene().setRoot(pR.getRoot());
        }
    }
    
   public class ManejadorReportes implements EventHandler{
        @Override
        public void handle(Event event) {
            PanelReporteVentas pR = new PanelReporteVentas(pStage,root);
            pStage.getScene().setRoot(pR.getRoot());
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

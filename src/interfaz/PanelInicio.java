/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import static constantes.Constantes.ESPACIADO;
import static constantes.Constantes.ICONOS;
import static constantes.Constantes.PATH_ICON;
import constantes.Metodos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
        rootBorder.setFocusTraversable(true);
    }
    
    
    public HBox crearBotones(){
        
        HashMap<String,Image> iconos = cargarIconos();
        
        JFXButton facturas = new JFXButton("");
        //facturas.setBackground(new Background(new BackgroundImage(iconos.get("factura2B.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          //BackgroundSize.DEFAULT)));
        //facturas.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        facturas.getStyleClass().add("jfx-button-factura");
        facturas.setOnAction(e -> {
           
            JFXButton registrar = new JFXButton("Registrar");
            registrar.getStyleClass().add("jfx-button-subIncio"); 
            registrar.setOnMouseClicked(new ManejadorRegistroFactura());
            
            JFXButton consultar = new JFXButton("Consultar");
            consultar.getStyleClass().add("jfx-button-subIncio");
            
            HBox cont = new HBox(registrar, consultar);
            cont.setAlignment(Pos.CENTER);
            cont.setSpacing(20);
            dialogo = Metodos.dialogoMaterial(root, "Selecione una acción a realizar (Factura)", cont);
            
            
            
            
        });
        
        JFXButton producto = new JFXButton("");
        producto.getStyleClass().add("jfx-button-producto");
        producto.setOnAction(e -> {
            
            JFXButton consultar = new JFXButton(" Consultar");
            consultar.getStyleClass().add("jfx-button-subIncio");
            consultar.setOnAction(new ManejadorRegistroProducto(false));
            
            JFXButton registrar = new JFXButton("Registrar");
            registrar.getStyleClass().add("jfx-button-subIncio");
            registrar.setOnAction(new ManejadorRegistroProducto(true));
            
            JFXButton modificar = new JFXButton("Modificar");
            modificar.getStyleClass().add("jfx-button-subIncio");
            modificar.setOnAction(new ManejadorRegistroProducto(false));
            
            HBox cont = new HBox(consultar, registrar, modificar);
            cont.setAlignment(Pos.CENTER);
            cont.setSpacing(20);
            dialogo = Metodos.dialogoMaterial(root, "Selecione una acción a realizar (Producto)", cont);
            
        });
          
        JFXButton clientes = new JFXButton("");
        clientes.getStyleClass().add("jfx-button-clientes");
        clientes.setOnAction(e -> {
           
            JFXButton registrar = new JFXButton("Registrar");
            registrar.getStyleClass().add("jfx-button-subIncio");
            registrar.setOnAction(new ManejadorRegistroClientes(true));
            
            JFXButton modificar = new JFXButton("Modificar");
            modificar.getStyleClass().add("jfx-button-subIncio");
            modificar.setOnAction(new ManejadorRegistroClientes(false));
            
            HBox cont = new HBox(registrar, modificar);
            cont.setAlignment(Pos.CENTER);
            cont.setSpacing(20);
            dialogo = Metodos.dialogoMaterial(root, "Selecione una acción a realizar (Cliente)", cont);
            
        });
        
        JFXButton proveedores = new JFXButton("");
        proveedores.getStyleClass().add("jfx-button-proveedor");
        proveedores.setOnAction(e -> {

            JFXButton registrar = new JFXButton("Registrar");
            registrar.getStyleClass().add("jfx-button-subIncio");
            registrar.setOnAction(new ManejadorRegistroProveedores(true));
            
            JFXButton modificar = new JFXButton("Modificar");
            modificar.getStyleClass().add("jfx-button-subIncio");
            modificar.setOnAction(new ManejadorRegistroProveedores(false));
            
            HBox cont = new HBox(registrar, modificar);
            cont.setAlignment(Pos.CENTER);
            cont.setSpacing(20);
            dialogo = Metodos.dialogoMaterial(root, "Selecione una acción a realizar (Proveedores)", cont);
            
        });
        
        VBox contIzq = new VBox(facturas, producto, clientes, proveedores);
        contIzq.setSpacing(40);
        contIzq.setAlignment(Pos.CENTER);
        
        JFXButton inventario = new JFXButton("");
        inventario.getStyleClass().add("jfx-button-inventario");
        
        JFXButton pedidos = new JFXButton("");
        pedidos.getStyleClass().add("jfx-button-pedidos");
        
        JFXButton reporte = new JFXButton("");
        reporte.getStyleClass().add("jfx-button-reporte");
        reporte.setOnAction(new ManejadorReportes());
        
        JFXButton ordenes = new JFXButton("");
        ordenes.getStyleClass().add("jfx-button-ordenes");
        
        VBox contDer = new VBox(inventario, pedidos, reporte, ordenes);
        contDer.setSpacing(40);
        contDer.setAlignment(Pos.CENTER);
        
        HBox cont = Metodos.crearPanel(contIzq, contDer);
        cont.setAlignment(Pos.CENTER);
        cont.setSpacing(150);
        cont.setPadding(ESPACIADO);
        return cont;
    }
    
    public HashMap<String,Image> cargarIconos(){
        
        HashMap<String,Image> iconos = new HashMap<>();
        
        ICONOS.forEach((icono) -> {
            
            Image image = new Image(PanelInicio.class.getClass().getResourceAsStream(PATH_ICON+icono), 60, 60, true, true);
            System.out.println(PATH_ICON+icono);
            ImageView img = new ImageView();
            img.setImage(image);
            HBox cont = new HBox(img);
            cont.setPadding(new Insets(0,30,0,0));
            cont.setAlignment(Pos.CENTER_LEFT);
            iconos.put(icono,image);
        });
        return iconos;
    }
    
    public class ManejadorRegistroFactura implements EventHandler{
        @Override
        public void handle(Event event) {
            dialogo.close();
            PanelBusquedaPedido pR = new PanelBusquedaPedido(pStage,root);
            pStage.getScene().setRoot(pR.getRoot());
           
        }
    }
    
    public class ManejadorRegistroProveedores implements EventHandler{
        
        boolean b;
        
        public ManejadorRegistroProveedores(boolean b){
            this.b = b;
        }
        
        @Override
        public void handle(Event event) {
            dialogo.close();
            PanelProveedorRegistro pR = new PanelProveedorRegistro(pStage,root, b);
            pStage.getScene().setRoot(pR.getRoot());
           
        }
    }
    
    public class ManejadorRegistroClientes implements EventHandler{
        
        boolean b;
        
        public ManejadorRegistroClientes(boolean b){
            this.b = b;
        }
        
        @Override
        public void handle(Event event) {
            dialogo.close();
            PanelClienteRegistro pR = new PanelClienteRegistro(pStage,root, b);
            pStage.getScene().setRoot(pR.getRoot());
        }
    }
    
    public class ManejadorRegistroProducto implements EventHandler{
        
        
        boolean b;
        
        public ManejadorRegistroProducto(boolean b){
            System.out.println(b);
            this.b = b;
        }
        
        @Override
        public void handle(Event event) {
            
            dialogo.close();
            PanelProductoRegistro pR = new PanelProductoRegistro(pStage,root, b);
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

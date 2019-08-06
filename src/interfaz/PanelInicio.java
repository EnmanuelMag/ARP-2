/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import static constantes.Constantes.BOTON;
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
import javafx.scene.control.Label;
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
        if(p.getScene() != null) {
            p.getScene().getStylesheets().clear();
            p.getScene().getStylesheets().add(getRutaCssFile());
        }
        
        rootBorder.setPadding(ESPACIADO);
        rootBorder.setCenter(crearBotones());
        this.root = new StackPane(rootBorder); 
        rootBorder.setFocusTraversable(true);
    }
    
    
    public VBox crearBotones(){
        
        HashMap<String,Image> iconos = cargarIconos();
        
        JFXButton facturas = new JFXButton("");
        facturas.setPadding(BOTON);
        //facturas.setBackground(new Background(new BackgroundImage(iconos.get("factura2B.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          //BackgroundSize.DEFAULT)));
        //facturas.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        facturas.getStyleClass().add("jfx-button-factura");
        facturas.setOnAction(e -> {
           
            JFXButton registrar = new JFXButton("Registrar");
            registrar.getStyleClass().add("jfx-button-subIncio"); 
            registrar.setOnMouseClicked(new ManejadorRegistroFactura(true));
            
            JFXButton consultar = new JFXButton("Consultar");
            consultar.getStyleClass().add("jfx-button-subIncio");
            consultar.setOnMouseClicked(new ManejadorRegistroFactura(false));
            
            HBox cont = new HBox(registrar, consultar);
            cont.setAlignment(Pos.CENTER);
            cont.setSpacing(20);
            dialogo = Metodos.dialogoMaterial(root, "Selecione una acción a realizar (Factura)", cont);
            
            
            
            
        });
        
        JFXButton producto = new JFXButton("");
        producto.setPadding(BOTON);
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
        clientes.setPadding(BOTON);
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
        proveedores.setPadding(BOTON);
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
        
        JFXButton inventario = new JFXButton("");
        inventario.setPadding(BOTON);
        inventario.getStyleClass().add("jfx-button-inventario");
        
        JFXButton pedidos = new JFXButton("");
        pedidos.setPadding(BOTON);
        pedidos.setOnAction(e -> {

            JFXButton registrar = new JFXButton("Registrar");
            registrar.getStyleClass().add("jfx-button-subIncio");
            registrar.setOnAction(new ManejadorRegistroPedido(true));
            
            JFXButton consultar = new JFXButton("Consultar");
            consultar.getStyleClass().add("jfx-button-subIncio");
            consultar.setOnAction(new ManejadorRegistroPedido(false));
            
            HBox cont = new HBox(registrar, consultar);
            cont.setAlignment(Pos.CENTER);
            cont.setSpacing(20);
            dialogo = Metodos.dialogoMaterial(root, "Selecione una acción a realizar (Proveedores)", cont);
            
        });
        pedidos.getStyleClass().add("jfx-button-pedidos");
        
        JFXButton reporte = new JFXButton("");
        reporte.setPadding(BOTON);
        reporte.getStyleClass().add("jfx-button-reporte");
        reporte.setOnAction(new ManejadorReportes());
        
        JFXButton ordenes = new JFXButton("");
        ordenes.setPadding(BOTON);
        ordenes.getStyleClass().add("jfx-button-ordenes");
        
        HBox ventas = new HBox(facturas, pedidos, clientes);
        ventas.setSpacing(25);
        ventas.setAlignment(Pos.CENTER);
        ventas.getStyleClass().add("contInfo");
        ventas.setPrefHeight(130);
        ventas.setMaxWidth(1100);
        
        Label vt = new Label("Ventas");
        vt.getStyleClass().add("label-tipo-paneles");
        
        VBox c1 = new VBox(vt, ventas);
        c1.setSpacing(5);
        c1.setAlignment(Pos.CENTER);
        
        VBox compra1 = new VBox(ordenes, proveedores);
        compra1.setSpacing(25);
        compra1.setAlignment(Pos.CENTER);
        VBox compra2 = new VBox(inventario, producto);
        compra2.setSpacing(25);
        compra2.setAlignment(Pos.CENTER);
        HBox compra = new HBox(compra1, compra2);
        
        compra.setAlignment(Pos.CENTER);
        compra.setSpacing(120);
        compra.getStyleClass().add("contInfo");
        compra.setPrefHeight(250);
        compra.setMaxWidth(1100);
        
        Label cmp = new Label("Compras");
        cmp.getStyleClass().add("label-tipo-paneles");
        
        VBox c2 = new VBox(cmp, compra);
        c2.setSpacing(5);
        c2.setAlignment(Pos.CENTER);
        
        HBox info = new HBox(reporte);
        info.setAlignment(Pos.CENTER);
        info.setSpacing(25);
        info.getStyleClass().add("contInfo");
        info.setPrefHeight(130);
        info.setMaxWidth(1100);
        
        Label inf = new Label("Análisis de Ventas");
        inf.getStyleClass().add("label-tipo-paneles");
        
        VBox c3 = new VBox(inf, info);
        c3.setSpacing(5);
        c3.setAlignment(Pos.CENTER);
        
        VBox contMain = new VBox(c1, c2, c3);
        contMain.setSpacing(45);
        contMain.setAlignment(Pos.CENTER);
        
        return contMain;
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
        
        boolean b;
        
        public ManejadorRegistroFactura(boolean b){
            this.b = b;
        }
        
        @Override
        public void handle(Event event) {
            dialogo.close();
            PanelBusquedaPedido pR = new PanelBusquedaPedido(pStage,root, b);
            pStage.getScene().setRoot(pR.getRoot());
           
        }
    }
    
    public class ManejadorRegistroPedido implements EventHandler{
        
        boolean b;
        
        public ManejadorRegistroPedido(boolean b){
            this.b = b;
        }
        
        @Override
        public void handle(Event event) {
            dialogo.close();
            PanelPedidoRegistro pR = new PanelPedidoRegistro(pStage,root, b);
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

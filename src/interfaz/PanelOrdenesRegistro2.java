/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import static constantes.Constantes.BOTON;
import static constantes.Constantes.BOTONA;
import static constantes.Constantes.ESPACIADO;
import constantes.Metodos;
import java.time.LocalDate;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author emman
 */
public class PanelOrdenesRegistro2 extends PanelGenerico{
    
   
    
    private VBox contDetalle;
    private boolean primero = true;
    private VBox contSp;
    private boolean b;
    private JFXDialog diag;
    
    public PanelOrdenesRegistro2(Stage p,StackPane lastRoot, boolean b){
        super(p, lastRoot);
        this.b = b;
 
        super.border.setCenter(crearFormulario());
        
        p.getScene().getStylesheets().clear();
        p.getScene().getStylesheets().add(getRutaCssFile());
    }
    
    
    public VBox crearFormulario(){
       
        HBox c1 = new HBox(new Label("REGISTRO ÓRDENES"));
        c1.getStyleClass().add("label-titulos-paneles");
        c1.setAlignment(Pos.CENTER);
        border.setTop(c1);
        JFXTextField nOrden = new JFXTextField();
        nOrden.setPromptText("Numero de Órden");
        nOrden.setLabelFloat(true);
        HBox contOrden = new HBox(nOrden);
        
        if(!b){
            ImageView img = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                img.setFitHeight(45);
                img.setFitWidth(45);
                HBox contImagen = new HBox(img);
                contImagen.setAlignment(Pos.CENTER);
                contImagen.setOnMouseClicked(new ManejadorBuscarFactura(true));
            contOrden.getChildren().add(contImagen);
        }
        
        JFXTextField nombre = new JFXTextField();
        nombre.setPromptText("Nombre");
        nombre.getStyleClass().add("jfx-texto-largo");
        nombre.setLabelFloat(true);
        
        JFXTextField ruc = new JFXTextField();
        ruc.setPromptText("RUC");
        ruc.setLabelFloat(true);
                
        HBox contRuc = new HBox(ruc);
        if(b){
            
            ImageView img= new ImageView(new Image("/recursos/iconos/lupa2.png"));
            img.setFitHeight(45);
            img.setFitWidth(45);
            HBox contImagen = new HBox(img);
            contImagen.setOnMouseClicked(new ManejadorBuscarCleinte(true));
            contRuc.getChildren().add(contImagen);
        }
        
        JFXTextField razonSocial = new JFXTextField();
        razonSocial.setPromptText("Razón Social");
        razonSocial.setLabelFloat(true);
        
        JFXDatePicker fecha = new JFXDatePicker();
        fecha.setDefaultColor(Color.web("005683"));
        fecha.setValue(LocalDate.now());
        fecha.setPromptText("Fecha");
        //fecha.setLabelFloat(true);
        
        VBox cont1 = new VBox(contOrden, fecha);
        cont1.setSpacing(30);
        cont1.setMaxWidth(400);
        cont1.setAlignment(Pos.TOP_LEFT);
                
        //nombre, razonSocial
        
        JFXTextField telf = new JFXTextField();
        telf.setPromptText("Teléfono");
        telf.setLabelFloat(true);
        
        VBox cont2 = new VBox(contRuc, telf);
        cont2.setSpacing(30);
        cont2.setMaxWidth(400);
        cont2.setAlignment(Pos.TOP_LEFT);
        
        VBox cont3 = new VBox(nombre, razonSocial);
        cont3.setSpacing(30);
        cont3.setMaxWidth(400);
        cont3.setAlignment(Pos.TOP_LEFT);
        
        contDetalle = new VBox();
        contDetalle.setPadding(BOTON);
        //contDetalle.setMaxWidth(750);
        contDetalle.setMaxHeight(300);
        
        ScrollPane sP = new ScrollPane(contDetalle);
        sP.setFitToWidth(true);
        //sP.setMinWidth(900);
        sP.setPrefHeight(600);
        
        //contDetalle.getStyleClass().add("contInfo");
        
        JFXButton anadir = new JFXButton("Añadir Producto");
        //anadir.setPadding(BOTONA);
        anadir.setOnAction(new ManejadorAnadir());
        
        
        //contDetalle.getChildren().add(0, anadir);
        
        contSp = new VBox(sP);
        sP.getStyleClass().add("contInfo2");
        
        contDetalle.setAlignment(Pos.CENTER);
        contDetalle.setSpacing(10);
        VBox lineasFatura = new VBox(contSp);
        lineasFatura.setSpacing(5);
        setLabels();
        contDetalle.getChildren().add(1,anadir);
        lineasFatura.setAlignment(Pos.CENTER);
        
        JFXButton registrar = new JFXButton("Registrar");
        VBox c = new VBox(registrar);
        c.setAlignment(Pos.CENTER);
        //lineasFatura.setMaxWidth(800);
        border.setBottom(c);
        
        JFXTextField subtotal = new JFXTextField();
        subtotal.setPromptText("Sub-Total");
        //subtotal.setPrefWidth(50);
        subtotal.setLabelFloat(true);
        
        JFXTextField iva = new JFXTextField();
        iva.setPromptText("IVA");
        iva.setLabelFloat(true);
        //iva.setPrefWidth(50);
        
        JFXTextField total = new JFXTextField();
        total.setPromptText("TOTAL");
        total.setLabelFloat(true);
        //total.setPrefWidth(50);
        
        VBox sumas = new VBox(subtotal, iva, total);
        sumas.setAlignment(Pos.CENTER);
        sumas.getStyleClass().add("texto-cant");
        //sumas.setPrefSize(150, 200);
        sumas.setSpacing(35);
        
        //sumas.getStyleClass().add("contBack");
        HBox contMedio = new HBox(lineasFatura, sumas);
        contMedio.setSpacing(40);
        contMedio.setAlignment(Pos.CENTER);
        VBox contRoot = new VBox(contMedio);
        contRoot.setAlignment(Pos.TOP_CENTER);
        contRoot.setMaxWidth(1250);
        contRoot.setSpacing(40);
        
        return contRoot;
    }
    
    public void setLabels(){
        GridPane contLabel = new GridPane();
        contLabel.getStyleClass().add("contBackOnly");
                Separator s = new Separator();
                s.setOrientation(Orientation.VERTICAL);
                Separator s2 = new Separator();
                s2.setOrientation(Orientation.VERTICAL);
                Separator s3 = new Separator();
                s3.setOrientation(Orientation.VERTICAL);
                Separator s4 = new Separator();
                s4.setOrientation(Orientation.VERTICAL);

                Label orden = new Label("N° Órden");
                orden.setMinWidth(130);
                
                Label prov = new Label("RUC Proveedor");
                prov.setMinWidth(130);
                
                Label cod = new Label("Nombre");
                cod.setMinWidth(360);
                
                Label cantt = new Label("Cantidad");
                cantt.setMinWidth(100);

                Label costU = new Label("Costo Unitario");
                costU.setMinWidth(130);
                
                Label costT = new Label("Costo Total");
                costT.setMinWidth(180);

                contLabel.add(orden, 0, 0);
                contLabel.add(s, 1, 0);
                contLabel.add(cod, 2, 0);
                contLabel.add(s2, 3, 0);
                contLabel.add(cantt, 4, 0);
                contLabel.add(s4, 5, 0);
                contLabel.add(costU, 6, 0);
                contLabel.setHgap(15);  
                contLabel.add(costT, 7, 0);
                contLabel.setHgap(15);
                
                //contLabel.getChildren().addAll(cod, s, cantt, s2, nomb, s3, costU, s4, costT);
                //contLabel.setSpacing(15);
                contLabel.setPadding(BOTONA);
                contDetalle.getChildren().add(0,contLabel);
    }
    
    public class ManejadorAnadir implements EventHandler{

        @Override
        public void handle(Event event) {
            
            if(primero){
                
                primero = false;
            }
            
            GridPane cont = new GridPane();
            JFXTextField nombre = new JFXTextField();
            nombre.setMinWidth(320);
            nombre.setEditable(false);
            nombre.setLabelFloat(true);
            
            ImageView img= new ImageView(new Image("/recursos/iconos/lupa2.png"));
            img.setFitHeight(40);
            img.setFitWidth(40);
            HBox contImagen = new HBox(img);
            HBox contNombre = Metodos.crearPanel(nombre, contImagen);
            contNombre.setMinWidth(360);
            
            JFXTextField cant = new JFXTextField();
            cant.setMaxWidth(100);
            cant.setLabelFloat(true);
            
            
            
            JFXTextField costo = new JFXTextField();
            costo.setMaxWidth(135);
            costo.setEditable(false);
            costo.setLabelFloat(true);
            
            JFXTextField costoCant = new JFXTextField();
            costoCant.setMaxWidth(130);
            costoCant.setEditable(false);
            costoCant.setLabelFloat(true);
            
            ImageView delete = new ImageView(new Image("/recursos/iconos/delete.png"));
            delete.setFitHeight(33);
            delete.setFitWidth(33);
            HBox contDelete = new HBox(delete);
            int ind =  contDetalle.getChildren().size();
            contDelete.setOnMouseClicked(e -> {
                contDetalle.getChildren().remove(cont);
            });
            
            Separator s = new Separator();
            s.setOrientation(Orientation.VERTICAL);
            Separator s2 = new Separator();
            s2.setOrientation(Orientation.VERTICAL);
            Separator s3 = new Separator();
            s3.setOrientation(Orientation.VERTICAL);
            Separator s4 = new Separator();
            s4.setOrientation(Orientation.VERTICAL);
            Separator s5 = new Separator();
            s5.setOrientation(Orientation.VERTICAL);
                        
            cont.add(contNombre, 0, 0);
            cont.add(s, 1, 0);
            cont.add(cant, 2, 0);
            cont.add(s2, 3, 0);
            cont.add(costo, 4, 0);
            cont.add(s4, 5, 0);
            cont.add(costoCant, 6, 0);
            cont.add(s5, 7, 0);
            cont.add(contDelete, 8, 0);
              
            cont.setHgap(15);
            cont.setPadding(BOTONA);
            contDetalle.getChildren().add(2,cont);
        }
    }
    
    public class ManejadorBuscarFactura implements EventHandler{

        boolean b;
        
        public ManejadorBuscarFactura(boolean b){
            this.b = b;
        }
        
        @Override
        public void handle(Event event) {
            
            int r = (int) (Math.random() * 1); 
            System.out.println(r);
            if(r==0){
                JFXButton btnRegistrar = new JFXButton("Registrar Nueva");
                btnRegistrar.setOnAction((e) -> {
                    if(diag != null)diag.close();
                    PanelOrdenesRegistro2 pR = new PanelOrdenesRegistro2(stage,root, b);
                    stage.getScene().setRoot(pR.getRoot());
                });
                diag = Metodos.dialogoMaterial(root, "No existe una factura con ese numero", btnRegistrar);
            }
            else{
                Metodos.dialogoMaterial(root, "Busqueda", "Se encontro factuea con ese numero", "Cerrar");
            }
        }
        }
    
    public class ManejadorBuscarCleinte implements EventHandler{

        boolean b;
        
        public ManejadorBuscarCleinte(boolean b){
            this.b = b;
        }
        
        @Override
        public void handle(Event event) {
            
            int r = (int) (Math.random() * 1); 
            System.out.println(r);
            if(r==0){
                JFXButton btnRegistrar = new JFXButton("Registrar Nuevo");
                btnRegistrar.setOnAction((e) -> {
                    if(diag != null)diag.close();
                    PanelClienteRegistro pR = new PanelClienteRegistro(stage,root, b);
                    stage.getScene().setRoot(pR.getRoot());
                });
                diag = Metodos.dialogoMaterial(root, "No existe un cliente con ese numero", btnRegistrar);
            }
            else{
                Metodos.dialogoMaterial(root, "Busqueda", "Se encontro un cliente con ese numero", "Cerrar");
            }
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
        return Class.class.getResource("/recursos/estilo-PanelPedido.css").toExternalForm();
    }
}

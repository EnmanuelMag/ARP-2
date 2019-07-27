/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author emman
 */
public class PanelFacturaRegistro {
    
    private BorderPane rootBorder;
    private StackPane root;
    private Stage pStage;
    private HBox contMain;
    private VBox contDetalle;
    private boolean primero = true;
    private VBox contSp;
    
    public PanelFacturaRegistro(Stage p){
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
        
        JFXTextField cedula = new JFXTextField();
        cedula.setPromptText("Cédula");
        cedula.setLabelFloat(true);
        ImageView img= new ImageView(new Image("/recursos/iconos/lupa2.png"));
        img.setFitHeight(45);
        img.setFitWidth(45);
        HBox contImagen = new HBox(img);
        HBox contCedula = Metodos.crearPanel(cedula, contImagen);
        
        JFXTextField total = new JFXTextField();
        total.setPromptText("Total");
        total.setLabelFloat(true);
        
        VBox cont1 = new VBox(contCedula, nombre, total);
        cont1.setSpacing(30);
        cont1.setMaxWidth(400);
        cont1.setAlignment(Pos.TOP_LEFT);
                
        JFXDatePicker fecha = new JFXDatePicker();
        fecha.setValue(LocalDate.now());
        fecha.setPromptText("Fecha");
        //fecha.setLabelFloat(true);
        
        JFXTextField descuento = new JFXTextField();
        descuento.setPromptText("Descuentos");
        descuento.setLabelFloat(true);
        
        VBox cont2 = new VBox(fecha, descuento);
        cont2.setSpacing(30);
        cont2.setMaxWidth(400);
        cont2.setAlignment(Pos.TOP_LEFT);
        
        contMain = Metodos.crearPanel(cont1, cont2);
        contMain.setSpacing(40);
        contMain.setAlignment(Pos.CENTER);
        contMain.setMaxWidth(600);
        contMain.setPadding(new Insets(35,60,30,60));
        contMain.getStyleClass().add("contInfo");
        
        contDetalle = new VBox();
        contDetalle.setPadding(BOTON);
        //contDetalle.setMaxWidth(750);
        contDetalle.setMaxHeight(300);
        
        ScrollPane sP = new ScrollPane(contDetalle);
        sP.setFitToWidth(true);
        sP.setPrefHeight(300);
        
        //contDetalle.getStyleClass().add("contInfo");
        
        JFXButton anadir = new JFXButton("Añadir Producto");
        //anadir.setPadding(BOTONA);
        anadir.setOnAction(new ManejadorAnadir());
        //contDetalle.getChildren().add(0, anadir);
        
        contSp = new VBox(sP);
        HBox cc = new HBox(contSp, anadir);
        cc.setSpacing(5);
        cc.getStyleClass().add("contInfo");
        cc.setAlignment(Pos.CENTER);
        
        JFXButton registrar = new JFXButton("Registrar");
        VBox c = new VBox(registrar);
        c.setAlignment(Pos.CENTER);
        c.setPadding(BOTON);
        this.rootBorder.setBottom(c);
        
        /*JFXButton volver = new JFXButton("Volver");
        VBox c2 = new VBox(volver);
        c2.setPadding(BOTON);
        volver.setOnAction(new ManejadorVolver());
        this.rootBorder.setTop(c2);
        */
        VBox contRoot = new VBox(contMain, cc);
        contRoot.setAlignment(Pos.CENTER);
        contRoot.setMaxHeight(700);
        contRoot.setSpacing(30);
        
        return contRoot;
    }
    
    public class ManejadorAnadir implements EventHandler{

        @Override
        public void handle(Event event) {
            
            if(primero){
                HBox contLabel = new HBox();
                Separator s = new Separator();
                s.setOrientation(Orientation.VERTICAL);
                Separator s2 = new Separator();
                s2.setOrientation(Orientation.VERTICAL);
                Separator s3 = new Separator();
                s3.setOrientation(Orientation.VERTICAL);
                Separator s4 = new Separator();
                s4.setOrientation(Orientation.VERTICAL);

                Label cod = new Label("Codigo");
                cod.setMinWidth(170);
                Label cantt = new Label("Cantidad");
                cantt.setMinWidth(100);
                Label nomb = new Label("Nombre");
                nomb.setMinWidth(205);
                Label costU = new Label("Costo Unitario");
                costU.setMinWidth(130);
                Label costT = new Label("Costo Total");
                costT.setMinWidth(180);
                contLabel.getChildren().addAll(cod, s, cantt, s2, nomb, s3, costU, s4, costT);
                contLabel.setSpacing(15);
                contLabel.setPadding(BOTONA);
                contSp.getChildren().add(0, contLabel);
                primero = false;
            }
            
            HBox cont = new HBox();
            JFXTextField codigo = new JFXTextField();
            codigo.setLabelFloat(true);
            ImageView img= new ImageView(new Image("/recursos/iconos/lupa2.png"));
            img.setFitHeight(45);
            img.setFitWidth(45);
            HBox contImagen = new HBox(img);
            HBox contCodigo = Metodos.crearPanel(codigo, contImagen);
            contCodigo.setMaxWidth(160);
            
            JFXTextField cant = new JFXTextField();
            cant.setMaxWidth(100);
            cant.setLabelFloat(true);
            
            JFXTextField nombre = new JFXTextField();
            nombre.setMaxWidth(210);
            nombre.setEditable(false);
            nombre.setLabelFloat(true);
            
            JFXTextField costo = new JFXTextField();
            costo.setMaxWidth(135);
            costo.setEditable(false);
            costo.setLabelFloat(true);
            
            JFXTextField costoCant = new JFXTextField();
            costoCant.setMaxWidth(130);
            costoCant.setEditable(false);
            costoCant.setLabelFloat(true);
            
            Separator s = new Separator();
            s.setOrientation(Orientation.VERTICAL);
            Separator s2 = new Separator();
            s2.setOrientation(Orientation.VERTICAL);
            Separator s3 = new Separator();
            s3.setOrientation(Orientation.VERTICAL);
            Separator s4 = new Separator();
            s4.setOrientation(Orientation.VERTICAL);
            
            cont.getChildren().addAll(contCodigo, s, cant, s4, nombre, s2, costo, s3, costoCant);
            cont.setSpacing(15);
            cont.setPadding(BOTONA);
            contDetalle.getChildren().add(cont);
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
        return Class.class.getResource("/recursos/estiloFrancis.css").toExternalForm();
    }
}

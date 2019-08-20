/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import static constantes.Constantes.ESPACIADO;
import static constantes.Constantes.TEXTS;
import static constantes.Constantes.TIPO_CLIENTES;
import constantes.Metodos;
import java.time.LocalDate;
import java.util.LinkedList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Pedido;

/**
 *
 * @author emman
 */
public class PanelBusquedaPedido extends PanelGenerico{
    
        private VBox contMain;
        private boolean b;
    
    public PanelBusquedaPedido(Stage s,StackPane lastRoot, boolean b){
        super(s,lastRoot);
        this.b = b;
        super.border.setCenter(crearFormulario());
        s.getScene().getStylesheets().clear();
        s.getScene().getStylesheets().add(getRutaCssFile());
        setTop();
    }
    
    public void setTop(){
        Label titulo=new Label("Registro cliente");
        titulo.getStyleClass().clear();
        titulo.getStyleClass().add("label-titulos-paneles");
        HBox cTitulo=new HBox(titulo);
        cTitulo.setAlignment(Pos.CENTER);
        ((BorderPane)super.getBorder()).setTop(cTitulo);
        
    }
    
    public VBox crearFormulario(){
       
        
        JFXTextField cedula = new JFXTextField();
        cedula.setPromptText("Cédula");
        cedula.setLabelFloat(true);
                
        HBox contCedula = new HBox(cedula);
        ImageView img= new ImageView(new Image("/recursos/iconos/lupa2.png"));
        img.setFitHeight(45);
        img.setFitWidth(45);
        HBox contImagen = new HBox(img);
        
        contImagen.setOnMouseClicked(e -> {
            setRoot();
        });
        contCedula.getChildren().add(contImagen);
        contCedula.setAlignment(Pos.TOP_LEFT);
        
        contMain = new VBox(contCedula);
        contMain.setPadding(new Insets(80,0,0,0));
        contMain.setPrefHeight(300);
        
        return contMain;
    }
    

    
    public LinkedList<VBox> cargarTarjetaPedidos(LinkedList<Pedido> pedidosPorFacturar){
        
        LinkedList<VBox> pedidosTarjeta=new LinkedList<>();
        
        for(Pedido p :pedidosPorFacturar){
            VBox v=new VBox();
            JFXButton button;
            if(b){
               button =new JFXButton("Facturar");
                button.setOnMouseClicked((r)->{
                stage.getScene().setRoot(new PanelFacturaRegistro(stage,root,p).getRoot());
                });
            }
            else{
                button =new JFXButton("Mostrar Detalle");
                button.setOnMouseClicked((r)->{
                stage.getScene().setRoot(new PanelFacturaRegistro(stage,root,p).getRoot());
                });
            }
            v.getStyleClass().add("tarjetaPedido");
            v.setAlignment(Pos.CENTER_LEFT);
            v.setPadding(new Insets(20));
            GridPane g=new GridPane();
            g.setVgap(20);
            g.setHgap(20);
            g.addColumn(0, new Label("Cédula"), new Label("Nombres"), new Label("Fecha"));
            //g.addColumn(1, new Label(p.getCliente().getCedula()) ,new Label(p.getCliente().getNombreCompleto()), new Label(p.getFecha().toString()));
            g.add(button,10,2);
            v.getChildren().add(g);
            pedidosTarjeta.add(v);
        }
        return pedidosTarjeta;
    }    
    
    public void setRoot(){
        
        ScrollPane scroll=new ScrollPane();
        scroll.setPadding(new Insets(20));
        VBox cScroll=new VBox();
        cScroll.setPadding(new Insets(25));
        cScroll.setSpacing(20);
        scroll.setContent(cScroll);
        
        //cScroll.getChildren().addAll(cargarTarjetaPedidos(cargarPedidos()));
        scroll.setContent(cScroll);
        scroll.setFitToWidth(true);
        
        contMain.getChildren().add(scroll);
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
        return PanelBusquedaPedido.class.getResource("/recursos/estilo-PanelFactura.css").toExternalForm();
    }
}

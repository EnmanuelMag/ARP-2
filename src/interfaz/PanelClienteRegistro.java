/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import static constantes.Constantes.ESPACIADO;
import static constantes.Constantes.TEXTS;
import static constantes.Constantes.TIPO_CLIENTES;
import constantes.Metodos;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class PanelClienteRegistro extends PanelGenerico{
    
    JFXDialog diag;
    boolean b;
    
    public PanelClienteRegistro(Stage s,StackPane lastRoot,  boolean b){
        super(s,lastRoot);
        this.b = b;
        s.getScene().getStylesheets().clear();
        s.getScene().getStylesheets().add(getRutaCssFile());
        super.border.setCenter(crearFormulario());
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
    
    public HBox crearFormulario(){
       
        
        JFXTextField nombre = new JFXTextField();
        nombre.setPromptText("Nombre");
        
        nombre.setMinWidth(TEXTS);
        nombre.setPrefWidth(TEXTS);
        nombre.setLabelFloat(true);
        
        JFXTextField apellido = new JFXTextField();
        
        apellido.setPromptText("Apellido");
        apellido.setLabelFloat(true);
        apellido.setMinWidth(TEXTS);
                
        JFXTextField cedula = new JFXTextField();
        cedula.setPromptText("Cédula");
        cedula.setLabelFloat(true);
                
        HBox contCedula = new HBox(cedula);
        if(!b){
            
            ImageView img= new ImageView(new Image("/recursos/iconos/lupa2.png"));
            img.setFitHeight(45);
            img.setFitWidth(45);
            HBox contImagen = new HBox(img);
            
            contCedula.getChildren().add(contImagen);
        }
        
        JFXTextField descuento = new JFXTextField();
        descuento.setPromptText("Descuento");
        descuento.setLabelFloat(true);
        
        JFXTextField direccion = new JFXTextField();
        direccion.setPromptText("Dirección");
        
        direccion.setMinWidth(TEXTS);
        direccion.setPrefWidth(TEXTS);
        direccion.setLabelFloat(true);
        
        JFXTextField telefono = new JFXTextField();
        telefono.setPromptText("Teléfono");
        telefono.setMaxWidth(160);
        telefono.setLabelFloat(true);
        
        VBox cont1 = new VBox(nombre, apellido, contCedula, descuento);
        cont1.setSpacing(65);
       
        cont1.setAlignment(Pos.TOP_LEFT);
        
        JFXComboBox tipoBox = new JFXComboBox();
        tipoBox.setItems(TIPO_CLIENTES); 
        HBox contBox = Metodos.crearPanel(new Label("Tipo"), tipoBox);
        
        JFXToggleButton estado = new JFXToggleButton();
        estado.setText("Inactivo");
        estado.setOnAction((e) -> {
            
            if(estado.isSelected()){
                estado.setText("Activo");
            }
            else{
                estado.setText("Inactivo");
            }
        
        });
        
        JFXTextField saldo = new JFXTextField();
        saldo.setPromptText("Saldo");
        saldo.setMaxWidth(150);
        saldo.setLabelFloat(true);
        
        VBox cont2 = new VBox(direccion, telefono, contBox, estado, saldo);
        cont2.setSpacing(65);
        
        cont2.setAlignment(Pos.TOP_LEFT);
        
        HBox contMain = Metodos.crearPanel(cont1, cont2);
        contMain.setMaxHeight(380);
        contMain.setSpacing(40);
        contMain.setAlignment(Pos.CENTER);
        
        JFXButton registrar = new JFXButton("Registrar");
        VBox c = new VBox(registrar);
        c.setAlignment(Pos.CENTER);
        border.setBottom(c);
        
        return contMain;
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
        return PanelClienteRegistro.class.getResource("/recursos/estilo-PanelCliente.css").toExternalForm();
    }
}

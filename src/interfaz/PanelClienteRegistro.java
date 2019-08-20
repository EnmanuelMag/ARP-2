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
import static constantes.Constantes.TEXTS;
import constantes.Metodos;
import controlador.DB;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.TipoCliente;


/**
 *
 * @author emman
 */
public final class PanelClienteRegistro extends PanelGenerico{
    
    JFXDialog diag;
    boolean b;
    JFXTextField nombre;
    JFXTextField apellido;
    JFXTextField cedula;
    JFXTextField descuento;
    JFXTextField direccion;
    JFXTextField telefono;
    JFXComboBox<TipoCliente> tipoBox;
    List<TipoCliente> tipoC;
    
    
    public PanelClienteRegistro(Stage s,StackPane lastRoot,  boolean b){
        super(s,lastRoot);
        this.tipoC = (List<TipoCliente>) DB.getAll(TipoCliente.class);
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
       
        
        nombre = new JFXTextField();
        nombre.setPromptText("Nombre");
        nombre.setMinWidth(TEXTS);
        nombre.setPrefWidth(TEXTS);
        nombre.setLabelFloat(true);
        
        apellido = new JFXTextField();
        
        apellido.setPromptText("Apellido");
        apellido.setLabelFloat(true);
        apellido.setMinWidth(TEXTS);
                
        cedula = new JFXTextField();
        cedula.setPromptText("Cédula");
        cedula.setLabelFloat(true);
                
        HBox contCedula = new HBox(cedula);
        
        descuento = new JFXTextField();
        descuento.setPromptText("Descuento");
        descuento.setLabelFloat(true);
        descuento.setEditable(false);
        
        direccion = new JFXTextField();
        direccion.setPromptText("Dirección");
        
        direccion.setMinWidth(TEXTS);
        direccion.setPrefWidth(TEXTS);
        direccion.setLabelFloat(true);
        
        telefono = new JFXTextField();
        telefono.setPromptText("Teléfono");
        telefono.setMaxWidth(160);
        telefono.setLabelFloat(true);
        
        VBox cont1 = new VBox(nombre, apellido, contCedula, descuento);
        cont1.setSpacing(65);
       
        cont1.setAlignment(Pos.TOP_LEFT);
        
        tipoBox = new JFXComboBox();
        tipoBox.setItems(FXCollections.observableArrayList(this.tipoC)); 
        
        tipoBox.setOnAction((p)->{ 
            TipoCliente tipo=tipoBox.getSelectionModel().getSelectedItem();
            if(tipo!=null){
                descuento.setText(String.valueOf(tipo.getDescuento()));
            };
        });
        
        HBox contBox = Metodos.crearPanel(new Label("Tipo"), tipoBox);
        
        
        
        VBox cont2 = new VBox(direccion, telefono, contBox);
        cont2.setSpacing(65);
        
        cont2.setAlignment(Pos.TOP_LEFT);
        
        HBox contMain = Metodos.crearPanel(cont1, cont2);
        contMain.setMaxHeight(380);
        contMain.setSpacing(40);
        contMain.setAlignment(Pos.CENTER);
        contMain.getStyleClass().add("contMain_");
        contMain.setMaxWidth(500);
        
        JFXButton registrar = new JFXButton("Registrar");
        registrar.setOnAction((w)->{
            if (this.formularioLLeno()) {
                Cliente c = new Cliente();
                c.setNombres(nombre.getText());
                c.setApellidos(apellido.getText());
                c.setRuc(cedula.getText());
                c.setDireccion(direccion.getText());
                c.setTelefono(telefono.getText());
                c.setTipoCliente(tipoBox.getValue());
                DB.agregar(c);
                Metodos.dialogoMaterial(root, "Datos", "Se ha guardado nuevo cliente", "Cerrar");
            } else {

                Metodos.dialogoMaterial(root, "Datos", "Por favor llene todo el formulario", "Cerrar");
            }
        });
        VBox c = new VBox(registrar);
        c.setAlignment(Pos.CENTER);
        border.setBottom(c);
        
        HBox main = new HBox(contMain);
        main.setAlignment(Pos.CENTER);
        return main;
    }
    public boolean formularioLLeno(){
        if(tipoBox.getValue()==null || nombre.getText().equals("") || apellido.getText().equals("")
                || cedula.getText().equals("")|| direccion.getText().equals("")|| telefono.getText().equals("")){
            return false;
        }
        return true;
        
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

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
import static constantes.Constantes.ESPACIADO;
import static constantes.Constantes.TEXTS;
import constantes.Metodos;
import controlador.DB;
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
import modelo.Proveedor;

/**
 *
 * @author emman
 */
public class PanelProveedorRegistro extends PanelGenerico{
    
    private JFXDialog diag;
    private boolean b;
    JFXTextField nombre;
    JFXTextField ruc;
    JFXTextField ciudad;
    JFXTextField direccion;
    JFXTextField telefono;
    
    public PanelProveedorRegistro(Stage s,StackPane lastRoot, boolean b){
        super(s,lastRoot);
        this.b = b;
        s.getScene().getStylesheets().clear();
        s.getScene().getStylesheets().add(getRutaCssFile());
        super.border.setCenter(crearFormulario());
        setTop();
    }
    public void setTop(){
        Label titulo=new Label("Registro proveedor");
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
        nombre.setLabelFloat(true);
        
        HBox contRUC = new HBox();
        
        
        ruc = new JFXTextField();
        ruc.setPromptText("RUC");
        ruc.setMinWidth(200);
        ruc.setLabelFloat(true);
        
        
        ciudad = new JFXTextField();
        ciudad.setPromptText("Ciudad");
        ciudad.setMinWidth(200);
        ciudad.setLabelFloat(true);
        
        VBox cont1 = new VBox(nombre, ruc, ciudad);
        cont1.setSpacing(90);
        //cont1.setMaxWidth(400);
        cont1.setAlignment(Pos.TOP_LEFT);
        
        direccion = new JFXTextField();
        direccion.setPromptText("Dirección");
       
        direccion.setMinWidth(TEXTS);
        direccion.setPrefWidth(TEXTS);
        direccion.setLabelFloat(true);
        
        telefono = new JFXTextField();
        telefono.setPromptText("Teléfono");
        telefono.setMaxWidth(150);
        telefono.setLabelFloat(true);
        
        VBox cont2 = new VBox(direccion, telefono);
        cont2.setSpacing(90);
        //cont2.setMaxWidth(400);
        cont2.setAlignment(Pos.TOP_LEFT);
        
        HBox contMain = Metodos.crearPanel(cont1, cont2);
        contMain.setSpacing(40);
        contMain.setAlignment(Pos.CENTER);
        
        JFXButton registrar = new JFXButton("Registrar");
        registrar.setOnMouseClicked(e -> {
            if(this.formularioLLeno()){
                 Proveedor p = new Proveedor();
            p.setCiudad(ciudad.getText());
            p.setDireccion(direccion.getText());
            p.setRazonSocial(nombre.getText());
            p.setRuc(ruc.getText());
            p.setTelefono(telefono.getText());
            DB.agregar(p);
            Metodos.dialogoMaterial(root, "Datos", "Se ha guardado nuevo proveedor", "Cerrar");
            
            }else{
                Metodos.dialogoMaterial(root, "Datos", "Por favor llene todo el formulario", "Cerrar");
            }
           
        });
        VBox c = new VBox(registrar);
        c.setAlignment(Pos.CENTER);
        border.setBottom(c);
        
        HBox contRoot = new HBox(contMain, cont2);
        contRoot.setMaxHeight(200);
        contRoot.setAlignment(Pos.CENTER);
        contRoot.setSpacing(55);
        contRoot.getStyleClass().add("contMain_");
        //contRoot.setMaxWidth(500);
        
        HBox main = new HBox(contRoot);
        main.setAlignment(Pos.CENTER);
     
        
        return main;
    }
    
    public boolean formularioLLeno(){
        if(nombre.getText().equals("") || ruc.getText().equals("")
                || telefono.getText().equals("")|| ciudad.getText().equals("")|| direccion.getText().equals("")){
            return false;
        }
        return true;
        
    }
    
    public class ManejadorBuscarP implements EventHandler{

        boolean b;
        
        public ManejadorBuscarP(boolean b){
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
                    PanelProveedorRegistro pR = new PanelProveedorRegistro(stage,root, b);
                    stage.getScene().setRoot(pR.getRoot());
                });
                diag = Metodos.dialogoMaterial(root, "No existe un proveedor con ese nombre", btnRegistrar);
            }
            else{
                Metodos.dialogoMaterial(root, "Busqueda", "Se encontro al proveedor con ese nombre", "Cerrar");
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
        return Class.class.getResource("/recursos/estilo-PanelProveedores.css").toExternalForm();
    }
}

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
import static constantes.Constantes.BOTON;
import static constantes.Constantes.BOTONA;
import static constantes.Constantes.CATG_PRODUCTOS;
import constantes.Metodos;
import controlador.DB;
import java.math.BigDecimal;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.*;

/**
 *
 * @author emman
 */
public class PanelProductoRegistro  extends PanelGenerico{
    
    private JFXDialog diag;
    private boolean b;
    HBox contNombre;
    JFXToggleButton estado;
    JFXTextField utilidad;
    JFXTextField costo;
    JFXTextField decripcion;
    JFXComboBox<Categoria> categBox;
    JFXComboBox<Proveedor> proveBox;
    JFXTextField nombre;
    
   //estilo-PanelProducto.css
    
    private HBox contMain;
    
    public PanelProductoRegistro(Stage s,StackPane lastRoot, boolean b){
        super(s,lastRoot);
        this.b = b;
        super.border.setCenter(crearFormulario());
        setTop();
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add(getRutaCssProducto());
    }
    
    public void setTop(){
        Label titulo=new Label("Registro producto");
        titulo.getStyleClass().clear();
        titulo.getStyleClass().add("label-titulos-paneles");
        HBox cTitulo=new HBox(titulo);
        cTitulo.setAlignment(Pos.CENTER);
        ((BorderPane)super.getBorder()).setTop(cTitulo);
        
    }
    
    public String getRutaCssProducto(){
        return PanelInicio.class.getResource("/recursos/estilo-PanelProducto.css").toExternalForm();
    }
    
    public HBox crearFormulario(){
       
        
        contNombre = new HBox();
        
        contNombre.setAlignment(Pos.CENTER_LEFT);
        
            nombre = new JFXTextField();
            nombre.setPromptText("Nombre");
            nombre.getStyleClass().add("jfx-texto-largo");
            nombre.setLabelFloat(true);
            contNombre.getChildren().add(nombre);
        
        estado = new JFXToggleButton();
        estado.setSelected(true);
        estado.setText("En Producción");
        estado.setOnAction((e) -> {
            
            if(estado.isSelected()){
                estado.setText("En Producción");
            }
            else{
                estado.setText("Descontinuado");
            }
        });
        
        utilidad = new JFXTextField();
        utilidad.setPromptText("Utilidad");
        utilidad.setMaxWidth(150);
        utilidad.setLabelFloat(true);
        
        costo = new JFXTextField();
        costo.setPromptText("Costo");
        costo.setMaxWidth(150);
        costo.setLabelFloat(true);
        
        decripcion = new JFXTextField();
        decripcion.setPromptText("Decripcion");
        decripcion.getStyleClass().add("jfx-texto-largo2");
        
        decripcion.setLabelFloat(true);

        VBox cont1 = new VBox(contNombre, utilidad, costo, decripcion);
        cont1.setSpacing(65);
       
        cont1.setAlignment(Pos.TOP_LEFT);

        
        categBox = new JFXComboBox();
        categBox.setItems(FXCollections.observableList(DB.getAll(Categoria.class))); 
        
        HBox contBox = Metodos.crearPanel(new Label("Categoria: "), categBox);
        
        proveBox = new JFXComboBox();
        proveBox.setItems(FXCollections.observableList(DB.getAll(Proveedor.class))); 
        
        HBox contBox2 = Metodos.crearPanel(new Label("Proveedor: "), proveBox);
        
        VBox cont2 = new VBox(estado, contBox,contBox2);
        cont2.setSpacing(65);
 
        cont2.setAlignment(Pos.TOP_LEFT);
        
        contMain = Metodos.crearPanel(cont1, cont2);
        contMain.setSpacing(40);
        contMain.setMaxHeight(400);
        contMain.setAlignment(Pos.CENTER);
        contMain.setPadding(new Insets(50,0,0,0)); 
        contMain.getStyleClass().add("contMain_");
        
        JFXButton registrar = new JFXButton("Registrar");
        registrar.setOnMouseClicked(e -> {
            if(formularioLLeno()){
                Producto p = new Producto();

                p.setProveedor(proveBox.getValue());
                p.setCategoria(categBox.getValue());
                p.setCosto(BigDecimal.valueOf(Float.valueOf(costo.getText())));
                p.setDescontinuado(this.estado.isSelected());
                p.setDescripcion(this.decripcion.getText());
                p.setNombre(nombre.getText());
                p.setUtilidad(BigDecimal.valueOf(Float.valueOf(this.utilidad.getText())));
                DB.agregar(p);
                Metodos.dialogoMaterial(root, "Datos", "Se ha guardado nuevo producto", "Cerrar");
            }else{
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
        if(proveBox.getValue()==null || categBox.getValue()==null || 
                costo.getText().equals("") || nombre.getText().equals("")
                || utilidad.getText().equals("")){
            return false;
        }
        return true;
        
    }
    
    public class ManejadorBuscarProducto implements EventHandler{

        boolean b;
        
        public ManejadorBuscarProducto(boolean b){
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
                    PanelProductoRegistro pR = new PanelProductoRegistro(stage,root, b);
                    stage.getScene().setRoot(pR.getRoot());
                });
                diag = Metodos.dialogoMaterial(root, "No existe una materia prima con ese nombre", btnRegistrar);
            }
            else{
                Metodos.dialogoMaterial(root, "Busqueda", "Se encontro la materia prima con ese nombre", "Cerrar");
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
        return Class.class.getResource("/recursos/estilo-PanelProducto.css").toExternalForm();
    }
}


/*
        FileChooser foto = new FileChooser();
        foto.setTitle("Open Resource File");
        foto.showOpenDialog(super.stage);
*/
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

/**
 *
 * @author emman
 */
public class PanelProductoRegistro  extends PanelGenerico{
    
    private JFXDialog diag;
    private boolean b;
    
   //estilo-PanelProducto.css
    
    private HBox contMain;
    
    public PanelProductoRegistro(Stage s,StackPane lastRoot, boolean b){
        super(s,lastRoot);
        this.b = b;
        super.border.setCenter(crearFormulario());
        setTop();
        s.getScene().getStylesheets().clear();
        s.getScene().getStylesheets().add(getRutaCssProducto());
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
       
        
        HBox contNombre = new HBox();
        
        contNombre.setAlignment(Pos.CENTER_LEFT);
        
            JFXTextField nombre = new JFXTextField();
            nombre.setPromptText("Nombre");
            nombre.getStyleClass().add("jfx-texto-largo");
            nombre.setLabelFloat(true);
            contNombre.getChildren().add(nombre);
        
        if(!b){
            ImageView img = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                img.setFitHeight(45);
                img.setFitWidth(45);
                HBox contImagen = new HBox(img);
                contImagen.setAlignment(Pos.CENTER);
                contImagen.setOnMouseClicked(new ManejadorBuscarProducto(true));
            contNombre.getChildren().add(contImagen);
            System.out.println(b);
        }
        
        
        JFXToggleButton estado = new JFXToggleButton();
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
        
        JFXTextField utilidad = new JFXTextField();
        utilidad.setPromptText("Utilidad");
        utilidad.setMaxWidth(150);
        utilidad.setLabelFloat(true);
        
        JFXTextField costo = new JFXTextField();
        costo.setPromptText("Costo");
        costo.setMaxWidth(150);
        costo.setLabelFloat(true);
        
        JFXTextField decripcion = new JFXTextField();
        decripcion.setPromptText("Decripcion");
        decripcion.getStyleClass().add("jfx-texto-largo2");
        
        decripcion.setLabelFloat(true);

        VBox cont1 = new VBox(contNombre, utilidad, costo, decripcion);
        cont1.setSpacing(65);
       
        cont1.setAlignment(Pos.TOP_LEFT);

        JFXButton examinar = new JFXButton("Examinar");
        examinar.setPadding(BOTONA);
        HBox contFoto = Metodos.crearPanel(new Label("Foto"), examinar);
        contFoto.setSpacing(15);
        
        JFXComboBox categBox = new JFXComboBox();
        categBox.setItems(CATG_PRODUCTOS); 
        HBox contBox = Metodos.crearPanel(new Label("Categoria: "), categBox);

        VBox cont2 = new VBox(estado, contFoto, contBox);
        cont2.setSpacing(65);
 
        cont2.setAlignment(Pos.TOP_LEFT);
        
        contMain = Metodos.crearPanel(cont1, cont2);
        contMain.setSpacing(40);
        contMain.setMaxHeight(400);
        contMain.setAlignment(Pos.CENTER);
        contMain.setPadding(new Insets(50,0,0,0)); 
   
        return contMain;
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
        return Class.class.getResource("/recursos/estiloFrancis.css").toExternalForm();
    }
}


/*
        FileChooser foto = new FileChooser();
        foto.setTitle("Open Resource File");
        foto.showOpenDialog(super.stage);
*/
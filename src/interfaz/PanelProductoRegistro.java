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
    
    
   
    
    private HBox contMain;
    
    public PanelProductoRegistro(Stage s,StackPane lastRoot){
        super(s,lastRoot);
        super.border.setCenter(crearFormulario());
        
    }
    
    
    public HBox crearFormulario(){
       
        
        JFXTextField nombre = new JFXTextField();
        nombre.setPromptText("Nombre");
        nombre.setLabelFloat(true);
        
        
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
        decripcion.setMaxWidth(270);
        decripcion.setLabelFloat(true);

        VBox cont1 = new VBox(nombre, utilidad, costo, decripcion);
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
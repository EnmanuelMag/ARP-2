/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constantes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 *
 * @author emman
 */
public class Metodos {
    
    public static void dialogoMaterial(Pane panel,String titulo, String cuerpo, String boton){
        JFXDialog dialogo;
        JFXDialogLayout contenido = new JFXDialogLayout();
        contenido.setHeading(new Text(titulo));
        contenido.setBody(new Text(cuerpo));
        JFXButton okay = new JFXButton(boton);
                    
        dialogo = new JFXDialog((StackPane) panel, contenido, JFXDialog.DialogTransition.CENTER);
                    
            okay.setOnAction(e -> dialogo.close());
        contenido.setActions(okay);
        
        dialogo.show();
        
    }
    
    public static HBox crearPanel(Node node, Node node2){
        HBox hbox= new HBox();
        hbox.getChildren().addAll(node, node2);
        return hbox;
    }
    
}

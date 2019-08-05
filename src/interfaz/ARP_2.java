/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 *
 * @author emman
 */
public class ARP_2 extends Application {
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage=primaryStage;
        PanelInicio pI = new PanelInicio(primaryStage);
        primaryStage.setTitle("Administrador FIGOCOBOS CRUZ");
        this.primaryStage.getIcons().add(new Image(ARP_2.class.getResourceAsStream("/recursos/iconos/icon.png")));
        primaryStage.setScene(new Scene(pI.getRoot(),1280,720));
        primaryStage.getScene().getStylesheets().add(getRutaCssFile());
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
     public String getRutaCssFile(){
        return PanelInicio.class.getResource("/recursos/estiloFrancis.css").toExternalForm();
    }
    
}

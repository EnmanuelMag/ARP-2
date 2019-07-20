/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author emman
 */
public class FrancisPan extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        PanelInicio pI = new PanelInicio(primaryStage);
   
        primaryStage.setTitle("Administrador FRANCIS PAN");
        primaryStage.setScene(pI.getScene());
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import static constantes.Constantes.BOTON;
import static constantes.Constantes.ESPACIADO;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author emman
 */
public class PanelGenerico {
    
    private JFXButton volver;
    private JFXButton mPrin;
    protected StackPane root;
    protected BorderPane border;
    protected Stage stage;
    
    public PanelGenerico(Stage s){
        this.stage = s;
        
        this.volver = new JFXButton("Volver");
        volver.setPadding(BOTON);
        volver.setAlignment(Pos.TOP_RIGHT);
        //volver.setOnAction(new ManejadorVolver());
    
        this.mPrin = new JFXButton("Menú Principal");
        this.mPrin.setOnAction(new ManejadorPrincipal());
        this.mPrin.setAlignment(Pos.TOP_LEFT);
        mPrin.setPadding(BOTON);
        
        this.border = new BorderPane();
        border.setPadding(ESPACIADO);
        border.setLeft(volver);
        border.setRight(mPrin);
        this.root = new StackPane(border);
    }
    
    public class ManejadorPrincipal implements EventHandler{
        
        
        @Override
        public void handle(Event event) {
            
            PanelRegistro pR = new PanelRegistro(stage);
            stage.setScene(pR.getScene());
        }
    }
    
}

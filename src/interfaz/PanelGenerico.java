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
    protected StackPane lastRoot;
    
    public PanelGenerico(Stage s,StackPane lastRoot){
        this.stage = s;
        this.lastRoot=lastRoot;
        this.volver = new JFXButton("Volver");
        volver.setPadding(BOTON);
        volver.setAlignment(Pos.TOP_RIGHT);
        volver.setOnAction((y)->{
            s.getScene().setRoot(lastRoot);
        });
    
        this.mPrin = new JFXButton("Menú Principal");
        this.mPrin.setOnAction(new ManejadorPrincipal());
        this.mPrin.setAlignment(Pos.TOP_LEFT);
        mPrin.setPadding(BOTON);
        
        this.border = new BorderPane();
        
        border.setPadding(ESPACIADO);
        border.setLeft(volver);
        border.setRight(mPrin);
        this.root = new StackPane(border);
        root.setAlignment(Pos.CENTER);
    }
    
    public class ManejadorPrincipal implements EventHandler{
        
        @Override
        public void handle(Event event) {
            PanelInicio pi=new PanelInicio(stage);
            stage.getScene().setRoot(pi.getRoot());
            //PanelRegistro pR = new PanelRegistro(stage);
            ///stage.setScene(pR.getScene());
        }
    }
    
    public class ManejadorVolver implements EventHandler{
         
        @Override
        public void handle(Event event) {
            stage.getScene().setRoot(lastRoot);
            //PanelRegistro pR = new PanelRegistro(stage);
            ///stage.setScene(pR.getScene());
        }
    }

    public StackPane getRoot() {
        return root;
    }

    public void setRoot(StackPane root) {
        this.root = root;
    }

    public BorderPane getBorder() {
        return border;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import java.time.LocalDate;
import java.util.LinkedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Pedido;

/**
 *
 * @author Josue
 */
public final class PanelPedidosFacturar extends PanelGenerico{
   
    private LinkedList<Pedido> pedidosPorFacturar;
    private LinkedList<VBox> pedidosTarjeta;
    private  ScrollPane scroll;
    
    public PanelPedidosFacturar(Stage s, StackPane lastRoot) {
        super(s, lastRoot);
        super.stage.getScene().getStylesheets().clear();
        super.stage.getScene().getStylesheets().add(getRutaCssFile());
       
        setRoot();
        setTop();
                
    }
    
    public void setTop(){
        Label titulo=new Label("Pedidos");
        titulo.getStyleClass().clear();
        titulo.getStyleClass().add("label-titulos-paneles");
        HBox cTitulo=new HBox(titulo);
        cTitulo.setAlignment(Pos.CENTER);
        ((BorderPane)super.getBorder()).setTop(cTitulo);
        
    }

    public BorderPane getBorder() {
        return border;
    }
    
    public String getRutaCssFile(){
        return PanelReporteVentas.class.getResource("/recursos/estilo-PanelFactura.css").toExternalForm();
        
    }
    
    public void setRoot(){
        scroll=new ScrollPane();
        scroll.setPadding(new Insets(20));
        VBox cScroll=new VBox();
        cScroll.setPadding(new Insets(15));
        cScroll.setSpacing(20);
        scroll.setContent(cScroll);
        
        cScroll.getChildren().addAll(pedidosTarjeta);
        scroll.setContent(cScroll);
        scroll.setFitToWidth(true);
        ((BorderPane)super.border).setCenter(scroll);
        
    }
    
   
   
    
}

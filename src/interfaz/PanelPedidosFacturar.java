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
import sistema.Cliente;
import sistema.FilaFactura;
import sistema.Pedido;

/**
 *
 * @author Josue
 */
public class PanelPedidosFacturar extends PanelGenerico{
   
    private LinkedList<Pedido> pedidosPorFacturar;
    private LinkedList<VBox> pedidosTarjeta;
    private  ScrollPane scroll;
    
    public PanelPedidosFacturar(Stage s, StackPane lastRoot) {
        super(s, lastRoot);
        super.stage.getScene().getStylesheets().clear();
        super.stage.getScene().getStylesheets().add(getRutaCssFile());
        cargarPedidos();
        cargarTarjetaPedidos();
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
    
    public void cargarPedidos(){
        pedidosPorFacturar=new LinkedList<>();
        
        pedidosPorFacturar.add(new Pedido(new Cliente(1,"Josue Alexander","Cobos Salvador","0951749456"," "," "),LocalDate.now()));
        
        pedidosPorFacturar.add(new Pedido(new Cliente(2,"Josue Alexander","Cobos Salvador","0951749456"," "," "),LocalDate.now()));
        
        pedidosPorFacturar.add(new Pedido(new Cliente(3,"Josue Alexander","Cobos Salvador","0951749456"," "," "),LocalDate.now()));
        
        pedidosPorFacturar.add(new Pedido(new Cliente(4,"Josue Alexander","Cobos Salvador","0951749456"," "," "),LocalDate.now()));

    }
    
    public void cargarTarjetaPedidos(){
        pedidosTarjeta=new LinkedList<>();
        for(Pedido p :pedidosPorFacturar){
            VBox v=new VBox();
            JFXButton b=new JFXButton("Facturar");
            b.setOnMouseClicked((r)->{
                stage.getScene().setRoot(new PanelFacturaRegistro(stage,root,p).getRoot());
            });
            v.getStyleClass().add("tarjetaPedido");
            v.setAlignment(Pos.CENTER_LEFT);
            v.setPadding(new Insets(20));
            GridPane g=new GridPane();
            g.setVgap(20);
            g.setHgap(20);
            g.addColumn(0, new Label("Cédula"), new Label("Nombres"), new Label("Fecha"));
            g.addColumn(1, new Label(p.getCliente().getCedula()) ,new Label(p.getCliente().getNombreCompleto()), new Label(p.getFecha().toString()));
            g.add(b,10,2);
            v.getChildren().add(g);
            pedidosTarjeta.add(v);
        }
        
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXComboBox;
import constantes.Constantes;
import static constantes.Constantes.TIPO_CLIENTES;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sistema.ClienteDB;
import sistema.Factura;
import sistema.ReporteDB;
import sistema.VentaPorCliente;

/**
 *
 * @author Josue
 */
public class SubPanelReporteVentas extends PanelGenerico {
    
    private Label subtitulo;
    private LocalDateTime desde;
    private LocalDateTime hasta;
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public SubPanelReporteVentas(Stage s, StackPane lastRoot,LocalDateTime desde, LocalDateTime hasta) {
        super(s, lastRoot);
        this.desde=desde;
        this.hasta=hasta;
        setCenterDefecto();
    }
    
    public void setSubtitulo(String subtitulo){
        this.subtitulo=new Label("Reporte Ventas por: "+subtitulo);
        HBox cTitulo=new HBox(this.subtitulo);
        cTitulo.setAlignment(Pos.CENTER);
        ((BorderPane)super.getBorder()).setTop(cTitulo);
    }
    
    public void setCenterDefecto(){
        ((BorderPane)super.getBorder()).setCenter(centroDefecto());
        
    }
    
    public TableView<VentaPorCliente> getTablaDefecto(){
        TableView<VentaPorCliente> tablaTotales=new TableView<>();
        
        TableColumn<VentaPorCliente,String> cCliente=new TableColumn<>("Cliente"); 
        cCliente.setPrefWidth(300);
        TableColumn<VentaPorCliente,String> cNombres=new TableColumn<>("Nombres");
        cNombres.setPrefWidth(150);
        TableColumn<VentaPorCliente,String> cApellidos=new TableColumn<>("Apellidos");
        cApellidos.setPrefWidth(150);
        cCliente.getColumns().addAll(cNombres,cApellidos);
        TableColumn<VentaPorCliente,Double> cMonto=new TableColumn<>("Monto Total"); 
        cMonto.setPrefWidth(300);
        tablaTotales.setPrefWidth(800);
        tablaTotales.setPrefHeight(900);
        tablaTotales.getColumns().addAll(cCliente,cMonto);
        
        //String desde=formato.format(this.desde);
        //String hasta=formato.format(this.hasta);
        //tablaTotales.setItems(FXCollections.observableArrayList(ReporteDB.getVentasClientes(desde, hasta)));
        return tablaTotales;
    }
    
    public GridPane gridFiltros(){
        GridPane filtros=new GridPane();
        filtros.setVgap(26);
        filtros.setHgap(26);
        JFXComboBox clientesBox = new JFXComboBox();
        JFXComboBox tipoPBox = new JFXComboBox();
        tipoPBox.setItems(Constantes.CATG_PRODUCTOS);
        tipoPBox.getItems().add("TODOS");
        tipoPBox.getSelectionModel().selectLast();
        JFXComboBox tipoCBox = new JFXComboBox();
        tipoCBox.setOnMouseClicked((w)->{clientesBox.setValue(null);});
        tipoCBox.setItems(TIPO_CLIENTES); 
        clientesBox.setOnMouseClicked((w)->{tipoCBox.setValue("TODOS");});
        
        clientesBox.setItems(FXCollections.observableArrayList("Josue","Enmanuel"));
        /*try{
            clientesBox.setItems(FXCollections.observableArrayList(ClienteDB.getAllNombres())); 
        }catch(SQLException e){
            JFXAlert alerta=new JFXAlert(super.stage);
            alerta.setHeaderText("Error Datos");
            alerta.setContentText("Ha ocurrido un error al conectarse a la base de datos");
        }*/
        
        filtros.addColumn(0, new Label("Tipo Cliente:"),tipoCBox);
        filtros.addColumn(1, new Label("Tipo Producto:"),tipoPBox);
        filtros.addColumn(2, new Label("Cliente:"),clientesBox);
        return filtros;
    }
    
    public VBox centroDefecto(){
        VBox cCentro=new VBox();
        cCentro.setSpacing(20);
        cCentro.setPadding(new Insets(20));
        cCentro.getChildren().add(gridFiltros());
        cCentro.getChildren().add(getTablaDefecto());
        return cCentro;
        
    }
    
}

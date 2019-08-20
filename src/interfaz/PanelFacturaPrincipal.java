/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import static constantes.Constantes.*;
import controlador.DB;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.*;

/**
 *
 * @author Josue
 */
public class PanelFacturaPrincipal extends PanelGenerico{
    
    private JFXTreeTableView<Factura> facturasT;
    private List<Factura> facturas;
    
    public PanelFacturaPrincipal(Stage s, StackPane lastRoot) {
        super(s, lastRoot);
        facturas=(List<Factura>)DB.getAll(Factura.class);
        s.getScene().getStylesheets().clear();
        s.getScene().getStylesheets().add(getRutaCssFile());
        super.border.setCenter(crearCentro());
        
    }
    
    public VBox crearCentro(){
        
        HBox c1 = new HBox(new Label("FACTURAS"));
        c1.getStyleClass().add("label-titulos-paneles");
        c1.setAlignment(Pos.CENTER);
        border.setTop(c1);
       
        //Cuando se seleccione un tipo de filtrador se hace un query para conocer las opciones
        //Si es por producto, se ingresa el nombre en el textfield que se mostrara
        //Si es proveedor, lo mismo
        //Si es por estado (recivido (completo), parcialmente, pendiente)
        
        JFXComboBox<String> filtrarPor = new JFXComboBox<>();
        HBox contFiltro = new HBox(new Label("Filtrar por: "), filtrarPor);
        contFiltro.setSpacing(20);
        
        filtrarPor.setItems(FILTRO_POR_FACTURAS);
        filtrarPor.setOnAction(e -> {
            if(filtrarPor.getValue()!=null){
                if(contFiltro.getChildren().size()==3) contFiltro.getChildren().remove(2);
                switch(filtrarPor.getValue()){
                    
                    case "NOMBRE CLIENTE":
                        JFXTextField nProd = new JFXTextField();
                        nProd.setPromptText("Nombre del cliente");
                        ImageView img = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                        img.setFitHeight(45);
                        img.setFitWidth(45);
                        HBox contImagen = new HBox(img);
                        contImagen.setAlignment(Pos.CENTER);
                        nProd.setLabelFloat(true);
                        HBox cont = new HBox(nProd, contImagen);
                        contFiltro.getChildren().add(cont);
                        break;
                    case "FECHA":
                        JFXDatePicker date0=new JFXDatePicker();
                        date0.setPrefWidth(125);
                        date0.setPrefWidth(125);
                        JFXDatePicker date1=new JFXDatePicker();
                        HBox contfechas = new HBox(date0, date1);
                        contFiltro.getChildren().add(contfechas);
                        break;
                }
            }
        });
        
        JFXButton registrar = new JFXButton("REGISTRAR NUEVA");
        registrar.getStyleClass().add("jfx-button-registrar");
        registrar.setOnAction(e -> {
            PanelFacturaRegistro pR = new PanelFacturaRegistro(stage, root,new Pedido());
            stage.getScene().setRoot(pR.getRoot());
        });
        
        HBox contRoot = new HBox(contFiltro);
        contRoot.setPadding(ESPACIADO_FILTROS);
        
        //registrar
        HBox contTop = new HBox(contRoot);
        contTop.setSpacing(80);
        contTop.setAlignment(Pos.CENTER_LEFT);
        
        
        JFXTreeTableColumn nOrden = new JFXTreeTableColumn<>("N° Factura");
        nOrden.setPrefWidth(125);
        nOrden.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Factura, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Factura, String> factura) {
                return new ReadOnlyObjectWrapper(factura.getValue().getValue().getFacturaId());
            }
        });
        
        JFXTreeTableColumn rucCliente = new JFXTreeTableColumn<>("Ruc Cliente");
        rucCliente.setPrefWidth(180);
        rucCliente.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Factura, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Factura, String> factura) {
                return new ReadOnlyObjectWrapper(factura.getValue().getValue().getCliente().getRuc());
            }
        });
        
        JFXTreeTableColumn cliente = new JFXTreeTableColumn<>("Cliente");
        cliente.setPrefWidth(300);
        cliente.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Factura, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Factura, String> factura) {
                Cliente c=factura.getValue().getValue().getCliente();
                return new ReadOnlyObjectWrapper(c.getNombres()+" "+c.getApellidos());
            }
        });
        
        JFXTreeTableColumn tipoC = new JFXTreeTableColumn<>("Tipo Cliente");
        tipoC.setPrefWidth(225);
        tipoC.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Factura, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Factura, String> factura) {
                return new ReadOnlyObjectWrapper(factura.getValue().getValue().getCliente().getTipoCliente().getTipo());
            }
        });
        
        JFXTreeTableColumn total = new JFXTreeTableColumn<>("Total");
        total.setPrefWidth(120);
        total.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Factura, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Factura, String> factura) {
                    Factura f=factura.getValue().getValue();
                    //subtotal
                    return new ReadOnlyObjectWrapper(f.getSubTotal());
                }});
        

        ObservableList<Factura> proveedoresOL = FXCollections.observableArrayList();
        proveedoresOL.addAll(facturas);
        
        TreeItem<Factura> root = new RecursiveTreeItem<>(proveedoresOL, RecursiveTreeObject::getChildren);
        facturasT = new JFXTreeTableView<>(root);
        facturasT.setShowRoot(false);
        facturasT.setEditable(false);
        facturasT.getColumns().setAll(nOrden, rucCliente,cliente, tipoC, total);
        facturasT.setMinHeight(500);
        
        VBox contRoot2 = new VBox(contTop, facturasT);
        contRoot2.setSpacing(40);
        contRoot2.setCenterShape(true);
        contRoot2.setPadding(ESPACIADO2);
        
        return contRoot2;
    }
    
    public String getRutaCssFile(){
        return Class.class.getResource("/recursos/estilo-PanelFactura.css").toExternalForm();
    }
    
}

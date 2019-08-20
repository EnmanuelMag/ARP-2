/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import static constantes.Constantes.*;
import controlador.DB;
import java.text.SimpleDateFormat;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
 * @author emman
 */
public class PanelPedidosPrincipal extends PanelGenerico{
    

    private boolean primero = true;
    private VBox contSp;
   
    private JFXDialog diag;
    
    private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    private JFXTreeTableView<Pedido> pedidosT;
    private List<Pedido> pedidos;
    
    public PanelPedidosPrincipal(Stage p,StackPane lastRoot){
        super(p, lastRoot);
        
        pedidos=(List<Pedido>)DB.getAll(Pedido.class);
        System.out.println(pedidos);
        super.border.setCenter(crearCentro());
        
        p.getScene().getStylesheets().clear();
        p.getScene().getStylesheets().add(getRutaCssFile());
    }
    
    
    public VBox crearCentro(){
        
        HBox c1 = new HBox(new Label("PEDIDOS"));
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
        
        filtrarPor.setItems(FILTRO_POR_PEDIDOS);
        filtrarPor.setOnAction(e -> {
            if(filtrarPor.getValue()!=null){
                if(contFiltro.getChildren().size()==3) contFiltro.getChildren().remove(2);
                switch(filtrarPor.getValue()){
                    
                    case "TIPO DE CLIENTE":
                        JFXComboBox<String> estado = new JFXComboBox<>();
                        //CONSULTA DE LOS TIPO
                        estado.setItems(TIPO_CLIENTES);
                        ImageView img = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                        img.setFitHeight(45);
                        img.setFitWidth(45);
                        HBox contImagen = new HBox(img);
                        contImagen.setAlignment(Pos.CENTER);
                        HBox cont = new HBox(estado, contImagen);
                    
                        contFiltro.getChildren().add(cont);
                        break;
                    case "CLIENTE":
                        JFXTextField nProv = new JFXTextField();
                        nProv.setPromptText("Nombre del Cliente");
                        nProv.setLabelFloat(true);
                        ImageView img2 = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                        img2.setFitHeight(45);
                        img2.setFitWidth(45);
                        HBox contImagen2 = new HBox(img2);
                        contImagen2.setAlignment(Pos.CENTER);
                        HBox cont2 = new HBox(nProv, contImagen2);
                        contFiltro.getChildren().add(cont2);
                        break;
                    
                }
            }
        });
        
        JFXButton registrar = new JFXButton("REGISTRAR NUEVA");
        registrar.getStyleClass().add("jfx-button-registrar");
        registrar.setOnAction(e -> {
            PanelPedidoRegistro pR = new PanelPedidoRegistro(stage, root, true);
            stage.getScene().setRoot(pR.getRoot());
        });
        
        HBox contRoot = new HBox(contFiltro);
        contRoot.setPadding(ESPACIADO_FILTROS);
       
        
        HBox contTop = new HBox(contRoot, registrar);
        contTop.setSpacing(80);
        contTop.setAlignment(Pos.CENTER_LEFT);
        
        JFXTreeTableColumn nOrden = new JFXTreeTableColumn<>("N° Pedido");
        nOrden.setPrefWidth(200);
        nOrden.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Pedido, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Pedido, String> orden) {
                    return new ReadOnlyObjectWrapper(orden.getValue().getValue().getPedidoId());
        }});
        
        JFXTreeTableColumn proveedor = new JFXTreeTableColumn<>("Nombre del Cliente");
        proveedor.setPrefWidth(300);
        proveedor.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Pedido, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Pedido, String> orden) {
                    Cliente c=orden.getValue().getValue().getCliente();
                    return new ReadOnlyObjectWrapper(c.getNombres()+" "+c.getApellidos());
        }});
        
        JFXTreeTableColumn fecha_pedido = new JFXTreeTableColumn<>("Fecha de Pedido");
        fecha_pedido.setPrefWidth(300);
        fecha_pedido.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Pedido, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Pedido, String> orden) {
                    return new ReadOnlyObjectWrapper(dateFormat.format(orden.getValue().getValue().getFecha()));
        }});
        
        
        JFXTreeTableColumn estado = new JFXTreeTableColumn<>("Estado");
        estado.setPrefWidth(300);
        estado.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Pedido, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Pedido, String> orden) {
                    //Orden o=orden.getValue().getValue();
                    //subtotal
                    if(orden.getValue().getValue().getEstado()){
                        
                        return new ReadOnlyObjectWrapper("Facturado");
                    }
                        return new ReadOnlyObjectWrapper("No Facturado");
                }});
        
        ObservableList<Pedido> proveedoresOL = FXCollections.observableArrayList();
        proveedoresOL.addAll(this.pedidos);

        TreeItem<Pedido> root = new RecursiveTreeItem<>(proveedoresOL, RecursiveTreeObject::getChildren);
        pedidosT = new JFXTreeTableView<>(root);
        pedidosT.setShowRoot(false);
        pedidosT.setEditable(false);
        pedidosT.getColumns().setAll(nOrden, proveedor, fecha_pedido, estado);
        pedidosT.setMinHeight(500);
        
        pedidosT.setOnMouseClicked((t)->{
            Pedido p=pedidosT.getSelectionModel().getSelectedItem().getValue();
            //getEstado false es que no ha sido facturada
            if(p!=null && !p.getEstado()){
                this.stage.getScene().setRoot(new PanelFacturaRegistro(this.stage,this.root,p).getRoot());
                
            }
        });
        
        VBox contRoot2 = new VBox(contTop, pedidosT);
        contRoot2.setSpacing(40);
        contRoot2.setCenterShape(true);
        contRoot2.setPadding(ESPACIADO2);
        
        return contRoot2;
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
        return Class.class.getResource("/recursos/estilo-PanelPedido.css").toExternalForm();
    }
}

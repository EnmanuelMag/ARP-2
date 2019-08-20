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
import static constantes.Constantes.ESPACIADO2;
import static constantes.Constantes.ESPACIADO_FILTROS;
import static constantes.Constantes.FILTRO_POR_CLIENTES;
import static constantes.Constantes.FILTRO_POR_FACTURAS;
import controlador.DB;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.Cliente;


/**
 *
 * @author Josue
 */
public class PanelClientePrincipal extends PanelGenerico {
    
    private JFXTreeTableView<Cliente> clientesT;
    private List<Cliente> clientes;
    
    public PanelClientePrincipal(Stage s, StackPane lastRoot) {
        super(s, lastRoot);
        clientes=(List<Cliente>)DB.getAll(Cliente.class);
        System.out.println(clientes);
        s.getScene().getStylesheets().clear();
        s.getScene().getStylesheets().add(getRutaCssFile());
        super.border.setCenter(crearCentro());
        clientesT.refresh();
        
    }
    
    public VBox crearCentro(){
        
        HBox c1 = new HBox(new Label("CLIENTES"));
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
        
        filtrarPor.setItems(FILTRO_POR_CLIENTES);
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
                }
            }
        });
        
        JFXButton registrar = new JFXButton("REGISTRAR NUEVO");
        registrar.getStyleClass().add("jfx-button-registrar");
        registrar.setOnAction(e -> {
            PanelClienteRegistro pR = new PanelClienteRegistro(stage, root,false);
            stage.getScene().setRoot(pR.getRoot());
        });
        
        HBox contRoot = new HBox(contFiltro);
        contRoot.setPadding(ESPACIADO_FILTROS);
       
        
        HBox contTop = new HBox(contRoot, registrar);
        contTop.setSpacing(80);
        contTop.setAlignment(Pos.CENTER_LEFT);
        
        
        JFXTreeTableColumn rucCliente = new JFXTreeTableColumn<>("Ruc Cliente");
        rucCliente.setPrefWidth(200);
        rucCliente.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Cliente, String> cliente) {
                    return new ReadOnlyObjectWrapper(cliente.getValue().getValue().getRuc());
                }});
        
        JFXTreeTableColumn cliente = new JFXTreeTableColumn<>("Cliente");
        cliente.setPrefWidth(300);
        cliente.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Cliente, String> cliente) {
                    Cliente c=cliente.getValue().getValue();
                    return new ReadOnlyObjectWrapper(c.getNombres()+" "+c.getApellidos());
                }});
        JFXTreeTableColumn tipoCliente = new JFXTreeTableColumn<>("Tipo Cliente");
        tipoCliente.setPrefWidth(225);
        tipoCliente.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Cliente, String> cliente) {
                    Cliente c=cliente.getValue().getValue();
                    //return new ReadOnlyObjectWrapper(c.getTipoCliente().getTipo());
                    return new ReadOnlyObjectWrapper("tipo") ;
                    
                }});
        
        ObservableList<Cliente> clientesOL = FXCollections.observableArrayList();
        clientesOL.addAll(clientes);
        
        TreeItem<Cliente> root = new RecursiveTreeItem<>(clientesOL, RecursiveTreeObject::getChildren);
        clientesT = new JFXTreeTableView<>(root);
        clientesT.setTooltip(new Tooltip("De doble sobre una fila para más detalles"));
        clientesT.setOnMouseClicked((i)->{
            Cliente c=clientesT.getSelectionModel().getSelectedItem().getValue();
            if(c!=null){
                if(i.getClickCount()==2){
                    this.stage.getScene().setRoot(new PanelClienteRegistro(this.stage,this.root,false).getRoot());
                           
                    
                }
            }
        });
        clientesT.setShowRoot(false);
        clientesT.setEditable(false);
        
        clientesT.getColumns().setAll(rucCliente,cliente, tipoCliente);
        clientesT.setMinHeight(500);
        
        VBox contRoot2 = new VBox(contTop, clientesT);
        contRoot2.setSpacing(40);
        contRoot2.setCenterShape(true);
        contRoot2.setPadding(ESPACIADO2);
        
        return contRoot2;
    }
    
    
    
    public String getRutaCssFile(){
        return Class.class.getResource("/recursos/estilo-PanelCliente.css").toExternalForm();
    }
    
}

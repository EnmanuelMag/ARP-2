/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import modelo.Cliente;
import modelo.Proveedor;

/**
 *
 * @author Josue
 */
public class PanelProveedorPrincipal extends PanelGenerico {
    private JFXTreeTableView<Proveedor> proveedoresT;
    private List<Proveedor> proveedores;
    public PanelProveedorPrincipal(Stage s, StackPane lastRoot) {
        super(s, lastRoot);
        proveedores=(List<Proveedor>)DB.getAll(Proveedor.class);
        s.getScene().getStylesheets().clear();
        s.getScene().getStylesheets().add(getRutaCssFile());
        super.border.setCenter(crearCentro());

    }

    public VBox crearCentro() {

        HBox c1 = new HBox(new Label("PROVEEDORES"));
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

        filtrarPor.setItems(FILTRO_POR_PROVEEDORES);
        filtrarPor.setOnAction(e -> {
            if (filtrarPor.getValue() != null) {
                if (contFiltro.getChildren().size() == 3) {
                    contFiltro.getChildren().remove(2);
                }
                switch (filtrarPor.getValue()) {
                    case "NOMBRE":
                        JFXTextField nProveedor = new JFXTextField();
                        nProveedor.setPromptText("Nombre del proveedor");
                        ImageView img = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                        img.setFitHeight(45);
                        img.setFitWidth(45);
                        HBox contImagen = new HBox(img);
                        contImagen.setAlignment(Pos.CENTER);
                        nProveedor.setLabelFloat(true);
                        HBox cont = new HBox(nProveedor, contImagen);
                        contFiltro.getChildren().add(cont);
                        break;

                }
            }
        });

        JFXButton registrar = new JFXButton("REGISTRAR NUEVO");
        registrar.getStyleClass().add("jfx-button-registrar");
        registrar.setOnAction(e -> {
            PanelProveedorRegistro pR = new PanelProveedorRegistro(stage, root, false);
            stage.getScene().setRoot(pR.getRoot());
        });

        HBox contRoot = new HBox(contFiltro);
        contRoot.setPadding(ESPACIADO_FILTROS);
       

        HBox contTop = new HBox(contRoot, registrar);
        contTop.setSpacing(80);
        contTop.setAlignment(Pos.CENTER_LEFT);

        JFXTreeTableColumn ruc = new JFXTreeTableColumn<>("RUC");
        ruc.setPrefWidth(200);
        ruc.setPrefWidth(120);
        ruc.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Proveedor, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Proveedor, String> proveedor) {
                    return new ReadOnlyObjectWrapper(proveedor.getValue().getValue().getRuc());
                }});
        
        JFXTreeTableColumn proveedor = new JFXTreeTableColumn<>("Nombres");
        proveedor.setPrefWidth(300);
        proveedor.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Proveedor, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Proveedor, String> proveedor) {
                    Proveedor p=proveedor.getValue().getValue();
                    return new ReadOnlyObjectWrapper(p.getRazonSocial());
                }});
        
        
        JFXTreeTableColumn telefono = new JFXTreeTableColumn<>("Teléfono");
        telefono.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Proveedor, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Proveedor, String> proveedor) {
                    Proveedor p=proveedor.getValue().getValue();
                    return new ReadOnlyObjectWrapper(p.getTelefono());
                }});
        telefono.setPrefWidth(225);
        

        ObservableList<Proveedor> proveedoresOL = FXCollections.observableArrayList();
        proveedoresOL.addAll(proveedores);
        
        TreeItem<Proveedor> root = new RecursiveTreeItem<>(proveedoresOL, RecursiveTreeObject::getChildren);
        proveedoresT = new JFXTreeTableView<>(root);
        proveedoresT.setShowRoot(false);
        proveedoresT.setEditable(false);
        proveedoresT.getColumns().setAll(ruc, proveedor, telefono);
        proveedoresT.setMinHeight(500);
        
        VBox contRoot2 = new VBox(contTop, proveedoresT);
        contRoot2.setSpacing(40);
        contRoot2.setCenterShape(true);
        contRoot2.setPadding(ESPACIADO2);

        return contRoot2;
    }

    public String getRutaCssFile() {
        return Class.class.getResource("/recursos/estilo-PanelProveedores.css").toExternalForm();
    }

}

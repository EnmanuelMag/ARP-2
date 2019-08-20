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
import modelo.*;

/**
 *
 * @author Josue
 */
public class PanelProductoPrincipal extends PanelGenerico {

    private JFXTreeTableView<Producto> productosT;
    private List<Producto> productos;
    
    
    public PanelProductoPrincipal(Stage s, StackPane lastRoot) {
        super(s, lastRoot);
        productos=(List<Producto>)DB.getAll(Producto.class);
        s.getScene().getStylesheets().clear();
        s.getScene().getStylesheets().add(getRutaCssFile());
        super.border.setCenter(crearCentro());
        productosT.refresh();
    }

    public VBox crearCentro() {

        HBox c1 = new HBox(new Label("PRODUCTOS"));
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

        filtrarPor.setItems(FILTRO_POR_PRODUCTO);
        filtrarPor.setOnAction(e -> {
            if (filtrarPor.getValue() != null) {
                if (contFiltro.getChildren().size() == 3) {
                    contFiltro.getChildren().remove(2);
                }
                switch (filtrarPor.getValue()) {

                    case "NOMBRE PRODUCTO":
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
                    case "PROVEEDOR":
                        JFXTextField proveedor = new JFXTextField();
                        proveedor.setPromptText("Nombre del proveedor");
                        ImageView img2 = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                        img2.setFitHeight(45);
                        img2.setFitWidth(45);
                        HBox contImagen2 = new HBox(img2);
                        contImagen2.setAlignment(Pos.CENTER);
                        proveedor.setLabelFloat(true);
                        HBox cont2 = new HBox(proveedor, contImagen2);
                        contFiltro.getChildren().add(cont2);
                        break;
                    case "TIPO":
                        JFXTextField tipo = new JFXTextField();
                        tipo.setPromptText("Tipo");
                        ImageView img3 = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                        img3.setFitHeight(45);
                        img3.setFitWidth(45);
                        HBox contImagen3 = new HBox(img3);
                        contImagen3.setAlignment(Pos.CENTER);
                        tipo.setLabelFloat(true);
                        HBox cont3 = new HBox(tipo, contImagen3);
                        contFiltro.getChildren().add(cont3);
                        break;
                }
            }
        });

        JFXButton registrar = new JFXButton("REGISTRAR NUEVO");
        registrar.getStyleClass().add("jfx-button-registrar");
        registrar.setOnAction(e -> {
            PanelProductoRegistro pR = new PanelProductoRegistro(stage, root, false);
            stage.getScene().setRoot(pR.getRoot());
        });

        HBox contRoot = new HBox(contFiltro);
        contRoot.setPadding(ESPACIADO_FILTROS);
       

        HBox contTop = new HBox(contRoot, registrar);
        contTop.setSpacing(80);
        contTop.setAlignment(Pos.CENTER_LEFT);

        
        JFXTreeTableColumn nomProducto = new JFXTreeTableColumn<>("Producto");
        nomProducto.setPrefWidth(200);
        nomProducto.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Producto, String> producto) {
                    return new ReadOnlyObjectWrapper(producto.getValue().getValue().getNombre());
        }});
        
        JFXTreeTableColumn proveedor = new JFXTreeTableColumn<>("Proveedor");
        proveedor.setPrefWidth(300);
        proveedor.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Producto, String> producto) {
                    return new ReadOnlyObjectWrapper(producto.getValue().getValue().getProveedor().getRazonSocial());
        }});
        
        JFXTreeTableColumn tipoProducto = new JFXTreeTableColumn<>("Tipo producto");
        tipoProducto.setPrefWidth(225);
        tipoProducto.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Producto, String> producto) {
                    //return new ReadOnlyObjectWrapper(producto.getValue().getValue().getCategoria().getDescripcion());
                    return new ReadOnlyObjectWrapper(producto.getValue().getValue().getCategoria().getDescripcion());
        }});

        
        JFXTreeTableColumn estado = new JFXTreeTableColumn<>("Estado");
        estado.setPrefWidth(120);
        estado.setCellValueFactory(new Callback<JFXTreeTableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(JFXTreeTableColumn.CellDataFeatures<Producto, String> producto) {
                    if(producto.getValue().getValue().getDescontinuado()){
                        
                    return new ReadOnlyObjectWrapper("Descontinuado");
                    }
                    return new ReadOnlyObjectWrapper("En circulación");
        }});

        
        
        ObservableList<Producto> proveedoresOL = FXCollections.observableArrayList();
        proveedoresOL.addAll(productos);

        TreeItem<Producto> root = new RecursiveTreeItem<>(proveedoresOL, RecursiveTreeObject::getChildren);
        productosT = new JFXTreeTableView<>(root);
        productosT.setShowRoot(false);
        productosT.setEditable(false);
        productosT.getColumns().setAll(nomProducto, proveedor, tipoProducto, estado);
        productosT.setMinHeight(500);
        
        
        VBox contRoot2 = new VBox(contTop, productosT);
        contRoot2.setSpacing(40);
        contRoot2.setCenterShape(true);
        contRoot2.setPadding(ESPACIADO2);

        return contRoot2;
    }

    public String getRutaCssFile() {
        return Class.class.getResource("/recursos/estilo-PanelProducto.css").toExternalForm();
    }

}

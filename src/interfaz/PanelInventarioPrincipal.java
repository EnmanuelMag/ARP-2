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
import static constantes.Constantes.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Josue
 */
public class PanelInventarioPrincipal extends PanelGenerico {
    
    public PanelInventarioPrincipal(Stage s, StackPane lastRoot) {
        super(s, lastRoot);
        
        super.border.setCenter(crearCentro());
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add(getRutaCssFile());
    }
    
    public VBox crearCentro() {

        HBox c1 = new HBox(new Label("INVENTARIO"));
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

        filtrarPor.setItems(FILTRO_POR_ORDENES);
        filtrarPor.setOnAction(e -> {
            if (filtrarPor.getValue() != null) {
                if (contFiltro.getChildren().size() == 3) {
                    contFiltro.getChildren().remove(2);
                }
                switch (filtrarPor.getValue()) {
                    case "PRODUCTO":
                        JFXTextField producto = new JFXTextField();
                        producto.setPromptText("Producto");
                        ImageView img = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                        img.setFitHeight(45);
                        img.setFitWidth(45);
                        HBox contImagen = new HBox(img);
                        contImagen.setAlignment(Pos.CENTER);
                        producto.setLabelFloat(true);
                        HBox cont = new HBox(producto, contImagen);
                        contFiltro.getChildren().add(cont);
                        break;
                    case "PROVEEDOR":
                        JFXTextField nProveedor = new JFXTextField();
                        nProveedor.setPromptText("Proveedor");
                        ImageView img2 = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                        img2.setFitHeight(45);
                        img2.setFitWidth(45);
                        HBox contImagen2 = new HBox(img2);
                        contImagen2.setAlignment(Pos.CENTER);
                        nProveedor.setLabelFloat(true);
                        HBox cont2 = new HBox(nProveedor, contImagen2);
                        contFiltro.getChildren().add(cont2);
                        break;
                    case "ESTADO":
                        JFXTextField estado = new JFXTextField("Estado");
                        estado.setPromptText("Estado");
                        ImageView img3 = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                        img3.setFitHeight(45);
                        img3.setFitWidth(45);
                        HBox contImagen3 = new HBox(img3);
                        contImagen3.setAlignment(Pos.CENTER);
                        estado.setLabelFloat(true);
                        HBox cont3 = new HBox(estado, contImagen3);
                        contFiltro.getChildren().add(cont3);
                        break;


                }
            }
        });

        JFXButton pedidos = new JFXButton("PEDIDOS");
        pedidos.getStyleClass().add("jfx-button-registrar");
        pedidos.setOnAction(e -> {
            PanelPedidoRegistro pR = new PanelPedidoRegistro(stage, root, false);
            stage.getScene().setRoot(pR.getRoot());
        });

        HBox contRoot = new HBox(contFiltro);
        contRoot.setPadding(ESPACIADO_FILTROS);
       

        HBox contTop = new HBox(contRoot, pedidos);
        contTop.setSpacing(80);
        contTop.setAlignment(Pos.CENTER_LEFT);

        
        JFXTreeTableView inventario = new JFXTreeTableView<>();
        JFXTreeTableColumn producto = new JFXTreeTableColumn<>("Producto");
        producto.setPrefWidth(200);
        JFXTreeTableColumn proveedor = new JFXTreeTableColumn<>("Proveeedor");
        proveedor.setPrefWidth(300);
        JFXTreeTableColumn estado = new JFXTreeTableColumn<>("Estado");
        estado.setPrefWidth(225);
        JFXTreeTableColumn stock = new JFXTreeTableColumn<>("Stock");
        stock.setPrefWidth(225);
        JFXTreeTableColumn costo = new JFXTreeTableColumn<>("Costo");
        costo.setPrefWidth(225);
        inventario.getColumns().setAll(producto, proveedor,estado,stock, costo);
        inventario.setMinHeight(500);
//contTop, esto estaba en el contRoot2
        VBox contRoot2 = new VBox(contTop, inventario);
        contRoot2.setSpacing(40);
        contRoot2.setCenterShape(true);
        contRoot2.setPadding(ESPACIADO2);

        return contRoot2;
    }

    public String getRutaCssFile() {
        return Class.class.getResource("/recursos/estilo-PanelInventario.css").toExternalForm();
    }

    
}

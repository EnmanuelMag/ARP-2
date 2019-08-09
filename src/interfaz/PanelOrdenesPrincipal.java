/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import static constantes.Constantes.BOTON;
import static constantes.Constantes.BOTONA;
import static constantes.Constantes.ESPACIADO;
import static constantes.Constantes.ESPACIADO2;
import static constantes.Constantes.ESPACIADO_FILTROS;
import static constantes.Constantes.ESTADO;
import constantes.Metodos;
import java.time.LocalDate;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static constantes.Constantes.FILTRO_POR_ORDENES;

/**
 *
 * @author emman
 */
public class PanelOrdenesPrincipal extends PanelGenerico{
    
   
    private HBox contMain;
    private VBox contDetalle;
    private boolean primero = true;
    private VBox contSp;
   
    private JFXDialog diag;
    
    public PanelOrdenesPrincipal(Stage p,StackPane lastRoot){
        super(p, lastRoot);
        
 
        super.border.setCenter(crearCentro());
        
        p.getScene().getStylesheets().clear();
        p.getScene().getStylesheets().add(getRutaCssFile());
    }
    
    
    public VBox crearCentro(){
        
        HBox c1 = new HBox(new Label("ÓRDENES"));
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
            if(filtrarPor.getValue()!=null){
                if(contFiltro.getChildren().size()==3) contFiltro.getChildren().remove(2);
                switch(filtrarPor.getValue()){
                    
                    case "PRODUCTO":
                        JFXTextField nProd = new JFXTextField();
                        nProd.setPromptText("Nombre del Producto");
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
                        JFXTextField nProv = new JFXTextField();
                        nProv.setPromptText("RUC del Proveedor");
                        nProv.setLabelFloat(true);
                        ImageView img2 = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                        img2.setFitHeight(45);
                        img2.setFitWidth(45);
                        HBox contImagen2 = new HBox(img2);
                        contImagen2.setAlignment(Pos.CENTER);
                        HBox cont2 = new HBox(nProv, contImagen2);
                        contFiltro.getChildren().add(cont2);
                        break;
                    case "ESTADO":
                        JFXComboBox<String> estado = new JFXComboBox<>();
                        estado.setItems(ESTADO);
                        ImageView img3 = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                        img3.setFitHeight(45);
                        img3.setFitWidth(45);
                        HBox contImagen3 = new HBox(img3);
                        contImagen3.setAlignment(Pos.CENTER);
                        HBox cont3 = new HBox(estado, contImagen3);
                        contFiltro.getChildren().add(cont3);
                        break;
                }
            }
        });
        
        JFXButton registrar = new JFXButton("REGISTRAR NUEVA");
        registrar.getStyleClass().add("jfx-button-registrar");
        registrar.setOnAction(e -> {
            PanelOrdenesRegistro pR = new PanelOrdenesRegistro(stage, root, true);
            stage.getScene().setRoot(pR.getRoot());
        });
        
        HBox contRoot = new HBox(contFiltro);
        contRoot.setPadding(ESPACIADO_FILTROS);
        contRoot.getStyleClass().add("contCategoriaBackground");
        
        HBox contTop = new HBox(contRoot, registrar);
        contTop.setSpacing(80);
        contTop.setAlignment(Pos.CENTER_LEFT);
        
        JFXTreeTableView ordenes = new JFXTreeTableView<>();
        JFXTreeTableColumn nOrden = new JFXTreeTableColumn<>("N° Órden");
        nOrden.setPrefWidth(200);
        JFXTreeTableColumn proveedor = new JFXTreeTableColumn<>("Proveedor");
        proveedor.setPrefWidth(300);
        JFXTreeTableColumn fecha_pedido = new JFXTreeTableColumn<>("Fecha de la Órden");
        fecha_pedido.setPrefWidth(400);
        JFXTreeTableColumn estado = new JFXTreeTableColumn<>("Estado");
        estado.setPrefWidth(300);
        ordenes.getColumns().setAll(nOrden, proveedor, fecha_pedido, estado);
        ordenes.setMinHeight(500);
        
        VBox contRoot2 = new VBox(contTop, ordenes);
        contRoot2.setSpacing(40);
        contRoot2.setCenterShape(true);
        contRoot2.setPadding(ESPACIADO2);
        
        return contRoot2;
    }
    
    
    
    
    
  
    
    public class ManejadorBuscarCleinte implements EventHandler{

        boolean b;
        
        public ManejadorBuscarCleinte(boolean b){
            this.b = b;
        }
        
        @Override
        public void handle(Event event) {
            
            int r = (int) (Math.random() * 1); 
            System.out.println(r);
            if(r==0){
                JFXButton btnRegistrar = new JFXButton("Registrar Nuevo");
                btnRegistrar.setOnAction((e) -> {
                    if(diag != null)diag.close();
                    PanelClienteRegistro pR = new PanelClienteRegistro(stage,root, b);
                    stage.getScene().setRoot(pR.getRoot());
                });
                diag = Metodos.dialogoMaterial(root, "No existe un cliente con ese numero", btnRegistrar);
            }
            else{
                Metodos.dialogoMaterial(root, "Busqueda", "Se encontro un cliente con ese numero", "Cerrar");
            }
        }
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
        return Class.class.getResource("/recursos/estilo-PanelOrdenes.css").toExternalForm();
    }
}

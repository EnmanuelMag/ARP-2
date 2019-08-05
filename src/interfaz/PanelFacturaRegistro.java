/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static constantes.Constantes.BOTON;
import static constantes.Constantes.BOTONA;
import static constantes.Constantes.ESPACIADO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
 * @author emman
 */
public class PanelFacturaRegistro extends PanelGenerico {
    
   
    private VBox contSp;
    private Pedido pedido;

    public PanelFacturaRegistro(Stage s, StackPane lastRoot,Pedido pedido) {
        super(s, lastRoot);
        this.pedido=pedido;
        super.border.setCenter(getCentro());
        setTop();
    }
    
    public void setTop(){
        Label titulo=new Label("Factura");
        titulo.getStyleClass().clear();
        titulo.getStyleClass().add("label-titulos-paneles");
        HBox cTitulo=new HBox(titulo);
        cTitulo.setAlignment(Pos.CENTER);
        ((BorderPane)super.getBorder()).setTop(cTitulo);
        
    }
    
    public VBox getCentro(){
        VBox centro=new VBox();
        centro.setSpacing(20);
        
        centro.getChildren().addAll(getDatosCliente(),getTablaFactura());
        return centro;
    }
    
    public HBox getDatosCliente(){
        
        GridPane gSucursal=new GridPane();
        gSucursal.setHgap(20);
        Label razonSocialL=new Label("FRIGO COBOS CRUZ");
        Label rucL=new Label("0999999999001");
        Label direccionL=new Label("<dirección>");
        Label telefonoL=new Label("<teléfono>");
        gSucursal.addColumn(0, razonSocialL,rucL,direccionL,telefonoL);
        
        GridPane gCliente=new GridPane();
        gCliente.setHgap(20);
        gCliente.add(new Label("Datos del cliente"),0,0, 2, 1);
        Label clienteL = new Label("Cliente ");
        Label clienteRUCL = new Label("RUC ");
        Label clienteTipoL = new Label("Tipo ");
        Label clienteDirL = new Label("Dirección");
        Label clienteTL = new Label("Teléfono");
        gCliente.addColumn(0, clienteL,clienteRUCL,clienteTipoL,clienteDirL,clienteTL);
        
        Label cliente = new Label("<Cliente> ");
        Label clienteRUC = new Label("<RUC> ");
        Label clienteTipo = new Label("<Tipo> ");
        Label clienteDir = new Label("<Dirección>");
        Label clienteT = new Label("<Teléfono>");
        gCliente.addColumn(1, cliente,clienteRUC,clienteTipo,clienteDir,clienteT);
        
        
        
        HBox cont=new HBox();
        cont.setSpacing(40);
        cont.setPadding(new Insets(20));
        cont.getChildren().addAll(gCliente,gSucursal);
        
        /*ImageView img= new ImageView(new Image("/recursos/iconos/lupa2.png"));
        img.setFitHeight(45);
        img.setFitWidth(45);
        HBox contImagen = new HBox(img);
        HBox contCedula = Metodos.crearPanel(cedula, contImagen);*/
        
        /*JFXDatePicker fecha = new JFXDatePicker();
        fecha.setValue(LocalDate.now());
        fecha.setPromptText("Fecha");
        */
        
        return cont;
    }
    
    
    public VBox getTablaFactura(){
        VBox contFactura=new VBox();
        contFactura.setSpacing(15);
        contFactura.setPadding(new Insets(0,20,20,20));
        TableView<FilaFactura> tFactura=new TableView<FilaFactura>();
        tFactura.setMaxHeight(400);
        TableColumn<FilaFactura,String> cDescripcion=new TableColumn<>("Descrición"); 
        cDescripcion.setPrefWidth(413);
        TableColumn<FilaFactura,String> cPedido=new TableColumn<>("Pedido");
        cPedido.setPrefWidth(150);
        TableColumn<FilaFactura,String> cVare=new TableColumn<>("Vare");
        cVare.setPrefWidth(150);
        TableColumn<FilaFactura,String> cVenta=new TableColumn<>("Venta");
        cVenta.setPrefWidth(150);
        TableColumn<FilaFactura,String> cPrecio=new TableColumn<>("Precio");
        cPrecio.setPrefWidth(150);
        tFactura.getColumns().addAll(cDescripcion,cPedido,cVare,cVenta,cPrecio);
        
        GridPane gTotales=new GridPane();
        gTotales.add(new Label("Subtotal"), 0, 0);
        gTotales.add(new Label("Transporte"), 0, 1);
        gTotales.add(new Label("Suma"), 0, 2);
        gTotales.add(new Label("Iva"), 0, 3);
        gTotales.add(new Label("Total"), 0, 4);
        
        gTotales.add(new Label("0.0"), 1, 0);
        gTotales.add(new Label("0.0"), 1, 1);
        gTotales.add(new Label("0.0"), 1, 2);
        gTotales.add(new Label("0.0"), 1, 3);
        gTotales.add(new Label("0.0"), 1, 4);
        
        contFactura.setAlignment(Pos.CENTER_RIGHT);
        contFactura.getChildren().addAll(tFactura,gTotales);
        return contFactura;
    }
    
    
    public StackPane getRoot(){
        return root;
    }
    
    public String getRutaCssFile(){
        return Class.class.getResource("/recursos/estilo-PanelFactura.css").toExternalForm();
    }
}

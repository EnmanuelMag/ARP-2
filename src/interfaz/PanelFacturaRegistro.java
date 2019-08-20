/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import static constantes.Constantes.BOTON;
import static constantes.Constantes.BOTONA;
import static constantes.Constantes.ESPACIADO;
import constantes.Metodos;
import java.time.LocalDate;
import java.util.Set;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Pedido;
import modelo.PedidoProducto;
import modelo.Producto;


/**
 *
 * @author emman
 */
public class PanelFacturaRegistro extends PanelGenerico {
    
    private HBox contMain;
    private VBox contDetalle;
    private boolean primero = true;
    private VBox contSp;
    private boolean b;
    private JFXDialog diag;
    
    private Pedido pedido;
    private Cliente cliente;
    
    JFXTextField subtotal;
    JFXTextField iva;
    JFXTextField cargo;
    JFXTextField total;
            
    public PanelFacturaRegistro(Stage s, StackPane lastRoot,Pedido pedido) {
        super(s, lastRoot);
        this.pedido=pedido;
        this.cliente=pedido.getCliente();
        super.border.setCenter(crearFormulario());
        s.getScene().getStylesheets().clear();
        s.getScene().getStylesheets().add(getRutaCssFile());
    }
    /*
    public VBox getCentro(){
        VBox centro=new VBox();
        centro.setSpacing(20);
        
        centro.getChildren().addAll((),getTablaFactura());
        return centro;
    }*/
    
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
    
    
    
    public VBox crearFormulario(){
       
        HBox c1 = new HBox(new Label("REGISTRO FACTURA"));
        c1.getStyleClass().add("label-titulos-paneles");
        c1.setAlignment(Pos.CENTER);
        border.setTop(c1);
        JFXTextField nOrden = new JFXTextField(String.valueOf(pedido.getPedidoId()));
        nOrden.setPromptText("Número de factura");
        nOrden.setLabelFloat(true);
        HBox contOrden = new HBox(nOrden);
        
        /*if(!b){
            ImageView img = new ImageView(new Image("/recursos/iconos/lupa2.png"));
                img.setFitHeight(45);
                img.setFitWidth(45);
                HBox contImagen = new HBox(img);
                contImagen.setAlignment(Pos.CENTER);
                contImagen.setOnMouseClicked(new PanelOrdenesRegistro.ManejadorBuscarFactura(true));
            contOrden.getChildren().add(contImagen);
        }*/
        
        JFXTextField nombre = new JFXTextField(cliente.getNombres()+" "+cliente.getApellidos());
        nombre.setPromptText("Nombre del cliente");
        nombre.getStyleClass().add("jfx-texto-largo");
        nombre.setLabelFloat(true);
        nombre.setEditable(false);
        
        JFXTextField ruc = new JFXTextField(cliente.getRuc());
        ruc.setPromptText("RUC ");
        ruc.setLabelFloat(true);
        ruc.setEditable(false);
                
        HBox contRuc = new HBox(ruc);
        /*if(b){
            
            ImageView img= new ImageView(new Image("/recursos/iconos/lupa2.png"));
            img.setFitHeight(45);
            img.setFitWidth(45);
            HBox contImagen = new HBox(img);
            contImagen.setOnMouseClicked(new PanelOrdenesRegistro.ManejadorBuscarCleinte(true));
            contRuc.getChildren().add(contImagen);
        }*/
        
        /*JFXTextField razonSocial = new JFXTextField();
        razonSocial.setPromptText("Razón Social");
        razonSocial.setLabelFloat(true);
        razonSocial.setEditable(false);*/
        
        JFXDatePicker fecha = new JFXDatePicker();
        fecha.setDefaultColor(Color.web("005683"));
        fecha.setValue(LocalDate.now());
        fecha.setPromptText("Fecha");
        fecha.setEditable(false);
        //fecha.setLabelFloat(true);
        
        VBox cont1 = new VBox(contOrden, fecha);
        cont1.setSpacing(30);
        cont1.setMaxWidth(400);
        cont1.setAlignment(Pos.TOP_LEFT);
                
        //nombre, razonSocial
        
        JFXTextField telf = new JFXTextField(cliente.getTelefono());
        telf.setPromptText("Teléfono");
        telf.setLabelFloat(true);
        telf.setEditable(false);
        
        VBox cont2 = new VBox(contRuc, telf);
        cont2.setSpacing(30);
        cont2.setMaxWidth(400);
        cont2.setAlignment(Pos.TOP_LEFT);
        
        VBox cont3 = new VBox(nombre);
        cont3.setSpacing(30);
        cont3.setMaxWidth(400);
        cont3.setAlignment(Pos.TOP_LEFT);
        
        contMain = new HBox(cont1, cont2, cont3);
        contMain.setSpacing(110);
        contMain.setAlignment(Pos.CENTER);
        //contMain.setMaxWidth(900);
        contMain.setPadding(new Insets(35,60,30,60));
        contMain.getStyleClass().add("contInfo");
        
        contDetalle = new VBox();
        contDetalle.setPadding(BOTON);
        //contDetalle.setMaxWidth(750);
        contDetalle.setMaxHeight(300);
        
        ScrollPane sP = new ScrollPane(contDetalle);
        sP.setFitToWidth(true);
        //sP.setMinWidth(900);
        sP.setPrefHeight(600);
        
        //contDetalle.getStyleClass().add("contInfo");
        
        /*JFXButton anadir = new JFXButton("Añadir Producto");
        //anadir.setPadding(BOTONA);
        anadir.setOnAction(new PanelOrdenesRegistro.ManejadorAnadir());
        */
        
        //contDetalle.getChildren().add(0, anadir);
        
        contSp = new VBox(sP);
        contSp.getStyleClass().add("contBackOnly");
        
        contDetalle.setAlignment(Pos.CENTER);
        contDetalle.setSpacing(10);
        VBox lineasFatura = new VBox(contSp);
        lineasFatura.setSpacing(5);
        setLabels();
        //contDetalle.getChildren().add(1,anadir);
        lineasFatura.setAlignment(Pos.CENTER);
        
        JFXButton registrar = new JFXButton("Registrar");
        VBox c = new VBox(registrar);
        c.setAlignment(Pos.CENTER);
        //lineasFatura.setMaxWidth(800);
        border.setBottom(c);
       
        subtotal = new JFXTextField();
        subtotal.setPromptText("Sub-Total");
        //subtotal.setPrefWidth(50);
        subtotal.setLabelFloat(true);
        
        
        iva = new JFXTextField();
        iva.setPromptText("IVA");
        iva.setLabelFloat(true);
        //iva.setPrefWidth(50);
        
        cargo = new JFXTextField();
        if(cliente.getTipoCliente().getTipo().equals("TRICICLO")) cargo.setText("0.5");
        cargo.setText("0");
        cargo.setPromptText("Cargo");
        //subtotal.setPrefWidth(50);
        cargo.setLabelFloat(true);
        cargo.setEditable(false);
        
        total = new JFXTextField();
        total.setPromptText("TOTAL");
        total.setLabelFloat(true);
        //total.setPrefWidth(50);
        
        VBox sumas = new VBox(subtotal, iva,cargo, total);
        sumas.setAlignment(Pos.CENTER);
        sumas.getStyleClass().add("texto-cant");
        //sumas.setPrefSize(150, 200);
        sumas.setSpacing(35);
        
        //sumas.getStyleClass().add("contBack");
        HBox contMedio = new HBox(lineasFatura, sumas);
        contMedio.setSpacing(40);
        contMedio.setAlignment(Pos.CENTER);
        VBox contRoot = new VBox(contMain, contMedio);
        contRoot.setAlignment(Pos.TOP_CENTER);
        contRoot.setMaxWidth(1250);
        contRoot.setSpacing(40);
        
        return contRoot;
    }
    
     public void setLabels(){
         GridPane contLabel = new GridPane();
         contLabel.getStyleClass().add("contBack");
         Separator s = new Separator();
         s.setOrientation(Orientation.VERTICAL);
         Separator s2 = new Separator();
         s2.setOrientation(Orientation.VERTICAL);
         Separator s3 = new Separator();
         s3.setOrientation(Orientation.VERTICAL);
         Separator s4 = new Separator();
         s4.setOrientation(Orientation.VERTICAL);

         Label cod = new Label("Nombre");
         cod.setMinWidth(250);
         Label pedido = new Label("Pedido");
         pedido.setMinWidth(100);

         Label vare = new Label("Vare");
         vare.setMinWidth(100);

         Label venta = new Label("Venta");
         venta.setMinWidth(100);

         Label costU = new Label("Costo Unitario");
         costU.setMinWidth(130);
         Label costT = new Label("Costo Total");
         costT.setMinWidth(180);

         contLabel.addRow(0, cod, pedido, vare, venta, costU, costT);
         contLabel.setHgap(15);

         contLabel.setPadding(BOTONA);
         contSp.getChildren().add(0, contLabel);
    }
     
   
     public void llenarFacturaProducto(){
         Set<PedidoProducto> setPedidosProductos=pedido.getPedidoProductos();
         
         for(PedidoProducto pp:setPedidosProductos){
            Producto p=pp.getProducto();
             
            GridPane cont = new GridPane();
            JFXTextField nombre = new JFXTextField(p.getNombre());
            nombre.setMinWidth(320);
            nombre.setEditable(false);
            nombre.setLabelFloat(true);
            
            JFXTextField cant = new JFXTextField(String.valueOf(pp.getCantidad()));
            cant.setMaxWidth(100);
            cant.setEditable(false);
            cant.setLabelFloat(true);
            
            
            
            JFXTextField venta = new JFXTextField(String.valueOf(p.getCosto()));
            venta.setMaxWidth(135);
            venta.setEditable(false);
            venta.setLabelFloat(true);
            
            
            JFXTextField costo = new JFXTextField(String.valueOf(p.getCosto()));
            costo.setMaxWidth(135);
            costo.setEditable(false);
            costo.setLabelFloat(true);
            
            JFXTextField costoCant = new JFXTextField();
            costoCant.setMaxWidth(130);
            costoCant.setEditable(false);
            costoCant.setLabelFloat(true);
            
            JFXTextField vare = new JFXTextField(String.valueOf(p.getCosto()));
            vare.setMaxWidth(135);
            vare.setEditable(true);
            vare.setLabelFloat(true);
            vare.textProperty().addListener((e,r,t)->{
                int vareInt;
                if(vare.equals("")){
                   venta.setText(cant.getText());
                }else{
                    venta.setText(String.valueOf(Double.valueOf(cant.getText())-Double.valueOf(vare.getText())));
                }  
                costoCant.setText(String.valueOf(Double.valueOf(venta.getText())*Double.valueOf(costo.getText())));
                calcular();
            });
            
            /*ImageView delete = new ImageView(new Image("/recursos/iconos/delete.png"));
            delete.setFitHeight(33);
            delete.setFitWidth(33);
            HBox contDelete = new HBox(delete);
            int ind =  contDetalle.getChildren().size();
            contDelete.setOnMouseClicked(e -> {
                contDetalle.getChildren().remove(cont);
            });*/
            
            Separator s = new Separator();  s.setOrientation(Orientation.VERTICAL);
            Separator s2 = new Separator(); s2.setOrientation(Orientation.VERTICAL);
            Separator s3 = new Separator();  s3.setOrientation(Orientation.VERTICAL);
            Separator s4 = new Separator();   s4.setOrientation(Orientation.VERTICAL);
            Separator s5 = new Separator();   s5.setOrientation(Orientation.VERTICAL);
                        
            cont.add(nombre, 0, 0);
            cont.add(s, 1, 0);
            cont.add(cant, 2, 0);
            cont.add(s2, 3, 0);
            cont.add(vare, 4, 0);
            cont.add(s4, 5, 0);
            cont.add(venta, 6, 0);
            cont.add(s5, 7, 0);
            cont.add(costo,8, 0);
            cont.add(s5, 9, 0);
            cont.add(costoCant,10, 0);
             
            cont.setHgap(15);
            cont.setPadding(BOTONA);
            contDetalle.getChildren().add(2,cont);
             
         }
         
     }
    public void calcular(){
        int subtotal=0;
        for (Node nodo : contDetalle.getChildren()) {

            GridPane g = (GridPane) nodo;
            Producto p = ((ComboBox<Producto>) g.getChildren().get(0)).getValue();
            subtotal+=Integer.valueOf(((JFXTextField)g.getChildren().get(10)).getText());
            
        }
        this.subtotal.setText(String.valueOf(subtotal));
        this.iva.setText(String.valueOf(subtotal*0.12));
        this.total.setText(String.valueOf(subtotal*1.12+Double.valueOf(cargo.getText())));
        
    }
     
    public StackPane getRoot(){
        return root;
    }
    
    public String getRutaCssFile(){
        return Class.class.getResource("/recursos/estilo-PanelFactura.css").toExternalForm();
    }
}

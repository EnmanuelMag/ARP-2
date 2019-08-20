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
import static constantes.Constantes.BOTON;
import static constantes.Constantes.BOTONA;
import static constantes.Constantes.ESPACIADO;
import constantes.Metodos;
import controlador.DB;
import controlador.NewHibernateUtil;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.*;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author emman
 */
public class PanelPedidoRegistro extends PanelGenerico{
    
   
    private HBox contMain;
    private VBox contDetalle;
    private boolean primero = true;
    private VBox contSp;
    private boolean b;
    private JFXDialog diag;
    JFXTextField nombreCliente;
    List<Producto> productos;
    List<Pedido> pedidos;
    JFXTextField cedula;
    JFXTextField tipo;
    JFXTextField descuento;
    JFXTextField subtotal;
    JFXTextField iva;
    JFXTextField total;
    ContextMenu ct;
    VBox cont2;
    private List<Cliente> clientes;
    JFXDatePicker fecha;
    //2019-08-19 00:18:33
    
    public PanelPedidoRegistro(Stage p,StackPane lastRoot, boolean b){
        super(p, lastRoot);
        this.b = b;
        productos=(List<Producto>)DB.getAll(Producto.class);
        pedidos=(List<Pedido>)DB.getAll(Pedido.class);
        clientes=(List<Cliente>)DB.getAll(Cliente.class);
        super.border.setCenter(crearFormulario());
        
        stage.getScene().getStylesheets().clear();
        stage.getScene().getStylesheets().add(getRutaCssFile());
    }
    
    
    public VBox crearFormulario(){
       
        HBox c1 = new HBox(new Label("REGISTRO PEDIDOS"));
        c1.getStyleClass().add("label-titulos-paneles");
        c1.setAlignment(Pos.CENTER);
        border.setTop(c1);
        JFXTextField nFac = new JFXTextField();
        nFac.setPromptText("Numero de Pedido");
        nFac.setLabelFloat(true);
        nFac.setEditable(false);
        HBox contFac = new HBox(nFac);
        
        
        ct = new ContextMenu();
        nombreCliente = new JFXTextField();
        nombreCliente.setPromptText("Nombre del Cliente");
        nombreCliente.getStyleClass().add("jfx-texto-largo");
        nombreCliente.setLabelFloat(true);
        
        TextFields.bindAutoCompletion(nombreCliente, clientes);
        
        cedula = new JFXTextField();
        cedula.setPromptText("Cédula");
        cedula.setLabelFloat(true);
                
        tipo = new JFXTextField();
        tipo.setPromptText("Tipo de Cliente");
        tipo.setLabelFloat(true);
        
        fecha = new JFXDatePicker();
        fecha.setDefaultColor(Color.web("005683"));
        fecha.setValue(LocalDate.now());
        fecha.setPromptText("Fecha");
        //fecha.setLabelFloat(true);
        
        VBox cont1 = new VBox(nFac, fecha);
        cont1.setSpacing(30);
        cont1.setMaxWidth(400);
        cont1.setAlignment(Pos.TOP_LEFT);
                
        //nombre, tipo
        
        descuento = new JFXTextField();
        descuento.setPromptText("Descuentos");
        descuento.setLabelFloat(true);
        
        cont2 = new VBox(nombreCliente, descuento);
        cont2.setSpacing(30);
        cont2.setMaxWidth(400);
        cont2.setAlignment(Pos.TOP_LEFT);
        
        VBox cont3 = new VBox(cedula, tipo);
        cont3.setSpacing(30);
        cont3.setMaxWidth(400);
        cont3.setAlignment(Pos.TOP_LEFT);
        
        contMain = new HBox(cont2, cont3, cont1);
        contMain.setSpacing(110);
        contMain.setAlignment(Pos.CENTER);
        //contMain.setMaxWidth(900);
        contMain.setPadding(new Insets(35,60,30,60));
        contMain.getStyleClass().add("contInfo");
        
        contDetalle = new VBox();
        contDetalle.setPadding(BOTON);
        contDetalle.setMaxHeight(300);
        
        ScrollPane sP = new ScrollPane(contDetalle);
        sP.setFitToWidth(true);
        sP.setPrefHeight(600);
        
        JFXButton anadir = new JFXButton("Añadir Producto");
        anadir.setOnAction(new ManejadorAnadir());
        
        contSp = new VBox(sP);
        contSp.setAlignment(Pos.CENTER);
        contSp.setSpacing(20);
        contSp.getStyleClass().add("contBackOnly");
        
        contDetalle.setAlignment(Pos.CENTER);
        contDetalle.setSpacing(10);
        VBox lineasFatura = new VBox(contSp);
        lineasFatura.setSpacing(5);
        setLabels();
        contSp.getChildren().add(1,anadir);
        lineasFatura.setAlignment(Pos.CENTER);
        
        JFXButton registrar = new JFXButton("Registrar");
        registrar.setOnMouseClicked(new MenejadorRegistroPedido());
        VBox c = new VBox(registrar);
        c.setAlignment(Pos.CENTER);
        border.setBottom(c);
        
        subtotal = new JFXTextField();
        subtotal.setPromptText("Sub-Total");
        subtotal.setEditable(false);
        subtotal.setLabelFloat(true);
        
        iva = new JFXTextField();
        iva.setPromptText("IVA");
        iva.setEditable(false);
        iva.setLabelFloat(true);
        
        total = new JFXTextField();
        total.setPromptText("TOTAL");
        total.setLabelFloat(true);
        total.setEditable(false);
        
        VBox sumas = new VBox(subtotal, iva, total);
        sumas.setAlignment(Pos.CENTER);
        sumas.getStyleClass().add("texto-cant");
        sumas.setSpacing(35);
        
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
                cod.setMinWidth(360);
                Label cantt = new Label("Cantidad");
                cantt.setMinWidth(100);

                Label costU = new Label("Costo Unitario");
                costU.setMinWidth(130);
                Label costT = new Label("Costo Total");
                costT.setMinWidth(180);

                contLabel.add(cod, 0, 0);
                contLabel.add(s, 1, 0);
                contLabel.add(cantt, 2, 0);
                contLabel.add(s2, 3, 0);
                contLabel.add(costU, 4, 0);
                contLabel.add(s4, 5, 0);
                contLabel.add(costT, 6, 0);
                contLabel.setHgap(15);         
                
                //contLabel.getChildren().addAll(cod, s, cantt, s2, nomb, s3, costU, s4, costT);
                //contLabel.setSpacing(15);
                contLabel.setPadding(BOTONA);
                contSp.getChildren().add(0,contLabel);
    }
    
    public class ManejadorAnadir implements EventHandler{

        @Override
        public void handle(Event event) {
            
            if(primero){
                
                primero = false;
            }
            
            JFXTextField costo = new JFXTextField();
            costo.setMaxWidth(135);
            costo.setEditable(false);
            costo.setLabelFloat(true);
            
            JFXTextField costoCant = new JFXTextField();
            costoCant.setMaxWidth(130);
            costoCant.setEditable(false);
            costoCant.setLabelFloat(true);
            
            GridPane cont = new GridPane();
            JFXComboBox<Producto> productoCombo = new JFXComboBox<>();
            productoCombo.setMinWidth(300);
            productoCombo.setItems(FXCollections.observableArrayList(productos));
            productoCombo.valueProperty().addListener((objeto,anterior,nuevo)->{
                if(nuevo!=null){
                    costo.setText(nuevo.getCosto().toString());
                }
            });
            
            JFXTextField cant = new JFXTextField();
            cant.setMaxWidth(100);
            cant.setEditable(true);
            cant.setLabelFloat(true);
            cant.textProperty().addListener((e,r,t)->{
                if(t!=null&& !t.equals("") && productoCombo.getValue()!=null){
                    
                    Double resultado=Double.valueOf(cant.getText())*Double.valueOf(costo.getText());
                    costoCant.setText(String.valueOf(resultado));
                    
                }else{
                     costoCant.setText("0");
                }
               calcular();
            });
            
            /*ImageView img= new ImageView(new Image("/recursos/iconos/lupa2.png"));
            img.setFitHeight(40);
            img.setFitWidth(40);
            HBox contImagen = new HBox(img);
            HBox contNombre = Metodos.crearPanel(productoCombo, contImagen);
            contNombre.setMinWidth(360);*/
            
            
            ImageView delete = new ImageView(new Image("/recursos/iconos/delete.png"));
            delete.setFitHeight(33);
            delete.setFitWidth(33);
            HBox contDelete = new HBox(delete);
            int ind =  contDetalle.getChildren().size();
            contDelete.setOnMouseClicked(e -> {
                contDetalle.getChildren().remove(cont);
                calcular();
            });
            
            Separator s = new Separator();
            s.setOrientation(Orientation.VERTICAL);
            Separator s2 = new Separator();
            s2.setOrientation(Orientation.VERTICAL);
            Separator s3 = new Separator();
            s3.setOrientation(Orientation.VERTICAL);
            Separator s4 = new Separator();
            s4.setOrientation(Orientation.VERTICAL);
            Separator s5 = new Separator();
            s5.setOrientation(Orientation.VERTICAL);
                        
            cont.add(productoCombo, 0, 0);
            cont.add(s, 1, 0);
            cont.add(cant, 2, 0);
            cont.add(s2, 3, 0);
            cont.add(costo, 4, 0);
            cont.add(s4, 5, 0);
            cont.add(costoCant, 6, 0);
            cont.add(s5, 7, 0);
            cont.add(contDelete, 8, 0);
              
            cont.setHgap(15);
            cont.setPadding(BOTONA);
            contDetalle.getChildren().add(cont);
        }
    }
    public boolean formularioLleno(){
        
        if(nombreCliente.getText()==null || contDetalle.getChildren().isEmpty()){
            return false;
        }
        return true;
            
    }
    
    public void calcular(){
        int subtotal=0;
        for (Node nodo : contDetalle.getChildren()) {

            GridPane g = (GridPane) nodo;
            Producto p = ((ComboBox<Producto>) g.getChildren().get(0)).getValue();
            subtotal+=Double.valueOf(((JFXTextField)g.getChildren().get(6)).getText());
            
        }
        this.subtotal.setText(String.valueOf(subtotal));
        this.iva.setText(String.valueOf(Double.valueOf(subtotal*0.12)));
        this.total.setText(String.valueOf(subtotal*1.12));
        
    }
    
    public class MenejadorRegistroPedido implements EventHandler<MouseEvent>{

        
        @Override
        public void handle(MouseEvent event) {
           if( formularioLleno()){
               /*SessionFactory factory = NewHibernateUtil.getSessionFactory();
            Session sesion = factory.openSession();
            Transaction tx = sesion.beginTransaction();*/
               
                Set<PedidoProducto> setPedidos=new HashSet<>();
                
                Pedido pedido=new Pedido();
                Cliente c = null;
                for(int i = 0; i<clientes.size();i++){
                    if(clientes.get(i).getNombres().equals(nombreCliente.getText())){
                        c = clientes.get(i);
                        i = clientes.size()+1;
                    }
                }
                
                pedido.setCliente(c);
                pedido.setEstado(false);
                pedido.setFecha(Date.from(Instant.now()));
                //pedido.setPedidoProductos(setPedidos);
                
                
                for(Node nodo:contDetalle.getChildren()){
                    
                    GridPane g=(GridPane)nodo;
                    Producto p=((ComboBox<Producto>)g.getChildren().get(0)).getValue();
                    if(p!=null){
                        int cantidad=Integer.valueOf(((JFXTextField)g.getChildren().get(2)).getText());
                        PedidoProducto pedidoP=new PedidoProducto();
                        pedidoP.setCantidad(cantidad);
                        pedidoP.setProducto(p);
                        pedidoP.setPedido(pedido);
                        setPedidos.add(pedidoP);
                    }
                }
                //pedido.setPedidoProductos(setPedidos);
                DB.agregar(pedido);
                System.out.println("pedido agregado");
                
                for(PedidoProducto pp:setPedidos){
                    DB.agregar(pp);
                    System.out.println("pedido producto registrado");
                }
                Metodos.dialogoMaterial(root, "Datos", "Se ha guardado correctamente su pedido", "Cerrar");
            }else{
                Metodos.dialogoMaterial(root, "Datos", "Por favor llene el formulario de la cabecera", "Cerrar");
            }   
            
           
        }
    }
    
    public class ManejadorBuscarFactura implements EventHandler{

        boolean b;
        
        public ManejadorBuscarFactura(boolean b){
            this.b = b;
        }
        
        @Override
        public void handle(Event event) {
            
            int r = (int) (Math.random() * 1); 
            System.out.println(r);
            if(r==0){
                JFXButton btnRegistrar = new JFXButton("Registrar Nueva");
                btnRegistrar.setOnAction((e) -> {
                    if(diag != null)diag.close();
                    PanelPedidoRegistro pR = new PanelPedidoRegistro(stage,root, b);
                    stage.getScene().setRoot(pR.getRoot());
                });
                diag = Metodos.dialogoMaterial(root, "No existe una factura con ese numero", btnRegistrar);
            }
            else{
                Metodos.dialogoMaterial(root, "Busqueda", "Se encontro factuea con ese numero", "Cerrar");
            }
        }
        }
    
    /*public List<MenuItem> buscarCleinte(String nomb){
        
        
        List<Cliente> clientesAll = (List<Cliente>) DB.getAll(Cliente.class);
        ListIterator<Cliente> iC = clientesAll.listIterator();
        List<MenuItem> clienteFiltro = new ArrayList<>();
        while (iC.hasNext()) {
            Cliente c = iC.next();
            if (c.getNombres().toLowerCase().startsWith(nombre.getText().toLowerCase())) {
                MenuItem mi = new MenuItem(c.getNombres());
                mi.setOnAction(e -> {
                    nombre.setText(c.getNombres());
                    cedula.setText(c.getRuc());
                    tipo.setText(c.getTipoCliente().getTipo());
                    descuento.setText(c.getTipoCliente().getDescuento() + "");
                    ct.hide();
                });
                clienteFiltro.add(mi);
            }
        }
        return clienteFiltro;
        }*/
    
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import constantes.Metodos;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.Factura;


/**
 *
 * @author Josue
 */
public class PanelReporteVentas extends PanelGenerico {
    
    private LinkedList<Factura> facturas;
    private GridPane gridOpciones;
    SimpleDateFormat fDiaInicio=new SimpleDateFormat("yyyy-MM-dd 00:00:01");
    SimpleDateFormat fDiaFin=new SimpleDateFormat("yyyy-MM-dd 23:59:59");
       

    public PanelReporteVentas(Stage s, StackPane lastRoot) {
        
        super(s, lastRoot);
        super.stage.getScene().getStylesheets().clear();
        super.stage.getScene().getStylesheets().add(getRutaCssFile());
        setTop();
        setCenter();
    }
    
    public void setTop(){
        Label titulo=new Label("Reporte Ventas");
        titulo.getStyleClass().clear();
        titulo.getStyleClass().add("label-titulos-paneles");
        HBox cTitulo=new HBox(titulo);
        cTitulo.setAlignment(Pos.CENTER);
        ((BorderPane)super.getBorder()).setTop(cTitulo);
        
    }
    
    public void setCenter(){
        VBox cGridOpciones=new VBox();
        cGridOpciones.setAlignment(Pos.CENTER);
        //cGridOpciones.setPrefWidth(stage.getMaxWidth());
        gridOpciones=new GridPane();
        gridOpciones.setHgap(26);
        gridOpciones.setVgap(26);
        
        JFXButton opcion1=new JFXButton("Ventas del día");
        opcion1.getStyleClass().add("jfx-button-opciones");
        opcion1.setOnAction((r)->{
            LocalDate ahora=LocalDate.now();
            LocalDateTime inicioDia=ahora.atStartOfDay();
            LocalDateTime finDia=inicioDia.plusDays(1);
            SubPanelReporteVentas sub=new SubPanelReporteVentas(stage,root,inicioDia,finDia);
            sub.setSubtitulo("DÍA");
            stage.getScene().setRoot(sub.getRoot());});
        
        JFXButton opcion2=new JFXButton("Ventas del mes");
        opcion2.getStyleClass().add("jfx-button-opciones");
        opcion2.setOnAction((r)->{
            LocalDate ahora=LocalDate.now();
            LocalDateTime inicioMes=ahora.atStartOfDay().minusDays(ahora.getDayOfMonth()-1);
            LocalDateTime finMes=inicioMes.plusMonths(1);
            SubPanelReporteVentas sub=new SubPanelReporteVentas(stage,root,inicioMes,finMes);
            sub.setSubtitulo("MES");
            stage.getScene().setRoot(sub.getRoot());});
        
        JFXButton opcion3=new JFXButton("Ventas del año");
        opcion3.getStyleClass().add("jfx-button-opciones");
        opcion3.setOnAction((r)->{
            LocalDate ahora=LocalDate.now();
            LocalDateTime inicioAño=ahora.atStartOfDay().minusDays(ahora.getDayOfYear()-1);
            LocalDateTime finAño=inicioAño.plusYears(1);
            SubPanelReporteVentas sub=new SubPanelReporteVentas(stage,root,inicioAño,finAño);
            sub.setSubtitulo("AÑO");
            stage.getScene().setRoot(sub.getRoot());});
        
        JFXButton opcion4=new JFXButton("Ventas por fecha");
        opcion4.getStyleClass().add("jfx-button-opciones");
        opcion4.setOnAction((r)->{
            
            GridPane g=new GridPane();
            
            JFXDatePicker dPickerDesde=new JFXDatePicker();
            dPickerDesde.setDefaultColor(Color.web("ff0a00"));
            dPickerDesde.setPromptText("");
            JFXDatePicker dPickerHasta=new JFXDatePicker();
            dPickerHasta.getStyleClass().add("jfx-date-picker-popup");
            dPickerHasta.setDefaultColor(Color.web("ff0a00"));
            dPickerHasta.setPromptText("");
            JFXButton bConsulta=new JFXButton("Consultar");
            //gridOpciones.add(bConsulta,5,4);
            g.addColumn(0,new Label("Desde"),dPickerDesde);
            g.addColumn(1,new Label("Hasta"),dPickerHasta);
            //g.addColumn(2,bConsulta);
            
            bConsulta.setOnAction((w)->{
                LocalDateTime inicio=dPickerDesde.getValue().atStartOfDay();
                LocalDateTime fin=inicio.plusDays(1);
                SubPanelReporteVentas sub=new SubPanelReporteVentas(stage,root,inicio,fin);
                sub.setSubtitulo("FECHA ESCOGIDA");
                stage.getScene().setRoot(sub.getRoot());
            });
            
            Metodos.dialogoMaterial(root, "Intervalo de fecha", g, bConsulta);
            
            });
        
        
        Label subtitulo=new Label("Escoja una opción :");
        cGridOpciones.getChildren().addAll(subtitulo, opcion1,opcion2,opcion3,opcion4);
        cGridOpciones.setSpacing(35);
        
        cGridOpciones.setAlignment(Pos.CENTER);
        ((BorderPane)super.getBorder()).setCenter(new StackPane(cGridOpciones));
    }
    
    public String getRutaCssFile(){
        return PanelReporteVentas.class.getResource("/recursos/reporte.css").toExternalForm();
    }
    
}

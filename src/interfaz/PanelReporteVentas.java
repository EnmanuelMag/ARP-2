/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.stage.Stage;
import sistema.Factura;

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
        setTop();
        setCenter();
    }
    
    public void setTop(){
        Label titulo=new Label("Reporte Ventas");
        HBox cTitulo=new HBox(titulo);
        cTitulo.setAlignment(Pos.CENTER);
        ((BorderPane)super.getBorder()).setTop(cTitulo);
        
    }
    
    public void setCenter(){
        VBox cGridOpciones=new VBox();
        cGridOpciones.setAlignment(Pos.CENTER);
        cGridOpciones.setPrefWidth(stage.getMaxWidth());
        gridOpciones=new GridPane();
        gridOpciones.setHgap(26);
        gridOpciones.setVgap(26);
        
        JFXButton opcion1=new JFXButton("Ventas del día");
        opcion1.setOnAction((r)->{
            LocalDate ahora=LocalDate.now();
            LocalDateTime inicioDia=ahora.atStartOfDay();
            LocalDateTime finDia=inicioDia.plusDays(1);
            SubPanelReporteVentas sub=new SubPanelReporteVentas(stage,root,inicioDia,finDia);
            sub.setSubtitulo("DÍA");
            stage.getScene().setRoot(sub.getRoot());});
        
        JFXButton opcion2=new JFXButton("Ventas del mes");
        opcion2.setOnAction((r)->{
            LocalDate ahora=LocalDate.now();
            LocalDateTime inicioMes=ahora.atStartOfDay().minusDays(ahora.getDayOfMonth()-1);
            LocalDateTime finMes=inicioMes.plusMonths(1);
            SubPanelReporteVentas sub=new SubPanelReporteVentas(stage,root,inicioMes,finMes);
            sub.setSubtitulo("MES");
            stage.getScene().setRoot(sub.getRoot());});
        
        JFXButton opcion3=new JFXButton("Ventas del año");
        opcion3.setOnAction((r)->{
            LocalDate ahora=LocalDate.now();
            LocalDateTime inicioAño=ahora.atStartOfDay().minusDays(ahora.getDayOfYear()-1);
            LocalDateTime finAño=inicioAño.plusYears(1);
            SubPanelReporteVentas sub=new SubPanelReporteVentas(stage,root,inicioAño,finAño);
            sub.setSubtitulo("AÑO");
            stage.getScene().setRoot(sub.getRoot());});
        
        JFXButton opcion4=new JFXButton("Ventas por fecha");
        opcion4.setOnAction((r)->{
            JFXDatePicker dPickerDesde=new JFXDatePicker();
            dPickerDesde.setPromptText("Desde");
            JFXDatePicker dPickerHasta=new JFXDatePicker();
            dPickerHasta.setPromptText("Hasta");
            gridOpciones.add(new Label("Desde"),1,4);
            gridOpciones.add(dPickerDesde,2,4);
            gridOpciones.add(new Label("Hasta"),3,4);
            gridOpciones.add(dPickerHasta,4,4);
            JFXButton bConsulta=new JFXButton("Consultar");
            gridOpciones.add(bConsulta,5,4);
            
           
            bConsulta.setOnAction((w)->{
                LocalDateTime inicioAño=dPickerDesde.getValue().atStartOfDay();
                LocalDateTime finAño=inicioAño.plusDays(1);
                SubPanelReporteVentas sub=new SubPanelReporteVentas(stage,root,inicioAño,finAño);
                sub.setSubtitulo("FECHA ESCOGIDA");
                stage.getScene().setRoot(sub.getRoot());
            });
            });
        
        
        Label subtitulo=new Label("Escoja una opción :");
        gridOpciones.addColumn(0,subtitulo, opcion1,opcion2,opcion3,opcion4);
        
        cGridOpciones.getChildren().add(gridOpciones);
        ((BorderPane)super.getBorder()).setCenter(cGridOpciones);
    }
    
    
    
}

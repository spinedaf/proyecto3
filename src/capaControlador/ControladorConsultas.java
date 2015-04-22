package capaControlador;

import javafx.fxml.Initializable;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import capaLogica.*;

public class ControladorConsultas implements Initializable {
	
	@FXML private TableView<Consulta> tablaConsultas;
	@FXML private TableView<Cita> tablaCitas;
	@FXML private ComboBox<String> cbExpediente;
	@FXML private ComboBox<String> cbCita;
	@FXML private ComboBox<String> cbDoctor;
	@FXML private ComboBox<String> cbDoctor2;
	
	@FXML private Label lStatusConsultas;
	@FXML private DatePicker dpFechaConsulta;
	@FXML private DatePicker dpFechaConsulta2;
	@FXML private TextField tfDescripcion;
	@FXML private TextField tfDescripcion2;
	
	@FXML private TableView<Receta> tablaRecetas;
	@FXML private Label labelDiasPrescritos;
	@FXML private Label labelFechaInicio;
	@FXML private Label labelFechaFinal;
	
	@FXML private Label labelExpediente; 
	@FXML private Label labelPaciente;
	@FXML private Label labelDoctor;
	@FXML private Label labelFecha;
	@FXML private Label labelDescripcion;
	
	
	/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	//Iniciar comboBoxes de Pacientes
    	ObservableList<String> listaExpedientes = cbExpediente.getItems();
    	List<String> listaCombo = new ArrayList<String>();
    	(new MultiExpediente()).buscarTodos()
    						 .forEach(expediente -> listaCombo.add(expediente.getCSM() ) );
    	
    	listaExpedientes.addAll(listaCombo);
    	
    	cbExpediente.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> actualizarElementos());
    	
    	tablaRecetas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> actualizarRecetas(newValue));
    	
    	tablaConsultas.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> actualizarConsultasRecetas(newValue));   			
    	
    }    
    
    public void actualizarConsultasRecetas(Consulta consulta)
    {
    	//Tabla de Recetas
    	ObservableList<Receta> recetas = tablaRecetas.getItems();
    	List<Receta> listaReceta = (new MultiReceta()).buscarPorConsulta(consulta.getCodigoConsulta());
    	
    	recetas.setAll(listaReceta);
    	
    	labelExpediente.setText("CSM-" + consulta.getCodigoConsulta());
    	Expediente exp = (new MultiExpediente()).buscar(consulta.getExpedienteAsociado());
    	labelPaciente.setText(exp.getPaciente().getNombre());
    	labelDoctor.setText(consulta.getDoctor().getNombre());
    	labelFecha.setText(consulta.getFecha().toString());
    	labelDescripcion.setText(consulta.getDescripcion());
    }
    
    public void actualizarRecetas(Receta receta)
    {
    	labelDiasPrescritos.setText(receta.getNumeroDias() + "");
    	labelFechaInicio.setText(receta.getFechaDeInicio().toString());
    	labelFechaFinal.setText(receta.getFechaFinalizacion().toString());
    }
    
    public void actualizarElementos( )
    {
    	int numeroExpediente = convertirNumeroConsulta(cbExpediente.getValue());
    	
    	//Inicializar la tabla de expedientes
    	ObservableList<Consulta> consultas = tablaConsultas.getItems();
    	List<Consulta> lista = (new MultiConsulta()).buscarTodos(numeroExpediente);
    	
    	consultas.setAll(lista);
    	
    	//Inicializar la tabla de citas
    	ObservableList<Cita> citas = tablaCitas.getItems();
    	List<Cita> lista2 = (new MultiCita()).buscarTodos(numeroExpediente);
    	
    	citas.setAll(lista2);
    	
    	//Iniciar los elementos de creacion de consulta
    	
    	//Combobox Citas
    	ObservableList<String> listaDoctores = cbDoctor.getItems();
    	List<String> listaComboDoctores = new ArrayList<String>();
    	(new MultiDoctor()).buscarTodos()
		 .forEach(doctor -> listaComboDoctores.add(doctor.getCedula() + "-" + doctor.getNombre()) );

    	listaDoctores.addAll(listaComboDoctores);
    	
    	ObservableList<String> listaDoctores2 = cbDoctor2.getItems();
    	List<String> listaComboDoctores2 = new ArrayList<String>();
    	(new MultiDoctor()).buscarTodos()
		 .forEach(doctor -> listaComboDoctores2.add(doctor.getCedula() + "-" + doctor.getNombre()) );

    	listaDoctores.addAll(listaComboDoctores);
    	listaDoctores2.addAll(listaComboDoctores2);
    	
    	//Combobox doctores
    	ObservableList<String> listaCitas = cbCita.getItems();
    	List<String> listaComboCitas = new ArrayList<String>();
    	(new MultiCita()).buscarTodos(numeroExpediente)
		 .forEach(cita -> listaComboCitas.add(cita.getCodigoCita() + "") );

    	listaCitas.addAll(listaComboCitas);
    	
    	
    	
    }
    
    public int convertirNumeroConsulta(String csm)
    {
    	String tokens[] = csm.split("-");
    	return Integer.parseInt(tokens[1]);
    }
    
    @FXML
    protected void agregarConsulta(ActionEvent event) {
        
        if(cbCita.getValue() == null || cbDoctor.getValue() == null )
        {
        	lStatusConsultas.setText("No se puede agregar un consulta sin cita especificada y/o doctor");
        }else{
        	 String cedulaDoctor = cbDoctor.getValue().split("-")[0];
        	 LocalDate localDate = dpFechaConsulta.getValue();
        	 java.util.Date fecha1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        	 Date fecha = new Date(fecha1.getTime());
             String descripcion = tfDescripcion.getText();
             int expAsociado = convertirNumeroConsulta(cbExpediente.getValue());
             
             ObservableList<Consulta> data = tablaConsultas.getItems();
             Consulta con = (new MultiConsulta()).crear(cedulaDoctor, fecha, descripcion, expAsociado);
             
             data.add(con);
        }
    }
    
    @FXML
    protected void agregarCita(ActionEvent event) {
    	
    	
    	try {
    		
        	int expAsociado = convertirNumeroConsulta(cbExpediente.getValue());
        	LocalDate fechaPropuesta = dpFechaConsulta2.getValue();
        	boolean fechasIguales = false;    	
        	List<Cita> lista = new MultiCita().buscarTodos(expAsociado);
        	
        	Instant instant = Instant.from(fechaPropuesta.atStartOfDay(ZoneId.systemDefault()));
        	java.util.Date fechaPropuestaDate = java.util.Date.from(instant);
        	
        	
        	
        	Calendar cal1 = Calendar.getInstance();
        	cal1.setTime(fechaPropuestaDate);  	 
        	int wk1=cal1.get(Calendar.WEEK_OF_MONTH);
        	
       	 	
        	for (Cita item : lista) {
        		
           	 	Calendar cal2 = Calendar.getInstance();
           	 	cal2.setTime(item.getDiaCita());
           	 	int wk2=cal2.get(Calendar.WEEK_OF_MONTH);
       
        		if (wk1 == wk2){
        			fechasIguales = true;
        			break;
        			
        		}
        		
        	}

        	
        	
            if(cbDoctor2.getValue() == null  || fechasIguales == true)
            {
            	lStatusConsultas.setText("No se pueden agregar citas sin Doctor o en la misma semana");
            }else{
            	 String cedulaDoctor = cbDoctor2.getValue().split("-")[0];
                 String descripcion = tfDescripcion2.getText();
                 Date fecha = new java.sql.Date(fechaPropuestaDate.getTime());
                 String estado = "Activa";             
                 ObservableList<Cita> data = tablaCitas.getItems();
                 Cita con = (new MultiCita()).crear(expAsociado,cedulaDoctor, fecha, descripcion, estado );       
                 data.add(con);
                 lStatusConsultas.setText("Cita agregada con �xito.");
            }
    		
    	} catch (UnsupportedOperationException ex) {
    	    throw ex;
    	}
    	

    }

}

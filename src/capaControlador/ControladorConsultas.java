package capaControlador;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
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
	
	@FXML private TableView tablaConsultas;
	@FXML private TableView tablaCitas;
	@FXML private ComboBox<String> cbExpediente;
	@FXML private ComboBox<String> cbCita;
	@FXML private ComboBox<String> cbDoctor;
	@FXML private Label lStatusConsultas;
	@FXML private DatePicker dpFechaConsulta;
	@FXML private TextField tfDescripcion;
	
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
    
    }    
    
    public void actualizarElementos()
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
    	//ObservableList<String> listaCitas = cbCita.getItems();
    	//List<String> listaComboCitas = new ArrayList<String>();
    	//(new MultiCita()).buscarTodos(numeroExpediente)
		// .forEach(cita -> listaComboCitas.add(cita.getCodigoCita() + "") );

    	//listaCitas.addAll(listaComboCitas);
    }
    
    public int convertirNumeroConsulta(String csm)
    {
    	String tokens[] = csm.split("-");
    	return Integer.parseInt(tokens[1]);
    }
    
    @FXML
    protected void agregarConsulta(ActionEvent event) {
        
    }

}

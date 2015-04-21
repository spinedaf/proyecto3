package capaControlador;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import capaLogica.Doctor;
import capaLogica.Expediente;
import capaLogica.MultiDoctor;
import capaLogica.MultiExpediente;
import capaLogica.Paciente;
import capaLogica.MultiPaciente;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author pinedas
 */
public class ControladorExpedientes implements Initializable{

	@FXML private TableView<Expediente> tTablaExpediente;
    @FXML private Label lCedulaPaciente;
    @FXML private Label lNombrePaciente;
    @FXML private Label lDireccionPaciente;
    @FXML private Label lTelefonoPaciente;
    @FXML private Label lFechaNacPaciente;
    @FXML private Label lEdadPaciente;
    @FXML private ComboBox<String> cbCedulaPaciente;
    @FXML private DatePicker calFechaApertura;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	//Inicializar la tabla de expedientes
    	ObservableList<Expediente> data = tTablaExpediente.getItems();
    	List<Expediente> lista = (new MultiExpediente()).buscarTodos();
    	
    	data.setAll(lista);
    	
    	mostrarDetallesPaciente(null);
    	tTablaExpediente.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesPaciente(
                		(new MultiPaciente()).buscar(newValue.getCedulaPaciente())
                		));
    	
    	//Iniciar comboBox de Pacientes
    	ObservableList listaPacientes = cbCedulaPaciente.getItems();
    	List<String> listaCombo = new ArrayList<String>();
    	(new MultiPaciente()).buscarTodos()
    						 .forEach(paciente -> listaCombo.add(paciente.getCedula() + " - " +
    														paciente.getNombre() ) );
    	
    	listaPacientes.addAll(listaCombo);
    }    
    
    @FXML
    protected void agregarExpediente(ActionEvent event) {
    	ObservableList<Expediente> data = tTablaExpediente.getItems();
    	
    	LocalDate fechaApertura = calFechaApertura.getValue();
    	Instant instant = Instant.from(fechaApertura.atStartOfDay(ZoneId.systemDefault()));
    	java.util.Date fechaAperturaDate = java.util.Date.from(instant);	
    	Date fecha = new java.sql.Date(fechaAperturaDate.getTime());
    	
    	
    	
    	
        Expediente exp = (new MultiExpediente()).crear( cbCedulaPaciente.getValue(), 
        		fecha );
        
        data.add(exp);
        
    }
    
    private void mostrarDetallesPaciente(Paciente paciente) {
        if (paciente != null) {
        	lCedulaPaciente.setText(paciente.getCedula());
        	lNombrePaciente.setText(paciente.getNombre());
        	lDireccionPaciente.setText(paciente.getDireccion());
        	lTelefonoPaciente.setText(paciente.getTelefono());
        	lFechaNacPaciente.setText(paciente.getFechaNacimiento().toString());
        	lEdadPaciente.setText(paciente.getEdad() + "");

        } else {
        	lCedulaPaciente.setText("");
        	lNombrePaciente.setText("");
        	lDireccionPaciente.setText("");
        	lTelefonoPaciente.setText("");
        	lFechaNacPaciente.setText("");
        	lEdadPaciente.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void borrarExpediente() {
        int selectedIndex = tTablaExpediente.getSelectionModel().getSelectedIndex();
        tTablaExpediente.getItems().remove(selectedIndex);
        
        Expediente exp = tTablaExpediente.getSelectionModel().getSelectedItem();
        new MultiExpediente().borrar(exp.getNumeroExpediente());
    }
}

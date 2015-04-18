package capaControlador;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import capaLogica.*;

public class ControladorConsultas implements Initializable {
	
	@FXML TableView tablaConsultas;
	@FXML TableView tablaCitas;
	@FXML private ComboBox<String> cbPaciente;
	
	
	/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	//Iniciar comboBox de Pacientes
    	ObservableList<String> listaExpedientes = cbPaciente.getItems();
    	List<String> listaCombo = new ArrayList<String>();
    	(new MultiExpediente()).buscarTodos()
    						 .forEach(expediente -> listaCombo.add(expediente.getCSM() ) );
    	
    	listaExpedientes.addAll(listaCombo);
    	
    	cbPaciente.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> actualizarTablaConsultas());
    }    
    
    public void actualizarTablaConsultas()
    {
    	//Inicializar la tabla de expedientes
    	ObservableList<Consulta> data = tablaConsultas.getItems();
    	List<Consulta> lista = (new MultiConsulta()).buscarTodos(
    			convertirNumeroConsulta(cbPaciente.getValue()));
    	
    	data.setAll(lista);
    }
    
    public int convertirNumeroConsulta(String csm)
    {
    	String tokens[] = csm.split("-");
    	return Integer.parseInt(tokens[1]);
    }
    
    @FXML
    protected void addDoctor(ActionEvent event) {
        
    }

}

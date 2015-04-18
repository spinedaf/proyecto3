/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaControlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import capaLogica.*;

/**
 * FXML Controller class
 *
 * @author pinedas
 */
public class ControladorDoctores implements Initializable {
	
	@FXML private TableView<Doctor> tablaDoctores;
    @FXML private TextField idDoctorField;
    @FXML private TextField nombreDoctorField;
    @FXML private TextField especialidadField;
    @FXML private TextField telefonoField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	ObservableList<Doctor> data = tablaDoctores.getItems();
    	List<Doctor> lista = (new MultiDoctor()).buscarTodos();
    	
    	data.setAll(lista);
    }    
    
    @FXML
    protected void addDoctor(ActionEvent event) {
        ObservableList<Doctor> data = tablaDoctores.getItems();
        Doctor doctor = (new MultiDoctor()).crear(idDoctorField.getText(), nombreDoctorField.getText(), 
        		especialidadField.getText(), telefonoField.getText());
        
        data.add(doctor);
        
        idDoctorField.setText("");
        nombreDoctorField.setText("");
        especialidadField.setText("");
        telefonoField.setText("");   
    }
    
}

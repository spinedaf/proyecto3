package capaTest;

import static org.junit.Assert.*;
import capaLogica.MultiPaciente;
import capaLogica.Paciente;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;

public class MultiPacienteTest {

	@Test
	public void testCrear() {
		System.out.println("crear");
        String cedula = "205870126";
        String nombre = "Eduardo Pineda";
        String direccion = "San Pablo";
        String telefono = "88986028";
        
        Calendar myCal = Calendar.getInstance();
        myCal.set(Calendar.YEAR, 1983);
        myCal.set(Calendar.MONTH, 9);
        myCal.set(Calendar.DAY_OF_MONTH, 3);
        
        Date fechaNacimiento = new Date(myCal.getTime().getTime());
        int edad = 31
                ;
        MultiPaciente instance = new MultiPaciente();
        Paciente expResult = new Paciente(cedula, nombre, direccion, telefono, fechaNacimiento, edad);
        Paciente result = instance.crear(cedula, nombre, direccion, telefono, fechaNacimiento, edad);
        assertEquals(expResult.getCedula(), result.getCedula());
	}

	@Test
	public void testBuscarTodos() {
		
	}

	@Test
	public void testBuscar() {
		
	}

	@Test
	public void testBorrar() {
		
	}

}

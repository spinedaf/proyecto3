package capaTest;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import capaLogica.MultiConsulta;
import capaLogica.MultiDoctor;
import capaLogica.Doctor;

public class MultiDoctorTest {

	@Test
	public void testCrear() {
		
		String pcedulaDoctor = "666";
		String pnombre = "Doctor House";
		String pespecialidad = "Fck-Off";
		String pexpedienteAsociado = "House123";
		
		MultiDoctor test = new MultiDoctor();
		Doctor result = test.crear(pcedulaDoctor,pnombre,pespecialidad,pexpedienteAsociado);	
		Doctor exp = new Doctor(pcedulaDoctor,pnombre,pespecialidad,pexpedienteAsociado);

		assertEquals(exp.getCedula(),result.getCedula());
		
		//fail("Not yet implemented");
		
		
	}

}

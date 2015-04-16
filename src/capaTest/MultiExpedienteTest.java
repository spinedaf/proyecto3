package capaTest;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import capaLogica.MultiExamen;
import capaLogica.MultiExpediente;

public class MultiExpedienteTest {

	@Test
	public void testMultiExpediente() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCrear() {
		String cedulaPaciente = "123";
		Date fechaApertura = new Date(1220227200);

		
		MultiExpediente test = new MultiExpediente();
		Boolean result = test.crear(cedulaPaciente,fechaApertura);	

		assertEquals(true,result);
		

		
		//fail("Not yet implemented");
		
	}

	@Test
	public void testBuscarTodos() {
		//fail("Not yet implemented");
	}

	@Test
	public void testBuscar() {
		//fail("Not yet implemented");
	}

	@Test
	public void testBorrar() {
		//fail("Not yet implemented");
	}

}

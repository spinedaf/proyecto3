package capaTest;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import capaLogica.MultiExamen;

public class MultiExamenTest {

	@Test
	public void testMultiExamen() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCrear() {
		
		String pnombre = "123";
		Date pfechaSolicitud = new Date(1220227200);
		Date pfechaRealizacion = new Date(1220227200);
		String pindicaciones = "Algo";
		int pconsultaAsociada = 1;
		
		MultiExamen test = new MultiExamen();
		Boolean result = test.crear(pnombre,pfechaSolicitud,pfechaRealizacion,
				pindicaciones,pconsultaAsociada);	

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

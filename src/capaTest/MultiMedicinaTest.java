package capaTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;

import capaLogica.MultiMedicina;
import capaLogica.Medicina;

public class MultiMedicinaTest {

	@Test
	public void testCrear() {
		System.out.println("crear");
		String pcodigo = "5555";
		String pnombre = "Silimarina";
		String pprecauciones = "No tome en exceso";
                
        MultiMedicina instance = new MultiMedicina();
        Medicina expResult = new Medicina(pcodigo, pnombre, pprecauciones);
        Medicina result = instance.crear(pcodigo,pnombre,pprecauciones);
        assertEquals(expResult.getCodigo(), result.getCodigo());
        assertEquals(expResult.getNombre(), result.getNombre());
        assertEquals(expResult.getPrecauciones(), result.getPrecauciones());
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

package capaTest;

import static org.junit.Assert.*;


import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;

import capaLogica.MultiResultadoExamen;
import capaLogica.ResultadoExamen;

public class MultiResultadoExamenTest {

	@Test
	public void testCrear() {
		System.out.println("crear");
        String codigoResultado = "1234";
        String nombreLugar = "Granadilla";
        String tipoLugar = "Feo";
        String descripcionLugar = "Muchas presas";
        String examenAsociado = "Sangre";
        
        MultiResultadoExamen instance = new MultiResultadoExamen();
        ResultadoExamen expResult = new ResultadoExamen(codigoResultado, nombreLugar, tipoLugar, descripcionLugar, examenAsociado);
        ResultadoExamen result = instance.crear(codigoResultado, nombreLugar, tipoLugar, descripcionLugar, examenAsociado);
        assertEquals(expResult.getDescripcionLugar(), result.getDescripcionLugar());
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

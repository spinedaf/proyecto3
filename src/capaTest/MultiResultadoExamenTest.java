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
        assertEquals(expResult.getCodigoResultado(), result.getCodigoResultado());
        assertEquals(expResult.getExamenAsociado(), result.getExamenAsociado());
        assertEquals(expResult.getNombreLugar(), result.getNombreLugar());
        assertEquals(expResult.getTipoLugar(), result.getTipoLugar());
	}

	@Test
	public void testBuscarTodos() {
		
	}

	@Test
	public void testBuscar() {
		System.out.println("crear");
        String codigoResultado = "12345";
        String nombreLugar = "Granadilla";
        String tipoLugar = "Feo";
        String descripcionLugar = "Muchas presas";
        String examenAsociado = "Sangre";
        
        MultiResultadoExamen instance = new MultiResultadoExamen();
        ResultadoExamen expResult = new ResultadoExamen(codigoResultado, nombreLugar, tipoLugar, descripcionLugar, examenAsociado);
        ResultadoExamen result = instance.buscar(codigoResultado);
        assertEquals(expResult.getDescripcionLugar(), result.getDescripcionLugar());
        assertEquals(expResult.getCodigoResultado(), result.getCodigoResultado());
        assertEquals(expResult.getExamenAsociado(), result.getExamenAsociado());
        assertEquals(expResult.getNombreLugar(), result.getNombreLugar());
        assertEquals(expResult.getTipoLugar(), result.getTipoLugar());
	}

	@Test
	public void testBorrar() {
		
	}

}

package capaTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;

import capaLogica.MultiReceta;
import capaLogica.Receta;

public class MultiRecetaTest {

	@Test
	public void testCrear() {
		System.out.println("crear");
		int pcodigoReceta = 2;
		String pdosis = "100"; 
		int pnumDias = 10;
		Date pfechaInicio = new Date(Calendar.getInstance().getTime().getTime());
		Date pfechaFinalizacion = new Date(Calendar.getInstance().getTime().getTime()); 
		String pmedicinaAsociada = "1234";
		int pconsultaAsociada = 1;
        
        MultiReceta instance = new MultiReceta();
        Receta expResult = new Receta(pcodigoReceta, pdosis, pnumDias, pfechaInicio, pfechaFinalizacion, pmedicinaAsociada, pconsultaAsociada );
        Receta result = instance.crear(pcodigoReceta, pdosis,pnumDias, pfechaInicio, pfechaFinalizacion, pmedicinaAsociada, pconsultaAsociada );
        assertEquals(expResult.getDosis(), result.getDosis());
        assertEquals(expResult.getCodigoMedicina(), result.getCodigoMedicina());
        assertEquals(expResult.getConsultaAsociada(), result.getConsultaAsociada());
        assertEquals(expResult.getNumeroDias(), result.getNumeroDias());
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

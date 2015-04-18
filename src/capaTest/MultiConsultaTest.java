package capaTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

import capaLogica.Cita;
import capaLogica.Consulta;
import capaLogica.MultiCita;
import capaLogica.MultiConsulta;

public class MultiConsultaTest {

	@Test
	public void testCrear() {
		
		String pcedulaDoctor = "123";
		Date pdiaCita = new Date(1220227200);
		String pdescripcion = "Algo";
		int pexpedienteAsociado = 1;
		
		MultiConsulta test = new MultiConsulta();
		Consulta result = test.crear(pcedulaDoctor,pdiaCita,pdescripcion,pexpedienteAsociado);
		Consulta exp = new Consulta(pcedulaDoctor,pdiaCita,pdescripcion,pexpedienteAsociado);

		assertEquals(exp.getCodigoConsulta(),result.getCodigoConsulta());
		
		//fail("Not yet implemented");
	}

	@Test
	public void testBuscarTodos() {
		
		int pnumeroExpediente = 1;
		
		MultiConsulta test = new MultiConsulta();
		
		 List<Consulta> result = test.buscarTodos(pnumeroExpediente);
		 List<Consulta> expected = test.buscarTodos(pnumeroExpediente);
		 
		 System.out.println(result.size());
		
		 assertEquals(expected.get(0).getCodigoConsulta(),result.get(0).getCodigoConsulta());
		
		
		//fail("Not yet implemented");
	}

	@Test
	public void testBuscar() {
		int pnumeroExpediente = 1;
		
		MultiConsulta test = new MultiConsulta();
		
		 Consulta result = test.buscar(pnumeroExpediente);
		 Consulta expected = test.buscar(pnumeroExpediente);
		 
		
		
		 assertEquals(expected.getCodigoConsulta(),result.getCodigoConsulta());
		
		
		//fail("Not yet implemented");
	}

	@Test
	public void testBorrar() {
		fail("Not yet implemented");
	}

}

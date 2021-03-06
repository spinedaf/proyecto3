package capaTest;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;

import org.junit.Test;

import capaLogica.Cita;
import capaLogica.MultiCita;

public class MultiCitaTest {

	@Test
	public void testCrear() {
		
		int pnumeroExpediente = 1;
		String pcedulaDoctor = "666";
		Date pdiaCita = new Date(1220227200);
		String pdescripcion = "Algo";
		String pestado = "Algo+";
	
		MultiCita test = new MultiCita();
		
		Cita result = test.crear(pnumeroExpediente, pcedulaDoctor, pdiaCita, pdescripcion, pestado);
		

		//assertEquals(true,result);
		
		//fail("Not yet implemented"); // TODO
	}
	
	
	
	@Test
	public void testbuscarTodos(){
		
		int pnumeroExpediente = 1;
		
		MultiCita test = new MultiCita();
		
		 List<Cita> result = test.buscarTodos(pnumeroExpediente);
		 List<Cita> expected = test.buscarTodos(pnumeroExpediente);
		 
		 int index = result.size();
		 
		System.out.println(result.size());
		
		 //assertEquals(expected.get(0).getCodigoCita(),result.get(0).getCodigoCita());
			
	}
	
	@Test
	public void testbuscar(){
		
		int codigoCita = 0;
		int pnumeroExpediente = 1;

	
		MultiCita test = new MultiCita();
		List<Cita> lista = test.buscarTodos(pnumeroExpediente);
		codigoCita = lista.get(0).getCodigoCita();
	
		Cita result = test.buscar(codigoCita);
		
		Cita expected = test.buscar(codigoCita);
		 
		 System.out.println(result.getCedulaDoctor() + " " + result.getCodigoCita());
		
		 assertEquals(expected.getCodigoCita(),result.getCodigoCita());
			
	}

	
	@Test
	public void testborrar(){
		
		//int codigoCita = 0;
		//int pnumeroExpediente = 1;

	
		//MultiCita test = new MultiCita();
		//List<Cita> lista = test.buscarTodos(pnumeroExpediente);
		//codigoCita = (lista.get((lista.size()-1)).getCodigoCita());
	
		//test.borrar(codigoCita);
		
		
		 
		 //System.out.println(result.getCedulaDoctor() + " " + result.getCodigoCita());
		
		 //assertEquals(expected.getCodigoCita(),result.getCodigoCita());
			
	}
	

}

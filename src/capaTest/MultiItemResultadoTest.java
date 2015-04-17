package capaTest;

import static org.junit.Assert.*;

import org.junit.Test;

import capaLogica.MultiItemResultado;
import capaLogica.ItemResultado;

public class MultiItemResultadoTest {

	@Test
	public void testCrear() {
		System.out.println("crear");
		String pnombre = "Plaquetas";
		double pvalor = 100;
		double plimiteInf = 50;
		double plimiteSup = 150;
		String punMed = "ppm";
		String presultadoAsociado = "1";
                
        MultiItemResultado instance = new MultiItemResultado();
        ItemResultado expResult = new ItemResultado(pnombre, pvalor, plimiteInf, plimiteSup, punMed, presultadoAsociado);
        ItemResultado result = instance.crear(pnombre,pvalor,plimiteInf, plimiteSup, punMed, presultadoAsociado);
        assertEquals(expResult.getLimiteInferior(), result.getLimiteInferior());
        assertEquals(expResult.getLimiteSuperior(), result.getLimiteSuperior());
        assertEquals(expResult.getNombre(), result.getNombre());
        assertEquals(expResult.getResultadoAsociado(), result.getResultadoAsociado());
        assertEquals(expResult.getValor(), result.getValor());
        
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaLogica;

import java.sql.Date;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pinedas
 */
public class MultiPacienteTest {
    
    public MultiPacienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of crear method, of class MultiPaciente.
     */
    @Test
    public void testCrear() {
        System.out.println("crear");
        String cedula = "205870126";
        String nombre = "Eduardo Pineda";
        String direccion = "San Pablo";
        String telefono = "88986028";
        
        Calendar myCal = Calendar.getInstance();
        myCal.set(Calendar.YEAR, 1983);
        myCal.set(Calendar.MONTH, 9);
        myCal.set(Calendar.DAY_OF_MONTH, 3);
        
        Date fechaNacimiento = new Date(myCal.getTime().getTime());
        int edad = 31
                ;
        MultiPaciente instance = new MultiPaciente();
        Paciente expResult = new Paciente(cedula, nombre, direccion, telefono, fechaNacimiento, edad);
        Paciente result = instance.crear(cedula, nombre, direccion, telefono, fechaNacimiento, edad);
        assertEquals(expResult.getCedula(), result.getCedula());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}

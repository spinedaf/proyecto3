/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaLogica;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Calendar;

/**
 *
 * @author pinedas
 */
public abstract class MultiPlantilla {
    
    protected PreparedStatement crear;
    protected PreparedStatement actualizar;
    protected PreparedStatement borrar;
    protected PreparedStatement buscar;
    
    public MultiPlantilla()
    {
        definirSentenciaCrear();
        definirSentenciaActualizar();
        definirSentenciaBorrar();
        definirSentenciaBuscar();
    }
    
    public Date obtenerFecha(int dia, int mes, int anio)
    {
        Calendar myCal = Calendar.getInstance();
        myCal.set(Calendar.YEAR, anio);
        myCal.set(Calendar.MONTH, mes);
        myCal.set(Calendar.DAY_OF_MONTH, dia);
        
        return new Date(myCal.getTime().getTime());
    }
    
    public abstract void definirSentenciaCrear();
    public abstract void definirSentenciaActualizar();
    public abstract void definirSentenciaBorrar();
    public abstract void definirSentenciaBuscar();
}

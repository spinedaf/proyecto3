//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Proyecto3
//  @ File Name : Examen.java
//  @ Date : 4/10/2015
//  @ Author : Eduardo Pineda, Javier Gonzalez
//
//

package capaLogica;

import java.sql.Date;


public class Examen {
    private String nombre;
    private Date fechaSolcitud;
    private Date fechaRealizacion;
    private String indicaciones;
    private int consultaAsociada;
    private ResultadoExamen resultadoExamen;
    
    public Examen(String pnombre, Date pfechaSolicitud, Date pfechaRealizacion, String pindicaciones, int pconsultaAsociada) 
    {
    	setNombre(pnombre);
    	setFechaSolcitud(pfechaSolicitud);
    	setFechaRealizacion(pfechaRealizacion);
    	setIndicaciones(pindicaciones);
    	setConsultaAsociada(pconsultaAsociada);
    	
    }
    
    public Examen(String pnombre, Date pfechaSolicitud, Date pfechaRealizacion, 
    		String pindicaciones, int pconsultaAsociada, ResultadoExamen presultadoExamen) 
    {
    	setNombre(pnombre);
    	setFechaSolcitud(pfechaSolicitud);
    	setFechaRealizacion(pfechaRealizacion);
    	setIndicaciones(pindicaciones);
    	setConsultaAsociada(pconsultaAsociada);
    	setResultadoExamen(presultadoExamen);
    	
    }

    
    /**
     * @param consultaAsociada the nomconsultaAsociadabre to set
     */
    private void setConsultaAsociada(int pconsultaAsociada) {
        this.consultaAsociada = pconsultaAsociada;
    }
    
    /**
     * @param nombre the nombre to set
     */
    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param fechaSolcitud the fechaSolcitud to set
     */
    private void setFechaSolcitud(Date fechaSolcitud) {
        this.fechaSolcitud = fechaSolcitud;
    }

    /**
     * @param fechaRealizacion the fechaRealizacion to set
     */
    private void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    /**
     * @param indicaciones the indicaciones to set
     */
    private void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }
    
    /**
     * @param resultadoExamen the resultadoExamen to set
     */
    private void setResultadoExamen(ResultadoExamen presultadoExamen) {
        this.resultadoExamen = presultadoExamen;
    }
    
    /**
     * @return the nomconsultaAsociadabre 
     */
    private int getConsultaAsociada() {
        return consultaAsociada;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the fechaSolcitud
     */
    public Date getFechaSolcitud() {
        return fechaSolcitud;
    }

    /**
     * @return the fechaRealizacion
     */
    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    /**
     * @return the indicaciones
     */
    public String getIndicaciones() {
        return indicaciones;
    }
    
    /**
     * @retun the resultadoExamen to set
     */
    public ResultadoExamen getResultadoExamen() {
    	if(resultadoExamen == null)
    	{
    		resultadoExamen = (new MultiResultadoExamen()).buscarPorExamen(this.getNombre());
    	}
        return resultadoExamen;
    }
    
    
    
}

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Proyecto3
//  @ File Name : Consulta.java
//  @ Date : 4/10/2015
//  @ Author : Eduardo Pineda, Javier Gonzalez
//
//

package capaLogica;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Consulta {
	
	private int codigoConsulta;
	private String cedulaDoctor;
    private Date fecha;
    private String descripcion;
    private List<Receta> recetasRecetadas;
    private List<Examen> examenesIndicados;
    private int expedienteAsociado;
    private Doctor doctor;
    
    public Consulta(String pcedulaDoctor, Date pfecha, String pdescripcion, int pexpediente) {
        
        this.setCedulaDoctor(pcedulaDoctor); 
        this.setFecha(pfecha);
        this.setDescripcion(pdescripcion);
        this.setExpedienteAsociado(pexpediente);
        
        recetasRecetadas = null;
        examenesIndicados = null;
        doctor = null;
    }
    
    public Consulta(int pcodigo, String pnombreDoctor, Date pfecha, String pdescripcion, int pexpediente) {
        
        this.setCedulaDoctor(pnombreDoctor);
        this.setCodigoConsulta(pcodigo);
        this.setFecha(pfecha);
        this.setDescripcion(pdescripcion);
        this.setExpedienteAsociado(pexpediente);
        
        recetasRecetadas = null;
        examenesIndicados = null;
        doctor = null;
    }

    public Doctor getDoctor() {
    	if(doctor == null)
    	{
    		doctor = (new MultiDoctor()).buscar(this.cedulaDoctor);
    	}
		return doctor;
	}

	/**
     * @return the codigoConsulta
     */
    public int getCodigoConsulta() {
        return codigoConsulta;
    }

    /**
     * @return the nombreDoctor
     */
    public String getNombreDoctor() {
        return this.getDoctor().getNombre();
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the medicinasRecetadas
     */
    public List<Receta> getRecetas() {
    	if(recetasRecetadas == null)
    	{
    		recetasRecetadas = (new MultiReceta()).buscarPorConsulta(this.getExpedienteAsociado());
    	}
        return recetasRecetadas;
    }

    /**
     * @return the examenesIndicados
     */
    public List<Examen> getExamenesIndicados() {
    	
        return examenesIndicados;
    }

    
    /**
     * @param codigoConsulta the codigoConsulta to set
     */
    private void setCodigoConsulta(int codigoConsulta) {
        this.codigoConsulta = codigoConsulta;
    }

    /**
     * @param nombreDoctor the nombreDoctor to set
     */
    private void setCedulaDoctor(String nombreDoctor) {
        this.cedulaDoctor = nombreDoctor;
    }

    /**
     * @param fecha the fecha to set
     */
    private void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @param descripcion the descripcion to set
     */
    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @param medicinasRecetadas the medicinasRecetadas to set
     */
    private void setRecetas(ArrayList<Receta> medicinasRecetadas) {
        this.recetasRecetadas = medicinasRecetadas;
    }

    /**
     * @param examenesIndicados the examenesIndicados to set
     */
    private void setExamenesIndicados(ArrayList<Examen> examenesIndicados) {
        this.examenesIndicados = examenesIndicados;
    }

   
    /**
     * @return the expedienteAsociado
     */
    public int getExpedienteAsociado() {
        return expedienteAsociado;
    }
    
    /**
     * @return the expedienteAsociado
     */
    public String getCSMAsociado() {
        return "CSM-" + expedienteAsociado;
    }

    /**
     * @param expedienteAsociado the expedienteAsociado to set
     */
    public void setExpedienteAsociado(int expedienteAsociado) {
        this.expedienteAsociado = expedienteAsociado;
    }
}

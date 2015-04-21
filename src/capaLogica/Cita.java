/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaLogica;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author pinedas
 */
public class Cita {
    private int codigoCita;
    private int numeroExpediente;
    private String cedulaDoctor;
    private Date diaCita;
    private String descripcion;
    private String estado;
    
    
    private Doctor doctor;
    
    public Cita(int pnumeroExpediente, String pcedulaDoctor, Date pdiaCita, 
    			String pdescripcion, String pestado)
    {
    	this.setNumeroExpediente(pnumeroExpediente);
    	this.setCedulaDoctor(pcedulaDoctor);
    	this.setDiaCita(pdiaCita);
    	this.setDescripcion(pdescripcion);
        this.setEstado(pestado);  
        doctor = null;
    }
    
    
    
    public Cita( int pcodigo, int pnumeroExpediente, String pcedulaDoctor, 
    			 Date pdiaCita, String pdescripcion, String pestado)
    {
        this.setCodigoCita(pcodigo);
        this.setNumeroExpediente(pnumeroExpediente);    
        this.setCedulaDoctor(pcedulaDoctor);
        this.setDiaCita(pdiaCita);  
        this.setDescripcion(pdescripcion);
        this.setEstado(pestado);
        
        doctor = null;
    }

    public Doctor getDoctor() {
    	if(doctor == null)
    	{
    		doctor = (new MultiDoctor()).buscar(cedulaDoctor);
    	}
		return doctor;
	}



	private void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}



	/**
     * @return the codigoCita
     */
    public int getCodigoCita() {
        return codigoCita;
    }

    /**
     * @return the numeroExpediente
     */
    public int getNumeroExpediente() {
        return numeroExpediente;
    }

    /**
     * @return the nombreDoctor
     */
    public String getCedulaDoctor() {
        return cedulaDoctor;
    }

    /**
     * @return the diaCita
     */
    public Date getDiaCita() {
        return diaCita;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param codigoCita the codigoCita to set
     */
    private void setCodigoCita(int codigoCita) {
        this.codigoCita = codigoCita;
    }

    /**
     * @param numeroExpediente the numeroExpediente to set
     */
    private void setNumeroExpediente(int numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    /**
     * @param nombreDoctor the nombreDoctor to set
     */
    private void setCedulaDoctor(String nombreDoctor) {
        this.cedulaDoctor = nombreDoctor;
    }

    /**
     * @param diaCita the diaCita to set
     */
    private void setDiaCita(Date diaCita) {

        this.diaCita = diaCita;
    }
    
    /**
     * @param estado the descripcion to set
     */
    private void setDescripcion(String pdescripcion) {
        this.descripcion= pdescripcion;
    }

    /**
     * @param estado the estado to set
     */
    private void setEstado(String estado) {
        this.estado = estado;
    }
}

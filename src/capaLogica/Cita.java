/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaLogica;

import java.sql.Date;

/**
 *
 * @author pinedas
 */
public class Cita {
    private int codigoCita;
    private String numeroExpediente;
    private String cedulaDoctor;
    private Date diaCita;
    private String estado;
    
    private Doctor doctor;
    
    public Cita( int pcodigo, String pnumeroExpediente, String pnombreDoctor, Date pdiaCita, String pestado)
    {
        this.setCodigoCita(pcodigo);
        this.setDiaCita(pdiaCita);
        this.setNumeroExpediente(pnumeroExpediente);
        this.setNombreDoctor(pnombreDoctor);
        this.setEstado(pestado);
        
        doctor = null;
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
    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    /**
     * @return the nombreDoctor
     */
    public String getNombreDoctor() {
        return cedulaDoctor;
    }

    /**
     * @return the diaCita
     */
    public Date getDiaCita() {
        return diaCita;
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
    private void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    /**
     * @param nombreDoctor the nombreDoctor to set
     */
    private void setNombreDoctor(String nombreDoctor) {
        this.cedulaDoctor = nombreDoctor;
    }

    /**
     * @param diaCita the diaCita to set
     */
    private void setDiaCita(Date diaCita) {
        this.diaCita = diaCita;
    }

    /**
     * @param estado the estado to set
     */
    private void setEstado(String estado) {
        this.estado = estado;
    }
}

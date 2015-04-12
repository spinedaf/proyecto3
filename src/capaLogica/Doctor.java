/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaLogica;

/**
 *
 * @author pinedas
 */
public class Doctor {
    private String cedula;
    private String nombre;
    private String especialidad;
    private String telefono;
    
    public Doctor(String pcedula, String pnombre, String pespecialidad, String ptelefono)
    {
        this.setCedula(pcedula);
        this.setNombre(pnombre);
        this.setEspecialidad(pespecialidad);
        this.setTelefono(ptelefono);
    }

    /**
     * @param cedula the cedula to set
     */
    private void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @param nombre the nombre to set
     */
    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param especialidad the especialidad to set
     */
    private void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * @param telefono the telefono to set
     */
    private void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the especialidad
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaLogica;

import capaAccesoBD.Conector;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pinedas
 */
public class MultiPaciente {
    
    private PreparedStatement crearPaciente;
    private String crearPacienteString;
    
    private PreparedStatement buscarPaciente;
    private String buscarPacienteString;
    
    
    public MultiPaciente()
    {
        crearPacienteString = "INSERT INTO TPaciente "
                    + "(cedula, nombre, direccion, telefono, fechaNacimiento, edad) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
        buscarPacienteString = "SELECT * FROM TPaciente WHERE cedula=?;";
        
        
        try {
            crearPaciente = Conector.getConector().obtenerSentenciaPreparada(crearPacienteString);
            buscarPaciente = Conector.getConector().obtenerSentenciaPreparada(buscarPacienteString);
        } catch (Exception ex) {
            Logger.getLogger(MultiPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Paciente crear(String cedula, String nombre, String direccion, String telefono,
            Date fechaNacimiento, int edad)
    {
        Paciente paciente = null;
        try {
            paciente = new Paciente(cedula,nombre,direccion,telefono,fechaNacimiento,edad);
            crearPaciente.setString(1, cedula);
            crearPaciente.setString(2, nombre);
            crearPaciente.setString(3, direccion);
            crearPaciente.setString(4, telefono);
            crearPaciente.setDate(5, fechaNacimiento);
            crearPaciente.setInt(6, edad);
            
            crearPaciente.execute();
            crearPaciente.close();
        } catch (SQLException ex) {
            Logger.getLogger(MultiPaciente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            return paciente;
        }
    }
    
    public Paciente buscar(String cedula){
        java.sql.ResultSet rs;
        Paciente paciente = null;
        try {         
            buscarPaciente.setString(1, cedula);
            rs = buscarPaciente.executeQuery();
            if (rs.next()){
                paciente = new Paciente(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getDate("fechaNacimiento"),
                    rs.getInt("edad")
                );
            } 
            rs.close();
            buscarPaciente.close();
        } catch (SQLException ex) {
            Logger.getLogger(MultiPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            return paciente;
        }
    }
}

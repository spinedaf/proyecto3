/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaLogica;

import capaAccesoBD.Conector;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    private String borrarPacienteString;
    private PreparedStatement borrarPaciente;
    
    private String buscarTodosString;
    private PreparedStatement buscarTodos;
    
    
    public MultiPaciente()
    {
        crearPacienteString = "INSERT INTO TPaciente "
                    + "(cedula, nombre, direccion, telefono, fechaNacimiento, edad) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
        buscarPacienteString = "SELECT * FROM TPaciente WHERE cedula=?;";
        borrarPacienteString = "DELETE FROM TPaciente WHERE cedula=?";
        buscarTodosString = "SELECT * FROM TPaciente;";
        
        try {
            crearPaciente = Conector.getConector().obtenerSentenciaPreparada(crearPacienteString);
            buscarPaciente = Conector.getConector().obtenerSentenciaPreparada(buscarPacienteString);
            borrarPaciente = Conector.getConector().obtenerSentenciaPreparada(borrarPacienteString);
            buscarTodos = Conector.getConector().obtenerSentenciaPreparada(buscarTodosString);
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
            
            crearPaciente.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return paciente;

    }
    
    public List<Paciente> buscarTodos()
    {
        List<Paciente> resultados = null;
        ResultSet rs = null;
        
        try{
            rs = buscarTodos.executeQuery();
            resultados = new ArrayList<Paciente>();
            
            while(rs.next())
            {
                resultados.add(new Paciente(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getDate("fechaNacimiento"),
                        rs.getInt("edad")
                ));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally{
            try{
                rs.close();
            }
            catch(SQLException sqlException)
            {
                sqlException.printStackTrace();
            }
        }
        
        return resultados;
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return paciente;
    }
    
    public void borrar(Paciente paciente)
    {
        try{
            borrarPaciente.setString(1, paciente.getCedula());
            borrarPaciente.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MultiPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package capaLogica;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import capaAccesoBD.Conector;

public class MultiDoctor {

   	private PreparedStatement crearDoctor;
    private String crearDoctorString;
    
    private PreparedStatement buscarDoctor;
    private String buscarDoctorString;
    
    private String borrarDoctor;
    private PreparedStatement borrarDoctorString;
    
    private String buscarTodosString;
    private PreparedStatement buscarTodos;
    
    
    public MultiDoctor()
    {
        crearDoctorString = "INSERT INTO TDoctor "
                    + "(cedula, nombre, especialidad, telefono) "
                    + "VALUES (?, ?, ?, ?)";
        buscarDoctorString = "SELECT * FROM TDoctor WHERE cedula=?;";
        borrarDoctor = "DELETE * FROM TDoctor WHERE cedula=?";
        buscarTodosString = "SELECT * FROM TDoctor;";
        
        try {
            crearDoctor = Conector.getConector().obtenerSentenciaPreparada(crearDoctorString);
            buscarDoctor = Conector.getConector().obtenerSentenciaPreparada(buscarDoctorString);
            borrarDoctorString = Conector.getConector().obtenerSentenciaPreparada(borrarDoctor);
            buscarTodos = Conector.getConector().obtenerSentenciaPreparada(buscarTodosString);
        } catch (Exception ex) {
            Logger.getLogger(MultiDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Doctor crear(String pcedula, String pnombre, String pespecialidad, String ptelefono)
    {
    	Doctor doctor = null;
        try {
        	
            crearDoctor.setString(1, pcedula);
            crearDoctor.setString(2, pnombre);
            crearDoctor.setString(3, pespecialidad);
            crearDoctor.setString(4, ptelefono);
                       
            crearDoctor.executeUpdate();
            doctor = new Doctor(pcedula,pnombre,pespecialidad,ptelefono);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return doctor;

    }
    
    public List<Doctor> buscarTodos()
    {
        List<Doctor> resultados = null;
        ResultSet rs = null;
        
        try{
        	
            rs = buscarTodos.executeQuery();
            resultados = new ArrayList<Doctor>();
            
            
            while(rs.next())
            {
            	
            	
               resultados.add(new Doctor(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"),
                        rs.getString("telefono")
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
    
    public Doctor buscar(String pcedula){
        java.sql.ResultSet rs;
        Doctor doctor = null;
        try {         
            buscarDoctor.setString(1, pcedula);
            rs = buscarDoctor.executeQuery();
            if (rs.next()){
            	doctor = new Doctor(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"),
                        rs.getString("telefono")
                );
            } 
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return doctor;
    }
    
    public void borrar(String pcedula)
    {
        try{
            borrarDoctorString.setString(1, pcedula);
            borrarDoctorString.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MultiDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	
}

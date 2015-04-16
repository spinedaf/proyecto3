package capaLogica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import capaAccesoBD.Conector;

public class MultiExamen {
	
	private PreparedStatement crearExamen;
    private String crearExamenString;
    
    private PreparedStatement buscarExamen;
    private String buscarExamenString;
    
    private String borrarExamen;
    private PreparedStatement borrarExamenString;
    
    private String buscarTodosString;
    private PreparedStatement buscarTodos;
    
    
    public MultiExamen()
    {
        crearExamenString = "INSERT INTO TExamen "
                    + "(nombre, fechaSolicitud, fechaRealizacion, indicaciones,consultaAsociada) "
                    + "VALUES (?, ?, ?, ?, ?)";
        buscarExamenString = "SELECT * FROM TExamen WHERE nombre=?;";
        borrarExamen = "DELETE * FROM TExamen WHERE nombre=?";
        buscarTodosString = "SELECT * FROM TExamen;";
        
        try {
            crearExamen = Conector.getConector().obtenerSentenciaPreparada(crearExamenString);
            buscarExamen = Conector.getConector().obtenerSentenciaPreparada(buscarExamenString);
            borrarExamenString = Conector.getConector().obtenerSentenciaPreparada(borrarExamen);
            buscarTodos = Conector.getConector().obtenerSentenciaPreparada(buscarTodosString);
        } catch (Exception ex) {
            Logger.getLogger(MultiExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean crear(String nombre, Date fechaSolicitud, Date fechaRealizacion, 
    					 String indicaciones,int consultaAsociada)
    {
    	
   
        try {

            crearExamen.setString(1, nombre);
            crearExamen.setDate(2, fechaSolicitud);
            crearExamen.setDate(3, fechaRealizacion);
            crearExamen.setString(4, indicaciones);
            crearExamen.setInt(5, consultaAsociada);          
            crearExamen.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;

    }
    
    public List<Examen> buscarTodos()
    {
        List<Examen> resultados = null;
        ResultSet rs = null;
        
        try{
        	
            rs = buscarTodos.executeQuery();
            resultados = new ArrayList<Examen>();
            
            
            while(rs.next())
            {
            	
            	
               resultados.add(new Examen(
                        rs.getString("nombre"),
                        rs.getDate("fechaSolicitud"),
                        rs.getDate("fechaRealizacion"),
                        rs.getString("indicaciones"),
                        rs.getInt("consultaAsociada")
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
    
    public Examen buscar(String pnombre){
        java.sql.ResultSet rs;
        Examen examen = null;
        try {         
            buscarExamen.setString(1, pnombre);
            rs = buscarExamen.executeQuery();
            if (rs.next()){
            	examen = new Examen(
                        rs.getString("nombre"),
                        rs.getDate("fechaSolicitud"),
                        rs.getDate("fechaRealizacion"),
                        rs.getString("indicaciones"),
                        rs.getInt("consultaAsociada")
                );
            } 
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return examen;
    }
    
    public void borrar(String pnombre)
    {
        try{
            borrarExamenString.setString(1, pnombre);
            borrarExamenString.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MultiDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

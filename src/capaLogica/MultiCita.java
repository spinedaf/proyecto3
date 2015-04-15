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

public class MultiCita {
	
	
	   	private PreparedStatement crearCita;
	    private String crearCitaString;
	    
	    private PreparedStatement buscarCita;
	    private String buscarCitaString;
	    
	    private String borrarCita;
	    private PreparedStatement borrarCitaString;
	    
	    private String buscarTodosString;
	    private PreparedStatement buscarTodos;
	    
	    
	    public MultiCita()
	    {
	        crearCitaString = "INSERT INTO TCita "
	                    + "(numeroExpediente, cedulaDoctor, diaCita, descripcion, estado) "
	                    + "VALUES (?, ?, ?, ?, ?)";
	        buscarCitaString = "SELECT * FROM TCita WHERE codigoCita=?;";
	        borrarCita = "DELETE * FROM TCita WHERE codigoCita=?";
	        buscarTodosString = "SELECT * FROM TCita WHERE numeroExpediente=?;";
	        
	        try {
	            crearCita = Conector.getConector().obtenerSentenciaPreparada(crearCitaString);
	            buscarCita = Conector.getConector().obtenerSentenciaPreparada(buscarCitaString);
	            borrarCitaString = Conector.getConector().obtenerSentenciaPreparada(borrarCita);
	            buscarTodos = Conector.getConector().obtenerSentenciaPreparada(buscarTodosString);
	        } catch (Exception ex) {
	            Logger.getLogger(MultiCita.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    
	    public boolean crear(String pnumeroExpediente, String pcedulaDoctor, Date pdiaCita, String pdescripcion,
	            String pestado)
	    {
	    	
	   
	        try {

	            crearCita.setString(1, pnumeroExpediente);
	            crearCita.setString(2, pcedulaDoctor);
	            crearCita.setDate(3, pdiaCita);
	            crearCita.setString(4, pdescripcion);
	            crearCita.setString(5, pestado);           
	            crearCita.executeUpdate();
	            return true;
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	        return false;

	    }
	    
	    public List<Cita> buscarTodos(String pnumeroExpediente)
	    {
	        List<Cita> resultados = null;
	        ResultSet rs = null;
	        
	        try{
	        	buscarTodos.setString(1, pnumeroExpediente);
	            rs = buscarTodos.executeQuery();
	            resultados = new ArrayList<Cita>();
	            
	            
	            while(rs.next())
	            {
	            	
	            	
	               resultados.add(new Cita(
	                        rs.getInt("codigoCita"),
	                        rs.getString("numeroExpediente"),
	                        rs.getString("cedulaDoctor"),
	                        rs.getDate("diaCita"),
	                        rs.getString("descripcion"),
	                        rs.getString("estado")
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
	    
	    public Cita buscar(String pcodigoCita){
	        java.sql.ResultSet rs;
	        Cita cita = null;
	        try {         
	            buscarCita.setString(1, pcodigoCita);
	            rs = buscarCita.executeQuery();
	            if (rs.next()){
	            	cita = new Cita(
	                    rs.getInt("codigoCita"),
	                    rs.getString("numeroExpediente"),
	                    rs.getString("cedulaDoctor"),
	                    rs.getDate("diaCita"),
	                    rs.getString("descripcion"),
	                    rs.getString("estado")
	                );
	            } 
	            rs.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	        return cita;
	    }
	    
	    public void borrar(Cita pcita)
	    {
	        try{
	            borrarCitaString.setInt(1, pcita.getCodigoCita());
	            borrarCitaString.executeUpdate();
	        } catch (SQLException ex) {
	            Logger.getLogger(MultiCita.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

}

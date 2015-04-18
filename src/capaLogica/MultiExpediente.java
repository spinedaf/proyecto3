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

public class MultiExpediente {

	
	private PreparedStatement crearExpediente;
    private String crearExpedienteString;
    
    private PreparedStatement buscarExpediente;
    private String buscarExpedienteString;
    
    private String borrarExpediente;
    private PreparedStatement borrarExpedienteString;
    
    private String buscarTodosString;
    private PreparedStatement buscarTodos;
    
    
    public MultiExpediente()
    {
        crearExpedienteString = "INSERT INTO TExpediente "
                    + "(cedulaPaciente, fechaApertura) "
                    + "VALUES (?, ?)";
        buscarExpedienteString = "SELECT * FROM TExpediente WHERE numeroExpediente=?;";
        borrarExpediente = "DELETE * FROM TExpediente WHERE numeroExpediente=?";
        buscarTodosString = "SELECT * FROM TExpediente;";
        
        try {
            crearExpediente = Conector.getConector().obtenerSentenciaPreparada(crearExpedienteString);
            buscarExpediente = Conector.getConector().obtenerSentenciaPreparada(buscarExpedienteString);
            borrarExpedienteString = Conector.getConector().obtenerSentenciaPreparada(borrarExpediente);
            buscarTodos = Conector.getConector().obtenerSentenciaPreparada(buscarTodosString);
        } catch (Exception ex) {
            Logger.getLogger(MultiExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Expediente crear(String pcedulaPaciente, Date pfechaApertura)
    {
    	Expediente exp = null;  
        try {
            crearExpediente.setString(1, pcedulaPaciente);
            crearExpediente.setDate(2, pfechaApertura);
            crearExpediente.executeUpdate();
            exp = new Expediente(pcedulaPaciente, pfechaApertura);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return exp;

    }
    
    public List<Expediente> buscarTodos()
    {
        List<Expediente> resultados = null;
        ResultSet rs = null;
        
        try{
        	
            rs = buscarTodos.executeQuery();
            resultados = new ArrayList<Expediente>();
            
            
            while(rs.next())
            {
            	
            	
               resultados.add(new Expediente(
            		   	rs.getInt("numeroExpediente"),
                        rs.getString("cedulaPaciente"),
                        rs.getDate("fechaApertura")
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
    
    public Expediente buscar(int pnumeroExpediente){
        java.sql.ResultSet rs;
        Expediente expediente = null;
        try {         
            buscarExpediente.setInt(1, pnumeroExpediente);
            rs = buscarExpediente.executeQuery();
            if (rs.next()){
            	expediente = new Expediente(
            		   	rs.getInt("numeroExpediente"),
                        rs.getString("cedulaPaciente"),
                        rs.getDate("fechaApertura")
                );
            } 
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return expediente;
    }
    
    public void borrar(int  pnumeroExpediente)
    {
        try{
            borrarExpedienteString.setInt(1, pnumeroExpediente);
            borrarExpedienteString.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MultiExpediente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
}

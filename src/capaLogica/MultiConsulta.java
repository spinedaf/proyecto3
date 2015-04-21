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

public class MultiConsulta {
	
 	private PreparedStatement crearConsulta;
    private String crerConsultaString;
    
    private PreparedStatement buscarConsulta;
    private String buscarConsultaString;
    
    private String borrraConsulta;
    private PreparedStatement borrarConsultaString;
    
    private String buscarTodosString;
    private PreparedStatement buscarTodos;
    
    private PreparedStatement buscarPorExpediente;
    private String buscarPorExpedienteString;
    
    public MultiConsulta()
    {
        crerConsultaString = "INSERT INTO TConsulta "
                    + "(cedulaDoctor, fechaConsulta, descripcion, expedienteAsociado) "
                    + "VALUES (?, ?, ?, ?)";
        buscarConsultaString = "SELECT * FROM TConsulta WHERE codigoConsulta=?;";
        borrraConsulta = "DELETE * FROM TConsulta WHERE codigoConsulta=?";
        buscarTodosString = "SELECT * FROM TConsulta WHERE expedienteAsociado=?;";
        buscarPorExpedienteString = "SELECT * FROM TConsulta WHERE expedienteAsociado=?;";
        
        try {
            crearConsulta = Conector.getConector().obtenerSentenciaPreparada(crerConsultaString);
            buscarConsulta = Conector.getConector().obtenerSentenciaPreparada(buscarConsultaString);
            borrarConsultaString = Conector.getConector().obtenerSentenciaPreparada(borrraConsulta);
            buscarTodos = Conector.getConector().obtenerSentenciaPreparada(buscarTodosString);
            buscarPorExpediente = Conector.getConector().obtenerSentenciaPreparada(buscarPorExpedienteString);
        } catch (Exception ex) {
            Logger.getLogger(MultiConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Consulta crear(String pcedulaDoctor, Date pfechaConsulta, 
    		String pdescripcion,int expedienteAsociado)
    {
    	Consulta consulta = null;
   
        try {

            crearConsulta.setString(1, pcedulaDoctor);
            crearConsulta.setDate(2, pfechaConsulta);
            crearConsulta.setString(3, pdescripcion);
            crearConsulta.setInt(4, expedienteAsociado);       
            crearConsulta.executeUpdate();
            
            ResultSet generatedKeys = crearConsulta.getGeneratedKeys();
            int codigo = 0;
            if (null != generatedKeys && generatedKeys.next()) {
            	codigo = generatedKeys.getInt(1);
            }
            
            consulta = new Consulta(codigo, pcedulaDoctor, pfechaConsulta, pdescripcion, expedienteAsociado);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return consulta;

    }
    
    public List<Consulta> buscarTodos(int pnumeroExpediente)
    {
        List<Consulta> resultados = null;
        ResultSet rs = null;
        
        try{
        	buscarTodos.setInt(1, pnumeroExpediente);
            rs = buscarTodos.executeQuery();
            resultados = new ArrayList<Consulta>();
            
            
            while(rs.next())
            {
            	
            	
               resultados.add(new Consulta(
            		   rs.getInt("codigoConsulta"),
                        rs.getString("cedulaDoctor"),
                        rs.getDate("fechaConsulta"),
                        rs.getString("descripcion"),
                        rs.getInt("expedienteAsociado")
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
    
    public Consulta buscar(int pcodigoConsulta){
        java.sql.ResultSet rs;
        Consulta consulta = null;
        try {         
            buscarConsulta.setInt(1, pcodigoConsulta);
            rs = buscarConsulta.executeQuery();
            if (rs.next()){
            	consulta = new Consulta(
                    rs.getInt("codigoConsulta"),
                    rs.getString("cedulaDoctor"),
                    rs.getDate("fechaConsulta"),
                    rs.getString("descripcion"),
                    rs.getInt("expedienteAsociado")
                );
            } 
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return consulta;
    }
    
    public List<Consulta> buscarPorExpediente(int pexpedienteAsociado){
    	List<Consulta> resultados = null;
        ResultSet rs = null;
        
        try{
        	buscarPorExpediente.setInt(1, pexpedienteAsociado);
            rs = buscarPorExpediente.executeQuery();
            resultados = new ArrayList<Consulta>();
            
            
            while(rs.next())
            {
            	
            	
               resultados.add(new Consulta(
            		   rs.getInt("codigoConsulta"),
                        rs.getString("cedulaDoctor"),
                        rs.getDate("fechaConsulta"),
                        rs.getString("descripcion"),
                        rs.getInt("expedienteAsociado")
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
    
    public void borrar(int pcodigoConsulta)
    {
        try{
            borrarConsultaString.setInt(1, pcodigoConsulta);
            borrarConsultaString.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MultiConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

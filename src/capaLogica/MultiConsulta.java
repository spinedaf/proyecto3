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
    
    
    public MultiConsulta()
    {
        crerConsultaString = "INSERT INTO TConsulta "
                    + "(cedulaDoctor, fechaConsulta, descripcion, expedienteAsociado) "
                    + "VALUES (?, ?, ?, ?)";
        buscarConsultaString = "SELECT * FROM TConsulta WHERE codigoConsulta=?;";
        borrraConsulta = "DELETE * FROM TConsulta WHERE codigoConsulta=?";
        buscarTodosString = "SELECT * FROM TConsulta WHERE numeroExpediente=?;";
        
        try {
            crearConsulta = Conector.getConector().obtenerSentenciaPreparada(crerConsultaString);
            buscarConsulta = Conector.getConector().obtenerSentenciaPreparada(buscarConsultaString);
            borrarConsultaString = Conector.getConector().obtenerSentenciaPreparada(borrraConsulta);
            buscarTodos = Conector.getConector().obtenerSentenciaPreparada(buscarTodosString);
        } catch (Exception ex) {
            Logger.getLogger(MultiConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean crear(String pcedulaDoctor, Date pfechaConsulta, String pdescripcion,
            String expedienteAsociado)
    {
    	
   
        try {

            crearConsulta.setString(1, pcedulaDoctor);
            crearConsulta.setDate(2, pfechaConsulta);
            crearConsulta.setString(3, pdescripcion);
            crearConsulta.setString(4, expedienteAsociado);       
            crearConsulta.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;

    }
    
    public List<Consulta> buscarTodos(String pnumeroExpediente)
    {
        List<Consulta> resultados = null;
        ResultSet rs = null;
        
        try{
        	buscarTodos.setString(1, pnumeroExpediente);
            rs = buscarTodos.executeQuery();
            resultados = new ArrayList<Consulta>();
            
            
            while(rs.next())
            {
            	
            	
               resultados.add(new Consulta(
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
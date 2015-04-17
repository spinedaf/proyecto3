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

public class MultiResultadoExamen {
	
	private PreparedStatement crearResultadoExamen;
    private String crearResultadoExamenString;
    
    private PreparedStatement buscarResultadoExamen;
    private String buscarResultadoExamenString;
    
    private String borrarResultadoExamenString;
    private PreparedStatement borrarResultadoExamen;
    
    private String buscarTodosString;
    private PreparedStatement buscarTodos;
    
    private String buscarPorExamenString;
    private PreparedStatement buscarPorExamen;
    
    public MultiResultadoExamen()
    {
    	crearResultadoExamenString = "INSERT INTO TResultadoExamen "
                + "(codigoResultado, nombreLugar, tipoLugar, descripcionLugar, examenAsociado) "
                + "VALUES (?, ?, ?, ?, ?)";
    	buscarResultadoExamenString = "SELECT * FROM TResultadoExamen WHERE codigoResultado=?;";
    	borrarResultadoExamenString = "DELETE FROM TResultadoExamen WHERE codigoResultado=?";
	    buscarTodosString = "SELECT * FROM TResultadoExamen;";
	    buscarPorExamenString = "SELECT * FROM TResultadoExamen WHERE examenAsociado=?;";
	    
	    try {
	    	crearResultadoExamen = Conector.getConector().obtenerSentenciaPreparada(crearResultadoExamenString);
	    	buscarResultadoExamen = Conector.getConector().obtenerSentenciaPreparada(buscarResultadoExamenString);
	    	borrarResultadoExamen = Conector.getConector().obtenerSentenciaPreparada(borrarResultadoExamenString);
	        buscarTodos = Conector.getConector().obtenerSentenciaPreparada(buscarTodosString);
	        buscarPorExamen = Conector.getConector().obtenerSentenciaPreparada(buscarPorExamenString);
	    } catch (Exception ex) {
	        Logger.getLogger(MultiPaciente.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }
    
    public ResultadoExamen crear(String codigoResultado, String nombreLugar, String tipoLugar, String descripcionLugar,
            String examenAsociado)
    {
    	ResultadoExamen resultado = null;
        try {
        	
        	crearResultadoExamen.setString(1, codigoResultado);
        	crearResultadoExamen.setString(2, nombreLugar);
        	crearResultadoExamen.setString(3, tipoLugar);
        	crearResultadoExamen.setString(4, descripcionLugar);
        	crearResultadoExamen.setString(5, examenAsociado);
            
        	crearResultadoExamen.executeUpdate();
        	resultado = new ResultadoExamen(codigoResultado,nombreLugar,tipoLugar,descripcionLugar,examenAsociado);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;

    }
    
    public List<ResultadoExamen> buscarTodos()
    {
        List<ResultadoExamen> resultados = null;
        ResultSet rs = null;
        
        try{
            rs = buscarTodos.executeQuery();
            resultados = new ArrayList<ResultadoExamen>();
            
            while(rs.next())
            {
                resultados.add(new ResultadoExamen(
                        rs.getString("codigoResultado"),
                        rs.getString("nombreLugar"),
                        rs.getString("tipoLugar"),
                        rs.getString("descripcionLugar"),
                        rs.getString("examenAsociado")
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
    
    public ResultadoExamen buscarPorExamen(String examenAsociado)
    {
    	java.sql.ResultSet rs;
        ResultadoExamen resultado = null;
        try {         
        	buscarPorExamen.setString(1, examenAsociado);
            rs = buscarPorExamen.executeQuery();
            if (rs.next()){
                resultado = new ResultadoExamen(
                		rs.getString("codigoResultado"),
                        rs.getString("nombreLugar"),
                        rs.getString("tipoLugar"),
                        rs.getString("descripcionLugar"),
                        rs.getString("examenAsociado")
                );
            } 
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;
    }
    
    public ResultadoExamen buscar(String codigoResultado){
        java.sql.ResultSet rs;
        ResultadoExamen resultado = null;
        try {         
            buscarResultadoExamen.setString(1, codigoResultado);
            rs = buscarResultadoExamen.executeQuery();
            if (rs.next()){
                resultado = new ResultadoExamen(
                		rs.getString("codigoResultado"),
                        rs.getString("nombreLugar"),
                        rs.getString("tipoLugar"),
                        rs.getString("descripcionLugar"),
                        rs.getString("examenAsociado")
                );
            } 
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;
    }
    
    public void borrar(ResultadoExamen resultado)
    {
        try{
            borrarResultadoExamen.setString(1, resultado.getCodigoResultado());
            borrarResultadoExamen.executeUpdate();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
    }

}

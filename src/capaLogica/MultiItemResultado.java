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

public class MultiItemResultado {
	
	private PreparedStatement crearItemResultado;
    private String crearItemResultadoString;
    
    private PreparedStatement buscarItemResultado;
    private String buscarItemResultadoString;
    
    private String borrarItemResultadoString;
    private PreparedStatement borrarItemResultado;
    
    private String buscarTodosString;
    private PreparedStatement buscarTodos;
    
    private String buscarPorResultadoString;
    private PreparedStatement buscarPorResultado;
    
    public MultiItemResultado()
    {
    	crearItemResultadoString = "INSERT INTO TItemAsociado "
                + "(nombre, valor, limiteSuperior, limiteInferior, unidadMedicion, resultadoAsociado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
    	buscarItemResultadoString = "SELECT * FROM TItemAsociado WHERE nombre=?;";
    	borrarItemResultadoString = "DELETE FROM TItemAsociado WHERE nombre=?";
	    buscarTodosString = "SELECT * FROM TItemAsociado;";
	    buscarPorResultadoString = "SELECT * FROM TItemAsociado WHERE resultadoAsociado=?;";
	    
	    try {
	    	crearItemResultado = Conector.getConector().obtenerSentenciaPreparada(crearItemResultadoString);
	    	buscarItemResultado = Conector.getConector().obtenerSentenciaPreparada(buscarItemResultadoString);
	    	borrarItemResultado = Conector.getConector().obtenerSentenciaPreparada(borrarItemResultadoString);
	        buscarTodos = Conector.getConector().obtenerSentenciaPreparada(buscarTodosString);
	        buscarPorResultado = Conector.getConector().obtenerSentenciaPreparada(buscarPorResultadoString);
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
    }
    
    public ItemResultado crear(String nombre, double valor, double limiteSuperior, double limiteInferior, String unidadMedicion,
            String resultadoAsociado)
    {
    	ItemResultado item = null;
        try {
        	item = new ItemResultado(nombre,valor,limiteSuperior,limiteInferior,unidadMedicion,
        			resultadoAsociado);
        	crearItemResultado.setString(1, nombre);
        	crearItemResultado.setDouble(2, valor);
        	crearItemResultado.setDouble(3, limiteSuperior);
        	crearItemResultado.setDouble(4, limiteInferior);
        	crearItemResultado.setString(5, unidadMedicion);
        	crearItemResultado.setString(6, resultadoAsociado);
            
        	crearItemResultado.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return item;

    }
    
    public List<ItemResultado> buscarTodos()
    {
        List<ItemResultado> resultados = null;
        ResultSet rs = null;
        
        try{
            rs = buscarTodos.executeQuery();
            resultados = new ArrayList<ItemResultado>();
            
            while(rs.next())
            {
                resultados.add(new ItemResultado(
                        rs.getString("nombre"),
                        rs.getDouble("valor"),
                        rs.getDouble("limiteSuperior"),
                        rs.getDouble("limiteInferior"),
                        rs.getString("unidadMedicion"),
                        rs.getString("resultadoAsociado")
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
    
    public List<ItemResultado> buscarPorResultado( String pcodigoResultado)
    {
        List<ItemResultado> resultados = null;
        ResultSet rs = null;
        
        try{
            rs = buscarPorResultado.executeQuery();
            resultados = new ArrayList<ItemResultado>();
            
            while(rs.next())
            {
                resultados.add(new ItemResultado(
                        rs.getString("nombre"),
                        rs.getDouble("valor"),
                        rs.getDouble("limiteSuperior"),
                        rs.getDouble("limiteInferior"),
                        rs.getString("unidadMedicion"),
                        rs.getString("resultadoAsociado")
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
    
    public ItemResultado buscar(String pcodigoReceta){
        java.sql.ResultSet rs;
        ItemResultado item = null;
        try {         
            buscarItemResultado.setString(1, pcodigoReceta);
            rs = buscarItemResultado.executeQuery();
            if (rs.next()){
            	item = new ItemResultado(
            			  rs.getString("nombre"),
                          rs.getDouble("valor"),
                          rs.getDouble("limiteSuperior"),
                          rs.getDouble("limiteInferior"),
                          rs.getString("unidadMedicion"),
                          rs.getString("resultadoAsociado")
                );
            } 
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return item;
    }
    
    public void borrar(ItemResultado item)
    {
        try{
            borrarItemResultado.setString(1, item.getNombre());
            borrarItemResultado.executeUpdate();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
    }

}

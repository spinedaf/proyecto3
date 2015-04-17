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

public class MultiReceta {
	
	private PreparedStatement crearReceta;
    private String crearRecetaString;
    
    private PreparedStatement buscarReceta;
    private String buscarRecetaString;
    
    private String borrarRecetaString;
    private PreparedStatement borrarReceta;
    
    private String buscarTodosString;
    private PreparedStatement buscarTodos;
    
    public MultiReceta()
    {
    	crearRecetaString = "INSERT INTO TReceta "
                + "(codigoReceta, dosis, numeroDias, fechaDeInicio, fechaFinalizacion, codigoMedicina, consultaAsociada) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    	buscarRecetaString = "SELECT * FROM TReceta WHERE codigoReceta=?;";
    	borrarRecetaString = "DELETE FROM TReceta WHERE codigoReceta=?";
	    buscarTodosString = "SELECT * FROM TReceta;";
	    
	    try {
	    	crearReceta = Conector.getConector().obtenerSentenciaPreparada(crearRecetaString);
	    	buscarReceta = Conector.getConector().obtenerSentenciaPreparada(buscarRecetaString);
	    	borrarReceta = Conector.getConector().obtenerSentenciaPreparada(borrarRecetaString);
	        buscarTodos = Conector.getConector().obtenerSentenciaPreparada(buscarTodosString);
	    } catch (Exception ex) {
	        Logger.getLogger(MultiPaciente.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }
    
    public Receta crear(int pcodigoReceta, String pdosis, int pnumeroDias, Date pfechaDeInicio, Date pfechaFinalizacion,
            String pcodigoMedicina, int pconsultaAsociada)
    {
        Receta receta = null;
        try {
        	receta = new Receta(pcodigoReceta,pdosis,pnumeroDias,pfechaDeInicio,pfechaFinalizacion,
        			pcodigoMedicina,pconsultaAsociada);
        	crearReceta.setInt(1, pcodigoReceta);
        	crearReceta.setString(2, pdosis);
        	crearReceta.setInt(3, pnumeroDias);
        	crearReceta.setDate(4, pfechaDeInicio);
        	crearReceta.setDate(5, pfechaFinalizacion);
        	crearReceta.setString(6, pcodigoMedicina);
        	crearReceta.setInt(7, pconsultaAsociada);
            
        	crearReceta.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return receta;

    }
    
    public List<Receta> buscarTodos()
    {
        List<Receta> resultados = null;
        ResultSet rs = null;
        
        try{
            rs = buscarTodos.executeQuery();
            resultados = new ArrayList<Receta>();
            
            while(rs.next())
            {
                resultados.add(new Receta(
                        rs.getInt("pcodigoReceta"),
                        rs.getString("pdosis"),
                        rs.getInt("pnumeroDias"),
                        rs.getDate("pfechaDeInicio"),
                        rs.getDate("pfechaFinalizacion"),
                        rs.getString("pcodigoMedicina"),
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
    
    public Receta buscar(String pcodigoReceta){
        java.sql.ResultSet rs;
        Receta receta = null;
        try {         
            buscarReceta.setString(1, pcodigoReceta);
            rs = buscarReceta.executeQuery();
            if (rs.next()){
            	receta = new Receta(
            			rs.getInt("pcodigoReceta"),
                        rs.getString("pdosis"),
                        rs.getInt("pnumeroDias"),
                        rs.getDate("pfechaDeInicio"),
                        rs.getDate("pfechaFinalizacion"),
                        rs.getString("pcodigoMedicina"),
                        rs.getInt("edad")
                );
            } 
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return receta;
    }
    
    public void borrar(Receta receta)
    {
        try{
            borrarReceta.setInt(1, receta.getCodigoReceta());
            borrarReceta.executeUpdate();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
    }

}

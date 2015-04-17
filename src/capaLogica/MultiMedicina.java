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

public class MultiMedicina {

	private PreparedStatement crearMedicina;
    private String crearMedicinaString;
    
    private PreparedStatement buscarMedicina;
    private String buscarMedicinaString;
    
    private String borrarMedicinaString;
    private PreparedStatement borrarMedicina;
    
    private String buscarTodosString;
    private PreparedStatement buscarTodos;
    
    public MultiMedicina()
    {
    	crearMedicinaString = "INSERT INTO TMedicina "
                + "(codigo, nombre, precauciones) "
                + "VALUES (?, ?, ?)";
    	buscarMedicinaString = "SELECT * FROM TMedicina WHERE codigo=?;";
    	borrarMedicinaString = "DELETE FROM TMedicina WHERE codigo=?";
	    buscarTodosString = "SELECT * FROM TMedicina;";
	    
	    try {
	    	crearMedicina = Conector.getConector().obtenerSentenciaPreparada(crearMedicinaString);
	    	buscarMedicina = Conector.getConector().obtenerSentenciaPreparada(buscarMedicinaString);
	    	borrarMedicina = Conector.getConector().obtenerSentenciaPreparada(borrarMedicinaString);
	        buscarTodos = Conector.getConector().obtenerSentenciaPreparada(buscarTodosString);
	    } catch (Exception ex) {
	        Logger.getLogger(MultiPaciente.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }
    
    public Medicina crear(String pcodigo, String pnombre, String pprecauciones)
    {
    	Medicina medicina = null;
        try {
        	medicina = new Medicina(pcodigo,pnombre,pprecauciones);
        	crearMedicina.setString(1, pcodigo);
        	crearMedicina.setString(2, pnombre);
        	crearMedicina.setString(3, pprecauciones);
            
        	crearMedicina.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return medicina;

    }
    
    public List<Medicina> buscarTodos()
    {
        List<Medicina> resultados = null;
        ResultSet rs = null;
        
        try{
            rs = buscarTodos.executeQuery();
            resultados = new ArrayList<Medicina>();
            
            while(rs.next())
            {
                resultados.add(new Medicina(
                        rs.getString("pcodigo"),
                        rs.getString("pnombre"),
                        rs.getString("pprecauciones")
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
    
    public Medicina buscar(String pcodigoReceta){
        java.sql.ResultSet rs;
        Medicina medicina = null;
        try {         
            buscarMedicina.setString(1, pcodigoReceta);
            rs = buscarMedicina.executeQuery();
            if (rs.next()){
            	medicina = new Medicina(
            			 rs.getString("pcodigo"),
                         rs.getString("pnombre"),
                         rs.getString("pprecauciones")
                );
            } 
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return medicina;
    }
    
    public void borrar(Medicina medicina)
    {
        try{
            borrarMedicina.setString(1, medicina.getCodigo());
            borrarMedicina.executeUpdate();
        } catch (SQLException ex) {
        	 ex.printStackTrace();
        }
    }
}

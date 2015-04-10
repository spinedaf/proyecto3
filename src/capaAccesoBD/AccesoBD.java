package capaAccesoBD;

/**
 *Clase AccesoBD
 *@version 2.0
 *@author Laura Monge Izaguirre
 *Clase que maneja el acceso a la base de datos.
 *Se debe hacer una instancia de la
 *clase para poder utilizar sus servicios
 *
 *
 */
import java.sql.*;

/**
 *
 * @author Jagonchen
 */
public class AccesoBD{
	//atributos del objeto
	private Connection conn=null;
	private Statement st;
	
	/**
	 *Método constructor que recibe todos
	 *los parámetros necesarios para abrir
	 *una conexión válida
	 *@param driver especificación del tipo
	 *de driver que se utiliza, el cual responde
	 *al repositorio utilizado
	 *@param conexion cadena de conexión con la
	 *base de datos
     * @throws java.sql.SQLException
     * @throws java.lang.Exception
	 */
//	public AccesoBD(String driver, String conexion,	String usuario, String clave) throws SQLException,Exception{
//		Class.forName(driver);
//		conn = DriverManager.getConnection(conexion, usuario, clave);
//		st = conn.createStatement();
//	}
	
	public AccesoBD(String driver,String conexion) throws SQLException,Exception{
		Class.forName(driver);
		conn = DriverManager.getConnection(conexion);
		st = conn.createStatement();
	}
	
	/**
	 *Método que ejecuta una sentencia en la
	 *base de datos, la cual no tiene retorno,
	 *es decir un insert, delete o update
	 *@param sentencia cadena sql que será
	 *ejecutada en la base de datos
     * @throws java.sql.SQLException
     * @throws java.lang.Exception
	 *
	 */
	
	public void ejecutarSQL(String sentencia) 
	throws SQLException,Exception{	
		st.execute(sentencia);	
            
	}
	
	/**
	 *Método que ejecuta una sentencia en la
	 *base de datos y devuelve un ResultSet
	 *con los resultados	 
	 *@param sentencia cadena sql que será
	 *ejecutada en la base de datos
	 *@param retorno booleana que indica que se
	 *desea un resultado de la consulta
     * @return 
     * @throws java.sql.SQLException 
     * @throws java.lang.Exception
	 */	
	public ResultSet ejecutarSQL(String sentencia,
	boolean retorno)
	throws SQLException,Exception{
		ResultSet rs;
		rs = st.executeQuery(sentencia);
		return rs;
	}
	
	/**
	 *Permite controlar el inicio una transacción 
	 *desde afuera.  A partir de este momento 
	 *todas las sentencias esperarán la orden para
	 *ser aceptadas en la base de datos
	 *
     * @throws java.sql.SQLException
	 */
	public void iniciarTransaccion()
	throws java.sql.SQLException{
		conn.setAutoCommit(false);	
	}
	
	/**
	 *Permite controlar el término una transacción 
	 *desde afuera.  A partir de este momento 
	 *todas las sentencias se ejecturán de forma
	 *individual en la base de datos
	 *
     * @throws java.sql.SQLException
	 */
	
	public void terminarTransaccion()
	throws java.sql.SQLException{
		conn.setAutoCommit(true);
	}
	
	/**
	 *Indica que la transacción ha sido aceptada
	 *
     * @throws java.sql.SQLException
	 */	
	
	public void aceptarTransaccion()
	throws java.sql.SQLException{
		conn.commit();
	}
	
	/**
	 *Indica que la transacción debe ser
	 *deshecha porque no se realizó de
	 *forma exitosa
	 *
     * @throws java.sql.SQLException
	 */	
	
	public void deshacerTransaccion()
	throws java.sql.SQLException{
		conn.rollback();	
	}
        
        public PreparedStatement obtenerSentenciaPreparada(String statement)
                throws java.sql.SQLException
        {
            return conn.prepareStatement(statement);
        }
	
	/**
	 *Método sobreescrito de la clase Object
	 *que es invocado por el Garbage Collector
	 *cuando es invocado libera la conexión
	 *abierta durante la creación del objeto
	 *
	 */
	protected void finalize(){
		try {
			conn.close();	
		}
		catch(Exception e){
			/*este método es llamado por el
			 *garbage collector, por lo tanto
			 *se atrapa la excepción pero no se
			 *reporta*/			
		}
	}				
}
package capaAccesoBD;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *Clase Conector
 *@version 1.0
 *@author Laura Monge Izaguirre
 *Clase que inicializa la conexi�n con
 *los valores correctos y
 *permite manejar una �nica
 *conexi�n para todo el proyecto y
 *
 */
 
public class Conector{
	//atributo de la clase	
	private static AccesoBD conectorBD = null;
	
	/**
	 *M�todo est�tico que devuelve el 
	 *objeto AccesoBD para que sea utilizado
	 *por las clases
	 *@return objeto del tipo AccesoBD del paquete 
	 *CapaAccesoDatos
     * @throws java.sql.SQLException
     * @throws java.lang.Exception
	 */
	 
//	public static AccesoBD getConector() throws 
//	java.sql.SQLException,Exception{
//		if (conectorBD == null){			
////			conectorBD = new AccesoBD("sun.jdbc.odbc.JdbcOdbcDriver","jdbc:odbc:BDCxC","sa","jass2002");
//			conectorBD = new AccesoBD("sun.jdbc.odbc.JdbcOdbcDriver","jdbc:odbc:BDCxCAccess","","");
//		}
//		return conectorBD;
//	}
//	
	
	public static AccesoBD getConector() throws java.sql.SQLException,Exception{
            String path = getDBPath();
            String driverPath = "jdbc:ucanaccess://" + path;
            
            File f = new File(path);
            if(!f.exists()) {
                String tempPath = path.replace("BDClinica", "BDClinicaTemplate");
                Files.copy(Paths.get(tempPath), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
            }
                    
            if(conectorBD==null){
                conectorBD = new AccesoBD("net.ucanaccess.jdbc.UcanaccessDriver", driverPath ); //c:/BaseDatos/BDCXC.mdb");
            }
            return conectorBD;
	}
	
    /**
     *
     * @return
     */
    public static String getDBPath()
        {
            String curDir = System.getProperty("user.dir").toString();
            curDir = curDir.replace("\\", "/");
            String path = curDir + "/databases/BDClinica.accdb";
            
            return path;
        }
}
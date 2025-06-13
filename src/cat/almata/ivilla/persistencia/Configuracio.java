package cat.almata.ivilla.persistencia;

/**
 * Clase Configuracio
 * Clase per a la configuracio de la conexio
 * amb la base de dades.
 * @author Ivan Villa
 * @version 1.0
 */

public class Configuracio {
	
	public static final String PROTOCOL = "jdbc:mysql://";
	public static final String IP_SERVIDOR = "127.0.0.1";
	public static final String PORT_BBDD = "3306";
	public static final String NOM_BBDD = "PuntsInteresCat";
	public static final String NO_USE_SSL = "?useSSL=false";
	public static final String USER_BBDD = "root";
	public static final String PASWORD_BBDD = "root";
	public static final String NOM_DRIVER_MYSQL = "com.mysql.cj.jdbc.Driver";
	
}

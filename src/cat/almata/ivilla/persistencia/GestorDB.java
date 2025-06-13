package cat.almata.ivilla.persistencia;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorDB {
	private static Connection connexioActual=null;
	/*
  public static void main(String[] args) {
		//Exemple d'ús. Es crida una consulta SQL a la BBDD i retorna una col·lecció d'objectes 
		//del tipus del segon paràmetre com a resposta. És fa el canvi de paradigma Relacional-OO.
		List<Usuari> usuaris = GestorDB.consultaDB("select * from usuari", Usuari.class);
		System.out.println(usuaris);
	}
	*/

	static {
		try {
			Class.forName(Configuracio.NOM_DRIVER_MYSQL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Connection getConnexio() {
		if(connexioActual==null) {
			try {
					connexioActual = DriverManager.getConnection(getCadenaConnexio(), Configuracio.USER_BBDD,
								Configuracio.PASWORD_BBDD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connexioActual;
	}
	
	private static String getCadenaConnexio() {
		return Configuracio.PROTOCOL + Configuracio.IP_SERVIDOR + ":" + Configuracio.PORT_BBDD + "/"
				+ Configuracio.NOM_BBDD;
	}
	
	private static void closeConnexio() {
		if(connexioActual!=null) {
			try {
				connexioActual.close();
				connexioActual=null;
			}catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}

	public static void modificaDB(String sql) throws GestioDBException {
		try (Statement sentencia = getConnexio().createStatement();
		) {
			sentencia.executeUpdate(sql);
		} catch ( SQLException e) {
			e.printStackTrace();
			throw new GestioDBException(e.getMessage());
		}finally {
			closeConnexio();
		}
	}

	public static <T> List<T> consultaDB(String sql, Class<T> classe) {
		List<T> llistaObjectes = null;
		try (  	Statement sentencia = getConnexio().createStatement();
				ResultSet files = sentencia.executeQuery(sql)) {
			llistaObjectes = resultSetToCollection(files, classe);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  finally {
			closeConnexio();
		}
		return llistaObjectes;
	}
	
	public static List<Map<String, Object>> consultaDB(String sql) {
	    List<Map<String, Object>> llistaObjectes = new ArrayList<>();
	    
	    try (Statement sentencia = getConnexio().createStatement();
	         ResultSet files = sentencia.executeQuery(sql)) {
	        
	        ResultSetMetaData metaData = files.getMetaData();
	        int columnCount = metaData.getColumnCount();
	        
	        while (files.next()) {
	            Map<String, Object> fila = new HashMap<>();
	            for (int i = 1; i <= columnCount; i++) {
	                String columnName = metaData.getColumnName(i);
	                Object value = files.getObject(i);
	                fila.put(columnName, value);
	            }
	            llistaObjectes.add(fila);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeConnexio();
	    }
	    
	    return llistaObjectes;
	}
	
	/*
	 * Per poder utilitzar aquesta classe, concretament "resultSetToCollection", que
	 * utilitza reflexió cal tenir en compte: 
	 * 1) La classe de domini del segon paràmetre ha de tenir el constructor buit. 
	 * 2) No estan controlats tots els tipus basics. Per exemple: si la classe té un 
	 * atribut de tipus char, no funcionaria. En tot cas es pot afegir el control del tipus "char". 
	 * 3) Tots els atributs han de tenir métodes accessors "set..." i "get..." excepte
	 * aquells atributs de tipus Objecte, col·lecció o Array. En aquests casos cal
	 * afegir els mètodes de negoci (addElement,remove,getElemet,...). Per exemple:
	 * en comptes de "set..." posar "add...". A més els atributs d'aquests darrers
	 * tipus s'han de persisitir de forma explícita en una altra consulta. El motiu
	 * és el canvi de paradigma POO->Model Relacional.
	 *
	 * Finalment comentar que es podria millorar el mètode "resultSetToCollection"
	 * per a que acceptés tots els tipus bàsics i potser també els atributs no
	 * simples i tractar-los en crides recursives al mateix mètode
	 * "resultSetToCollection". En tot cas queda pendent de fer....
	 */
	// El suppresswarnings és pel cast "objecte= (T) Class.forName..."
	@SuppressWarnings("unchecked")
	private static <T> List<T> resultSetToCollection(ResultSet files, Class<T> classe)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		Method[] metodes = null;
		Parameter[] p = null;
		T objecte = null;
		List<T> llistaObjectes = new ArrayList<T>();
		while (files.next()) {
			try {
				objecte = (T) Class.forName(classe.getName()).getDeclaredConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
			metodes = objecte.getClass().getDeclaredMethods();
			for (int i = 0; i < metodes.length; i++) {
				if (metodes[i].getName().startsWith("set")) {
					p = metodes[i].getParameters();
					switch (p[0].getType().getName()) {
					case "int":
						metodes[i].invoke(objecte, files.getInt(metodes[i].getName().substring(3)));
						break;
					case "boolean":
						metodes[i].invoke(objecte, files.getBoolean(metodes[i].getName().substring(3)));
						break;
					case "double":
						metodes[i].invoke(objecte, files.getDouble(metodes[i].getName().substring(3)));
						break;
					case "java.lang.String":
						metodes[i].invoke(objecte, files.getString(metodes[i].getName().substring(3).trim()));
						break;
					case "java.time.LocalDate":
						Date d = files.getDate(metodes[i].getName().substring(3));
						LocalDate ld = d.toLocalDate();
						metodes[i].invoke(objecte, ld);
						break;
					}
				}
			}
			llistaObjectes.add(objecte);
		}
		return llistaObjectes;
	}

}

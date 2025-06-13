package cat.almata.ivilla.presentacio;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import javax.swing.JInternalFrame;
import cat.almata.ivilla.domini.Desti;
import cat.almata.ivilla.domini.Pi;
import cat.almata.ivilla.persistencia.GestioDBException;
import cat.almata.ivilla.persistencia.HelperBBDDMem;

	/**
	 * Clase Controlador Presentacio
	 * Clase per a la comunicacio de dades amb la 
	 * clase encarregada de gestio de dades.
	 * @author Ivan Villa
	 * @version 1.1
	 * Registre de camvis:
	 * V1.0
	 */

public class ControladorPresentacio {
	
	private static Aplicacio aplicacio=null;
	private static JInternalFrame vistaActual=null;
	
	public static void crearAplicacio() {
		if(aplicacio==null) {
			aplicacio= new Aplicacio("Gestio de punts d'interes de Catalunya");
		}
	}
	
	public static void cambiVista (JInternalFrame vistaSeguent) {
		if(vistaActual!=null) vistaActual.dispose();
		aplicacio.getContentPane().add(vistaSeguent, BorderLayout.CENTER);
		aplicacio.setVisible(true);
		vistaActual=vistaSeguent;
	}
	
	/**
	 *Formulari Gestio destinacions
	 * @throws GestioDBException 
	 */
	
	public static void addDesti(
			String id,
			String provincia,
			String comarca,
			String poblacio,
			String CP,
			boolean esDePlatja,
			boolean esDeMontanya) throws GestioDBException {
		try {
			HelperBBDDMem.addDesti(
					new Desti(
							id,
							provincia,
							comarca,
							poblacio,
							CP,
							esDePlatja,
							esDeMontanya
					)
			);
		} catch (GestioDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); throw new GestioDBException(e.getMessage());
		}			
	}	
	
	/**
	 * Metodes de negoci de la taulaDestins.	
	 * @throws GestioDBException 
	 */

	public static Map<String, Desti> getDestins() throws GestioDBException{
		try {
			return HelperBBDDMem.getDestins();
		} catch (GestioDBException e) {
			e.printStackTrace(); throw new GestioDBException(e.getMessage());
		}
	}
	
	public static Desti getDesti(String key) throws GestioDBException {
		return HelperBBDDMem.getDesti(key);	
		
	}
	public static void removeDesti(String key) throws GestioDBException {
		try {
			HelperBBDDMem.removedesti(key);
		} catch (GestioDBException e) {
			e.printStackTrace(); throw new GestioDBException(e.getMessage());
		}
	}

	public static void modifyDesti(Desti desti) throws GestioDBException {
		try {
			HelperBBDDMem.modifydesti(desti);
		} catch (GestioDBException e) {
			e.printStackTrace(); throw new GestioDBException(e.getMessage());
		}
	}
	
	public static Map<String, Pi> getPisDestins(String id) throws GestioDBException{
		return HelperBBDDMem.getPiDesti(id);
	}
	
	/**
	 *Formulari Gestio Pi
	 * @throws GestioDBException 
	 */
	
	public static void addPi(
			String id,
			String nom,
			String descripcio,
			List<String> tipus,
			List<String> activitats,
			Desti desti,
			BufferedImage imatge, String nomImatge) throws GestioDBException {
		try {
			HelperBBDDMem.addPi(
					new Pi(id, 
						nom, 
						descripcio, 
						tipus, 
						activitats,
						desti,
						imatge,nomImatge)
					);
		} catch (GestioDBException e) {
			e.printStackTrace(); throw new GestioDBException(e.getMessage());
		}			
	}
	
	
	/**
	 * Metodes de negoci de la taulaPi.	
	 * @throws GestioDBException 
	 */
	
	public static Map<String, Pi> getPis() throws GestioDBException{
		try {
			return HelperBBDDMem.getPis();
		} catch (GestioDBException e) {
			e.printStackTrace(); throw new GestioDBException(e.getMessage());
		}
	}
	
	public static Pi getPi(String key) throws GestioDBException {
			try {
				return HelperBBDDMem.getPi(key);
			} catch (GestioDBException e) {
				e.printStackTrace(); throw new GestioDBException(e.getMessage());
			}	
		
	}
	public static void removePi(String key) throws GestioDBException {
		try {
			HelperBBDDMem.removePi(key);
		} catch (GestioDBException e) {
			e.printStackTrace(); throw new GestioDBException(e.getMessage());
		}
	}

	public static void modifyPi(Pi pi) throws GestioDBException {
		try {
			HelperBBDDMem.modifyPi(pi);
		} catch (GestioDBException e) {
			e.printStackTrace(); throw new GestioDBException(e.getMessage());
		}
	}
	
	public static JInternalFrame getVistaActual() {
		return vistaActual;
	}
	
	
	
}

package cat.almata.ivilla.persistencia;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;


import cat.almata.ivilla.domini.Desti;
import cat.almata.ivilla.domini.Pi;
import java.util.Map.Entry;

/**
 * Clase HelperBBDDMem
 * Clase per a la gestio de dades.
 * Conte simulació en un mapa
 * @author Ivan Villa
 * @version 1.2
 * Registre de camvis:
 * V1.1
 * Afegida llista de ids del punts d'interes.
 * V1.2
 * Modificacio per treballar amb Base de dades
 */


public class HelperBBDDMem {

	/**
	 * Metodes de negoci de la taulaDestins.	
	 */
	
	public static Map<String, Desti> getDestins() throws GestioDBException {
    List<Map<String, Object>> destins = GestorDB.consultaDB("SELECT * FROM Desti");
         Map<String, Desti> destiMap =  new TreeMap<String, Desti>();
	    for (Map<String, Object> destin : destins) {
	        Desti desti = new Desti();
	        desti.setId((String) destin.get("id"));
	        desti.setProvincia((String) destin.get("provincia"));
	        desti.setComarca((String) destin.get("comarca"));
	        desti.setCiutat((String) destin.get("ciutat"));
	        desti.setCP((String) destin.get("CP"));
	        desti.setEsDePlatja((Boolean) destin.get("esDePlatja"));
	        desti.setEsDeMontanya((Boolean) destin.get("esDeMontanya"));
	        if (destin.get("pis") != null) {
	            String pisString = (String) destin.get("pis");
	            List<String> pisList = Arrays.asList(pisString.split(","));
	            desti.setPis(pisList);
	        } else {
	            desti.setPis(new ArrayList<>());
	        }
	        destiMap.put(desti.getId(), desti);
	    }
		return destiMap;
		//return taulaDestins;
	}
	
	public static void addDesti(Desti desti) throws GestioDBException{
		int isEsDePlatja = 0; 
		int isEsDeMontanya = 0;
		if(desti.getEsDePlatja()==true)isEsDePlatja=1;
		if(desti.getEsDeMontanya()==true)isEsDeMontanya=1;
		GestorDB.modificaDB ("INSERT INTO Desti (id, provincia, comarca, ciutat, CP, esDePlatja, esDeMontanya) VALUES ('"+
				desti.getId()+"','"+
				desti.getProvincia()+"','"+
				desti.getComarca()+"','"+
				desti.getCiutat()+"','"+
				desti.getCP()+"','"+
				isEsDePlatja+"','"+
				isEsDeMontanya+"')");
		//taulaDestins.put(desti.getId(), desti);	
			}

	
	public static Desti getDesti(String key) throws GestioDBException {
	    List<Map<String, Object>> destins = GestorDB.consultaDB("SELECT * FROM Desti WHERE id='" + key + "'");
	    if (destins.isEmpty()) {
	        return null;
	    }
	    Map<String, Object> destin = destins.get(0);
	    Desti desti = new Desti();
	    desti.setId((String) destin.get("id"));
	    desti.setProvincia((String) destin.get("provincia"));
	    desti.setComarca((String) destin.get("comarca"));
	    desti.setCiutat((String) destin.get("ciutat"));
	    desti.setCP((String) destin.get("CP"));
	    desti.setEsDePlatja((Boolean) destin.get("esDePlatja"));
	    desti.setEsDeMontanya((Boolean) destin.get("esDeMontanya"));
	    if (destin.get("pis") != null) {
	        String pisString = (String) destin.get("pis");
	        List<String> pisList = Arrays.asList(pisString.split(","));
	        desti.setPis(pisList);
	    } else {
	        desti.setPis(new ArrayList<>());
	    }
	    return desti;
	}
	

	public static void modifydesti(Desti desti) throws GestioDBException {
		int isEsDePlatja = 0; 
		int isEsDeMontanya = 0;
		if(desti.getEsDePlatja()==true)isEsDePlatja=1;
		if(desti.getEsDeMontanya()==true)isEsDeMontanya=1;
		String pis = String.join(",", desti.getPis());
		GestorDB.modificaDB("UPDATE Desti SET id = '"+
				desti.getId()+"',provincia='"+
				desti.getProvincia()+"',comarca='"+
				desti.getComarca()+"',ciutat='"+
				desti.getCiutat()+"',CP='"+
				desti.getCP()+"',esDePlatja='"+
				isEsDePlatja+"',esDeMontanya='"+
				isEsDeMontanya+"',pis='"+
				pis.trim()	+"' WHERE id = '" +desti.getId()+"'");
		//taulaDestins.replace(desti.getId(),desti);
	}
	
	public static void removedesti(String key) throws GestioDBException{
		GestorDB.modificaDB("DELETE FROM Desti WHERE id =  "+ key);
		//taulaDestins.remove(key);	
	}
	
	public static Map<String, Pi> getPiDesti(String id) throws GestioDBException{
		Map<String, Pi> piDesti =new TreeMap<String, Pi>();		
		Map<String, Pi> TotsPis = getPis();
		for(Entry<String,Pi> key: TotsPis.entrySet()) {
			Pi pi = key.getValue();
			if(pi.getDesti().getId().equals(id)) {
				piDesti.put(pi.getId(), pi);
			}
		}
		return piDesti;
	}
	
	
	/**
	 * Metodes de negoci de la taulaPi.	
	 */

	public static Map<String, Pi> getPis() throws GestioDBException {
		List<Map<String, Object>> pins =  GestorDB.consultaDB("SELECT * FROM PuntInteres ");
		 Map<String, Pi> piMap =  new TreeMap<String, Pi>();
		   for (Map<String, Object> pin : pins) {
			   Pi pi = new Pi();
			pi.setId((String) pin.get("id"));
			pi.setNom((String) pin.get("nom"));
			pi.setDescripcio((String) pin.get("descripcio"));
			pi.setNomImatge((String) pin.get("imatge"));
	        if (pin.get("tipus") != null) {
	            String tipusString = (String) pin.get("tipus");
	            List<String> tipusList = Arrays.asList(tipusString.split(","));
	            pi.setTipus(tipusList);
	        } else {
	        	pi.setTipus(new ArrayList<>());
	        }
	        if (pin.get("activitats") != null) {
	            String activitatsString = (String) pin.get("activitats");
	            List<String> activitatsList = Arrays.asList(activitatsString.split(","));
	            pi.setActivitats(activitatsList);
	        } else {
	        	pi.setActivitats(new ArrayList<>());
	        }
	        String idDesti = (String) pin.get("desti");
	        Desti desti = new Desti(idDesti);
	        pi.setDesti(desti);
			pi.setNomImatge((String) pin.get("imatge"));
			String ruta= "imatges/";
			String nomImatge = pi.getNomImatge();
			if(nomImatge == null || nomImatge == "")nomImatge= "noimatge.jpg";
			File archiu = new File(ruta, nomImatge);
			BufferedImage imatge = null;
			try {
			imatge = ImageIO.read(archiu);
			} catch (IOException e) {
				e.printStackTrace();
			}
			pi.setImatge(imatge);
			piMap.put(pi.getId(), pi);	
		   }
			return piMap;
		//return taulaPis;
	}
	
 	public static void addPi(Pi pi) throws GestioDBException {
	String tipus = String.join(",", pi.getTipus());
	String activitats = String.join(",", pi.getActivitats());
	String idDesti = pi.getDesti().getId();
	String nomImatge = pi.getNomImatge();
	if(nomImatge == null || nomImatge == "")nomImatge= "noimatge.jpg";
	GestorDB.modificaDB ("INSERT INTO PuntInteres (id, nom, descripcio, tipus, activitats, desti, imatge) VALUES ('"+
			pi.getId()+"','"+
			pi.getNom()+"','"+
			pi.getDescripcio()+"','"+
			tipus+"','"+
			activitats+"','"+
			idDesti+"','"+
			nomImatge+"')");
	//Desti desti = ControladorPresentacio.getDesti(idDesti);
	//desti.afegirPi(pi.getId());
	HelperBBDDMem.modifydesti(pi.getDesti());
	}
 	
 	
	public static Pi getPi (String key)throws GestioDBException {
	    List<Map<String, Object>> punts = GestorDB.consultaDB("SELECT * FROM PuntInteres WHERE id='" + key + "'");
	    if (punts.isEmpty()) {
	        return null;
	    }
	    Map<String, Object> punt = punts.get(0);
			   Pi pi = new Pi();
			pi.setId((String) punt.get("id"));
			pi.setNom((String) punt.get("nom"));
			pi.setDescripcio((String) punt.get("descripcio"));
			pi.setNomImatge((String) punt.get("imatge"));
	        if (punt.get("tipus") != null) {
	            String tipusString = (String) punt.get("tipus");
	            List<String> tipusList = Arrays.asList(tipusString.split(","));
	            pi.setTipus(tipusList);
	        } else {
	        	pi.setTipus(new ArrayList<>());
	        }
	        if (punt.get("activitats") != null) {
	            String activitatsString = (String) punt.get("activitats");
	            List<String> activitatsList = Arrays.asList(activitatsString.split(","));
	            pi.setActivitats(activitatsList);
	        } else {
	        	pi.setActivitats(new ArrayList<>());
	        }
	        String idDesti = (String) punt.get("desti");
	        Desti desti = new Desti(idDesti);
	        pi.setDesti(desti);
			pi.setNomImatge((String) punt.get("imatge"));
			String ruta= "imatges/";
			String nomImatge = pi.getNomImatge();
			if(nomImatge == null || nomImatge == "")nomImatge= "noimatge.jpg";
			File archiu = new File(ruta, nomImatge);
			BufferedImage imatge = null;
			try {
			imatge = ImageIO.read(archiu);
			} catch (IOException e) {
				e.printStackTrace();
			}
			pi.setImatge(imatge);
		   return pi;
    }
	
	public static void modifyPi(Pi pi) throws GestioDBException {
		System.out.println(pi);
		String tipus = String.join(",", pi.getTipus());
		String activitats = String.join(",", pi.getActivitats());
		String idDesti = pi.getDesti().getId();
		GestorDB.modificaDB ("UPDATE PuntInteres Set id = '"+ 
		pi.getId()+"', nom='"+
		pi.getNom()+"', descripcio= '"+
		pi.getDescripcio()+"', tipus='"+
		tipus+"',activitats= '"+
		activitats+"',desti='"+
		idDesti+"',imatge='"+
		pi.getNomImatge()+"' WHERE id= '"+ pi.getId()+"'");
		}
	
	
	public static void removePi (String key) throws GestioDBException {
		GestorDB.modificaDB("DELETE FROM PuntInteres WHERE id =  '"+ key +"'");
		//taulaPis.remove(key);	
	}
		
	
}

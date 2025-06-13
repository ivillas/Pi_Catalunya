package cat.almata.ivilla.domini;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase del punt d'interes
 * Conte tots els atributs del desti.
 * @author Ivan Villa
 * @version 1.0
 */

public class Pi {

	/**
	 * Declaracio dels atributs
	 * VISTA
	 */
	
	private String id;
	private String nom;
	private String descripcio;
	private List<String> tipus;
	private List<String> Activitats;
	private Desti desti;
	private BufferedImage imatge;
	private String nomImatge;
	
	public String getNomImatge() {
		return nomImatge;
	}


	public void setNomImatge(String nomImatge) {
		this.nomImatge = nomImatge;
	}


	public void setTipus(List<String> tipus) {
		this.tipus = tipus;
	}


	public void setActivitats(List<String> activitats) {
		Activitats = activitats;
	}


	/**
	 * Constructors amb paremetres i sense parametres	
	 */
	
	public Pi(BufferedImage imatge) {
		super();
		this.imatge = imatge;
	}


	public Pi(String id) {
		super();
		this.id = id;
	}


	public Pi() {
		super();
	}


	public Pi(String id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}


	public Pi(String id, String nom, String descripcio, List<String> tipus, List<String> activitats, Desti desti,
			BufferedImage imatge, String nomImatge) {
		super();
		this.id = id;
		this.nom = nom;
		this.descripcio = descripcio;
		this.tipus = tipus;
		this.Activitats = activitats;
		this.desti = desti;
		this.imatge = imatge;
		this.nomImatge = nomImatge;
	}




	public Pi(String nom, String descripcio, List<String> tipus, List<String> activitats, Desti desti) {
		super();
		this.nom = nom;
		this.descripcio = descripcio;
		this.tipus = tipus;
		Activitats = activitats;
		this.desti = desti;
	}


	public Pi(String id, String nom, String descripcio, List<String> tipus, List<String> activitats) {
		super();
		this.id = id;
		this.nom = nom;
		this.descripcio = descripcio;
		this.tipus = tipus;
		Activitats = activitats;
	}

	public Pi(String nom, String descripcio, List<String> tipus, List<String> activitats, Desti desti,
			BufferedImage imatge) {
		super();
		this.nom = nom;
		this.descripcio = descripcio;
		this.tipus = tipus;
		Activitats = activitats;
		this.desti = desti;
		this.imatge = imatge;
	}
	
	public Pi(String id, String nom, String descripcio, List<String> tipus, List<String> activitats,
			BufferedImage imatge) {
		super();
		this.id = id;
		this.nom = nom;
		this.descripcio = descripcio;
		this.tipus = tipus;
		Activitats = activitats;
		this.imatge = imatge;
	}

	
	/**
	 * Getters & Setters 
	 * Per a gestionar les dades dels parametres
	 */

	public String getId() {
		return id;
	}

	public void addDesti(Desti desti) {
		this.desti=desti;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getDescripcio() {
		return descripcio;
	}


	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}


	public List<String> getTipus() {
		return tipus;
	}


	public void setTipus(ArrayList<String> tipus) {
		this.tipus = tipus;
	}


	public List<String> getActivitats() {
		return Activitats;
	}


	public void setActivitats(ArrayList<String> activitats) {
		Activitats = activitats;
	}


	public Desti getDesti() {
		return desti;
	}


	public void setDesti(Desti desti) {
		this.desti = desti;
	}
	
	
	public BufferedImage getImatge() {
		return imatge;
	}


	public void setImatge(BufferedImage imatge) {
		this.imatge = imatge;
	}

    /**
     * Calcula el hash del Pi basanse amb l'id
     * @return el hash del Pi
     */

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

    /**
     * Metode per comparar aquet Pi amb un altre objecte
     * @param obj del objecte a comparar
     * @return true si son iguals, false en cas de ser diferents
     */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pi other = (Pi) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Pi [id=" + id + ", nom=" + nom + ", descripcio=" + descripcio + ", tipus=" + tipus + ", Activitats="
				+ Activitats + ", desti=" + desti + ", imatge=" + imatge + ", nomImatge=" + nomImatge + "]";
	}

    /**
     * Metode per retornar una representacio del Pi.
     * @return una cadena que representa el Pi
     */
	



	
	
	
	
	
	
	
}

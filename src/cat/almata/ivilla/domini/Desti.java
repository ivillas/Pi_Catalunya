package cat.almata.ivilla.domini;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

	/**
	 * Clase del desti
	 * Conte tots els atributs del desti.
	 * @author Ivan Villa
	 * @version 1.1
	 * Registre de canvis:
	 * V1.1
	 * Afegida llista de ids del punts d'interes.
	 */


public class Desti {

	/**
	 * Declaracio dels atributs
	 * VISTA
	 */
	
	private String id;
	private String provincia;
	private String comarca;
	private String ciutat;
	private String CP;
	private boolean esDePlatja;
	private boolean esDeMontanya;
	private List<String> pis = new ArrayList<>();
	
	/**
	 * Constructors amb paremetres i sense parametres	
	 */
	
		public Desti() {
		super();
	}

	public Desti(String id) {
		super();
		this.id = id;
	}

	public Desti(String provincia, String comarca, String poblacio, String cP, boolean esDePlatja,
			boolean esDeMontanya) {
		super();
		this.provincia = provincia;
		this.comarca = comarca;
		this.ciutat = poblacio;
		CP = cP;
		this.esDePlatja = esDePlatja;
		this.esDeMontanya = esDeMontanya;
	}

	public Desti(String id, String provincia, String comarca, String poblacio, String cP, boolean esDePlatja,
			boolean esDeMontanya) {
		super();
		this.id = id;
		this.provincia = provincia;
		this.comarca = comarca;
		this.ciutat = poblacio;
		CP = cP;
		this.esDePlatja = esDePlatja;
		this.esDeMontanya = esDeMontanya;
	}
	

	/**
	 * Getters & Setters 
	 * Per a gestionar les dades dels parametres
	 */
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getComarca() {
		return comarca;
	}

	public void setComarca(String comarca) {
		this.comarca = comarca;
	}

	public String getPoblacio() {
		return ciutat;
	}

	public void setPoblacio(String poblacio) {
		this.ciutat = ciutat;
	}

	public String getCiutat() {
		return ciutat;
	}

	public void setCiutat(String ciutat) {
		this.ciutat = ciutat;
	}

	public String getCP() {
		return CP;
	}

	public void setCP(String cP) {
		CP = cP;
	}

	public boolean getEsDePlatja() {
		return esDePlatja;
	}

	public void setEsDePlatja(boolean esDePlatja) {
		this.esDePlatja = esDePlatja;
	}

	public boolean getEsDeMontanya() {
		return esDeMontanya;
	}

	public void setEsDeMontanya(boolean esDeMontanya) {
		this.esDeMontanya = esDeMontanya;
	}

	public List<String> getPis() {
		return pis;
	}

	public void setPis(List<String> pis) {
		 this.pis.clear();
		  this.pis.addAll(pis);
	}
	
	
    /**
     * Calcula el hash del desti basanse amb l'id
     * @return el hash del desti
     */

	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

    /**
     * Metode per comparar aquet desti amb un altre objecte
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
		Desti other = (Desti) obj;
		return Objects.equals(id, other.id);
	}

    /**
     * Metode per retornar una representacio del desti.
     * @return una cadena que representa el desti
     */

	@Override
	public String toString() {
		return "Desti [id=" + id + ", provincia=" + provincia + ", comarca=" + comarca + ", ciutat=" + ciutat + ", CP="
				+ CP + ", esDePlatja=" + esDePlatja + ", esDeMontanya=" + esDeMontanya + ", pis=" + pis + "]";
	}
	
		
	/**
	 * Metode per afegir a la llista pis un punt d'interes
	 * S'afegeig l'id del pun d'interes i sutilitza per eliminar els Pi al eliminar el desti
	 * @param id
	 */
	
	 public void afegirPi(String id) {
		pis.add(id);
	    }
	
}

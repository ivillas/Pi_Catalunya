package cat.almata.ivilla;


import cat.almata.ivilla.presentacio.ControladorPresentacio;
import cat.almata.ivilla.presentacio.VistaDefault;

/**
 * Clase main per executar l'aplicació
 * Executa la vista default
 * @author Ivan Villa
 * @version 1.0
 */

public class Run {

	public static void main(String[] args) {

		ControladorPresentacio.crearAplicacio();
		ControladorPresentacio.cambiVista(new VistaDefault());
		
		

	}

}

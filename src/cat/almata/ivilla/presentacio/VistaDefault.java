package cat.almata.ivilla.presentacio;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import cat.almata.ivilla.utils.Util;

/**
 * VistaDefault
 * Clase principal que nomes contendra la imatge de fons
 * atributs, metodes de negoci...
 * @author Ivilla
 * @version 1.1
 * Registre de canvis:
 * V1.1
 * Actualitzar metode Alta() per asignar el id del combobox
 */

public class VistaDefault  extends JInternalFrame implements Formulari {
	

	/**
	 * Declaracio dels atributs
	 * VISTA
	 */
	
	private GridBagLayout layout;
	private JLabel imageFons;
	
	/**
	 * @serial VersionUID
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 */
	
	public VistaDefault() {
		inicialitzacions();
		crearComponents();
		afegirComponents();
		posicionarComponents();
	}
	
	/**
	 * Metode Sobreescrit de Formulari
	 * per inicialitzar la vista 
	 */
	
	@Override
	public void inicialitzacions() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(layout = new GridBagLayout());
		Util.treureBarraTitolInteralFrame(this);	
		this.setVisible(true);
	}
	
	@Override
	public void crearComponents() {
		imageFons =new JLabel(Util.redimensionarImatge("imatges/fons.png",600, 600));	

	}

	/**
	 * Metode Sobreescrit de Formulari
	 * per crear els components que nececite la vista la vista 
	 */
	
	@Override
	public void afegirComponents() {
		add(imageFons);

	}
	
	/**
	 * Metode Sobreescrit de Formulari
	 * per posicionar els components necesaris a la vista 
	 */
	
	@Override
	public void posicionarComponents() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets= new Insets(5,5,5,5);
		layout.setConstraints(imageFons, gbc);
	}

}


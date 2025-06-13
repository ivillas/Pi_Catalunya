package cat.almata.ivilla.presentacio;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import cat.almata.ivilla.utils.Fonts;
import cat.almata.ivilla.utils.Util;

/**
 * CLase VistaInfo
 * Clase per mostras informació de la aplicació
 * inclou informació i enllaç al document javaDoc
 * @author Ivilla
 * @version 1.2
 * Registre de canvis:
 * V1.1
 * Actualització de la versió
 * V1.2
 * Actualització de la versió
 */

public class VistaInfo extends JInternalFrame  implements Formulari {
	
	/**
	 * Declaracio de les constants d'informació
	 * MODEL
	 */
	
	private static final String TITOL="Informació de la aplicació";
	private static final String LBL_TITOL="Informació de la aplicació";
	private static final String LBL_DESCRIPCIO="Aplicació per a la gestio dels punts d'interes de Catalunya";
	private static final String LBL_INFO="Pràctica DAX - RA6, RA5.6-5.8, RA1-Mòdul optatiu";
	private static final String LBL_ENTITAT="Institut Almata";
	private static final String LBL_VERSIO="Versio: 1.3";
	private static final String LBL_AUTOR="Autor: Ivan Villa";
	private static final String LBL_PEU="";
	private static final String LBL_ACEPTAR="Aceptar";
	
	/**
	 * @serial VersionUID
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * Declaracio dels atributs
	 * VISTA
	 */
	
	private GridBagLayout layout;
	private Controlador controlador;
	private JLabel lblTitol;
	private JLabel lblDescripcio;
	private JLabel lblInfo;
	private JLabel lblEntitat;
	private JLabel lblVersio;
	private JLabel lblAutor;
	private JLabel lblPeu;
	private JButton btnAceptar;
	
	/**
	 * Constructor
	 */

	public VistaInfo() {
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
		this.setTitle(TITOL);
		getContentPane().setLayout(layout = new GridBagLayout());
		controlador  = new Controlador();	
		Util.treureBarraTitolInteralFrame(this);
	    setVisible(true);
	}

	/**
	 * Metode Sobreescrit de Formulari
	 * per crear els components que nececite la vista la vista 
	 */
	
	@Override
	public void crearComponents() {
		lblTitol = new JLabel(LBL_TITOL);
		lblTitol.setFont(Fonts.fontLabel());
		
		lblDescripcio = new JLabel(LBL_DESCRIPCIO);
		lblDescripcio.setFont(Fonts.fontLabel());
		
		lblInfo = new JLabel(LBL_INFO);
		lblInfo.setFont(Fonts.fontLabel());
					
		lblEntitat = new JLabel(LBL_ENTITAT);
		lblEntitat.setFont(Fonts.fontLabel());
		
		lblVersio = new JLabel(LBL_VERSIO);
		lblVersio.setFont(Fonts.fontLabel());
		
	    lblAutor = new JLabel(LBL_AUTOR);
	    lblAutor.setFont(Fonts.fontLabel());

	    lblPeu = new JLabel(LBL_PEU);
	    lblPeu.setFont(Fonts.fontLabel());

		btnAceptar = new JButton(LBL_ACEPTAR);
		btnAceptar.addActionListener(controlador);
		btnAceptar.setActionCommand("aceptar");
	}

	/**
	 * Metode Sobreescrit de Formulari
	 * per afegir els components necesaris a la vista 
	 */	

	@Override
	public void afegirComponents() {
				
		getContentPane().add(lblTitol);
		getContentPane().add(lblDescripcio);
		getContentPane().add(lblInfo);
		getContentPane().add(lblEntitat);
		getContentPane().add(lblVersio);
		getContentPane().add(lblAutor);
		getContentPane().add(lblPeu);
		getContentPane().add(btnAceptar);

	}
	
	/**
	 * Metode Sobreescrit de Formulari
	 * per posicionar els components necesaris a la vista 
	 */

	@Override
	public void posicionarComponents() {
		GridBagConstraints gbc = new GridBagConstraints();
			
		//lblTitol
		gbc.gridx = 2; 
		gbc.gridy = 1; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets= new Insets(5,5,5,5);
		layout.setConstraints(lblTitol, gbc);

		//lblDescripcio
		gbc.gridx =2; 
		gbc.gridy =5;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(lblDescripcio, gbc);		
		
		//lblInfo
		gbc.gridx = 2; 
		gbc.gridy = 7;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(lblInfo, gbc);
				
		//lblEntitat
		gbc.gridx = 2;
		gbc.gridy =9;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(lblEntitat, gbc);
						
		//lblVersio
		gbc.gridx = 2;
		gbc.gridy = 11; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(lblVersio, gbc);
	
		//lblAutor
		gbc.gridx = 2; 
		gbc.gridy = 13; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(lblAutor, gbc);
		
		//lblPeu
		gbc.gridx = 2;
		gbc.gridy = 15; 
		gbc.gridheight = 3;
		gbc.gridwidth = 3;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(lblPeu, gbc);
			
		//btnAceptar
		gbc.gridx = 2; 
		gbc.gridy = 25;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(btnAceptar, gbc);		
		
	}
	
	/**
	 * Clase interna Controlador
	 * Per gestionar les accions del usuari sobre la vista
	 */
	
	private class Controlador extends MouseAdapter implements ActionListener{
		   
		/**
		 * Metode Sobreescrit actionPerformed
		 * controlara els events dels botons.
		 * @since en aquet metode s'ha modificat el boto obrir per que directament obri la carpeta /imatges
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj instanceof JButton) {
				JButton boto = (JButton) obj;
				if(boto.getActionCommand().equals("aceptar")) {
					ControladorPresentacio.cambiVista(new VistaDefault());
				}
			}
		}
	}
	
}

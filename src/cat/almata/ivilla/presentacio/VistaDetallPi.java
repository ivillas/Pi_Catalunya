package cat.almata.ivilla.presentacio;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import cat.almata.ivilla.domini.Pi;
import cat.almata.ivilla.utils.Fonts;

/**
 * Clase VistaDetallPi
 * Clase per mostrar les dades complertes del Punt d'interes
 * @author Ivilla
 * @version 1.1
 * Ajusta reescalat de imatge
 */

public class VistaDetallPi extends JDialog implements Formulari{

	/**
	 * @serial VersionUID
	 */
	
	private static final long serialVersionUID = 1L;

	/**
	 * Declaracio de les constants d'informació
	 * MODEL
	 */
	
	private static final String LBL_TITOL="Dades del Punt d'interes";
	private static final String LBL_DESTI="Ciutat/Poblacio on esta situat:";
	private static final String LBL_ID="Id:";
	private static final String TXT_ID="Introdueix l'ID del punt d'interes";
	private static final String LBL_NOM="Nom:";
	private static final String TXT_NOM="Aquets son els punts d'interes:";
	private static final String LBL_DESCRIPCIO="Descripció:";
	private static final String LBL_TIPUS="Interes que hi podem trobar:";
	private static final String LBL_ACTIVITATS="Aquestes activitas hi podem trobar:";
	private static final String LBL_ACEPTAR="Aceptar";
	protected static final int MAX_ID = 4;
	protected static final int MAX_NOM = 50;
	protected static final int MAX_DESCRIPCIO = 500;

	/**
	 * Declaracio dels atributs
	 * VISTA
	 */
	
	private GridBagLayout layout;
	private Controlador controlador;
	private JLabel lblTitol;
	private JLabel lblDesti;
	private JLabel lblDesti2;
	private JLabel lblId;
	private JTextField txtId;
	private JLabel lblNom;
	private JTextField txtNom;
	private JLabel lblDescripcio;
	private JTextArea txaDescripcio;
	private JScrollPane txaDescripcioScroll;
	private JLabel lbltipus;
	JList<String> llistaActivitats;
	private JScrollPane txallistaActivitats;
	JList<String> llistaTipus;
	private JScrollPane txallistaTipus;
	private JLabel lblactivitats;
	private JLabel lblimatge;
	public  JButton btnImatge;
	private JButton btnAceptar;
	private JPanel pnlBotons;
	private JDialog VistaDetallPi;
	private Pi pi;
	
	/**
	 * Constructor
	 */
	
	public VistaDetallPi(Pi pi) {
		this.pi=pi;
		inicialitzacions();
		crearComponents();
		afegirComponents();
		posicionarComponents();
		pack();
		setLocationRelativeTo(ControladorPresentacio.getVistaActual());	
		this.setVisible(true);
	
	}
	
	/**
	 * Metode Sobreescrit de Formulari
	 * per inicialitzar la vista 
	 */
		
	@Override
	public void inicialitzacions() {
		getContentPane().setLayout(layout = new GridBagLayout());
		this.setBounds(0, 0, 800, 800);
		controlador = new Controlador();	
		VistaDetallPi=this;
	  	this.setUndecorated(true);
		setModal(true);
		Container c= this.getContentPane();
		((JComponent) c).setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(
				Color.BLACK),
				LBL_TITOL, 
				TitledBorder.LEFT, 
				TitledBorder.DEFAULT_JUSTIFICATION, 
				Fonts.fontTitol(), 
				Color.BLUE)
			  );

	}
	
	/**
	 * Metode Sobreescrit de Formulari
	 * per crear els components que nececite la vista la vista 
	 */
	
	@Override
	public void crearComponents() {
		lblTitol = new JLabel(LBL_TITOL);
		lblTitol.setFont(Fonts.fontLabel());
		
		lblDesti = new JLabel(LBL_DESTI);
		lblDesti.setFont(Fonts.fontLabel());
		lblDesti2 = new JLabel(pi.getDesti().getPoblacio());
		lblDesti2.setFont(Fonts.fontLabel());
		
		lblId = new JLabel(LBL_ID);
		lblId.setFont(Fonts.fontLabel());
		txtId = new JTextField();
		txtId.setToolTipText(TXT_ID);
		txtId.setText(pi.getId());
		txtId.setEnabled(false);
					
		lblNom = new JLabel(LBL_NOM);
		lblNom.setFont(Fonts.fontLabel());
		txtNom = new JTextField();
		txtNom.setToolTipText(TXT_NOM);
		txtNom.setText(pi.getNom());
		txtNom.setEnabled(false);
		
		lblDescripcio = new JLabel(LBL_DESCRIPCIO);
		lblDescripcio.setFont(Fonts.fontLabel());
		txaDescripcio =new JTextArea();
		txaDescripcio.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txaDescripcio.setLineWrap(true);
		txaDescripcio.setWrapStyleWord(true);
		txaDescripcio.setText(pi.getDescripcio());
		txaDescripcio.setEnabled(false);
		
		txaDescripcioScroll = new JScrollPane(txaDescripcio);
		txaDescripcioScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		txaDescripcioScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
		lbltipus = new JLabel(LBL_TIPUS);
		lbltipus.setFont(Fonts.fontLabel());
		
	    List<String> tipus = pi.getTipus();
	    String[] arrayTipus = tipus.toArray(new String[0]);
	    llistaTipus = new JList<>(arrayTipus);
	    llistaTipus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    llistaTipus.setEnabled(false);
	    
		txallistaTipus = new JScrollPane(llistaTipus);
		txallistaTipus.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		txallistaTipus.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		lblactivitats = new JLabel(LBL_ACTIVITATS);
		lblactivitats.setFont(Fonts.fontLabel());
	    
	    List<String> activitats = pi.getActivitats();
	    String[] arrayActivitats = activitats.toArray(new String[0]);
	    llistaActivitats = new JList<>(arrayActivitats);
	    llistaActivitats.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    llistaActivitats.setEnabled(false);
	  	    
		txallistaActivitats = new JScrollPane(llistaActivitats);
		txallistaActivitats.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		txallistaActivitats.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
	    lblimatge = new JLabel();
	    lblimatge.setFont(Fonts.fontLabel());
	    try {
	        ImageIcon imatge = new ImageIcon(pi.getImatge());
	        Image img = imatge.getImage();
	        Image imatgeRescalada = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
	        lblimatge.setIcon(new ImageIcon(imatgeRescalada));
	    } catch (Exception e) {
	        lblimatge.setText("No hi ha cap imatge ");
	    }
		
		btnAceptar = new JButton(LBL_ACEPTAR);
		btnAceptar.addActionListener(controlador);
		btnAceptar.setActionCommand("aceptar");
	
		pnlBotons = new JPanel();
		pnlBotons.add(btnAceptar);
	}
		
	/**
	 * Metode Sobreescrit de Formulari
	 * per afegir els components necesaris a la vista 
	 */	
	
	@Override
	public void afegirComponents() {
		getContentPane().add(lblDesti);
		getContentPane().add(lblDesti2);
		getContentPane().add(lblId);
		getContentPane().add(txtId);
		getContentPane().add(lblNom);
		getContentPane().add(txtNom);
		getContentPane().add(lblimatge);
		getContentPane().add(lblDescripcio);
		getContentPane().add(txaDescripcioScroll);
		getContentPane().add(lbltipus);
		getContentPane().add(txallistaTipus);
		getContentPane().add(lblactivitats);
		getContentPane().add(txallistaActivitats);
		getContentPane().add(btnAceptar);

	}

	/**
	 * Metode Sobreescrit de Formulari
	 * per posicionar els components necesaris a la vista 
	 */

	@Override
	public void posicionarComponents() {
		GridBagConstraints gbc = new GridBagConstraints();
				
		//lblDesti
		gbc.gridx = 0;
		gbc.gridy = 0; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets= new Insets(5,5,5,5);
		layout.setConstraints(lblDesti, gbc);

		//lblDesti2
		gbc.gridx = 1;
		gbc.gridy = 0; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(lblDesti2, gbc);
		
		
		//lblid
		gbc.gridx = 0; 
		gbc.gridy = 1; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets= new Insets(5,5,5,5);
		layout.setConstraints(lblId, gbc);
				
		//txtId
		gbc.gridx = 1;
		gbc.gridy = 1; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(txtId, gbc);
						
		//lblNom
		gbc.gridx = 0; 
		gbc.gridy = 2; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets= new Insets(5,5,5,5);
		layout.setConstraints(lblNom, gbc);
	
		//txtNom
		gbc.gridx = 1; 
		gbc.gridy = 2; 
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(txtNom, gbc);
		
		//lblimatge
		gbc.gridx = 0; 
		gbc.gridy = 3; 
		gbc.gridheight = 3;
		gbc.gridwidth = 3;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		layout.setConstraints(lblimatge, gbc);
		
		//lblDescripcio
		gbc.gridx = 0; 
		gbc.gridy = 6;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(lblDescripcio, gbc);

		//txaDescripcioScroll
		gbc.gridx = 0; 
		gbc.gridy = 7; 
		gbc.gridheight = 4;
		gbc.gridwidth = 3;
		gbc.weightx = 1; 
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		layout.setConstraints(txaDescripcioScroll, gbc);

		
		//lbltipus
		gbc.gridx = 0; 
		gbc.gridy = 11; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(lbltipus, gbc);
		
		//llistaTipus
		gbc.gridx = 0; 
		gbc.gridy = 13; 
		gbc.gridheight = 3;
		gbc.gridwidth = 3;
		gbc.weightx = 1; 
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		layout.setConstraints(txallistaTipus, gbc);
			
		//lblactivitats
		gbc.gridx = 0; 
		gbc.gridy = 16;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(lblactivitats, gbc);
		
		//llistaActivitats
		gbc.gridx = 0; 
		gbc.gridy = 17; 
		gbc.gridheight = 3;
		gbc.gridwidth = 3;
		gbc.weightx = 1; 
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		layout.setConstraints(txallistaActivitats, gbc);

		//pnlBotons
		gbc.gridx = 1; 
		gbc.gridy = 20; 
		gbc.gridheight = 3;
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

	private class Controlador implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			VistaDetallPi.dispose();
			
		}
	}
}


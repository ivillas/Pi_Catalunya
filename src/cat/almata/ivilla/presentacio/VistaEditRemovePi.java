package cat.almata.ivilla.presentacio;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;
import cat.almata.ivilla.domini.Desti;
import cat.almata.ivilla.domini.Pi;
import cat.almata.ivilla.persistencia.GestioDBException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import cat.almata.ivilla.utils.Fonts;
import cat.almata.ivilla.utils.Util;

/**
 * Clase que conte els metodes per modificar i eliminar Punts d'interes
 * atributs, metodes de negoci...
 * @author Ivilla
 * @version 1.1
 * Registre de canvis:
 * V1.2
 * Afegit metode idfinal() per retornar el id desde una funció i no en cada solicitud
 * Actualitzat la forma de guardar imatge, ara tambe es guarda el nom en un atribut
 */

public class VistaEditRemovePi extends JInternalFrame implements Formulari{
	
	/**
	 * Declaracio de les constants d'informació
	 * MODEL
	 */
	
	private static final String TITOL="Dades del Punt d'interes";
	private static final String LBL_TITOL="Dades dels punts d'interes";
	private static final String LBL_TITOL2="Editant punt d'interes";
	private static final String LBL_DESTI="Ciutat/Poblacio on esta situat:";
	private static final String LBL_ID="Id:";
	private static final String TXT_ID="Introdueix l'ID del punt d'interes";
	private static final String LBL_NOM="Nom:";
	private static final String TXT_NOM="Introdueix el nom del punt d'interes:";
	private static final String LBL_DESCRIPCIO="Descripció:";
	private static final String LBL_TIPUS="Elegeix els tipus d'interes:";
	private static final String LBL_IMATGE="Tria una imatge:";
	private static final String LBL_IMATGE2="Cap imatge";
	private static final String LBL_OBRIR="Obrir";
	private static final String LBL_ACEPTAR="Aceptar";
	private static final String LBL_CANCELAR="Sortir";
	private static final String LBL_GASTRONOMIC="Gastronòmic";
	private static final String LBL_CULTURAL="Cultural";
	private static final String LBL_OCI="Oci";
	private static final String LBL_ESPORT="Esport";
	private static final String LBL_PLATJA="Platja";
	private static final String LBL_MUNTANYA="Muntanya";
	private static final String LBL_RURAL="Rural";
	private static final String LBL_ESQUI="Esquí";
	private static final String LBL_NATACIO="Natació";
	private static final String LBL_ESCALADA="Escalada";
	private static final String LBL_COMPRES="Compres";
	private static final String LBL_EQUITACIO="Equitació";
	private static final String LBL_SENDERISME="Senderisme";
	protected static final int MAX_ID = 4;
	protected static final int MAX_NOM = 50;
	protected static final int MAX_DESCRIPCIO = 500;
 
	
	
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
	private JLabel lblDesti;
	private JTextField txtDesti;
	private JComboBox<String> cmbPis;
	private JButton btnEditar;
	private JButton btnEsborrar;
	private JLabel lblId;
	private JTextField txtId;
	private JLabel lblNom;
	private JTextField txtNom;
	private JLabel lblDescripcio;
	private JTextArea txaDescripcio;
	private JScrollPane txaDescripcioScroll;
	private JLabel lbltipus;
	private JCheckBoxMenuItem itemGastronomic;
	private JCheckBoxMenuItem itemCultural;
	private JCheckBoxMenuItem itemOci;
	private JCheckBoxMenuItem itemEsport;
	private JCheckBoxMenuItem itemPlatja;
	private JCheckBoxMenuItem itemMuntanya;
	private JCheckBoxMenuItem itemRural;
	private JLabel lblactivitats;
	private JCheckBoxMenuItem itemEsqui;
	private JCheckBoxMenuItem itemNatacio;
	private JCheckBoxMenuItem itemEscalada;
	private JCheckBoxMenuItem itemCompres;
	private JCheckBoxMenuItem itemEquitacio;
	private JCheckBoxMenuItem itemSenderisme;
	private JLabel lblimatge;
	private JLabel lblimatge2;
	public  JButton btnImatge;
	private JButton btnAcceptar;
	private JButton btnCancelar;
	private JPanel pnlBotons;
	private JInternalFrame VistaEditRemovePi;
	private BufferedImage imatge;
	private JPanel pnlBotons2;
	private String nomImatge;
	private VistaEditRemovePi vistaEditRemovePi;
	
	/**
	 * Constructor
	 */

	public VistaEditRemovePi() {
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
		getContentPane().setLayout(layout=new GridBagLayout());
		controlador= new Controlador();
		this.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createLineBorder(
						Color.BLUE),
						LBL_TITOL, 
						TitledBorder.LEFT, 
						TitledBorder.DEFAULT_JUSTIFICATION, 
						Fonts.fontTitol(), 
						Color.BLUE)
					  );
		Util.treureBarraTitolInteralFrame(this);
		vistaEditRemovePi=this;
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
		
		lblDesti= new JLabel(LBL_DESTI) ;
		lblDesti.setFont(Fonts.fontLabel());
		txtDesti= new JTextField();
	
		lblId= new JLabel(LBL_ID) ;
		lblId.setFont(Fonts.fontLabel());
		txtId= new JTextField();
		txtId.setToolTipText(TXT_ID);
		
		cmbPis= new JComboBox<String>(omplirPis());
		cmbPis.setActionCommand("combousuaris");
		cmbPis.addActionListener(controlador);
		
		btnEditar= new JButton("Editar");
		btnEditar.addActionListener(controlador);
		btnEditar.setActionCommand("editar");
		
		btnEsborrar= new JButton("Esborrar");
		btnEsborrar.addActionListener(controlador);
		btnEsborrar.setActionCommand("esborrar");
		
		pnlBotons2= new JPanel();
		pnlBotons2.setLayout(new GridLayout(1,2,5,0));
		pnlBotons2.add(btnEditar);
		pnlBotons2.add(btnEsborrar);
		
		lblDesti = new JLabel(LBL_DESTI);
		lblDesti.setFont(Fonts.fontLabel());
		cmbPis= new JComboBox<String>(omplirPis());
		cmbPis.setActionCommand("combousuaris");
		cmbPis.addActionListener(controlador);
		
		lblNom = new JLabel(LBL_NOM);
		lblNom.setFont(Fonts.fontLabel());
		txtNom = new JTextField();
		txtNom.setToolTipText(TXT_NOM);
		txtNom.addKeyListener(
				new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(txtNom.getText().length()==MAX_NOM) {
							e.consume();
						}
						super.keyTyped(e);
					}
				}
			);
		
		lblDescripcio = new JLabel(LBL_DESCRIPCIO);
		lblDescripcio.setFont(Fonts.fontLabel());
		txaDescripcio =new JTextArea();
		txaDescripcio.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txaDescripcio.setLineWrap(true);
		txaDescripcio.setWrapStyleWord(true);
		txaDescripcio.addKeyListener(
				new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(txaDescripcio.getText().length()==MAX_DESCRIPCIO) {
							e.consume();
						}
						super.keyTyped(e);
					}
				}
			);
		
		txaDescripcioScroll = new JScrollPane(txaDescripcio);
		txaDescripcioScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		txaDescripcioScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		lbltipus = new JLabel(LBL_TIPUS);
		lbltipus.setFont(Fonts.fontLabel());
		
		itemGastronomic = new JCheckBoxMenuItem(LBL_GASTRONOMIC);
		itemCultural = new JCheckBoxMenuItem(LBL_CULTURAL);
		itemOci = new JCheckBoxMenuItem(LBL_OCI);
		itemEsport = new JCheckBoxMenuItem(LBL_ESPORT);		
		itemPlatja = new JCheckBoxMenuItem(LBL_PLATJA);
		itemMuntanya = new JCheckBoxMenuItem(LBL_MUNTANYA);
		itemRural = new JCheckBoxMenuItem(LBL_RURAL);

		lblactivitats = new JLabel(LBL_TIPUS);
		lblactivitats.setFont(Fonts.fontLabel());
		
		itemEsqui = new JCheckBoxMenuItem(LBL_ESQUI);
		itemNatacio = new JCheckBoxMenuItem(LBL_NATACIO);
		itemEscalada = new JCheckBoxMenuItem(LBL_ESCALADA);
		itemCompres = new JCheckBoxMenuItem(LBL_COMPRES);		
		itemEquitacio = new JCheckBoxMenuItem(LBL_EQUITACIO);
		itemSenderisme = new JCheckBoxMenuItem(LBL_SENDERISME);
		
		lblimatge = new JLabel(LBL_IMATGE);
		lblimatge.setFont(Fonts.fontLabel());
		
		lblimatge2 = new JLabel(LBL_IMATGE2);
		lblimatge2.setFont(Fonts.fontLabel());
		
		btnImatge = new JButton(LBL_OBRIR);
		btnImatge.addActionListener(controlador);
		btnImatge.setActionCommand("obrir");
		
		btnAcceptar= new JButton(LBL_ACEPTAR);
		btnAcceptar.addActionListener(controlador);
		btnAcceptar.setActionCommand("acceptar");
		
		btnCancelar= new JButton(LBL_CANCELAR);
		btnCancelar.addActionListener(controlador);
	
		pnlBotons= new JPanel();
		pnlBotons.setLayout(new GridLayout(1,2,5,0));
		pnlBotons.add(btnAcceptar);
		pnlBotons.add(btnCancelar);

		activarComponents(false);
	}
	
	/**
	 * Metode activarComponents
	 * per activar o desactivar els components del formulari 
	 */
		
	private void activarComponents(Boolean activar) {
		btnEditar.setEnabled(activar);
		btnEsborrar.setEnabled(activar);
		txtDesti.setEnabled(false);
		txtId.setEnabled(false);
		txtNom.setEnabled(activar);
		txaDescripcio.setEnabled(activar);
		itemGastronomic.setEnabled(activar);
		itemCultural.setEnabled(activar);
		itemOci.setEnabled(activar);
		itemEsport.setEnabled(activar);
		itemPlatja.setEnabled(activar);
		itemMuntanya.setEnabled(activar);
		itemRural.setEnabled(activar);
		itemEsqui.setEnabled(activar);
		itemNatacio.setEnabled(activar);
		itemEscalada.setEnabled(activar);
		itemCompres.setEnabled(activar);
		itemEquitacio.setEnabled(activar);
		itemSenderisme.setEnabled(activar);
		btnAcceptar.setEnabled(activar);
		btnImatge.setEnabled(activar);
		lblimatge.setEnabled(activar);
		lblimatge2.setEnabled(activar);
	}
	
	/**
	 * Metode per afegir els punts d'interes al comboBox
	 * afegira el id entre parentesis
	 * i el nom de la població
	 */

	private Vector<String> omplirPis() {
		Vector<String> pis= new Vector<String>();
		pis.add("Tria un Punt d'interes");
		Map<String, Pi> consultaPis;
		try {
			consultaPis = ControladorPresentacio.getPis();
			for(Entry<String,Pi> entrada: consultaPis.entrySet()) {
				pis.add("(" + entrada.getValue().getId() + ")" + entrada.getValue().getNom());
			}
		} catch (GestioDBException e) {
			JOptionPane.showInternalMessageDialog(vistaEditRemovePi.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
		}

		return pis;
	}

	/**
	 * Metode Sobreescrit de Formulari
	 * per afegir els components necesaris a la vista 
	 */	
		
	@Override
	public void afegirComponents() {
		getContentPane().add(cmbPis);
		getContentPane().add(pnlBotons2);
		getContentPane().add(lblDesti);
		getContentPane().add(txtDesti);
		getContentPane().add(lblId);
		getContentPane().add(txtId);
		getContentPane().add(lblNom);
		getContentPane().add(txtNom);
		getContentPane().add(lblDescripcio);
		getContentPane().add(txaDescripcioScroll);
		getContentPane().add(lbltipus);
		getContentPane().add(itemGastronomic);
		getContentPane().add(itemCultural);
		getContentPane().add(itemOci);
		getContentPane().add(itemEsport);
		getContentPane().add(itemPlatja);
		getContentPane().add(itemMuntanya);
		getContentPane().add(itemRural);
		getContentPane().add(lblactivitats);
		getContentPane().add(itemEsqui);
		getContentPane().add(itemNatacio);
		getContentPane().add(itemEscalada);
		getContentPane().add(itemCompres);
		getContentPane().add(itemEquitacio);
		getContentPane().add(itemSenderisme);
		getContentPane().add(lblimatge);
		getContentPane().add(lblimatge2);
		getContentPane().add(btnImatge);
		getContentPane().add(pnlBotons);		
	}
	
	/**
	 * Metode Sobreescrit de Formulari
	 * per posicionar els components necesaris a la vista 
	 */

	@Override
	public void posicionarComponents() {
		GridBagConstraints gbc= new GridBagConstraints();
		
		//cmbPis
		gbc.gridx=0; 
		gbc.gridy=0; 
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.NONE;
		gbc.insets= new Insets(5, 5, 5, 5);
		layout.setConstraints(cmbPis, gbc);
		
		//botonsEditarEsborrar
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.fill=GridBagConstraints.NONE;
		layout.setConstraints(pnlBotons2, gbc);

		//lblDesti
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets= new Insets(5,5,5,5);
		layout.setConstraints(lblDesti, gbc);
		
		//txtDesti
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(txtDesti, gbc);
				
		//lblid
		gbc.gridx = 0;
		gbc.gridy = 2;
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
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(txtId, gbc);
						
		//lblNom
		gbc.gridx = 0; 
		gbc.gridy = 3; 
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
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(txtNom, gbc);
		
		//lblimatge
		gbc.gridx = 0;
		gbc.gridy = 4; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(lblimatge, gbc);
		
		//btnImatge
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(btnImatge, gbc);
		
		//lblimatge2
		gbc.gridx = 2; 
		gbc.gridy = 2;
		gbc.gridheight = 3;
		gbc.gridwidth = 3;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(lblimatge2, gbc);
		
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
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.gridheight = 2;
		gbc.gridwidth = 3;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		layout.setConstraints(txaDescripcioScroll, gbc);
		
		//lbltipus
		gbc.gridx = 0; 
		gbc.gridy = 9;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(lbltipus, gbc);
						
		//itemGastronomic
		gbc.gridx = 0; 
		gbc.gridy = 10; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(itemGastronomic, gbc);
		
		//itemCultural
		gbc.gridx = 1; 
		gbc.gridy = 10; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(itemCultural, gbc);
		//itemOci
		gbc.gridx = 2; 
		gbc.gridy = 10;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(itemOci, gbc);

		//itemEsport
		gbc.gridx = 3; 
		gbc.gridy = 10; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(itemEsport, gbc);

		//itemPlatja
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(itemPlatja, gbc);

		//itemMuntanya
		gbc.gridx = 1;
		gbc.gridy = 11;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(itemMuntanya, gbc);

		//itemRural
		gbc.gridx = 2;
		gbc.gridy = 11;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(itemRural, gbc);	
		
		//lblactivitats
		gbc.gridx = 0; 
		gbc.gridy = 12; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(lblactivitats, gbc);
		
		//itemEsqui
		gbc.gridx = 0;
		gbc.gridy = 13; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(itemEsqui, gbc);

		//itemNatacio
		gbc.gridx = 1; 
		gbc.gridy = 13;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(itemNatacio, gbc);

		//itemEscalada
		gbc.gridx = 2; 
		gbc.gridy = 13; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(itemEscalada, gbc);
		
		//itemCompres
		gbc.gridx = 0; 
		gbc.gridy = 14; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(itemCompres, gbc);
		
		//itemEquitacio
		gbc.gridx = 1; 
		gbc.gridy = 14;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(itemEquitacio, gbc);
		
		//itemSenderisme
		gbc.gridx = 2; 
		gbc.gridy = 14; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(itemSenderisme, gbc);
				
		//pnlBotons
		gbc.gridx = 3; 
		gbc.gridy = 15; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(pnlBotons, gbc);		
		
	}

	/**
	 * Clase interna Controlador
	 * Per gestionar les accions del usuari sobre la vista
	 */
	
	private class Controlador implements ActionListener{
		
		 /**
		 * Metode per trobar el id del punt d'interes seleccionat
		 * utilitzara tots els caracters fins trobar el marcat ")"
		 * @param id1 id del puntd'interes
		 */
		   
		
		private String idfinal() {
			String id1 = String.valueOf(cmbPis.getSelectedItem());
			id1= id1.substring(1, (id1.indexOf(')', 1, id1.length())));
			return id1;
			}
		
		/**
		 * Metode Sobreescrit actionPerformed
		 * controlara els events dels botons.
         * @since en aquet metode s'ha modificat el boto obrir per que directament obri la carpeta /imatges
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton boto;
			Object obj= e.getSource();
			if(obj instanceof JButton) {
				boto= (JButton)obj;
				
				//Salvar les dades
				if(boto.getActionCommand().equals("acceptar")) {
					if(robustesaFormulari() && !campsBuits() ) {
						modificar();
						netejarFormulari();
						activarComponents(false);
						cmbPis.setSelectedIndex(0);
						cmbPis.setEnabled(true);
						btnCancelar.setText("Sortir");
						VistaEditRemovePi.setBorder(BorderFactory.createTitledBorder(
								BorderFactory.createLineBorder(
								Color.BLUE),
								LBL_TITOL, 
								TitledBorder.LEFT, 
								TitledBorder.DEFAULT_JUSTIFICATION, 
								Fonts.fontTitol(), 
								Color.BLUE)
							  );
					}
				}
				else if(boto.getActionCommand().equals("obrir")) {
					JFileChooser fitxer=new JFileChooser();
					File carpetaImatges = new File("imatges/");
					fitxer.setCurrentDirectory(carpetaImatges);
					int  valor= fitxer.showOpenDialog(null);
					if(valor==JFileChooser.APPROVE_OPTION) {
						File arxiu = fitxer.getSelectedFile();
			            try {
			                imatge = ImageIO.read(arxiu);
			                nomImatge = arxiu.getName();
			                lblimatge2.setIcon(new ImageIcon(new ImageIcon(imatge).getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH)));
				             lblimatge2.setText("");
			            } catch (Exception ex) {
			                JOptionPane.showMessageDialog(null, "El fitxer no correspon a una imatge valida", "Error", JOptionPane.ERROR_MESSAGE);

			            	}
					}

				}
				else if(boto.getText().equals("Sortir")) {
					ControladorPresentacio.cambiVista(new VistaDefault());
				}else if(boto.getActionCommand().equals("editar")) {
					activarComponents(true);
					btnEsborrar.setEnabled(false);
					btnEditar.setEnabled(false);
					btnCancelar.setText("Cancel·lar");
					cmbPis.setEnabled(false);
					VistaEditRemovePi.setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createLineBorder(
							new Color(128,0,128)),
							LBL_TITOL2, 
							TitledBorder.LEFT, 
							TitledBorder.DEFAULT_JUSTIFICATION, 
							Fonts.fontTitol(), 
							new Color(128,0,128))
						  );
				}else if(boto.getText().equals("Cancel·lar")) {
					activarComponents(false);
					btnEsborrar.setEnabled(true);
					btnEditar.setEnabled(true);
					btnCancelar.setText("Sortir");
					cmbPis.setEnabled(true);
					VistaEditRemovePi.setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createLineBorder(
							Color.BLUE),
							LBL_TITOL, 
							TitledBorder.LEFT, 
							TitledBorder.DEFAULT_JUSTIFICATION, 
							Fonts.fontTitol(), 
							Color.BLUE)
						  );
				}else if(boto.getActionCommand().equals("esborrar")) {
					try {
						ControladorPresentacio.removePi(String.valueOf(idfinal()));
						cmbPis.removeItem(cmbPis.getSelectedItem());
						cmbPis.setSelectedIndex(0);
					} catch (GestioDBException e1) {
						JOptionPane.showInternalMessageDialog(vistaEditRemovePi.getContentPane(), e1.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
					}

				}

				
			}else if(obj instanceof JComboBox<?>) {
				@SuppressWarnings("unchecked")
				JComboBox<String> comboUsuaris= (JComboBox<String>) obj;
				if(comboUsuaris.getActionCommand().equals("combousuaris")) {
					if(cmbPis.getSelectedIndex()!=0) {
					btnEsborrar.setEnabled(true);
					btnEditar.setEnabled(true);
					omplirFormularPi();
					}else {
						activarComponents(false);
						netejarFormulari();
					}
					
				}
				
			}

		}
		
		/**
		* Metode per omplir el formulari amb les dades del punt d'interes seleccionat
		* Agafa els noms de les llistes i marca els items com a true
		*/
		
		private void omplirFormularPi() {
			String id = String.valueOf(idfinal());
			Pi pi;
			try {
				pi = ControladorPresentacio.getPi(id);
				String idDesti = pi.getDesti().getId();
				Desti desti=ControladorPresentacio.getDesti(idDesti);
				txtDesti.setText(desti.getPoblacio());
				txtId.setText(pi.getId());
				txtNom.setText(pi.getNom());
				txaDescripcio.setText(pi.getDescripcio());
				lblimatge2.setText("");
				imatge = pi.getImatge();
				nomImatge=pi.getNomImatge();
				try {
			    	ImageIcon imatge = new ImageIcon(pi.getImatge().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
			        lblimatge2.setIcon(imatge);
			    } catch (Exception e) {
			        lblimatge2.setText("No hi ha cap imatge ");
			    }
				 if (pi.getTipus().contains("Gastronòmic")) {
					 itemGastronomic.setSelected(true);
					 }
				 if (pi.getTipus().contains("Cultural")) {
					 itemCultural.setSelected(true);
					 }
				 if (pi.getTipus().contains("Oci")) {
					 itemOci.setSelected(true);
					 }
				 if (pi.getTipus().contains("Esport")) {
					 itemEsport.setSelected(true);
				 }
				 if (pi.getTipus().contains("Platja")) {
				      itemPlatja.setSelected(true);
					 }
				 if (pi.getTipus().contains("Muntanya")) {
					 itemMuntanya.setSelected(true);
					 }
				 if (pi.getTipus().contains("Rural")) {
					 itemRural.setSelected(true);
					 }
				 if (pi.getActivitats().contains("Esquí")) {
					 itemEsqui.setSelected(true);
					 }
				 if (pi.getActivitats().contains("Natació")) {
					 itemNatacio.setSelected(true);
					 }
				 if (pi.getActivitats().contains("Escalada")) {
					 itemEscalada.setSelected(true);
					 }
				 if (pi.getActivitats().contains("Compres")) {
					 itemCompres.setSelected(true);
					 }
				 if (pi.getActivitats().contains("Equitació")) {
					 itemEquitacio.setSelected(true);
					 }
				 if (pi.getActivitats().contains("Senderisme")) {
					 itemSenderisme.setSelected(true);
					 }
			} catch (GestioDBException e) {
				JOptionPane.showInternalMessageDialog(vistaEditRemovePi.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
			}

		}

		/**
		* Mètode per robustesa que comprovarà si certs atributs estan assignats
		* ja que no poden estar buits si estan buits mostra missatge 
		* i es posiciona en la casella afectada.
		* Tambe comprova que s'ha seleccionat un desti
		* @return true en cas de tots plens i desti seleccionat o false en cas contrari
		*/
		
		private boolean campsBuits() {
			boolean buits =false;
			if(txtId.getText().isEmpty() ||
				txtNom.getText().isEmpty() ||
				txaDescripcio.getText().isEmpty()){
				JOptionPane.showInternalMessageDialog(VistaEditRemovePi.getContentPane(), "El ID, nom i descripcio no poden estar buits", "Advertencies", JOptionPane.WARNING_MESSAGE);			
				buits = true;
			} 
			return buits;
		}
		
		/**
		 * Mètode per deixar els camps del formulari buits i els items desmarcats
		 */
		
		private void netejarFormulari() {
			txtDesti.setText("");
			
			txtId.setText("");
			txtNom.setText("");
			txaDescripcio.setText("");
			imatge = null;
			lblimatge2.setIcon(null);
			nomImatge="";
			
		}
		
		private boolean robustesaFormulari() {
			boolean correcte=true;
			return correcte;
		}

		
		/**
	    * Metode que modificara les dades del desti
	    * Agafa el text dels items que estan seleccionats i els afegeis en una llista
	    * les demes dades les envia com s'entren
	    * @since 1.1
	    * Actualitzat metode per trobar id desti
	    */
			   
		
		private void modificar() { 
			
	String nom = txtNom.getText();
	String descripcio = txaDescripcio.getText();
	List<String> tipus = new ArrayList<>();
	Pi pi;
	try {
		pi = ControladorPresentacio.getPi(idfinal());
		String idDesti = pi.getDesti().getId();
		
		
		/*
		 *  Afegir el text de cada item si esta seleccionat
		 */
		
	    if (itemGastronomic.isSelected()) {
	    	tipus.add(itemGastronomic.getText());
	    }
	    if (itemCultural.isSelected()) {
	    	tipus.add(itemCultural.getText());
	    }
	    if (itemOci.isSelected()) {
	    	tipus.add(itemOci.getText());
	    }
	    if (itemEsport.isSelected()) {
	    	tipus.add(itemEsport.getText());
	    }
	    if (itemPlatja.isSelected()) {
	    	tipus.add(itemPlatja.getText());
	    }
	    if (itemMuntanya.isSelected()) {
	    	tipus.add(itemMuntanya.getText());
	    }
	    if (itemRural.isSelected()) {
	    	tipus.add(itemRural.getText());
	    }
		List<String> activitats = new ArrayList<>();
		
		/*
		 *  Afegir el text de cada item si esta seleccionat
		 */
		
	    if (itemEsqui.isSelected()) {
	    	activitats.add(itemEsqui.getText());
	    }
	    if (itemNatacio.isSelected()) {
	    	activitats.add(itemNatacio.getText());
	    }
	    if (itemEscalada.isSelected()) {
	    	activitats.add(itemEscalada.getText());
	    }
	    if (itemCompres.isSelected()) {
	    	activitats.add(itemCompres.getText());
	    }
	    if (itemEquitacio.isSelected()) {
	    	activitats.add(itemEquitacio.getText());
	    }
	    if (itemSenderisme.isSelected()) {
	    	activitats.add(itemSenderisme.getText());
	    }	    

		Desti desti=ControladorPresentacio.getDesti(idDesti);
		if (desti == null) {
		    System.out.println("No s'ha trobat cap desti amb aquet id: " + idDesti);
		    return;
		}
		System.out.println(imatge);
		System.out.println(nomImatge);
		System.out.println(idfinal()+nom+ descripcio+ tipus+ activitats+ desti+imatge+ nomImatge);
		ControladorPresentacio.modifyPi(
				new Pi( idfinal(),nom, descripcio, tipus, activitats, desti, imatge, nomImatge));
	} catch (GestioDBException e) {
		JOptionPane.showInternalMessageDialog(vistaEditRemovePi.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
	}

	
		}
	}
	

}




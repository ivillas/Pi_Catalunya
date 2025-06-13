package cat.almata.ivilla.presentacio;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import cat.almata.ivilla.domini.Desti;
import cat.almata.ivilla.domini.Pi;
import cat.almata.ivilla.persistencia.GestioDBException;
import cat.almata.ivilla.utils.Fonts;
import cat.almata.ivilla.utils.Util;

/**
 * CLase VistaAltaPi
 * Clase que incloura tot el relacionat amb els Punts D'interes
 * atributs, metodes de negoci...
 * @author Ivilla
 * @version 1.2
 * Registre de canvis:
 * V1.1
 * Actualitzar metode Alta() per asignar el id del combobox
 * V1.3
 * Ara s'envia l'id del punt d'interes al desti, aixi a l'hora
 * de eliminar el desti s'eliminen els punts d'interes que te guardats i
 * no es recorren tots els punts d'interes eliminan els que tenen el desti seleccionat
 * Actualitzat la forma de guardar imatge, ara tambe es guarda el nom en un atribut
 */

public class VistaAltaPi extends JInternalFrame implements Formulari{

	
	/**
	 * Declaracio de les constants d'informació
	 * MODEL
	 */
	
	private static final String TITOL="Dades del Punt d'interes";
	private static final String LBL_TITOL="Dades dels punts d'interes";
	private static final String LBL_DESTI="Tria la Ciutat/Poblacio on esta situat:";
	private static final String LBL_ID="Id:";
	private static final String TXT_ID="Introdueix l'ID del punt d'interes";
	private static final String LBL_NOM="Nom:";
	private static final String TXT_NOM="Introdueix el nom del punt d'interes:";
	private static final String LBL_DESCRIPCIO="Descripció:";
	private static final String LBL_TIPUS="Elegeix els tipus d'interes:";
	private static final String LBL_ACTIVITATS="Elegeix les activitas que hi podem trobar:";
	private static final String LBL_IMATGE="Tria una imatge:";
	private static final String LBL_IMATGE2="Cap imatge";
	private static final String LBL_OBRIR="Obrir";
	private static final String LBL_ACEPTAR="Aceptar";
	private static final String LBL_CANCELAR="Cancel·lar";	
	private static final String LBL_GASTRONOMIC="Gastronòmic";
	private static final String LBL_CULTURAL=" Cultural";
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
	 * Declaracio dels atributs
	 * VISTA
	 */
	
	private GridBagLayout layout;
	private Controlador controlador;
	private JLabel lblTitol;
	private JLabel lblDesti;
	private JComboBox<String> cmbDestins;
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
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JPanel pnlBotons;
	private VistaAltaPi vistaAltaPi;
	private BufferedImage imatge;
	private String nomImatge;
	
	/**
	 * @serial VersionUID
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 */

	public VistaAltaPi() {
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
		controlador = new Controlador();	
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), LBL_TITOL, TitledBorder.LEFT, TitledBorder.DEFAULT_JUSTIFICATION, Fonts.fontTitol(), Color.BLUE));
		Util.treureBarraTitolInteralFrame(this);
		this.setVisible(true);
		vistaAltaPi=this;

	}

	/**
	 * Metode Sobreescrit de Formulari
	 * per crear els components que nececite la vista la vista 
	 */
		
	@Override
	public void crearComponents() {
		lblTitol = new JLabel(LBL_TITOL);
		lblTitol.setFont(Fonts.fontLabel());
		lblId = new JLabel(LBL_ID);
		lblId.setFont(Fonts.fontLabel());
		txtId = new JTextField();
		txtId.setToolTipText(TXT_ID);
		txtId.addKeyListener(
				new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(txtId.getText().length()==MAX_ID) {
							e.consume();
						}
						super.keyTyped(e);
					}
				}
			);
		
		//jComboBox
		lblDesti = new JLabel(LBL_DESTI);
		lblDesti.setFont(Fonts.fontLabel());
		cmbDestins= new JComboBox<String>(omplirDestins());
		cmbDestins.setActionCommand("combousuaris");
		cmbDestins.addActionListener(controlador);
		cmbDestins.setToolTipText(LBL_DESTI);
		
		
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

		lblactivitats = new JLabel(LBL_ACTIVITATS);
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
		
		
		btnAceptar = new JButton(LBL_ACEPTAR);
		btnAceptar.addActionListener(controlador);
		btnAceptar.setActionCommand("aceptar");
		
		btnCancelar = new JButton(LBL_CANCELAR);
		btnCancelar.addActionListener(controlador);
		btnCancelar.setActionCommand("cancel·lar");
		
		pnlBotons = new JPanel();
		pnlBotons.add(btnAceptar);
		pnlBotons.add(btnCancelar);
		
	}
	
	/**
	 * Metode per afegir els destins al comboBox
	 * afegira el id entre parentesis
	 * i el nom de la poblaciózz
	 */

	private Vector<String> omplirDestins() {
		Vector<String> destins= new Vector<String>();
		destins.add("Tria un desti");
		Map<String, Desti> consultaDestins;
		try {
			consultaDestins = ControladorPresentacio.getDestins();
			for(Entry<String,Desti> entrada: consultaDestins.entrySet()) {
				destins.add("(" + entrada.getValue().getId() + ")" + entrada.getValue().getPoblacio());
			}
		} catch (GestioDBException e) {
			JOptionPane.showInternalMessageDialog(vistaAltaPi.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
		}

		return destins;
	}

	/**
	 * Metode Sobreescrit de Formulari
	 * per afegir els components necesaris a la vista 
	 */	
	
	@Override
	public void afegirComponents() {
		getContentPane().add(lblDesti);
		getContentPane().add(cmbDestins);
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
		
		//cmbDestins
		gbc.gridx=0; 
		gbc.gridy=1; 
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.NONE;
		gbc.insets= new Insets(5, 5, 5, 5);
		layout.setConstraints(cmbDestins, gbc);
		
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
	
   private  class Controlador implements ActionListener{
	   
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
				boto = (JButton)obj;
				if(boto.getActionCommand().equals("aceptar")) {
					if(!campsBuits() ) {
						if(!existeix(txtId.getText())) {
						alta();
						}else {
							JOptionPane.showInternalMessageDialog(vistaAltaPi.getContentPane(), "El ID "+ txtId.getText()+" del punt d'interes ja existeix!", "Advertencies", JOptionPane.WARNING_MESSAGE);	
						}
						netejarFormulari();
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
				else if(boto.getActionCommand().equals("cancel·lar")) {
					ControladorPresentacio.cambiVista(new VistaDefault());
				}
			}
			
		}

		private boolean existeix(String key) {
			Pi pi = null;
			try {
				pi = ControladorPresentacio.getPi(key);
			} catch (GestioDBException e) {
				JOptionPane.showInternalMessageDialog(vistaAltaPi.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
			}
			return (pi !=null)?true:false;
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
		Object selectedItem = cmbDestins.getSelectedItem();
		 if (selectedItem == null || selectedItem.equals("Tria un desti")) {
		    JOptionPane.showInternalMessageDialog(vistaAltaPi.getContentPane(), "Has de seleccionar un desti", "Advertencies", JOptionPane.WARNING_MESSAGE);            
		    cmbDestins.requestFocus();
		    buits = true;
		}
		 else if(txtId.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(vistaAltaPi.getContentPane(), "El ID no pot estar buit", "Advertencies", JOptionPane.WARNING_MESSAGE);			
			txtId.requestFocus();
			buits = true;
		}
			else if(txtNom.getText().isEmpty()) {
				JOptionPane.showInternalMessageDialog(vistaAltaPi.getContentPane(), "El nom no pot estar buit", "Advertencies", JOptionPane.WARNING_MESSAGE);			
				txtNom.requestFocus();
				buits = true;
		} 
			else if(txaDescripcio.getText().isEmpty()) {
				JOptionPane.showInternalMessageDialog(vistaAltaPi.getContentPane(), "La descripció no pot estar buida", "Advertencies", JOptionPane.WARNING_MESSAGE);			
				txaDescripcio.requestFocus();
				buits = true;
		} 

		return buits;
	}
	
	/**
	 * Mètode per deixar els camps del formulari buits i els items desmarcats
	 */
	
	private void netejarFormulari() {
		txtId.setText("");
		txtNom.setText("");
		txaDescripcio.setText("");
		itemGastronomic.setSelected(false);
		itemCultural.setSelected(false);
		itemOci.setSelected(false);
		itemEsport.setSelected(false);
		itemPlatja.setSelected(false);
		itemMuntanya.setSelected(false);		
		itemRural.setSelected(false);
		itemEsqui.setSelected(false);
		itemNatacio.setSelected(false);
		itemEscalada.setSelected(false);
		itemCompres.setSelected(false);
		itemEquitacio.setSelected(false);
		itemSenderisme.setSelected(false);
		nomImatge="";
		lblimatge2.setIcon(null);
		cmbDestins.setSelectedIndex(0);
	}
	
   /**
    * Metode per a donar d'alta un punt d'interes
    * @since Actualitzar metode per asignar el id del combobox
    * @since També ara s'envia l'id del punt d'interes al desti, aixi a l'hora
    * de eliminar el desti s'eliminen els punts d'interes que te guardats i
    * no es recorren tots els punts d'interes eliminan els que tenen el desti seleccionat
    */
   
   private void alta() {
	   
				String id= txtId.getText();
				String nom = txtNom.getText();
				String descripcio = txaDescripcio.getText();
				List<String> tipus = new ArrayList<>();


	    /*
	     *  Afegir el texte de cada item si esta seleccionat
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
	     *  Afegir el texte de cada item si esta seleccionat
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
		String id1 = String.valueOf(cmbDestins.getSelectedItem().toString());
		id1= id1.substring(1, (id1.indexOf(')', 1, id1.length())));
		Desti desti;
		try {
			desti = ControladorPresentacio.getDesti(id1);
			desti.afegirPi(id);
			ControladorPresentacio.addPi(id, nom, descripcio, tipus, activitats, desti, imatge, nomImatge);
		} catch (GestioDBException e) {
			JOptionPane.showInternalMessageDialog(vistaAltaPi.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
		}

	  }
   
}

















































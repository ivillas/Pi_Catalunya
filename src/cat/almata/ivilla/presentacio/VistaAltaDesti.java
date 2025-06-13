package cat.almata.ivilla.presentacio;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import cat.almata.ivilla.domini.Desti;
import cat.almata.ivilla.persistencia.GestioDBException;
import cat.almata.ivilla.utils.Fonts;
import cat.almata.ivilla.utils.Util;

/**
 * Clase que incloura tot el relacionat amb els destins
 * atributs, metodes de negoci...
 * @author Ivilla
 * @version 1.0
 */

public class VistaAltaDesti extends JInternalFrame implements Formulari{


	/**
	 * Declaracio de les constants d'informació
	 * MODEL
	 */
	
	private static final String TITOL="Dades d'estinacio";
	private static final String LBL_TITOL="Dades de les d'estinacios";
	private static final String LBL_ID="Id:";
	private static final String TXT_ID="Introdueix l'ID del desti";
	private static final String LBL_PROVINCIA="Provincia:";
	private static final String TXT_PROVINCIA="Elegeig la provincia:";
	private static final String LBL_COMARCA="Comarca:";
	private static final String TXT_COMARCA="Introdueix la comarca";
	private static final String LBL_CIUTAT="Ciutat/Població";
	private static final String TXT_CIUTAT="Introdueix la ciutat o la població";
	private static final String LBL_CP="CP:";
	private static final String TXT_CP="Introdueix el codi postal";
	private static final String LBL_TIPUS ="Selecciona una o dos caracteristiques:";
	private static final String LBL_ACEPTAR="Aceptar";
	private static final String LBL_CANCELAR="Cancel·lar";
	protected static final int MAX_ID = 4;
	protected static final int MAX_PROVINCIA = 25;
	protected static final int MAX_COMARCA = 25;
	protected static final int MAX_CIUTAT = 30;
	protected static final int MAX_CP = 5;

	/**
	 * Declaracio dels atributs
	 * VISTA
	 */
	
	private GridBagLayout layout;
	private Controlador controlador;
	private JLabel lblTitol;
	private JLabel lblId;
	private JTextField txtId;
	private JLabel lblProvincia;
	private JTextField txtProvincia;
	private JLabel lblComarca;
	private JTextField txtComarca;
	private JLabel lblCiutat;
	private JTextField txtCiutat;
	private JLabel lblCp;
	private JTextField txtCp;
	private JLabel lbltipus;
	private JCheckBoxMenuItem itemPlatja;
	private JCheckBoxMenuItem itemMontanya;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JPanel pnlBotons;
	private JInternalFrame vistaDataDesti;
		
	
	/**
	 * @serial VersionUID
	 */
	
	private static final long serialVersionUID = 1L;
	

	/**
	 * Constructor
	 */

	public VistaAltaDesti() {
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
		vistaDataDesti=this;
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
		
		lblProvincia = new JLabel(LBL_PROVINCIA);
		lblProvincia.setFont(Fonts.fontLabel());
		txtProvincia = new JTextField();
		txtProvincia.setToolTipText(TXT_PROVINCIA);
		txtProvincia.addKeyListener(
				new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(txtProvincia.getText().length()==MAX_PROVINCIA) {
							e.consume();
						}
						super.keyTyped(e);
					}
				}
			);
		
		lblComarca = new JLabel(LBL_COMARCA);
		lblComarca.setFont(Fonts.fontLabel());
		txtComarca = new JTextField();
		txtComarca.setToolTipText(TXT_COMARCA);
		txtComarca.addKeyListener(
				new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(txtComarca.getText().length()==MAX_COMARCA) {
							e.consume();
						}
						super.keyTyped(e);
					}
				}
			);
		
		lblCiutat = new JLabel(LBL_CIUTAT);
		lblCiutat.setFont(Fonts.fontLabel());
		txtCiutat = new JTextField();
		txtCiutat.setToolTipText(TXT_CIUTAT);
		txtCiutat.addKeyListener(
				new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(txtCiutat.getText().length()==MAX_CIUTAT) {
							e.consume();
						}
						super.keyTyped(e);
					}
				}
			);
		
		lblCp = new JLabel(LBL_CP);
		lblCp.setFont(Fonts.fontLabel());
		txtCp = new JTextField();
		txtCp.setToolTipText(TXT_CP);
		txtCp.addKeyListener(
				new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(txtCp.getText().length()==MAX_CP) {
							e.consume();
						}
						super.keyTyped(e);
					}
				}
			);
		
		lbltipus = new JLabel(LBL_TIPUS);
		lbltipus.setFont(Fonts.fontLabel());
		
		itemPlatja = new JCheckBoxMenuItem("Platja");
		itemMontanya = new JCheckBoxMenuItem("Montanya");
				
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
	 * Metode Sobreescrit de Formulari
	 * per afegir els components necesaris a la vista 
	 */
	
	@Override
	public void afegirComponents() {
		getContentPane().add(lblId);
		getContentPane().add(txtId);
		getContentPane().add(lblProvincia);
		getContentPane().add(txtProvincia);
		getContentPane().add(lblComarca);
		getContentPane().add(txtComarca);
		getContentPane().add(lblCiutat);
		getContentPane().add(txtCiutat);
		getContentPane().add(lblCp);
		getContentPane().add(txtCp);
		getContentPane().add(lbltipus);
		getContentPane().add(itemMontanya);
		getContentPane().add(itemPlatja);
		getContentPane().add(pnlBotons);
		
	}
	
	/**
	 * Metode Sobreescrit de Formulari
	 * per posicionar els components necesaris a la vista 
	 */
	
	
	@Override
	public void posicionarComponents() {
		GridBagConstraints gbc = new GridBagConstraints();

				//lblid
				gbc.gridx = 0; 
				gbc.gridy = 0; 
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
				gbc.gridy = 0;
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				gbc.weightx = 0; 
				gbc.weighty = 0;
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				layout.setConstraints(txtId, gbc);
								
				//lblProvincia
				gbc.gridx = 0; 
				gbc.gridy = 1; 
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				gbc.weightx = 0; 
				gbc.weighty = 0;
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.NONE;
				gbc.insets= new Insets(5,5,5,5);
				layout.setConstraints(lblProvincia, gbc);
				
				//txtProvincia
				gbc.gridx = 1; 
				gbc.gridy = 1; 
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				gbc.weightx = 0; 
				gbc.weighty = 0;
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				layout.setConstraints(txtProvincia, gbc);
				
				//lblComarca
				gbc.gridx = 0; 
				gbc.gridy = 2; 
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				gbc.weightx = 0;
				gbc.weighty = 0;
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.NONE;
				layout.setConstraints(lblComarca, gbc);
				
				//txtComarca
				gbc.gridx = 1; 
				gbc.gridy = 2; 
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				gbc.weightx = 0; 
				gbc.weighty = 0;
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				layout.setConstraints(txtComarca, gbc);
				
				//lblCiutat
				gbc.gridx = 0; 
				gbc.gridy = 3;
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				gbc.weightx = 0; 
				gbc.weighty = 0;
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.NONE;
				layout.setConstraints(lblCiutat, gbc);
				
				//txtCiutat
				gbc.gridx = 1; 
				gbc.gridy = 3; 
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				gbc.weightx = 0; 
				gbc.weighty = 0;
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				layout.setConstraints(txtCiutat, gbc);
				
				//lblCp
				gbc.gridx = 0; 
				gbc.gridy = 4; 
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				gbc.weightx = 0; 
				gbc.weighty = 0;
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				layout.setConstraints(lblCp, gbc);
				
				//txtCp
				gbc.gridx = 1; 
				gbc.gridy = 4; 
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				gbc.weightx = 0; 
				gbc.weighty = 0;
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				layout.setConstraints(txtCp, gbc);
				
				//lbltipus
				gbc.gridx = 0; 
				gbc.gridy = 5; 
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				gbc.weightx = 0; 
				gbc.weighty = 0;
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.NONE;
				layout.setConstraints(lbltipus, gbc);
				
				//itemPlatja
				gbc.gridx = 1; 
				gbc.gridy = 5; 
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				gbc.weightx = 0; 
				gbc.weighty = 0;
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.NONE;
				layout.setConstraints(itemPlatja, gbc);
				
				//itemMontanya
				gbc.gridx = 1; 
				gbc.gridy = 6;
				gbc.gridheight = 1;
				gbc.gridwidth = 1;
				gbc.weightx = 0; 
				gbc.weighty = 0;
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.NONE;
				layout.setConstraints(itemMontanya, gbc);
		
				//pnlBotons
				gbc.gridx = 1; 
				gbc.gridy = 11; 
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
		 */
	   
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton boto;
			Object obj= e.getSource();
			if(obj instanceof JButton) {
				boto = (JButton)obj;
				if(boto.getActionCommand().equals("aceptar")) {
					if(robustesaFormulari() && !campsBuits() ) {
						if(!existeix(txtId.getText())) {
						alta();
						}else {
							JOptionPane.showInternalMessageDialog(vistaDataDesti.getContentPane(), "El desti "+ txtId.getText()+" ja existeix!", "Advertencies", JOptionPane.WARNING_MESSAGE);	
						}
						netejarFormulari();
					}
				}
				else if(boto.getActionCommand().equals("cancel·lar")) {
				ControladorPresentacio.cambiVista(new VistaDefault());
				}
			
		}}

		private boolean existeix(String key) {
			Desti desti = null;
			try {
				desti = ControladorPresentacio.getDesti(key);
			} catch (GestioDBException e) {
				JOptionPane.showInternalMessageDialog(vistaDataDesti.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
			}
			return (desti !=null)?true:false;
		}

		private boolean robustesaFormulari() {
			boolean correcte = true;
			return correcte;
		}
			
}
   
   /**
    * Mètode per robustesa que comprovarà si certs atributs estan assignats
    * ja que no poden estar buits si estan buits mostra missatge 
    * i es posiciona en la casella afectada.
    * @return true en cas de tots plens o false en cas contrari
    */
   
	private boolean campsBuits() {
		boolean buits =false;
		if(txtId.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(vistaDataDesti.getContentPane(), "El ID no pot estar buit", "Advertencies", JOptionPane.WARNING_MESSAGE);			
			txtId.requestFocus();
			buits = true;
		}
			else if(txtProvincia.getText().isEmpty()) {
				JOptionPane.showInternalMessageDialog(vistaDataDesti.getContentPane(), "La provincia no pot estar buida", "Advertencies", JOptionPane.WARNING_MESSAGE);			
				txtProvincia.requestFocus();
				buits = true;
		} 
			else if(txtComarca.getText().isEmpty()) {
				JOptionPane.showInternalMessageDialog(vistaDataDesti.getContentPane(), "La comarca no pot estar buida", "Advertencies", JOptionPane.WARNING_MESSAGE);			
				txtComarca.requestFocus();
				buits = true;
		} 
			else if(txtCiutat.getText().isEmpty()) {
				JOptionPane.showInternalMessageDialog(vistaDataDesti.getContentPane(), "La Ciutat no pot estar buida", "Advertencies", JOptionPane.WARNING_MESSAGE);			
				txtCiutat.requestFocus();
				buits = true;
		} 
			else if(txtCp.getText().isEmpty()) {
				JOptionPane.showInternalMessageDialog(vistaDataDesti.getContentPane(), "El codi postal no pot estar buit", "Advertencies", JOptionPane.WARNING_MESSAGE);			
		} 

		return buits;
	}


	/**
	 * Mètode per deixar els camps del formulari buits i els items desmarcats
	 */
	
	private void netejarFormulari() {
		txtId.setText("");
		txtProvincia.setText("");
		txtCiutat.setText("");
		txtComarca.setText("");
		txtCp.setText("");
		itemPlatja.setSelected(false);
		itemMontanya.setSelected(false);
	}
   
   /**
    * Mètode per afegir les dades a un desti nou 
    * cridarà al mètode encarregat de fer la gestió
    */
	
   private void alta() {
		try {
			ControladorPresentacio.addDesti(
					txtId.getText(),
					txtProvincia.getText(),
					txtComarca.getText(),
					txtCiutat.getText(),
					txtCp.getText(),
					itemPlatja.isSelected(),
					itemMontanya.isSelected());
		} catch (GestioDBException e) {
			JOptionPane.showInternalMessageDialog(vistaDataDesti.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
		}
   }

}


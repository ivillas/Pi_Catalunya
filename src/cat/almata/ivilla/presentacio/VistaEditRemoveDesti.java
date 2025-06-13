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
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;
import cat.almata.ivilla.domini.Desti;
import cat.almata.ivilla.persistencia.GestioDBException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import cat.almata.ivilla.utils.Fonts;
import cat.almata.ivilla.utils.Util;

/**
 * Clase que conte els metodes per modificar i eliminar destins
 * atributs, metodes de negoci...
 * @author Ivilla
 * @version 1.1
 * Registre de canvis:
 * V1.1
 * Actualitzar metode idfinal() per retornar el id desde una funció i no en cada solicitud
 */

public class VistaEditRemoveDesti extends JInternalFrame implements Formulari{
	
	/**
	 * Declaracio de les constants d'informació
	 * MODEL
	 */
	
	private static final String TITOL="Dades d'estinacio";
	private static final String LBL_TITOL="Dades de les d'estinacios";
	private static final String LBL_PROVINCIA="Provincia:";
	private static final String TXT_PROVINCIA="Introdueix la provincia:";
	private static final String LBL_COMARCA="Comarca:";
	private static final String TXT_COMARCA="Introdueix la comarca";
	private static final String LBL_CIUTAT="Ciutat/Població";
	private static final String TXT_CIUTAT="Introdueix la ciutat o la població";
	private static final String LBL_CP="CP:";
	private static final String TXT_CP="Introdueix el codi postal";
	private static final String LBL_TIPUS ="Selecciona una o dos caracteristiques:";
	protected static final int MAX_ID = 4;
	protected static final int MAX_PROVINCIA = 25;
	protected static final int MAX_COMARCA = 25;
	protected static final int MAX_CIUTAT = 30;
	protected static final int MAX_CP = 5;
	public static final String LBL_TITOL2 = "Editant el perfil dels destins";
	
	/**
	 * Declaracio dels atributs
	 * VISTA
	 */
	
	private GridBagLayout layout;
	private Controlador controlador;
	private JLabel lblTitol;
	private JComboBox<String> cmbDestins;
	private JButton btnEditar;
	private JButton btnEsborrar;
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
	private JButton btnAcceptar;
	private JButton btnCancelar;
	private JPanel pnlBotons;
	private JInternalFrame VistaEditRemovedesti;
	private JPanel pnlBotons2;
	private VistaEditRemoveDesti vistaEditRemoveDesti;
	
	/**
	 * @serial VersionUID
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 */

	public VistaEditRemoveDesti() {
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
		VistaEditRemovedesti=this;
		setVisible(true);
		vistaEditRemoveDesti = this;
		}

	/**
	 * Metode Sobreescrit de Formulari
	 * per crear els components que nececite la vista la vista 
	 */
	
	@Override
	public void crearComponents() {
		
		lblTitol = new JLabel(LBL_TITOL);
		lblTitol.setFont(Fonts.fontLabel());

		cmbDestins= new JComboBox<String>(omplirDestins());
		cmbDestins.setActionCommand("combousuaris");
		cmbDestins.addActionListener(controlador);
		
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
		
		lbltipus=new JLabel(LBL_TIPUS);
		lbltipus.setFont(Fonts.fontLabel());
		
		itemPlatja = new JCheckBoxMenuItem("Platja");
		itemMontanya = new JCheckBoxMenuItem("Montanya");
		
				
		btnAcceptar= new JButton("Acceptar");
		btnAcceptar.addActionListener(controlador);
		btnAcceptar.setActionCommand("acceptar");
		
		btnCancelar= new JButton("Sortir");
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
		txtProvincia.setEnabled(activar);
		txtComarca.setEnabled(activar);
		txtCiutat.setEnabled(activar);
		txtCp.setEnabled(activar);
		itemMontanya.setEnabled(activar);
		itemPlatja.setEnabled(activar);
		btnAcceptar.setEnabled(activar);
	}
	
	/**
	 * Metode per afegir els destins al comboBox
	 * afegira el id entre parentesis
	 * i el nom de la població
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
			JOptionPane.showInternalMessageDialog(vistaEditRemoveDesti.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
		}
		return destins;
	}
	
	/**
	 * Metode Sobreescrit de Formulari
	 * per afegir els components necesaris a la vista 
	 */	
	
	@Override
	public void afegirComponents() {
		getContentPane().add(cmbDestins);
		getContentPane().add(pnlBotons2);
		getContentPane().add(lblProvincia);
		getContentPane().add(txtProvincia);
		getContentPane().add(lblComarca);
		getContentPane().add(txtComarca);
		getContentPane().add(lblCiutat);
		getContentPane().add(txtCiutat);
		getContentPane().add(lblCp);
		getContentPane().add(txtCp);
		getContentPane().add(lbltipus);
		getContentPane().add(itemPlatja);
		getContentPane().add(itemMontanya);
		getContentPane().add(pnlBotons);

		
	}
	
	/**
	 * Metode Sobreescrit de Formulari
	 * per posicionar els components necesaris a la vista 
	 */

	@Override
	public void posicionarComponents() {
		GridBagConstraints gbc= new GridBagConstraints();
		
		//cmbDestins
		gbc.gridx=0; 
		gbc.gridy=0; 
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.NONE;
		gbc.insets= new Insets(5, 5, 5, 5);
		layout.setConstraints(cmbDestins, gbc);
		
		//pnlBotons2
		gbc.gridx=1; 
		gbc.gridy=0; 
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.fill=GridBagConstraints.NONE;
		layout.setConstraints(pnlBotons2, gbc);

		//lblProvincia
		gbc.gridx=0; 
		gbc.gridy=1; 
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.NONE;
		gbc.insets= new Insets(5, 5, 5, 5);
		layout.setConstraints(lblProvincia, gbc);
		
		//txtProvincia
		gbc.gridx=1;
		gbc.gridy=1; 
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		layout.setConstraints(txtProvincia, gbc);
		
		//lblComarca
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.NONE;
		layout.setConstraints(lblComarca, gbc);
		
		//txtComarca
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		layout.setConstraints(txtComarca, gbc);
		
		//lblCiutat
		gbc.gridx=0; 
		gbc.gridy=3; 
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.NONE;
		layout.setConstraints(lblCiutat, gbc);
		
		//txtCiutat
		gbc.gridx=1; 
		gbc.gridy=3;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		layout.setConstraints(txtCiutat, gbc);
				
		//lblCp
		gbc.gridx=0; 
		gbc.gridy=4; 
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.NONE;
		layout.setConstraints(lblCp, gbc);
		
		//txtCp
		gbc.gridx=1; 
		gbc.gridy=4; 
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		layout.setConstraints(txtCp, gbc);
				
		//lbltipus
		gbc.gridx=0;
		gbc.gridy=5; 
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.NONE;
		layout.setConstraints(lbltipus, gbc);
		
		//itemPlatja
		gbc.gridx=1; 
		gbc.gridy=5; 
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		layout.setConstraints(itemPlatja, gbc);
		
		//itemMontanya
		gbc.gridx=1; 
		gbc.gridy=6; 
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		layout.setConstraints(itemMontanya, gbc);
			
		//pnlBotons
		gbc.gridx=1; 
		gbc.gridy=7; 
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.anchor=GridBagConstraints.EAST;
		gbc.fill=GridBagConstraints.NONE;
		layout.setConstraints(pnlBotons, gbc);
	}

	/**
	 * Clase interna Controlador
	 * Per gestionar les accions del usuari sobre la vista
	 */
	
	private class Controlador implements ActionListener{
		
		/**
		 * Metode Sobreescrit actionPerformed
		 * controlara els events dels botons.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton boto;
			Object obj= e.getSource();
			if(obj instanceof JButton) {
				boto= (JButton)obj;
				if(boto.getActionCommand().equals("acceptar")) {
					if(!campsBuits() ) {
						modificar();
						netejarFormulari();
						activarComponents(false);
						cmbDestins.setSelectedIndex(0);
						cmbDestins.setEnabled(true);
						btnCancelar.setText("Sortir");
						VistaEditRemovedesti.setBorder(BorderFactory.createTitledBorder(
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
				else if(boto.getText().equals("Sortir")) {
					ControladorPresentacio.cambiVista(new VistaDefault());
				}else if(boto.getActionCommand().equals("editar")) {
					activarComponents(true);
					btnEsborrar.setEnabled(false);
					btnEditar.setEnabled(false);
					btnCancelar.setText("Cancel·lar");
					cmbDestins.setEnabled(false);
					VistaEditRemovedesti.setBorder(BorderFactory.createTitledBorder(
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
					//netejarFormulari();
					btnEsborrar.setEnabled(true);
					btnEditar.setEnabled(true);
					btnCancelar.setText("Sortir");
					cmbDestins.setEnabled(true);
					VistaEditRemovedesti.setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createLineBorder(
							Color.BLUE),
							LBL_TITOL, 
							TitledBorder.LEFT, 
							TitledBorder.DEFAULT_JUSTIFICATION, 
							Fonts.fontTitol(), 
							Color.BLUE)
						  );
				}else if(boto.getActionCommand().equals("esborrar")) {
					List<String> ids;
					try {
						ids = ControladorPresentacio.getDesti(String.valueOf(idfinal())).getPis();
						for (String i : ids) {
							ControladorPresentacio.removePi(i);
						};
						ControladorPresentacio.removeDesti(String.valueOf(idfinal()));
						cmbDestins.removeItem(cmbDestins.getSelectedItem());
						cmbDestins.setSelectedIndex(0);
					} catch (GestioDBException e1) {
						JOptionPane.showInternalMessageDialog(vistaEditRemoveDesti.getContentPane(), e1.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
					}
				}

				
			}else if(obj instanceof JComboBox<?>) {
				@SuppressWarnings("unchecked")
				JComboBox<String> comboUsuaris= (JComboBox<String>) obj;
				if(comboUsuaris.getActionCommand().equals("combousuaris")) {
					if(cmbDestins.getSelectedIndex()!=0) {
					btnEsborrar.setEnabled(true);
					btnEditar.setEnabled(true);
					omplirFormulariDesti();
					}else {
						activarComponents(false);
						netejarFormulari();
					}
					
				}
				
			}

		}
		
		   /**
		    * Metode per omplir el formulari amb les dades del desti seleccionat
		    */
		
		private void omplirFormulariDesti() {
			String id = String.valueOf(idfinal());
			Desti desti;
			try {
				desti = ControladorPresentacio.getDesti(id);
				txtProvincia.setText(desti.getProvincia());
				txtComarca.setText(desti.getComarca());
				txtCiutat.setText(desti.getPoblacio());
				txtCp.setText(desti.getCP());
				itemPlatja.setSelected(desti.getEsDePlatja());
				itemMontanya.setSelected(desti.getEsDeMontanya());
			} catch (GestioDBException e) {
				JOptionPane.showInternalMessageDialog(vistaEditRemoveDesti.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
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
				if(txtProvincia.getText().isEmpty()) {
					JOptionPane.showInternalMessageDialog(VistaEditRemovedesti.getContentPane(), "La provincia no pot estar buida", "Advertencies", JOptionPane.WARNING_MESSAGE);			
					txtProvincia.requestFocus();
					buits = true;
			} 
				else if(txtComarca.getText().isEmpty()) {
					JOptionPane.showInternalMessageDialog(VistaEditRemovedesti.getContentPane(), "La comarca no pot estar buida", "Advertencies", JOptionPane.WARNING_MESSAGE);			
					txtComarca.requestFocus();
					buits = true;
			} 
				else if(txtCiutat.getText().isEmpty()) {
					JOptionPane.showInternalMessageDialog(VistaEditRemovedesti.getContentPane(), "La Ciutat no pot estar buida", "Advertencies", JOptionPane.WARNING_MESSAGE);			
					txtCiutat.requestFocus();
					buits = true;
			} 
				else if(txtCp.getText().isEmpty()) {
					JOptionPane.showInternalMessageDialog(VistaEditRemovedesti.getContentPane(), "El codi postal no pot estar buit", "Advertencies", JOptionPane.WARNING_MESSAGE);			
					txtCp.requestFocus();
					buits = true;
			} 

			return buits;
		}
				
		/**
		 * Mètode per deixar els camps del formulari buits i els items desmarcats
		 */
		
		private void netejarFormulari() {
			txtProvincia.setText("");
			txtCiutat.setText("");
			txtComarca.setText("");
			txtCp.setText("");
			itemPlatja.setSelected(false);
			itemMontanya.setSelected(false);
		}
		
		
	   /**
	    * Metode que modificara les dades del desti
	    */
		   
		private void modificar() { 
			try {
				ControladorPresentacio.modifyDesti(
						new Desti (
						idfinal(),
						txtProvincia.getText(),
						txtComarca.getText(),
						txtCiutat.getText(),
						txtCp.getText(),
						itemPlatja.isSelected(),
						itemMontanya.isSelected()));
			} catch (GestioDBException e) {
				JOptionPane.showInternalMessageDialog(vistaEditRemoveDesti.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
			}
		}
	}
	
	 /**
	 * Metode per trobar el id del desti seleccionat
	 * utilitzara tots els caracters fins trobar el marcat ")"
	 * @param id1 id del desti
	 */
	   
	private String idfinal() {
	String id1 =  String.valueOf(cmbDestins.getSelectedItem().toString());
	id1 = id1.substring(1, (id1.indexOf(')', 1, id1.length())));
	return id1;
	}
}




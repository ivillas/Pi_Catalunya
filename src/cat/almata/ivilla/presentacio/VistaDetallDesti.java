package cat.almata.ivilla.presentacio;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import cat.almata.ivilla.domini.Desti;
import cat.almata.ivilla.utils.Fonts;

/**
 * Clase VistaDetallDesti
 * Clase per mostrar les dades complertes del desti
 * @author Ivilla
 * @version 1.0
 */

public class VistaDetallDesti extends JDialog implements Formulari{
	
	/**
	 * @serial VersionUID
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * Declaracio de les constants d'informació
	 * MODEL
	 */
	
	private static final String LBL_TITOL="Dades de les d'estinacios";
	private static final String LBL_ID="Id:";
	private static final String TXT_ID="ID del desti";
	private static final String LBL_PROVINCIA="Provincia:";
	private static final String TXT_PROVINCIA="Provincia del desti seleccionat:";
	private static final String LBL_COMARCA="Comarca:";
	private static final String TXT_COMARCA="Comarca del desti seleccionat";
	private static final String LBL_CIUTAT="Ciutat/Població";
	private static final String TXT_CIUTAT="Ciutat o població del desti seleccionat";
	private static final String LBL_CP="CP:";
	private static final String TXT_CP="Codi postal del desti seleccionat";
	private static final String LBL_TIPUS ="Característiques del paisatje";
	private static final String LBL_ACEPTAR="Aceptar";
		
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
	private JButton btnAcceptar;
	private JDialog VistaDetallDesti;
	private Desti desti;
	
	/**
	 * Constructor
	 */
	
	public VistaDetallDesti(Desti desti) {
		this.desti=desti;
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
		this.setBounds(0, 0, 400, 500);
		controlador = new Controlador();	
		VistaDetallDesti=this;
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
					
		lblId = new JLabel(LBL_ID);
		lblId.setFont(Fonts.fontLabel());
		txtId = new JTextField();
		txtId.setToolTipText(TXT_ID);
		txtId.setText(desti.getId());
		txtId.setEnabled(false);
		
		lblProvincia = new JLabel(LBL_PROVINCIA);
		lblProvincia.setFont(Fonts.fontLabel());
		txtProvincia = new JTextField();
		txtProvincia.setToolTipText(TXT_PROVINCIA);
		txtProvincia.setText(desti.getProvincia());
		txtProvincia.setEnabled(false);
				
		lblComarca = new JLabel(LBL_COMARCA);
		lblComarca.setFont(Fonts.fontLabel());
		txtComarca = new JTextField();
		txtComarca.setToolTipText(TXT_COMARCA);
		txtComarca.setText(desti.getComarca());
		txtComarca.setEnabled(false);
		
		lblCiutat = new JLabel(LBL_CIUTAT);
		lblCiutat.setFont(Fonts.fontLabel());
		txtCiutat = new JTextField();
		txtCiutat.setToolTipText(TXT_CIUTAT);
		txtCiutat.setText(desti.getPoblacio());
		txtCiutat.setEnabled(false);
		
		lblCp = new JLabel(LBL_CP);
		lblCp.setFont(Fonts.fontLabel());
		txtCp = new JTextField();
		txtCp.setToolTipText(TXT_CP);
		txtCp.setText(desti.getCP());
		txtCp.setEnabled(false);
				
		lbltipus = new JLabel(LBL_TIPUS);
		lbltipus.setFont(Fonts.fontLabel());
		
		itemPlatja = new JCheckBoxMenuItem("Platja");
		itemMontanya = new JCheckBoxMenuItem("Montanya");
		itemMontanya.setEnabled(false);
		itemPlatja.setEnabled(false);
		
		itemPlatja.setSelected(desti.getEsDePlatja());
		itemMontanya.setSelected(desti.getEsDeMontanya());

		btnAcceptar = new JButton(LBL_ACEPTAR);
		btnAcceptar.addActionListener(controlador);
		btnAcceptar.setActionCommand("aceptar");
			
		
		
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
		getContentPane().add(itemPlatja);		
		getContentPane().add(itemMontanya);
		getContentPane().add(btnAcceptar);

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

		//btnAcceptar
		gbc.gridx = 1; 
		gbc.gridy = 7; 
		gbc.gridheight = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0; 
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(btnAcceptar, gbc);	
		
	}
		
	/**
	 * Clase interna Controlador
	 * Per gestionar les accions del usuari sobre la vista
	 */

	private class Controlador implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			VistaDetallDesti.dispose();
			
		}
	}
}


package cat.almata.ivilla.presentacio;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import cat.almata.ivilla.domini.Desti;
import cat.almata.ivilla.domini.Pi;
import cat.almata.ivilla.persistencia.GestioDBException;
import cat.almata.ivilla.utils.Util;

/**
 * Clase VistaDestiPi
 * Clase per mostrar les dades dels Punts d'interes d'un desti
 * @author Ivilla
 * @version 1.1
 * Registre de canvis:
 * V1.1
 * Actualitzar metode omplirTaulaPis() per asignar el id del comboboxx
 */

public class VistaDestiPi extends JInternalFrame implements Formulari {
	
	/**
	 * @serial VersionUID
	 */

	private static final long serialVersionUID = 1L;
	
	/**
	 * Declaracio de les constants d'informació
	 * MODEL
	 */
	
	private static final String TITOL = "Consulta Punts D'interes dels destins";
	
	/**
	 * Declaracio dels atributs
	 * VISTA
	 */
	
	private DefaultTableModel model;
	private JScrollPane tblpiScroll;
	private JTable tblpi;
	private ActionListener controlador;
	private GridBagLayout layout;
	private JComboBox<String> cmbDestins;
	private JButton btnsortit;
	private VistaDestiPi vistaDestiPi;
	
	/**
	 * Constructor
	 */
	
	public  VistaDestiPi() {
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
		Util.treureBarraTitolInteralFrame(this);
		this.setVisible(true);
		vistaDestiPi = this;
	}
	
	/**
	 * Metode Sobreescrit de Formulari
	 * per crear els components que nececite la vista la vista 
	 */
	
	@Override
	public void crearComponents() {
	
		cmbDestins= new JComboBox<String>(omplirDestins());
	    cmbDestins.setActionCommand("cmbDestins");
		cmbDestins.addActionListener(controlador);
		
		 model  = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
	
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return Object.class;
			}
	
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		model.addColumn("Id");
		model.addColumn("Nom");
		model.addColumn("Descripcio");

 
		tblpi = new JTable(model);
		tblpi.addMouseListener((MouseListener) controlador);
		DefaultTableCellRenderer celes = new DefaultTableCellRenderer();
		celes.setVerticalAlignment(JTextField.CENTER);
		celes.setHorizontalAlignment(JTextField.CENTER);
		
		for(int i=0; i < tblpi.getColumnCount(); i++) {
		tblpi.getColumnModel().getColumn(i).setCellRenderer(celes);
		}
		
		tblpi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblpiScroll = new JScrollPane(tblpi);
				
		btnsortit = new JButton("Sortir");
		btnsortit.addActionListener(controlador); 
		btnsortit.setActionCommand("sortir");

	}
	
	/**
	 * Metode per afegir els destins al comboBox
	 * afegira el id entre parentesis
	 * i el nom de la població
	 */


	private Vector<String> omplirDestins() {
		Vector<String> destins = new Vector<String>();
		destins.add("Tria un Desti");
		Map<String, Desti> consultaDestins;
		try {
			consultaDestins = ControladorPresentacio.getDestins();
			for(Entry<String,Desti> entrada: consultaDestins.entrySet()) {
				destins.add("(" + entrada.getValue().getId() + ")" + entrada.getValue().getPoblacio());			
			}
		} catch (GestioDBException e) {
			JOptionPane.showInternalMessageDialog(vistaDestiPi.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
		}

		
		return destins;
	}

	/**
	 * Metode Sobreescrit de Formulari
	 * per afegir els components necesaris a la vista 
	 */	
	
	@Override
	public void afegirComponents() {
		add(cmbDestins);
		add(tblpiScroll);
		add(btnsortit);
		
	}
	
	/**
	 * Metode Sobreescrit de Formulari
	 * per posicionar els components necesaris a la vista 
	 */
	
	@Override
	public void posicionarComponents() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		//cmbDestins
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets= new Insets(5,5,5,5);
		layout.setConstraints(cmbDestins, gbc);
		
		//Taula
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		layout.setConstraints(tblpiScroll, gbc);
		
		//btnAceptar
		gbc.gridx = 0;
		gbc.gridy = 4; 
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		layout.setConstraints(btnsortit, gbc);
		
	}
	
	/**
	 * Clase interna Controlador
	 * Per gestionar les accions del usuari sobre la vista
	 */
	
	private class Controlador extends MouseAdapter implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj instanceof JButton) {
				JButton boto = (JButton) obj;
				if(boto.getActionCommand().equals("sortir")) {
					ControladorPresentacio.cambiVista(new VistaDefault());
				}
			} else if(obj instanceof JComboBox<?>) {
				@SuppressWarnings("unchecked")
				JComboBox<String> comboDestins = (JComboBox<String>) obj;
				if(comboDestins.getActionCommand().equals("cmbDestins")) {
					omplirTaulaPis();
				}
				
			}
		}
		
		
		   /**
		    * Metode per omplir les dades dels punt d'interes segons el desti
		    * @since Actualitzar metode per asignar el id del combobox
		    */
				
		private void omplirTaulaPis() {
			model.setRowCount(0);
			if(cmbDestins.getSelectedIndex()!=0) {
				String id = String.valueOf(cmbDestins.getSelectedItem().toString());
				id= id.substring(1, (id.indexOf(')', 1, id.length())));
				Map<String, Pi> pidesti;
				try {
					pidesti = ControladorPresentacio.getPisDestins(id);
					afegirPiDestiTaula(pidesti);
				} catch (GestioDBException e) {
					JOptionPane.showInternalMessageDialog(vistaDestiPi.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
				}
			}	
		}
		
		   /**
		    * Metode per omplir les dades dels punt d'interes a la taula
		    */
		
		private void afegirPiDestiTaula(Map<String, Pi> pi) {
			for(Entry<String,Pi> key: pi.entrySet()) {
				Pi pis= key.getValue();
				Object[] dadesFila = new Object[3];
				dadesFila[0]=pis.getId();
				dadesFila[1]=pis.getNom();
				dadesFila[2]=pis.getDescripcio();
				model.addRow(dadesFila);
			}	
		}
				
		/**
		 * Metode Sobreescrit actionPerformed
		 * controlara els events del ratoli
		 * obrira les dades al fer click
		 */
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int fila= tblpi.rowAtPoint(e.getPoint());
			int columna= tblpi.columnAtPoint(e.getPoint());
			if(fila>-1 && columna>-1) {
				Pi pi = null;
				try {
					pi = ControladorPresentacio.getPi((String) model.getValueAt(fila, 0));
					new VistaDetallPi(pi);
				} catch (GestioDBException e1) {
					JOptionPane.showInternalMessageDialog(vistaDestiPi.getContentPane(), e1.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
				}

			}
		}
	}
}

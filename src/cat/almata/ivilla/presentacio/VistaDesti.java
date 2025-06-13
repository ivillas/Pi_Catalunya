package cat.almata.ivilla.presentacio;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JButton;
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
import cat.almata.ivilla.persistencia.GestioDBException;
import cat.almata.ivilla.utils.Util;


/**
 * Clase VistaDesti
 * Clase per mostrar les dades dels destins
 * @author Ivilla
 * @version 1.0
 */

public class VistaDesti extends JInternalFrame  implements Formulari {

	/**
	 * @serial VersionUID
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Declaracio de les constants d'informació
	 * MODEL
	 */
	
	private static final String TITOL="Consulta Destins";

	/**
	 * Declaracio dels atributs
	 * VISTA
	 */
	
	private GridBagLayout layout;
	private DefaultTableModel model;
	private Controlador controlador;
	private JTable tblDestins;
	private JScrollPane tblDestinsScroll;
	private JButton btnsortit;


	private VistaDesti vistaAltaPi;

	/**
	 * Constructor
	 */
	
	public VistaDesti() {
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
	    vistaAltaPi = this;
	}
	

	/**
	 * Metode Sobreescrit de Formulari
	 * per crear els components que nececite la vista la vista 
	 */
	
	@Override
	public void crearComponents() {
		
		/*
		 * Inici JTable
		 */
		model  = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;
				
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if(columnIndex==4) return LocalDate.class;
				if(columnIndex==5) return Boolean.class;
				return Object.class;
			}
			
			/*
			 *Decidirem si les files son editables
			 */
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		/*
		 *Columnes de la taula
		 */
		
		model.addColumn("Id");
		model.addColumn("Provincia");
		model.addColumn("Comarca");
		model.addColumn("Ciutat");
		model.addColumn("CP");
		
		/*
		 * Comencem a crear la taula qapartir del model 
		 */
		
		tblDestins = new JTable(model);
		tblDestins.addMouseListener(controlador);
		DefaultTableCellRenderer celes = new DefaultTableCellRenderer();
		celes.setVerticalAlignment(JTextField.CENTER);
		celes.setHorizontalAlignment(JTextField.CENTER);
		
		for(int i=0; i < tblDestins.getColumnCount(); i++) {
			if(i!=5) {
				tblDestins.getColumnModel().getColumn(i).setCellRenderer(celes);
			}
		}
		
		tblDestins.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDestinsScroll = new JScrollPane(tblDestins);
		afegirDadesTaula();
				
		/*
		 * FI Jtable
		 */
		
		btnsortit = new JButton("Sortir");
		btnsortit.addActionListener(controlador);
		btnsortit.setActionCommand("sortir");

	}

	/**
	 * Metode per afegir les dades del desti a la taula
	 */
	
	private void afegirDadesTaula() {
		Map<String, Desti> destins;
		try {
			destins = ControladorPresentacio.getDestins();
			for(Entry<String,Desti> key: destins.entrySet()) {
				Desti desti= key.getValue();
				Object[] dadesFila = new Object[6];
				dadesFila[0]=desti.getId();
				dadesFila[1]=desti.getProvincia();
				dadesFila[2]=desti.getComarca();
				dadesFila[3]=desti.getPoblacio();
				dadesFila[4]=desti.getCP();
				model.addRow(dadesFila);
			}
		} catch (GestioDBException e) {
			JOptionPane.showInternalMessageDialog(vistaAltaPi.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
		} 

		
	}

	/**
	 * Metode Sobreescrit de Formulari
	 * per afegir els components necesaris a la vista 
	 */
	
	@Override
	public void afegirComponents() {
		add(tblDestinsScroll);
		add(btnsortit);
		
	}

	/**
	 * Metode Sobreescrit de Formulari
	 * per posicionar els components necesaris a la vista 
	 */
	
	@Override
	public void posicionarComponents() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Taula
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		gbc.gridheight = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 1; 
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets= new Insets(5,5,5,5);
		layout.setConstraints(tblDestinsScroll, gbc);
		
		//btnAceptar
		gbc.gridx = 0; 
		gbc.gridy = 3; 
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
		
		/**
		 * Metode Sobreescrit actionPerformed
		 * controlara els events dels botons.
		 */

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj instanceof JButton) {
				JButton boto = (JButton) obj;
				if(boto.getActionCommand().equals("sortir")) {
					ControladorPresentacio.cambiVista(new VistaDefault());
				}
			}
		}

		/**
		 * Metode Sobreescrit actionPerformed
		 * controlara els events del ratoli
		 * obrira les dades al fer click
		 */
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int fila= tblDestins.rowAtPoint(e.getPoint());
			int columna= tblDestins.columnAtPoint(e.getPoint());
			if(fila>-1 && columna>-1) {
				Desti desti = null;
				try {
					desti = ControladorPresentacio.getDesti((String) model.getValueAt(fila, 0));
				} catch (GestioDBException e1) {
					JOptionPane.showInternalMessageDialog(vistaAltaPi.getContentPane(), e1.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
				} 
				new VistaDetallDesti(desti);
			}
		}
	
	}
	
}

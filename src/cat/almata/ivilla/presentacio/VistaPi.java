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

import cat.almata.ivilla.domini.Pi;
import cat.almata.ivilla.persistencia.GestioDBException;
import cat.almata.ivilla.utils.Util;

/**
 * Clase VistaDesti
 * Clase per mostrar les dades dels Punts d'interes
 * @author Ivilla
 * @version 1.0
 */

public class VistaPi extends JInternalFrame  implements Formulari {

	/**
	 * @serial VersionUID
	 */
		
	private static final long serialVersionUID = 1L;
	
	/**
	 * Declaracio de les constants d'informació
	 * MODEL
	 */
		
	private static final String TITOL="Consulta Punts D'interes";

	/**
	 * Declaracio dels atributs
	 * VISTA
	 */
		
	private GridBagLayout layout;
	private DefaultTableModel model;
	private Controlador controlador;
	private JTable tblPis;
	private JScrollPane tblPisScroll;
	private JButton btnsortit;

	private VistaPi vistaPi;

	/**
	 * Constructor
	 */
	
	public VistaPi() {
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
	    vistaPi = this;
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
			 * Farem les files no editables
			 */
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		/*
		 * Afegim columnes de la taula
		 */
		
		model.addColumn("Id");
		model.addColumn("Nom");
		model.addColumn("Descripcio");
		
		/*
		 * Comencem a crear la taula qapartir del model 
		 */
		
		tblPis = new JTable(model);
		tblPis.addMouseListener(controlador);
		DefaultTableCellRenderer celes = new DefaultTableCellRenderer();
		celes.setVerticalAlignment(JTextField.CENTER);
		celes.setHorizontalAlignment(JTextField.CENTER);
		for(int i=0; i < tblPis.getColumnCount(); i++) {
			if(i!=5) {
				tblPis.getColumnModel().getColumn(i).setCellRenderer(celes);
			}
		}
		
		tblPis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPisScroll = new JScrollPane(tblPis);
		afegirDadesTaula();
		
		/*
		 * FI Jtable
		 */
		
		btnsortit = new JButton("Sortir");
		btnsortit.addActionListener(controlador); 
		btnsortit.setActionCommand("sortir");
		
	}

	/**
	 * Metode per afegir les dades del punt d'interes a la taula
	 */
	
	private void afegirDadesTaula() {
		Map<String, Pi> pis;
		try {
			pis = ControladorPresentacio.getPis();
			for(Entry<String,Pi> key: pis.entrySet()) {
				Pi pi= key.getValue();
				Object[] dadesFila = new Object[3];
				dadesFila[0]=pi.getId();
				dadesFila[1]=pi.getNom();
				dadesFila[2] = pi.getDescripcio();
				model.addRow(dadesFila);
			}
		} catch (GestioDBException e) {
			JOptionPane.showInternalMessageDialog(vistaPi.getContentPane(), e.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
		}

		
	}

	/**
	 * Metode Sobreescrit de Formulari
	 * per afegir els components necesaris a la vista 
	 */
	
	@Override
	public void afegirComponents() {
		add(tblPisScroll);
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
		layout.setConstraints(tblPisScroll, gbc);
		
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
			int fila= tblPis.rowAtPoint(e.getPoint());
			int columna= tblPis.columnAtPoint(e.getPoint());
			if(fila>-1 && columna>-1) {
				Pi pi = null;
				try {
					pi = ControladorPresentacio.getPi((String) model.getValueAt(fila, 0));
					new VistaDetallPi(pi);
				} catch (GestioDBException e1) {
					JOptionPane.showInternalMessageDialog(vistaPi.getContentPane(), e1.getMessage(), "Advertencies", JOptionPane.WARNING_MESSAGE);			
				}


			}
		}

	}
	
}

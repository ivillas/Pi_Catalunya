package cat.almata.ivilla.presentacio;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import cat.almata.ivilla.utils.Util;

/**
 * Clase Aplicacio
 * Conte tota la gestio de les opcions de la barra del menu.
 * @author Ivan Villa
 * @version 1.1
 * V1.1
 * Afegit enllas en menu ajuda per descarregar la base de dades necesaria
 */


public class Aplicacio extends JFrame {
	
	/**
	 * @serial VersionUID
	 */

	
	private static final long serialVersionUID = 1L;

	/**
	 * Declaracio dels atributs
	 * VISTA
	 */
	
	private JMenu menuGestio;
	private JMenuItem itemAltaDesti;
	private JMenuItem itemEditBaixaDesti;
	private JMenuItem itemAltaPi;
	private JMenuItem itemEditBaixaPi;
	private JMenuItem itemExit;
	private JMenu menuConsulta;
	private JMenuItem itemConsultaDesti;
	private JMenuItem itemConsultaPi;
	private JMenuItem itemConsultaPiDesti;
	private JMenu menuAjudaa;
	private JMenuItem itemAjuda;
	private JMenuItem itemInfo;
	private JMenuItem itemBD;
	private Controlador controlador;
	public static final int AMPLADA=800;
	public static final int ALÇADA=800;

	/**
	 * Constructor
	 */
	
	public Aplicacio(String titol) {
		inicialitzacions(titol);
		
	}

	/**
	 * Metode Sobreescrit de Formulari
	 * per inicialitzar la vista 
	 */

	private void inicialitzacions(String titol) {
		this.setBounds(0, 0, AMPLADA, ALÇADA);
		this.setDefaultCloseOperation(3);
		this.setTitle(titol);
		Util.centrarFinestra(this);
		this.setIconImage(new ImageIcon("imatges/icona.png").getImage());
	    Container c= this.getContentPane();
		((JComponent) c).setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
		controlador = new Controlador(); //instancia abans d'utilitzarlo
		this.setJMenuBar(crearMenu());

	}
	
	/**
	 * Metode per a crear el menu
	 * @return barraMenu
	 */
	
	private JMenuBar crearMenu() {
		
		/**
		 * Barra Menu
		 */
		
		JMenuBar barraMenu = new JMenuBar();
		
		/**
		 * Primera opció Gestió
		 */
		
		menuGestio= new JMenu("Gestió");
		
		/**
		 * Opció del menu gestió: alta de desti
		 */
		
		itemAltaDesti= new JMenuItem("Alta desti");
		itemAltaDesti.setMnemonic(KeyEvent.VK_A);
		itemAltaDesti.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.ALT_MASK));
		itemAltaDesti.setActionCommand("altadesti");
		itemAltaDesti.addActionListener(controlador);
		
		/**
		 * Opció del menu gestió: Baixa de desti
		 */
		
		itemEditBaixaDesti= new JMenuItem("Editar/Esborrar Desti");
		itemEditBaixaDesti.setMnemonic(KeyEvent.VK_B);
		itemEditBaixaDesti.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.ALT_MASK));
		itemEditBaixaDesti.setActionCommand("editbaixadesti");
		itemEditBaixaDesti.addActionListener(controlador);
		
		/**
		 * Opció del menu gestió: alta de Punt d'interes
		 */
		
		itemAltaPi= new JMenuItem("Alta PI");
		itemAltaPi.setMnemonic(KeyEvent.VK_P);
		itemAltaPi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.ALT_MASK));
		itemAltaPi.setActionCommand("altapi");
		itemAltaPi.addActionListener(controlador);
		
		/**
		 * Opció del menu gestió: Baixa de Punt d'interes
		 */
		
		itemEditBaixaPi= new JMenuItem("Editar/Esborrar PI");
		itemEditBaixaPi.setMnemonic(KeyEvent.VK_R);
		itemEditBaixaPi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.ALT_MASK));
		itemEditBaixaPi.setActionCommand("editbaixapi");
		itemEditBaixaPi.addActionListener(controlador);
		
		/**
		 * Opció del menu gestió: Sortir de l'aplicació
		 */
		
		itemExit= new JMenuItem("Tancar");
		itemExit.setMnemonic(KeyEvent.VK_T);
		itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.ALT_MASK));
		itemExit.setActionCommand("tancar");
		itemExit.addActionListener(controlador);
		
		/**
		 * Possar items dins del menu gestió
		 */
		
		menuGestio.add(itemAltaDesti);
		menuGestio.add(itemEditBaixaDesti);
		menuGestio.addSeparator();
		menuGestio.add(itemAltaPi);
		menuGestio.add(itemEditBaixaPi);
		menuGestio.addSeparator();
		menuGestio.add(itemExit);
		
		/**
		 * Segona opció consulta
		 */
		
		menuConsulta= new JMenu("Consultes");
		
		/**
		 * Opcio menu consulta: Consulta destins
		 */
		
		itemConsultaDesti= new JMenuItem("Consulta destins");
		itemConsultaDesti.setMnemonic(KeyEvent.VK_C);
		itemConsultaDesti.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.ALT_MASK));
		itemConsultaDesti.setActionCommand("consultadesti");
		itemConsultaDesti.addActionListener(controlador);
		
		/**
		 * Opcio menu consulta: Consulta PI
		 */
		
		itemConsultaPi= new JMenuItem("Consulta PI");
		itemConsultaPi.setMnemonic(KeyEvent.VK_I);
		itemConsultaPi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.ALT_MASK));
		itemConsultaPi.setActionCommand("consultapi");
		itemConsultaPi.addActionListener(controlador);
		
		/**
		 * Opcio menu consulta: Consulta PI dels destins
		 */
		
		itemConsultaPiDesti= new JMenuItem("Consulta PI/Destins");
		itemConsultaPiDesti.setMnemonic(KeyEvent.VK_N);
		itemConsultaPiDesti.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.ALT_MASK));
		itemConsultaPiDesti.setActionCommand("consultapidesti");
		itemConsultaPiDesti.addActionListener(controlador);
		
		/**
		 * Possar items dins del menu consulta
		 */
		
		menuConsulta.add(itemConsultaDesti);
		menuConsulta.add(itemConsultaPi);
		menuConsulta.add(itemConsultaPiDesti);
		
		/**
		 * Tercera opció ajuda
		 */
		
		menuAjudaa= new JMenu("Ajuda");
		
		/**
		 * Opcio menu ajuda: Ajuda de la aplicació
		 */
		
		itemAjuda= new JMenuItem("Ajuda (javaDoc)");
		itemAjuda.setMnemonic(KeyEvent.VK_J);
		itemAjuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,ActionEvent.ALT_MASK));
		itemAjuda.setActionCommand("ajuda");
		itemAjuda.addActionListener(controlador);
		
		/**
		 * Opcio menu ajuda: Informació de la aplicació
		 */
		
		itemInfo= new JMenuItem("Informació");
		itemInfo.setMnemonic(KeyEvent.VK_F);
		itemInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.ALT_MASK));
		itemInfo.setActionCommand("info");
		itemInfo.addActionListener(controlador);
		
		/**
		 * Opcio menu ajuda: Descarrega base de dades
		 */
		
		itemBD= new JMenuItem("Base de dades");
		itemBD.setMnemonic(KeyEvent.VK_D);
		itemBD.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.ALT_MASK));
		itemBD.setActionCommand("bd");
		itemBD.addActionListener(controlador);
		
		/**
		 * Possar items dins del menu ajuda
		 */
		
		menuAjudaa.add(itemAjuda);
		menuAjudaa.add(itemInfo);
		menuAjudaa.add(itemBD);
			
		/**
		 * Afegir opcions a la barra menú
		 */
		
		barraMenu.add(menuGestio);
		barraMenu.add(menuConsulta);
		barraMenu.add(menuAjudaa);
	
		return barraMenu;
	}
	
	/**
	 * Controlador intern per controlar les accions/events de cada opció del menu	 
	 */
	
	public class Controlador implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj instanceof JMenuItem) {
				JMenuItem jmi=(JMenuItem)obj;
				switch (jmi.getActionCommand()) {
				case "altadesti":
					ControladorPresentacio.cambiVista(new VistaAltaDesti());	
					break;
				case "editbaixadesti":
					ControladorPresentacio.cambiVista(new VistaEditRemoveDesti());	
					break;
				case "altapi":
					ControladorPresentacio.cambiVista(new VistaAltaPi());	
					break;
				default:
					break;
				case "editbaixapi":
					ControladorPresentacio.cambiVista(new VistaEditRemovePi());	
					break;
				case "tancar":
					 System.exit(0);	
					break;
				case "consultadesti":
					ControladorPresentacio.cambiVista(new VistaDesti());	
					break;
				case "consultapi":
					ControladorPresentacio.cambiVista(new VistaPi());	
					break;
				case "consultapidesti":
					ControladorPresentacio.cambiVista(new VistaDestiPi());	
					break;
				case "info":
					ControladorPresentacio.cambiVista(new VistaInfo());	
					break;
				case "ajuda":
					 File htmlFile = new File("doc/index.html");
					 try {
						Desktop.getDesktop().browse(htmlFile.toURI());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				case "bd":
					 File file = new File("imatges/bd.sql");
					 try {
						Desktop.getDesktop().open(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;

				}
				
			}
			
		}
		
	}
	
}

package cat.almata.ivilla.utils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Util {
	public static void centrarFinestra(JFrame finestra) {
		// **Centrar la finestra a la pantalla**
		// Aquest m�tode retorna les dimensions de la pantalla
		Dimension dimensionsPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		// Obtenim el tamany de la finestra
		Dimension tamanyFinestra = finestra.getSize();
		// Centrem la finestra a la pantalla
		finestra.setLocation((dimensionsPantalla.width - tamanyFinestra.width) / 2,
				(dimensionsPantalla.height - tamanyFinestra.height) / 2);
	}

	public static void treureBarraTitolInteralFrame(JInternalFrame ifal) {
		((BasicInternalFrameUI) ifal.getUI()).setNorthPane(null);
	}

	public static TitledBorder crearBorder(String titol, Color color) {
		TitledBorder tb = BorderFactory.createTitledBorder(titol);
		tb.setTitleColor(color);
		return tb;
	}

	public static ImageIcon redimensionarImatge(String imatge, int amplada, int alcada) {
		// redimensionar imatge (straight)
		return new ImageIcon(
				new ImageIcon(imatge).getImage().getScaledInstance(amplada, alcada, java.awt.Image.SCALE_SMOOTH));
	}

}

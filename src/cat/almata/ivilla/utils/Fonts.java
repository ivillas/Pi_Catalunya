package cat.almata.ivilla.utils;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;

public class Fonts {
	private static String font = null;

	public static Font fontLabel() {
		return new Font("Arial", Font.BOLD, 12);
	}

	public static Font fontTextField() {
		if (font == null)
			inicialitzaFontMonoespaida();
		return new Font(font, Font.PLAIN, 12);
	}

	public static Font fontTitol() {
		return new Font("Verdana", Font.BOLD, 12);
	}

	public static Font fontTitolTaula() {
		return new Font("Dialog", Font.BOLD, 13);
	}

	public static void inicialitzaFontMonoespaida() {
		if (existeixFont("Monospaced"))
			font = "Monospaced";
		else if (existeixFont("Lucida Console"))
			font = "Lucida Console";
	}

	public static String[] fontsDisponibles() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	}

	public static boolean existeixFont(String nomFont) {
		return (Arrays.asList(fontsDisponibles()).contains(nomFont));
	}

}

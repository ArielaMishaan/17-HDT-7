import java.util.Comparator;
/*
 * Ariela Mishaan (22052)
 * Algoritmos y Estructuras de Datos Sección 40
 * Hoja de Trabajo 7
 * 20-03-2023
 * Clase Comparador de palabras: compara las plabras. Tomada del repositorio de la clase. 
 */

/**
 * @author MAAG
 *
 */
public class ComparadorPalabras<String> implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		if (o1 == o2) {
			return 0;
		} else if ((int)o1 > (int)o2) {
			return 1;
		} else {
			return -1;
		}
	}

}

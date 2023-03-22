import java.util.Comparator;
import java.lang.String;

/**
 * @author MAAG
 *
 */
public class ComparadorPalabras implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {

		int comparacion = o1.compareTo(o2);

		if (o1.equals(o2)) {
			return 0;
		} else if (comparacion > 0) {
			return 1;
		} else {
			return -1;
		}
	}
}

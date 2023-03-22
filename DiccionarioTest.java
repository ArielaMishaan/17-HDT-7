import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
/*
 * Ariela Mishaan (22052)
 * Algoritmos y Estructuras de Datos Sección 40
 * Hoja de Trabajo 4
 * 20-02-2023
 * Clase CalculadoraTest: Pruebas Unitarias
 */


public class DiccionarioTest {

	//Test para insertar elementos en el BST
    @Test
    void abrirArchivoPalabrasTest(){
		Diccionario diccionario = new Diccionario();
		
		ArrayList<String> palabras = new ArrayList<String>();
		palabras.add("dog,perro,chien");
		palabras.add("woman,mujer,femme");
		palabras.add("man,hombre,homme");

		diccionario.abrirArchivoPalabras(palabras);

		boolean boolean1 = diccionario.getIngles().contains("dog");
		assertEquals(true, boolean1);
		boolean boolean2 = diccionario.getIngles().contains("woman");
		assertEquals(true, boolean2);
		boolean boolean3 = diccionario.getIngles().contains("man");
		assertEquals(true, boolean3);


		boolean boolean4 = diccionario.getEspaniol().contains("perro");
		assertEquals(true, boolean4);
		boolean boolean5 = diccionario.getEspaniol().contains("mujer");
		assertEquals(true, boolean5);
		boolean boolean6 = diccionario.getEspaniol().contains("hombre");
		assertEquals(true, boolean6);

		boolean boolean7 = diccionario.getFrances().contains("chien");
		assertEquals(true, boolean7);
		boolean boolean8 = diccionario.getFrances().contains("femme");
		assertEquals(true, boolean8);
		boolean boolean9 = diccionario.getFrances().contains("homme");
		assertEquals(true, boolean9);
    }

	//Test para el recorrido in order
	@Test
	void inOrderTest(){

		Diccionario diccionario = new Diccionario();
		
		ArrayList<String> palabras = new ArrayList<String>();
		palabras.add("dog,perro,chien");
		palabras.add("woman,mujer,femme");
		palabras.add("man,hombre,homme");

		diccionario.abrirArchivoPalabras(palabras);

		String resultadoEsperadoIngles = "\n\nDICCIONARIO DE INGLÉS" + "\n-----------------------" + "\n\ndog: " + "\nEspañol: perro\nFrancés: chien\n\nman: \nEspañol: hombre\nFrancés: homme\n\nwoman: \nEspañol: mujer\nFrancés: femme" ;
		String resultado = diccionario.mostrarPalabrasInOrder(1);
		assertEquals(resultadoEsperadoIngles, resultado);

		String resultadoEsperadoEspaniol = "\n\nDICCIONARIO DE ESPAÑOL" + "\n-----------------------" + "\n\nhombre: " + "\nInglés: man\nFrancés: homme\n\nmujer: \nInglés: woman\nFrancés: femme\n\nperro: \nInglés: dog\nFrancés: chien" ;
		String resultado2 = diccionario.mostrarPalabrasInOrder(2);
		assertEquals(resultadoEsperadoEspaniol, resultado2);

		String resultadoEsperadoFrances = "\n\nDICCIONARIO DE FRANCÉS" + "\n-----------------------" + "\n\nchien: " + "\nInglés: dog\nEspañol: perro\n\nfemme: \nInglés: woman\nEspañol: mujer\n\nhomme: \nInglés: man\nEspañol: hombre" ;
		String resultado3 = diccionario.mostrarPalabrasInOrder(3);
		assertEquals(resultadoEsperadoFrances, resultado3);
	}

	//Test para detectar el idioma del documento
	@Test
	void detectarIdiomaTest(){

		Diccionario diccionario = new Diccionario();

		ArrayList<String> palabras = new ArrayList<String>();
		palabras.add("dog,perro,chien");
		palabras.add("woman,mujer,femme");
		palabras.add("man,hombre,homme");
		palabras.add("house,casa,loger");
		palabras.add("pizza,pizza,pizza");
		palabras.add("hello,hola,bonjour");

		diccionario.abrirArchivoPalabras(palabras);

		ArrayList<String> oracionesEspaniol = new ArrayList<String>();
		oracionesEspaniol.add("Hola el perro está en la casa");
		oracionesEspaniol.add("El hombre y la mujer");

		int idiomaEspaniol = diccionario.detectarIdioma(oracionesEspaniol);
		assertEquals(2, idiomaEspaniol);

		ArrayList<String> oracionesIngles = new ArrayList<String>();
		oracionesIngles.add("Hello the man is eating a pizza");
		oracionesIngles.add("The woman says hello and is inside the house");

		int idiomaIngles = diccionario.detectarIdioma(oracionesIngles);
		assertEquals(1, idiomaIngles);

		ArrayList<String> oracionesFrances = new ArrayList<String>();
		oracionesFrances.add("Bonjour le chien mange");
		oracionesFrances.add("L'homme est dans le loger et il mange une pizza");

		int idiomaFrances = diccionario.detectarIdioma(oracionesFrances);
		assertEquals(3, idiomaFrances);
	}

	//Test para buscar asociación basado en el valor
	@Test
	void buscarAsociacionTest(){
		
		Diccionario diccionario = new Diccionario();

		ArrayList<String> palabras = new ArrayList<String>();
		palabras.add("dog,perro,chien");
		palabras.add("woman,mujer,femme");
		palabras.add("man,hombre,homme");
		palabras.add("house,casa,loger");
		palabras.add("pizza,pizza,pizza");
		palabras.add("hello,hola,bonjour");

		diccionario.abrirArchivoPalabras(palabras);

		BinarySearchTree ingles = diccionario.getIngles();
		Association entradaDog = (Association) ingles.search("dog");
		String[] traducciones = (String[]) entradaDog.getValue();
		assertEquals("perro", traducciones[0]);

		BinarySearchTree espaniol = diccionario.getEspaniol();
		Association entradaMujer = (Association) espaniol.search("mujer");
		String[] traducciones2 = (String[]) entradaMujer.getValue();
		assertEquals("femme", traducciones2[1]);

		BinarySearchTree frances = diccionario.getFrances();
		Association entradaBonjour = (Association) frances.search("bonjour");
		String[] traducciones3 = (String[]) entradaBonjour.getValue();
		assertEquals("hello", traducciones3[0]);

	}

	//Test para traducir documento
	@Test
	void traducirTest(){
		
		Diccionario diccionario = new Diccionario();

		ArrayList<String> palabras = new ArrayList<String>();
		palabras.add("dog,perro,chien");
		palabras.add("woman,mujer,femme");
		palabras.add("man,hombre,homme");
		palabras.add("house,casa,loger");
		palabras.add("pizza,pizza,pizza");
		palabras.add("hello,hola,bonjour");

		diccionario.abrirArchivoPalabras(palabras);

		ArrayList<String> oracionesEspaniol = new ArrayList<String>();
		oracionesEspaniol.add("Hola el perro está en la casa");
		oracionesEspaniol.add("El Hombre y la mujer");

		String espaniolAIngles = diccionario.traducirDocumento(oracionesEspaniol, 2, 1);
		String espaniolAInglesEsperado = "\n hello *el* dog *está* *en* *la* house\n *el* man *y* *la* woman";
		String espaniolAlFrances = diccionario.traducirDocumento(oracionesEspaniol, 2, 3);
		String espaniolAFrancesEsperado = "\n bonjour *el* chien *está* *en* *la* loger\n *el* homme *y* *la* femme";

		assertEquals(espaniolAInglesEsperado, espaniolAIngles);
		assertEquals(espaniolAFrancesEsperado, espaniolAlFrances);

		ArrayList<String> oracionesIngles = new ArrayList<String>();
		oracionesIngles.add("Hello the dog is in the house");
		oracionesIngles.add("The Man and the Woman");

		String inglesEspaniol = diccionario.traducirDocumento(oracionesIngles, 1, 2);
		String inglesEspaniolEsperado = "\n hola *the* perro *is* *in* *the* casa\n *the* hombre *and* *the* mujer";
		String inglesFrances = diccionario.traducirDocumento(oracionesIngles, 1, 3);
		String inglesFrancesEsperado = "\n bonjour *the* chien *is* *in* *the* loger\n *the* homme *and* *the* femme";

		assertEquals(inglesEspaniolEsperado, inglesEspaniol);
		assertEquals(inglesFrancesEsperado, inglesFrances);

		ArrayList<String> oracionesFrances = new ArrayList<String>();
		oracionesFrances.add("Bonjour le chien est dans le loger");
		oracionesFrances.add("L' homme et la Femme");

		String francesIngles = diccionario.traducirDocumento(oracionesFrances, 3, 1);
		String francesInglesEsperado = "\n hello *le* dog *est* *dans* *le* house\n *l'* man *et* *la* woman";
		String francesEspaniol = diccionario.traducirDocumento(oracionesFrances, 3, 2);
		String francesEspaniolEsperado = "\n hola *le* perro *est* *dans* *le* casa\n *l'* hombre *et* *la* mujer";

		assertEquals(francesInglesEsperado, francesIngles);
		assertEquals(francesEspaniolEsperado, francesEspaniol);
	}

}

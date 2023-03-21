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

    Diccionario diccionario = new Diccionario();


	//Test para insertar elementos en el BST
    @Test
    void abrirArchivoPalabrasTest(){
        Stack<Integer> stack = fabricaStack.getInstanceStack(1,0);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        int resultado = 0;
        resultado = stack.pull();
        assertEquals(2, resultado);
    }

	//Test para el recorrido in order
	@Test
	void inOrderTest(){

	}

	//Test para detectar el idioma del documento
	@Test
	void detectarIdiomaTest(){

	}

	//Test para buscar asociación basado en el valor
	@Test
	void buscarAsociacionTest(){

	}

	//Test para traducir documento
	@Test
	void traducirTest(){
		
	}

}

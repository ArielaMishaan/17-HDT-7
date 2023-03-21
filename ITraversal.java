/*
 * Ariela Mishaan (22052)
 * Algoritmos y Estructuras de Datos Sección 40
 * Hoja de Trabajo 7
 * 20-03-2023
 * Interfaz ITraversal: sirve para visitar los nodos del BST (tomada del repositorio de la clase)
 */


/**
 * @author MAAG
 *
 */
public interface ITraversal<K, V> {

	void visit(TreeNode<K, V> actualNode);
	
}

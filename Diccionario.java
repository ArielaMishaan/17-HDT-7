/*
 * Ariela Mishaan (22052)
 * Algoritmos y Estructuras de Datos Sección 40
 * Hoja de Trabajo 7
 * 20-03-2023
 * Clase Diccionario: clase controladora para realizar todas las acciones que se piden en la tarea.
 */

import java.util.ArrayList;

public class Diccionario {

    private BinarySearchTree<String, Association> ingles;
    private BinarySearchTree<String, Association> espaniol;
    private BinarySearchTree<String, Association> frances;
    private ComparadorPalabras comparadorPalabras;

    
    public Diccionario() {
        this.ingles = new BinarySearchTree<String, Association>(comparadorPalabras);
        this.espaniol = new BinarySearchTree<String, Association>(comparadorPalabras);
        this.frances = new BinarySearchTree<String, Association>(comparadorPalabras);
    }

    /**
     * Constructor con parámetros
     * @param ingles
     * @param espaniol
     * @param frances
     */
    public Diccionario(BinarySearchTree<String,Association> ingles, BinarySearchTree<String,Association> espaniol, BinarySearchTree<String,Association> frances) {
        this.ingles = ingles;
        this.espaniol = espaniol;
        this.frances = frances;
    }

    /***
     * Con la lista de las líneas del archivo categoriza la información y la guarda en el BST
     * @param lineas
     */
    public void abrirArchivoPalabras(ArrayList<String> lineasPalabras){

        for (String linea : lineasPalabras) {

            String [] traduccionesIngles = new String[2];
            String [] traduccionesEspaniol = new String[2];
            String [] traduccionesFrances = new String[2];
            
            String[] traducciones = linea.split(",");

            String palabraIngles = traducciones[0];
            String palabraEspaniol = traducciones[1];
            String palabraFrances = traducciones[2];

            traduccionesIngles[0] = palabraEspaniol;
            traduccionesIngles[1] = palabraFrances;
            traduccionesEspaniol[0] = palabraIngles;
            traduccionesEspaniol[1] = palabraFrances;
            traduccionesFrances[0] = palabraIngles;
            traduccionesFrances[1] = palabraEspaniol; 

            Association entradaIngles = new Association(palabraIngles.toLowerCase(), traduccionesIngles);
            Association entradaEspaniol = new Association(palabraEspaniol.toLowerCase(), traduccionesEspaniol);
            Association entradaFrances = new Association(palabraFrances.toLowerCase(), traduccionesFrances);

            ingles.insert(palabraIngles, entradaIngles);
            espaniol.insert(palabraEspaniol, entradaEspaniol);
            frances.insert(palabraFrances, entradaFrances);
        }
    }

    /**
     * Dependiendo del diccionario que el usuario quiera abrir muestra las palabras en orden. 
     * @param idioma
     * @return la lista de palabras en orden
     */
    public String mostrarPalabrasInOrder(int idioma){

        String resultado = "";
        VisitadorDeNodos visitador = new VisitadorDeNodos<String, Association>();        

        switch (idioma) {
            case 1: //inglés
                resultado = resultado + "\n\nDICCIONARIO DE INGLÉS" + "\n-----------------------";

                ingles.InOrderTraversal(visitador);
                
                for (int i = 0; i < visitador.getMiListado().size(); i++) {

                    Association asociacionActual = (Association) visitador.getMiListado().get(i);
                    String[] traducciones = (String[]) asociacionActual.getValue();
                    resultado = resultado + "\n\n" + asociacionActual.getKey() + ": " + "\nEspañol: " + traducciones[0] + "\nFrancés: " + traducciones [1]; 
                } 

                break;

            case 2: //español

                resultado = resultado + "\n\nDICCIONARIO DE ESPAÑOL" + "\n-----------------------";

                espaniol.InOrderTraversal(visitador);
                
                for (int i = 0; i < visitador.getMiListado().size(); i++) {

                    Association asociacionActual = (Association) visitador.getMiListado().get(i);
                    String[] traducciones = (String[]) asociacionActual.getValue();
                    resultado = resultado + "\n\n" + asociacionActual.getKey() + ": " + "\nInglés: " + traducciones[0] + "\nFrancés: " + traducciones [1]; 
                } 

                break;

            case 3: //francés
                
                resultado = resultado + "\n\nDICCIONARIO DE FRANCÉS" + "\n-----------------------";

                frances.InOrderTraversal(visitador);
                
                for (int i = 0; i < visitador.getMiListado().size(); i++) {

                    Association asociacionActual = (Association) visitador.getMiListado().get(i);
                    String[] traducciones = (String[]) asociacionActual.getValue();
                    resultado = resultado + "\n\n" + asociacionActual.getKey() + ": " + "\nInglés: " + traducciones[0] + "\nEspañol: " + traducciones [1]; 
                } 

                break;
        
            
            default:
                resultado = resultado +"\nOpción incorrecta.";
                break;
        }
        return resultado;
    }
 
    /**
     * Basándose en la primera línea del archivo de texto, busca coincidencias en los árboles para determinar el idioma del archivo.
     * @param lineasOraciones
     * @return el idioma (1 = inglés, 2 = español, 3 = francés)
     */
    public int detectarIdioma(ArrayList<String> lineasOraciones){
        int idioma = 0;

        int contadorIngles = 0;
        int contadorEspaniol = 0;
        int contadorFrances = 0;

        String lineaInicial = lineasOraciones.get(0);
        String[] palabras = lineaInicial.split(" ");
        
        for (String palabra : palabras) {
            if(ingles.search(palabra) != null){
                contadorIngles++;
            }
            else if(espaniol.search(palabra) != null){
                contadorEspaniol++;
            }
            else if(frances.search(palabra) != null){
                contadorFrances++;
            }
        }
        
        if(contadorIngles >= contadorEspaniol && contadorIngles >= contadorFrances){
            idioma =1;
        }
        else if(contadorEspaniol >= contadorIngles && contadorEspaniol >= contadorFrances){
            idioma =1;
        }
        else if(contadorFrances >= contadorEspaniol && contadorFrances >= contadorIngles){
            idioma =1;
        }

        return idioma;
    }

    /**
     * En base al idioma de entrada y el idioma de salida, se traduce todo el archivo de texto.
     * @param lineasOraciones
     * @param idiomaOrigen
     * @param idiomaDestino
     * @return el documento traducido
     */
    public String traducirDocumento(ArrayList<String> lineasOraciones, int idiomaOrigen, int idiomaDestino){
        String resultado = "";

        //por cada línea en mi archivo
        for (String linea : lineasOraciones) {
            resultado = resultado + "\n";

            //separo la línea en palabras
            String[] palabras = linea.split(" ");

            //a cada palabra voy a buscarle la traducción
            for (String palabra : palabras) {

                //depende del idioma de origen en qué BST busco la llave
                switch (idiomaOrigen) {

                    case 1: //inglés

                        Association entradaIngles = ingles.search(palabra.toLowerCase());
                        String [] traduccionesIngles = (String[]) entradaIngles.getValue();
                    
                        switch (idiomaDestino) {

                            case 2: //español
                                if (ingles.contains(palabra)){
                                    resultado = resultado + " " + traduccionesIngles[0];    
                                }
                                else{
                                    resultado = resultado + " *" + palabra + "*";
                                }
                                
                                break;

                            case 3: //francés
                                if (ingles.contains(palabra)){
                                    resultado = resultado + " " + traduccionesIngles[1];    
                                }
                                else{
                                    resultado = resultado + " *" + palabra + "*";
                                }
                                break;
                        
                            default:
                                break;
                        }

                        break;

                    case 2: //español

                        Association entradaEspaniol = espaniol.search(palabra.toLowerCase());
                        String[] traduccionesEspaniol = (String[]) entradaEspaniol.getValue();

                        switch (idiomaDestino) {

                            case 1: //inglés
                                if (espaniol.contains(palabra)){
                                    resultado = resultado + " " + traduccionesEspaniol[0];    
                                }
                                else{
                                    resultado = resultado + " *" + palabra + "*";
                                }
                                break;

                            case 3: //francés
                                if (espaniol.contains(palabra)){
                                    resultado = resultado + " " + traduccionesEspaniol[1];    
                                }
                                else{
                                    resultado = resultado + " *" + palabra + "*";
                                }
                                break;
                        
                            default:
                                break;
                        }
                        
                        break;

                    case 3: //francés

                        Association entradaFrances = frances.search(palabra.toLowerCase());
                        String[] traduccionesFrances = (String[]) entradaFrances.getValue();

                        switch (idiomaDestino) {
                            case 1: //inglés
                                if (frances.contains(palabra)){
                                    resultado = resultado + " " + traduccionesFrances[0];    
                                }
                                else{
                                    resultado = resultado + " *" + palabra + "*";
                                }
                                break;

                            case 2: //español
                                if (frances.contains(palabra)){
                                    resultado = resultado + " " + traduccionesFrances[1];    
                                }
                                else{
                                    resultado = resultado + " *" + palabra + "*";
                                }
                                break;
                        
                            default:
                                break;
                        }
                        break;
                
                    default:
                        break;
                }
            }
        }

        return resultado;
    }
    
}

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

            Association entradaIngles = new Association(palabraIngles, traduccionesIngles);
            Association entradaEspaniol = new Association(palabraEspaniol, traduccionesEspaniol);
            Association entradaFrances = new Association(palabraFrances, traduccionesFrances);

            ingles.insert(palabraIngles, entradaIngles);
            espaniol.insert(palabraEspaniol, entradaEspaniol);
            frances.insert(palabraFrances, entradaFrances);
        }
    }

    /**
     * Dependiendo del diccionario que el usuario quiera abrir muestra las palabras en orden. 
     * @param idioma
     * @return
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
     * @return
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
    
}

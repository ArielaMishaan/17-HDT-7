import java.util.ArrayList;
import java.util.Scanner;

public class PrincipalDiccionario {

    public static void main(String[] args) {
        Archivo palabras = new Archivo("./diccionario.txt");
        Archivo oraciones = new Archivo("./texto.txt");
    
        ArrayList<String> lineasPalabras = palabras.leerArchivo();
        ArrayList<String> lineasOraciones = oraciones.leerArchivo();
    
        Diccionario diccionario = new Diccionario();
        diccionario.abrirArchivoPalabras(lineasPalabras);

        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        String menu = "\n\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°";
        menu = menu + "\nDiccionario Inglés - Español - Francés\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°";
        menu = menu + "\n\n1. Mostrar palabras del diccionario.\n2. Mostrar texto traducido.\n3. Salir";

        while (opcion>= 0 && opcion < 3) {
            
            try {
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {

                    case 1://mostrar palabras del diccionario
                        System.out.println("\n1. Diccionario inglés\n2. Diccionario español\n3. Diccionario francés");
                        System.out.println("Escoja el diccionario que quiere mostrar: ");
                        int opcionDiccionario = teclado.nextInt();
                        teclado.nextLine();
                        System.out.println(diccionario.mostrarPalabrasInOrder(opcionDiccionario));
                        break;

                    case 2: //mostrar texto traducido
                        int idiomaOrigen = diccionario.detectarIdioma(lineasOraciones);
                        switch (idiomaOrigen) {
                            
                            case 1: //inglés
                                System.out.println("\n2. Español\n3. Francés");
                                System.out.println("Escoja el idioma al que quiere traducir el texto: ");
                                int idiomaDestino = teclado.nextInt();
                                teclado.nextLine();
                                System.out.println(diccionario.traducirDocumento(lineasOraciones, idiomaOrigen, idiomaDestino));
                                break;

                            case 2: //español
                                System.out.println("\n1. Inglés\n3. Francés");
                                System.out.println("Escoja el idioma al que quiere traducir el texto: ");
                                int idiomaDestino2 = teclado.nextInt();
                                teclado.nextLine();
                                System.out.println(diccionario.traducirDocumento(lineasOraciones, idiomaOrigen, idiomaDestino2));
                                break;

                            case 3: //francés
                                System.out.println("\n1. Inglés\n2. Español");
                                System.out.println("Escoja el idioma al que quiere traducir el texto: ");
                                int idiomaDestino3 = teclado.nextInt();
                                teclado.nextLine();
                                System.out.println(diccionario.traducirDocumento(lineasOraciones, idiomaOrigen, idiomaDestino3));
                                break;
                        
                            default:
                                break;
                        }
                        break;

                    case 3: //salir
                        System.out.println("\nGracias por utilizar el servicio.");
                        break;
                
                    default:
                        break;
                }

            } catch (Exception e) {
                teclado.nextLine();
                System.out.println("\nEntrada inválida.");
                System.out.println();
                // TODO: handle exception
            }
        }
    }

}

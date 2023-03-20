/*
 * Ariela Mishaan (22052)
 * Algoritmos y Estructuras de Datos Sección 40
 * Hoja de Trabajo 6
 * 06-03-2023
 * Clase Archivo: sirve para la abrir archivos
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Archivo {
    
    private File archivo;

    public Archivo (String nombreArchivo){
        archivo = new File(nombreArchivo);
        try {
            archivo.createNewFile();
        } catch (IOException e) {
            // El archivo ya existe o está en uso
            e.printStackTrace();
        }
    }
    
    
    /** 
     * @param linea Recibe la línea que tiene que escribir.
     */
    public void escribirArchivo(String linea){
        try {
            FileWriter miEscritor = new FileWriter(archivo);
            miEscritor.write(linea);
            miEscritor.close();
        } catch (IOException e) {
            // Ocurrió un error en la escritura
            e.printStackTrace();
        }
    }

    
    /** 
     * @param linea la línea que tiene que escribir
     */
    public void escribirArchivo2(String linea){
        try {
            FileWriter miEscritor = new FileWriter(archivo, true);
            miEscritor.write(linea);
            miEscritor.close();
        } catch (IOException e) {
            // Ocurrió un error en la escritura
            e.printStackTrace();
        }
    }

    
    /** 
     * @return ArrayList<String> 
     */
    public ArrayList<String> leerArchivo(){
        Scanner miLector;
        ArrayList<String> lineas = new ArrayList<String>();
        try {
            miLector = new Scanner(archivo);        
            while (miLector.hasNextLine()){
                lineas.add(miLector.nextLine());
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return lineas;
    }
    
    /** 
     * @return String
     */
    public String leerArchivoString(){
        String texto = "";
        try {
            Scanner miLector = new Scanner(archivo);
            while (miLector.hasNextLine()){
                texto = texto + miLector.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return texto;
    }

    public void eliminarArchivo(){
        archivo.delete();
    }

}

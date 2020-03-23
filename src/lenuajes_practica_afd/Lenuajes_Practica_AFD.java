/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lenuajes_practica_afd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kenet Gaona
 */
public class Lenuajes_Practica_AFD {

    static List<String> Lista;
    static String[][] transMatrix;
    static List<String> CadenasAceptadas = new ArrayList<>();

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Creamos un archivo para hacer una lectura
        Path archivo_lectura;
        archivo_lectura = Paths.get("D:\\Escritorio\\Lenguajes\\lol.txt");

        //Leemos el archivo y lo asignamos el texto a una lista
        List<String> lectura = Files.readAllLines(archivo_lectura);
        transMatrix = new String[lectura.size()][];

        //Preguntamos si es que el archivo esta vacio 
        if (!lectura.isEmpty()) {
            getData(lectura);
        } else {
            System.out.println("No hay nada en el archivo");
        }
    }

    //Funcion para retornara las variables globales
    // los datos que se requieren
    private static void getData(List<String> lines) {
        String entrada = null;
        List<String> alfabeto = null;
        String i_State = null;
        String[] f_States = null;

        for (int i = 0; i < lines.size(); i++) {
            //se obtiene la entrada a analizar.
            if (i == 0) {
                //Utilizamos trim para poder quitar los espacios
                entrada = lines.get(i).trim();
            } //se obtiene el alfabeto
            else if (i == 1) {
                //Realizamos un split para eliminar el ;
                //Usamos un asList para poder pasar los parametros
                alfabeto = Arrays.asList(lines.get(i).trim().split(";"));
            } //estado inicial.
            else if (i == 2) {
                i_State = lines.get(i).trim();
            } //estados finales.
            else if (i == 3) {
                f_States = lines.get(i).trim().split(";");
            } //obtenemos la matriz de transiciones.
            else {
                //Llenamos la matriz con los datos otenidos 
                transMatrix[i - 4] = lines.get(i).trim().split(";");
            }
        }
        //toStateList(i_State, transMatrix, f_States, entrada, alfabeto);
        
        //Enviamos caracter por caracter
        for (int i = 0; i < entrada.length(); i++) {
            checkSubcadenas(i_State, transMatrix, f_States, entrada.substring(i, i + 1), alfabeto);
        }

        //Enviamos Subcadenas
        for (int i = 0; i <= entrada.length(); i++) {
            for (int j = i+1; j <=entrada.length(); j++) {
                checkSubcadenas(i_State, transMatrix, f_States, entrada.substring(i, j), alfabeto);
            }
        }

        if (!CadenasAceptadas.isEmpty()) {
            System.out.println("Cadenas aceptadas:");
            for (int i = 0; i < CadenasAceptadas.size(); i++) {
                System.out.print(CadenasAceptadas.get(i).toString() + ',');
            }
            System.out.println("");
        } else {
            System.out.println("Ninguna subcadena fue aceptada");
        }
    }

    //checamos la valides de los estados
    public static void toStateList(String i_State, String[][] transMatrix, String[] f_States, String entrada, List<String> alfabeto) {
        String r_estados = i_State + "/";
        int currentState = Integer.parseInt(i_State);

        //Iteramos la cadena de entrada para obtener la secuencia de estados visitados.
        for (int i = 0; i < entrada.length(); i++) {
            String symbol = entrada.substring(i, i + 1);
            currentState = Integer.parseInt(transMatrix[currentState][alfabeto.indexOf(symbol)]);
            r_estados += currentState + "/";
        }

        //Consultamos si el último estado visitado es parte de los estados finales. 
        //Imprimimos si es aceptada o no.    
        String last = Integer.toString(currentState);
        boolean x = false;

        for (int i = 0; i < f_States.length; i++) {
            if (f_States[i].contentEquals(last)) {
                x = true;
                break;
            }
        }

        //Comparación de si la bandera fue verdadera o falsa.
        if (x == true) {
            System.out.println("Aceptada.");
        } else {
            System.out.println("No Aceptada.");
        }

        //Impresión de la secuencia de estados.
        System.out.println("Secuencia de estados: " + r_estados);
    }

    //Imprecon de arreglo
    public static void toStingArray(String[][] transMatrix) {
        for (int i = 0; i < 3; i++) {
            System.out.print(transMatrix[i][0].toString());
            for (int j = 0; j < 2; j++) {
                System.out.print(transMatrix[i][j].toString());
            }
            System.out.println("");
        }
    }

    public static void checkSubcadenas(String i_State, String[][] transMatrix, String[] f_States, String entrada, List<String> alfabeto) {
        String r_estados = i_State + "/";
        int currentState = Integer.parseInt(i_State);

        //Iteramos la cadena de entrada para obtener la secuencia de estados visitados.
        for (int i = 0; i < entrada.length(); i++) {
            String symbol = entrada.substring(i, i + 1);
            currentState = Integer.parseInt(transMatrix[currentState][alfabeto.indexOf(symbol)]);
            r_estados += currentState + "/";
        }

        //Consultamos si el último estado visitado es parte de los estados finales. 
        //Imprimimos si es aceptada o no.    
        String last = Integer.toString(currentState);
        boolean x = false;

        for (int i = 0; i < f_States.length; i++) {
            if (f_States[i].contentEquals(last)) {
                x = true;
                break;
            }
        }

        //Comparación de si la bandera fue verdadera o falsa.
        if (x == true) {
            if(!CadenasAceptadas.contains(entrada))
            CadenasAceptadas.add(entrada);
        }
    }
}

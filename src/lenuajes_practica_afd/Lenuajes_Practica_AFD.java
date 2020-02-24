/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lenuajes_practica_afd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kenet Gaona
 */
public class Lenuajes_Practica_AFD {

    static String entrada = "";
    static String alfabeto = "";
    static String q0 = "";
    static String e_finales = "";
    static List<String> Lista;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String lectura = "";

        // Apertura del fichero y creacion de BufferedReader para poder
        // hacer una lectura comoda (disponer del metodo readLine()).
        archivo = new File("D:\\Escritorio\\Lenguajes\\texto2.txt");
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);

        // Lectura del fichero
        String linea;
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
            lectura += linea;
        }
        //System.out.println(lectura);
        getData(lectura);
    }

    //Funcion para retornara las variables globales
    // los datos que se requieren
    private static void getData(String x) {

        //Entrada
        x = x.substring(9, x.length());
        int i = x.indexOf(";");
        entrada = x.substring(0, i - 1);
        System.out.println("Entrada: " + entrada);

        //Alfabeto
        x = x.substring(i - 1);
        i = x.indexOf(" ");
        alfabeto = x.substring(0, i);
        System.out.println("Alfabeto: " + alfabeto);

        //Estado inicial
        x = x.substring(i);
        q0 = x.substring(0, 2);
        System.out.println("Estado inicial: " + q0);

        //Estado(s) final
        i = x.indexOf(" ");
        x = x.substring(i + 2);//aunmentamos 1 por la diferencia del espacio
        i = x.indexOf(" ");
        e_finales = x.substring(0, i);
        System.out.println("Estados finales: " + e_finales);

        //Lista de transisicones 
        x = x.substring(i).replaceAll(";", "").replaceAll(" ", "");
        System.out.println(x);
        Lista = toList(x);
        System.out.println("Estados finales:");
        
        //Corroboramos que se quede en un estado final
        //para garantizar que fue acepatada por el Alfabeto
        System.out.println(isFinalState(Lista, entrada, e_finales));
    }

    private static List<String> toList(String x) {
        List<String> Lista = new ArrayList<>();

        for (int i = 0; i < x.length(); i += 2) {
            Lista.add(x.substring(i, i + 2));
        }

        for (int i = 0; i < Lista.size(); i++) {
            System.out.println(Lista.get(i));
        }
        return Lista;
    }

    private static boolean isFinalState(List<String> y, String x, String f) {
        String state = "";
        for (int i = 0; i < x.length(); i++) {
            state = returnState(y,x.substring(i, i+1));
            System.out.print(state + "/");
        }
        System.out.println("");
        return f.contains(state);
    }
    
    public static String returnState(List<String> y,String x){
        //Ciclo para realizar la busqueda del siguiente estado 
        for (int i = 0; i < y.size(); i++) {
            if (y.get(i).startsWith(x)) {
                return y.get(i).substring(+1);
            }
        }
        return null;
    }

}

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

/**
 *
 * @author Kenet Gaona
 */
public class Lenuajes_Practica_AFD {
    
        static String entrada = "";
        static String alfabeto = "";
        static String q0 = "";
        static String e_finales = "";
        static String [][] matriz_F;
    /**
     * @param args the command line arguments
     */
        
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String lectura = "";
        
        // Apertura del fichero y creacion de BufferedReader para poder
        // hacer una lectura comoda (disponer del metodo readLine()).
        archivo = new File("D:\\Escritorio\\Lenguajes\\Practica 1\\texto.txt");
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
    private static void getData(String x){
        
        //Entrada
        x = x.substring(9, x.length());
        int i = x.indexOf(";");
        entrada = x.substring(0, i-1);
        System.out.println("Entrada: " + entrada);
        
        //Alfabeto
        x = x.substring(i-1);
        i = x.indexOf(" ");
        alfabeto = x.substring(0, i);
        System.out.println("Alfabeto: " + alfabeto);
        
        //Estado inicial
        x = x.substring(i);
        q0 = x.substring(0, 2);
        System.out.println("Estado inicial: " + q0);
        
        //Estado(s) final
        i = x.indexOf(" ");
        x = x.substring(i+2);//aunmentamos 1 por la diferencia del espacio
        i = x.indexOf(" ");
        e_finales = x.substring(0,i);
        System.out.println("Estados finales: " + e_finales);
        
        //Matriz de transisicones 
        x = x.substring(i);
        int renglones = 0;
        String[] matriz = x.split(" ");
        for (String matriz1 : matriz) {
            System.out.println(matriz1);
            renglones ++;
        }
        System.out.println("Renglones: "+ renglones);
       
        
        
    }
    

}

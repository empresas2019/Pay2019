/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigopsl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 2098325 Este programa permite descifrar los numeros que vienen
 * codificados en un documento txt.
 *
 */
public class CodigoPSL {

    private static Map<String, List<String>> numeros = new HashMap<String, List<String>>();

    private static List<String> arreglo;

    /**
     * @param args Inicia el programa
     * @throws java.io.IOException Se lanza una excepcion si ocurre un problema de ejecucion.
     *
     */
    public static void main(String[] args) throws IOException {
        List<String> n1 = new ArrayList<String>();
        n1.add(new String("..."));
        n1.add(new String("..|"));
        n1.add(new String("..|"));
        List<String> n2 = new ArrayList<String>();
        n2.add(new String("._."));
        n2.add(new String("._|"));
        n2.add(new String("|_."));
        List<String> n3 = new ArrayList<String>();
        n3.add(new String("._."));
        n3.add(new String("._|"));
        n3.add(new String("._|"));

        List<String> n4 = new ArrayList<String>();
        n4.add(new String("..."));
        n4.add(new String("|_|"));
        n4.add(new String("..|"));
        List<String> n5 = new ArrayList<String>();
        n5.add(new String("._."));
        n5.add(new String("|_."));
        n5.add(new String("._|"));

        List<String> n6 = new ArrayList<String>();
        n6.add(new String("._."));
        n6.add(new String("|_."));
        n6.add(new String("|_|"));
        List<String> n7 = new ArrayList<String>();
        n7.add(new String("._."));
        n7.add(new String("..|"));
        n7.add(new String("..|"));
        List<String> n8 = new ArrayList<String>();
        n8.add(new String("._."));
        n8.add(new String("|_|"));
        n8.add(new String("|_|"));
        List<String> n9 = new ArrayList<String>();
        n9.add(new String("._."));
        n9.add(new String("|_|"));
        n9.add(new String("..|"));
        List<String> n10 = new ArrayList<String>();
        n10.add(new String("._."));
        n10.add(new String("|.|"));
        n10.add(new String("|_|"));

        numeros.put("1", n1);
        numeros.put("2", n2);
        numeros.put("3", n3);
        numeros.put("4", n4);
        numeros.put("5", n5);
        numeros.put("6", n6);
        numeros.put("7", n7);
        numeros.put("8", n8);
        numeros.put("9", n9);
        numeros.put("0", n10);
        try{
            muestraContenido();        
        } catch (IOException e) {
            new Throwable("NO se pudo cargar",e );
        }

    }

    /**
     * Esta funcion permite mostrar el contenido del archivo .txt, nos da
     * acceso al texto cifrado.
     *
     * @throws java.io.FileNotFoundException Se lanaza una excepcion cuando ocurre un error al cargar el archivo.
     */
    public static void muestraContenido() throws FileNotFoundException, IOException {
        String resp = "";
        arreglo = new ArrayList<String>();
        int n = 0;
        String cadena;
        try {
            FileReader f = new FileReader("src/codigopsl/pr.txt");
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                if (n == 2) {
                    arreglo.add(cadena);
                    n = 0;
                    resp = anal();//RECORDAR DEJAR LIMPIA LA LISTA
                    cadena = "";
                } else {
                    arreglo.add(cadena);
                    n++;
                }
            }
            System.out.println("La respuesta es :" + resp);
            b.close();
        } catch (FileNotFoundException e) {
            new Throwable("NO se pudo cargar",e );
        }
    }

    /**
     *  Funcion recibe un arreglo global, de donde obtiene los vectores
     * del texto cifrado.
     * @return resp Este metodo retorna un String con la el numero descifrado
     */
    public static String anal()  {//Se analizara las 3 lineas
        List<String> temp = new ArrayList<String>();//Se leera desde la 3
        List<String> temp2 = new ArrayList<String>();
        List<String> temp3 = new ArrayList<String>();
        String resp = "";
        String firtsLine = arreglo.get(0);
        String secondLine = arreglo.get(1);
        String thirdsLine = arreglo.get(2);
        for (int i = 0; i < 37; i++) {
            String cadena1 = Character.toString(firtsLine.charAt(i));
            String cadena2 = Character.toString(secondLine.charAt(i));
            String cadena3 = Character.toString(thirdsLine.charAt(i));
            temp.add(cadena1);
            temp2.add(cadena2);
            temp3.add(cadena3);
        }//Ahora si vamos a comparar 
        int i = 0;
        while (i < 34) {
            String p1 = temp.get(i) + temp.get(i + 1) + temp.get(i + 2);
            String p2 = temp2.get(i) + temp2.get(i + 1) + temp2.get(i + 2);
            String p3 = temp3.get(i) + temp3.get(i + 1) + temp3.get(i + 2);//FASE D ECOMPARACION
            Iterator it = numeros.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                List<String> fin = numeros.get(key);
                if (fin.get(0).equals(p1)) {
                    if (fin.get(1).equals(p2)) {
                        if (fin.get(2).equals(p3)) {
                            resp += key;
                        }
                    }
                }
            }
            i++;
        }
        return resp;
    }
}

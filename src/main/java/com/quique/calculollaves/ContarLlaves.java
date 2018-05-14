/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quique.calculollaves;

import com.google.gson.*;
import java.io.*;
import javax.swing.JOptionPane;
import com.google.gson.JsonParser;
import java.util.ArrayList;

/**
 *
 * @author Quique
 */
public class ContarLlaves {

    private static File fich, fich2, fichlinks;
    private static PrintWriter escribir;
    private static int cuenta = 0;
    private static int cuenta2 = 0;
    static ArrayList<String> lportales = new ArrayList<String>();

    public static void ingresarPortales() {

        BufferedReader leer;

        String portal = JOptionPane.showInputDialog("Bookmarks: ");

        try {
            fich = new File("bkm.json");
            escribir = new PrintWriter(new FileWriter(fich, false));
            escribir.println(portal);

        } catch (FileNotFoundException ex) {
            System.out.println("Error de escritura" + ex);
        } catch (IOException ex) {
            System.out.println("Error de escritura" + ex);
        } finally {
            escribir.close();
        }

    }

    public static void leerJSonEscribirFichero() {

        FileReader fr;

        try {

            JsonParser parser = new JsonParser();
            fr = new FileReader("bkm.json");
            JsonElement datos = parser.parse(fr);
            Gson gson = new Gson();
            JsonElement bkm = datos.getAsJsonObject().get("portals");

            dumpJSONElement(bkm);
            fr.close();

            try {
                fich = new File("portales.txt");
                escribir = new PrintWriter(new FileWriter(fich, true));

                for (int i = 0; i < lportales.size(); i++) {
                    escribir.println(lportales.get(i) + " = ");
                }

            } catch (FileNotFoundException ex) {
                System.out.println("Error de escritura" + ex);
            } catch (IOException ex) {
                System.out.println("Error de escritura" + ex);
            } finally {
                escribir.close();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException " + ex);
        } catch (IOException ex) {
            System.out.println("IOException " + ex);
        }
    }

    public static void dumpJSONElement(JsonElement elemento) {

        if (elemento.isJsonObject()) {
            JsonObject obj = elemento.getAsJsonObject();
            java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
            java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry<String, JsonElement> entrada = iter.next();
                dumpJSONElement(entrada.getValue());
            }

        } else if (elemento.isJsonPrimitive()) {
            JsonPrimitive valor = elemento.getAsJsonPrimitive();
            if (valor.isString()) {
                cuenta++;
                if (cuenta == 4) {
                    lportales.add(valor.getAsString());
                    cuenta2 = 1;
                    cuenta = 0;
                }

                if (cuenta2 == 1 && cuenta == 3) {
                    lportales.add(valor.getAsString());
                    cuenta = 0;
                }

            }

        }

    }

    public static void establecerLinks() {

        int nlinks = Integer.parseInt(JOptionPane.showInputDialog("Cuantos links quieres ingresar?"));

        for (int i = 0; i < nlinks; i++) {

            String portal = JOptionPane.showInputDialog(null, "Portal 1", "Link " + String.valueOf(i + 1), JOptionPane.QUESTION_MESSAGE);
            String portal2 = JOptionPane.showInputDialog(null, "Portal 2", "Link " + String.valueOf(i + 1), JOptionPane.QUESTION_MESSAGE);

            try {
                fich = new File("links.txt");
                escribir = new PrintWriter(new FileWriter(fich, true));

                escribir.println(portal + " -> " + portal2);

            } catch (FileNotFoundException ex) {
                System.out.println("Error de escritura" + ex);
            } catch (IOException ex) {
                System.out.println("Error de escritura" + ex);
            } finally {
                escribir.close();
            }
        }
    }

    public static void contarLlaves() {

        BufferedReader reader, reader2;
        String salvadas, salvadas2 = "";
        String line, line2;
        int m = 0;

        fich = new File("portales.txt");
        fich2 = new File("portales2.txt");
        fichlinks = new File("links.txt");

        try {
            reader = new BufferedReader(new FileReader(fich));
            escribir = new PrintWriter(new FileWriter(fich2, true));

            while ((line = reader.readLine()) != null) {

                salvadas = line;

                String[] portals = salvadas.split("\\s*=\\s*");

                System.out.println(portals[0]);

                reader2 = new BufferedReader(new FileReader(fichlinks));

                while ((line2 = reader2.readLine()) != null) {

                    salvadas2 = line2;

                    String[] links = salvadas2.split("\\s*->\\s*");

                    System.out.println(links[1]);

                    if (portals[0].equalsIgnoreCase(links[1])) {
                        m++;
                    }
                }

                escribir.println(portals[0] + " = " + m);
                m = 0;
                reader2.close();
            }

            reader.close();
            escribir.close();
            fich.delete();
            //Renombramos el fichero. Esto es una prueba para ver si lo renombra o no.
            boolean correcto = fich2.renameTo(fich);
            if (correcto) {
                System.out.println("Fichero renombrado, Operacion completada.");
            } else {
                System.out.println("fichero no renombrado");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado." + ex);
        } catch (IOException ex) {
            System.out.println("IOException" + ex);
        }
    }

    public static void mostrarLlaves() {
        BufferedReader leer;
        try {

            fich = new File("portales.txt");
            leer = new BufferedReader(new FileReader("portales.txt"));
            String lineah = "";

            while ((lineah = leer.readLine()) != null) {

                JOptionPane.showMessageDialog(null, lineah);
            }

            leer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error. No se ha encontrado. " + ex);
        } catch (IOException ex) {
            System.out.println("Error. " + ex);
        }
    }
}

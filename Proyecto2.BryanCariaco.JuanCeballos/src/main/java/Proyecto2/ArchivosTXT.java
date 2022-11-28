package Proyecto2;

import java.io.*;
import javax.swing.JOptionPane;

public class ArchivosTXT {

    public static void Crear_Archivo(String nombre) {
        File archivo = new File(nombre);

        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            JOptionPane.showMessageDialog(null, "Se creo el archivo exitosamente");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public static void Escribir_Archivo(String nombre, String expresion) {
        File archivo = new File(nombre);

        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(expresion);
            salida.close();
            JOptionPane.showMessageDialog(null, "Se registraron todos los datos exitosamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

    public static void Leer_Archivo(String nombre) {
        File archivo = new File(nombre);

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = entrada.readLine();
            while (lectura != null) {
                JOptionPane.showMessageDialog(null, lectura);
                lectura = entrada.readLine();
            }
            entrada.close();
            JOptionPane.showMessageDialog(null, "Se registraron todos los datos exitosamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }

    }
}

package actividad_2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Actividad_2 {
    public static void main(String[] args) {
        System.out.println("Programa iniciado");

        File fichero = new File("Alumnos.txt");

        try {
            // Crear si no existe
            if (!fichero.exists()) {
                if (fichero.createNewFile()) {
                    System.out.println("Fichero creado");
                } else {
                    System.out.println("No se pudo crear el fichero");
                }
            } else {
                System.out.println("El fichero ya existe.");
            }

            try(FileWriter writer = new FileWriter(fichero)){
                writer.write("Hector\n");
                writer.write("Pol\n");
                writer.write("Marc\n");
                writer.write("Albert\n");
                writer.write("Carlos\n");


            }catch (IOException ex){
                System.err.println("no se ha creado el fichero");
                System.err.println(ex.getMessage());
            }










        } catch (IOException ex) {
            System.err.println("no se ha creado el fichero");
            System.err.println(ex.getMessage());
        }
    }
}

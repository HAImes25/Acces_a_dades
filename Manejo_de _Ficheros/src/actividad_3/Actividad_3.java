package actividad_3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Actividad_3 {
    public static void main(String[] args) {

        File fichero = new File("numeros.txt");

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
                for (int i = 0; i < 100; i++) {
                    writer.write((i+1) + "\n");
                }


            }catch (IOException ex){
                System.err.println("no se ha creado el fichero");
                System.err.println(ex.getMessage());
            }







        }catch (IOException ex){
            System.err.println("no se ha creado el fichero");
            System.err.println(ex.getMessage());
        }







    }
}

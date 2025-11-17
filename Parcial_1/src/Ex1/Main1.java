package Ex1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main1 {
    public static void main(String[] args) {

        // Parte 1
        File fichero = new File("empleados.txt");

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

        }catch (IOException ex){
            System.err.println("no se ha creado el fichero");
            System.err.println(ex.getMessage());
        }

            //Mostrar informacion
            System.out.println("Nombre: " + fichero.getName());
            System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
            System.out.println("Lectura: " + fichero.canRead());
            System.out.println("Escritura: " + fichero.canWrite());

            System.out.println("---------------------------------");
            //Parte 2

            try(FileWriter writer = new FileWriter(fichero)){
                writer.write("Carlos\n");
                writer.write("Jose\n");
                writer.write("Maria\n");
                writer.write("Marc\n");
                writer.write("Pol");


            }catch (IOException ex){
                System.err.println("no se ha escribir en el fichero");
                System.err.println(ex.getMessage());
            }


            //Parte 3
            //Scanner scanner = new Scanner(fichero.getAbsoluteFile());

            try(Scanner scanner = new Scanner(fichero)) {

                int contador = 0;
                while (scanner.hasNextLine()) {
                    String empleado = scanner.nextLine();
                    contador++;
                    System.out.println("La línea " + contador + " contiene: " + empleado);
                }
                System.out.println("Líneas totales : " + contador);




            }catch (IOException ex){
                System.err.println("no se ha podido leer el fichero");
                System.err.println(ex.getMessage());
            }
















    }
}

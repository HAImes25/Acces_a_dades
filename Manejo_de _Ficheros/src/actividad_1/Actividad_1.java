package actividad_1;

import java.io.File;
import java.io.IOException;

public class Actividad_1 {
    public static void main(String[] args) {
        System.out.println("Programa iniciado");

        File fichero = new File("datos.txt");

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

            //Mostrar informacion
            System.out.println("Nombre: " + fichero.getName());
            System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
            System.out.println("Lectura: " + fichero.canRead());
            System.out.println("Escritura: " + fichero.canWrite());

            //Borrar el fichero
            if (fichero.delete()) {
                System.out.println("Fichero eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el fichero.");
            }


        }catch (IOException ex){
            System.err.println("no se ha creado el fichero");
            System.err.println(ex.getMessage());
        }
    }
}


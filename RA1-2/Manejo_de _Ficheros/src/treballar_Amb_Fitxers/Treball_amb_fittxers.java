package treballar_Amb_Fitxers;

import java.io.File;
import java.io.IOException;

public class Treball_amb_fittxers {
    public static void main(String[] args) {


        String directorio = "Directorio";
        boolean exito = (new File(directorio).mkdir());

        if (exito) {
            System.out.println("Directorio creado");
        } else {
            System.out.println("No se ha podido crear el directorio");
        }

        File fichero = new File("datos.txt");

        try {

            if (fichero.createNewFile()) {
                System.out.println("Fichero creado");
            }

        } catch (IOException ex) {
            System.err.println("no se ha creado el fichero");
            System.err.println(ex.getMessage());
        }
    }
}

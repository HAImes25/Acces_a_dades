package actividad_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

            // Write
            try(FileWriter writer = new FileWriter(fichero)){
                for (int i = 0; i < 100; i++) {
                    writer.write((i+1) + "\n");
                }
            }catch (IOException ex){
                System.err.println("no se ha creado el fichero");
                System.err.println(ex.getMessage());
            }

            // Read
            try (Scanner scanner = new Scanner(fichero.getAbsoluteFile())){
                while (scanner.hasNext()){
                    String linea = scanner.nextLine();
                    int numero = Integer.parseInt(linea); // Pasar el String a Int
                    if ((numero % 10) == 0){
                        System.out.println(numero);
                    }
                }
            }catch (FileNotFoundException e){
                System.err.println(e.getMessage());
            }

            Scanner reeder = new Scanner(System.in);
            System.out.println("Cual linea quieres leer?");
            Boolean permitido = false;

            while (!permitido){
                try{
                    int userName = Integer.parseInt(reeder.nextLine());
                    break;
                }catch (Exception exception){
                    System.err.println("Introduzca un numero");
                    System.out.println("Cual linea quieres leer?");
                }
            }
            Scanner scanner = new Scanner(fichero.getAbsoluteFile());
            String respuesta = scanner.nextLine();


            try (Scanner scannerElegido = new Scanner(fichero.getAbsoluteFile())){
                int contador = -1;
                while (scannerElegido.hasNext()){

                    String linea = scanner.nextLine();
                    int lineaescogida = Integer.parseInt(linea); // Pasar el String a Int

                    int numero = Integer.parseInt(respuesta); // Pasar el String a Int
                    if (contador == numero){
                        System.out.println("La linea es " + lineaescogida);
                    }
                    contador++;

                }
            }catch (FileNotFoundException e){
                System.err.println(e.getMessage());
            }











        }catch (IOException ex){
            System.err.println("no se ha creado el fichero");
            System.err.println(ex.getMessage());
        }

    }
}

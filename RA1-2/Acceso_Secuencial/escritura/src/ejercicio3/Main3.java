package ejercicio3;

import java.io.*;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca marca del coche: ");
        String respuestaMarca = scanner.nextLine();

        System.out.println("Introduzca modelo del coche: ");
        String respuestaModelo = scanner.nextLine();

        System.out.println("Introduzca año del coche: ");
        int respuestaAño = Integer.parseInt(scanner.nextLine());

        Coche coche1 = new Coche(respuestaMarca, respuestaModelo, respuestaAño);

        File fichero = new File("escritura/ficheros/coches.dat");

        try{
            fichero.createNewFile();
        }catch (IOException e ){
            System.err.println(e.getMessage());
            System.err.println("No se ha podido crear el fichero");
            System.exit(-1);
        }
        // Creamos un FileOutputStream para saber donde escribir al objeto
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fichero);
        }catch (FileNotFoundException ex){
            System.err.println("No se ha encontrado el fichero" + fichero.getName());
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
        // Creamos un ObjectOutputStream que es el que pasa el objeto de forma que el fichero pueda guardar
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

        }catch (IOException ex){
            System.err.println("No se ha podido crear el ObjectOutputSteam");
            System.err.println(ex.getMessage());
            System.exit(-3);
        }

        // Intentamos escribir el objeto en el fichero
        try {
            objectOutputStream.writeObject(coche1);
            System.out.println("El producto se ha guardado correctamente ");
        }catch (IOException ex){
            System.err.println("No se ha podido escribir en el Fichero" + fichero.getName());
            System.err.println(ex.getMessage());
            System.exit(-4);
        }














    }
}

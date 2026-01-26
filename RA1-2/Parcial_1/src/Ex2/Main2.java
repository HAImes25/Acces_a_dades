package Ex2;

import java.io.*;

public class Main2 {
    public static void main(String[] args) {


        Libro libro1 = new Libro(1, "Principito", "Manolo", 20d);
        Libro libro2 = new Libro(2, "Ventanas", "Paco", 30d);
        Libro libro3 = new Libro(3, "Subsuelo", "Sara", 40d);


        File carpeta = new File("ficheros");

        //si la carpeta no existe
        if (!carpeta.exists()) {
            if (carpeta.mkdir()) {
            } else {
                System.err.println("No se pudo crear la carpeta");
            }
        }


        File fichero = new File(carpeta, "libros.dat");



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
            objectOutputStream.writeObject(libro1);
            System.out.println("El " + libro1.getTitulo() + " se ha guardado correctamente ");
            objectOutputStream.writeObject(libro2);
            System.out.println("El " + libro2.getTitulo() + " se ha guardado correctamente ");
            objectOutputStream.writeObject(libro3);
            System.out.println("El " + libro3.getTitulo() + " se ha guardado correctamente ");
        }catch (IOException ex){
            System.err.println("No se ha podido escribir en el Fichero" + fichero.getName());
            System.err.println(ex.getMessage());
            System.exit(-4);
        }


        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(fichero);
        } catch (FileNotFoundException ex) {
            System.err.println("No se ha encontrado el fichero " + fichero.getName());
            System.err.println(ex.getMessage());
        }


        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException ex) {
            System.err.println("No se ha podido crear el ObjectInputStream");
            System.err.println(ex.getMessage());
        }

        try {
            Libro libroLeido1 = (Libro) objectInputStream.readObject();
            System.out.println("---------------------------------");
            System.out.println("Libro leído1: " + libroLeido1.toString());
            Libro libroLeido2 = (Libro) objectInputStream.readObject();
            System.out.println("---------------------------------");
            System.out.println("Libro leído2: " + libroLeido2.toString());
            Libro libroLeido3 = (Libro) objectInputStream.readObject();
            System.out.println("---------------------------------");
            System.out.println("Libro leído3: " + libroLeido3.toString());

        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("No se ha podido leer desde el fichero " + fichero.getName());
            System.err.println(ex.getMessage());
        }










    }
}

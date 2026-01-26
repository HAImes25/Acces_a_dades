package ejercicio1;

import java.io.*;

public class Main1 {
    public static void main(String[] args) {

        Producto producto = new Producto(1, "Producto1", 25d);

        File fichero = new File("escritura/ficheros/producto.dat");

        // Crear ficheros
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
            objectOutputStream.writeObject(producto);
            System.out.println("El producto se ha guardado correctamente ");
        }catch (IOException ex){
            System.err.println("No se ha podido escribir en el Fichero" + fichero.getName());
            System.err.println(ex.getMessage());
            System.exit(-4);
        }



















    }
}

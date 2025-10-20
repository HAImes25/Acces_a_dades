package ejercicio2;


import java.io.*;

public class Main2 {
    public static void main(String[] args) {

        Persona persona1 = new Persona(1, "Pablo", 25);
        Persona persona2 = new Persona(2, "Jose", 56);
        Persona persona3 = new Persona(3, "Maria", 30);

        File fichero = new File("escritura/ficheros/personas.dat");

        try{
            fichero.createNewFile();
        }catch (IOException e ){
            System.err.println(e.getMessage());
            System.err.println("No se ha podido crear el fichero");
            System.exit(-1);
        }

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
            objectOutputStream.writeObject(persona1);
            System.out.println("Persona con el id : " + persona1.getId() + " Guardada");
            objectOutputStream.writeObject(persona2);
            System.out.println("Persona con el id : " + persona2.getId() + " Guardada");
            objectOutputStream.writeObject(persona3);
            System.out.println("Persona con el id : " + persona3.getId() + " Guardada");
        }catch (IOException ex){
            System.err.println("No se ha podido escribir en el Fichero" + fichero.getName());
            System.err.println(ex.getMessage());
            System.exit(-4);
        }





    }
}

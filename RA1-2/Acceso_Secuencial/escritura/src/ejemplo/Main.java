package ejemplo;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        //Crear el fichero donde guardaremos le objeto
        File file = new File("escritura/ficheros/objeto");
        guardarObjeto(file);

    }

    private static void recuperarObjeto(File file){
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e){
            System.out.println("Error al abrir el FileInputStream" + file.getName());
            System.out.println(e.getMessage());
            System.exit(-5);
        }
        ObjectInputStream objectInputStream = null;
        try{
            objectInputStream = new ObjectInputStream(fileInputStream);
        }catch (IOException e){
            System.out.println("Error al crear el ObjectInputStream" + file.getName());
            System.out.println(e.getMessage());
            System.exit(-6);
        }
        try{
            Persona persona = (Persona) objectInputStream.readObject() ;
            System.out.println(persona);
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Error al recuperar el objeto" + file.getName());
            System.err.println(e.getMessage());
            System.exit(-8);
        }
        try{
            objectInputStream.close();
            fileInputStream.close();
        }catch (IOException e){
            System.err.println("Error al cerrar el ObjectInputStream" + file.getName());
            System.err.println(e.getMessage());
            System.exit(-9);
        }

    }






    private static void guardarObjeto(File file){

        //Creamos el fichero, acordaros del try - execption.
        try {
            file.createNewFile();
        }catch (IOException ex){
            System.err.println("No se ha podido crear el fichero" + file.getName());
            System.err.println(ex.getMessage());
//            System.exit(-1);
        }

        //Crear el objeto que necessitamos guardar
        //Crear el FileOutputStream para guardar la información en el foichero --> Flujo de datos de salida
        //Convertir el objeto a Bytes
        //Guardar los bytes en el fichero


        //Crear el objeto
        Persona persona = new Persona(1, "Jass",28, "C/Major 1");

        //Necesitamos todo el sistema para guardar el objeto en un fichero.
        //FileOutputSteam, le tenemos que pasar el fichero --> file
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        }catch (FileNotFoundException ex){
            System.err.println("No se ha encontrado el fichero" + file.getName());
            System.err.println(ex.getMessage());
            System.exit(-2);
        }

        //Lo que quiero es guardar un objeto
        //Nosotros no tenemos ningun mecanismo para guardar el objeto en bytes
        // El mecanismo es ObjectOutputStream

        //Nos pide un OutputStream, que ahora tenemos el fileOutputStream
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        }catch (IOException ex){
            System.err.println("No se ha podido crear el ObjectOutputSteam");
            System.err.println(ex.getMessage());
            System.exit(-3);
        }

        try {
            objectOutputStream.writeObject(persona);
        }catch (IOException ex){
            System.err.println("No se ha podido escribir en el Fichero" + file.getName());
            System.err.println(ex.getMessage());
            System.exit(-4);
        }

        try {
            fileOutputStream.close();
            //objectOutputStream.close();
        }catch (IOException ex){
            System.err.println("No se ha podido cerrar la Fichero" + file.getName());
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
    }



}


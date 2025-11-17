import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        File file = new File("ficheros/objeto");
        guardarObjeto(file);
        recuperarObjeto(file);
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


        //Crear la lista 
        List<Persona> listaPersonas = new ArrayList<>();

        listaPersonas.add(new Persona(1, "Yass", 28));
        listaPersonas.add(new Persona(2, "Ana", 30));
        listaPersonas.add(new Persona(3, "Luis", 25));

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
            objectOutputStream.writeObject(listaPersonas);
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
            List<Persona> listaPersonas = (List<Persona>) objectInputStream.readObject();

            System.out.println("-------------------------------");

            for (Persona p : listaPersonas) {
                System.out.println("ID: " + p.getId());
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Edad: " + p.getEdad());
                System.out.println("-------------------------------");

            }
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


}
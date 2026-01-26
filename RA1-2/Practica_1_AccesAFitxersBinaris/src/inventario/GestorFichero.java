package inventario;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorFichero {
    public GestorFichero() {}

    public void addProducto(File file) {

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ex) {
            System.err.println("No se ha podido crear el fichero " + file.getName());
            System.err.println(ex.getMessage());
            System.exit(-1);
        }

        //ArrayList<Producto> lista = new ArrayList<Producto>(); //Hace falta Mirar esto ya que ahora se reinicia la lista por cada producto
        ArrayList<Producto> lista = getListaProductos(file);

        // Pedir datos al usuario
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el nombre del producto: ");
        String nombre = sc.nextLine();

        lista.add(new Producto((lista.size()+1),nombre));



        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        }catch (FileNotFoundException ex){
            System.err.println("No se ha encontrado el fichero" + file.getName());
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        }catch (IOException ex){
            System.err.println("No se ha podido crear el ObjectOutputSteam");
            System.err.println(ex.getMessage());
            System.exit(-3);
        }

        try {
            objectOutputStream.writeObject(lista);
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

    public void listarProductos(File file) {

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
            ArrayList<Producto> lista = (ArrayList<Producto>) objectInputStream.readObject();

            System.out.println("------------------------------------");
            for (Producto p : lista) {
                System.out.println("ID: " + p.getID());
                System.out.println("Nombre: " + p.getName());
                System.out.println("------------------------------------");

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

    public ArrayList<Producto> getListaProductos(File file){
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
            ArrayList<Producto> lista = (ArrayList<Producto>) objectInputStream.readObject();
            return lista;
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
        return null;
    }

    public void buscarProducto(File file){


        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el ID del producto a buscar: ");
        String idABuscar = sc.nextLine();
        int idABuscarInt = Integer.parseInt(idABuscar);
        boolean encontrado = false;

        ArrayList<Producto> lista = getListaProductos(file);

        System.out.println("------------------------------------");
        for (Producto p : lista) {
            if (idABuscarInt == p.getID()){
                encontrado=true;
                System.out.println("ID: " + p.getID());
                System.out.println("Nombre: " + p.getName());
                System.out.println("------------------------------------");
            }
            if (encontrado = false){
                System.out.println("No se ha encontrado el producto.");
                System.out.println("------------------------------------");
            }

        }







    }


}

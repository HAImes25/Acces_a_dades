package inventario;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorFichero gestorFichero = new GestorFichero();
        File file = new File( "src/datos/productos.dat");




        Scanner sc = new Scanner(System.in);

        boolean acabado = false;

while (acabado == false) {

    System.out.println("--------GESTIÓN DE PRODUCTOS-------");
    System.out.println("1. Añadir producto\n" +
            "2. Listar productos\n" +
            "3. Buscar producto por ID\n" +
            "4. Salir\n" +
            "Selecciona una opción:");

    int opcion = sc.nextInt();
    sc.nextLine(); // limpiar buffer


    switch (opcion) {
        case 1:
            gestorFichero.addProducto(file);
            break;

        case 2:
            gestorFichero.listarProductos(file);
            break;

        case 3:
            gestorFichero.buscarProducto(file);
            break;

        case 4:
            System.out.println("Saliendo del programa...");
            acabado = true;
            break;

        default:
            System.out.println("Opción no válida. Inténtalo de nuevo.");

    }


}



    }
}
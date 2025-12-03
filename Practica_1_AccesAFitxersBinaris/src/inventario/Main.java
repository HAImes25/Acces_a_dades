package inventario;

public class Main {
    public static void main(String[] args) {
        GestorFichero gestorFichero = new GestorFichero();

        System.out.println("--------GESTIÓN DE PRODUCTOS-------");
        System.out.println("1. Añadir producto\n" +
                "2. Listar productos\n" +
                "3. Buscar producto por ID\n" +
                "4. Salir\n" +
                "Selecciona una opción:");

        gestorFichero.addProducto();








    }
}
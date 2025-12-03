package inventario;

import java.io.File;
import java.io.IOException;

public class GestorFichero {
    public GestorFichero() {
    }

    public void addProducto() {
        File file = new File( "src/datos/productos.dat");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ex) {
            System.err.println("No se ha podido crear el fichero " + file.getName());
            System.err.println(ex.getMessage());
            System.exit(-1);
        }

    }
}

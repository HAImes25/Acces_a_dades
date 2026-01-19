import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Boolean acabado = false;
        while (!acabado) {

            //Crear el fichero donde guardaremos le objeto
            File file = new File("ficheros/fichero.txt");

            File carpeta = file.getParentFile();
            if (carpeta != null && !carpeta.exists()) {
                carpeta.mkdirs();
            }

            //Creamos el fichero, acordaros del try - execption.
            try {
                file.createNewFile();
            } catch (
                    IOException ex) {
                System.err.println("No se ha podido crear el fichero" + file.getName());
                System.err.println(ex.getMessage());
                System.exit(-1);
            }

            Scanner scin = new Scanner(System.in);
            System.out.println("-------------------------");
            System.out.println("1. Sobrescribir fichero");
            System.out.println("2. Añadir texto");
            System.out.println("3. Leer fichero letras");
            System.out.println("4. Leer fichero numeros");
            System.out.println("5. Estadísticas ");
            System.out.println("6. Buscar caracter ");
            System.out.println("7. Salir ");
            System.out.println("-------------------------");

            //int opcion = scin.nextInt();
            //scin.nextLine();
            int opcion = 1;

            if (scin.hasNextInt()) {
                opcion = scin.nextInt();
                scin.nextLine();
            } else {
                System.out.println("Opción no válida. Introduce un número.");
                scin.nextLine();
                continue;
            }

                switch (opcion) {
                    case 1:
                        System.out.println("Opcion 1 ");
                        sobrescribir(file);
                        break;

                    case 2:
                        System.out.println("Opcion 2 ");
                        agregarTexto(file);
                        break;

                    case 3:
                        System.out.println("Opcion 3 ");
                        leerFicheroLetras(file);
                        break;

                    case 4:
                        System.out.println("Opcion 4 ");
                        leerCodigos(file);
                        break;

                    case 5:
                        System.out.println("Opcion 5 ");
                        estadisticas(file);
                        break;

                    case 6:
                        System.out.println("Opcion 6 ");
                        buscarCaracter(file);
                        break;

                    case 7:
                        System.out.println("Saliendo del programa...");
                        acabado = true;
                        break;

                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");

                }


        }

    }


    public static void sobrescribir(File file) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el texto a guardar: ");
        String texto = sc.nextLine();

        try (FileWriter fw = new FileWriter(file, false);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(texto);
            bw.newLine();

            System.out.println("Texto sobrescrito correctamente.");

        } catch (IOException ex) {
            System.err.println("Error al escribir en el fichero");
            ex.printStackTrace();
        }
    }
    public static void agregarTexto(File file) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el texto a guardar: ");
        String texto = sc.nextLine();

        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(texto);
            bw.newLine();

            System.out.println("Texto agregado correctamente.");

        } catch (IOException ex) {
            System.err.println("Error al escribir en el fichero");
            ex.printStackTrace();
        }
    }
    public static void leerFicheroLetras(File file){
        try (FileReader fr = new FileReader(file)) {

            int caracter;
            System.out.println("Contenido del fichero:");

            while ((caracter = fr.read()) != -1) {
                System.out.print((char) caracter);
            }

            // Salto de liena para que se vea bien
            System.out.println();

        } catch (IOException ex) {
            System.err.println("Error al leer el fichero");
            ex.printStackTrace();
        }
    }
    private static void leerCodigos(File file) {

        try (FileReader fr = new FileReader(file)) {

            int codigo;
            System.out.println("Códigos numéricos de los caracteres:");

            while ((codigo = fr.read()) != -1) {
                System.out.println(codigo);
            }

        } catch (IOException ex) {
            System.err.println("Error al leer el fichero");
            ex.printStackTrace();
        }
    }
    private static void estadisticas(File file) {

        int totalCaracteres = 0;
        int totalLineas = 0;
        int vocales = 0;
        int noAlfabeticos = 0;

        try (FileReader fr = new FileReader(file)) {

            int codigo;
            while ((codigo = fr.read()) != -1) {

                char c = (char) codigo;
                totalCaracteres++;

                if (c == '\n') {
                    totalLineas++;
                }

                if (Character.isLetter(c)) {

                    char letra = Character.toLowerCase(c);
                    if (letra == 'a' || letra == 'e' || letra == 'i'
                            || letra == 'o' || letra == 'u') {
                        vocales++;
                    }

                } else {
                    noAlfabeticos++;
                }
            }

        } catch (IOException ex) {
            System.err.println("Error al leer el fichero");
            ex.printStackTrace();
        }

        System.out.println("ESTADÍSTICAS");
        System.out.println("-------------------------");
        System.out.println("Total de caracteres: " + totalCaracteres);
        System.out.println("Total de líneas: " + totalLineas);
        System.out.println("Número de vocales: " + vocales);
        System.out.println("Caracteres no alfabéticos: " + noAlfabeticos);
    }
    private static void buscarCaracter(File file) {
        int contador = 0;
        Scanner scB = new Scanner(System.in);
        System.out.print("Introduce el caracter a buscar: ");
        char caracterABuscar = scB.nextLine().charAt(0);

        try (FileReader fr = new FileReader(file)) {

            int codigo;
            while ((codigo = fr.read()) != -1) {

                char c = (char) codigo;
                if (c == caracterABuscar) {
                    contador++;
                }
            }


            System.out.println("Carácter a buscar: '"+ caracterABuscar + "'.");
            System.out.println("Aparece " + contador + " veces.");

        } catch (IOException ex) {
            System.err.println("Error al leer el fichero");
            ex.printStackTrace();
        }
    }


}

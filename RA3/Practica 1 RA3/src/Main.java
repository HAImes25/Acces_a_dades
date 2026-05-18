import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Main {

    static class TestDuracion {
        String id;
        String suite;
        String name;
        int total;

        public TestDuracion(String id, String suite, String name, int total) {
            this.id = id;
            this.suite = suite;
            this.name = name;
            this.total = total;
        }
    }

    public static void main(String[] args) {

        Path path = Path.of("ficheros/prac1.xml");
        File xml = path.toFile();

        Scanner sc = new Scanner(System.in);

        try {

            PrintWriter writer = new PrintWriter("resultat.txt");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xml);
            document.getDocumentElement().normalize();

            NodeList suites = document.getElementsByTagName("Suite");

            int totalSuites = suites.getLength();
            int totalTests = 0;

            int cmd = 0;
            int gui = 0;
            int api = 0;
            int disabled = 0;

            Map<String, Integer> testsPorSuite = new LinkedHashMap<>();

            Map<String, Integer> failPorPlataforma = new LinkedHashMap<>();
            Map<String, Integer> duracionTotal = new LinkedHashMap<>();
            Map<String, Integer> ejecuciones = new LinkedHashMap<>();

            List<TestDuracion> tiempos = new ArrayList<>();

            System.out.println("--- LLISTAT COMPLET ---");
            writer.println("--- LLISTAT COMPLET ---");

            for (int i = 0; i < suites.getLength(); i++) {

                Element suite = (Element) suites.item(i);

                String suiteId = suite.getAttribute("id");
                String suiteName = suite.getAttribute("name");

                NodeList tests = suite.getElementsByTagName("Test");

                testsPorSuite.put(suiteId + " " + suiteName, tests.getLength());

                for (int j = 0; j < tests.getLength(); j++) {

                    totalTests++;

                    Element test = (Element) tests.item(j);

                    String testId = test.getAttribute("TestId");
                    String type = test.getAttribute("TestType");
                    String diff = test.getAttribute("difficulty");
                    String enabled = test.getAttribute("enabled");
                    String owner = test.getAttribute("owner");

                    if (type.equals("CMD")) cmd++;
                    if (type.equals("GUI")) gui++;
                    if (type.equals("API")) api++;

                    if (enabled.equals("false")) disabled++;

                    String name = test.getElementsByTagName("Name")
                            .item(0)
                            .getTextContent();

                    NodeList cmdNode = test.getElementsByTagName("CommandLine");
                    String commandLine = "---";

                    if (cmdNode.getLength() > 0) {
                        commandLine = cmdNode.item(0).getTextContent();
                    }

                    System.out.println("Test        - " + testId);
                    System.out.println("   Name -> " + name);
                    System.out.println("   CommandLine -> " + commandLine);
                    System.out.println();

                    writer.println("Test        - " + testId);
                    writer.println("   Name -> " + name);
                    writer.println("   CommandLine -> " + commandLine);
                    writer.println();

                    int totalMs = 0;

                    NodeList platforms = test.getElementsByTagName("Platform");

                    for (int k = 0; k < platforms.getLength(); k++) {

                        Element platform = (Element) platforms.item(k);

                        String pname = platform.getAttribute("name");
                        String status = platform.getAttribute("status");
                        int duration = Integer.parseInt(platform.getAttribute("durationMs"));

                        totalMs += duration;

                        if (status.equals("FAIL")) {
                            failPorPlataforma.put(pname,
                                    failPorPlataforma.getOrDefault(pname, 0) + 1);
                        }

                        if (!status.equals("SKIP")) {

                            duracionTotal.put(pname,
                                    duracionTotal.getOrDefault(pname, 0) + duration);

                            ejecuciones.put(pname,
                                    ejecuciones.getOrDefault(pname, 0) + 1);
                        }
                    }

                    tiempos.add(new TestDuracion(
                            testId,
                            suiteId + "-" + suiteName,
                            name,
                            totalMs
                    ));
                }
            }

            System.out.println();
            System.out.println("=== RESUMEN ===");
            writer.println();
            writer.println("=== RESUMEN ===");

            System.out.println("Suites: " + totalSuites);
            writer.println("Suites: " + totalSuites);

            System.out.println("Total tests: " + totalTests);
            writer.println("Total tests: " + totalTests);

            System.out.println("Por tipo: CMD=" + cmd + ", GUI=" + gui + ", API=" + api);
            writer.println("Por tipo: CMD=" + cmd + ", GUI=" + gui + ", API=" + api);

            System.out.println("Disabled (enabled=false): " + disabled);
            writer.println("Disabled (enabled=false): " + disabled);

            System.out.println();
            System.out.println("Por suite:");
            writer.println();
            writer.println("Por suite:");

            for (String key : testsPorSuite.keySet()) {

                String linea = "- " + key + ": " + testsPorSuite.get(key);

                System.out.println(linea);
                writer.println(linea);
            }

            System.out.println();
            System.out.println("=== PLATAFORMAS ===");
            writer.println();
            writer.println("=== PLATAFORMAS ===");

            System.out.println("FAIL por plataforma:");
            writer.println("FAIL por plataforma:");

            for (String p : failPorPlataforma.keySet()) {

                String linea = "- " + p + ": " + failPorPlataforma.get(p);

                System.out.println(linea);
                writer.println(linea);
            }

            System.out.println();
            System.out.println("Duración media (ms) sin SKIP:");
            writer.println();
            writer.println("Duración media (ms) sin SKIP:");

            for (String p : duracionTotal.keySet()) {

                double media = (double) duracionTotal.get(p) / ejecuciones.get(p);

                String linea = "- " + p + ": " + media + " ms";

                System.out.println(linea);
                writer.println(linea);
            }

            System.out.println();
            System.out.println("=== TOP 3 MÁS LENTOS ===");
            writer.println();
            writer.println("=== TOP 3 MÁS LENTOS ===");

            tiempos.sort((a, b) -> b.total - a.total);

            for (int i = 0; i < 3 && i < tiempos.size(); i++) {

                TestDuracion t = tiempos.get(i);

                String linea = "#" + (i + 1) + " " +
                        t.id + " (" + t.suite + ") - " +
                        t.name + " - " +
                        t.total + " ms";

                System.out.println(linea);
                writer.println(linea);
            }

            System.out.println();
            writer.println();

            System.out.print("Filtrar por suite? (S01/S02/S03/TOTS): ");
            String filtroSuite = sc.nextLine();

            System.out.print("Filtrar por tipus? (CMD/GUI/API/TOTS): ");
            String filtroTipo = sc.nextLine();

            System.out.println();
            System.out.println("--- FILTRE ---");
            writer.println("--- FILTRE ---");

            for (int i = 0; i < suites.getLength(); i++) {

                Element suite = (Element) suites.item(i);

                String suiteId = suite.getAttribute("id");
                String suiteName = suite.getAttribute("name");

                NodeList tests = suite.getElementsByTagName("Test");

                for (int j = 0; j < tests.getLength(); j++) {

                    Element test = (Element) tests.item(j);

                    String testId = test.getAttribute("TestId");
                    String type = test.getAttribute("TestType");
                    String diff = test.getAttribute("difficulty");
                    String enabled = test.getAttribute("enabled");
                    String owner = test.getAttribute("owner");

                    String name = test.getElementsByTagName("Name")
                            .item(0)
                            .getTextContent();

                    NodeList cmdNode = test.getElementsByTagName("CommandLine");
                    String commandLine = "---";

                    if (cmdNode.getLength() > 0) {
                        commandLine = cmdNode.item(0).getTextContent();
                    }

                    boolean mostrar = true;

                    if (!filtroSuite.equals("TOTS") && !suiteId.equals(filtroSuite)) {
                        mostrar = false;
                    }

                    if (!filtroTipo.equals("TOTS") && !type.equals(filtroTipo)) {
                        mostrar = false;
                    }

                    if (mostrar) {

                        String linea = String.format("[%s-%s] %s | %s | D%s | %s | %s | %s | %s",
                                suiteId,
                                suiteName,
                                testId,
                                type,
                                diff,
                                enabled,
                                owner,
                                name,
                                commandLine);

                        System.out.println(linea);
                        writer.println(linea);
                    }
                }
            }

            writer.close();

            System.out.println();
            System.out.println("Archivo resultat.txt generado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
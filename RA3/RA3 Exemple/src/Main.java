import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("ficheros/ms.xml");
        File xml = path.toFile();
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xml);

            NodeList listaInicial = document.getElementsByTagName("Tests").item(0).getChildNodes();
            //System.out.println(listaInicial.toString());
            for (int i = 0; i < listaInicial.getLength(); i++) {
                Node node = listaInicial.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    System.out.println(node.getNodeName());
                    NodeList hijos = node.getChildNodes();

                    for (int j = 0; j < hijos.getLength(); j++) {
                        Node hijo = hijos.item(j);


                        if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                            System.out.println("  Etiqueta: " + hijo.getNodeName());

                            NodeList nietos = hijo.getChildNodes();
                            for (int k = 0; k < nietos.getLength(); k++) {
                                Node nieto = nietos.item(k);


                                if (nieto.getNodeType() == Node.TEXT_NODE) {
                                    String texto = nieto.getTextContent().trim();
                                    if (!texto.isEmpty()) {

                                        System.out.println("    Texto: " + texto);
                                    }
                                }
                            }
                        }
                    }
                }
                //System.out.println(node);
            }

        }catch (ParserConfigurationException | SAXException | IOException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}

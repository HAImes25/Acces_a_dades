package RA3;

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
            /*Primeros pasos de configuración*/
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xml);

            /*Tratar el fichero*/
            NodeList listaInicial = document.getElementsByTagName("Tests").item(0).getChildNodes();
            //System.out.println(listaInicial.toString());
            for (int i = 0; i < listaInicial.getLength(); i++) {
                Node node = listaInicial.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){

                    System.out.println(node.getNodeName());
                }
                //System.out.println(node);
            }

        }catch (ParserConfigurationException | SAXException | IOException ex){
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}

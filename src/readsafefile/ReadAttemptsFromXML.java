package readsafefile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadAttemptsFromXML {
	
	public String getAttempts(String level) {
		String atts = "0";
        try {
            // Pfad zur XML-Datei
            String filePath = "C:\\ExtremeDemonList\\userdata\\CCGameManager.dat.xml";
            

            // XML-Datei einlesen und als DOM-Dokument parsen
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Nach dem Eintrag für "Theory of Everything" suchen
            NodeList levelNodes = doc.getElementsByTagName("d");
            int index = 1;
            for (int i = 0; i < levelNodes.getLength(); i++) {
            	
                Node levelNode = levelNodes.item(i);
                if (levelNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element levelElement = (Element) levelNode;
                    NodeList nameNodes = levelElement.getElementsByTagName("s");
                    for (int j = 0; j < nameNodes.getLength(); j++) {
                        Node nameNode = nameNodes.item(j);
                        if (nameNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element nameElement = (Element) nameNode;
                            String name = nameElement.getTextContent();
                            if (name.equalsIgnoreCase(level)) {
                                // Wenn der Name übereinstimmt, die Anzahl der Versuche extrahieren
                                NodeList keyNodes = levelElement.getElementsByTagName("k");
                                for (int k = 0; k < keyNodes.getLength(); k++) {
                                    Node keyNode = keyNodes.item(k);
                                    if (keyNode.getNodeType() == Node.ELEMENT_NODE) {
                                        Element keyElement = (Element) keyNode;
                                        String key = keyElement.getTextContent();
                                        if (key.equals("k18") && index == 2) {
                                            // Wert des Schlüssels "k18" (Versuche) ausgeben
                                            NodeList valueNodes = levelElement.getElementsByTagName("i");
                                            Node valueNode = valueNodes.item(k);
                                           atts = valueNode.getTextContent(); // Sobald die Anzahl der Versuche gefunden wurde, die Schleife beenden
                                        }
                                        
                                        if(key.equals("GS_7")) {
                                        	NodeList valueNodes = levelElement.getElementsByTagName("i");
                                            Node valueNode = valueNodes.item(k);
                                        	System.out.println(valueNode.getTextContent() + " = practice percent");
                                        }
                                        
                                    }
                                }
                               index++; 
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		return atts;
    }
	
	
	
}

package readsafefile;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ReadAttemptsFromXML {
	
	 public String getAttempts(String levelID) {
	        String atts = "0";
	        try {
	            // Pfad zur XML-Datei
	            File xmlFile = new File("C:\\ExtremeDemonList\\userdata\\CCGameManager.dat.xml");

	            // Erstellen des Dokument-Builders
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	            // Parsen der XML-Datei, um ein Document-Objekt zu erhalten
	            Document doc = dBuilder.parse(xmlFile);

	            // Normalisieren des Documents
	            doc.getDocumentElement().normalize();

	            // Die NodeList der <k> Elemente erhalten
	            NodeList kList = doc.getElementsByTagName("k");

	            // Durchlaufen der NodeList
	            for (int i = 0; i < kList.getLength(); i++) {
	                // Aktuelles Node
	                Element kElement = (Element) kList.item(i);

	                // Prüfen, ob der Wert des <k> Elements eine Level-ID ist
	                String currentLevelID = kElement.getTextContent(); 
	                
	                if (currentLevelID.equals(levelID)) {
	                    // Das übergeordnete <d> Element finden
	                    Element dElement = (Element) kElement.getNextSibling();

	                    // Überprüfen, ob das nächste Element ein <d> Element ist
	                    while (dElement != null && !dElement.getNodeName().equals("d")) {
	                        dElement = (Element) dElement.getNextSibling();
	                    }

	                    // Überprüfen, ob das <d> Element gefunden wurde
	                    if (dElement != null) {
	                        // Den <k> Key "k1" im <d> Element finden
	                        NodeList dChildren = dElement.getChildNodes();
	                        for (int j = 0; j < dChildren.getLength(); j++) {
	                            if (dChildren.item(j).getNodeName().equals("k")) {
	                                Element kChild = (Element) dChildren.item(j);
	                                if (kChild.getTextContent().equals("k18")) {
	                                    // Den Wert der Attempts erhalten
	                                    atts = dChildren.item(j + 1).getTextContent();
	                                    System.out.println("Attempts des Levels " + levelID + ": " + atts);
	                                    return atts;
	                                }
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

package readsafefile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ReadAttemptsFromXML {
    
    public Map<String, String> attempts = new HashMap<String, String>();
    public Map<String, String> newbestMap = new HashMap<>();
    
    public void readAttempts() {
        try {
            File xmlFile = new File("C:\\ExtremeDemonList\\userdata\\CCGameManager.dat.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList kList = doc.getElementsByTagName("k");

            Map<String, String> tempAttempts = new HashMap<>();
            

            for (int i = 0; i < kList.getLength(); i++) {
                Element kElement = (Element) kList.item(i);
                String currentLevelID = kElement.getTextContent();
                
                Element dElement = (Element) kElement.getNextSibling();
                
                while (dElement != null && !dElement.getNodeName().equals("d")) {
                    dElement = (Element) dElement.getNextSibling();
                }
                
                if (dElement != null) {
                    NodeList dChildren = dElement.getChildNodes();
                    String attemptsValue = null;
                    String percentValue = null;
                    
                    for (int j = 0; j < dChildren.getLength(); j++) {
                        if (dChildren.item(j).getNodeName().equals("k")) {
                            Element kChild = (Element) dChildren.item(j);
                            
                            if (kChild.getTextContent().equals("k18")) {
                                attemptsValue = dChildren.item(j + 1).getTextContent();
                            }
                            
                            if (kChild.getTextContent().equals("k19")) {
                                percentValue = dChildren.item(j + 1).getTextContent();
                            }
                        }
                    }
                    
                    if (attemptsValue != null && percentValue != null) {
                        tempAttempts.put(currentLevelID, attemptsValue ); // + "," + percentValue
                        newbestMap.put(currentLevelID, percentValue);
                    }
                }
            }
            
            // Füge die Daten aus der temporären Map in die attempts-Map ein
            attempts.putAll(tempAttempts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

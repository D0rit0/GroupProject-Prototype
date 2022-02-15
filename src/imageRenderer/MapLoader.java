package imageRenderer;
//Thanks to https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
//I can now process xml files
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class MapLoader {
    private static final String fileLocation = "src\\Team Valentine World Map.tmx";
    public static Map<Integer, Image> textureMap = new HashMap<>();

    private static Document documentLoader() {
        //create a document builder factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            //makes sure no funny business can happen with the file
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            //gets a document builder from the factory.
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            //parses over the file and creates a document object out of it.
            Document doc = docBuilder.parse(new File(MapLoader.fileLocation));
            System.out.println("success");
            doc.getDocumentElement().normalize();
            return doc;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getLayer(int layerNum){
        String dataString = null;
        //gets a list of all layer nodes
        NodeList list = Objects.requireNonNull(documentLoader()).getElementsByTagName("layer");
        Element layer = null;
        //iterates over layer nodes till it finds the wanted layer.
        for(int i = 0; i < list.getLength(); i++){
            Element node = (Element)list.item(i);
            if(node.getAttribute("id").equals(Integer.toString(layerNum))){
                layer = node;
            }
        }
        assert layer != null;
        //creates an element object by casting out layer node to an element
        if(layer.getNodeType() == Node.ELEMENT_NODE) {

            System.out.println(layer.getAttribute("id"));
            //gets a list of the nodes contained within our layer element
            list = layer.getChildNodes();
            //iterates over those nodes until it finds the wanted node
            for(int i =0; i < list.getLength(); i++){
                //once it finds the wanted node it takes the content within that node
                //this content is the map data
                if(list.item(i).getNodeName().equals("data")){
                    Element data = (Element)list.item(i);
                    dataString = data.getTextContent();
                }
            }
        }
        return dataString;
    }
    public static int[][] loadLayerArray(String dataString){
        assert dataString != null;
        //removes all new line characters and splits the string at each comma and creates a string array out of it.
        String[] string = dataString.replaceAll("\n","").split(",");
        int[] dataArrTemp = new int[string.length];
        int y = 0;
        //takes that string array and parses it into an int array
        for(int i=0; i< string.length;i++){
            dataArrTemp[i] = Integer.parseInt(string[i]);
        }
        //takes our new int array and turns it into a 2d int array
        int[][] dataArr = new int[100][100];
        //found better method from https://stackoverflow.com/questions/5134555/how-to-convert-a-1d-array-to-2d-array
        for(int i = 0; i < 100; i++){
            System.arraycopy(dataArrTemp, (i*100), dataArr[i], 0,100);
        }
        System.out.println(Arrays.deepToString(dataArr));
        return dataArr;
    }
    public static void LoadTextures(int layer){
        //loads in textures for layer 1
        for(int[] array: loadLayerArray(getLayer(layer))){
            for(int textureId: array){
                if(!textureMap.containsKey(textureId)&& textureId!=0) {
                    textureMap.put(textureId,SpriteLoader.loadImage("src\\resources\\atlas_32x.png", textureId));
                }
            }
        }
    }
}

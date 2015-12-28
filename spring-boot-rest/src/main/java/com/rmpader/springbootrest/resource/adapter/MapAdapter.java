package com.rmpader.springbootrest.resource.adapter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;

/**
 * @author RMPader
 */
public class MapAdapter extends XmlAdapter<MapAdapter.AdaptedMap, Map<String, Object>> {

    private DocumentBuilder documentBuilder;

    public MapAdapter() throws Exception {
        documentBuilder = DocumentBuilderFactory.newInstance()
                                                .newDocumentBuilder();
    }

    public static class AdaptedMap {

        @XmlAnyElement
        public List<Node> elements = new ArrayList<>();
    }

    @Override
    public AdaptedMap marshal(Map<String, Object> map) throws Exception {
        Document document = documentBuilder.newDocument();
        AdaptedMap adaptedMap = new AdaptedMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
           /*
            * This part is pretty complex aside from not being in the scope of the article,
            * I currently have no idea of how to implement this efficiently.
            */
        }
        return adaptedMap;
    }

    /*
     * (non-Javadoc)
     *
     * PLEASE READ
     * This is by no means the correct way to implement un-marshalling.
     * If you read the code, you can see that it only really caters to the
     * use case presented in the sample request.
     */
    @Override
    public Map<String, Object> unmarshal(AdaptedMap adaptedMap) throws Exception {
        HashMap<String, Object> map = new LinkedHashMap<>();
        for (Node element : adaptedMap.elements) {
            String key = element.getLocalName();
            if(key == null){
                continue;
            }
            if (map.containsKey(key)) {
                Object o = map.get(key);
                if (o instanceof List) {
                    ((List) o).add(element.getTextContent());
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(o.toString());
                    list.add(element.getTextContent());
                    map.put(key, list);
                }
            } else {
                if (element.getChildNodes().getLength() > 1) {
                    AdaptedMap subMap = new AdaptedMap();
                    for (int i = 0; i < element.getChildNodes()
                                               .getLength(); i++) {
                        Node item = element.getChildNodes()
                                           .item(i);
                        subMap.elements.add(item);
                    }
                    map.put(key,unmarshal(subMap));
                } else {
                    map.put(key, element.getTextContent());
                }
            }
        }
        return map;
    }


}

package zeragan.perfit.core.configuration;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import zeragan.perfit.core.NodeSource;

public class NodeSourceBuilder {

    public static NodeSource build(InputSource configuration) throws IOException, ReflectiveOperationException,
            ParserConfigurationException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document xmlConf = db.parse(configuration);

        Node configuredProfiler = xmlConf.getElementsByTagName("profiler").item(0);
        NodeSource profiler = ConfiguredObjectBuilder.build(configuredProfiler);

        NodeList configuredCollectors = xmlConf.getDocumentElement().getElementsByTagName("collector");
        for (int index = 0; index < configuredCollectors.getLength(); index++) {
            Node configuredCollector = configuredCollectors.item(index);
            profiler.addNodeCollector(NodeCollectorBuilder.build(configuredCollector));
        }

        return profiler;
    }

}

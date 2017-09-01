package zeragan.perfit.core;

import java.io.IOException;
import java.lang.reflect.Constructor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class NodeSourceBuilder
{

    public static NodeSource build(InputSource configuration) throws IOException, ReflectiveOperationException,
        ParserConfigurationException, SAXException
    {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document xmlConf = db.parse(configuration);

        NodeSource profiler = null;
        Node configuredProfiler = xmlConf.getElementsByTagName("profiler").item(0);
        Node confProfilerType = configuredProfiler.getAttributes().getNamedItem("type");
        Class<?> profilerClass = Class.forName(confProfilerType.getNodeValue());

        for (Constructor<?> constructor : profilerClass.getConstructors())
        {
            if (constructor.getParameterTypes().length == 1 && constructor.getParameterTypes()[0].equals(Node.class))
            {
                profiler = (NodeSource) constructor.newInstance(configuredProfiler);
                break;
            }
        }
        if (profiler == null)
        {
            profiler = (NodeSource) profilerClass.newInstance();
        }

        NodeList configuredCollectors = xmlConf.getDocumentElement().getElementsByTagName("collector");
        for (int index = 0; index < configuredCollectors.getLength(); index++)
        {
            Node configuredCollector = configuredCollectors.item(index);
            profiler.addNodeCollector(NodeCollectorBuilder.build(configuredCollector));
        }

        return profiler;
    }

}

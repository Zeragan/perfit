package zeragan.perfit.core;

import java.io.IOException;
import java.lang.reflect.Constructor;

import org.w3c.dom.Node;

public class NodeCollectorBuilder
{

    public static NodeCollector build(Node configurationNode) throws IOException, ReflectiveOperationException
    {

        Node collectorName = configurationNode.getAttributes().getNamedItem("type");
        NodeCollector collector = null;

        Class<?> collectorClass = Class.forName(collectorName.getNodeValue());
        for (Constructor<?> constructor : collectorClass.getConstructors())
        {
            if (constructor.getParameterTypes().length == 1 && constructor.getParameterTypes()[0].equals(Node.class))
            {
                collector = (NodeCollector) constructor.newInstance(configurationNode);
                break;
            }
        }
        if (collector == null)
        {
            collector = (NodeCollector) collectorClass.newInstance();
        }

        return collector;
    }

}

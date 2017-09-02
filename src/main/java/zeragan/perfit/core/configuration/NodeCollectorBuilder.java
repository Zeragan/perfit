package zeragan.perfit.core.configuration;

import java.io.IOException;

import org.w3c.dom.Node;

import zeragan.perfit.core.NodeCollector;

public final class NodeCollectorBuilder {

    private NodeCollectorBuilder() {

    }

    public static NodeCollector build(Node configurationNode) throws IOException, ReflectiveOperationException {

        NodeCollector collector = ConfiguredObjectBuilder.build(configurationNode);

        return collector;
    }

}

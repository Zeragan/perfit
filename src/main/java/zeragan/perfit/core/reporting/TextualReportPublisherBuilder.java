package zeragan.perfit.core.reporting;

import java.io.IOException;

import org.w3c.dom.Node;

import zeragan.perfit.core.configuration.ConfiguredObjectBuilder;
import zeragan.perfit.core.configuration.ConfiguredServices;

public final class TextualReportPublisherBuilder {

    private TextualReportPublisherBuilder() {

    }

    public static TextualReportPublisher build(Node configurationNode) throws IOException, ReflectiveOperationException {

        String publisherName = configurationNode.getAttributes().getNamedItem("name").getNodeValue();
        TextualReportPublisher publisher = ConfiguredObjectBuilder.build(configurationNode);
        ConfiguredServices.setPublisher(publisherName, publisher);

        return publisher;
    }

}

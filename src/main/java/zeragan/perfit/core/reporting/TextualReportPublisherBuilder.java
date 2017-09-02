package zeragan.perfit.core.reporting;

import java.io.IOException;

import org.w3c.dom.Node;

import zeragan.perfit.core.configuration.ConfiguredObjectBuilder;

public final class TextualReportPublisherBuilder {

    private TextualReportPublisherBuilder() {

    }

    public static TextualReportPublisher build(Node configurationNode) throws IOException, ReflectiveOperationException {

        TextualReportPublisher publisher = ConfiguredObjectBuilder.build(configurationNode);

        return publisher;
    }

}

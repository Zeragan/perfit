package zeragan.perfit.core.reporting;

import java.io.IOException;

import org.w3c.dom.Node;

import zeragan.perfit.core.configuration.ConfiguredObjectBuilder;
import zeragan.perfit.core.configuration.ConfiguredServices;

public final class TextualReportGeneratorBuilder {

    private TextualReportGeneratorBuilder() {

    }

    public static TextualReportGenerator build(Node configurationNode) throws IOException, ReflectiveOperationException {

        String generatorName = configurationNode.getAttributes().getNamedItem("name").getNodeValue();
        TextualReportGenerator generator = ConfiguredObjectBuilder.build(configurationNode);
        ConfiguredServices.setGenerator(generatorName, generator);

        return generator;
    }

}

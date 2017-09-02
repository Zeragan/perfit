package zeragan.perfit.core.reporting;

import java.io.IOException;

import org.w3c.dom.Node;

import zeragan.perfit.core.configuration.ConfiguredObjectBuilder;

public final class TextualReportGeneratorBuilder {

    private TextualReportGeneratorBuilder() {

    }

    public static TextualReportGenerator build(Node configurationNode) throws IOException, ReflectiveOperationException {

        TextualReportGenerator generator = ConfiguredObjectBuilder.build(configurationNode);

        return generator;
    }

}

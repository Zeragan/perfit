package zeragan.perfit.core.collector;

import java.io.IOException;

import org.w3c.dom.NodeList;

import zeragan.perfit.core.Node;
import zeragan.perfit.core.NodeCollector;
import zeragan.perfit.core.configuration.ConfiguredServices;
import zeragan.perfit.core.reporting.TextualReportGenerator;
import zeragan.perfit.core.reporting.TextualReportPublisher;
import zeragan.perfit.util.reporting.DefaultTextualReportGenerator;
import zeragan.perfit.util.reporting.DefaultTextualReportPublisher;

abstract class AbstractCollector implements NodeCollector {

    protected AbstractCollector(org.w3c.dom.Node conf) throws IOException, ReflectiveOperationException {
        super();

        NodeList parameters = conf.getChildNodes();
        for (int index = 0; index < parameters.getLength(); index++) {
            org.w3c.dom.Node parameter = parameters.item(index);
            if ("logOnShutdown".equals(parameter.getNodeName())) {
                TextualReportGenerator generatorTmp = null;
                org.w3c.dom.Node generatorName = parameter.getAttributes().getNamedItem("generator");
                if (generatorName != null) {
                    generatorTmp = ConfiguredServices.getGenerator(generatorName.getNodeValue());
                }

                TextualReportPublisher publisherTmp = null;
                org.w3c.dom.Node publisherName = parameter.getAttributes().getNamedItem("publisher");
                if (publisherName != null) {
                    publisherTmp = ConfiguredServices.getPublisher(publisherName.getNodeValue());
                }

                final TextualReportGenerator generator = generatorTmp != null ? generatorTmp : new DefaultTextualReportGenerator();
                final TextualReportPublisher publisher = publisherTmp != null ? publisherTmp : new DefaultTextualReportPublisher();
                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        publisher.publish(generator.generate(AbstractCollector.this.getData()));
                    }
                }));
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void enter(Node node) {

    }

    @Override
    public void exit(Node node) {

    }

}

package zeragan.perfit.core.collector;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.NodeList;

import zeragan.perfit.core.Node;
import zeragan.perfit.core.NodeCollector;
import zeragan.perfit.core.reporting.TextualReportGenerator;
import zeragan.perfit.core.reporting.TextualReportGeneratorBuilder;
import zeragan.perfit.core.reporting.TextualReportPublisher;
import zeragan.perfit.core.reporting.TextualReportPublisherBuilder;
import zeragan.perfit.util.reporting.DefaultTextualReportGenerator;
import zeragan.perfit.util.reporting.DefaultTextualReportPublisher;

abstract class AbstractCollector implements NodeCollector {

    protected AbstractCollector(org.w3c.dom.Node conf) throws IOException, ReflectiveOperationException {
        super();

        Map<String, TextualReportGenerator> generators = new HashMap<>();
        Map<String, TextualReportPublisher> publishers = new HashMap<>();

        NodeList parameters = conf.getChildNodes();
        for (int index = 0; index < parameters.getLength(); index++) {
            org.w3c.dom.Node parameter = parameters.item(index);
            if ("generators".equals(parameter.getNodeName())) {
                for (int index2 = 0; index2 < parameter.getChildNodes().getLength(); index2++) {
                    org.w3c.dom.Node generatorConf = parameter.getChildNodes().item(index2);
                    if ("generator".equals(generatorConf.getNodeName())) {
                        String generatorName = generatorConf.getAttributes().getNamedItem("name").getNodeValue();
                        TextualReportGenerator generator = TextualReportGeneratorBuilder.build(generatorConf);
                        generators.put(generatorName, generator);
                    }
                }
            }
            if ("publishers".equals(parameter.getNodeName())) {
                for (int index2 = 0; index2 < parameter.getChildNodes().getLength(); index2++) {
                    org.w3c.dom.Node publisherConf = parameter.getChildNodes().item(index2);
                    if ("publisher".equals(publisherConf.getNodeName())) {
                        String publisherName = publisherConf.getAttributes().getNamedItem("name").getNodeValue();
                        TextualReportPublisher publisher = TextualReportPublisherBuilder.build(publisherConf);
                        publishers.put(publisherName, publisher);
                    }
                }
            }
            if ("logOnShutdown".equals(parameter.getNodeName())) {
                TextualReportGenerator generatorTmp = null;
                org.w3c.dom.Node generatorName = parameter.getAttributes().getNamedItem("generator");
                if (generatorName != null) {
                    generatorTmp = generators.get(generatorName.getNodeValue());
                }

                TextualReportPublisher publisherTmp = null;
                org.w3c.dom.Node publisherName = parameter.getAttributes().getNamedItem("publisher");
                if (publisherName != null) {
                    publisherTmp = publishers.get(publisherName.getNodeValue());
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

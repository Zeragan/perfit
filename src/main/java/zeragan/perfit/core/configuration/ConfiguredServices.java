package zeragan.perfit.core.configuration;

import java.util.HashMap;
import java.util.Map;

import zeragan.perfit.core.reporting.TextualReportGenerator;
import zeragan.perfit.core.reporting.TextualReportPublisher;

public final class ConfiguredServices {

    private static final Map<String, TextualReportGenerator> generators = new HashMap<>();
    private static final Map<String, TextualReportPublisher> publishers = new HashMap<>();

    private ConfiguredServices() {

    }

    public static TextualReportGenerator getGenerator(String name) {
        return generators.get(name);
    }

    public static TextualReportGenerator setGenerator(String name, TextualReportGenerator generator) {
        return generators.put(name, generator);
    }

    public static TextualReportPublisher getPublisher(String name) {
        return publishers.get(name);
    }

    public static TextualReportPublisher setPublisher(String name, TextualReportPublisher publisher) {
        return publishers.put(name, publisher);
    }

}

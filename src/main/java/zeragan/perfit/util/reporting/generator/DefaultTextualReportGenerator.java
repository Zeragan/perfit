package zeragan.perfit.util.reporting.generator;

import java.util.stream.Collectors;

import zeragan.perfit.core.collector.CollectedData;
import zeragan.perfit.core.reporting.TextualReportGenerator;

public class DefaultTextualReportGenerator implements TextualReportGenerator {

    @Override
    public String generate(CollectedData data) {
        return data.getHotSpots().toList().stream().map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

}

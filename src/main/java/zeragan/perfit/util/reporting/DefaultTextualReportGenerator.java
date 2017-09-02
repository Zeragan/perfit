package zeragan.perfit.util.reporting;

import java.util.stream.Collectors;

import zeragan.perfit.core.CollectedData;
import zeragan.perfit.core.reporting.TextualReportGenerator;

public class DefaultTextualReportGenerator implements TextualReportGenerator {

    @Override
    public String generate(CollectedData data) {
        return data.getList().stream().map(Object::toString).collect(Collectors.joining(System.lineSeparator()));
    }

}

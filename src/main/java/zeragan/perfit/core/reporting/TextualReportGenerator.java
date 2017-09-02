package zeragan.perfit.core.reporting;

import zeragan.perfit.core.collector.CollectedData;

public interface TextualReportGenerator {

    String generate(CollectedData data);

}

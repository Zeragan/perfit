package zeragan.perfit.core.reporting;

import zeragan.perfit.core.CollectedData;

public interface TextualReportGenerator {

    String generate(CollectedData data);

}

package zeragan.perfit.util.reporting.publisher;

import zeragan.perfit.core.reporting.TextualReportPublisher;

public class DefaultTextualReportPublisher implements TextualReportPublisher {

    @Override
    public void publish(String text) {
        System.err.println(text);
    }

}

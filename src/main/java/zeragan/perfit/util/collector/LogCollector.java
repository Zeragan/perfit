package zeragan.perfit.util.collector;

import java.io.IOException;

import zeragan.perfit.core.Node;
import zeragan.perfit.core.collector.DiscardCollector;

public abstract class LogCollector extends DiscardCollector {

    public LogCollector(org.w3c.dom.Node conf) throws IOException, ReflectiveOperationException {
        super(conf);
    }

    @Override
    public void enter(Node node) {
        logEnter(node);
    }

    @Override
    public void exit(Node node) {
        logExit(node);
    }

    protected abstract void logEnter(Node node);

    protected abstract void logExit(Node node);

}

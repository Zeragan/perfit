package zeragan.perfit.core.collector;

import java.io.IOException;

import org.w3c.dom.Node;

import zeragan.perfit.core.CollectedData;

public abstract class DiscardCollector extends AbstractCollector {

    protected DiscardCollector(Node conf) throws IOException, ReflectiveOperationException {
        super(conf);
    }

    @Override
    public CollectedData getData() {
        return new CollectedData();
    }

}

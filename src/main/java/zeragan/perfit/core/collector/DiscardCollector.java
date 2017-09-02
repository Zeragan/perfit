package zeragan.perfit.core.collector;

import java.io.IOException;

import org.w3c.dom.Node;

public abstract class DiscardCollector extends AbstractCollector {

    protected DiscardCollector(Node conf) throws IOException, ReflectiveOperationException {
        super(conf);
    }

}

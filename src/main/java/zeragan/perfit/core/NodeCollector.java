package zeragan.perfit.core;

import zeragan.perfit.core.collector.CollectedData;

public interface NodeCollector {

    void activate(CollectedData collectedData);

    void deactivate();

    boolean isActive();

    void enter(Node node);

    void exit(Node node);

    CollectedData getData();

}

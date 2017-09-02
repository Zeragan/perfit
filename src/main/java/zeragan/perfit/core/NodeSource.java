package zeragan.perfit.core;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public interface NodeSource {

    UUID getId();

    TimeUnit getTimeUnit();

    boolean hasActiveCollector();

    void addNodeCollector(NodeCollector nodeCollector);

    void removeNodeCollector(NodeCollector nodeCollector);

}
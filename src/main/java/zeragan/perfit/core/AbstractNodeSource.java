package zeragan.perfit.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import zeragan.perfit.core.collector.CollectedData;

public class AbstractNodeSource implements NodeSource {

    private TimeUnit timeUnit = null;

    private final UUID id = UUID.randomUUID();

    private final List<NodeCollector> nodeCollectors = new ArrayList<>();

    protected final void setTimeUnit(TimeUnit timeUnit) {
        if (this.timeUnit != null) {
            throw new IllegalStateException();
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException();
        }
        this.timeUnit = timeUnit;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public boolean hasActiveCollector() {
        for (NodeCollector nodeCollector : nodeCollectors) {
            if (nodeCollector.isActive()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public final void addNodeCollector(NodeCollector nodeCollector) {
        nodeCollector.activate(new CollectedData(getId(), getTimeUnit()));
        this.nodeCollectors.add(nodeCollector);
    }

    @Override
    public final void removeNodeCollector(NodeCollector nodeCollector) {
        nodeCollector.deactivate();
        this.nodeCollectors.remove(nodeCollector);
    }

    protected void notifyEnter(Node node) {
        for (NodeCollector nodeCollector : nodeCollectors) {
            nodeCollector.enter(node);
        }
    }

    protected void notifyExit(Node node) {
        for (NodeCollector nodeCollector : nodeCollectors) {
            nodeCollector.exit(node);
        }
    }

}

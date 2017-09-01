package zeragan.perfit.core;

import java.util.ArrayList;
import java.util.List;

public class AbstractNodeSource implements NodeSource
{

    private final List<NodeCollector> nodeCollectors = new ArrayList<>();

    @Override
    public boolean hasActiveCollector()
    {
        for (NodeCollector nodeCollector : nodeCollectors)
        {
            if (nodeCollector.isActive())
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public final void addNodeCollector(NodeCollector nodeCollector)
    {
        this.nodeCollectors.add(nodeCollector);
    }

    @Override
    public final void removeNodeCollector(NodeCollector nodeCollector)
    {
        this.nodeCollectors.remove(nodeCollector);
    }

    protected void notifyEnter(Node node)
    {
        for (NodeCollector nodeCollector : nodeCollectors)
        {
            nodeCollector.enter(node);
        }
    }

    protected void notifyExit(Node node)
    {
        for (NodeCollector nodeCollector : nodeCollectors)
        {
            nodeCollector.exit(node);
        }
    }

}

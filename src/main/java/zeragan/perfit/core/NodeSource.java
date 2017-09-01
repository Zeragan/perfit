package zeragan.perfit.core;

public interface NodeSource
{
    
    boolean hasActiveCollector();

    void addNodeCollector(NodeCollector nodeCollector);

    void removeNodeCollector(NodeCollector nodeCollector);

}
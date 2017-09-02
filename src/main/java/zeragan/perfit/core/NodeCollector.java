package zeragan.perfit.core;

public interface NodeCollector {

    boolean isActive();

    void enter(Node node);

    void exit(Node node);

    CollectedData getData();

}

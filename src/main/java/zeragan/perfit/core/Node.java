package zeragan.perfit.core;

public interface Node {

    String getThreadName();

    String getNodeName();

    long getTotalTime();

    long getInnerTime();

}